package cn.wenbo_lee.androidnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cn.wenbo_lee.androidnews.R;
import cn.wenbo_lee.androidnews.api.pojo.MyNews;


/**
 * Created by Administrator on 2017/4/23.
 */

public class MyNewsListAdapter extends RecyclerView.Adapter<MyNewsListAdapter.MyViewHolder> {

    private static final String TAG = "MyNewsListAdapter";
    List<MyNews.ResultBean.DataBean> dataBeen;
    private Context context;
    private MyItemClickListener mItemClickListener;
    private MyItemLongClickListener mItemLongClickListener;

    public MyNewsListAdapter(List<MyNews.ResultBean.DataBean> dataBeen, Context context) {
        this.dataBeen = dataBeen;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.item_news_list, parent, false);
        MyViewHolder holder = new MyViewHolder(inflate, mItemClickListener, mItemLongClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MyNews.ResultBean.DataBean data = dataBeen.get(position);
        holder.title.setText(data.getTitle());
        holder.date.setText(data.getDate());
        holder.author_name.setText(data.getAuthor_name());
        switch (thumNumber(data)) {
            case 3:
                holder.pic_1.setScaleType(ImageView.ScaleType.FIT_START);
                holder.pic_2.setScaleType(ImageView.ScaleType.FIT_START);
                holder.pic_3.setScaleType(ImageView.ScaleType.FIT_START);
                Glide.with(context).load(data.getThumbnail_pic_s()).into(holder.pic_1);
                Glide.with(context).load(data.getThumbnail_pic_s02()).into(holder.pic_2);
                Glide.with(context).load(data.getThumbnail_pic_s03()).into(holder.pic_3);
                break;
            case 2:
                holder.pic_3.setVisibility(View.GONE);
                holder.pic_1.setScaleType(ImageView.ScaleType.FIT_START);
                holder.pic_2.setScaleType(ImageView.ScaleType.FIT_START);
                Glide.with(context).load(data.getThumbnail_pic_s()).into(holder.pic_1);
                Glide.with(context).load(data.getThumbnail_pic_s02()).into(holder.pic_2);
                break;
            case 1:
                holder.pic_3.setVisibility(View.GONE);
                holder.pic_2.setVisibility(View.GONE);
                holder.pic_1.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Glide.with(context).load(data.getThumbnail_pic_s()).into(holder.pic_1);
                break;
        }
    }

    private int thumNumber(MyNews.ResultBean.DataBean data) {
        String thumbnail_pic_s02 = data.getThumbnail_pic_s02();
        String thumbnail_pic_s03 = data.getThumbnail_pic_s03();
        if (!TextUtils.isEmpty(thumbnail_pic_s03)) {
            return 3;
        }
        if (!TextUtils.isEmpty(thumbnail_pic_s02)) {
            return 2;
        }
        return 1;
    }

    @Override
    public int getItemCount() {
        return dataBeen.size();
    }

    public interface MyItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface MyItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        MyItemClickListener mListener;
        MyItemLongClickListener mLongClickListener;
        TextView title;
        TextView date;
        TextView author_name;
        ImageView pic_1;
        ImageView pic_2;
        ImageView pic_3;

        public MyViewHolder(View itemView, MyItemClickListener listener, MyItemLongClickListener longClickListener) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            date = (TextView) itemView.findViewById(R.id.date);
            author_name = (TextView) itemView.findViewById(R.id.author_name);
            pic_1 = (ImageView) itemView.findViewById(R.id.pic_1);
            pic_2 = (ImageView) itemView.findViewById(R.id.pic_2);
            pic_3 = (ImageView) itemView.findViewById(R.id.pic_3);
            this.mListener = listener;
            this.mLongClickListener = longClickListener;
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        /**
         * 点击监听
         */
        @Override
        public void onClick(View v) {
            if (mListener != null) {
                mListener.onItemClick(v, getPosition());
            }
        }

        /**
         * 长按监听
         */
        @Override
        public boolean onLongClick(View arg0) {
            if (mLongClickListener != null) {
                mLongClickListener.onItemLongClick(arg0, getPosition());
            }
            return true;
        }

    }

    /**
     * 设置Item点击监听
     *
     * @param listener
     */
    public void setOnItemClickListener(MyItemClickListener listener) {
        this.mItemClickListener = listener;
    }

    public void setOnItemLongClickListener(MyItemLongClickListener listener) {
        this.mItemLongClickListener = listener;
    }
}
