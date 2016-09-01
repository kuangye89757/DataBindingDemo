package com.kuangye.mvvm;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewStub;

import com.kuangye.mvvm.databinding.MainBuilding;
import com.kuangye.mvvm.databinding.ViewStubBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /**Data Binding 为我们生成了 databinding包，以及 ActivityMainBinding类名 根据activity_main.xml生成*/
//    private ActivityMainBinding binding;

    /**Data Binding 为我们生成了 databinding包，以及 MainBuilding类名 根据 <data class="MainBuilding">生成*/
    private MainBuilding binding;

    /**调用Activity中变量  必须是 public static*/
    public static String mName = "我是Activity中的变量";

    /***/
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        /**绑定UserViewModel*/
        final UserViewModel userViewModel = new UserViewModel("micheal","jack");
        userViewModel.setStudent(true);
        userViewModel.sex.set("男");
        userViewModel.hobby.put("hobby","打篮球");
        for(int i = 0 ; i < 3; i++) {
            userViewModel.info.add("info" + i);
        }
        binding.setUserViewModel(userViewModel);

        /**自动生成的 DataBinding 代码会检查 null，避免出现NullPointerException*/
        binding.setUserNull(null);

        /**绑定PeopleViewModel*/
        final PeopleViewModel peopleViewModel = new PeopleViewModel(this);
        peopleViewModel.age.set(18);
        binding.setPeopleViewModel(peopleViewModel);

        /**变化数据*/
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userViewModel.setStudent(false);
                userViewModel.setFirstName("Bill");
                userViewModel.setLastName("Joy");
                userViewModel.sex.set("女");
                userViewModel.hobby.put("hobby","踢足球");
            }
        });



        /**ViewStubProxy不好使*/
        binding.viewStub.setOnInflateListener(new ViewStub.OnInflateListener() {
            @Override
            public void onInflate(ViewStub stub, View inflated) {
                ViewStubBinding stubBinding = DataBindingUtil.bind(inflated);
                UserViewModel viewModel = new UserViewModel("fee","lang");
                stubBinding.setUserViewModel(viewModel);
            }
        });

        /**添加数据*/
        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                peopleViewModel.getNames().add("yanyu cai");
            }
        });


        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<UserViewModel> mData = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            UserViewModel model = new UserViewModel("item"+i,"");
            model.sex.set("女"+i);
            mData.add(model);
        }
        MyAdapter adapter = new MyAdapter(mData);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
