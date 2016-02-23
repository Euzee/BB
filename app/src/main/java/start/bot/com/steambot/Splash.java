package start.bot.com.steambot;

import android.animation.Animator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;

import start.bot.com.steambot.model.TransferParameters;
import start.bot.com.steambot.utils.BaseActivity;
import start.bot.com.steambot.utils.PM;
import start.bot.com.steambot.utils.Util;
import start.bot.com.steambot.volley.TransferRequest;

/**
 * Created by Dmitriy on 22.02.2016.
 */
public class Splash extends BaseActivity {

    private static final long ANIMATION_DURATION = 566;
    private TransferParameters params;
    private boolean resultDelivered = false;
    private Animator.AnimatorListener processResponse = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (!resultDelivered)
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    if (params == null) {
                        startActivity(new Intent(Splash.this, LoginActivity.class));
                        finish();
                    }
                }
            }).start();
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(0, android.R.anim.fade_in);
        setContentView(R.layout.splash);
        animateView(findViewById(R.id.logo), processResponse);
        checkSession();
    }

    private void checkSession() {
        post(new TransferRequest(PM.getInstance().getObjectByClass(TransferParameters.class), new Response.Listener<TransferParameters>() {
            @Override
            public void onResponse(TransferParameters params) {
                setResponse(params);
            }
        }));
    }

    private void setResponse(TransferParameters params) {
        resultDelivered = true;
        this.params = params;
    }

    private void animateView(View view, Animator.AnimatorListener listener) {
        view.animate().translationY(detectPosition(view))
                .setDuration(ANIMATION_DURATION)
                .alpha(1)
                .setListener(listener);
    }

    private float detectPosition(View view) {
        ((ViewGroup) view.getParent()).measure(View.MeasureSpec.EXACTLY, View.MeasureSpec.EXACTLY);
        return -(view.getMeasuredHeight());
    }
}
