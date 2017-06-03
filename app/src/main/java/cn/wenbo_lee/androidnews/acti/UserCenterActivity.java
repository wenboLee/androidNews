package cn.wenbo_lee.androidnews.acti;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import cn.wenbo_lee.androidnews.R;
import cn.wenbo_lee.androidnews.adapter.MyNewsListAdapter;
import cn.wenbo_lee.androidnews.api.pojo.MyNews;
import cn.wenbo_lee.androidnews.entity.NewsCollectionInfo;
import cn.wenbo_lee.androidnews.utils.SharePreferenceUtil;
import cn.wenbo_lee.androidnews.view.DividerItemDecoration;

public class UserCenterActivity extends AppCompatActivity {

    private static final String TAG = "UserCenterActivity";
    private MyNewsListAdapter myNewsListAdapter;
    private RecyclerView recycleView;

    private List<NewsCollectionInfo> newsCollectionInfoList;  //数据源
    private List<MyNews.ResultBean.DataBean> dataBeanList;  //数据源
    private SharePreferenceUtil sharePreferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
        initView();

        initData();

    }

    private void initData() {
        sharePreferenceUtil = SharePreferenceUtil.getInstance(this, "login");
        String username = sharePreferenceUtil.getString("username", "");

        newsCollectionInfoList = DataSupport.where("username = ? ",username).find(NewsCollectionInfo.class);
        dataBeanList = new ArrayList<>();
        for (NewsCollectionInfo newsCollectionInfo : newsCollectionInfoList){
            MyNews.ResultBean.DataBean dataBean = new MyNews.ResultBean.DataBean(newsCollectionInfo);
            dataBeanList.add(dataBean);
        }

        myNewsListAdapter = new MyNewsListAdapter(dataBeanList, this);
        myNewsListAdapter.setOnItemClickListener(new MyNewsListAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MyNews.ResultBean.DataBean dataBean = dataBeanList.get(position);
                Intent intent = new Intent(UserCenterActivity.this, ScrollingActivity.class);
                intent.putExtra("bean", dataBean);
                startActivity(intent);
            }
        });
        recycleView.setAdapter(myNewsListAdapter);
    }

    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.logout);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharePreferenceUtil.setString("username", "");
                finish();
//                Snackbar.make(view, "退出成功", Snackbar.LENGTH_LONG).show();
            }
        });

        recycleView = (RecyclerView) findViewById(R.id.collection);
        recycleView.setLayoutManager(new LinearLayoutManager(recycleView.getContext()));
        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.addItemDecoration(new DividerItemDecoration(UserCenterActivity.this, DividerItemDecoration.VERTICAL_LIST, 10));
    }

}
