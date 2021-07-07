package cn.edu.guet.bll.impl;

import cn.edu.guet.bean.editor.Post;
import cn.edu.guet.bll.PostBll;
import cn.edu.guet.filter.SqlsessionFilter;
import cn.edu.guet.mapper.editor.PostMapper;
import cn.edu.guet.mapper.editor.TravelMapper;
import org.apache.ibatis.session.SqlSession;

/**
 * create by hzg 2021/07/07
 * 帖子的各个业务实现类
 */
public class PostBllImpl implements PostBll {

    @Override
    public boolean publishPost(String type, String title, String content,String time) {
        //生成一个帖子对象
        Post postBean = new Post();
        postBean.setPtitle(title);
        postBean.setPtime(time);
        postBean.setPtype(type);
        postBean.setPcontent(content);
        postBean.setPstatus("1");//1表示正常
        //获取当前线程中的sqlsession
        SqlSession sqlSession = SqlsessionFilter.getSqlsession();
        //生成一个代理对象
        PostMapper postMapper=sqlSession.getMapper(PostMapper.class);
        postMapper.insertPost(postBean);
        //mybatis并非自动提交事务
        sqlSession.commit();

        //前面没报错一定可以到这一步
        return true;
    }
}
