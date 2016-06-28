package com.naiive.zhihudaily.model;

/**
 * Created by wangzhe on 16/5/21.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopStory {

    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("type")
    @Expose
    private int type;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("ga_prefix")
    @Expose
    private String gaPrefix;
    @SerializedName("title")
    @Expose
    private String title;

    /**
     * No args constructor for use in serialization
     *
     */
    public TopStory() {
    }

    /**
     *
     * @param id
     * @param title
     * @param image
     * @param type
     * @param gaPrefix
     */
    public TopStory(String image, int type, int id, String gaPrefix, String title) {
        this.image = image;
        this.type = type;
        this.id = id;
        this.gaPrefix = gaPrefix;
        this.title = title;
    }

    /**
     *
     * @return
     * The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    public TopStory withImage(String image) {
        this.image = image;
        return this;
    }

    /**
     *
     * @return
     * The type
     */
    public int getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(int type) {
        this.type = type;
    }

    public TopStory withType(int type) {
        this.type = type;
        return this;
    }

    /**
     *
     * @return
     * The id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(int id) {
        this.id = id;
    }

    public TopStory withId(int id) {
        this.id = id;
        return this;
    }

    /**
     *
     * @return
     * The gaPrefix
     */
    public String getGaPrefix() {
        return gaPrefix;
    }

    /**
     *
     * @param gaPrefix
     * The ga_prefix
     */
    public void setGaPrefix(String gaPrefix) {
        this.gaPrefix = gaPrefix;
    }

    public TopStory withGaPrefix(String gaPrefix) {
        this.gaPrefix = gaPrefix;
        return this;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public TopStory withTitle(String title) {
        this.title = title;
        return this;
    }

}