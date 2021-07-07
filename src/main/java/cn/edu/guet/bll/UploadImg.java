package cn.edu.guet.bll;

import org.apache.commons.fileupload.FileItem;

import java.util.List;
import java.util.Map;

/**
 * create by hzg 2021/07/05
 * 图片的业务接口类
 */
public interface UploadImg {
    Map uploadImg(String realPath, List<FileItem> items);
}
