package com.example.graduationproject.common;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.graduationproject.R;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerDrawable;

public class Utils {
    private static ShimmerDrawable getShimmerDrawable() {
        Shimmer shimmer = new Shimmer.AlphaHighlightBuilder()
                .setDuration(1800)
                .setBaseAlpha(0.7f)
                .setHighlightAlpha(0.6f)
                .setDirection(Shimmer.Direction.LEFT_TO_RIGHT)
                .setAutoStart(true)
                .build();

        ShimmerDrawable shimmerDrawable = new ShimmerDrawable();
        shimmerDrawable.setShimmer(shimmer);
        return shimmerDrawable;
    }

    public static void setImageUsingGlide(ImageView imageView, String imageUrl) {
        Context context = imageView.getContext();
        ShimmerDrawable shimmerDrawable = getShimmerDrawable();
        Glide.with(context)
                .load(imageUrl)
                .placeholder(R.drawable.ic_no_image)
                .error(R.drawable.ic_no_image)
                .into(imageView);
    }
}
