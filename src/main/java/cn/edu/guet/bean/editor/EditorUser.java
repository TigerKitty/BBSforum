package cn.edu.guet.bean.editor;

public class EditorUser {
    private String realname;
    private String major;
    private String group;
    private String grade;
    private String college;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    @Override
    public String toString() {
        return "EditorUser{" +
                "realname='" + realname + '\'' +
                ", major='" + major + '\'' +
                ", group='" + group + '\'' +
                ", grade='" + grade + '\'' +
                ", college='" + college + '\'' +
                '}';
    }
}
