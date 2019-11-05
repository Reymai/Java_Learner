package com.example.javalearner;

public class Upload {

    private String mName;
    private String mPrifileUrl;

    public Upload(){

    }

    public Upload(String name, String prifileUrl){
        if(name.trim().equals("")){
            name = "no name";
        }

        mName = name;
        mPrifileUrl = prifileUrl;
        }

    public String getName(){
        return mName;
    }

    public void setName(String name){
        mName=name;
    }

    public String getPrifileUri(){
        return mPrifileUrl;
    }

    public void setPrifileUrl(String prifileUrl){
        mPrifileUrl = prifileUrl;
    }
}
