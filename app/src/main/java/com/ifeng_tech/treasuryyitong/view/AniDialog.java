package com.ifeng_tech.treasuryyitong.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.AnimationDrawable;
import android.view.KeyEvent;
import android.widget.ImageView;

import com.ifeng_tech.treasuryyitong.R;


/**
 * Created by Administrator on 2017/5/27.
 * 描述：进度框（小菊花加载框）
 */
public class AniDialog {

    private Context mContext;
    private Dialog dialog;
    private ImageView commodity_loading1;
    private final AnimationDrawable animationDrawable;

    public AniDialog(Context context, final onKeyCodeBackDownListn onKeyCodeBackDownListn) {
        this.mContext = context;
        dialog = new Dialog(context, R.style.Dialogstyle);
        dialog.setContentView(R.layout.dialog_ani);
//        tv_msg = (TextView) dialog.findViewById(R.id.tv_msg);
        commodity_loading1 = (ImageView) dialog.findViewById(R.id.commodity_loading1);
        commodity_loading1.setBackgroundResource(R.drawable.custom_progress_bar);

        animationDrawable = (AnimationDrawable) commodity_loading1.getBackground();

        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    if (onKeyCodeBackDownListn != null) {
                        onKeyCodeBackDownListn.onKeyCodeBackDown(keyCode, event);
                        return true;
                    }
                }
                return false;
            }
        });
    }



    public void setCancelable(boolean flag) {
        dialog.setCancelable(flag);//不可以返回取消
    }

    public void show() {
        if (dialog != null) {
            if (!dialog.isShowing()) {
                dialog.show();
                commodity_loading1.post(new Runnable() {

                    @Override
                    public void run() {
                        animationDrawable.start();
                    }
                });
            }
        }
    }

    public void dismiss() {
        if (dialog != null) {
            if (dialog.isShowing()) {
                dialog.dismiss();
                animationDrawable.stop();
            }
        }
    }

    /**
     * 弹窗是否在显示
     *
     * @return
     */
    public boolean isShow() {
        return dialog.isShowing();
    }

//    public void setMsg(String msg) {
//        this.tv_msg.setText(msg);
//    }

    public interface onKeyCodeBackDownListn {
        void onKeyCodeBackDown(int keyCode, KeyEvent event);
    }
}
