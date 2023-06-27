package com.hex.zz.utils;

import android.media.MediaRecorder;

public class SoundLevelMeter {
    private MediaRecorder mediaRecorder;

    public void start() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile("/dev/null");

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float getAmplitude(MediaRecorder mediaRecorder) {
        if (this.mediaRecorder != null) {
            try {
                return this.mediaRecorder.getMaxAmplitude();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public void stop() {
        if (mediaRecorder != null) {
            try {
                mediaRecorder.stop();
                mediaRecorder.release();
                mediaRecorder = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

