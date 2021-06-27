package cn.edu.guet.mvc.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)//将来Controller注解可以作用在<类>上面
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
}