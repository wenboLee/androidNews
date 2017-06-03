package cn.wenbo_lee.androidnews.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        public MyViewHolder(View itemView, MyItemClickListener listener, MyItemLongClickListener longClickListener) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            date = (TextView) itemView.findViewById(R.id.date);
            author_name = (TextView) itemView.findViewById(R.id.author_name);
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
