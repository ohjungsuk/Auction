package com.ajou.auction.Chat;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajou.auction.R;

import java.util.ArrayList;

public class ChattingFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chatting, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        RecyclerView recyclerView = getActivity().findViewById(R.id.chat_recyclerview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ArrayList<ChatItem> dataList = new ArrayList<>();
        dataList.add(new ChatItem("뿡뿡이", "어디서 만나면 될까요?"));
        dataList.add(new ChatItem("하", "좋은 거래 감사합니다!"));
        dataList.add(new ChatItem("경매쟁", "지하철 역 앞에서 만나영"));

        ChattingAdapter chattingAdapter = new ChattingAdapter(dataList);
        recyclerView.setAdapter(chattingAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
}