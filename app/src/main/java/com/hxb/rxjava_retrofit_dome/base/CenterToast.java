package com.hxb.rxjava_retrofit_dome.base;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hxb.rxjava_retrofit_dome.R;

/**
 * 位于屏幕中心，圆角的toast，屏蔽多次显示
 *
 * @author
 */
public class CenterToast {

    private static long oneTime = 0;
    private static long twoTime = 0;
    private static String msg = "";
    private static Context mContext;

    public static void show(String str) {
        mContext = AppManager.appContext();
        if (TextUtils.isEmpty(str))
            return;
        TextView textView = (TextView) View.inflate(mContext, R.layout.toast_view, null);
        textView.setText(str);

        Toast toast = new Toast(mContext);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(textView);

        if (msg.equals(str)) {
            twoTime = System.currentTimeMillis();
            if (twoTime - oneTime > 600) {
                toast.show();
                oneTime = twoTime;
//                LogUtils.e("应该显示"+"相差--"+(twoTime - oneTime )+"  "+ Toast.LENGTH_SHORT);
            }
        } else {
            msg = str;
            toast.show();
            oneTime = System.currentTimeMillis();
        }
        oneTime = twoTime;
    }
}
