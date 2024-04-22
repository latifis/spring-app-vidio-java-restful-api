package com.latif.vidiojavaspringboot.domain.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResJwtValidationDto {

    private String email;

    private int id;

    private String username;

    private String type;

}
