package com.practice.springretry.controller;

import com.practice.springretry.controller.dto.SoJuCountResponse;
import com.practice.springretry.service.*;
import com.practice.springretry.service.info.ApiType;
import com.practice.springretry.service.info.SoJuCountInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SoJuController {

    private final SoJuRetryAnnotationService retryAnnotationService;
    private final SoJuRetryTemplateService retryTemplateService;

    @GetMapping("/api/v1/soju/{soJuName}/count")
    public ResponseEntity<SoJuCountResponse> getSoJuCountV1(@PathVariable String soJuName) {
        SoJuCountInfo info = retryAnnotationService.getSoJuCount(soJuName);
        SoJuCountResponse response = SoJuCountResponse.of(info);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/v2/soju/{soJuName}/count")
    public ResponseEntity<SoJuCountResponse> getSoJuCountV2(@PathVariable String soJuName) {
        SoJuCountInfo info = retryAnnotationService.getSoJuCountFail(soJuName);
        SoJuCountResponse response = SoJuCountResponse.of(info);
        return ResponseEntity.ok(response);
    }

    @GetMapping("api/v3/soju/{soJuName}/count")
    public ResponseEntity<SoJuCountResponse> getSoJuCountV3(@PathVariable String soJuName) {
        SoJuCountInfo info = retryTemplateService.getSoJuCount(soJuName);
        SoJuCountResponse response = SoJuCountResponse.of(info);
        return ResponseEntity.ok(response);
    }

    @GetMapping("api/v4/soju/{soJuName}/count")
    public ResponseEntity<SoJuCountResponse> getSoJuCountV4(@PathVariable String soJuName) {
        SoJuCountInfo info = retryTemplateService.getSoJuCountMethod(soJuName, ApiType.V4);
        SoJuCountResponse response = SoJuCountResponse.of(info);
        return ResponseEntity.ok(response);
    }

}
