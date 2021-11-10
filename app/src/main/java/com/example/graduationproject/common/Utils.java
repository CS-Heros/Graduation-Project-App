package com.example.graduationproject.common;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.graduationproject.R;
import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerDrawable;

import java.io.File;

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

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
                .placeholder(shimmerDrawable)
                .error(R.drawable.ic_no_image)
                .into(imageView);
    }

    public static void toastMy(Context context, String message, boolean success) {
        if (success) {
            Toasty.success(context, message, Toasty.LENGTH_SHORT).show();
        } else {
            Toasty.error(context, message, Toasty.LENGTH_SHORT).show();
        }
    }

    public static MultipartBody.Part getImageAsMultiBodyPart(Context context, Uri uri, String name) {

        String path = RealPathUtil.getRealPath(context, uri);
        File file = new File(path);

        RequestBody requestBody = RequestBody.create
                (MediaType.parse(context.getContentResolver().getType(uri)), file);

        return okhttp3.MultipartBody.Part.createFormData(name, file.getName(), requestBody);
    }
}
