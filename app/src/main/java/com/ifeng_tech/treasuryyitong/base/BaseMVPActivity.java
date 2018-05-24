package com.ifeng_tech.treasuryyitong.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.presenter.MyPresenter;
import com.ifeng_tech.treasuryyitong.utils.NetWorkUtil;


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

        setContentView(R.layout.activity_base_mvp);
        myPresenter=initPresenter();

        boolean conn = NetWorkUtil.isConn((Context) this);
        if(!conn){
            NetWorkUtil.showNoNetWorkDlg((Context) this);
        }
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
