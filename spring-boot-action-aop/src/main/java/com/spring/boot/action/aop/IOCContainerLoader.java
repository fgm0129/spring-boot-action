package com.spring.boot.action.aop;

import com.spring.boot.action.aop.proxy.hotswap.Student;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by fgm on 2018/2/23.
 *
 * 编程式的使用IoC容器
 *
 */
public class IOCContainerLoader {

    public static void main(String[] args) {
        //1、创建IoC配置文件的抽象资源，这个抽象资源包含了BeanDefinition的信息
        ClassPathResource res=new ClassPathResource("spring-context.xml");
        //2、创建一个BeanFactory，这里使用 DefaultListableBeanFactory
        DefaultListableBeanFactory factory=new DefaultListableBeanFactory();
        //3、创建一个BeanDefinition读取器，这里使用XmlBeanDefinitionReader来载入XML形式的BeanDefinition，通过一个回调配置给BeanFactory
        XmlBeanDefinitionReader reader=new XmlBeanDefinitionReader(factory);
        //4、从定义好的资源位置读取配置信息，解析过程由XmlBeanDefinitionReader来完成，此时IoC容器已经建立起来了，可以通过BeanFactory直接使用IoC容器了
        reader.loadBeanDefinitions(res);
        //5、从容器中获取需要的bean
        Student student=(Student) factory.getBean("student");
        System.out.println(student.getName());

    }

}
