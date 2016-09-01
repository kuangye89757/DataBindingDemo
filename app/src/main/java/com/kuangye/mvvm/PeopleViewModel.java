package com.kuangye.mvvm;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableInt;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by wangshijie on 2016/8/30.
 */
public class PeopleViewModel{
    public Context context;
    public ObservableArrayList<String> names = new ObservableArrayList<>();
    public ObservableInt age = new ObservableInt();

    /**
     * 构造器
     *      赋值context和names
     * @param context
     */
    public PeopleViewModel(Context context) {
        this.context = context;
        names.add("linus chen");
        names.add("lin xueyan");
        names.add("zhang xiaona");
        names.add("chen lei");
        names.add("liu yuhong");
    }

    /**
     * 在 layout 中，使用 “app:attr1” 的格式来添加参数，
     * 这些参数会被传递到 @BindingAdapter 修饰的方法中，
     * 方法必须是 public static void 类型
     * @param linearLayout
     * @param names
     * @param context
     */
    @BindingAdapter({"names","context"})
    public static void whateverMethod(ViewGroup linearLayout, ArrayList<String> names,Context context){
        linearLayout.removeAllViews();

        for(String name: names){
            TextView textView = new TextView(context);
            textView.setText(name);
            linearLayout.addView(textView);
        }

    }

    public ObservableArrayList<String> getNames() {
        return names;
    }
}
