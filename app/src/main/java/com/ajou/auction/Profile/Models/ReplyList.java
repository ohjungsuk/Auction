package com.ajou.auction.Profile.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReplyList {
    @SerializedName("content")
    @Expose
    private String content;

    @SerializedName("uploadDate")
    @Expose
    private String uploadDate;

    @SerializedName("writerNickName")
    @Expose
    private String writerNickName;

    @SerializedName("writerUserId")
    @Expose
    private String writerUserId;

    public ReplyList(String content, String uploadDate, String writerNickName, String writerUserId) {
        this.content = content;
        this.uploadDate = uploadDate;
        this.writerNickName = writerNickName;
        this.writerUserId = writerUserId;
    }

    public String getContent() {
        return content;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public String getWriterNickName() {
        return writerNickName;
    }

    public String getWriterUserId() {
        return writerUserId;
    }
}
