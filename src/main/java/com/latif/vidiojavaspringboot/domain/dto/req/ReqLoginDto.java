package com.latif.vidiojavaspringboot.domain.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReqLoginDto {

    @NotBlank
    @Email(message = "email must in the right email format")
    private String email;

    @NotBlank
    @Size(min = 8, message = "password must be more than 8 char")
    private String password;

}
