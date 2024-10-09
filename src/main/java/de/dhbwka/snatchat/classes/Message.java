package de.dhbwka.snatchat.classes;

public class Message {
    private String text;
    private Account account;

    public Message(String txt, Account acc) {
        this.text = txt;
        this.account = acc;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
