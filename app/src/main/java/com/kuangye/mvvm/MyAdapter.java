package com.kuangye.mvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by wangshijie on 2016/9/1.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private ArrayList<UserViewModel> mData = new ArrayList<>();

    public MyAdapter(ArrayList<UserViewModel> data) {
        mData.addAll(data);
    }

    /**
     * 调用DataBindingUtil.inflate方法返回了一个ViewDataBinding的对象
     *      我们之前返回的那些都是ViewDataBinding的子类
     * @param viewGroup
     * @param i
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater
                .from(viewGroup.getContext()), R.layout.item, viewGroup, false);

        ViewHolder holder = new ViewHolder(binding.getRoot());
        holder.setBinding(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.getBinding()
                .setVariable(com.kuangye.mvvm.BR.userViewModel, mData.get(i));
        viewHolder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    /**
     * 在这个holder里，我们保存了一个ViewDataBinding对象，并给它提供了Getter和Setter方法
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        public void setBinding(ViewDataBinding binding) {
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return this.binding;
        }
    }
}