package com.demo.controller;

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

@ServerEndpoint("/echo/{username}")
@Component
public class EchoSocket {



    static Map<String,Session> map = new HashMap<>();


    static List<Integer> ids = new ArrayList<>();
    static List<Session> sessions = new ArrayList<>();

    Session session;

    @OnOpen
    public void onOpen(@PathParam(value="username")String username,Session session){

        System.out.println(":"+username);
        this.map.put(username,session);

    }

    @OnMessage
    public void onMessage(String message, Session session){
        System.out.println("客户端消息"+message);


            try {
                map.get("456").getBasicRemote().sendText("开始游戏");
            } catch (IOException e) {
                e.printStackTrace();
            }

        /*for(Session session1:sessions){
            System.out.println(session1);
            try {
                session1.getBasicRemote().sendText("开始游戏");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

    }

    @OnClose
    public void onClose(){


        this.sessions.remove(this);
        this.ids.remove(this.session.getId());
        System.out.println("退出");
    }
}
