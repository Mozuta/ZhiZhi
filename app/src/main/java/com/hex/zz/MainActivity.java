package com.hex.zz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.barnettwong.dragfloatactionbuttonlibrary.view.DragFloatActionButton;
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
    private DragFloatActionButton btn_float;



    private int[] images = {R.drawable.main_strength,R.drawable.main_movement,R.drawable.main_exercise};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    @SuppressLint("ClickableViewAccessibility")
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
        btn_float = findViewById(R.id.circle_button);
        btn_float.setOnClickListener(this);
        //重写onTouch方法

        btn_float.setOnTouchListener(new DragFloatActionButton.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                //如果在整个屏幕左半边则更改图片
                int screenWidth = getResources().getDisplayMetrics().widthPixels;
                if (motionEvent.getRawX() < screenWidth/ 2) {
                    btn_float.setImageResource(R.drawable.main_strength);
                } else {
                    btn_float.setImageResource(R.drawable.main_movement);
                }


                return false;
            }
        });



    }

    public void onClick(View v){
        if (v.getId() == R.id.main_head) {
            Intent intent = new Intent(MainActivity.this, SoundLevelActivity.class);
            startActivity(intent);
        }
        else if (v.getId() == R.id.circle_button) {
            Intent intent = new Intent(MainActivity.this, Mmt_choose_Activity.class);
            startActivity(intent);
        }
    }

}