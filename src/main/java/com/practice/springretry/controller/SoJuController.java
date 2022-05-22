package com.practice.springretry.controller;

import com.practice.springretry.service.SoJuApiService;
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

    private final SoJuApiService soJuApiService;

    @GetMapping("/api/v1/soju/{sojuName}/count")
    public ResponseEntity<Integer> getSoJuCountV1(@PathVariable String sojuName) {
        return ResponseEntity.ok(soJuApiService.getSoJuCount(sojuName));
    }

    @GetMapping("/api/v2/soju/{sojuName}/count")
    public ResponseEntity<Integer> getSoJuCountV2(@PathVariable String sojuName) {
        return ResponseEntity.ok(soJuApiService.getSoJuCountFail(sojuName));
    }
}
