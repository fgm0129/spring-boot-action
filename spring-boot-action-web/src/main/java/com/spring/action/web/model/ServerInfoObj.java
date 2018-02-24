package com.spring.action.web.model;

import lombok.Data;

/**
 * Created by fgm on 2017/5/25.
 */
@Data
public class ServerInfoObj {
    private String serviceName;
    private String ip;
    private String port;
    private String version;
    private String weight;
}
