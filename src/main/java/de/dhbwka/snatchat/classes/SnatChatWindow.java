package de.dhbwka.snatchat.classes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnatChatWindow extends JFrame implements SnatChatFrontend {

    private SnatChatRoom snatRoom;
    private Account account;

    private String[] radioBtnName = {State.AVAILABLE.label, State.AWAY.label, State.DND.label};
    private ChatMessagesComponent chatMessagesComponent = new ChatMessagesComponent();

    public SnatChatWindow(SnatChatRoom room, Account acc) {
        this.snatRoom = room;
        this.account = acc;

        JPanel namePl = new JPanel(new FlowLayout());
        JLabel nameLb = new JLabel(account.getName());
        nameLb.setForeground(account.getColor());
        namePl.add(nameLb);
        this.add(namePl, BorderLayout.NORTH);

        this.add(chatMessagesComponent, BorderLayout.CENTER);

        JPanel btnPl = new JPanel(new BorderLayout());
        JPanel radioBtnPl = new JPanel(new FlowLayout());
        ButtonGroup group = new ButtonGroup();
        for (int i = 0; i < 3; i++) {
            JRadioButton radioBtn = new JRadioButton(radioBtnName[i]);
            if(i == 0){
                radioBtn.setSelected(true);
            }
            radioBtnPl.add(radioBtn);
            group.add(radioBtn);
            radioBtn.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (radioBtn.getText()) {
                        case "Available":
                            account.setState(State.AVAILABLE);
                            snatRoom.sendMessage("State of user" + account.getName() + " is now " + account.getState().label);
                            break;
                        case "Away":
                            account.setState(State.AWAY);
                            snatRoom.sendMessage("State of user" + account.getName() + " is now " + account.getState().label);
                            break;
                        case "Do not disturb":
                            account.setState(State.DND);
                            snatRoom.sendMessage("State of user" + account.getName() + " is now " + account.getState().label);
                            break;
                    }
                }
            });
        }
        btnPl.add(radioBtnPl, BorderLayout.NORTH);
        JPanel textPl = new JPanel(new FlowLayout());
        JTextField txtField = new JTextField(30);
        txtField.addActionListener(new ActionListener() {
           public void actionPerformed(ActionEvent e) {
               if(txtField.getText().isEmpty()){
                   JOptionPane.showMessageDialog(null, account.getName() + " enter text to chat");
               }else{
                   Message message = new Message(txtField.getText() , account);
                   snatRoom.sendMessage(message);
                   txtField.setText("");
               }
           }
        });
        textPl.add(txtField);
        JButton sendBtn = new JButton("Send");
        sendBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, account.getName() + " enter text to chat");
                }else{
                    Message message = new Message(txtField.getText() , account);
                    snatRoom.sendMessage(message);
                    txtField.setText("");
                }
            }
        });
        textPl.add(sendBtn);
        btnPl.add(textPl);
        this.add(btnPl, BorderLayout.SOUTH);

        this.setTitle(account.getName() + " (" + snatRoom.getRoomName() + ") ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(500, 200);
        this.setVisible(true);
    }

    @Override
    public void receiveMessage(Message msg) {
        JLabel messageLb = new JLabel();
        messageLb.setText(msg.getAccount().getName() + ": " + msg.getText() + " [30]");
        messageLb.setForeground(msg.getAccount().getColor());
        chatMessagesComponent.add(messageLb);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 10;
                while(true){
                    count--;
                    messageLb.setText(msg.getAccount().getName() + ": " + msg.getText() + " [" + count + "]");
                    messageLb.revalidate();
                    if(count == 0){
                        chatMessagesComponent.remove(messageLb);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public void receiveMessage(String text) {
        JLabel txtLb = new JLabel();
        txtLb.setText(text);
        txtLb.setForeground(Color.gray);
        chatMessagesComponent.add(txtLb);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int count = 10;
                while(true){
                    count--;
                    txtLb.setText(text + " [" + count + "]");
                    txtLb.revalidate();
                    if(count == 0){
                        chatMessagesComponent.remove(txtLb);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public Account getAccount() {
        return null;
    }

    public SnatChatRoom getSnatRoom() {
        return snatRoom;
    }

    public void setSnatRoom(SnatChatRoom snatRoom) {
        this.snatRoom = snatRoom;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String[] getRadioBtnName() {
        return radioBtnName;
    }

    public void setRadioBtnName(String[] radioBtnName) {
        this.radioBtnName = radioBtnName;
    }
}
