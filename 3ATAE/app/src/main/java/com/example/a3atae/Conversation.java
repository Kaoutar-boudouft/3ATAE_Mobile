package com.example.a3atae;

public class Conversation {
    String idconv;
    User convwithuser;
    Annonce convaboutannonce;

    public Conversation(String idconv, User convwithuser, Annonce convaboutannonce) {
        this.idconv = idconv;
        this.convwithuser = convwithuser;
        this.convaboutannonce = convaboutannonce;
    }

    public String getIdconv() {
        return idconv;
    }

    public void setIdconv(String idconv) {
        this.idconv = idconv;
    }

    public User getConvwithuser() {
        return convwithuser;
    }

    public void setConvwithuser(User convwithuser) {
        this.convwithuser = convwithuser;
    }

    public Annonce getConvaboutannonce() {
        return convaboutannonce;
    }

    public void setConvaboutannonce(Annonce convaboutannonce) {
        this.convaboutannonce = convaboutannonce;
    }
}
