package cn.wenbo_lee.androidnews.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import cn.wenbo_lee.androidnews.Constants;
import cn.wenbo_lee.androidnews.frag.NewsFragment;


/**
 * Created by Administrator on 2017/4/23.
 */

public class MyTableAdapter extends FragmentPagerAdapter {

    public final int COUNT = Constants.titles.length;

    private Context context;

    public MyTableAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return NewsFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Constants.titles[position];
    }
}
