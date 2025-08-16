package com.juandavyc.products.controller;

import com.juandavyc.products.dto.response.ConfigInfoDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class ConfigInfoController {

    @Value("${build.version}")
    private String buildVersion;

    @Value("${spring.application.name}")
    private String applicationName;

    @GetMapping(path = "/config-info")
    public ResponseEntity<ConfigInfoDto> getConfigInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ConfigInfoDto(buildVersion, applicationName));
    }

}
