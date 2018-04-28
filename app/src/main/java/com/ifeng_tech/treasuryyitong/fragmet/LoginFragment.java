package com.ifeng_tech.treasuryyitong.fragmet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifeng_tech.treasuryyitong.R;
import com.ifeng_tech.treasuryyitong.ui.HomePageActivity;
import com.ifeng_tech.treasuryyitong.ui.LoginActivity;
import com.ifeng_tech.treasuryyitong.utils.MyUtils;
import com.qdong.slide_to_unlock_view.CustomSlideToUnlockView;

/**
 * Created by zzt on 2018/4/28.
 */

public class LoginFragment extends Fragment {

    private LoginActivity activity;
    private CustomSlideToUnlockView slide_to_unlock;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_fragment, container, false);

        slide_to_unlock = view.findViewById(R.id.slide_to_unlock);

        activity = (LoginActivity) getActivity();


        slide_to_unlock.setmCallBack(new CustomSlideToUnlockView.CallBack() {
            @Override
            public void onSlide(int distance) {

            }

            @Override
            public void onUnlocked() {
                MyUtils.setToast("解锁成功");

                slide_to_unlock.setVisibility(View.GONE);
                Intent intent = new Intent(activity, HomePageActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
