package com.example.sumit.recysqexample;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Sumit on 30-01-2016.
 */
public class VolleySingleton extends Application {
    public static final String TAG = VolleySingleton.class.getSimpleName();
    private RequestQueue queue;
    private ImageLoader loader;
    private static VolleySingleton mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public static synchronized VolleySingleton getmInstance() {
        return mInstance;
    }

    public RequestQueue getQueue() {
        if (queue == null) {
            queue = Volley.newRequestQueue(getApplicationContext());
        }
        return queue;
    }

    public ImageLoader getLoader() {
        getQueue();
        if (loader == null) {
            loader = new ImageLoader(this.queue, new BitmapCache());
        }
        return this.loader;
    }

    public <T> void addToRequestQueue(Request<T> request, String tag) {
        request.setTag((TextUtils.isEmpty(tag) ? TAG : tag));
    }

    public <T> void addToRequestQueue(Request<T> request) {
        request.setTag(TAG);
        getQueue().add(request);
    }

    public void cancelPendingRequest(Object tag) {
        if (queue != null) {
            queue.cancelAll(tag);
        }
    }
}
