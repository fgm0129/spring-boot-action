package com.spring.action.web.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by fgm on 2017/4/4.
 */
@Controller
public class UploadController {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody  String upload(MultipartFile file){
        try {

            FileUtils.writeByteArrayToFile(new File("/data/upload/"+file.getOriginalFilename()),file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        return "success";
    }

    @RequestMapping(value = "/uploadStream", method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public @ResponseBody  String upload(@RequestHeader("Content-Disposition") String header, @RequestBody byte[] buffer){
        try {
            String fileName=getFileName(header);
            FileUtils.writeByteArrayToFile(new File("/data/upload/"+fileName),buffer);
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
        return "success!";
    }

    public static String getFileName(String header) {
        String fileName = null;
        String value = URLDecoder.decode(header);
        Pattern regex = Pattern.compile("(?<=filename=\").*?(?=\")");
        Matcher regexMatcher = regex.matcher(value);
        if (regexMatcher.find()) {
            fileName = regexMatcher.group();
        }
        return fileName;
    }

}
