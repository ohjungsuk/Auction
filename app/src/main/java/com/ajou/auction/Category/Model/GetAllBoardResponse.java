package com.ajou.auction.Category.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import javax.xml.transform.Result;

public class GetAllBoardResponse {
    @SerializedName("allBoards")
    @Expose
    private List<BoardListInfos> boardList = null;

    public List<BoardListInfos> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<BoardListInfos> boardList) {
        this.boardList = boardList;
    }
}
