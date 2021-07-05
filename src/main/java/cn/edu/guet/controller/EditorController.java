package cn.edu.guet.controller;

import cn.edu.guet.bean.editor.Travel;
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
        System.out.println("�����ϴ��ļ���λ���ˣ�����������");
        Travel travel = new Travel();
        Iterator<FileItem> itr = items.iterator();
        List<String> pathList = new ArrayList<>();
        if (itr.hasNext()) {//ÿ��ֻ�ϴ�һ��ͼƬ
            FileItem item = (FileItem) itr.next();
            // ��鵱ǰ��Ŀ����ͨ����Ŀ�����ϴ��ļ���
//            if (item.isFormField()) {
//                // �������ͨ����Ŀ����ʾ�����ݡ�
//                String fieldName = item.getFieldName();
//                String value = item.getString();
//
//                if (fieldName.equals("id")) {
//                    travel.setId(Integer.parseInt(item.getString()));
//                } else if (fieldName.equals("title")) {
//                    travel.setTitle(item.getString());
//                }
//            } else {// ������ϴ��ļ�����ʾ�ļ�����
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
            System.out.println("�������travel�������Ϣ�������ݿ�");
        }
        map.put("errno","0");
        //������list����Ϊ�˷��ظ�ʽ
        List<Map> list = new ArrayList<>();
        Map<String,String> remap = new HashMap<>();
        remap.put("url",pathList.get(0));
        list.add(remap);
        map.put("data",list);
//        System.out.println(map);
        return map;
    }
}
