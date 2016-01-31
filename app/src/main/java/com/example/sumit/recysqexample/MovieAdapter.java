package com.example.sumit.recysqexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * Created by Sumit on 27-01-2016.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {
    Context context;
    List<MovieApi> arrayList;
    DatabaseHandler db;

    public MovieAdapter(Context context, List<MovieApi> movieApis) {
        this.context = context;
        arrayList = movieApis;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        db = new DatabaseHandler(context);
        MovieApi movieApi = arrayList.get(position);
        holder.txt.setText(movieApi.getTitle());
        holder.txt1.setText(String.valueOf(movieApi.getReleaseYear()));
        holder.txt2.setText(String.valueOf(movieApi.getRating()));
        Picasso.with(context).load(movieApi.getImage()).into(holder.imageView);
        // Bitmap bitmap = ((BitmapDrawable) holder.imageView.getDrawable()).getBitmap();
        //db.insertImage(conversion(holder));
    }

    public byte[] conversion(MyViewHolder holder) {
        Bitmap bitmap = ((BitmapDrawable) holder.imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txt, txt1, txt2;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv);
            txt = (TextView) itemView.findViewById(R.id.textView);
            txt1 = (TextView) itemView.findViewById(R.id.textView1);
            txt2 = (TextView) itemView.findViewById(R.id.textView2);
        }
    }
}
