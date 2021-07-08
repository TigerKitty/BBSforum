package cn.edu.guet.bll.impl;

import cn.edu.guet.bean.editor.EditorUser;
import cn.edu.guet.bean.editor.Post;
import cn.edu.guet.bll.PostBll;
import cn.edu.guet.filter.SqlsessionFilter;
import cn.edu.guet.mapper.editor.PostMapper;
import org.apache.ibatis.session.SqlSession;

/**
 * create by hzg 2021/07/07
 * 帖子的各个业务实现类
 */
public class PostBllImpl implements PostBll {
    Post postBean;
    public void setpostBean(Post postBean){ this.postBean = postBean; }

    @Override
    public boolean publishPost(String type, String title, String content,String time) {
        postBean.setPtitle(title);
        postBean.setPtime(time);
        postBean.setPtype(type);
        postBean.setPcontent(content);
        postBean.setPstatus("1");//1表示正常
        postBean.setUserid("1");//获取Session中的用户对象
        postBean.setUpstatus("1");//1表示是发帖

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

    @Override
    public Post selectPostById(String pid) {
        SqlSession sqlSession = SqlsessionFilter.getSqlsession();
        PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
        postBean = postMapper.selectPostById(pid);
        return postBean;
    }

    @Override
    public EditorUser selectUserById() {
        String userid = "1";
        SqlSession sqlSession = SqlsessionFilter.getSqlsession();
        PostMapper postMapper = sqlSession.getMapper(PostMapper.class);
        EditorUser editorUser = postMapper.selectUserById(userid);
        return editorUser;
    }
}
