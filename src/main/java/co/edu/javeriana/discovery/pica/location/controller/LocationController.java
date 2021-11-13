package co.edu.javeriana.discovery.pica.location.controller;

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

import java.util.ArrayList;

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

    private ILocationService locationService;

    @Autowired
    private Tracer tracer;

    @Autowired
    public LocationController(ILocationService locationService) {
        this.locationService = locationService;
    }


    @PostMapping("/Locacion")
        public ResponseEntity<Void> postLocacion(@RequestBody ReqPostLocacion reqPostLocacion, @RequestHeader(value=XRQUID) String xRqUID ) throws JsonProcessingException {
        log.info("Creating Locacion for RqUID {}", xRqUID);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(reqPostLocacion);
        LocationController.this.tracer.currentSpan().tag(REQUEST,json);
        LocationController.this.tracer.currentSpan().tag(RQUID,xRqUID);
        locationService.postLocacion(reqPostLocacion, xRqUID);
        LocationController.this.tracer.currentSpan().tag(RESPONSECODE,HttpStatus.CREATED.toString());
        return new ResponseEntity<>(putRqUIDHeader(xRqUID),HttpStatus.CREATED);
    }

    @GetMapping("/Locaciones")
    public ResponseEntity< ArrayList<RespGetLocacion>> getLocaciones(@RequestHeader(value=XRQUID) String xRqUID ) throws JsonProcessingException {
        log.info("Get Locaciones for RqUID {}", xRqUID);
        LocationController.this.tracer.currentSpan().tag(RQUID,xRqUID);
        ArrayList<RespGetLocacion> response = locationService.getLocaciones(xRqUID);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(response);
        LocationController.this.tracer.currentSpan().tag(RESPONSE,json);
        LocationController.this.tracer.currentSpan().tag(RESPONSECODE,HttpStatus.OK.toString());
        return new ResponseEntity<>(response, putRqUIDHeader(xRqUID), HttpStatus.OK);
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
}
