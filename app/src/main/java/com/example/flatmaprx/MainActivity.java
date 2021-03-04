package com.example.flatmaprx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.ShutdownChannelGroupException;
import java.time.LocalDate;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    RetrofitHolder retrofitHolder;

    //recyclerView :
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecAdapter recAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //rec init :

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recAdapter = new RecAdapter();

        recyclerView.setLayoutManager(layoutManager);


        getPosts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Post>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                        System.out.println("Subscribed");
                    }

                    @Override
                    public void onNext(@NonNull Post post) {


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                        makeToast("Error : " + e.getMessage());
                        System.out.println("Error : " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("Completed !!");
                    }
                });
    }


    private void makeToast(String message) {

        Toast.makeText(this , message , Toast.LENGTH_SHORT).show();
    }

    public Observable<Post> getPosts(){

        return RetrofitHolder.getApi()
                .getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Function<List<Post>, ObservableSource<Post>>() {
                    @Override
                    public ObservableSource<Post> apply(@NonNull List<Post> posts) throws Exception {


                        //Adapter Process :
                        recAdapter.setPosts(posts);
                        recyclerView.setAdapter(recAdapter);
                        return Observable.fromIterable(posts).subscribeOn(Schedulers.io());

                    }
                });
    }
}