package com.worlddomination.mushroom.interactivestory.model;

/**
 * Created by mushroom on 17/03/16.
 */
public class Choice {
    private String mText;
    private int mNextPage;

    public Choice(String text, int nextPage){
        this.mText=text;
        this.mNextPage=nextPage;
    }
    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public int getNextPage() {
        return mNextPage;
    }

    public void setNextPage(int nextPage) {
        mNextPage = nextPage;
    }
}
