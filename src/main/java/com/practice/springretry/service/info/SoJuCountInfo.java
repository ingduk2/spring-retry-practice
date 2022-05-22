package com.practice.springretry.service.info;

public record SoJuCountInfo (
        ApiType apiType,
        RetryStatus status,
        String soJuName,
        int count
) {
    public static SoJuCountInfo of(
            ApiType apiType,
            RetryStatus status,
            String soJuName,
            int count) {
        return new SoJuCountInfo(
                apiType,
                status,
                soJuName,
                count);
    }
}
