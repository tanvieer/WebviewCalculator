package calculator.webview.tanvir.com.webviewcalculator;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.URLUtil;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class WebviewCalculator extends AppCompatActivity {
    
    private WebView webView;
    private final String TAG = "MyWebviewCalculator";
    private ProgressDialog progress;
    private String url;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview_calculator);
        url = getIntent().getExtras().getString("intentUrl");
        webView = (WebView) findViewById(R.id.webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){

            public void onReceivedError(WebView view, int errorCode, String description,String failingUrl) {

                Toast.makeText(WebviewCalculator.this, "Please Check WIFI connection." , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(WebviewCalculator.this, OfflineCalculator.class);
                Log.d(TAG,"offline calculator called");
                Toast.makeText(WebviewCalculator.this, "Offline Calculator Loaded" , Toast.LENGTH_SHORT).show();

                startActivity(intent);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                Toast.makeText(WebviewCalculator.this, "Wifi not connected" , Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(WebviewCalculator.this, OfflineCalculator.class);
                Log.d(TAG,"offline calculator called");

                startActivity(intent);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.d(TAG," onPageStarted");
                progress = new ProgressDialog(WebviewCalculator.this);

                progress.setMessage("Loading Webview Calculator");
                progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progress.setIndeterminate(true);
                progress.show();
            }


            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d(TAG," onPageFinished done");
                progress.hide();
            }
        });
    }
    private boolean isNetworkAvailable() {
        Log.d(TAG, "function called 1");
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        Log.d(TAG, "function called 2");
        try {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            Log.d(TAG, "function called 3" + activeNetworkInfo.getTypeName());
            if (activeNetworkInfo.getTypeName().equalsIgnoreCase("WIFI"))
                if (activeNetworkInfo.isConnected())
                    return true;
        } catch (Exception e) {
            Log.d(TAG, "Exception paise");
            return false;
        }
        return false;
    }

}
