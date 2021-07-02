package cn.edu.guet.bean.editor;

public class WangEditor {
    private Integer errno; //错误代码，0 表示没有错误。
    private String[] data; //已上传的图片路径


    public WangEditor(String[] data) {
        super();
        this.errno = 0;
        this.data = data;
    }
}
