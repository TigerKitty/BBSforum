package cn.edu.guet;

import cn.edu.guet.bean.editor.Travel;
import cn.edu.guet.filter.SqlsessionFilter;
import cn.edu.guet.mapper.editor.TravelMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;

import java.io.InputStream;
import java.util.List;

public class Test {
        private SqlSessionFactory sqlSessionFactory;
    /*
    相当于JDBC中的Connection
     */
        private SqlSession sqlSession;

        @Before
        public void init() throws ClassNotFoundException {
//            sqlSession = SessionFactory.getInstance().getSqlSession();
            InputStream in=Class.forName("cn.edu.guet.Test").getResourceAsStream("/mybatis-config.xml");
            sqlSessionFactory= new SqlSessionFactoryBuilder().build(in);
            sqlSession=sqlSessionFactory.openSession();
    }
        @org.junit.Test
        public void testGetAllTravel(){
        TravelMapper travelMapper=sqlSession.getMapper(TravelMapper.class);//生成一个代理对象
        List<Travel> list=travelMapper.getAllTravel();
        for (Travel t:list){
            System.out.println(t);
        }
    }

//    @org.junit.Test
//    public void insertTravel(){
//            Travel travel = new Travel();
//        //测试mybatis事务处理
//        travel.setTravelid("001");
//        travel.setTravelurl("../../upload/");
//        travel.setTravelalt("一张图片");
//        travel.setTravelhref("www.baidu.com");
//        //获取当前线程中的sqlsession
//        SqlSession sqlSession = SqlsessionFilter.getSqlsession();
//        //生成一个代理对象
//        TravelMapper travelMapper=sqlSession.getMapper(TravelMapper.class);
//        travelMapper.insertTravel(travel);
//        //mybatis并非自动提交事务
//        sqlSession.commit();
//    }
        @After
        public void destroy(){
        sqlSession.close();
    }

}
