package de.dhbwka.snatchat.classes;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SnatChatRoom {

    private String roomName;
    List<SnatChatFrontend> snatChatFrontends = new ArrayList<SnatChatFrontend>();

    public SnatChatRoom(String name) {
        this.roomName = name;
    }


    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public void register(SnatChatFrontend s){
        snatChatFrontends.add(s);
        try(BufferedReader br = new BufferedReader(new FileReader(roomName +".txt"))) {
            int count = 0;
            while(br.ready() && count < 10){
                count++;
                String txt = br.readLine() + "\n";
                sendMessage(txt);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void unregister(SnatChatFrontend s){
        snatChatFrontends.remove(s);
    }

    public void sendMessage(Message msg){
        System.out.println(snatChatFrontends.size());
        for(SnatChatFrontend s: snatChatFrontends){
            s.receiveMessage(msg);
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(roomName+".txt", true))) {
            bw.write(msg.getAccount().getName() + ": " + msg.getText());
            bw.newLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String text){
        System.out.println(snatChatFrontends.size());
        for(SnatChatFrontend s: snatChatFrontends){
            s.receiveMessage(text);

        }
    }


}
