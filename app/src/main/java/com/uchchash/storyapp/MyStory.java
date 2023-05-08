package com.uchchash.storyapp;

public class MyStory {

    String title;
    String story;

    public MyStory(){}

    public MyStory(String title, String story) {
        this.title = title;
        this.story = story;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }
}
