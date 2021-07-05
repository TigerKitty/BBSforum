package cn.edu.guet.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * 饿汉模式
 * 懒汉模式
 * @Author liwei
 * @Date 2021-07-04 11:16
 * @Version 1.0
 */
public class SessionFactory {
    /*
    工厂实例化一次开销比较大（CPU、内存开销、时间）
     */
    private SqlSessionFactory sqlSessionFactory;
    private SqlSession sqlSession;
    private static SessionFactory instance=new SessionFactory();//饿汉模式

    private SessionFactory(){
        String resource = "mybatis-config.xml";

        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }
    public SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
    public void close(SqlSession sqlSession){
        sqlSession.close();
    }
    public static SessionFactory getInstance(){
        return instance;
    }
}