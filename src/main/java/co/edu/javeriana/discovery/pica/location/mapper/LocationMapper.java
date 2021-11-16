package co.edu.javeriana.discovery.pica.location.mapper;

import co.edu.javeriana.discovery.pica.location.controller.model.ReqPostLocacion;
import co.edu.javeriana.discovery.pica.location.controller.model.RespGetLocacion;
import co.edu.javeriana.discovery.pica.location.repository.model.Location;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocationMapper {

    public static RespGetLocacion mapLocationToResptGetLocacion(final Location location) {
        RespGetLocacion respGetLocacion = new RespGetLocacion();
        respGetLocacion.setCodigo(location.getId());
        respGetLocacion.setNombre(location.getName());
        respGetLocacion.setDireccion(location.getAddress());
        respGetLocacion.setBarrio(location.getNeighborhood());
        respGetLocacion.setSector(location.getArea());
        respGetLocacion.setRepresentante(location.getRepresentative());
        respGetLocacion.setCorreo(location.getEmail());
        respGetLocacion.setTelefono(location.getPhone());

        return respGetLocacion;
    }

    public static Location mapReqPostLocacionToLocation(final ReqPostLocacion reqPostLocacion) {

        return Location.builder()
                .name(reqPostLocacion.getNombre())
                .address(reqPostLocacion.getDireccion())
                .neighborhood(reqPostLocacion.getBarrio())
                .area(reqPostLocacion.getSector())
                .representative(reqPostLocacion.getRepresentante())
                .email(reqPostLocacion.getCorreo())
                .phone(reqPostLocacion.getTelefono())
                .build();
    }
}
