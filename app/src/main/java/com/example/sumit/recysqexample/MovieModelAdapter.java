package com.example.sumit.recysqexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Sumit on 29-01-2016.
 */
public class MovieModelAdapter extends RecyclerView.Adapter<MovieModelAdapter.MyViewHolder3> {
    Context context;
    List<MovieModel> list;
    ImageLoader imageLoader = VolleySingleton.getmInstance().getLoader();

    public MovieModelAdapter(Context context, List<MovieModel> arrayList) {
        this.context = context;
        this.list = arrayList;
    }

    @Override
    public MyViewHolder3 onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.volley_row, parent, false);
        return new MyViewHolder3(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder3 holder, int position) {
        MovieModel model = list.get(position);
        holder.txt1.setText(model.getTitle());
        holder.txt2.setText(String.valueOf(model.getRating()));
        holder.txt3.setText(String.valueOf(model.getReleaseYear()));
        Picasso.with(context).load(model.getImage()).into(holder.imageView);
        // if (imageLoader == null) {
        //   imageLoader = VolleySingleton.getmInstance().getLoader();
        // holder.img.setImageUrl(model.getImage(), imageLoader);
        // }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder3 extends RecyclerView.ViewHolder {
        TextView txt1, txt2, txt3;
        //NetworkImageView img;
        ImageView imageView;

        public MyViewHolder3(View itemView) {
            super(itemView);
            txt1 = (TextView) itemView.findViewById(R.id.text1);
            txt2 = (TextView) itemView.findViewById(R.id.text2);
            txt3 = (TextView) itemView.findViewById(R.id.text3);
            //img = (NetworkImageView) itemView.findViewById(R.id.volleyImage);
            imageView = (ImageView) itemView.findViewById(R.id.volleyImage);

        }

    }
}
