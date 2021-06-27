package cn.edu.guet.mvc;

/**
 * @Author liwei
 * @Date 2020/9/5 10:57
 * @Version 1.0
 */

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * Servlet3.0�汾��֧��ע�ⷽʽʵ�ּ�����
 */
//@WebListener()
public class ContextConfigListener implements ServletContextListener {

    // Public constructor is required by servlet spec
    public ContextConfigListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
        try {
            //����Configuration��װMap
            Map<String, ControllerMapping> controllerMapping = new Configuration().config();
            /*
            request������ session���Ự application��ȫ��������
             */
            sce.getServletContext().setAttribute("cn.guet.web.controller", controllerMapping);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }
}