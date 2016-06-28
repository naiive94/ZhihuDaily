package com.naiive.zhihudaily.model;

/**
 * Created by wangzhe on 16/6/18.
 */

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ThemeNews {

    @SerializedName("stories")
    @Expose
    private List<Story> stories = new ArrayList<Story>();
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("background")
    @Expose
    private String background;
    @SerializedName("color")
    @Expose
    private Long color;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("editors")
    @Expose
    private List<Editor> editors = new ArrayList<Editor>();
    @SerializedName("image_source")
    @Expose
    private String imageSource;

    /**
     * No args constructor for use in serialization
     */
    public ThemeNews() {
    }

    /**
     * @param imageSource
     * @param stories
     * @param color
     * @param description
     * @param name
     * @param background
     * @param image
     * @param editors
     */
    public ThemeNews(List<Story> stories, String description, String background, Long color, String name, String image, List<Editor> editors, String imageSource) {
        this.stories = stories;
        this.description = description;
        this.background = background;
        this.color = color;
        this.name = name;
        this.image = image;
        this.editors = editors;
        this.imageSource = imageSource;
    }

    /**
     * @return The stories
     */
    public List<Story> getStories() {
        return stories;
    }

    /**
     * @param stories The stories
     */
    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The background
     */
    public String getBackground() {
        return background;
    }

    /**
     * @param background The background
     */
    public void setBackground(String background) {
        this.background = background;
    }

    /**
     * @return The color
     */
    public Long getColor() {
        return color;
    }

    /**
     * @param color The color
     */
    public void setColor(Long color) {
        this.color = color;
    }

    /**
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return The image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return The editors
     */
    public List<Editor> getEditors() {
        return editors;
    }

    /**
     * @param editors The editors
     */
    public void setEditors(List<Editor> editors) {
        this.editors = editors;
    }

    /**
     * @return The imageSource
     */
    public String getImageSource() {
        return imageSource;
    }

    /**
     * @param imageSource The image_source
     */
    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

}