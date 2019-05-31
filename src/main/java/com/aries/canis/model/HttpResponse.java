package com.aries.canis.model;

import com.alibaba.fastjson.JSON;
import com.aries.canis.constants.SystemStatus;


public class HttpResponse {
    private Integer code;
    private String message;

    public static String of(SystemStatus systemStatus) {
        return JSON.toJSONString(systemStatus);
    }
}
