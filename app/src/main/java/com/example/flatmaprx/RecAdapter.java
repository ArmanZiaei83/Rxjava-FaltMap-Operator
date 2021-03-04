package com.example.flatmaprx;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SNIHostName;

import rx.internal.operators.SingleDoAfterTerminate;

public class RecAdapter  extends RecyclerView.Adapter<RecAdapter.MyViewHolder> {

    List<Post> posts = new ArrayList<>();

    @NonNull
    @Override
    public RecAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_view , parent , false);
        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecAdapter.MyViewHolder holder, int position) {

        Post post = posts.get(position);

        TextView textView = holder.textView;
        textView.setText( "Title : " + post.getTitle() + "\n" +  "Id : " + post.getId() + "\n" + "Body : " + post.getBody() + "\n" );

        if (textView.getText() == null){

            holder.progressBar.setVisibility(View.VISIBLE);
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    holder.progressBar.setVisibility(View.GONE);
                }
            } , 2000 );

        }
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Post> posts){

        this.posts = posts;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        ProgressBar progressBar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView);
            progressBar =itemView.findViewById(R.id.progressBar);

        }


    }
}
