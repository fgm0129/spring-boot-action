package com.spring.boot.aciton.mailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Created by fgm on 2017/12/31.
 */
@RestController
@SpringBootApplication
public class Application {

    @Autowired
    JavaMailSender mailSender;

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }


    @RequestMapping("/")
    public String hello(){
        return "Hello World!";
    }

    @RequestMapping("/send")
    public String send(){

        try {

           // String email="yangyongli@outlook.com";
            String email="562629704@qq.com";

            String content="<!DOCTYPE html>\n" +
                    "<html lang=\"zh-cn\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\"/>\n" +
                    "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge,chrome=1\"/>\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no\"/>\n" +
                    "    <meta name=\"format-detection\" content=\"telephone=no,email=no\"/>\n" +
                    "    <meta name=\"apple-mobile-web-app-capable\" content=\"yes\"/>\n" +
                    "    <meta name=\"renderer\" content=\"webkit\"/>\n" +
                    "    <meta name=\"x5-fullscreen\" content=\"true\"/>\n" +
                    "    <meta name=\"full-screen\" content=\"yes\"/>\n" +
                    "    <title>职位申请邮件模板</title>\n" +
                    "    <style>\n" +
                    "        @media (orientation: portrait) {\n" +
                    "            /* 竖屏 */\n" +
                    "        }\n" +
                    "        \n" +
                    "        @media (orientation: landscape) {\n" +
                    "            /* 横屏 */\n" +
                    "        }\n" +
                    "        \n" +
                    "        * {\n" +
                    "            margin: 0;\n" +
                    "            padding: 0;\n" +
                    "        }\n" +
                    "        \n" +
                    "        html {\n" +
                    "            -webkit-text-size-adjust: none;\n" +
                    "        }\n" +
                    "        \n" +
                    "        body {\n" +
                    "            font-family: Microsoft Yahei, PingFangSC-Regular, \"Helvetica Neue\", Helvetica, Hiragino Sans GB, WenQuanYi Micro Hei, sans-serif;\n" +
                    "            font-size: 16px;\n" +
                    "            color: #222;\n" +
                    "            min-height: 100%;\n" +
                    "            line-height: 1.42857143;\n" +
                    "            /*word-wrap: break-word;*/\n" +
                    "            /*word-break: break-all;*/\n" +
                    "            overflow-x: hidden;\n" +
                    "        }\n" +
                    "        \n" +
                    "        a, img, button, select, input, textarea {\n" +
                    "            outline: none;\n" +
                    "        }\n" +
                    "        \n" +
                    "        a, a:link:visited:hover:active {\n" +
                    "            color: #118BFB;\n" +
                    "            text-decoration: none;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .container {\n" +
                    "            max-width: 600px;\n" +
                    "            margin-left: auto;\n" +
                    "            margin-right: auto;\n" +
                    "            padding-left: 20px;\n" +
                    "            padding-right: 20px;\n" +
                    "            box-sizing: border-box;\n" +
                    "            box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.5);\n" +
                    "            overflow: auto;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .logo {\n" +
                    "            height: 30px;\n" +
                    "            padding-top: 28px;\n" +
                    "            padding-bottom: 26px;\n" +
                    "            text-align: center;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .logo img {\n" +
                    "            height: 100%;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .banner {\n" +
                    "            height: 225px;\n" +
                    "            line-height: 225px;\n" +
                    "            text-align: center;\n" +
                    "            margin-left: -20px;\n" +
                    "            margin-right: -20px;\n" +
                    "            background: url('http://mss-sh.sankuai.com/v1/mss_beb8568dae034a508fb2d11abbf9920e/static/1516431135383-image.png') transparent no-repeat center;\n" +
                    "            background-size: cover;\n" +
                    "            position: relative;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .banner .alt-text {\n" +
                    "            position: absolute;\n" +
                    "            left: 0;\n" +
                    "            right: 0;\n" +
                    "            z-index: -1;\n" +
                    "        }\n" +
                    "        \n" +
                    "        h1 {\n" +
                    "            font-size: 32px;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .title {\n" +
                    "            text-align: center;\n" +
                    "            margin-top: 30px;\n" +
                    "            margin-bottom: 42px;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .font-lg {\n" +
                    "            font-size: 24px;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .text-gray {\n" +
                    "            color: #aaa;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .text-secondary {\n" +
                    "            color: #aaa;\n" +
                    "            font-size: 16px;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .main {\n" +
                    "            text-align: center;\n" +
                    "        }\n" +
                    "\n" +
                    "        .main-body{\n" +
                    "            text-align: left;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .section {\n" +
                    "            margin-bottom: 32px;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .greeting {\n" +
                    "            margin-bottom: 37px;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .btn {\n" +
                    "            font-family: Microsoft Yahei, PingFangSC-Regular, \"Helvetica Neue\", Helvetica, Hiragino Sans GB, WenQuanYi Micro Hei, sans-serif;\n" +
                    "            font-size: 16px;\n" +
                    "            vertical-align: middle;\n" +
                    "            padding: 8px 15px;\n" +
                    "            text-align: center;\n" +
                    "            cursor: pointer;\n" +
                    "            box-sizing: border-box;\n" +
                    "            background-color: transparent;\n" +
                    "            border: 1px solid transparent;\n" +
                    "            -webkit-appearance: none;\n" +
                    "            border-radius: 4px;\n" +
                    "            box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.10), inset 0 0 0 0 #C8C7CC, inset 0 0 0 0 #C8C7CC;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .btn-lg {\n" +
                    "            padding: 10px 38px;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .btn-primary {\n" +
                    "            color: #fff;\n" +
                    "            background-color: #3DC4BF;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .divider {\n" +
                    "            margin: 32px -15px;\n" +
                    "            border-bottom: 1px solid #ddd;\n" +
                    "            display: block;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .wx-qr-code {\n" +
                    "            height: 147px;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .footer {\n" +
                    "            color: #fff;\n" +
                    "            font-size: 12px;\n" +
                    "            min-height: 200px;\n" +
                    "            background-color: #333;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .footer > .container {\n" +
                    "            padding: 40px;\n" +
                    "            box-shadow: none;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .footer-logo {\n" +
                    "            float: right;\n" +
                    "            height: 20px;\n" +
                    "            margin-left: 32px;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .footer-logo img {\n" +
                    "            height: 100%;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .footer-content {\n" +
                    "            line-height: 18px;\n" +
                    "            overflow: hidden;\n" +
                    "        }\n" +
                    "        \n" +
                    "        .footer-content p {\n" +
                    "            margin-bottom: 5px;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<div class=\"wrapper\">\n" +
                    "    <div class=\"container\">\n" +
                    "        <div class=\"header\">\n" +
                    "            <div class=\"logo\">\n" +
                    "                <img src=\"http://mss-sh.sankuai.com/v1/mss_beb8568dae034a508fb2d11abbf9920e/static/1516429878243-image.png\" alt=\"美团点评校园招聘\"/>\n" +
                    "            </div>\n" +
                    "            <div class=\"banner\">\n" +
                    "                <span class=\"alt-text\">无畏挑战，极速成长</span>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "        <div class=\"content\">\n" +
                    "            <h1 class=\"title\">美团点评校园招聘 职位申请</h1>\n" +
                    "            <div class=\"main\">\n" +
                    "                <div class=\"greeting font-lg\">${studentName}您好，</div>\n" +
                    "                <div class=\"main-body section font-lg\">您已成功申请美团点评校园招聘${jobName}职位，请继续完善个人简历，以便我们更全面的了解您：</div>\n" +
                    "                <div class=\"section\">\n" +
                    "                    <a class=\"btn btn-lg btn-primary\" href=\"https://campus.it.test.meituan.com/resume-edit\">立刻完善简历</a>\n" +
                    "                </div>\n" +
                    "                <div class=\"section text-secondary\">\n" +
                    "                    <p>如果以上按钮点击无跳转，请复制以下链接并粘贴在浏览器中打开：</p>\n" +
                    "                    <a href=\"https://campus.it.test.meituan.com/resume-edit\">完善简历</a>\n" +
                    "                </div>\n" +
                    "                <div class=\"divider\"></div>\n" +
                    "                <div class=\"section\">您可以通过【美团点评招聘】微信公众号查询招聘进度及更多招聘信息</div>\n" +
                    "                <div class=\"section\">\n" +
                    "                    <img class=\"wx-qr-code\" src=\"http://mss-sh.sankuai.com/v1/mss_beb8568dae034a508fb2d11abbf9920e/static/1516429885302-image.png\"\n" +
                    "                         alt=\"微信二维码：美团点评招聘\"/>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "    <div class=\"footer\">\n" +
                    "        <div class=\"container\">\n" +
                    "            <div class=\"footer-logo\">\n" +
                    "                <img src=\"http://mss-sh.sankuai.com/v1/mss_beb8568dae034a508fb2d11abbf9920e/static/1516429881776-image.png\" alt=\"美团点评校园招聘\"/>\n" +
                    "            </div>\n" +
                    "            <div class=\"footer-content\">\n" +
                    "                <p>如有任何疑问，请在【美团点评招聘】微信公众号留言或发送邮件至xiaozhao@dianping.com进行咨询。</p>\n" +
                    "                <p>为保证正常接收美团点评招聘邮件， 请添加xiaozhao@dianping.com到地址薄。</p>\n" +
                    "                <p>Copyright © 2018 美团点评招聘, All rights reserved.</p>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>";

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false);  // true表示是multipart类型
            helper.setFrom("it_hr_test@meituan.com","企业平台测试邮件");
            helper.setTo(email);
            helper.setSubject("这是一封测试邮件");
            helper.setText(content,true);
//            ClassPathResource img = new ClassPathResource("banner.jpeg");
//            helper.addInline("banner", img);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL!";
        }
        return "SUCCESS!";

    }


    @RequestMapping("/sendText")
    public String sendText(){

        try {

            String email="563508194@qq.com";
            String content="<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "    <img src=\"data:image/jpeg;base64,/9j/4AAQSkZJRgABAQEASABIAAD/2wBDAAIBAQEBAQIBAQECAgICAgQDAgICAgUEBAMEBgUGBgYFBgYGBwkIBgcJBwYGCAsICQoKCgoKBggLDAsKDAkKCgr/2wBDAQICAgICAgUDAwUKBwYHCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgoKCgr/wAARCAEAAQADAREAAhEBAxEB/8QAHgAAAQMFAQEAAAAAAAAAAAAAAgABAwQFBwgJBgr/xABKEAABAgQEAwUFBQYDBwEJAAABAgMABAURBgcSIQgTMQkiQVFhFFJxkZIKIzJCgRUzU2KhohZysRckQ7LB0fDhGCY0VFZjc4LC/8QAHQEBAAEFAQEBAAAAAAAAAAAAAAMBAgQFBgcICf/EADoRAAICAgEDAwIEAwYGAgMAAAABAgMEEQUSITEGE0EiUQcUMnEjYZEVUoGxwdEIM0JigqEWcpKywv/aAAwDAQACEQMRAD8A75Pf9oAkl/3R+BgCIL0KPqB4QA/N/wDNMALnW6f8sAPykHfff+aAFykev1QAzjYSglJN/UwBIj9yPiYAia/EPgP9YAI/vz+kAKa6/qf9IABTS3pVbTdrqG14AFKUyrOmZSom3/DgCnXMyaF6m2nb+ogCcTUu+BMLQ5cd21oASn0zqtO42/MLQAbDHs5sl1FvEaoAdxKTN6tX5hteADnLHTv4mAENmEj+SAAY/ej4mADnOg/ywAz3QfEQAUv+H5wALSErWQo/lEAByGw6CDvt4wBK4w2Um5PT3oAFbLZaHX8PnACYabAIB/rACcQhpwLSPDzvADc3/wA0wAnv+0ASS/7o/AwBG2AVG/ujxgA9I/8AFQAtI/8AFQABYuSQpPXxgBvZz7yYAZbJQkquD8IAmR+5HxMARNfiHwH+sAEf35/SAFNdf1P+kACCtMupTa0pUBsVdBABNm7V5l1Kz5IMAULk7OF/lMNKSPNSNoATszUWJkNrdSpBTclCdrwBK/MU94XWytfokwBRol6Op5CjTJkK1CxJ2BgC5rk1Kf1ahe8AJ+VKrbjx2gAktlprTfbTAAsfvR8TABznQf5YAZ7oPiIAKX/D84ABCCtRAI2T4wBGJdQevqG9oAldl1FB7ydusAA5LqUyNxuIAeXYUEm6h4QAgyUPglQtbzgCTSPX6oAjfUof8M+EAGytQa/dne8AR9/WbII/SAF97/DP0QAvvfcP0QA+t7yX9MALU95L+UAM4p3RulRHqIAkSpQasWzteAImlLC7cpW1oACenDKPs/cKXznAg6fy+pgCCpVxtuqMUyWllTCnHdDxaN+R6q8oAUzVJF1TlNlnkPKSdD/LXflHyUPCANcuKftP+GDhKlHWaljGk4grTSFFOHabXWETalJWEKQEKN7jqfhAGm+NO3q4kc1VuyGTHAXm1RmtRQxVlSzL7KwCSFghG4Um1oAxPiTj17ZysuKqWGJHHlPYHSUcwshSibk9dHlYQB5LDHbodp9kFPLnM+uEfOCryDaTqmzQ2pdtRve11N26AwBsnwY/al+GviQxQ3gfMLK+p4FmtIK5rFGI5RCQS6lA2AB6HV8AYA6Y4HzWwHmdRGMVZd44ptapk2CZSfps2l5p8A27qk7He4gC+qnSpSWhNp13sW/FJ9YAJiYfcUtp5lfcNgbdYAJGtC9XLV4+EAE+pTlvu1Cw8oAZ5RA/dq8IAJhZCL8s+MACguBw2QQdMAAFv82xvba20ASuLf0nc+u0AAVvrQNNxt1tADtqfQNwo/BMAMovKVqKSfimAF97/DP0QA8xM2/J/WADaf8AuxdJ+cAAJi6yQk/OAC5x91X1QAucfdV9UALnH3VfVAC5x91X1QALsxpTcoPXzgCQTN030Hp0vAESZkFwjQfnAEM3UJRuaQ1O2ZusBlaz+8V5CAID7LJVRw0qRCn5tYE2831b8iqAOeHavce1ewhXE8MHB7jt2l42xOiZk6riqgTSVOUCdQpOhbzZFyq17AQBqxlHwz5BszzeYnFnh6kZnY3SoOqq1QaLcypxafvj8VKOowBsBSuJidwzJIo2EaPNUKnMJCGJVuaGkJSLJt6WAH6QBNVOMTMKfpXs0vjKdpr4dBTMKmge6PDp4wBBjPikqGLMPDCWZcrNY4knHO+hybsgXBTcj0BI/WAOe3ac9l3k7jbBz+a3CrgemYVqEk4qbmpelySnXFstMKJSTqsAVAG8AYr7Ertu87OCrNik5A8Q1frFawbU5mTpdOkp2ool5bC2uZ1OzagUElOlVyPIQB9OeAswcvM1cA0jMfLTFMjWafiFgu0qv09zW1MpuQHEHxG0AekpE/PaVyM/LuFcvZBmFnZ4+8IArecfdV9UAMXtt0H6oAUxMhKb6D4eMAEzMXbuEHqfGAGRMAuE6OqbdYAjExd2xR0t4wBM5MEJI5Z3/mgAEv7Dun9DAD84+6r6oAXOPuq+qAFzj7qvqgBPmXIsdPygAmyyEAHT1PUQABMvzFW02+EAPeX/AJYAV5f+WAFeX/lgBXl/5YAFZl9O+nrAEo9mIsNNreUARJ9nDndCflAFFVmE1CYHNkgoSiubLkj94ryHlAGMeMbOGd4dOFXMDPxEqUPUPDT9RWjnaC0WwPzAG3xtAHCfBWdVOxpjnFef1dqgm6ljupJqlKm3lkuU0kWIQu3ev5wB6A5/tSb16jOal+E2t03Hr08YAJ3PuqzywpuquvsAW5nPNk+lrQAYzmw+twTk7jRLjITpIWTYK8ukARU3iCfk3VFMwpMoUkCYDxtq+FoAeWzuM/I1GRl8wnZ6UnJFxl1orOkJULKJFt4A5G8X0rTcKcUmKJPDQb9jQ8yGHmU6UoHLSdQHxvAHff7Kzx1T+eOTE9w14sq7j6MvMOyDFEXMTSnNSnHnAdCLWbMAdjGG0NtJD5usp72rreADvL/ywA15f0gB3zLnrbp4iAHZMvo209T4QAkmX5qiNP4drCAABYLndCbi19oAkcMtpN9PTaAASWNIG3SAHvL/AMsAK8v/ACwAry/8sANMS4OxUflABNMDl7KPygAfZwHFAKPygB+SfeV9MALkn3lfTAC5J95X0wAuSfeV9MAC5Lak7qOx8oAl9mATp1Hp5QBCiVSly4J6eUH2BTzCWac9Mzjk2pX3ezahsmw8ItnkVR+h+SinFSSObH2lbiKncquBipYZk6y8wMYYXqstym3lpDtko2ITsevjFylHWzKUepvRxOyszTlF5K4LmF1JbLkjRkB5DZVZw3/N5xkTtplR0xfc1tlV7sZVtZ1UqaptQmmq0t6aZdAallldj5i5jV2xtjJaMnHrsjH6ipRmt+1eXS5yuvU9LjevXLuKuLfCMzEnH3F1E11lMIFJhjPDD+NK0uhy1VUmWZaWtTyUruVo8LGJsvUp7h4IIfxV1Q8FtofFjS6jTFVOnvpfnVOqYXS1lYbQ2NuaDb8UY9al1dyRyjB9yqOetFwthuar89Wlslcu4lDQC9NwCbbfCMjW0Wu6p9tmj+bOO/8AaPmHP4uS6bTi0nYneyQPGIG0i3oskvpN6Ps4fEpUMmuP7CuXjNXdl2cZ4np0gtpt9xKXgFrVYhOx/WK9LnHsXKi1eT61Eyy1PuLU8ohSzYHoPhEdcZR8lZa0VCZYJGyj9MSFoD0sE94LPwtACflQoW1np5QATTADdgo9T4QAyZZIdUAo/h8oAD2YB2+onfygCVyXuk3WdvSAAXLgtDvHpfYQA7DAsQVePlADLlhzAnWel4Afkn3lfTAATDbxtYnw21QATbb3L2v9UACG3g4oEm49YAflu+R+qAFy3fI/VAC5bvkfqgBct3yP1QALjbxTsD1H5oAkDT+mx/5oAiQ0+HN72t70ARTEk886+h0d1aLJJN/CMOyhe51EOmrNnFD7YnWH8N5S5R0xLpSw6awh5IJspOhrqB1ian+J57GXG6UX+5yx7Mjs8s3+P6YnJCm1CoylAp81LsPTEhVUMqbbcQpQKUrPp4RLXUnakY88qbn4MocfPZJ8UvAjgteaNCoH7Qw1TZQuz07Ua2y45bmBCCUIN1E33iXIqSl2MmqbmmaVv5vUSXlzPqxFOCpJOky2lfLB/ML+hvEfRGtdSfcrZiVWx02bXdlH2fmafaI4yViSsyz1FwJL+0sTFZolQbZmfa2whQb0L3KSFbm0Wu2XV4MZv8k/bh3TOqyuxp4C6FgUYWxNjSvSU644Q29KUxguFSk2AKko84ynGKW9ly/ivuch+1m7NPODs9MeP4ilZieqGWtVfZp9EqtTqrbr7k0tkuLSWkG6QNKrG0Q+41LRR40G9mjBlQl9xEuSUtC4J6mLnSmSxm63tGwnZTYvl8JdovkriMr0Gn46lHXbA+Gry6wrbjYol/uOxpM+0HLXEZxrgSlYqZN0z8ml5JFx1v5xW7TmR2RUX2PRIafA71/0MRFhG40+DdV7eeqAFMNPFAt1/wA0AEy29o8evvQAyGng4QfLzgAA08Hbk+I/NAErrbxQdN/qgAHG3iyLeXnADy7b2ki56+cAMpp4OC/X/NAD6HfI/VADPvOE7JH6iADZdcLWwG3mIAAPuFZJAF+ptAD85Xmn5QAucrzT8oAXOV5p+UALnK80/KAGW85pskJO/S0ASB9wo1ED5QBEl9wr1FI9NoAo6rOezTCfaSQH1aGdIv3vXyiZShGBk1uKXg4hfbOnXv8AAGSdKAHNm3602m52voa6nwjFlB2zTj8FjnFNmQ+BKrYJ4G+yfwfiyTkpdqs41y/lZ5x9yWS5d1slNwpIBHXzjaTnS8dR+SLSlLsZV4XM6sv+05ySxbk1VA3N8uoS1PmwqVDdlAcwhKl6hbu9YwVNR7SL1VY/B8yfEdhVnC/EFjjDktL6RS8ZT0mwi22hEytI6ddhEKbhLqfgkWJkSR9HHBZhHL/ga7LlzEtbQJOXnMWMO+0tJStzmPSzdhdIBsSOkTxtqsTaMa7Htql0y8s1fxl2qecmS2e0jV8y5TDy8GVZyWpko/KSRmJj2p1wAXAWdI033tsYxpbj3bKw47KRuN2y/D9Ss1eA79ly0iiZl6M7N1NqYmSguakybp/EoG9r9Itg3ZLUTIjJYy6LPKPlhZS5o0si6tI5l/KMpxnjvc/kSvqnFJeTKXBHNyVF4wMuqqVuAM4nYW4Ujpa/SEmrYdMPLMScvLR9n3B7WW6hwvYDqjCypEzhxhbZUDcgg9YgrqtqbUylUnJGT0TpUOg+mJSQF2ZcUdNh+ggB3n3bCwFyOhEAGy84UWAHU+EACmZWFlRA3HlAAh1wr1K02gA1TKlAgad/MQADs0pLYFvDygB5ebuk28PSAEqZUXQALHp0gBw8q3VPygApgNg2MAGwEcu49esAAEpK1Ej+sAPy0eX90ALlo8v7oAXLR5f3QAuWjy/ugBlpbSm5Hj5wBKCnl7wBC3oDlrbwBSzs2l6ZelmW1c1hOpKlJ7t7bWi/pi4kE75wbSOMn2vnBTeLckMt69UWVrdpkvWHdbZICSUN7m0Y85TpX0mO7r5a0YfzGqU5xWdgzhvEGSc+0/OZI5StSleklOXmHHnFhSQ02gqLhsOhtGtfIWQu+uSS+77G1xabbFGTi/6Ft+zwZecRuWrFVxdXGW5STqValpx1mbl1odN5Y27q0i1r7iON9Weqc3j8iuOFOMk0961Lvs6nE4uuyLcov/IzXjjsCODrNmcxDmNiXC9f/wAQVOuuzhcRX3m2lFxzmKOkC3UmOJxfX3qW/J9q5pQ/+ujrsHguNlJOW/6nq+PHIrFWcvAk7wSYZYWhaa/KVZhxS1oQEMIKAC6kXvsNvGNuvW3KU2KCnHXb7GTl+kuGtk5NPx9/scYaZwvcU8jndQsgcY03mSklWpSph4S7xRcvJA+9Kd9r7R6dVymHkVpO2P8AVHnPJ1ZeNFuqD8/Zn0DdsjmRROF3s70Uis1iVL1VVN09IbfQ4SXJJ0j8RHlCrKX5iKrkmt/BopRuvfVamt+T5PZZoOJecSq9mR4x1zqpua6ys6oV/pMh8GakHigwPzPDEDP+hjVWJU5H0/BiyUurXwfZ3wQqZc4Q8ukhJ2wvL9fgYvlNzltkkYKHgy7LBu4Cf6xaXBLS1a6ht8YAUwG0jx6CACl9HKBT69YAEpSVm48B4wAuWjy/ugBctHl/dADuOMpSEqB6W2EAJl1vfSD18YAFTrTjgRY3t5QA/LR5f3QBHMJX4+nhABsBQasfI22gAEpcKiPL0gAtDnkPpgBaHPIfTAC0OeQ+mAFoc8h9MAA6lYSLja/uwBKlKi1Yjf4QBA2lZc2BuPSAIptbhfUhKw3p/EtzYEekQOzVyRDKuUp7Ryg+1RVOURw84ZwkiQemp+vU+qy9OMsnVZzS31A3P6RZyGVDH6epeTc8ZjTl1GtHYo8F3EhwwZBzuM5vGtFRhzMOVkqhVaEZZaptxtCFpCLLT3Vb+EeCetfxE478vlYKrl1ra320en8Tx0varfb+h0pwJSsPUPDn7Qp3KbmOWhXK1jVe2/djyXifW/H8ZXNWQk9v4NxdjSjYonp044qE0+25MpIl22tCklsAqPhvHUX+uMDOo9uuEk2Y9WFpvpLXOVugVaqlTFKmGXUsH717ZNh4RxvJ89jQvUel+DKqrtrepMwxN4To2Nq/+36zSFMPtEBuZfTy0XSdh5GMSj1FTxkvesUmn2NxZhyuj0rya5drvwh5z8b2Sj9KOM6SqQwu45WkocC0FXKlnEkJLY3O/Q7R6t6Q/Fbi6qqaHXLcpa32+WcXzPpbIttssTSWv8T56MTYaqGC6xMU6cQUmwSQUkeF/GPp7DyI53evt+55blYk8FKU++z2XB+QzxP4GeURb/EDJ/1ilkX7jizFjcpvoSPs74IVtv8AB/lspr/6Vl7/ACMV9twKTrdb0zLkulzbY9PKBYO424DYjaADfQvTcjwA2EAEwlYbF+m8ACEuFf6eUAFoc8h9MALQ55D6YAZ5lOi6rXI33gB5VhojVbx84AFTIDmpJF/nABaHPIfTADvF69ir+kAEzzS3+Lz6wBGOalZ7362gAtb3vj6YAWt73x9MALW974+mAFre98fTAArW7puVXHwgCZC1coG/W8AQtKVqvq8BBgpqgyH3HTOuBbWnuN3sQbbxcq4dpaIHKfua+DjX9qozqo+VVe4eQpaRLis1T2tsPJGlIS1sSekafl6bcmKlW96+Decbd0N/zMucIeP8L51cLmB8U5ZVWWedpOHmf2hJSswl9xa1XITZN9J9I+KvXmPyVPIZljg0lJ99dv3PUuGyk1UnIyTQ6vKLnvZZqhTDEwVd5502CDbe4jx6We/+qSOv1Rb9Tkux6Rbk1LS55s6iptnflStrp9DbxEYcPUXJ036Vvb9jD/gSi2lotmIauqWQFSM6iovm1qfKkKdt52G9hElnN5uRPrlZsthOrq02Wueo2I8bUZFOFMmaYyh/mFqbYN9vG/6xTK5PNzKlVXPqe/CNvHIxqf4nUtnleIaryuWnDhjBykNmszCMMVDnIpygotI5CzrUN7AR6R6E4vl8meNb7UmnNd9f9xzHLco7Iz1L4Z8tGY2PBjvEL9YnGVEP6S2lRHdOkDwj9CeJxvyu9r4PFOTutujHv4KfBWIqjgDE9LxhSnC1OyEwHpRwJBLax42OxiScm8t/bZqqZNXo+xvsgc3pPM7s0sosSPVtieqreAJByr8lxBWh1ST+JKfwn0iW1x32ZsMhvqNnxOPstMz5eHJ5YU4m3n6+ERGOToU+1LqmH3g6FEFIQLEA9BAEjynSkAKtcDwgA2OaW91ecAAkO6yNe+nygBit/WAV+PlAEjqXAjUle/laAIilawCsg7e7ADoSpu4SQL/ywAtK76gqx87QA9pj+L/SAFMunVt6QAbDoLQ/WAADw1Ej/WAC5yPfPzgBc5Hvn5wAucj3z84AXOR75+cAC64lSNIN/wBYAkR+5HxMAQtfiAv4CAKSpFhtcxOTM4lLcujmOhXQJA3ik71Gtx0WuHyfLb9p64xmuIPjJrOR8tNh2Uy1r00zJlMyFpAeZZUbJAGn9SYxsWTjt6Jq7FBmzn2XTDWc2bWQGZ7VPxFPSwoj1MapCkywXpSppw92POfXnoePPcTke3Z0Oa86/mdNx/LKiUOpHSyaElRynD2YdA/Y07p0qrE+rTzynZTtvU/6x8Qeu/QL9M5NNUr+vqTfjXhnYYnLtvqS7FfJZdvhn2mjYrTTpdfeM2lq6VX8d/MR4lDmFkZrxenWm+5spcmpR/SRP0bAeCquDLqlBUyzcT4UQpST1TbpuRHWcZirJiot629f6GDdyXRPWivwtgvN7N2bXJzFNqOGpZLZUKk+xrQ5bbR+oN/0j6Y9Lf8AD/dPK915q7x/u/c11vqCEF0qHf8Acr63w2YWpeWGMcD1yVYm5msYWnZFFVW0pPOU60pIQBfe1+kfQfpn0pL0pj14UrOvpe961vvs5/O5ad8vpWj5LOPjh4HCzxS4myNSeWugPso0lktkFTSV/hJJHWPX671e9HO3RU4rRiRmYEzMS6JqbB0r7wV4Riyh12NGE49Etndz7Kx2lFOpVXrPChm1ilpTNQmadTsKsT08lHLbbbdUoNpCbqG3nFXT7RcpdXc730lKHZN5M46HJN5YVLqV+Hl/lt6dIoCsk5ph8HoGUHT1226QBNMO72HSw6GACl3QWrdTcwAm3hqP+XzgCIPK5oBJtceMATPOp02Hj4mABS8iwF7becAPzke+fnADF5I6b/rAC5//ANs/VAAzCWT+cfpABsBvl3Cxfe8ARpSyVm64ALQx/EEALQx/EEALQx/EEALQx/EEAC9yG0a+aOsAGl9lMuFl0Wud4ApxPSjLaZh18BCjpSrzPlDuDVrta+NLL3gl4Xa/jvFWP5Wk1Gv0SoyuGWJh9TRnJ1uWKktNqAPeJI+cE6m/qG9HyAcQ2c+IOIbOvEmcWJFuKquKJwTE2hbxcUF6EpsVWGrZIjNUceS+gj772fS/9mzyKlcn+BU4ol5tUlM4volNnFuJZCSpSULF7336+Ma7Kx7JQl1LcTJqcupM3qxLhDA2MJEyWL6XKTjunS3OzTepSB1NviY4vlfTHpvkrIyzMaFjS7OSNxRl2QX69fyPEMZCJbmlS7ObM05JqUSiV9nTobHgkb+Eec5H4T+jK7JXV8ZXtvzr7mfDkpf3y90DJPBmFR/7wVRvEM0VakOTsuApCT0SLeAjoeF/Df0jXRt4ENp78GNZn2TlvqPeIn6bTGRLyFVAbuB7OjYD1j1L8vx+PWljxUX/ACNNkW2vun3LHjyoSMiuVAKJ5Ds0lLratg2kjdcau2mUspT12IIztb7s+d/7Upwl07LPiSPE7SKaPYsb4kUyl9MuEoWGZRBNlXurp5R0NFtPVpGQn3Wzk481LvTb08khpN9TCQNlegitm+vcSGyO9tGReFXObM/I3PzDGYeVMvNuVinz5ek5OTfLaplfLULah0Nid4t6nLyY8IuKPrm7JvjjoPGRw6U7m1piZr9GpshJ1+URMqdclZwy4U42skDvAg3+EULza1IlJlpUpJaUoCvvXED8Kh4WgCcBl5rUw6FBOxUPMQBJLJZU1qbXfrvaAGQ22FlVwBbygCMpZDtwsdeloAleDOj8Y6eEAAlLBSO+BAD6GP4ggB0IY1brBgA+XLeaYAB9CAbk/wBIANhKQ3cb3v4QAAbSVq/0AgBclHkflAC5KPI/KAFyUeR+UALko8j8oAgqLrMlK89wC2oDvCAI1BTExzZgaWVI0gHpc+kAY74g87cJ8PGDKrmDjueal6bKSDrtMLyVFExMNtKWGyEglIOm14A+antyOLfNXjIrSM28RT8zKYNnJxa8N01ifW7Jtuol0tuctC/wEkXJtveMGy2Tu6PgictTNBuFHKqezt4isIZSS8kVuYsrjMjKrSEld13F0hW1/jGxT/LrsTQj1M+tLhkwlJ8M/C9lZkdTWwqdkcMtSdT1oCFpcbJ66did4rbmuylwaJox0zJjdYdcb+9Vbbfcxz2ZWtokckiA4gLDndV4eZiaFSv1BvRC7ZDuYscM0FKAPc8VG8ZCh+Rj7ce+ysbHIdeJlKd1bdOt4jl9D2iWNan5LZieurqCNCHSFXBFj6RNVFWaX3EqIrvs1F7aThbpvFFwNPzbsgmZqOA6XVKusFhC1JV7OQCSrp06jeM6XH10JNS3sgcmj5dp2m1CXnUUybleU4shOgG+k28IpCaf0Ferq7D01+tUetNM0SZdRPtrKWHW16Vtqt+UjpFZQUX2LbIpM6wcBHGdivIGiYe4u8gKy9UlZa05iSxjgpM25LU+szz7PKLk4U7rcRcqBAO4ihYfRDwi8UWAuLLJCnZmYHnJdU1MyksutSkoleiTnHGUuOMhSgCoJKiL+NoAym5ypuRLVPUAdQvoFt/GABkWl0qR/wB5dVe5/EYAkp6hMtc7X1BgAtKA4BqPUeEATPISUEaf6QBGGUlI2PygBclHkflACKAgahf5QA8ARzGu9ybdOhgA5cqDe/r4wAALpWR/1gAvvPdHzgBfee6PnAC+890fOAF957o+cARTMkmoN+zvqITfV3TvtAFsrFalBTpicnnC2zKMLeWtCdwEJJP9BAHHDtQ+Mhzi6zWqGR+Ha45LYVy4SxilydklOMvTaW2busuhR0uN9RpAuYA4mcdXEieI/N2pHAjbLeFqW43M09phtbKf3SQv7smw3B6DeCg3YpaGjcP7NTwhS+dmfVV4icRSagzlvUaZVqcEBsoUSpd9QUCbbflsYjz42dmXLsd0qtW9FRqNemzZUw6XKa2N0hPiB7ojFqb33Ke4k9EUnXcU1Rv/AHeXQRttqMZT6JfAc9vsVLFNxq44FqlE2/8AyGM+2VFdO0kSwi5Meou4tkX0oEg2bpvuomMONtE1sTXS9Arqde9n5r8uhPwUYrG2iT0iqsimUMlVpiZdKnVkFA1WuYrKVcVtFXYmFUpySqdErWE682l2UxfIKpq0LRrASsWNgdh18YxHmw+WRuDaPmb7YrhlqPDZxsYtpchTBL0ReJHmqMoFAK0oQgnuoACesZtEoWJOPyU7rRh5fDtimey9RmhhtpT7DUomYqji30gs6jYaR1PWJ8mt162JtNnuuz84npThtz0kK7jZLL2Emph01+UmWVvNOrLSkIKmgbLIUR1BtGKWHVLs1uNbFnZv8VdOwjmPUSvKXM1M7i6Yq0247MTEkqZbV7Mwy02rShq5QNJF0iAO9OEqlR8XYVlsT4SqDr8vPMomGVLOm6VpCxt4bEQBcqR7RMyxantikqPnAEsi+lLipJk3CU3v8YAKyg6Oux84AmeUopHw33gAUlwjoPnAD/ee6PnACGu/eAH6wAUAMp5pXVX9sAOJhpKdIO3wgBg80CTq6+kAPz2/ePyMALnNnoo/IwAAmVPkplOoNjrgCObqsrJ2Q8VarXISLiAI6o4ZuS0yhVq1JO+20AaLdtf2hcxwrZPM4Dy+bYexNXqxK0meZmZZS0NyU22tClpKFApUAQQTsIA4d8U89PU+lSfAnl3UluzeC59NfxFVJt/756RmE8xaPaAfvLBdggiwgDRvNzDcjiXPB7L/AC9UtbE1NMS9LLxCVLdcSkWXbw1GLlJpjZ9F3Y/8MJyA4AsG0aiy2nFWKMNhnFKHVgtocS87o5agkHoR1vFLZO5LfwV2bx5dZBU52lU9/GUw+mYYaTy0y7wKSfG9xEKriizoTMl0bCGHsOJ/3FnVa371IP8A0i5wTCikXNdVShOgSEra1tmhF8/rr6WTRtkvCI11MrlS2KbJne9yyLxDGmMVpCUnPuy2zOFcL4jYUxWW1tE3/wDh0pHw8IrGqKeyzoRizH3D+iR505hF11dkm3tDw3FifAecXyimtDWmYxnZqepLVPptSaSJ+Sf1WCbo1eG8YrwqpFzmzQz7RXwmSHEfw70LNymSpbqWDGahUq2ZYpQFlSG0j8pK9x5xl1ap0o/Ba5b8nFnhTz8cySxo5ScZNBzCM1NIFfJYLsw2ylJty0k2vcjYiJrb5XeShPxp5DvZU4hpmNqaFKomP5RyuUpaXApSJdxYUgLAFm1WUO6YiBsvwgZp03iy4aKhwtZkTLrD7tQYVIT0gNEw2zKoStKQ6onSCUbgCxgDsZ9ne7QA50YKmMhs5Z3lY9pVYqEvRpeTZUJVdKlW0IQtalrJLtkm9tj12i5paB1Dl69ITEp+15cL5JWWxqRY6h128oolvsCsp8+3NsB1SLKIN7ItBrQH57ZdsP12igJXXmig9d+m0AAl5sJF1Hp5QA/Pb94/IwAynkKTYKPygBuYPX5QA/KR6/VAC5SPX6oAXKR6/VAC5SPX6oAXJR5n6oAo3H26WtfJQo8w6jteAJnmZRLYn5kKOwNgfOALPjjHVNwjhycxDUZptiXlpRxYW8pKRqShRAuSB1HSAPm44kOM17iIzoxJ2gGautUpU6S/hWmyUswG1pmmApppZZKinTdP4gbnygDUbEFbfwLwxy+dldWpGMsUzM3SaprJ0ez98N2QrdJsBvAEvYs8IOIuJvjJoVaW2hcrhCvUqqTi1OLGpsTSdQBCSCbJ8bQB9UOXmXNJwrWJmrSaAmWacSuVb1XIAPiLWgD1aq25UXX5xZCRLq7g022P+sAM3XlLF7wBMiqBXUi59IAherZYqCWxYoLdz3fGBRtFTOTUu6yFsr3+MCpSyOMH6cTLON6goaTZu/WAPCZ85f0uqUhvFlLB9qUpbi0lzbZO2wgPBhGcodHzBwPX8msWMqUjGsj+z3iLJ0XNz3j+Hp1tArrZ81vaZ8LM/wAK/Gdi/Ac80E0CexRONU9Ta1KUphsix1FIB69RDwUMl8NtXRxc8IWNuHutlDmKZeckpHBCkgIbRKM2UsOWupStKeqRAGvmQGOapkVxAmiOOJSulzE3KTV03HMSFNm17eIggbjU/Oys8GPE1h7jXy7mEgIpbFIfZWObvNKCHDy7hPRXW9xF8vAPqDyZzJpWcGEKfinDU027Sn5NtQIKb84ISVC6SR1Jii7IHt2GpQOFDQVcDz2hLeu4FyWw7cHe4v3otBK4w3pJudht3oAjSyjSCL/VAD8pHr9UAOhtCVXJP1QBJqZ98/VAEHIP8vzMAL2c+Q+ZgBcg/wAvzMALk/5fmYAQYPmPmYACXXMvBxLp/AqyQRa4gCApYqSlIn0EISSO8dPSAOXf2kbjlksruEubyWwJNOSmKJquUmYbk3OWpxcsp8pWsNk69Nge9a0AcWs7KROYor1A4bcNNcw06syGIJ5CQVJ5RUFubgFQPePUWEAY57VXFMvjDizqmX2XiFJoEnKSDsjKGy1B4y6QvvJve6idoA7E/Zu+GeWyH4Y6NnnWKbpqWP5R2nvoGvmN8qbWAVpUAEj1EAdRpVUyw9UJNb6EolUjlA/n+HnAaYLEy5PS63NQTyR3kqG5+ECumCJgJBAO/htAaaDTUlJ3BPygEtgu1DnqDe/xIgWS7MBS3ZdWsOD9DArtlVL1tgsq5ra72NrCA7lEywuclpyqTbqOUmXKm2r2VtAu76MFYwpq3cSTteYQW1tulyUC7gg/DxgXLwc4vtIXCZK5i5E4U4iafJansJ0Scm62tKl3UtxxobBIsP8A9rRRvsUkcuuyLxnL4F49sHYonErQ3LrndCDa9lSyx0JsesEWlF2nORn+wriTeqzbQbGKzMVtIClEkPzC1X7w9fDaAPeUOqyuZeAmMMV90OyqJdDwQDpsttAUnceoi7bYO4P2WPiqbzR4IpPJ/E1WS9WZKvVmaLZUhJDKZgBPdB1dCN7WguwOrEoy2tHMa8QfGKyAwllJe1ahvaLQSuSxKT3ht1gAEsnSLkfqYAfk/wCX5mAGMuVjSFAfrAC9gX/ETACmFv7Wv4eEAE0p4IFiflAAhT+tRNx+kAPqf95XygBanvEn5QBB7TNGcQLK0i4JtAAVI818Sy090tFZuNrgE/8ASAPmB7YTiXo/F/2qrFTkFhxmjYQNNW6p5CwFyzz1xdHQ38PnAFo7OvD9Lzx7RrHk9OIQuWkMlKhMNlZJHMZZTaxT47QBp3haTrucPHZhSVkJV55NVxfSZOeCGtVmFTLbairT0Fj1gD6jssMr6Xw/UajZSYfDaZOkzILYZJ099Ws21b+PjEU33LkjK9QnHX52XmASbrveKbRXuMZx5pxwAnvq2imyoImn/eivUBe1Pe/DqAjMPHzMOpkcvIwmXh0MOouURKmntJurwh1FenuBNzzstSlOE76Fb2g5FzTRjSve01mfW8ATpUSDaKdRVIxbxs5MN8Q/BFmbk47UWJOaqtFRLSs3NJUpDR5yFElKdyO7Fyeylh83/D1geq4K49k5Xyk+h2codbqEg1NtIOlRaS4kqCTvY2PWJEtERsX9ofwq/hTN/Kpqoj2l5/KqSdL7aSAi6zdJ9YMGs+V+MWZTBiqsittM6VlnQpQvcpAioOgX2WHNWrZV9odUsuMUVPmSTmBp91sEpQnW68yR13PWAPpklVvs1NyTYQpLCWgpG21yLmK7BMFvl3e/yigJXFv6SLn12gAEre0jcjbygB9T/vK+UALU/wC8r5QA13vM/KAHmJm2+k/OADafs2O6fnAAe0XcUdB+cAP7QPI/VACEwL9D9UAA26ENOuqFrL2uYA89mBjelYNwVP4tqqkIRLpLfMWu1tQIG8AfGzT8XOzfEZjbHk9Oa5j/ABVWGGXFKGoNqmHLAHy3gDbn7Pw5Rqx2guYGGsQTrLZnMlq02w4+dluLQhKUgedzAEHZ4cIM5hXtX61lpjChFDtAp9OqTCH5cp5azMNLSoC+x3vAHeTF7c3L4xfXNOKWtooUVkegjGm31E0Y7iX6XrJcpjEwQdkE/i9YsK6RVTs6ENyboSfvEE9YrtlekD9qA/l/qIp4GtC/aY93+6K7ZXuL9pj3f7ooWuCb20L9pbfh/rFdsdPcdNRClBJTe/8ANDbKso8Q1YiXMghJvYi1/SKb0UemeblWBLKUHW7Ffn4xXbKkGJJvLrCGC6zV835iTpmGHGkrq1eqStMtINBQ77h8ibD9YvgyOzucq8AdnZ2aGG+0BHEfLdqFlTOSdRrVTqK6E3KvBTfP5hS2V3sSnX18bRLtkZh3t5eE/iPzOzjwzm1ltl5Wsf4BouEm5Bus0mUKpNKS8rkpCr7ApKVDboRFU9sHMRbspKy72GqjJCjLbmipyVeVuFJPl8Rb9IuBuH2IeY09K9oZL19mcWpb1AEtcLG457IgD65cPTU+5LodnG3O80N1fAQBViYHN06Tt6wBM5Md09w7jzgAA+Akd09POAF7QPI/VAC9oHkfqgBe0DyP1QAlrlnB1G3pEKs18FyjsdDkulOk6f1iaK6lsr0SGCmCvYJ38LRd0vRY+wni010bB/SIXYk9FYrqY3MbS3r5Y6dIrGXUi/2++i2/tpybmDKSVP5iEkh1YV0PkYgryOu3o0SyoUY+TC3HjXEyHCziF5M17MpM2wCtJ3H3g2ia+1Uy19zByLY0S19z5BkUnHVbzGxM/gbCL1SviSoFSGHEjUeesqVuf1ijs0tm2pwFak+o93wj8QuPuELirpOclQw+/IMT7stSp9wzfLAYceb5gUU3JGlJuPSJIvqr6yPIxFS33O/2C+EvLjMzivne084eMTy1aoWM6bJUg02myOlmTEpoS6+HFEFXeQbi0YOPmO6eteDXYs/zNjj9jZasuU+oVt5+am0hiZARLTZF+aq1rD4RbZc/daNt+X6a973oJLD4ZRSqU17U3LjTNOp25QPiRCy32zD9zv4LhNzUtOy8q3IhL3sbemYKfyH1i9v+F1mfDG6q+rZFzGwN2REULupdzHyI+zJJCK0DblCK129c+kvx61fYo70IOIO3KG0STkoS0SZFCon0piLqOnKEWW2e3HZb7W/kdDraVhXKGxuTF9cuurqIZ/S9FItlFUrbz7p5bLVlpXa4VGPC9yb7FqrTZG9I/tGrS7TDGmWLv3kwBslPnaIrcz2ot68GdRge60k/JzY7bXP2vY7z6yu4NsF5tTlCwfiOYqFMzDmJN1Rl1IRpW2Zhq33neSLCMnisn8+p9taKcpxzwejvvZgXFXBVwhVbCyKDhPGdBpU5JMIYexdKUc80LTa7hHmuxv8AGNl0bno5ueW4Sa0Y+rHEPxhcCGMZFmsZsYozzyqTJlU5QapP+zU5CiOWw2UG5OgaVJ28BEjpRJXf7kd6LNxJcLfCv2l2UkzxF8FslQsP5iybzck/k7henFbsy0195NVAvr0puAolQ/ljHU25aZPKSS2Yx7AbLbEONe0E9kkMOLV+zqI65MspAPLDMw1rUbnwsYza8fqr6iimmz63cN1uWrdp+nTQekCyEtOI/CVAWIEQOPcmnDoW2XEqlUubEE+G3WKNa8kLmkEt1labcofGIpWRiWq1MQVLaAbgnytF0ZdTJY7kMFseISP0iXoZd0sIGWVsAm/laIev6tFo+ln+X5ReCISKQd3lfKKdK+xXbSEZVANi+q/jFfBY5tvyRvsFpSClwm6ovU1IvjFvYpoLKki56+cXRhF92i59okikXlx6iEVFSMW2yce6Za1vNguPU1I5rSrKbTsFn1jByrKfakq/1FI5Cf6pGEONnMLL7B2Uk9hnFsw08/PLZfEpMS5WkguAeRGxjlc/nsDin7WZZqTXbf7HXcD6d5DnqXfjV9cYvW+xo9w6dm3laKPPVOhZVUSZnJ6qzE2krpMuklDh1HvW8jHiV8vVnJXyhiWye22tS12PXcTI9OcXFTy4RSS09x33PP8AE/2ZeRmPaLN07FeXtFwynk3k5unUSXUv2kIUEAWGxub38DFKeW53h8iGNnXyUk02tt9tmDmQ4LlZOzDjFxfj6dGsvB3xxZr9jhjUZC8Ryn53K90CUkMRVuouvqS9NOh11SZdoqAKElVjbe3rHu/E83xfJWuGNPcku/ZnnPK8Hl4Nan7Sim/KOrORGdHD7xY4KRmVws4+OLaQ+ypyZcekVy6ZJlKihTqEuAG+oHp5RtLIScnL4NG3Kv6JnrWJR2pBNEW6qQaZ7spOS/46jfxc8resRzbv/R3LVCP2JkyM1TUKkHZZKB05gO7vqYghc1PobM2P6EgAz5jxi6+XTL6WQ21Sm/A3JJPj8onrfRqQhF1PfgcsK6+MSO2DLp/xJb8jckDqD84w8mUnBaZbodDIWsJJIBNvhFaLOmtRky5UOUOpIJ2U1z7dLlhs44EFQ6m8ZsfbXkirrctpIxzxCZqnK6elcH0+ypmafWwTqUkpIANwR8Y8z5zmK6s22rr0960dvx3HzeLC3p7eTjt24uFcUZEZ7ZVZ4Yjqs2iQxI/Uag+649qDKRpGwSbnr47xvfQ+TO2u1uW/Bp/UeRjQsrUzVv8A9sqoYfmBJOLDtMql3/anH3L6RukhPrtHfRxcqT6kjkLPys03FItOYPGVU61lNVaDVqey7KuzzZRMOPrUQkKGkAHwi+cLqXqRFXWl4RkDsFsPZrucXTmJsOyTzlBmMO1pldpzQjnuS5A7t+veG9o5zlObwON73z6WdZgcFl5klGqvfbZu79n94Dca8IvHTW8zM9pF2QkZ7DdRlmA+pp5KnHX0KQLIJO4B3jDxvW3B2xSV3/pkuX6V5WhP+D4/mjvdhp+TmZblUeRZZkgCWSwkJF/HbwjpaOQxcr/lS2cc6siqxxtRXCUUHBdZ2IvtGWpJrZVxTJXGEKT3HD67RbKHV8FOhfYESSSm/NVFO8WXb0hvYR/FVB2fZhy2OiUKFXDp+F4grjNWbfgtJOWr3z9UZIBCl+AHziRpFLn9HYRLhVcgdYim9GMtuS0JRKlJ1gDfaLKl3ZmQetkc+6G9J8rxkJ9MG/sXKPW9MpJurJYZF1JG22o9Y1eTyM8dp9u5JHj42PyzCGcPGnlhlumZlJKp8yqS7hQZdci4UKUFWVcgb+McfzPP2YGJPJrSbXwdBwPpOnk+QhRa5JS+UatPs5kcU2Y4xhWpYGlslxpLaZnuEXK09xZPmPhHi/qDn8vm8yNtkUmlrsewcbg0eisZ4+O+uM31Pq87/wADY2TRKtUJmVpLKJRthKG3HZZGhWpIAI2tHXcXkyxLIWru0v8AQ89zHLkZzhPsm2w8Ry9MxbTXZmm0yXnJAslKHppka+aEkHZXkfGMbm+Lx+Yvlm2vUkvC8diXi3bgxjV8bOd/aOcC+GOI/KytyVUS6iq4ekJiqsNspbAU43Lr0AqUOh26Rr/QvM34/KTUkktf6nc8pgU8jjpS+O5x24UeOHiu4C86eThOoOKlMOzMtM1SiTNXeEoWEOB0pLbawlaVC90gb3j6FxZQzcH3YPezxjm6PyvIzr+x9C3Zp9qJk12qOWyHsNTctJY6pMm0uvUanU12Wlpd95aghDa3fxiyQbgm14jwKG5yU+2jWQuejZaV9tnnJyizLKedRl8mbVfcK69fzfpGqyYKvIlOPwbCl9bSfyAZRHuxfTJ5Cbl8Gf0JIL2NF+g+Qie2TjWYs477DeyDVuP6RhRufhItUFFC9jSoWKf6Rm11Kx6ZHKTEqR7pShO9trRj3L2bulG0xo9WHsdmQMohl9Yu6Fgm9usXW5Uo9zX4y6pM1px4tnHXE29RqstS/Y62OWn8QBI9Y8D53Oss9T2VNdnLR7BxuDX/AGBCfz0ld2inABgjjk4WKnlPOSAViWXoypTCa22mQsLU4hSrOOCzWyTuCI770zmz4uFigvOvP8jzP1Lhwusrlt9tnzv5zdk3xj5NZpzOXdYwtzGETj7UityutOEtNrKQbpNh0G0eq43OydCb1vRplxcOlabM6cFf2dnjCz0xVLO5s0B2n4aeU4px6SxJLKVbl62+4onqbeEavludtjZ2UfBLXx1cGu7OtWWXBdlJwg4EZpeCKY23KS2lExVTJtImRMFIQpF20glJKdz43j5y9X+psuOJNtJ/Uey+m8CKyIJeekutToUziHDgmag65S6lJvGZQaavSVpb3SlShvY2FxHn/H+rMz3IJwj5X3+51PI8dTNSe/h/5G2vZ/5w4mzOyul1Ypl2W5tHtBWGlqVsHSBuT5R9P+juSszMycJJdo/B8/eo+Prw6VZFttvRnw8zXY9B6x6XFaWzmK2+hbDUVhJOkH4RcrX8knbYSXHSkDTF7SlEhn2HC3E/lHziCVa7EcO4CZhZUdSRGR0pQ7GQ4pR2GXd9lJ+UWEQLjpHTziBS2yRrSDadSpvp0BMX+S3pRTTagqziSQWzq28TEuOtN7LbU46Zb60mVTTXMRTrziRLILqQlWx+IjA5PIhRiWN/CJsOuV98IL5ZonxSZ81nNbNlnL6kVSZlpaQnnpZ1coVsqsdxdQNj0jwX1V6lx6ZV7m1vZ7F6W4OcqrPpT1osGW+WcnMV1aahMPzJDygTMPhZOx63vHm+Hfk5PJ79xuLf3/oeh3PFx8TUYJSX2X+pnnDtJpmHKclEuwlu1tm0geFt7R32Bixdbel5OH5Gy3JuTKlyuMoa0NHqrpptF0FOM9GO4U1rqkgTVwEBlvupJvpSLf0jNjm1V1uufkmphTZHqijwucVKlqnSXtEuj71BS53QNSdBFj5j0jjufx7q6Iyq7Pfx28m14efuXtPv2OA3bS8Jqsv86ajnUiUdlZHEEwlpCZRwBNmpdN+4kbCPZPw75StcFRjSk3Nb8/ucH6s462fIXWpLRqlwucSWbPCfnLhjPXKmsmXOFaqzUZeVcmHAzNlskhL7aFJ5qb/lMen3R/LyW/k8wyIzhFJ/J9VPZpcZlK4/OFemZuOuywxG1RpV7E7MnK8hpMy6FGyUkkkWHiY1mTX7kJaN1gzX0JmbJSyJjlONg97yjCp1RFqXyba6STCmaYpEwlwJ8OgjGtl1bMet9UyqdZSshSWwLJtsIkorcq9ktkdMD2VN90CMiq+uuW2a7vIdEqm4AR4+UarMvhPL7G4x5qOE0/5lPUiJGVqjykjuSpIuL228Ivti+lGLxa67JGrGWTiZviQxJUXgFXqDSklQvaPBOTaXrSe/757fixS9MwX/AGmfpuskOFTayC0e6QI67OtUenp7HKQx4WJ7Wyxz03hyZmudUMJUqZdufvZimIWr13IvvGsu5O2FTXW/6lsuPrl8EUzPa0BFMlmpRIFgJVHKA/QWjQZXLyctub/qXLAXwkebxvPtCmKlnW0rBIJSpFxePOfUHIVTxJLfydfxsfbsTfbseLo0ot1lyYUgEFCxYxwcMyuF8Xvw0b2bdi7fJ7LggxZJ0LPKsUqqvLYlnqOluWbbBCeYpafDoI+mfwj53Fu5q2Lk3qB5V6+4y+rjK5aX6v8AQ3BkqqpVSmMOTH7pKAlDgB1Eq9Y+mqnC+jrieRqqcY7ZWSjrMi3MSLa1qEumwK7kxjXQlGKbIpySW2VkvMhxhKwOov0jIh+hFH9S7Bl2/Uf0iOycYtCEWiNJWlzWoC3wiXrjKPYyJa6CTnDy/ti0hGebSSAP9YjrrTk9orZNxiSNIbDVvj4xdNdLKRk5IpZl1pkBbl7eIEXUbbZfOPUjzmZci9W8HTgkdk+zKuFG2xtGg9QuS46/9jM4uMY51f7mga5OnzWYuMJBtB9sk6kUaj0Co+R/XuVbW6dfzPoH0lNx9xfHYvOAmnaRVFPTLgBLhN7+kabheSn+bgupG4zq3KE9IypKVOWnZQDWTZIGx9I9Bp5jIqj0wa0cxbipy7ohdnGGkaATcna8ZlOfOc+7RSWFRbHpl/mC7VQPH5xW61yl1okrxaaYdKKersNVSQUjWDcHYn0jU5WVfnQUbPCLsOtY03KPk1N49eGqicR2VFZy/rVOU49T6ZNLpCkLLY57jJSLqAuoXttGy4Tmcrj8+mqEkoqSLs/jcfLxbLJLbaPnh4kck8V5A5nz+WmJae41+zpssApQuy+6lR0lQGr8UfSmPyb5OGlJNx+3c8W5rjIUOOos61/ZHsUY3ZxXjbDdFbfNHmsQ01NVD7KlBCQw5ax6I/pFLpTrj9TNZTN1TSXwd5KvQ2ET2tpP5idlesai+5trRuFJ2rbIJhhCrNrG9totktR7iC6JbDXT9CgPNN7Xiem1Qra2St9a2xvYj+a3zjCVs5vsYtVUetiMkm2ofHrEUq3KzqaMmcnCtxLZjZtmUwTVZ5ZAPsDpJJ8kmL8u5VwT2U4eLeTpGo+UKVvZrVGpNKul+ZbVePnXlsqa9Wzkmv1HuNGlwiiv7pmqrl7mBTCx1Ou5vG8zOQvevBztEIxT2UzE7oWErF1f5Y5y/kLXtNoylVFojn3XngQ1YfERoM7OmpdmvBkV1QS0zy89MipamljcKt0t0jy7luUvsqlFy+TZUycPBFTJBtALZbNjfp6xzjy7N7Ml5E15ZY8c4Un6BMyWM8NjS9JTrcw+bk3Q33rW8ekekfh56qyuL5OyyucVuOu+jmvU0o8jhwqs7pP4No+GrP6nZ04cClrUmeQ0VOh1lLfVZA2vH2l6M9R5HI8TROycX1b8a+55LymEsex9CejKSWxLsracQSt0WUR0jv8AK7paOYu2kXCUbCJVtPkkeMF+krFdkSFKev8A/UYuSn1LRLrQ2loH/wBYlgn2LOtvsPpHkfqiQETyVpTcqFrxSm6FktJC2t3R6UM3e3WLbbUp9BjqqVT7lLU0atDS0KWFqKe6OkX0QcJbZlwvg+yRR1NsJpkxTGToHL0qK+hjRc81PBugvlGXi2RWRCWvk0LncMsyOeuPXZN1Cnna4ogpUSAfUR8afipasCzHU++9ntnpLITqsf7FTOUWoJVzEHvb6jpPWPKsDPhVmRte9Hd/nK/b6Wi+YZnFy7XJmkLJv5WHSO9wfU2LCtpxZq8qyNj3HsXp2USpQmkPo023Tq3jb4nNUSu30s0Ua5Rs22U0/LuOp0suAb+Mb+rm6JV66WS+25TT2U6HJtlBbLoPwilGbXCW2jY29Fq+ktOIZOcqSGTLvtpcl3NZKx1/SNfk2deWrUuyZJRlV1xlS13a0al8RvZw8OfFjnTJV7PGltqdTVC4t5dXclEkqSkKPdIH5RHpfof13x/BWWu+uUurXj40cr6j9PZFlVc4yXbZvlwD8BfCZwBZW1Jvhfw85Ku4ral5jEE1+2nJ1tbrSSlCgVqPLFidh1j21XR5XjFlVdo2La/Y8dy6J4+XOMvhmdSp6TQ3NvgvhxGsckXjV0xePFxm97MvDsU4vRDU2wHEvlaWwUCwWbWvGarFkP20Zcq3OHYqZhLbbQeCwsWA7huYwcmiVNiiy2r+FDTHZl5eYY53trKDc9xawFRkY2PKEm2yym1KbetlOX2kpc7qiUI1aR1VbwHrGU7Y76PuSZEW9yMc8QuNqZRcrpiYXNttTFRlX2kU9xxIfZVo21oJuI5H1dN8TjVyn322bb0fi/2jlzjHt0pMwHw9UebS0mtTiSkOttq1KTbxMfOfIzT5yeR8dWz1CzLjVj/l2u6WjI0xOzbExNvI+8bC7gITewjMs5ij7GjslruE44ywlqYmFgFxGoC9iI5DleUpopnZKL0bGmXZFJUpqabm25WVQpRcb1ApRcWjir/UOPc9qLJZWqL7lrm6c4y9ZLCwVbkFJ3Mclk5sJb7fJl15UW/BdZORS3LBbks5c+JBEaHJ5GqNnTruyG+6MpMqmKXNzaTKka2ZhPKcSkXsk7EnyjX0zlgSc5PyaON8au8ts8pltUX8ouJH/DGGG1vS87OykstEuObygpSSVKP5Rv1j7P8Awe5ul8JgV6e3L/8Ao5rl7oXKx6+DdxU+UzIlHblbitKFgd28fYbh7qevg82yK9xWitQ08W9KlC4AuSIRsSaTEGlrYykuNdU3v7oiVpSLpPbWh20OLWDqAv5xZ7sH9KMSEGrNkns7tv3iP1ihOQPFwq0qN/W0U6IUx6o9i2E5b8joukDWQD5GHRCa6tEzipR2RzTy5UiySbnaw6QhNLyzAm/b8Hmc1q83hvA1RxKtwIEvKrdXdQFrW8THP+p7KqeDyLU9NRZ0PDVRuyK1Jb7mmOX0i7V8a4tx9NNlbNSqCZlldjYpN+h6GPz9/FvnZ2zxd2p/q+x7DxMIYqlFLWz0a5KYcWpzkr0KNxt4eEeT1clkbT6kdOrq+hdyFynzFiGmyD6CNnRy98VrrRa5Vy+SNtuqyzgW+VaOn7uOjxOdn1rdqI1ClslMy6sdD9MdDj863H/mr+qJY1VEZ5l+hG8byvmE/NiJuivXYt1efmZJhExJ0x51VyV8tJNxGfDkaZV7c1v9yKNFMslM8XivCtDzBSl2o4bfTMtXKVOKUO8fQRr58s61qM0tm4ycaOk5NNHnGO0uw/wgYkp+UuYmA6kzhybcLNQrExMtsSks03trcW4O6nfrePpX0P6msy8bEw5XRaaS12PGvUfD0qy67238vZttlbxkcNuf2GZeYyk4iMGsPKZQEyjeIJeYcupOoCyVE3Aj163Dx5fBw9U409kvJ70IqU60kzks5V2ykaZiTb7qh4bj5xpJwtqk3BF0srSemVSU1JqtIbYoky3KljvIU2fxed7RWEbsh9di2zIotqsqblI8/j3HWUOBZg1LHecmGsOtJQnUisVRtm+xPVah1AMbWqhb+rsY0rY1P6TTXji+0C8GHDHTpujYAnqdjGqJSUSk5hvFUo6ltxTSlJXpubgKAHxjI/KU+05pd0Wzuslvv5NKOz74/uKftLuJ/GtdxxWJ9vDclIyc5ItTlMaCe85y12W2kX2EePfinl24+BjuVi/U/wDI7X8Oq5wzLtx19K/zOnGEqYukUmWpTHfQpOgKT0MfOOXn2SyJPrWtncZKq/MvZdiy4W3pWSqbUqRs8251c+EanK5CzS6JIx7a6upaWyiq042lLbbuF5ucU2jSXWQbH5RbdVPKx9Wr6WV64QXlFunMQzKJtt+WmxTNDekCZtceHj8o57K4zHhL+HHsYuRkwlLtJF1RX2JOXH7ellTryhdCmzpuk9No0C4bOun0wpk/8GY8c1Rf60PS8QpaV7RieoIkpaxCW5shG/hubRjX+j+buu3HDsf/AIv/AGKvPx15sW/3LNmHm5MUeQDeW0s64t1ehUzK2eSQQfj4x2vpT8N+VzcyceS4+3oUe24yW3/Q1Gbl0RrXTYt7+5f+EbCMk3it/M3MueY9sqiGkspmDyVpWhdgbbA7AbR9aehPQ2FxWDjRhjSj0y38/ffycnnZrk5fUbPzOJMPmYXOOVeWCJA6wS+nvfAx9BOKrhrRyls2/DKqXx7QpptLzdUYCVC4++T0jA7q8uh3YSMd4bU5yhV5bUT/APMJjNUOruSSRXyzrkzaaYnEONjwTYxB0qMiHXcm1ueR+mKlRg4grJ9IwHmK5dKRGlp7GKUuOBwK6eEZ2O37BkweqyKpzDaGlOrIsgE7mKflXP5MO+rqieBz3pj+KslMRSkoo3fpLiUaU33No4r1jQ7ODyqd/wDT5Oi4OyOPfT+5rDlJizB0hT05dz8zLCblkIl5hK3bK1Ab3HhH52/in6ekp42rP73wenRzOt7R75dJbmGgiTYKkWsgpSSLeFo8e/tFUv2uneu2zJjl6+CimMNVJolaJdwjrsnpEkc1S8xJ4ZsPsUc7T5h1nkuy6ki99x4xm05HfwZ1d8d+ChXSNPVG/wAI2+Pn+3HwZMb19iJyRUlXTb4Rtq+cjBp9BJ+aSXgoZ+Tmm1pcl65pSs2VLhP4RGyp5pTin0EP51e+looZynmVfQ7LzYcdUq5bSNyfKJrOQ9zv0m4/PRcddJqn2vOW2FcW8FWP5yvYObbnW8PO8ipPaiWSVpuoC8e+fhXY7PUXHpfL/wBDnvUepcLdL+RwPys4oM/+HnErzWTeYs/T1y0yQ25IJQN0gpBGpJ8I+1Z4rk/J4h7q8NGxOAe387VDDsuKenjTxS000rSmUJlhsBYf8LwtEkeIdvbrNTOTlJlxr32jntT5yRVLSHFdiyWe1WDyXpY3Hw5URWYMsOXtt72SVKXSYKzX7Szjt4hZVQzn4oK5XEKP7qeQz4AgfhQPAn5xZbUoxTfckvu6IbMEofmJ2bVNTy+c4R+Mixv4RTqhCGtEdeYlNJr5O7f2eLA1PkOHz/GCZtDEzUqDpfcKTdQTMrtcx8x/8QOWuPw8aSjvc5f/AKnrfoq5wum2t9kdO6Y7Ky+HJBymzCZlyWbu+pr+l/KPnGWerMB2qPlbN7ltTz5fuVCZinvLTNmhB913dawrdB9Y5S3n1RrcNiUHHwzza8V1cz0zLSmIlSqUPKSGdjax6R6PTyCu4yD15imc9l5PeUdFnnWpiryD01PSKqiUvgarevp84wHkqLXYxK17sdouGa+OqDgNmWYlJdqoT6pZnly7bulaQdrfpHY+kOS/PcuqFHT0/wD0WX4bVXU2QYcyezNzilW6rmRMzlAp5X3U1KW1IJSdhfbqI+jeA4V34sbev58f4nLZsvZvaPeu5CGiUGTlMGU0zku3M6nJ+UZOhSL3UOu1o9Tpqdz6d60aaWZ1a7HuMP5ELrFLYqM1W0yyQSqTbWyT3weiTfrG2p/gQSMSy1Sky/VHJLETktTZdOIVhpwlM+fZrjT/ADeUXXX+4lpGNpaLlL5JTEqyGG8QDlAWQoMbWiBL6uoy4RXZkLeQtR9tTMt4i1AE9JeJ4z0iVySMhYckW6FJCnrqCHVi1hax6WixzUiFvZcfaE+8n6ooUAclkgfiPyiOFVO29F8Et9x0MgDZdojsVyl9PgSffsQz0g0+gIdeKQTuCn8UVlK5LsykY9XwUFQFIakXKPNS7a5dxJQtKhsU/CLXhQzIuFsOpS8lYZHs2LT00YTzW4Q8D4ufdrWCJ1uhzswpTjk1T5QFeskWNyevWOK9Ufh76c5F19eDCXTv4Ol47l7JdW57MaVDhcz5ogKaVmfiCYQjZBDKRqHQePlHifI/gtgTsslVxUe7etJHWV81xaglK1bLDNYc4ksIrJekqzUkp8HlgX8PCOP5L8FctWr2eL7a+yMqvluLkvpsTKb/AGyZjYdPMx3lYqRkgdKp6ZmTYKOwH6x5nyv4V+o8aqUoce46f8v9zYQzsWb1GZ6PD+YWAMVEcnFUs28b2l0G/SPPeS9G+pMO1xljyjpGT+Zb/Sy/vU1akkiR+7/i6Y4+7B5WmO5RaRe7ZN633KX/AA/IuKUttCXFEbHTuImx77IQUJPUi7Ti9y8lCugUqkuLqNXn0tae8hLiPKNxiRzL+rp29F12RKOmpGn3aT4czF4hspMS5HZfSk2tFdp7soiakrKKLqSQQk2B6R9d/hdxOVRk8fkyr8ae9Gv53KjLgbYqXfRzgwN9nzxfPTJnMZZs1ujqWu7qnKK0dyNz+Pzj6weXLX2PG4+73Mq4T7CHI7BxExU81hWHhutMzQ2wSSLHoqMOWdlKX0zZHTDqu7rZ7JrsgOF+ZlS1PSlKl3R0vSE3I8/xRBPKyrZpykzZRril+k8tibsLMlsRXTRMcokwRtyKIjb5qjb1ZFUv1Mxcuhyq+mPyeGnPs4tJnJnm0/P6qNpJFkt0Fq3/ADxDfkqM3pbRDj4V0rI7j8o3o7OnhtkuBvDjGVVRxq9W0Il0yqUzkuGerpXfSknztHzX+PnHZ3L8diflq3Jqcnr/AMT1r0/D8vZNeOxu1R5RTEp7ZK0wNyz6RsnZOmPinl83M4+2zFsk4uPbX2NxJxd+2y9YewvKTiHqiawWmGiFOhKAQ2D4RqOPpzuVTdO59Jr+UybIOKiUMzgHBlQn1O0+ptL75LzqGfzevrHqHG8N6ojCHVCXTo5mf5iUm2WfFtTwxhSnqp+FHGalMqsVSyAUkm9j8usdhx/oX1pzC93DxZTin31r/ckpvhTHU3oLIzIehT1SVN5g179szq+a+0J+XBU03bUlAI8E+EfVPob0Ng4GfXZlYii+jvtfOjEz+YxVTpWGV6ViKlVHCbNWqMw2acudMs20vdAWDpuPWPYo4GBR9FMEkczfmUXW9XVs9HLYlwrg1SMPytbaeE4oMNSp7obUv8w9d4nnjSh3ijFyI1qtdKL+moUTAjMvTMR1RFlOaJZMwLBCjvcW8d4g6b97MPpRW1HMnBlNl0S8ziNlRmAUo1E7f0iSKkvJVV/A1Ox9h5LDil1Jp1G3L1E2tCTXT2L+nSL5h2vyFabWuUUgpSsDum/hEalIsb7krMrqnLuS1utnCOkZGl07LSq9lR6/TFAQlt0i11QSjV3RJGXcYodBtY/OJVKLjsa6u4E20u6CtakgK+cYOVe4x8FIz6CCccpyyGnEIOokFRTuInovmopowrYdU2QzOHpaZluY1OrbSU7aEiLr8t/JmYc/y6aRZJrDbaCpKcSzKt/wm20YMeatUuhQLvbUp9RYqxhsvIKEzC3TfYEDeMqGXLIW2ieu50RPLVrLZVSYU1N4bYnmyd2JhAKfjv5RqeQ9PVZlDg5tb0bWnmrKn+kxljXhmwFW1qcksXv0OauLN0+VQLHyv6mPL+e/DLCzMiU3fJbWvCNzT6mvhFL2zxszk/nplwtUzhqfquI2E/hTPTYSD4na/wCkeOcn+B/H219P5ufn7I2kPVdu/wDlJst0/mLxVyBQZTh/k1oJtr/aZEcRf+BvHf2h0rKn/RG0o5my+Ck4LuKew5njmZSy9iTDDtFKkHUmVm9dr/Ex23F/gZxmM5ay5vf8kWZvIyilqJT0XJGtUySeZU9MTM1ty1O6dQPxj6P9N+jMbieNo6LW+hLyl8HO5/K2XY86WuzAnspZ9tsmuzz7Hnexjo8mKqkkjllWtbLQMrMMmYKW60txVzcFoRjwXVIsoj02lYnJ2Vfs6xLhwWtqLabxlRxYyjvZsowUltlUxk9NNn7qSN/RKYwpS9rujFdpWMZXVhpQUphxKAbki2wietu2GyWrMlXJNLwHXclpKdlzV6ZNuOzqbqSnlpBKh03jQc56dq5qqEJzcelt9kdHheobYye4DYPzAxpgFZkMaUhPsyAEoXMTBUCBudhHxN6//DDCfqXLl78v1P4OipzJ5EVLWtl7r3EXlrWKDOYbmMVN0iam29EqiUQq7pBufDwjlOJ9O1em1JVzcuv+RS6v3GtsxbS8G8Rtfrbkrl+3UahITTqlCbTPhJA3KdifER716YxVyltOLJuKkvJiX1Qrrb2ZSy64E8Q0lxOMsxM2sQSryiVLlXAhaUlY6Xv4E2j6G9K0f/GcOWPBuXXLff8Aocvm5MZT+kybhLharWHayK+1mNWHGeQptOrTbvCw6H1j0WrCrUVPfdnHZLc01/Mv8twyTD+WUvgtONqkh1qqGaK0pTqtqJ87W3jCycyWJeqorZFVBRWyrpfCMk1hjFM3mXV3OS8hwNOISU9wg+d/CNpHLdvZozZWylHwXbMzIJzM2pS03J4/qjQl5gOEMpFrWA8TFX2XctTXkts3wdqqMzLOv5o1kBhdyNCD/wBYx7Gtdi+UtFejhY9ml3ENZm1ghIsLoTv/AFiJVqTLJTej0uXWWy8KBxCsYTr+lwbOgb7Wi/2enwR+T2EnLpS9qbqDjit+6qJJT+nWiutFZof91XziMEaluJ/F/QxBZGxrSLIqSYwS+s6kpBESVqSjpk6lHp7iUHHjpCRtvFJx6vghuTa+kimZecIBYlGlHxKoyIe2lpl9aitdQTctOLZ5bzQBtbumLLVF/pLchJ69sTco/fSuTbIHQkbxHGqtPbRInqKQnaS26neWQD6JESx6IrWixttlLU8NMzUpyQCjvA6kWBi/rWu5YlNMtT+DW3m+Qqly5B/4hQnVFGqZPbSJozaXktE1lfMMOGYkQt4+DbqxpiGWNhyXeC/oZMLor5Le/gmeW+tU3SWEu/mbTbSn4Rjf2fx/vKftR/oiqy7VbtTei1VnKxdT2mi5Lp3sJdQETZuNi2JKuCX7IluzZz19TLTNZVOysi7JySVrS4LKeWoax8Nox4U2w0l4NXGV35rqlJ6KWUyyflE2XKh8+T1lRI6uryjZ+/VrySKy6Klb4dlB6pbT/wBonjXVFeCxXVpgv5bNDvqlEot4JSIguj9a14ILrpSf0sdnLEvJ1tNEj4phZGuUUkjBq9+M31MkYyydW4C20SUm+kkWP9ItjCKhpIy+t9Oiplct541MTD1OaSCod1JFouqp6XuSIN3Lwy6VzKKl1xDSZ6jsWBN1BpFz8xEF3E8XkNyspi2/lxRvsbkfapUXJ7KqQ4eMFeytpbwrIOnQNTjko0VJ+B0xqsn0xxFiSWND/wDFf7F75Vt/rZfaNlZT6OgS0hTm2h7yEJBFh4WEblcXxNWMlVTGM19orsYN+dbZBpTZXy2AhRlmaYccm1Ek8maUFI39PSLqceEY/VFbNRP8x1Jplzl6Q4xSlSrjI1qc1AeXpEuMp127m+xmVSgpfUVFOk5jlHnspBKSNjE9sap2ba2X5EqnFqA8jIOtJVLBRKSkjc+BjHnGWvpNXTVdGTcmKVpk1T3ryzYUlRAVqPhFqVvyZSTRNN052ZWPvFpBPeKVWtFyjPRc9jciZlWFNIQFADulStz8Yyl0aWwDTmJlPMU8wgald23lF9kq+3SyqZIx7UiY0+zoCSTuI10IWK3b8F8unXYnKnR/wx84yiMje/7QAcv+6PwMAAhQSs3v0HSAD5gvff5QAtY8z8oAWseZ+UALWPX5GAGUoEWF/lADEeUCmhFI07wKkKXZf2lSVDfbwvAEkwmUdbOtBsR4JgCNmn08J5gQdut0iKaAXs8h/D/sioH9nkP4Z+iAIJ2WphRdSN9vyiIZ/qApOXpnJ7rewv8AlEWgUm3TeYQ2jcjxSILyB3ZeRbmblBvqHRMZAJJ16VQEXQOp/LFV5AbLzTzA5Y/CnfaEk0wJk3dG3nFAHNmwG35YAF7oP0gA5f8AD84ABshKzfyHSAD1j1+RgBax6/IwAn1DlA+aYAeWII+GxgBrkOi3rAAuOAj/ANIA/9k=\"/>\n" +
                    "    <sapn>${studentName}您好,</sapn>\n" +
                    "    <span>您已申请美团点评校园招聘${jobName}职位。</span>\n" +
                    "    <span>您可以通过【美团点评招聘】微信公众号查询招聘进度及更多招聘信息。</span>\n" +
                   // "    <img src=\"http://mmbiz.qpic.cn/mmbiz_jpg/CibkGEkiadsJl8OgKPgjt62RNaE8VMDo8S3gm4V8LERsD1tdOg2UicRf55FesicJNLxrzBtZpzlUbzsGBQ12GpibIYA/0\">\n" +
                    "     <div>如有任何疑问，可发送邮件至xiaozhao@dianping.com进行咨询，谢谢</div>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>";

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);  // true表示是multipart类型
            helper.setFrom("it_hr_test@meituan.com","企业平台测试邮件");
            helper.setTo(email);
            helper.setSubject("职位申请成功通知");
            helper.setText(content,true);
            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL!";
        }
        return "SUCCESS!";

    }




}
