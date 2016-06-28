package com.naiive.zhihudaily.model;

/**
 * Created by wangzhe on 16/6/18.
 */
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.naiive.zhihudaily.common.base.BaseModel;

public class Topic implements BaseModel {

    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("subscribed")
    @Expose
    private List<Other> subscribed = new ArrayList<Other>();
    @SerializedName("others")
    @Expose
    private List<Other> others = new ArrayList<Other>();

    /**
     *
     * @return
     * The limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     *
     * @param limit
     * The limit
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     *
     * @return
     * The subscribed
     */
    public List<Other> getSubscribed() {
        return subscribed;
    }

    /**
     *
     * @param subscribed
     * The subscribed
     */
    public void setSubscribed(List<Other> subscribed) {
        this.subscribed = subscribed;
    }

    /**
     *
     * @return
     * The others
     */
    public List<Other> getOthers() {
        return others;
    }

    /**
     *
     * @param others
     * The others
     */
    public void setOthers(List<Other> others) {
        this.others = others;
    }

    public static class Other{
        @SerializedName("color")
        @Expose
        private Integer color;
        @SerializedName("thumbnail")
        @Expose
        private String thumbnail;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;

        /**
         *
         * @return
         * The color
         */
        public Integer getColor() {
            return color;
        }

        /**
         *
         * @param color
         * The color
         */
        public void setColor(Integer color) {
            this.color = color;
        }

        /**
         *
         * @return
         * The thumbnail
         */
        public String getThumbnail() {
            return thumbnail;
        }

        /**
         *
         * @param thumbnail
         * The thumbnail
         */
        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        /**
         *
         * @return
         * The description
         */
        public String getDescription() {
            return description;
        }

        /**
         *
         * @param description
         * The description
         */
        public void setDescription(String description) {
            this.description = description;
        }

        /**
         *
         * @return
         * The id
         */
        public Integer getId() {
            return id;
        }

        /**
         *
         * @param id
         * The id
         */
        public void setId(Integer id) {
            this.id = id;
        }

        /**
         *
         * @return
         * The name
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name
         * The name
         */
        public void setName(String name) {
            this.name = name;
        }
    }
}
