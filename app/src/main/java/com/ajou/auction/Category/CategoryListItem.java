package com.ajou.auction.Category;

public class CategoryListItem {
    private String img;
    private String title, endDate, price, likeCnt;

    public CategoryListItem(String img, String title, String endDate, String price, String likeCnt) {
        this.img = img;
        this.title = title;
        this.endDate = endDate;
        this.price = price;
        this.likeCnt = likeCnt;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(String likeCnt) {
        this.likeCnt = likeCnt;
    }
}
