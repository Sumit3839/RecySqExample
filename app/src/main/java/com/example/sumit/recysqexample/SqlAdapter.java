package com.example.sumit.recysqexample;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sumit on 27-01-2016.
 */
public class SqlAdapter extends RecyclerView.Adapter<SqlAdapter.MyViewHolder1> {
    List<MovieApi> arrayList;
    Context context;
    DatabaseHandler db;

    public SqlAdapter(Context context, List<MovieApi> objects) {
        this.context = context;
        arrayList = objects;
    }

    @Override
    public MyViewHolder1 onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.row, parent, false);
        MyViewHolder1 holder1 = new MyViewHolder1(view);
        return holder1;
    }

    @Override
    public void onBindViewHolder(MyViewHolder1 holder, int position) {
        MovieApi movieApi = arrayList.get(position);
        holder.txt1.setText(movieApi.getTitle());
        holder.txt2.setText(String.valueOf(movieApi.getRating()));
        holder.txt3.setText(String.valueOf(movieApi.getReleaseYear()));
        //byte[] encodeByte = Base64.decode(movieApi.getImage(), Base64.DEFAULT);
        //Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        // holder.imageView.setImageBitmap(bitmap);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView txt1, txt2, txt3;

        public MyViewHolder1(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv);
            txt1 = (TextView) itemView.findViewById(R.id.textView);
            txt2 = (TextView) itemView.findViewById(R.id.textView1);
            txt3 = (TextView) itemView.findViewById(R.id.textView2);

        }
    }
}
