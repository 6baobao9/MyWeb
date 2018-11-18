package com.wy.springtest.controller;

import java.util.HashMap;
import java.util.Map;

public class Result {
    public final static String OK = "00";
    public final static String ERR = "01";

    private String code;
    private String reason;
    private Map<String, Object> body;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Result() {
        body = new HashMap<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Map<String, Object> getBody() {
        return body;
    }
}
