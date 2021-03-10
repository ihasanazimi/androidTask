package com.basalam.androidtask.utils;

import android.widget.ImageView;
import androidx.databinding.BindingAdapter;

import com.basalam.androidtask.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

public class Binding {

    @BindingAdapter("image")
    public static void LoadImage(ImageView iv , String url){
        Glide.with(iv.getContext()).load(url).transition(DrawableTransitionOptions.withCrossFade()).placeholder(R.drawable.ic_picture).into(iv);
    }
}
