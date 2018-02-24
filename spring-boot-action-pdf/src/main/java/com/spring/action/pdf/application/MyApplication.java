package com.spring.action.pdf.application;

import com.google.common.collect.Lists;
import com.spring.action.pdf.application.config.listener.EventPublisher;
import com.spring.action.pdf.application.model.ReportModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pdf.kit.component.PDFHeaderFooter;
import pdf.kit.component.PDFKit;
import pdf.kit.component.chart.Line;
import pdf.kit.component.chart.impl.TemperatureLineChart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fgm on 2017/4/22.
 */
@Controller
@SpringBootApplication
public class MyApplication {

    @Autowired
    private EventPublisher publisher;

    @RequestMapping("/")
    public  String notFound(){
        return "/404";
    }

    @RequestMapping("/hello")
    public @ResponseBody String hello(){
        return "hello world";
    }

    @RequestMapping(value = "/publish", produces = "text/plain;charset=UTF-8")
    public @ResponseBody String publish(String msg){

        publisher.publish(msg);
        return "publish success!";
    }



    @RequestMapping("/pdf")
    public  @ResponseBody String pdf(){
        ReportModel templateBO=new ReportModel();
        templateBO.setTemplateName("Hello iText! Hello freemarker! Hello jFreeChart!");
        templateBO.setFreeMarkerUrl("http://www.zheng-hang.com/chm/freemarker2_3_24/ref_directive_if.html");
        templateBO.setITEXTUrl("http://developers.itextpdf.com/examples-itext5");
        templateBO.setJFreeChartUrl("http://www.yiibai.com/jfreechart/jfreechart_referenced_apis.html");
        templateBO.setImageUrl("http://mss.vip.sankuai.com/v1/mss_74e5b6ab17f44f799a524fa86b6faebf/360report/logo_1.png");
        List<String> scores=new ArrayList<String>();
        scores.add("90");
        scores.add("95");
        scores.add("98");
        templateBO.setScores(scores);
        templateBO.setImageUrl("http://i2.dpfile.com/s/res/betalogo.v4.png");
        List<Line> lineList=getTemperatureLineList();
        TemperatureLineChart lineChart=new TemperatureLineChart();
        String picUrl=lineChart.draw(lineList,0);
        templateBO.setPicUrl(picUrl);


        //设置自定义PDF页眉页脚工具类
        PDFHeaderFooter headerFooter=new PDFHeaderFooter();
        PDFKit kit=new PDFKit();
        kit.setHeaderFooterBuilder(headerFooter);
        //设置输出路径
        kit.setSaveFilePath("/Users/fgm/Desktop/pdf/hello.pdf");
        String saveFilePath=kit.exportToFile("hello.pdf",templateBO);

       return saveFilePath;

    }


    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class,args);
    }
    public static List<Line> getTemperatureLineList() {
        List<Line> list= Lists.newArrayList();
        for(int i=1;i<=7;i++){
            Line line=new Line();
            float random=Math.round(Math.random()*10);
            line.setXValue("星期"+i);
            line.setYValue(20+random);
            line.setGroupName("下周");
            list.add(line);
        }
        for(int i=1;i<=7;i++){
            Line line=new Line();
            float random=Math.round(Math.random()*10);
            line.setXValue("星期"+i);
            line.setYValue(20+random);
            line.setGroupName("这周");
            list.add(line);
        }
        return list;
    }




}
