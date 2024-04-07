package com.example.mobile_w10;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Objects;

public class ShowHeadlines extends AppCompatActivity {
    ArrayList<SingleItem> newsList = new ArrayList<SingleItem>();
    ListView myListView; String urlAddress = "", urlCaption = ""; SingleItem selectedNewsItem;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.list_channel);
        myListView = this.findViewById(R.id.myListView);
// find out which intent is calling us & grab data bundle holding selected url & caption sent to us
        Intent callingIntent = getIntent();
        Bundle myBundle = callingIntent.getExtras();
        urlAddress = myBundle.getString("urlAddress"); urlCaption = myBundle.getString("urlCaption");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String newsName = getIntent().getStringExtra("newsName");
        getSupportActionBar().setTitle("Items in channel " + urlCaption + " - " + newsName);

        myListView.setOnItemClickListener((av, v, index, id) -> {
            selectedNewsItem = newsList.get(index);
            showNiceDialogBox(selectedNewsItem);
        });
// get stories for the selected news option
        DownloadRssFeed downloader = new DownloadRssFeed(ShowHeadlines.this);
        downloader.execute(urlAddress, urlCaption);
    }//onCreate

    public void showNiceDialogBox(SingleItem selectedStoryItem){
        String title = selectedStoryItem.getTitle();
        String description = selectedStoryItem.getDescription();
        if (title.equalsIgnoreCase(description)){ description = ""; }
        try {
            final Uri storyLink = Uri.parse(selectedStoryItem.getLink());
            new AlertDialog.Builder(this)
                    .setTitle(Html.fromHtml(urlCaption))
                    .setMessage(title + "\n\n" + Html.fromHtml(description) + "\n")
                    .setPositiveButton("Close", null)
                    .setNegativeButton("More", (dialog, whichOne) -> {
                        Intent browser = new Intent(Intent.ACTION_VIEW, storyLink);
                        startActivity(browser);
                    })
                    .show();
        } catch (Exception e) {
            Log.e("Error DialogBox", e.getMessage());
        }
    }//showNiceDialogBox
}//ShowHeadlines
