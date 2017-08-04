package com.example.yfc.stockcountapp;


/**
 * Created by YFC on 2016/6/11.
 */
public class OilData {

    private String title;

    private String result;


    public OilData(String title, String result){
        this.title = title;

        this.result=result;
    }

    public String getTitle() {
        return title;

    }
    public void setTitle(String id) {
        this.title = id;

    }

    public String getResult() {

        return result;
    }

    public void setResult(String result)
    {

        this.result = result;
    }
}
