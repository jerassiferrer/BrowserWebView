package com.mobileappscompany.trainning.tareanavegadorconbotones;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.webkit.WebChromeClient;
import android.widget.Button;
import android.widget.EditText;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity {
     WebView browser;
    ProgressBar progressBar;
    EditText url;
    String laurl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //creamos el webview
        browser = (WebView)findViewById(R.id.webkit);

        //habilitamos javascript y el zoom
        browser.getSettings().setJavaScriptEnabled(true);
        browser.getSettings().setBuiltInZoomControls(true);

        laurl = "http://mobileappscompany.com/";




        browser.loadUrl(laurl);

        browser.setWebViewClient(new WebViewClient() {
            // evita que los enlaces se abran fuera nuestra app en el navegador de android
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

        });

// Texto del URL
        url = (EditText) findViewById(R.id.url);
        url.setText("http://");
//Barra de progreso
        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        browser.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progress) {
                progressBar.setProgress(0);
                progressBar.setVisibility(View.VISIBLE);
                MainActivity.this.setProgress(progress * 1000);

                progressBar.incrementProgressBy(progress);

                if (progress == 100) {
                    progressBar.setVisibility(View.GONE);
                }
            }
        });




    }

// NAVEGAcion>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>WEB

    public void ir(View view)
    {
        // oculta el teclado al pulsar el botón
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(url.getWindowToken(), 0);

        // he observado que si se pulsa "Ir" sin modificarse la url no se
        // ejecuta el método onPageStarted, así que nos aseguramos
        // que siempre que se cargue una url, aunque sea la que se está
        // mostrando, se active el botón "detener"


        browser.loadUrl(url.getText().toString());

    }

    public void anterior(View view)
    {
        browser.goBack();
    }

    public void siguiente(View view)
    {
        browser.goForward();
    }



}
