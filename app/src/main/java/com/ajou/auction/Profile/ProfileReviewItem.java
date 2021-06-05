package com.ajou.auction.Profile;

public class ProfileReviewItem {
    private String img;
    private String id;
    private String review;

    public ProfileReviewItem(String img, String id, String review) {
        this.img = img;
        this.id = id;
        this.review = review;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}
