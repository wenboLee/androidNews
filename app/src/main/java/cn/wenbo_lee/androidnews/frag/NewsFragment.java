package cn.wenbo_lee.androidnews.frag;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.wenbo_lee.androidnews.Constants;
import cn.wenbo_lee.androidnews.view.DividerItemDecoration;
import cn.wenbo_lee.androidnews.adapter.MyNewsListAdapter;
import cn.wenbo_lee.androidnews.R;
import cn.wenbo_lee.androidnews.acti.ScrollingActivity;
import cn.wenbo_lee.androidnews.api.pojo.MyNews;
import cn.wenbo_lee.androidnews.api.utils.MyNewsUtils;
import cn.wenbo_lee.androidnews.utils.SharePreferenceUtil;
import cn.wenbo_lee.androidnews.utils.TimeUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Administrator on 2017/4/23.
 */

public class NewsFragment extends Fragment {

    private static final String TAG = "NewsFragment";

    public static final String ARGS_PAGE = "args_page";
    private int mPage;
    private RecyclerView recycleView;

    private List<MyNews.ResultBean.DataBean> dataBeanList;  //数据源

    private MyNewsListAdapter myNewsListAdapter;

    private SharePreferenceUtil sharePreferenceUtil;

    private Calendar calendar = Calendar.getInstance();         //当前时间
    private ProgressBar progress;

    public static NewsFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARGS_PAGE, page);
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharePreferenceUtil = SharePreferenceUtil.getInstance(getContext(), "news");
        mPage = getArguments().getInt(ARGS_PAGE);
        dataBeanList = new ArrayList<>();
    }

    @Override
    public void onStart() {
        super.onStart();
        Long time = sharePreferenceUtil.getLong(Constants.types[mPage], 0L);
        /*
         * 刷新新闻的条件：
         * 1，第一次进入，long为0
         * 2，第二次以后，long不为0，且大于5分钟，
         * 3，没有任何内容
         */
        if (time == 0L || TimeUtils.diff5Time(System.currentTimeMillis(), time) || dataBeanList.isEmpty()) {
            initNews();
        }else {
            progress.setVisibility(View.GONE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        recycleView = (RecyclerView) view.findViewById(R.id.recycleView);
        progress = (ProgressBar) view.findViewById(R.id.progress);

        myNewsListAdapter = new MyNewsListAdapter(dataBeanList, getContext());
        myNewsListAdapter.setOnItemClickListener(new MyNewsListAdapter.MyItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                MyNews.ResultBean.DataBean dataBean = dataBeanList.get(position);
                Intent intent = new Intent(getActivity(), ScrollingActivity.class);
                intent.putExtra("bean", dataBean);
                startActivity(intent);
            }
        });
        myNewsListAdapter.setOnItemLongClickListener(new MyNewsListAdapter.MyItemLongClickListener() {
            @Override
            public void onItemLongClick(View view, final int position) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("对本条推荐不感兴趣？");
                builder.setMessage(dataBeanList.get(position).getTitle());
                builder.setNegativeButton("点错", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dataBeanList.remove(position);
                        myNewsListAdapter.notifyDataSetChanged();
                    }
                });
                builder.create().show();
            }
        });
        recycleView.setLayoutManager(new LinearLayoutManager(recycleView.getContext()));
        recycleView.setAdapter(myNewsListAdapter);
        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST,4));
        return view;
    }

    private void initNews() {
        //存储当前时间
        sharePreferenceUtil.setLong(Constants.types[mPage], System.currentTimeMillis());
        Log.e(TAG, "title " + Constants.types[mPage]);
        Call<MyNews> myNews = MyNewsUtils.getMyNews(Constants.types[mPage]);
        myNews.enqueue(new Callback<MyNews>() {
            @Override
            public void onResponse(Call<MyNews> call, Response<MyNews> response) {
                Log.e(TAG, "call " + call.request());
                MyNews body = response.body();
                MyNews.ResultBean result = body.getResult();
                List<MyNews.ResultBean.DataBean> data = result.getData();
                dataBeanList.clear();
                dataBeanList.addAll(data);
                myNewsListAdapter.notifyDataSetChanged();
                progress.setVisibility(View.GONE);
                String reason = body.getReason();
                Log.e(TAG, "reason " + reason);
            }

            @Override
            public void onFailure(Call<MyNews> call, Throwable t) {
                Log.e(TAG, "call " + call.request());
                progress.setVisibility(View.GONE);
            }
        });
    }
}
