
package com.example.sumit.recysqexample;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("org.jsonschema2pojo")
public class Deal {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("actual_price")
    @Expose
    public String actualPrice;
    @SerializedName("discount")
    @Expose
    public String discount;
    @SerializedName("rating")
    @Expose
    public String rating;
    @SerializedName("provider")
    @Expose
    public String provider;
    @SerializedName("link")
    @Expose
    public String link;
    @SerializedName("image")
    @Expose
    public String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(String actualPrice) {
        this.actualPrice = actualPrice;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
