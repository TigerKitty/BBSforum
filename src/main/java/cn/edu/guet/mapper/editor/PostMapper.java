package cn.edu.guet.mapper.editor;

import cn.edu.guet.bean.editor.EditorUser;
import cn.edu.guet.bean.editor.Post;

public interface PostMapper {
    void insertPost(Post post);
    Post selectPostById(String pid);
    EditorUser selectUserById(String userid);
}
