package com.ifeng_tech.treasuryyitong.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;


/**
 *
 * @param <V>  v是activity
 * @param <T>  t是p层
 */
public abstract class BaseMVPActivity<V,T extends MyPresenter<V>> extends AppCompatActivity {

    public T myPresenter;
    //将子类共有方法抽成公有，，，因为每一个Activity都会需要new一个P层，所以将new的这个过程，抽成抽象方法才是最佳良策
    public abstract T initPresenter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
//            //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            //状态栏透明颜色
//            getWindow().setStatusBarColor(getResources().getColor(R.color.zhuse));
//        }
//        //为了设置全屏
//        ViewGroup mContentView = (ViewGroup) findViewById(android.R.id.content);
//        View mChildView = mContentView.getChildAt(0);
//        if (mChildView != null) {
//            //注意不是设置 ContentView 的 FitsSystemWindows, 而是设置 ContentView 的第一个子 View . 使其不为系统 View 预留空间.
//            ViewCompat.setFitsSystemWindows(mChildView, true);
//        }

        setContentView(R.layout.activity_base_mvp);
        myPresenter=initPresenter();

    }

    //在获取焦点是将当前对象与p层绑定
    @Override
    protected void onResume() {
        super.onResume();
        myPresenter.attch((V) this);
    }

    //在页面销毁时，调用p层方法，将view对象置空，方便垃圾回收
    @Override
    protected void onDestroy() {
        super.onDestroy();
        myPresenter.setonDestroy();
    }


}
