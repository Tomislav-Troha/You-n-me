package com.example.myapplication.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.user_profile.ProfileofUsers;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Adapter  extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Cards> data;

    private Context context;

    Adapter(Context context, List<Cards> data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }



    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_of_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.username.setText(data.get(position).getUsername());
        holder.img.setImageBitmap(data.get(position).getImg());



        holder.clickForMore.setOnClickListener(view -> {
            Intent intent = new Intent(context, ProfileofUsers.class);
            intent.putExtra("username", data.get(position).getUsername());
            intent.putExtra("profileImage", data.get(position).getImg());
            context.startActivity(intent);
            //data.get(position).getObjectID()
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }




    public class ViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView username, godine;
        ImageView img;
        TextView clickForMore;




        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.txImeUsername);
            godine = itemView.findViewById(R.id.txGodinaUser);
            img = itemView.findViewById(R.id.imgUser);

            clickForMore = itemView.findViewById(R.id.txVidiVise);

            context = clickForMore.getContext();
            clickForMore.setClickable(true);
            clickForMore.setOnClickListener(this);


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
