package frosquivel.com.infinitescrollapp.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.github.glomadrian.loadingballs.BallView;
import frosquivel.com.infinitescrollapp.Activities.Base.BaseDetailActivity;
import frosquivel.com.infinitescrollapp.R;

/**
 * Created by Fabian on 10/06/2017.
 * An activity for charge all web pages, in this web view
 */

public class WebViewActivity extends BaseDetailActivity {

    private WebView webView;
    private BallView progressBar;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = addLayout(R.layout.activity_web_view);

        webView = (WebView) activity.findViewById(R.id.webView);
        progressBar = (BallView) activity.findViewById(R.id.ballViewLoading);
        progressBar.setVisibility(View.GONE);
        String link = getIntent().getStringExtra("link");

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.setInitialScale(90);
        webView.getSettings().setSupportZoom(true);
        progressBar.setVisibility(View.VISIBLE);
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                view.loadUrl(url);
                return false;
            }

            @Override
            public void onPageFinished(WebView view, final String url) {
                progressBar.setVisibility(View.GONE);
            }
        });

        webView.loadUrl(link);
    }
}
