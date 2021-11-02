package co.edu.javeriana.discovery.pica.location.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

@Validated
@Data
public class RespGetLocacion   {
  @JsonProperty("Codigo")
  private String codigo = null;

  @JsonProperty("Nombre")
  private String nombre = null;

  @JsonProperty("Direccion")
  private String direccion = null;

  @JsonProperty("Barrio")
  private String barrio = null;

  @JsonProperty("Sector")
  private String sector = null;

  @JsonProperty("Representante")
  private String representante = null;

  @JsonProperty("Correo")
  private String correo = null;

  @JsonProperty("Telefono")
  private String telefono = null;

}

