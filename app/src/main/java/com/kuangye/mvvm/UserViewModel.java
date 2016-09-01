package com.kuangye.mvvm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableField;

/**
 * 继承BaseObservable实现双向绑定.
 *      BR.java 是类似 R.java 的资源文件，是 Binding Resources 的缩写，由框架自动生成
 *      BR 中的 id 生成的依据是 @Bindable 修饰的方法名 getXXX() BR.java 中就立即生成常量 BR.xXX
 */
public class UserViewModel extends BaseObservable{
    private @Bindable String firstName;
    private @Bindable String lastName;
    private boolean isStudent;

    /**使用 ObservableFields 这些变量会自动触发值改变事件，使用 get 和 set 来访问*/
    public ObservableField<String> sex = new ObservableField<>();

    /**
     * ObservableByte / ObservableChar / ObservableShort / ObservableInt /ObservableLong /
     * ObservableFloat / ObservableDouble / ObservableBoolean / ObservableParcelable 为基本数据类型
     * ObservableField<T> 对应引用类型
     * 这样就没有set/get方法了
     * */
    public ObservableArrayMap<String,Object> hobby = new ObservableArrayMap<>();


    /**ObservableArrayList 的使用*/
    public ObservableArrayList<String> info = new ObservableArrayList<>();


    public UserViewModel(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Bindable
    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean student) {
        isStudent = student;
        /**当数据发生变化时还是需要手动发出通知。
         * 通过调用 notifyPropertyChanged(BR.student) 可以通知系统 BR.student 这个 entry 的数据已经发生变化，
         * 需要更新 UI*/
        notifyPropertyChanged(com.kuangye.mvvm.BR.student);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
        notifyPropertyChanged(com.kuangye.mvvm.BR.firstName);
        notifyPropertyChanged(com.kuangye.mvvm.BR.displayName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        notifyPropertyChanged(com.kuangye.mvvm.BR.lastName);
        notifyPropertyChanged(com.kuangye.mvvm.BR.displayName);
    }

    @Bindable
    public String getDisplayName(){
        return firstName + "." + lastName;
    }
}
