package com.naiive.zhihudaily.model;

/**
 * Created by wangzhe on 16/5/21.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Story {

    @SerializedName("images")
    @Expose
    private List<String> images = new ArrayList<String>();
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
    @SerializedName("multipic")
    @Expose
    private boolean multipic;

    /**
     * No args constructor for use in serialization
     *
     */
    public Story() {
    }

    /**
     *
     * @param id
     * @param multipic
     * @param title
     * @param images
     * @param type
     * @param gaPrefix
     */
    public Story(List<String> images, int type, int id, String gaPrefix, String title, boolean multipic) {
        this.images = images;
        this.type = type;
        this.id = id;
        this.gaPrefix = gaPrefix;
        this.title = title;
        this.multipic = multipic;
    }

    /**
     *
     * @return
     * The images
     */
    public List<String> getImages() {
        return images;
    }

    /**
     *
     * @param images
     * The images
     */
    public void setImages(List<String> images) {
        this.images = images;
    }

    public Story withImages(List<String> images) {
        this.images = images;
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

    public Story withType(int type) {
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

    public Story withId(int id) {
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

    public Story withGaPrefix(String gaPrefix) {
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

    public Story withTitle(String title) {
        this.title = title;
        return this;
    }

    /**
     *
     * @return
     * The multipic
     */
    public boolean isMultipic() {
        return multipic;
    }

    /**
     *
     * @param multipic
     * The multipic
     */
    public void setMultipic(boolean multipic) {
        this.multipic = multipic;
    }

    public Story withMultipic(boolean multipic) {
        this.multipic = multipic;
        return this;
    }


}