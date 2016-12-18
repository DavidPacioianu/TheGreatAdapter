package com.pixelcan.example;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.pixelcan.thegreatadapter.GreatAdapter;
import com.pixelcan.thegreatadapter.items.SimpleViewItem;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private GreatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new GreatAdapter.Builder(MainActivity.this)
                .dividerSizeDp(2)
                .build();

        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
        recyclerView.setAdapter(adapter);
    }

    int hCount = 0;
    int count = 0;
    int fCount = 0;

    @OnClick(R.id.click_me_button)
    public void onClick() {
        Random rand = new Random();
        int spanSize = rand.nextInt() % 3 + 1;

        View view = getLayoutInflater().inflate(R.layout.simple_item, null);
        view.setBackgroundColor(getRandomColor());
        TextView textView = ButterKnife.findById(view, R.id.text_view);

        int type = rand.nextInt() % 3;

        switch (type) {
            case 0:
                textView.setText("Header " + hCount++);
                adapter.addHeader(new SimpleViewItem(spanSize, textView));
                break;
            case 1:
                textView.setText("Item " + count++);
                adapter.add(new SimpleViewItem(spanSize, textView));
                break;
            case 2:
                textView.setText("Footer " + fCount++);
                adapter.addFooter(new SimpleViewItem(spanSize, textView));
                break;
        }
    }

    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}
