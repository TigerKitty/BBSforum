package cn.edu.guet.bean.editor;

public class Post {
    private String pid;
    private String ptitle;
    private String ptime;
    private String ptype;
    private String pcontent;
    private String pstatus;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPtitle() {
        return ptitle;
    }

    public void setPtitle(String ptitle) {
        this.ptitle = ptitle;
    }

    public String getPtime() {
        return ptime;
    }

    public void setPtime(String ptime) {
        this.ptime = ptime;
    }

    public String getPtype() {
        return ptype;
    }

    public void setPtype(String ptype) {
        this.ptype = ptype;
    }

    public String getPcontent() {
        return pcontent;
    }

    public void setPcontent(String pcontent) {
        this.pcontent = pcontent;
    }

    public String getPstatus() {
        return pstatus;
    }

    public void setPstatus(String pstatus) {
        this.pstatus = pstatus;
    }

    @Override
    public String toString() {
        return "Post{" +
                "pid='" + pid + '\'' +
                ", ptitle='" + ptitle + '\'' +
                ", ptime='" + ptime + '\'' +
                ", ptype='" + ptype + '\'' +
                ", pcontent='" + pcontent + '\'' +
                ", pstatus='" + pstatus + '\'' +
                '}';
    }
}
