package com.example.mobile_w10;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class ShowChannels extends AppCompatActivity {
    // Main GUI - A NEWS application based on National Public Radio RSS material
    ArrayAdapter<String> adapterMainSubjects;
    ListView myMainListView;
    Context context;
    SingleItem selectedNewsItem;
    // hard-coding main NEWS categories (TODO: use a resource file)
    String [][] myUrlCaptionMenu = {
            {"https://feeds.npr.org/510289/podcast.xml", "Business"},
            {"https://feeds.npr.org/344098539/podcast.xml", "Comedy"},
            {"https://feeds.npr.org/510308/podcast.xml", "Science"},
            {"https://feeds.npr.org/510298/podcast.xml", "Technology"},
            {"https://feeds.npr.org/510306/podcast.xml", "Music"},
            {"https://feeds.npr.org/510354/podcast.xml", "Kid & family"},
            {"https://feeds.npr.org/510309/podcast.xml", "Society & culture"}
    };
    String [][] ThanhnienUrlCaptionMenu = {
            {"https://thanhnien.vn/rss/thoi-su.rss", "Thời sự"},
            {"https://thanhnien.vn/rss/the-gioi.rss", "Thế giới"},
            {"https://thanhnien.vn/rss/kinh-te.rss", "Kinh tế"},
            {"https://thanhnien.vn/rss/doi-song.rss", "Đời sống"},
            {"https://thanhnien.vn/rss/suc-khoe.rss", "Sức khỏe"},
            {"https://thanhnien.vn/rss/gioi-tre.rss", "Giới trẻ"},
            {"https://thanhnien.vn/rss/giao-duc.rss", "Giáo dục"},
            {"https://thanhnien.vn/rss/du-lich.rss", "Du lịch"},
            {"https://thanhnien.vn/rss/van-hoa.rss", "Văn hóa"}
    };
    String [][] TuoiTreUrlCaptionMenu = {
            {"https://tuoitre.vn/rss/thoi-su.rss", "Thời sự"},
            {"https://tuoitre.vn/rss/the-gioi.rss", "Thế giới"},
            {"https://tuoitre.vn/rss/kinh-doanh.rss", "Kinh doanh"},
            {"https://tuoitre.vn/rss/khoa-hoc.rss", "Khoa học"},
            {"https://tuoitre.vn/rss/suc-khoe.rss", "Sức khỏe"},
            {"https://tuoitre.vn/rss/gioi-tre.rss", "Thể thao"},
            {"https://tuoitre.vn/rss/giao-duc.rss", "Giáo dục"},
            {"https://tuoitre.vn/rss/du-lich.rss", "Du lịch"},
            {"https://tuoitre.vn/rss/van-hoa.rss", "Văn hóa"}
    };
    String [][] VnExpressUrlCaptionMenu = {
            {"https://vnexpress.net/rss/thoi-su.rss", "Thời sự"},
            {"https://vnexpress.net/rss/the-gioi.rss", "Thế giới"},
            {"https://vnexpress.net/rss/kinh-doanh.rss", "Kinh doanh"},
            {"https://vnexpress.net/rss/gia-dinh.rss", "Đời sống"},
            {"https://vnexpress.net/rss/suc-khoe.rss", "Sức khỏe"},
            {"https://vnexpress.net/rss/du-lich.rss", "Du lịch"},
            {"https://vnexpress.net/rss/giao-duc.rss", "Giáo dục"},
            {"https://vnexpress.net/rss/khoa-hoc.rss", "Khoa học"},
            {"https://vnexpress.net/rss/the-thao.rss", "Thể thao"}
    };
        String[] myUrlCaption; String[] myUrlAddress;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); setContentView(R.layout.list_channel);
        int position = getIntent().getIntExtra("position", -1);
        switch (position){
            case 0:
                myUrlAddress = getMyUrlAddress(myUrlCaptionMenu);
                myUrlCaption = getMyUrlCaption(myUrlCaptionMenu);
                break;
            case 1:
                myUrlAddress = getMyUrlAddress(ThanhnienUrlCaptionMenu);
                myUrlCaption = getMyUrlCaption(ThanhnienUrlCaptionMenu);
                break;
            case 2:
                myUrlAddress = getMyUrlAddress(TuoiTreUrlCaptionMenu);
                myUrlCaption = getMyUrlCaption(TuoiTreUrlCaptionMenu);
                break;
            case 3:
                myUrlAddress = getMyUrlAddress(VnExpressUrlCaptionMenu);
                myUrlCaption = getMyUrlCaption(VnExpressUrlCaptionMenu);
                break;
            default:
                break;
        }
        context = getApplicationContext();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String newsName = getIntent().getStringExtra("newsName");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Channels in " + newsName);
        }
// user will tap on a ListView’s row to request category’s headlines
        myMainListView = (ListView)this.findViewById(R.id.myListView);
        myMainListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> _av, View _v, int _index, long _id) {
                String urlAddress = myUrlAddress[_index], urlCaption = myUrlCaption[_index];
//create an Intent to talk to activity: ShowHeadlines
                Intent callShowHeadlines = new Intent(ShowChannels.this, ShowHeadlines.class);
//prepare a Bundle and add the input arguments: url & caption
                Bundle myData = new Bundle();
                myData.putString("urlAddress", urlAddress); myData.putString("urlCaption", urlCaption); myData.putString("newsName", newsName);
                callShowHeadlines.putExtras(myData); startActivity(callShowHeadlines);
            }
        });
// fill up the Main-GUI’s ListView with main news categories
        adapterMainSubjects = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myUrlCaption);
        myMainListView.setAdapter(adapterMainSubjects);
    }
    private String[] getMyUrlCaption(String[][] urlCaptionMenu) {
        myUrlCaption = new String[urlCaptionMenu.length];
        for (int i = 0; i < urlCaptionMenu.length; i++) {
            myUrlCaption[i] = urlCaptionMenu[i][1];
        }
        return myUrlCaption;
    }
    private String[] getMyUrlAddress(String[][] urlCaptionMenu) {
        myUrlAddress = new String[urlCaptionMenu.length];
        for (int i = 0; i < urlCaptionMenu.length; i++) {
            myUrlAddress[i] = urlCaptionMenu[i][0];
        }
        return myUrlAddress;
    }
}
