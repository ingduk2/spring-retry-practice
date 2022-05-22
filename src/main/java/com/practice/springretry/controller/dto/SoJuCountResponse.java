package com.practice.springretry.controller.dto;


import com.practice.springretry.service.info.RetryStatus;
import com.practice.springretry.service.info.SoJuCountInfo;

public record SoJuCountResponse(
        String apiVersion,
        String apiDescription,
        RetryStatus status,
        String soJuName,
        int count
) {
    public static SoJuCountResponse of(
            SoJuCountInfo info) {
        return new SoJuCountResponse(
                info.apiType().getVersion(),
                info.apiType().getDescription(),
                info.status(),
                info.soJuName(),
                info.count());
    }
}
