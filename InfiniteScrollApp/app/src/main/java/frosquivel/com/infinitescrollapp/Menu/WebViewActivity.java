package frosquivel.com.infinitescrollapp.Menu;

import android.os.Bundle;
import android.webkit.WebView;

import frosquivel.com.infinitescrollapp.MainActivity;
import frosquivel.com.infinitescrollapp.R;

/**
 * Created by Fabian on 10/06/2017.
 */

public class WebViewActivity extends MainActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_web_view);
        webView = (WebView) this.findViewById(R.id.webView);

        String link = getIntent().getStringExtra("link");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(link);
    }
}
