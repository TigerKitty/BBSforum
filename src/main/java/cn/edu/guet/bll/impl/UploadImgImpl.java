package cn.edu.guet.bll.impl;

import cn.edu.guet.bll.UploadImg;
import cn.edu.guet.filter.SqlsessionFilter;
import org.apache.commons.fileupload.FileItem;
import org.apache.ibatis.session.SqlSession;

import java.io.File;
import java.util.*;
/**
 * create by hzg 2021/07/05
 * 图片的业务实现类
 */
public class UploadImgImpl implements UploadImg {

    @Override
    public Map uploadImg(String realPath, List<FileItem> items) {
        Map<String,Object> map = new HashMap<>();
        Iterator<FileItem> itr = items.iterator();
        List<String> pathList = new ArrayList<>();
        if (itr.hasNext()) {//每次只上传一张图片
            FileItem item = (FileItem) itr.next();
            File fullFile = new File(item.getName());
            File savedFile = new File(realPath + "\\", fullFile.getName());
            try {
                item.write(savedFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
//            System.out.println(savedFile.getAbsolutePath());
            pathList.add("../../upload/"+item.getName());
        }
        map.put("errno","0");
        //这里用list纯属为了返回格式
        List<Map> list = new ArrayList<>();
        Map<String,String> remap = new HashMap<>();
        remap.put("url",pathList.get(0));
        list.add(remap);
        map.put("data",list);
        return map;
    }
}