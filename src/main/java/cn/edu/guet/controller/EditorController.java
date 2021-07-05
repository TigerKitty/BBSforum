package cn.edu.guet.controller;

import cn.edu.guet.bean.editor.Travel;
import cn.edu.guet.bll.UploadImg;
import cn.edu.guet.bll.impl.UploadImgImpl;
import cn.edu.guet.mvc.annotaion.Controller;
import cn.edu.guet.mvc.annotaion.RequestMapping;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.util.*;

@Controller
public class EditorController {
    @RequestMapping("html/upload-img.do")
    public Map uploadimg(String realPath, List<FileItem> items){
        Map<String,Object> map = new HashMap<>();
        UploadImg ui = new UploadImgImpl();
        map = ui.uploadImg(realPath,items);
        return map;
    }
}
