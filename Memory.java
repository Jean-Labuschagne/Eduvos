package com.eduv4955673.itmba2_33_formativeassessment_tygervally_eduv4955673;

import java.util.Date;

public class Memory {
    private int id;
    private String title;
    private String description;
    private String imagePath;
    private String location;
    private Date date;
    private String mood;
    private String musicTrack;

    public Memory() {}

    public Memory(String title, String description, String imagePath, String location, Date date, String mood, String musicTrack) {
        this.title = title;
        this.description = description;
        this.imagePath = imagePath;
        this.location = location;
        this.date = date;
        this.mood = mood;
        this.musicTrack = musicTrack;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getMood() { return mood; }
    public void setMood(String mood) { this.mood = mood; }

    public String getMusicTrack() { return musicTrack; }
    public void setMusicTrack(String musicTrack) { this.musicTrack = musicTrack; }
}