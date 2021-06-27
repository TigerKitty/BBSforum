package cn.edu.guet.controller;

import cn.edu.guet.bean.Users;
import cn.edu.guet.mvc.annotaion.Controller;
import cn.edu.guet.mvc.annotaion.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {

    @RequestMapping("user/addUser.do")
    public String addUser(Users user){
        System.out.println("ÃÌº””√ªß");
        return "forward:viewUser.jsp";
    }
    @RequestMapping("user/viewUser.do")
    public Users viewUser(){
        Users users=new Users();
        return users;
    }
}