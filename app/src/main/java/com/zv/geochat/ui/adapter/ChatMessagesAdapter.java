package com.zv.geochat.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zv.geochat.R;
import com.zv.geochat.model.ChatMessage;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import co.dift.ui.SwipeToAction;


public class ChatMessagesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ChatMessage> items;


    /**
     * References to the views for each data item
     **/
    public class ChatMessageViewHolder extends SwipeToAction.ViewHolder<ChatMessage> {
        public TextView userName;
        public TextView chatMessageBody;
        public ImageView imageView;

        public TextView tvUserId;
        public TextView tvMsgDate;

        public ChatMessageViewHolder(View v) {
            super(v);

            userName = (TextView) v.findViewById(R.id.userName);
            chatMessageBody = (TextView) v.findViewById(R.id.body);
            imageView = (ImageView) v.findViewById(R.id.image);
            tvUserId = (TextView) v.findViewById(R.id.tvUserId);
            tvMsgDate = (TextView) v.findViewById(R.id.tvMsgDate);
        }
    }

    /**
     * Constructor
     **/
    public ChatMessagesAdapter(List<ChatMessage> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);

        return new ChatMessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChatMessage item = items.get(position);
        ChatMessageViewHolder vh = (ChatMessageViewHolder) holder;
        vh.userName.setText(item.getUserName());
        vh.chatMessageBody.setText(item.getBody());
        Date date = new Date();
        date.setTime(item.getChatMsgDate());

        if (System.currentTimeMillis() > (item.getChatMsgDate() + (1000 * 60 * 60 * 24))) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/DD/YYYY");
            vh.tvMsgDate.setText(simpleDateFormat.format(date));
        } else {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
            vh.tvMsgDate.setText(simpleDateFormat.format(date));
        }
        vh.tvUserId.setText(String.valueOf(item.getId()));
        vh.data = item;
    }
}