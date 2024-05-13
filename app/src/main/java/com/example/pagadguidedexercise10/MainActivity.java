package com.example.pagadguidedexercise10;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    AutoCompleteTextView txtAutoComplete;
    ArrayAdapter adapter, songAdapter;
    Button btnOpenURL;
    ListView listView;
    String[] urls = {"https://www.youtube.com/watch_popup?v=FEacDWPWfJU",
            "https://www.youtube.com/watch_popup?v=jcz0YxYl6Ac",
            "https://www.youtube.com/watch_popup?v=G0wOOlwXLgA",
            "https://www.youtube.com/watch_popup?v=2aW7HweAf3o",
            "https://www.youtube.com/watch_popup?v=2PMnJ_Luk_o",
            "https://www.youtube.com/watch_popup?v=2aW7HweAf3o",
            "https://www.youtube.com/watch_popup?v=HoLhKJuGhK0",
            "https://www.youtube.com/watch_popup?v=l8pEjmZVx3k",
            "https://www.youtube.com/watch_popup?v=QFdkM40KOhE",
            "https://www.youtube.com/watch_popup?v=k0xGxnZFNYs"
            };

    String[] songTitles = {"Speak to Me",
            "Breathe (In the Air)",
            "On the Run",
            "Time",
            "The Great Gig in the Sky",
            "Money",
            "Us and Them",
            "Any Colour You Like",
            "Brain Damage",
            "Eclipse"
            };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        txtAutoComplete = findViewById(R.id.txtAutoComplete);
        btnOpenURL = findViewById(R.id.btnOpenURL);
        listView = findViewById(R.id.listView);

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,songTitles);
        txtAutoComplete.setThreshold(2);
        txtAutoComplete.setAdapter(adapter);

        songAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, songTitles);
        listView.setAdapter(songAdapter);


        initializeWebView();
        loadWebURL();

    }

    public void initializeWebView(){
        webView.getSettings().setLoadsImagesAutomatically(true);
        // enabled java script
        webView.getSettings().setJavaScriptEnabled(true);
        // Android webview launches browser when calling loadurl
        webView.setWebViewClient(new WebViewClient());
        // enabled Scrollbar
        webView.setScrollBarStyle(webView.SCROLLBARS_INSIDE_OVERLAY);
    }

    public void loadWebURL(){
        btnOpenURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String song = txtAutoComplete.getText().toString();
                String url = "";

                for(int i = 0; i < songTitles.length; i++){
                    if(song.compareTo(songTitles[i]) == 0){
                        url = urls[i];
                    }
                }

                if(url == ""){
                    Toast.makeText(getApplicationContext(), "There is no such song in the album.", Toast.LENGTH_SHORT).show();
                }
                else{
                    webView.loadUrl(url);
                }
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) parent.getItemAtPosition(position);
                String url = "";

                for(int j = 0; j < songTitles.length;j++){
                    if(selected.compareTo(songTitles[j]) == 0){
                        url = urls[j];
                    }
                }
                webView.loadUrl(url);
            }
        });
    }
}