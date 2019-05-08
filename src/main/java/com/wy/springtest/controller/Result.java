package com.wy.springtest.controller;

import java.util.HashMap;
import java.util.Map;

public class Result {
    private CODE code;
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

    public CODE getCode() {
        return code;
    }

    public void setCode(CODE code) {
        this.code = code;
    }

    public Map<String, Object> getBody() {
        return body;
    }

    public enum CODE {
        OK("00"), ERR("01");
        private final String code;

        CODE(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        @Override
        public String toString() {
            return code;
        }
    }
}
