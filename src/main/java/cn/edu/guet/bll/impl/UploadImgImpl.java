package cn.edu.guet.bll.impl;

import cn.edu.guet.bean.editor.Travel;
import cn.edu.guet.bll.UploadImg;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.util.*;

public class UploadImgImpl implements UploadImg {

    @Override
    public Map uploadImg(String realPath, List<FileItem> items) {
        Map<String,Object> map = new HashMap<>();
        Travel travel = new Travel();
        Iterator<FileItem> itr = items.iterator();
        List<String> pathList = new ArrayList<>();
        if (itr.hasNext()) {//每次只上传一张图片
            FileItem item = (FileItem) itr.next();
            // 检查当前项目是普通表单项目还是上传文件。
//            if (item.isFormField()) {
//                // 如果是普通表单项目，显示表单内容。
//                String fieldName = item.getFieldName();
//                String value = item.getString();
//
//                if (fieldName.equals("id")) {
//                    travel.setId(Integer.parseInt(item.getString()));
//                } else if (fieldName.equals("title")) {
//                    travel.setTitle(item.getString());
//                }
//            } else {// 如果是上传文件，显示文件名。
            File fullFile = new File(item.getName());
            File savedFile = new File(realPath + "\\", fullFile.getName());
            try {
                item.write(savedFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("savedFile.getAbsolutePath()"+savedFile.getAbsolutePath());
            pathList.add("../upload/"+item.getName());
//            }
            System.out.println("在这里把travel对象的信息存入数据库");
        }
        map.put("errno","0");
        //这里用list纯属为了返回格式
        List<Map> list = new ArrayList<>();
        Map<String,String> remap = new HashMap<>();
        remap.put("url",pathList.get(0));
        list.add(remap);
        map.put("data",list);
//        System.out.println(map);
        return map;
    }
}