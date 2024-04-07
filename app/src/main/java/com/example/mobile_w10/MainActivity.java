package com.example.mobile_w10;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int[] buttonImages = {R.drawable.npr_logo, R.drawable.thanhnien_logo, R.drawable.vietnamnet_logo, R.drawable.vnexpress_logo};
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
            return 4;
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
                button.setLayoutParams(new GridView.LayoutParams(new GridView.LayoutParams(420, 420)));
                button.setPadding(18, 18, 18, 18);
            } else {
                button = (Button) convertView;
            }

            button.setBackgroundResource(buttonImages[position]);
            button.setWidth(100);
            button.setHeight(100);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, ShowChannels.class);
                    mContext.startActivity(intent);
                }
            });

            return button;
        }
    }
}
