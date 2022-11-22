package com.drivill.courier.activity.onboarding;

public class OnBoardItems {
    String Title,Description;
    int SplashImg,LastImage;

    public OnBoardItems(String title, String description, int splashImg) {
        Title = title;
        Description = description;
        SplashImg = splashImg;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getSplashImg() {
        return SplashImg;
    }

    public void setSplashImg(int splashImg) {
        SplashImg = splashImg;
    }

}

