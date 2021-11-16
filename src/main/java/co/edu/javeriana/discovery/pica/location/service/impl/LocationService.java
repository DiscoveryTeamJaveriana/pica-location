package co.edu.javeriana.discovery.pica.location.service.impl;

import co.edu.javeriana.discovery.pica.location.controller.model.ReqPostLocacion;
import co.edu.javeriana.discovery.pica.location.controller.model.RespGetLocacion;
import co.edu.javeriana.discovery.pica.location.mapper.LocationMapper;
import co.edu.javeriana.discovery.pica.location.repository.LocationRepository;
import co.edu.javeriana.discovery.pica.location.repository.model.Location;
import co.edu.javeriana.discovery.pica.location.service.ILocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationService implements ILocationService {

    private final LocationRepository locationRepository;

    @Override
    public void postLocacion(ReqPostLocacion request, String rquid) {

        Location location = LocationMapper.mapReqPostLocacionToLocation(request);
        locationRepository.save(location);

    }

    @Override
    public List<RespGetLocacion> getLocaciones(String rquid) {

        return locationRepository.findAll().stream()
                .map(LocationMapper::mapLocationToResptGetLocacion)
                .collect(Collectors.toList());
    }

    @Override
    public RespGetLocacion getLocacion(String codigo, String rquid) {

        return LocationMapper
                .mapLocationToResptGetLocacion(locationRepository.findById(codigo)
                        .orElseThrow(() -> new RuntimeException("No Location")));
        //TODO: Add ControllerAdvice for exception control
    }
}
