package com.juandavyc.products.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@AllArgsConstructor
@Data
public class ConfigInfoDto {

    private String buildVersion;
    private String applicationName;

}
