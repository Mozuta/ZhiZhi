package com.hex.zz;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;



import java.io.IOException;
import java.util.ArrayList;

public class SoundLevelActivity extends AppCompatActivity {

    private MediaRecorder mediaRecorder;

    private TextView soundLevelTextView;
    private Handler handler;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_level);


        soundLevelTextView = findViewById(R.id.soundLevelTextView);

        handler = new Handler();
        initPermission();


    }

    private void startRecording() {
        try {
            mediaRecorder = new MediaRecorder();
            Log.e("SoundLevelActivity", "startRecording: " + mediaRecorder.toString());
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            // 设置输出文件路径，例如保存到应用的私有目录中
            String outputFile = getFilesDir().getAbsolutePath() + "/recording.3gp";
            mediaRecorder.setOutputFile(outputFile);

            mediaRecorder.prepare();
            mediaRecorder.start();
            startUpdatingSoundLevel();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initPermission() {
        String permissions[] = {Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        ArrayList<String> toApplyList = new ArrayList<String>();

        for (String perm : permissions) {
            if (PackageManager.PERMISSION_GRANTED != ContextCompat.checkSelfPermission(this, perm)) {
                toApplyList.add(perm);
            }
        }
        String tmpList[] = new String[toApplyList.size()];
        if (!toApplyList.isEmpty()) {
            ActivityCompat.requestPermissions(this, toApplyList.toArray(tmpList), 123);

        }

        // 权限已经授予，直接初始化
        else {
            startRecording();
        }

    }

    /**
     * 权限申请回调，可以作进一步处理
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 123) { // 根据您的请求代码进行匹配
            boolean allPermissionsGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allPermissionsGranted = false;
                    break;
                }
            }
            if (allPermissionsGranted) {
                // 所有权限已授予，执行相应操作
                startRecording();
            } else {
                // 权限未授予，可能需要向用户解释权限的重要性或提供其他处理方式
                Log.e("SoundLevelActivity", "onRequestPermissionsResult: 权限未授予");
            }
        }
    }



    private void startUpdatingSoundLevel() {
        Runnable updateSoundLevelRunnable = new Runnable() {
            @Override
            public void run() {
                updateSoundLevel();
                handler.postDelayed(this, 100); // 更新频率为每100毫秒
            }
        };

        handler.post(updateSoundLevelRunnable);
    }

    private void updateSoundLevel() {
        if (mediaRecorder == null) {
            return;
        }
        soundLevelTextView.setText(String.valueOf(mediaRecorder.getMaxAmplitude()));
    }
    //返回键
    @Override
    public void onBackPressed() {
        // 停止录音
        stopRecording();
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopRecording();
    }

    private void stopRecording() {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }
}
