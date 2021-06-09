package com.ajou.auction.Category.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BettingCompleteResponse {
    @SerializedName("completedBoardId")
    @Expose
    private Long completedBoardId;

    @SerializedName("selectedPrice")
    @Expose
    private Long selectedPrice;
    @SerializedName("selectedUserId")
    @Expose
    private String selectedUserId;
    @SerializedName("selectedUserNickName")
    @Expose
    private String selectedUserNickName;

    public Long getCompletedBoardId() {
        return completedBoardId;
    }

    public void setCompletedBoardId(Long completedBoardId) {
        this.completedBoardId = completedBoardId;
    }

    public Long getSelectedPrice() {
        return selectedPrice;
    }

    public void setSelectedPrice(Long selectedPrice) {
        this.selectedPrice = selectedPrice;
    }

    public String getSelectedUserId() {
        return selectedUserId;
    }

    public void setSelectedUserId(String selectedUserId) {
        this.selectedUserId = selectedUserId;
    }

    public String getSelectedUserNickName() {
        return selectedUserNickName;
    }

    public void setSelectedUserNickName(String selectedUserNickName) {
        this.selectedUserNickName = selectedUserNickName;
    }
}
