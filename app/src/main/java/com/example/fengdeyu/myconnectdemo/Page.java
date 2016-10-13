package com.example.fengdeyu.myconnectdemo;

import java.io.Serializable;

/**
 * Created by fengdeyu on 2016/10/13.
 */

public class Page implements Serializable {
    private int currentPage;
    private int totalPage;
    private int startIndex;
    private int eachPageCount;


    public Page( int totalPage, int eachPageCount) {

        this(totalPage,1,eachPageCount);

    }
    public Page( int totalPage,int currentPage, int eachPageCount) {

        this.totalPage = totalPage;

        this.currentPage=currentPage;

        this.eachPageCount = eachPageCount;

        this.startIndex=(currentPage-1)*(eachPageCount-1);
    }

    public int getStartIndex(){
        return (currentPage-1)*(eachPageCount-1);
    }
    public int getEndIndex(){
        return startIndex+eachPageCount;
    }


}
