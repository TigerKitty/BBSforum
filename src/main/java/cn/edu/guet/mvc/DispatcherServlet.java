package cn.edu.guet.mvc;

import com.google.gson.GsonBuilder;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author liwei
 * @Date 2021-06-24 19:45
 * @Version 1.0
 */
public class DispatcherServlet extends HttpServlet {

    Map<String, ControllerMapping> controllerMapping;

    @Override
    public void init(ServletConfig config) throws ServletException {
        controllerMapping = (Map<String, ControllerMapping>) config.getServletContext().getAttribute("cn.guet.web.controller");
        /*
        ��HashMap���б���
         */
        System.out.println(controllerMapping);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);// ������������Ƿ�Ϊmultipart�����ݡ�
        try {
            String uri = request.getRequestURI();
            uri = uri.substring(uri.indexOf("/", 1) + 1);
            System.out.println("��ʵ�Ŀͻ�����" + uri);
            ControllerMapping mapping = null;
            if (controllerMapping.containsKey(uri)) {
                mapping = controllerMapping.get(uri);
            }
            Class controllerMappingClass = mapping.getControllerClass();
            Method method = mapping.getHandleMethod();
            //��ø÷����еĲ�������
            Class[] parameterType = method.getParameterTypes();

            List<String> paramterList = new ArrayList<String>();//List���ص㣺������ظ�
            //����
            Parameter[] params = method.getParameters();
            for (Parameter parameter : params) {
                //��������(��ǰ�˽ӿ�һ��)
                paramterList.add(parameter.getName());
            }
            //��ȡ�Ĳ�����ֵ
            Object[] parameterValues = new Object[parameterType.length];

            //�жϱ����ͣ���������
            if (isMultipart != true) {//������multipart���ͣ���Ϊform����
                for (int i = 0; i < parameterType.length; i++) {
                    //System.out.println(parameterType[i].getTypeName());
            /*
            8�ֻ�������
             */
                    if (parameterType[i].isPrimitive()) {
                        if (parameterType[i].getTypeName().equals("int")) {
                            parameterValues[i] = Integer.parseInt(request.getParameter(paramterList.get(i)));
                        }
                /*
                 ����String����
                 */
                    } else if (ClassUtils.isAssignable(parameterType[i], String.class)) {
                        parameterValues[i] = request.getParameter(paramterList.get(i));
                    } else {//ʵ����
                        //Bean
                        Object pojo = parameterType[i].newInstance();
                        //�õ����������еĲ�����Map<������, value>
                        //��ȡ���������
                        Map<String, String[]> parameterMap = request.getParameterMap();
                        //beanutils���Զ���map���key��bean�����������з��丳ֵ,ע�����ݽӿ�����Ҫһ��
                        BeanUtils.populate(pojo, parameterMap);
                        parameterValues[i] = pojo;
                    }
                }
            } else {//����multipart����
                for(int i=0; i<parameterType.length;i++){
                    if (ClassUtils.isAssignable(parameterType[i], String.class)) {
                        parameterValues[i] = "D:\\IntelliJ IDEA 2019.2.4\\IdeaProject\\BBSforum\\src\\main\\webapp\\upload";
                    }else{//ͼƬ����
                        FileItemFactory factory = new DiskFileItemFactory();// Ϊ�����󴴽�һ��DiskFileItemFactory����ͨ��������������ִ�н��������еı���Ŀ��������һ��List�С�
                        ServletFileUpload upload = new ServletFileUpload(factory);
                        upload.setHeaderEncoding("UTF-8");
                        List<FileItem> items = null;
                        try {
                            items = upload.parseRequest(request);
                        } catch (FileUploadException e) {
                            e.printStackTrace();
                        }
                        parameterValues[i] = items;
                    }
                }
            }
            Object obj = controllerMappingClass.newInstance();
            Object returnValue = method.invoke(obj, parameterValues);//���÷����������󼴿�
            if (returnValue != null && returnValue instanceof String) { //�������ص���һ���ַ�����
                String path = returnValue.toString();
                if (((String) returnValue).startsWith("forward:")) {
                    request.getRequestDispatcher(StringUtils.substringAfter(path, "forward:")).forward(request, response);
                } else if (((String) returnValue).startsWith("redirect:")) {
                    response.sendRedirect(StringUtils.substringAfter(path, "redirect:"));
                }
            } else if (returnValue != null && !(returnValue instanceof String)) {
                response.setContentType("application/json; charset=UTF-8");
                //���ص���һ��bean�����ͻ��˷��͵���ajax���󣬲�����beanת����json
                String json = new GsonBuilder()
                        .setDateFormat("yyyy-MM-dd HH:mm:ss")
                        .setPrettyPrinting()
                        .create()
                        .toJson(returnValue);
                PrintWriter out = response.getWriter();
                out.write(json);
//                System.out.println(json);
                out.flush();
                out.close();
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}