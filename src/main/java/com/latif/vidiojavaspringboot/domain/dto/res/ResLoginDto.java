package com.latif.vidiojavaspringboot.domain.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResLoginDto {

    private String email;

    private String token;

}
