package com.example.javalearner;

public class Upload {

    private String mName;
    private String mProfileUrl;

    public Upload(){

    }

    public Upload(String name, String prifileUrl){
        if(name.trim().equals("")){
            name = "no name";
        }

        mName = name;
        mProfileUrl = prifileUrl;
        }

    public String getName(){
        return mName;
    }

    public void setName(String name){
        mName=name;
    }

    public String getPrifileUri(){
        return mProfileUrl;
    }

    public void setPrifileUrl(String prifileUrl){
        mProfileUrl = prifileUrl;
    }
}
