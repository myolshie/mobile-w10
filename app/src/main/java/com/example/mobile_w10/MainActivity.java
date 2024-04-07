package com.example.mobile_w10;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

public class MainActivity extends AppCompatActivity {

    private int[] buttonImages = {R.drawable.npr_logo, R.drawable.thanhnien_logo, R.drawable.tuoitre_logo, R.drawable.vnexpress_logo};
    private String[] newsName = {"NPR", "Thanh niên", "Tuổi trẻ", "VNExpress"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = findViewById(R.id.myGridView);
        gridView.setAdapter(new ButtonAdapter(this));
    }

    private class ButtonAdapter extends BaseAdapter {
        private Context mContext;

        public ButtonAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getCount() {
            return buttonImages.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            Button button;
            if (convertView == null) {
                button = new Button(mContext);
                ViewGroup.MarginLayoutParams layoutParams = new ViewGroup.MarginLayoutParams(430, 430);
                layoutParams.setMargins(0, 0, 0, 100);
                button.setLayoutParams(layoutParams);
            } else {
                button = (Button) convertView;
            }

            button.setBackgroundResource(buttonImages[position]);

            button.setOnClickListener(v -> {
                Intent intent = new Intent(mContext, ShowChannels.class);
                intent.putExtra("position", position);
                intent.putExtra("newsName", newsName[position]);
                mContext.startActivity(intent);
            });
            return button;
        }
    }
}
