package com.spring.action.web.model;

import lombok.Data;

/**
 * Created by fgm on 2017/4/3.
 */
@Data
public class DemoObj {

    private Long id;

    private String name;

    public DemoObj(){

    }
    public DemoObj(Long id,String name){
        this.id=id;
        this.name=name;

    }



}
