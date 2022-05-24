package com.practice.springretry.service;

import com.practice.springretry.common.exception.SoJuApiException;
import com.practice.springretry.infrastructure.SoJuApiReader;
import com.practice.springretry.service.info.ApiType;
import com.practice.springretry.service.info.RetryStatus;
import com.practice.springretry.service.info.SoJuCountInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SoJuRetryTemplateService {

    private int retryCount = 0;

    private final RetryTemplate retryTemplate;
    private final SoJuApiReader soJuApiReader;

    public SoJuCountInfo getSoJuCount(String soJuName) {
        return retryTemplate.execute(
                retry -> {
                    retryCount++;
                    log.info("retry(Template) V3 Count {}", retryCount);

                    if (retryCount == 4) {
                        return SoJuCountInfo.of(ApiType.V3, RetryStatus.SUCCESS, soJuName, 33);
                    } else {
                        throw new SoJuApiException();
                    }
                },
                recovery -> {
                    log.info("retry(Template) V3 Fail, Recovery");
                    return SoJuCountInfo.of(ApiType.V3, RetryStatus.RECOVER, soJuName, 333);
                });
    }

    public SoJuCountInfo getSoJuCountMethod(String soJuName, ApiType apiType) {
        return retryTemplate.execute(
                retry -> soJuApiReader.findSoJuCount(soJuName, apiType),
                recovery -> soJuApiReader.recover(soJuName, apiType)
        );
    }

}
