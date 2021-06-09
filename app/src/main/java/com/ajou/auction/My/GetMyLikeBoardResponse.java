package com.ajou.auction.My;

import com.ajou.auction.Category.Model.BoardListInfos;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetMyLikeBoardResponse {
    @SerializedName("allBoards")
    @Expose
    private List<GetMyLikeBoardListInfos> boardList = null;

    public List<GetMyLikeBoardListInfos> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<GetMyLikeBoardListInfos> boardList) {
        this.boardList = boardList;
    }
}
