package com.example.myapplication.home;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

public class Adapter  extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Cards> data;


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
        holder.ime.setText(data.get(position).getIme());
        holder.img.setImageBitmap(data.get(position).getImg());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class ViewHolder  extends RecyclerView.ViewHolder{

        TextView ime, godine;
        ImageView img;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ime = itemView.findViewById(R.id.txImeUser);
            godine = itemView.findViewById(R.id.txGodinaUser);
            img = itemView.findViewById(R.id.imgUser);
        }
    }
}
