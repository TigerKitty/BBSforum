package cn.edu.guet.bll.impl;

import cn.edu.guet.bean.editor.Travel;
import cn.edu.guet.bll.UploadImg;
import cn.edu.guet.filter.SqlsessionFilter;
import cn.edu.guet.mapper.editor.TravelMapper;
import org.apache.commons.fileupload.FileItem;
import org.apache.ibatis.session.SqlSession;

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
            File fullFile = new File(item.getName());
            File savedFile = new File(realPath + "\\", fullFile.getName());
            try {
                item.write(savedFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("savedFile.getAbsolutePath()"+savedFile.getAbsolutePath());
            pathList.add("../../upload/"+item.getName());

            //测试mybatis事务处理
            travel.setTravelid("002");
            travel.setTravelurl("../../upload/"+item.getName());
            travel.setTravelalt("一张图片");
            travel.setTravelhref("www.baidu.com");
            //获取当前线程中的sqlsession
            SqlSession sqlSession = SqlsessionFilter.getSqlsession();
            //生成一个代理对象
            TravelMapper travelMapper=sqlSession.getMapper(TravelMapper.class);
            travelMapper.insertTravel(travel);
            //mybatis并非自动提交事务
            sqlSession.commit();
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