package com.naiive.zhihudaily.model;

/**
 * Created by wangzhe on 16/5/21.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.naiive.zhihudaily.common.base.BaseModel;

import java.util.ArrayList;
import java.util.List;

public class News implements BaseModel{

    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("stories")
    @Expose
    private List<Story> stories = new ArrayList<Story>();
    @SerializedName("top_stories")
    @Expose
    private List<TopStory> topStories = new ArrayList<TopStory>();

    /**
     * No args constructor for use in serialization
     *
     */
    public News() {
    }

    /**
     *
     * @param stories
     * @param topStories
     * @param date
     */
    public News(String date, List<Story> stories, List<TopStory> topStories) {
        this.date = date;
        this.stories = stories;
        this.topStories = topStories;
    }

    /**
     *
     * @return
     * The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     * The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    public News withDate(String date) {
        this.date = date;
        return this;
    }

    /**
     *
     * @return
     * The stories
     */
    public List<Story> getStories() {
        return stories;
    }

    /**
     *
     * @param stories
     * The stories
     */
    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public News withStories(List<Story> stories) {
        this.stories = stories;
        return this;
    }

    /**
     *
     * @return
     * The topStories
     */
    public List<TopStory> getTopStories() {
        return topStories;
    }

    /**
     *
     * @param topStories
     * The top_stories
     */
    public void setTopStories(List<TopStory> topStories) {
        this.topStories = topStories;
    }

    public News withTopStories(List<TopStory> topStories) {
        this.topStories = topStories;
        return this;
    }


}