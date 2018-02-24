package com.spring.boot.action.aop.proxy;

/**
 * Created by fgm on 2018/2/18.
 */
public interface CarService {

    /**
     * 启动汽车
     */
     void start();

    /**
     * 获得汽车搭载人数
     * @return
     */
     int getLoadAmount();

    /**
     * 设置驾驶员
     * @param driver
     * @return
     */
     String setDriver(String driver);

    /**
     * 搭载货物
     * @param goods
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
     void loadGoods(String goods)throws NullPointerException,IllegalArgumentException;

}
