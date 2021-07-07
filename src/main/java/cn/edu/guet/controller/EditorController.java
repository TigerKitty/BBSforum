package cn.edu.guet.controller;

import cn.edu.guet.bll.PostBll;
import cn.edu.guet.bll.UploadImg;
import cn.edu.guet.bll.impl.PostBllImpl;
import cn.edu.guet.mvc.annotaion.Controller;
import cn.edu.guet.mvc.annotaion.RequestMapping;
import org.apache.commons.fileupload.FileItem;

import java.util.*;

/**
 * create by hzg in 2021.07.07
 * �йظ��ı��༭��д�õ��ϴ����ӣ��Լ�ͼƬ���������Ŀ�����
 */
@Controller
public class EditorController {
    //springʵ����������
    private UploadImg uploadImg;
    public void setUploadImgImpl(UploadImg uploadImg){
        this.uploadImg = uploadImg;
    }
    private PostBll postBll;
    public void setPostBllImpl(PostBll postBll){
        this.postBll = postBll;
    }

    @RequestMapping("html/editor/upload-img.do")
    public Map uploadimg(String realPath, List<FileItem> items){
        Map<String,Object> map = new HashMap<>();
        map = uploadImg.uploadImg(realPath,items);
        return map;
    }

    @RequestMapping("html/editor/publishPost.do")
    public boolean publishPost(String type, String title, String content, String time){
        return postBll.publishPost(type,title,content,time);
    }
}
