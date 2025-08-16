package com.juandavyc.accounts.controller;

import com.juandavyc.accounts.dto.response.ConfigInfoResponseDto;
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
    public ResponseEntity<ConfigInfoResponseDto> getConfigInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ConfigInfoResponseDto(buildVersion, applicationName));
    }

}
