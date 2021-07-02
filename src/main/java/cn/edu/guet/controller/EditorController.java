package cn.edu.guet.controller;

import cn.edu.guet.bean.editor.Travel;
import cn.edu.guet.mvc.annotaion.Controller;
import cn.edu.guet.mvc.annotaion.RequestMapping;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
public class EditorController {
    @RequestMapping("html/upload-img.do")
    public Map uploadimg(List<FileItem> items){
        Map map = new HashMap();
        System.out.println("到了上传文件的位置了！！！！！！");
        String realPath = ("E:/upload");
        Travel travel = new Travel();
        Iterator<FileItem> itr = items.iterator();

        while (itr.hasNext()) {
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
                System.out.println("upload/pic/"+item.getName());
                travel.setPic("upload/" + item.getName());
//            }
            System.out.println("在这里把travel对象的信息存入数据库");
        }
        // 把travel对象的信息存入数据库
//            try {
//                request.getRequestDispatcher("index.jsp").forward(request, response);
//            } catch (ServletException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        return map;
    }
}
