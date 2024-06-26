package com.latif.vidiojavaspringboot.domain.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ReqLoginDto {

    @NotBlank(message = "email must not be blank")
    @Email(message = "email must in the right email format")
    private String email;

    @NotBlank(message = "password must not be blank")
    @Size(min = 8, message = "password must be more than 8 char")
    private String password;

}
