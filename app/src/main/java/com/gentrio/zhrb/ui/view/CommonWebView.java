package com.gentrio.zhrb.ui.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.AttributeSet;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;

/**
 * Created by Gentrio on 2016/10/22.
 */
public class CommonWebView extends WebView {

    public static final String ENCODING_UTF_8 = "UTF-8";
    public static final String MIME_TYPE = "text/html";

    public CommonWebView(Context context) {
        super(context);
        init();
    }

    public CommonWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CommonWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public CommonWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        if (isInEditMode()) {
            return;
        }
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(false);
        //设置缓存模式
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //开启DOM storage API功能
        settings.setDomStorageEnabled(true);
        //开启database storage 功能
        settings.setDatabaseEnabled(true);
        String cacheDir;
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheDir = getContext().getExternalCacheDir().getAbsolutePath()+"/web_cache";
        }else {
            cacheDir = getContext().getCacheDir().getAbsolutePath()+"/web_cache";
        }
        settings.setAppCachePath(cacheDir);
        settings.setAllowFileAccess(true);
        settings.setAppCacheEnabled(true);

        settings.setLoadsImagesAutomatically(true);
        settings.setDefaultTextEncodingName(ENCODING_UTF_8);
        settings.setBlockNetworkImage(false);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        settings.setUseWideViewPort(true);
//        settings.setLoadWithOverviewMode(true);
        setHorizontalScrollBarEnabled(false);

//        setWebChromeClient(new WebChromeClient());
    }
}
