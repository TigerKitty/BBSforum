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
        ��ȡ������������
        ���磺cn.edu.guet.controller
         */
        String controllerPackageName = bundle.getString("controller.package");
        /*
        �ѿ�������ת�ɾ���·��
        ���磺
        C:/Users/liwei/IdeaProjects/demo/target/demo/WEB-INF/classes/cn/edu/guet/controller/
         */
        String path = controllerPackageName.replace(".", "/");
        URI uri = Configuration.class.getResource("/" + path).toURI();
        System.out.println(uri.toString());
        File controllerDirectory = new File(uri);
        /*
        ɸѡ��·�������е�class�ļ���ȫ����
         */
        String[] controllerFileNames = controllerDirectory.list();

        for (String className : controllerFileNames) {
            if (className.endsWith(".class")) {
                String fullClassName = controllerPackageName + "." + StringUtils.substringBefore(className, ".class");
                try {
                    //���÷������ɶ�Ӧ�����ʵ��
                    Class controllerClass = Class.forName(fullClassName);
                    /*
                    �ҳ���Щ����ʹ����Controllerע��
                     */
                    if (controllerClass.isAnnotationPresent(Controller.class)) {
                        /*
                         �ҳ���Щ����ʹ����RequestMappingע��
                         */
                        Method methods[] = MethodUtils.getMethodsWithAnnotation(controllerClass, RequestMapping.class);
                        for (Method method : methods) {
                            /*
                            ��ȡ��RequestMappingע���ֵ��ֵ����url���൱��ԭ����web.xml�е�url-pattern�е�����
                             */
                            RequestMapping annotation = method.getAnnotation(RequestMapping.class);
                            ControllerMapping mapping=new ControllerMapping(controllerClass,method);
                            System.out.print("��������"+controllerClass.getSimpleName());
                            System.out.print("\t������"+method.getName());
                            System.out.println("\turl��" + annotation.value());
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
