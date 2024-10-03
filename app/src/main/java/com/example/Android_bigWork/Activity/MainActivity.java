package com.example.Android_bigWork.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
//import com.example.Android_bigWork.R;

import com.example.Android_bigWork.Entity.Person;
import com.example.Android_bigWork.Fragments.DishMenuFragment;

import com.example.Android_bigWork.Fragments.OrderFragment;
import com.example.Android_bigWork.Fragments.SettingFragment;
import com.example.Android_bigWork.R;

import java.util.ArrayList;

/**
 * @Author Yikuanzz
 * @Type MainActivity
 * @Dec 主界面
 * **/

public class MainActivity extends AppCompatActivity {

    // 导航栏、碎片管理三个页面
    private final String TAG = "MainActivity";
    private BottomNavigationBar bottomNavigationBar;
    private ArrayList<Fragment> fragmentArrayList;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    private Person user;    // 从登录界面传来的用户信息

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取到fragment的管理对象
        fragmentManager = getSupportFragmentManager();

        // init FragmentArrayList
        initFragmentArrayList();

        // init BottomNavigationBar
        initBottomNavigationBar();

        // init FragmentTransaction and select the first fragment to show
        initFragmentTransaction();

        // 隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // 获取从登录界面传来的数据
        Intent initIntent = getIntent();
        if (initIntent.getExtras() != null) {
            //获取Bundle数据
            Bundle bundle = initIntent.getExtras();
            //获取Bundle中的数据
            user = (Person) bundle.getSerializable("user");

        }
    }

    // initFragmentTransaction 初始化页面
    private void initFragmentTransaction() {
        // 开启事务
        fragmentTransaction = fragmentManager.beginTransaction();
        for (int i = 0; i < fragmentArrayList.size(); i++) {
            fragmentTransaction.add(R.id.fragmentContainer, fragmentArrayList.get(i));
            fragmentTransaction.hide(fragmentArrayList.get(i));
        }
        fragmentTransaction.show(fragmentArrayList.get(0));
        // commit FragmentTransaction to apply changes
        fragmentTransaction.commit();
    }

    // initFragmentArrayList 初始化碎片
    private void initFragmentArrayList() {

        // 传递用户数据
        fragmentArrayList = new ArrayList<>();
        Bundle bundle = new Bundle();
        bundle.putSerializable("user", user);

        // 菜单碎片
        DishMenuFragment dishMenuFragment = new DishMenuFragment();
        dishMenuFragment.setArguments(bundle);
        fragmentArrayList.add(dishMenuFragment);

        // 订单碎片
        OrderFragment orderFragment = new OrderFragment();
        orderFragment.setArguments(bundle);
        fragmentArrayList.add(orderFragment);

        // 设置碎片
        SettingFragment settingFragment = new SettingFragment();
        settingFragment.setArguments(bundle);
        fragmentArrayList.add(settingFragment);

    }

    // initBottomNavigationBar 初始化底部导航栏
    private void initBottomNavigationBar() {
        // 绑定导航栏控件
        bottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottomNavigationBar);
        bottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.icon_home, R.string.menu))
                .addItem(new BottomNavigationItem(R.drawable.icon_order, R.string.orders))
                .addItem(new BottomNavigationItem(R.drawable.icon_setting, R.string.settings))
                .setFirstSelectedPosition(0)
                .initialise();

        // BottomNavigationBar 的点击监听器
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Log.d(TAG, "onTabSelected: " + position);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.show(fragmentArrayList.get(position));
                fragmentTransaction.commit();
            }

            @Override
            public void onTabUnselected(int position) {
                Log.d(TAG, "onTabUnselected: " + position);
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.hide(fragmentArrayList.get(position));
                fragmentTransaction.commit();
            }

            @Override
            public void onTabReselected(int position) {
                Log.d(TAG, "onTabReselected: " + position);
            }
        });

        // 监听 BottomNavigationBar 的宽高
        bottomNavigationBar.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {

                int width = bottomNavigationBar.getMeasuredWidth();
                int height = bottomNavigationBar.getMeasuredHeight();
                Log.d(TAG, "onLayoutChange: BottomNavigationBar (width,height)=(" + width + "," + height + ")");

            }
        });
    }
}