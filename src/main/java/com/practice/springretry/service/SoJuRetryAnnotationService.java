package com.practice.springretry.service;

import com.practice.springretry.common.exception.SoJuApiException;
import com.practice.springretry.service.info.ApiType;
import com.practice.springretry.service.info.RetryStatus;
import com.practice.springretry.service.info.SoJuCountInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SoJuRetryAnnotationService {

    private int retryCount = 0;

    @Retryable(
            value = {SoJuApiException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 2000))
    public SoJuCountInfo getSoJuCount(String soJuName) {
        retryCount++;
        log.info("retry(Annotation) V1 Count {}", retryCount);

        if (retryCount == 4) {
            initRetryCount();
            return SoJuCountInfo.of(ApiType.V1, RetryStatus.SUCCESS, soJuName, 11);
        } else {
            throw new SoJuApiException();
        }
    }

    @Retryable(
            value = {SoJuApiException.class},
            maxAttempts = 4,
            backoff = @Backoff(delay = 2000))
    public SoJuCountInfo getSoJuCountFail(String soJuName) {
        retryCount++;
        log.info("retry(Annotation) V2 Count {}", retryCount);
        throw new SoJuApiException();
    }

    @Recover
    public SoJuCountInfo getSoJuRecover(SoJuApiException e, String soJuName) {
        log.info("retry(Annotation) V2 Fail, Recover");
        initRetryCount();
        return SoJuCountInfo.of(ApiType.V2, RetryStatus.RECOVER, soJuName, 22);
    }

    private void initRetryCount() {
        retryCount = 0;
    }
}
