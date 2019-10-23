package com.demo.controller;

import com.demo.Bean.General;
import com.demo.Bean.Room;
import com.demo.Bean.User;
import com.demo.service.GeneralService;
import com.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class LoginController {

    Integer num=0;

    @Autowired
    UserService userService;

    @Autowired
    GeneralService generalService;

    //登录界面
    @GetMapping("/login")
    public String login(){

        return "html/login.html";
    }

    //进入房间获取信息--头像
    @ResponseBody
    @GetMapping("/image")
    public User getImage(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        User userInfo = userService.getUserInfo(user);
        return userInfo;
    }

    //登录验证
    @ResponseBody
    @PostMapping("/loginTest")
    public User loginTest(User user,HttpServletRequest request){

        if(userService.haveUser(user)){
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            return user;
        }
        return null;
    }

    @ResponseBody
    @GetMapping("/fangzhu")
    public Room fangzhu(HttpServletRequest request){

        HttpSession session = request.getSession();
        User user_session = (User) session.getAttribute("user");
        User user = userService.getUserInfo(user_session);
        user.setFz_flag(true);  //设置房主flag 在放入session
        session.setAttribute("user",user);

        Room room = new Room();
        room.setFangzhu(user);  //设置房主
        room.setRoom_number(++this.num);    //房间号

        //全局变量 放房间map
        ServletContext application = request.getServletContext();
        List<User> users = new ArrayList<>();
        Map<Integer,Room> map = new HashMap<>();//房间号 房间

        //放入房间
        map.put(room.getRoom_number(),room);
        map.get(room.getRoom_number()).setUsers(users);
        application.setAttribute("rooms",map);

        return room;
    }

    //快速加入 给用户设置房间号
    @ResponseBody
    @GetMapping("/join")
    public Object join(HttpServletRequest request){
        ServletContext application = request.getServletContext();
        Map<Integer,Room> map = (Map)application.getAttribute("rooms");

        Room room=null;
        //循环房间号码
        for(int i=1;i<map.size()+1;i++){
            if(map.get(i).getUsers().size()!=7){
                room = map.get(i);
                room.setRoom_number(i);
                break;
            }
        }

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        User userInfo = userService.getUserInfo(user);
        userInfo.setUserRoomNumber(room.getRoom_number());  //给用户设置房间号
        userInfo.setUserReadymsg(0);    //给用户设置0 没准备
        session.setAttribute("user",userInfo);

        //给房间添加用户
        room.getUsers().add(userInfo);
        //指定房间号 放入map
        map.put(room.getRoom_number(),room);
        application.setAttribute("rooms",map);

        return room.getRoom_number();
    }

    //房主得到房间
    @ResponseBody
    @GetMapping("/getRooms")
    public Object getRooms(Integer id,HttpServletRequest request){
        ServletContext application = request.getServletContext();
        Map<Integer,Room> map = (Map)application.getAttribute("rooms");

        Room room = map.get(id);
        return room;
    }

    //判断房主 可以开始游戏 得到每个用户的英雄池
    @ResponseBody
    @GetMapping("/getUser")
    public Object getUser(HttpServletRequest request){
        User user = (User)request.getSession().getAttribute("user");

        return user;
    }

    //把状态变准备 为1
    @ResponseBody
    @GetMapping("/setOne")
    public Object setOne(HttpServletRequest request){

        HttpSession session = request.getSession();

        User user = (User)session.getAttribute("user");
        user.setUserReadymsg(1);
        session.setAttribute("user",user);

        return user;
    }

    //把状态变不准备 为0
    @ResponseBody
    @GetMapping("/setZero")
    public Object setZero(HttpServletRequest request){

        HttpSession session = request.getSession();

        User user = (User)session.getAttribute("user");
        user.setUserReadymsg(0);
        session.setAttribute("user",user);

        return user;
    }

    //得到房间 分配英雄
    @ResponseBody
    @GetMapping("/setRoles")
    public Object setRoles(Integer id,HttpServletRequest request){
        ServletContext application = request.getServletContext();
        Map<Integer,Room> map = (Map)application.getAttribute("rooms");

        Room room = map.get(id);
        List<General> generals = generalService.selectAll();
        List<General> fanzhu_generalList = new ArrayList<>();

        Random random = new Random();
        for(int i=0;i<4;i++){   //房主设置英雄
            int i1 = random.nextInt(generals.size());//0-11
            General general = generals.get(i1);
            fanzhu_generalList.add(general);
            generals.remove(i1);    //移除该英雄
        }
        room.getFangzhu().setWujiang_list(fanzhu_generalList);

        List<General> generalList = null;
        //给房间其他用户设置英雄
        for(int i=0;i<room.getUsers().size();i++){
            generalList = new ArrayList<>();
            for(int j=0;j<4;j++){
                int i2 = random.nextInt(generals.size());//0-11
                General general = generals.get(i2);
                generalList.add(general);
                generals.remove(i2);    //移除该英雄
            }

            room.getUsers().get(i).setWujiang_list(generalList);
        }

        map.put(room.getRoom_number(),room);
        application.setAttribute("rooms",map);
        return room;
    }


}
