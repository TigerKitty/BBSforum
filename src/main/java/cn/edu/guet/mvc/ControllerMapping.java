package cn.edu.guet.mvc;

import java.lang.reflect.Method;


/**
 * Author liwei
 * Date 2018/8/30 10:35
 * Version 1.0
 *
 * ������Ԫ������Ϣ�࣬��װ�˿�������������Ϣ:
 * 1. ��������ʵ��
 * 2. ��������ķ�������
 */

public class ControllerMapping {
    // ҵ���������ʵ��,  UserController, BookController ......
    private Class<?> controllerClass;

    // ҵ���������ʵ����Ŀ�귽��������ע��@RequestMapping�ķ���ʵ��
    private Method handleMethod;

    public ControllerMapping() {
    }

    public ControllerMapping(Class<?> controllerClass, Method handleMethod) {
        this.controllerClass = controllerClass;
        this.handleMethod = handleMethod;
    }

    public Class<?> getControllerClass() {
        return controllerClass;
    }

    public void setControllerClass(Class<?> controllerClass) {
        this.controllerClass = controllerClass;
    }

    public Method getHandleMethod() {
        return handleMethod;
    }

    public void setHandleMethod(Method handleMethod) {
        this.handleMethod = handleMethod;
    }

    @Override
    public String toString() {
        //UserController.login
        return "��������: " + controllerClass.getSimpleName() + "." + handleMethod.getName();
    }
}