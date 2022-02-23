package com.example.myapplication.Chat;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.ceylonlabs.imageviewpopup.ImagePopup;
import com.example.myapplication.R;
import com.example.myapplication.home.SecondFragment;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List <ChatCards> data;

    private Context context;

    public ChatAdapter(Context context, List<ChatCards> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_of_chat_user, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.chatUsername.setText(data.get(position).getChatUsername());
        holder.imgChat.setImageBitmap(data.get(position).getChatImg());
        holder.chatGodine.setText(data.get(position).getChatGodina());

        ImagePopup imagePopup = new ImagePopup(context);
        imagePopup.setWindowHeight(800); // Optional
        imagePopup.setWindowWidth(800); // Optional
        imagePopup.setBackgroundColor(Color.BLACK);  // Optional
        imagePopup.setHideCloseIcon(true);  // Optional
        imagePopup.setImageOnClickClose(true);  // Optional

        imagePopup.initiatePopup(holder.imgChat.getDrawable());


        holder.imgChat.setOnClickListener(view -> {
            imagePopup.viewPopup();
        });
    }





    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView chatUsername;
        ImageView imgChat;
        TextView chatGodine;
        CardView chat;


        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            chatUsername = itemView.findViewById(R.id.txChatUsername);
            imgChat = itemView.findViewById(R.id.profileChatUserImg);
            chatGodine = itemView.findViewById(R.id.txChatGodine);

            chat = itemView.findViewById(R.id.cardViewChat);

            imgChat.setClickable(true);
            context = imgChat.getContext();

            chat.setClickable(true);
            context = chat.getContext();

            context = itemView.getContext();

        }

        @Override
        public void onClick(View v) {


        }

    }

}