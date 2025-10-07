package com.javbre.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateRequest {

    @NotBlank(message = "El tipo de identificación no puede estar vacío")
    @Size(max = 3, message = "El tipo de identificación debe tener máximo 3 caracteres")
    @Pattern(
            regexp = "^(CC|PS|CE)$",
            message = "El tipo de identificación debe ser uno de: CC, PS o CE"
    )
    @JsonProperty("identificationType")
    private String identificationType;

    @NotBlank(message = "El número de identificación no puede estar vacío")
    @Size(max = 10, message = "El número de identificación debe tener máximo 10 caracteres")
    @Pattern(regexp = "\\d+", message = "El número de identificación solo puede contener dígitos")
    @JsonProperty("identificationDocument")
    private String identificationDocument;

    @JsonProperty("name")
    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("telephoneNumber")
    @NotBlank(message = "El número de teléfono no puede estar vacío")
    @Size(max = 10, message = "El número de teléfono debe tener máximo 10 caracteres")
    @Pattern(regexp = "\\d+", message = "El número de teléfono solo puede contener dígitos")
    private String telephoneNumber;

    @JsonProperty("email")
    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email(message = "El correo electrónico no es válido")
    private String email;

}
