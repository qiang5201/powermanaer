package com.demo.controller;

import com.demo.Bean.Room;
import com.demo.Bean.User;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ServerEndpoint("/userReady/{username}")
@Component
public class readyController {

    static List<Session> sessionList = new ArrayList<>();
    static Map<String,Session> map = new HashMap<>();
    static Map<Integer,Room> roomMap = new HashMap<>();

    @OnOpen
    public void onOpen(@PathParam("username")String username,Session session){

        //进来一个用户 房间集合
        System.out.println(username);
        map.put(username,session);
        System.out.println(map);
    }

    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("客户端消息"+message);

        if(message.startsWith("开始游戏")){

            String[] split = message.split(":");
            Room room = new Room();
            List<User> userList = new ArrayList<>();
            room.setUsers(userList);
            room.setRoom_number(Integer.valueOf(split[1]));//设置房间号
            User user = null;

            for(int i=2;i<split.length;i++){    //遍历一个房间全部用户 发送开始
                System.out.println(split[i]);
                user = new User();
                user.setUsername(split[i]);
                room.getUsers().add(user);
                roomMap.put(room.getRoom_number(),room);

                try {
                    map.get(split[i]).getBasicRemote().sendText("开始游戏");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else if(message.startsWith("选将信息")){
            String[] split = message.split("`");

            Room room = roomMap.get(Integer.valueOf(split[1]));
            System.out.println(room);

            //把自己的武将信息发给 房间的其他用户
            for(int j=0;j<room.getUsers().size();j++){
                String username = room.getUsers().get(j).getUsername();
                try {
                    map.get(username).getBasicRemote().sendText(split[3]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

    }

    @OnClose
    public void onClose(Session session){

        //用户退出
        System.out.println(session.getId()+"退出");
    }
}
