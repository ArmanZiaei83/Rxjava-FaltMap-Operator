package com.example.flatmaprx;

public class RecViewModel {

    String body;
    boolean showProgressBar;

    public RecViewModel(String body, boolean showProgressBar) {
        this.body = body;
        this.showProgressBar = showProgressBar;
    }

    public String getBody() {
        return body;
    }

    public boolean isShowProgressBar() {
        return showProgressBar;
    }
}
