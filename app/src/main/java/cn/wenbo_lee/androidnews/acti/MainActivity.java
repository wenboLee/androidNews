package cn.wenbo_lee.androidnews.acti;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import cn.wenbo_lee.androidnews.adapter.MyTableAdapter;
import cn.wenbo_lee.androidnews.R;
import cn.wenbo_lee.androidnews.utils.SharePreferenceUtil;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    TabLayout tabLayout;
    ViewPager viewPager;

    MyTableAdapter adapter;

    SharePreferenceUtil sharePreferenceUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharePreferenceUtil = SharePreferenceUtil.getInstance(this, "login");
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new MyTableAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabsFromPagerAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_user_center:
                String username = sharePreferenceUtil.getString("username", "");
                Log.e(TAG,"当前用户名： " + username);
                if (!TextUtils.isEmpty(username)) {
                    startActivity(new Intent(this, UserCenterActivity.class));
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
        }
        return true;
    }
}
