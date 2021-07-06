package cn.edu.guet.controller;

import cn.edu.guet.bean.editor.Travel;
import cn.edu.guet.bll.UploadImg;
import cn.edu.guet.mvc.annotaion.Controller;
import cn.edu.guet.mvc.annotaion.RequestMapping;
import org.apache.commons.fileupload.FileItem;

import java.util.*;

@Controller
public class EditorController {
    //这里是接口
    private UploadImg uploadImg;
    public void setUploadImgImpl(UploadImg uploadImg){
        this.uploadImg = uploadImg;
    }



    @RequestMapping("html/editor/upload-img.do")
    public Map uploadimg(String realPath, List<FileItem> items){
        Map<String,Object> map = new HashMap<>();
        map = uploadImg.uploadImg(realPath,items);
        return map;
    }
}
