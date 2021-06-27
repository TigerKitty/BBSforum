package cn.edu.guet.mvc;

import cn.edu.guet.mvc.annotaion.Controller;
import cn.edu.guet.mvc.annotaion.RequestMapping;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Configuration {

    public Map<String, ControllerMapping> config() throws URISyntaxException {
        Map<String, ControllerMapping> controllerMapping = new HashMap<String, ControllerMapping>();
        ResourceBundle bundle = ResourceBundle.getBundle("config");
        /*
        获取控制器包名称
        例如：cn.edu.guet.controller
         */
        String controllerPackageName = bundle.getString("controller.package");
        /*
        把控制器包转成具体路径
        例如：
        C:/Users/liwei/IdeaProjects/demo/target/demo/WEB-INF/classes/cn/edu/guet/controller/
         */
        String path = controllerPackageName.replace(".", "/");
        URI uri = Configuration.class.getResource("/" + path).toURI();
        System.out.println(uri.toString());
        File controllerDirectory = new File(uri);
        /*
        筛选出路径下所有的class文件和全类名
         */
        String[] controllerFileNames = controllerDirectory.list();

        for (String className : controllerFileNames) {
            if (className.endsWith(".class")) {
                String fullClassName = controllerPackageName + "." + StringUtils.substringBefore(className, ".class");
                try {
                    //利用反射生成对应的类的实体
                    Class controllerClass = Class.forName(fullClassName);
                    /*
                    找出哪些类上使用了Controller注解
                     */
                    if (controllerClass.isAnnotationPresent(Controller.class)) {
                        /*
                         找出哪些方法使用了RequestMapping注解
                         */
                        Method methods[] = MethodUtils.getMethodsWithAnnotation(controllerClass, RequestMapping.class);
                        for (Method method : methods) {
                            /*
                            获取到RequestMapping注解的值：值就是url，相当于原来的web.xml中的url-pattern中的内容
                             */
                            RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                            ControllerMapping mapping=new ControllerMapping(controllerClass,method);
                            System.out.print("控制器："+controllerClass.getSimpleName());
                            System.out.print("\t方法："+method.getName());
                            System.out.println("\turl：" + annotation.value());
                            controllerMapping.put(annotation.value(),mapping);
                        }
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        return controllerMapping;
    }
}
