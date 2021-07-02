package cn.edu.guet.util;

import cn.edu.guet.filter.ConnectionFilter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * 在Controller层中声明即可
 */
public class TransactionHandler implements InvocationHandler {
    private Object targetObject;//目标类
    private Object createProxyObject(Object targetObject){
        this.targetObject = targetObject;
        return Proxy.newProxyInstance(
                targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(),
                this
        );
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Connection conn = ConnectionFilter.getConn();
        Object retVal = null;
        try{
            System.out.println("Service:"+conn.hashCode());
            String methodName = method.getName();
            if(methodName.startsWith("delete") || methodName.startsWith("insert") || methodName.startsWith("update")){
                conn.setAutoCommit(false);
                retVal=method.invoke(targetObject,args);
                conn.commit();
            }else {
                retVal = method.invoke(targetObject,args);
            }
        }catch (SQLException e){
            conn.rollback();
        }
        return retVal;
    }
}
