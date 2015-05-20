package com.libraua.photobattle.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Build;
import android.view.View;

/**
 * Created by 23060607 on 2015-05-20.
 */
public class AnimUtils {
    public static void startAnimation(Context context, final boolean show, final View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = context.getResources().getInteger(android.R.integer.config_shortAnimTime);
            view.setVisibility(show ? View.VISIBLE : View.GONE);
            view.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    view.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            view.setVisibility(show ? View.VISIBLE : View.GONE);
        }

    }
}
