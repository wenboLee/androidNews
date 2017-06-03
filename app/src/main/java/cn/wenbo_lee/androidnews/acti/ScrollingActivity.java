package cn.wenbo_lee.androidnews.acti;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.tencent.smtt.sdk.WebView;

import org.litepal.crud.DataSupport;

import java.util.List;

import cn.wenbo_lee.androidnews.R;
import cn.wenbo_lee.androidnews.api.pojo.MyNews;
import cn.wenbo_lee.androidnews.entity.NewsCollectionInfo;
import cn.wenbo_lee.androidnews.utils.SharePreferenceUtil;


public class ScrollingActivity extends AppCompatActivity {

    private static final String TAG = "ScrollingActivity";
    private MyNews.ResultBean.DataBean dataBean;

    private FloatingActionButton collectIcon;
    private Toolbar toolbar;
    private WebView webX5;

    private boolean isCollect = false;

    private NewsCollectionInfo collectionInfo;
    private SharePreferenceUtil sharePreferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        initView();
        initData();
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        collectIcon = (FloatingActionButton) findViewById(R.id.collectIcon);
        collectIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if (isCollect) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScrollingActivity.this);
                    builder.setTitle(getString(R.string.tips));
                    builder.setMessage(getString(R.string.cancelCollect));
                    builder.setPositiveButton(getString(R.string.confirm), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //取消收藏
                            noCollect();
                            List<NewsCollectionInfo> newsCollectionInfos = DataSupport.where("uniquekey = ? ", dataBean.getUniquekey()).find(NewsCollectionInfo.class);
                            if (newsCollectionInfos != null && !newsCollectionInfos.isEmpty()) {
                                NewsCollectionInfo collectionInfo = newsCollectionInfos.get(0);
                                if (collectionInfo.delete() == 1)
                                    Snackbar.make(view, "取消收藏成功", Snackbar.LENGTH_LONG).show();
                            }
                        }
                    });
                    builder.create().show();
                } else {
                    //收藏
                    String username = sharePreferenceUtil.getString("username", "");
                    if (TextUtils.isEmpty(username)){
                        //没有登录
                        startActivity(new Intent(ScrollingActivity.this,LoginActivity.class));
                    }else {
                        collect();
                        collectionInfo.setUsername(username);
                        if (collectionInfo.save())
                            Snackbar.make(view, "收藏成功", Snackbar.LENGTH_LONG).show();
                    }
                }
            }
        });

        webX5 = (WebView) findViewById(R.id.webX52);
    }

    private void initData() {
        dataBean = (MyNews.ResultBean.DataBean) getIntent().getSerializableExtra("bean");
        toolbar.setTitle(dataBean.getTitle());

        sharePreferenceUtil = SharePreferenceUtil.getInstance(this, "login");

        Log.e(TAG, "dataBean " + dataBean);
        collectionInfo = new NewsCollectionInfo(dataBean);
        webX5.loadUrl(dataBean.getUrl());

        List<NewsCollectionInfo> newsCollectionInfos = DataSupport.where("uniquekey = ? ", dataBean.getUniquekey()).find(NewsCollectionInfo.class);
        if (newsCollectionInfos != null && !newsCollectionInfos.isEmpty()) {
            //收藏过了
            collect();
        } else {
            //没有收藏
            noCollect();
        }
    }

    private void collect() {
        isCollect = true;
        collectIcon.setImageResource(R.drawable.ic_star_white_48dp);
    }

    private void noCollect() {
        isCollect = false;
        collectIcon.setImageResource(R.drawable.ic_star_border_white_48dp);
    }
}
