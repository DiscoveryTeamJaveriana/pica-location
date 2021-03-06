package co.edu.javeriana.discovery.pica.location.controller;

import co.edu.javeriana.discovery.pica.location.controller.model.Error;
import co.edu.javeriana.discovery.pica.location.controller.model.ReqPostLocacion;
import co.edu.javeriana.discovery.pica.location.controller.model.RespGetLocacion;
import co.edu.javeriana.discovery.pica.location.service.ILocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/c3p/v1/Portal")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LocationController {

    private static final String XRQUID = "X-RqUID";
    private static final String CODIGO = "Codigo";
    private static final String REQUEST = "Request";
    private static final String RESPONSE = "Response";
    private static final String RESPONSECODE = "ResponseCode";
    private static final String RQUID = "RqUID";
    private static final String ERRORCREACION = "Error al crear locacion";
    private static final String CODIGOERRORCREACION = "300";
    private static final String ERRORCONSULTA = "Error al consultar locaciones";
    private static final String CODIGOERRORCONSULTA = "200";

    private ILocationService locationService;

    @Autowired
    private Tracer tracer;

    @Autowired
    public LocationController(ILocationService locationService) {
        this.locationService = locationService;
    }


    @PostMapping("/Locacion")
        public ResponseEntity<Object> postLocacion(@RequestBody ReqPostLocacion reqPostLocacion, @RequestHeader(value=XRQUID) String xRqUID ) throws JsonProcessingException {
        log.info("Creating Locacion for RqUID {}", xRqUID);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(reqPostLocacion);
        LocationController.this.tracer.currentSpan().tag(REQUEST,json);
        LocationController.this.tracer.currentSpan().tag(RQUID,xRqUID);
        try {
        locationService.postLocacion(reqPostLocacion, xRqUID);
        }catch (Exception e) {
            LocationController.this.tracer.currentSpan().tag(RESPONSECODE,HttpStatus.PARTIAL_CONTENT.toString());
            String jsonError = mapper.writeValueAsString(buildError(ERRORCREACION,CODIGOERRORCREACION));
            LocationController.this.tracer.currentSpan().tag(RESPONSE,jsonError);
            return new ResponseEntity<>(jsonError,putRqUIDHeader(xRqUID),HttpStatus.PARTIAL_CONTENT);
        }
        LocationController.this.tracer.currentSpan().tag(RESPONSECODE,HttpStatus.CREATED.toString());
        return new ResponseEntity<>(putRqUIDHeader(xRqUID),HttpStatus.CREATED);
    }

    @GetMapping("/Locaciones")
    public ResponseEntity<Object> getLocaciones(@RequestHeader(value=XRQUID) String xRqUID ) throws JsonProcessingException {
        log.info("Get Locaciones for RqUID {}", xRqUID);
        ObjectMapper mapper = new ObjectMapper();
        LocationController.this.tracer.currentSpan().tag(RQUID,xRqUID);
        try {
            List<RespGetLocacion> response = locationService.getLocaciones(xRqUID);
            String json = mapper.writeValueAsString(response);
            LocationController.this.tracer.currentSpan().tag(RESPONSE,json);
            LocationController.this.tracer.currentSpan().tag(RESPONSECODE,HttpStatus.OK.toString());
            return new ResponseEntity<>(response, putRqUIDHeader(xRqUID), HttpStatus.OK);
        }catch (Exception e) {
            LocationController.this.tracer.currentSpan().tag(RESPONSECODE,HttpStatus.PARTIAL_CONTENT.toString());
            String jsonError = mapper.writeValueAsString(buildError(ERRORCONSULTA,CODIGOERRORCONSULTA));
            LocationController.this.tracer.currentSpan().tag(RESPONSE,jsonError);
            return new ResponseEntity<>(jsonError,putRqUIDHeader(xRqUID),HttpStatus.PARTIAL_CONTENT);
        }
    }

    @GetMapping("/Locacion/{Codigo}")
    public ResponseEntity<RespGetLocacion> getLocacion(@RequestHeader(value=XRQUID) String xRqUID, @PathVariable(CODIGO) String codigo) throws JsonProcessingException {
        log.info("Get Locacion for RqUID {}", xRqUID);
        LocationController.this.tracer.currentSpan().tag(RQUID,xRqUID);
        RespGetLocacion response = locationService.getLocacion(codigo, xRqUID);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(response);
        LocationController.this.tracer.currentSpan().tag(RESPONSE,json);
        LocationController.this.tracer.currentSpan().tag(RESPONSECODE,HttpStatus.OK.toString());
        return new ResponseEntity<>(response,putRqUIDHeader(xRqUID),HttpStatus.OK);
    }

    private HttpHeaders putRqUIDHeader(String rquid) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(XRQUID,rquid);
        return headers;
    }

    private Error buildError(String mensaje, String codigo) {
        Error error = new Error();
        error.setCodigo(codigo);
        error.setMensaje(mensaje);
        return error;
    }
}
