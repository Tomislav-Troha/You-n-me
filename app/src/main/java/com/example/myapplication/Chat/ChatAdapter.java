package com.example.myapplication.Chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
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
    }





    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView chatUsername;
        ImageView imgChat;
        TextView chatGodine;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            chatUsername = itemView.findViewById(R.id.txChatUsername);
            imgChat = itemView.findViewById(R.id.profileChatUserImg);
            chatGodine = itemView.findViewById(R.id.txChatGodine);

            context = itemView.getContext();

        }

        @Override
        public void onClick(View v) {

      /*     final Intent intent;
           switch (getAdapterPosition()){
               case 0:
                   intent = new Intent(context, ProfileofUsers.class);
                   break;

               default:
                   intent = new Intent(context, HomePage.class);
           }
           context.startActivity(intent); */

        }

    }

}