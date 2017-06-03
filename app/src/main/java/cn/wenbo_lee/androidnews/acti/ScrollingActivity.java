package cn.wenbo_lee.androidnews.acti;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.tencent.smtt.sdk.WebView;

import cn.wenbo_lee.androidnews.R;
import cn.wenbo_lee.androidnews.api.pojo.MyNews;


public class ScrollingActivity extends AppCompatActivity {

    private static final String TAG = "ScrollingActivity";
    private MyNews.ResultBean.DataBean dataBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataBean = (MyNews.ResultBean.DataBean) getIntent().getSerializableExtra("bean");

        toolbar.setTitle(dataBean.getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "收藏成功", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        WebView webX5 = (WebView) findViewById(R.id.webX52);
        MyNews.ResultBean.DataBean bean = (MyNews.ResultBean.DataBean) getIntent().getSerializableExtra("bean");
        Log.e(TAG, "bean " + bean);
        webX5.loadUrl(bean.getUrl());
    }
}
