package co.edu.javeriana.discovery.pica.location.service.impl;

import co.edu.javeriana.discovery.pica.location.controller.model.ReqPostLocacion;
import co.edu.javeriana.discovery.pica.location.controller.model.RespGetLocacion;
import co.edu.javeriana.discovery.pica.location.service.ILocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
public class LocationService implements ILocationService {
    @Override
    public void postLocacion(ReqPostLocacion request, String rquid) {
        log.info("Panic implement me !");

    }

    @Override
    public ArrayList<RespGetLocacion> getLocaciones(String rquid) {
        log.info("Panic implement me !");
        ArrayList<RespGetLocacion> respGetLocacions = new ArrayList<RespGetLocacion>();
        RespGetLocacion respGetLocacion = new RespGetLocacion();
        respGetLocacion.setCodigo("1234");
        respGetLocacion.setNombre("Mauro");
        respGetLocacion.setDireccion("Calle 123");
        respGetLocacion.setBarrio("Pradera Norte");
        respGetLocacion.setSector("Norte");
        respGetLocacion.setRepresentante("Mauro");
        respGetLocacion.setCorreo("mauro@gmail.com");
        respGetLocacion.setTelefono("310310310");

        RespGetLocacion respGetLocacion2 = new RespGetLocacion();
        respGetLocacion2.setCodigo("1234");
        respGetLocacion2.setNombre("Mauro");
        respGetLocacion2.setDireccion("Calle 123");
        respGetLocacion2.setBarrio("Pradera Norte");
        respGetLocacion2.setSector("Norte");
        respGetLocacion2.setRepresentante("Mauro");
        respGetLocacion2.setCorreo("mauro@gmail.com");
        respGetLocacion2.setTelefono("310310310");

        respGetLocacions.add(respGetLocacion);
        respGetLocacions.add(respGetLocacion2);
        return respGetLocacions;
    }

    @Override
    public RespGetLocacion getLocacion(String codigo, String rquid) {
        log.info("Panic implement me !");
        RespGetLocacion respGetLocacion = new RespGetLocacion();
        respGetLocacion.setCodigo("1234");
        respGetLocacion.setNombre("Mauro");
        respGetLocacion.setDireccion("Calle 123");
        respGetLocacion.setBarrio("Pradera Norte");
        respGetLocacion.setSector("Norte");
        respGetLocacion.setRepresentante("Mauro");
        respGetLocacion.setCorreo("mauro@gmail.com");
        respGetLocacion.setTelefono("310310310");
        return respGetLocacion;
    }
}
