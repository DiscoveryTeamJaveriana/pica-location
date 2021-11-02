package co.edu.javeriana.discovery.pica.location.service;

import co.edu.javeriana.discovery.pica.location.controller.model.ReqPostLocacion;
import co.edu.javeriana.discovery.pica.location.controller.model.RespGetLocacion;

import java.util.ArrayList;

public interface ILocationService {
   void  postLocacion (ReqPostLocacion request, String rquid);
    ArrayList<RespGetLocacion> getLocaciones (String rquid);
    RespGetLocacion getLocacion (String codigo, String rquid);
}