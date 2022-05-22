package com.practice.springretry.service;

import com.practice.springretry.common.exception.SoJuApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SoJuApiService {

    private int retryCount = 0;

    @Retryable(
            value = {SoJuApiException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 2000))
    public int getSoJuCount(String sojuName) {
        retryCount++;
        log.info("retry {} Count {}", sojuName, retryCount);
        if (retryCount == 4) {
            return 100;
        } else {
            throw new SoJuApiException();
        }
    }

    @Retryable(
            value = {SoJuApiException.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 2000))
    public int getSoJuCountFail(String sojuName) {
        retryCount++;
        log.info("retry {} Count {}", sojuName, retryCount);
        throw new SoJuApiException();
    }

    @Recover
    public int getSoJuRecover(SoJuApiException e, String sojuName) {
        log.info("getSoJuCount Api Fail, Recover : {}", sojuName);
        return 123;
    }
}
