package com.practice.springretry.service.info;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApiType {
    V1("V1", "Retry(Annotation), Success"),
    V2("V2", "Retry(Annotation) Finally Fail, Recover"),
    V3("V3", "Retry(Template) Success And Recover"),
    V4("V4", "Retry(Template-Method) Success And Recover");

    private final String version;
    private final String description;
}
