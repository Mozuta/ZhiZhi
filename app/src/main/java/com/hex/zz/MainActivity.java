package com.hex.zz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.youth.banner.Banner;
import com.youth.banner.holder.BannerImageHolder;
import com.youth.banner.indicator.CircleIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //private Banner banner;
    //Drawable localDrawable = getResources().getDrawable(R.drawable.main_strength);
    private ImageButton head_pic;


    private int[] images = {R.drawable.main_strength,R.drawable.main_movement,R.drawable.main_exercise};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    public void init(){

        /*List<Drawable> images = new ArrayList<>();
        images.add(getResources().getDrawable(R.drawable.main_strength));
        images.add(getResources().getDrawable(R.drawable.main_movement));
        images.add(getResources().getDrawable(R.drawable.main_exercise));
        //设置图片加载器
        banner = findViewById(R.id.banner);
        banner.setImageList(images);*/
        head_pic = findViewById(R.id.main_head);
        head_pic.setOnClickListener(this);


    }

    public void onClick(View v){
        if (v.getId() == R.id.main_head) {
            Intent intent = new Intent(MainActivity.this, HeadActivity.class);
            startActivity(intent);
        }
    }

}