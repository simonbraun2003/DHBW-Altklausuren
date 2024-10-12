package de.dhbwka.coronawarn.classes;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CoronaWarnTerm extends JFrame implements CoronaWarnClient {

    private JPhone jPhone;
    private Token currentToken;
    private List<Token> allTokens = new ArrayList<>();
    private List<Token> nearTokens = new ArrayList<>();
    private WarnStatus warnStatus;

    private CoronaWarnClient coronaWarnClient = this;

    private JLabel dangerLb;
    private JLabel countLb;
    private Thread thread;

    public CoronaWarnTerm(JPhone phone) {
        this.warnStatus = WarnStatus.UNKNOWN;
        this.jPhone = phone;
        allTokens = CoronaWarn.loadTokens(jPhone);

        currentToken = new Token();
        CoronaWarn.saveToken(this.jPhone, currentToken);
        CoronaWarnAPI.sendToken(this);

        dangerLb = new JLabel();
        dangerLb.setText(warnStatus.text);
        dangerLb.setHorizontalAlignment(JLabel.CENTER);
        dangerLb.setBackground(warnStatus.color);
        dangerLb.setPreferredSize(new Dimension(0, 100));
        dangerLb.setOpaque(true);
        this.add(dangerLb, BorderLayout.NORTH);

        JPanel btnPl = new JPanel(new GridLayout(4,1,5,5));
        JButton newTokenBtn = new JButton("New Token");
        newTokenBtn.addActionListener(e -> {
            allTokens.add(currentToken);
            currentToken = new Token();
            CoronaWarnAPI.sendToken(this);
            countLb.setToolTipText(currentToken.toString());
            countLb.revalidate();
        });
        JButton checkBtn = new JButton("Check for infections");
        checkBtn.addActionListener(e -> {
            if(CoronaWarnAPI.checkInfection(this)){
                this.warnStatus = WarnStatus.ALARM;
            }else {
                this.warnStatus = WarnStatus.OK;
            }
            this.dangerLb.setText(warnStatus.text);
            this.dangerLb.setBackground(warnStatus.color);
            dangerLb.revalidate();
        });
        JButton clearBtn = new JButton("Clear Tokens");
        clearBtn.addActionListener(e -> {
            currentToken = new Token();
            allTokens.clear();
            nearTokens.clear();
            CoronaWarn.clearTokenStore(this.jPhone);
            allTokens.add(currentToken);
            currentToken = new Token();
            CoronaWarnAPI.sendToken(this);
            countLb.setToolTipText(currentToken.toString());
            countLb.revalidate();
        });
        JButton reportBtn = new JButton("Report infection");
        reportBtn.addActionListener(e -> {
           CoronaWarnAPI.reportInfection(this);
           newTokenBtn.setEnabled(false);
           checkBtn.setEnabled(false);
           clearBtn.setEnabled(false);
           reportBtn.setEnabled(false);
           this.warnStatus = WarnStatus.INFECTED;
           this.dangerLb.setText(warnStatus.text);
           this.dangerLb.setBackground(warnStatus.color);
           dangerLb.revalidate();
           try{
               this.thread.interrupt();
           }catch(Exception ex){
               System.out.println("Thread gestoppt");
           }

        });
        btnPl.add(newTokenBtn);
        btnPl.add(checkBtn);
        btnPl.add(clearBtn);
        btnPl.add(reportBtn);
        this.add(btnPl, BorderLayout.CENTER);

        countLb = new JLabel("Seen Tokens: " + nearTokens.size());
        countLb.setToolTipText(currentToken.toString());
        countLb.setHorizontalAlignment(JLabel.CENTER);
        this.add(countLb, BorderLayout.SOUTH);

        this.setTitle(this.jPhone.getOwner());
        //this.setSize(200, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.pack();

        thread = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    allTokens.add(currentToken);
                    currentToken = new Token();
                    CoronaWarnAPI.sendToken(coronaWarnClient);
                    countLb.setToolTipText(currentToken.toString());
                    countLb.revalidate();
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        System.out.println("Thread gestoppt");
                    }
                }
            }
        });
        thread.start();


        this.setVisible(true);
    }

    @Override
    public Token getCurrentToken() {
        return this.currentToken;
    }

    @Override
    public List<Token> getAllTokens() {
        return this.allTokens;
    }

    @Override
    public List<Token> getAllSeenTokens() {
        return this.nearTokens;
    }

    @Override
    public void tokenReceived(Token token) {
        nearTokens.add(token);
    }

    public JPhone getjPhone() {
        return jPhone;
    }

    public void setjPhone(JPhone jPhone) {
        this.jPhone = jPhone;
    }

    public void setCurrentToken(Token currentToken) {
        this.currentToken = currentToken;
    }

    public void setAllTokens(List<Token> allTokens) {
        this.allTokens = allTokens;
    }

    public void setAllSeenTokens(List<Token> allSeenTokens) {
        this.nearTokens = allSeenTokens;
    }

    public List<Token> getNearTokens() {
        return nearTokens;
    }

    public void setNearTokens(List<Token> nearTokens) {
        this.nearTokens = nearTokens;
    }

    public WarnStatus getWarnStatus() {
        return warnStatus;
    }

    public void setWarnStatus(WarnStatus warnStatus) {
        this.warnStatus = warnStatus;
    }
}
