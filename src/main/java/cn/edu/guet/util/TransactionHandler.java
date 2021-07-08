package cn.edu.guet.util;

import cn.edu.guet.filter.SqlsessionFilter;
import org.apache.ibatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.SQLException;

/**
 * 动态代理实现事务处理
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
        SqlSession sqlSession = SqlsessionFilter.getSqlsession();
        Object retVal = null;
        try{
            System.out.println("Service:"+sqlSession.hashCode());
            String methodName = method.getName();
            if(methodName.startsWith("delete") || methodName.startsWith("insert") || methodName.startsWith("update")){
                sqlSession.getConnection().setAutoCommit(false);
                retVal=method.invoke(targetObject,args);
                sqlSession.getConnection().commit();
            }else {
                retVal = method.invoke(targetObject,args);
            }
        }catch (SQLException e){
            sqlSession.getConnection().rollback();
        }
        return retVal;
    }
}
