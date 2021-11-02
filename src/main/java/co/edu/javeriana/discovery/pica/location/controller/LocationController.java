package co.edu.javeriana.discovery.pica.location.controller;

import co.edu.javeriana.discovery.pica.location.controller.model.ReqPostLocacion;
import co.edu.javeriana.discovery.pica.location.controller.model.RespGetLocacion;
import co.edu.javeriana.discovery.pica.location.service.ILocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final String RQUID = "X-RqUID";
    private static final String CODIGO = "Codigo";

    private ILocationService locationService;

    @Autowired
    public LocationController(ILocationService locationService) {
        this.locationService = locationService;
    }


    @PostMapping("/Locacion")
        public ResponseEntity<Void> postLocacion(@RequestBody ReqPostLocacion reqPostLocacion, @RequestHeader(value=RQUID) String xRqUID ) {
        log.info("Creating Locacion for RqUID {}", xRqUID);
        locationService.postLocacion(reqPostLocacion, xRqUID);
        return new ResponseEntity<>(putRqUIDHeader(xRqUID),HttpStatus.CREATED);
    }

    @GetMapping("/Locaciones")
    public ResponseEntity< ArrayList<RespGetLocacion>> getLocaciones(@RequestHeader(value=RQUID) String xRqUID ) {
        log.info("Get Locaciones for RqUID {}", xRqUID);
        ArrayList<RespGetLocacion> response = locationService.getLocaciones(xRqUID);
        return new ResponseEntity<>(response, putRqUIDHeader(xRqUID), HttpStatus.OK);
    }

    @GetMapping("/Locacion/{Codigo}")
    public ResponseEntity<RespGetLocacion> getLocacion(@RequestHeader(value=RQUID) String xRqUID, @PathVariable(CODIGO) String codigo) {
        log.info("Get Locacion for RqUID {}", xRqUID);
        RespGetLocacion response = locationService.getLocacion(codigo, xRqUID);
        return new ResponseEntity<>(response,putRqUIDHeader(xRqUID),HttpStatus.OK);
    }

    private HttpHeaders putRqUIDHeader(String rquid) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(RQUID,rquid);
        return headers;
    }
}
