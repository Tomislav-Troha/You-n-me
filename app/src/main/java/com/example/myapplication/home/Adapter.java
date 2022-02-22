package com.example.myapplication.home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.user_profile.ProfileofUsers;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class Adapter extends RecyclerView.Adapter < Adapter.ViewHolder > {

    private LayoutInflater layoutInflater;
    private List < Cards > data;

    private Context context;

    Adapter(Context context, List < Cards > data) {
        this.layoutInflater = LayoutInflater.from(context);
        this.data = data;
    }

    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.list_of_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.username.setText(data.get(position).getUsername());
        holder.img.setImageBitmap(data.get(position).getImg());
        holder.godine.setText(data.get(position).getGodinaRodenja());


        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ProfileofUsers.class);
            intent.putExtra("username", data.get(position).getUsername());
            intent.putExtra("profileImage", data.get(position).getImg());
            intent.putExtra("About_partner", data.get(position).getAboutPartner());
            intent.putExtra("About_you", data.get(position).getAboutYou());
            intent.putExtra("User_traziSpol", data.get(position).getTraziSpol());
            intent.putExtra("godine_od", data.get(position).getGodine_od());
            intent.putExtra("godine_do", data.get(position).getGodine_do());
            intent.putExtra("godinaRodenja", data.get(position).getGodinaRodenja());
            intent.putExtra("objectId", data.get(position).getObjectId());
            context.startActivity(intent);
            //data.get(position).getObjectID()
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView username;
        ImageView img;
        TextView clickForMore;
        TextView godine;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.txImeUsername);
            img = itemView.findViewById(R.id.iconImg);

            godine = itemView.findViewById(R.id.txGodineList);


            clickForMore = itemView.findViewById(R.id.txVidiVise);

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