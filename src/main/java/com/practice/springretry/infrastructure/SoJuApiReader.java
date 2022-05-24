package com.practice.springretry.infrastructure;

import com.practice.springretry.common.exception.SoJuApiException;
import com.practice.springretry.service.info.ApiType;
import com.practice.springretry.service.info.RetryStatus;
import com.practice.springretry.service.info.SoJuCountInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SoJuApiReader {

    private int retryCount = 0;

    public SoJuCountInfo findSoJuCount(String soJuName, ApiType apiType) {
        retryCount++;
        log.info("retry(template) {} Count {}",apiType.name(), retryCount);
        if (retryCount == 4) {
            return SoJuCountInfo.of(apiType, RetryStatus.SUCCESS, soJuName, 44);
        } else {
            throw new SoJuApiException();
        }
    }

    public SoJuCountInfo recover(String soJuName, ApiType apiType) {
        log.info("retry(template) {} Fail, Recover", apiType.name());
        return SoJuCountInfo.of(apiType, RetryStatus.RECOVER, soJuName, 444);
    }
}
