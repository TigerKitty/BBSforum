package cn.edu.guet.bll;

import cn.edu.guet.bean.editor.EditorUser;
import cn.edu.guet.bean.editor.Post;

/**
 * create by hzg 2021/07/07
 * 帖子的各个业务接口类
 */
public interface PostBll {
    /**
     * 上传帖子
     */
    boolean publishPost(String type, String title, String content,String time);
    /**
     * 通过id查询帖子
     */
    Post selectPostById(String pid);
    /**
     * 通过id查询用户
     */
    EditorUser selectUserById();
}
