package com.spring.action.pdf.application.model;

import lombok.Data;

import java.util.List;

/**
 * Created by fgm on 2017/5/13.
 */
@Data
public class ReportModel {
    private String templateName;
    private String freeMarkerUrl;
    private String ITEXTUrl;
    private String jFreeChartUrl;
    private String imageUrl;
    private String picUrl;
    private List<String> scores;
}
