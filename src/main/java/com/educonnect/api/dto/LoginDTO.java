package com.educonnect.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginDTO {

    @NotBlank(message = "Login (e-mail) é obrigatório")
    private String login;

    @NotBlank(message = "Senha é obrigatória")
    private String senha;
}
