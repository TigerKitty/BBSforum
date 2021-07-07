package cn.edu.guet.bll;

/**
 * create by hzg 2021/07/07
 * 帖子的各个业务接口类
 */
public interface PostBll {
    /**
     * 上传帖子
     */
    boolean publishPost(String type, String title, String content,String time);
}
