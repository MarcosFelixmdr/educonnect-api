package com.educonnect.api.dto;

import com.educonnect.api.model.Usuario;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotNull(message = "Tipo é obrigatório")
    private Usuario.TipoUsuario tipo;

    private String bio;
    private String areaAtuacao;
    private String foto;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
}
