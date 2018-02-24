package com.spring.boot.action.aop;

import com.spring.boot.action.aop.proxy.BusinessService;
import com.spring.boot.action.aop.proxy.CarService;
import com.spring.boot.action.aop.proxy.hotswap.HotSwapperService;
import com.spring.boot.action.aop.proxy.hotswap.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by fgm on 2018/2/15.
 */
@RestController
@SpringBootApplication
public class Application {


    @Autowired
    private CarService carService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private HotSwapperService hotSwapperService;

    @Resource(name = "targetStudent")
    private Student student;


    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @RequestMapping("/")
    public String hello(){
        return "Hello World";
    }

    @RequestMapping("/normalAspect")
    public String normalAspect(){

        carService.start();
        System.out.println("------------------------------");
        carService.loadGoods("Miss Mary");
        System.out.println("------------------------------");


        return "Hello NormalAspect!";
    }

    @RequestMapping("/throwsAspect")
    public String throwsAspect(){

        try{
            carService.loadGoods(null);
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        System.out.println("------------------------------");
        try{
            carService.loadGoods("tiger");
        }catch(IllegalArgumentException e){
            e.printStackTrace();
        }

        return "Hello ThrowsAspect!";
    }

    @RequestMapping("/logAspect")
    public  String logAspect(){
        businessService.say();
        return "Hello LogAspect";
    }

    @RequestMapping("/logAnnotationAspect")
    public  String logAnnotationAspect(){
        businessService.hello();
        return "Hello LogAnnotationAspect";
    }

    @RequestMapping("/hotSwap")
    public  String hotSwap(){
        System.out.println("oldStudent:"+student.getName());
        hotSwapperService.swap();
        System.out.println("newStudent:"+student.getName());
        return "Hello HotSwap";
    }



}
