package com.hex.zz.bean;

import android.graphics.drawable.Drawable;

public class Item {
    private int id;
    private Drawable image;

    public Item(int id, Drawable image) {
        this.id = id;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public Drawable getImage() {
        return image;
    }
}
