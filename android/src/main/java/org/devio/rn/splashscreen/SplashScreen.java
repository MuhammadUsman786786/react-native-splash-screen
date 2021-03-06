package org.devio.rn.splashscreen;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import java.lang.ref.WeakReference;

/**
 * SplashScreen
 * 启动屏
 * from：http://www.devio.org
 * Author:CrazyCodeBoy
 * GitHub:https://github.com/crazycodeboy
 * Email:crazycodeboy@gmail.com
 */
public class SplashScreen {
  private static Dialog mSplashDialog;
  private static WeakReference<Activity> mActivity;
  private static final String SPLASH_INDEX_KEY = "SplashIndex";
  private static final int[] LAYOUTS = {
    R.layout.launch_screen,
    R.layout.launch_screen_1,
    R.layout.launch_screen_2,
    R.layout.launch_screen_3,
    R.layout.launch_screen_4,
    R.layout.launch_screen_5,
    R.layout.launch_screen_6,
    R.layout.launch_screen_7,
    R.layout.launch_screen_8,
    R.layout.launch_screen_9,
    R.layout.launch_screen_10,
    R.layout.launch_screen_11,
    R.layout.launch_screen_12,
    R.layout.launch_screen_13,
    R.layout.launch_screen_14,
    R.layout.launch_screen_15,
    R.layout.launch_screen_16,
    R.layout.launch_screen_17,
    R.layout.launch_screen_18,
    R.layout.launch_screen_19,
    R.layout.launch_screen_20,
    R.layout.launch_screen_21,
    R.layout.launch_screen_22,
    R.layout.launch_screen_23,
    R.layout.launch_screen_24,
    R.layout.launch_screen_25,
    R.layout.launch_screen_26,
    R.layout.launch_screen_27
  };


  /**
   * 打开启动屏
   */
  public static void show(final Activity activity, final int themeResId) {
    if (activity == null) return;
    mActivity = new WeakReference<Activity>(activity);
    activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        if (!activity.isFinishing()) {
          mSplashDialog = new Dialog(activity, themeResId);
          int currentSplashIndex = getValue(activity);
          mSplashDialog.setContentView(LAYOUTS[currentSplashIndex]);
          mSplashDialog.setCancelable(false);
          if (!mSplashDialog.isShowing()) {
            mSplashDialog.show();
          }
          setValue(activity);
        }
      }
    });
  }

  public static int getValue(final Activity activity) {
    SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
    return sharedPref.getInt(SPLASH_INDEX_KEY, 0);
  }


  public static void setValue(final Activity activity) {
    SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPref.edit();
    editor.putInt(SPLASH_INDEX_KEY, (getValue(activity) + 1) % 28);
    editor.apply();
  }

  /**
   * 打开启动屏
   */
  public static void show(final Activity activity, final boolean fullScreen) {
    int resourceId = fullScreen ? R.style.SplashScreen_Fullscreen : R.style.SplashScreen_SplashTheme;

    show(activity, resourceId);
  }

  /**
   * 打开启动屏
   */
  public static void show(final Activity activity) {
    show(activity, false);
  }

  /**
   * 关闭启动屏
   */
  public static void hide(Activity activity) {
    if (activity == null) {
      if (mActivity == null) {
        return;
      }
      activity = mActivity.get();
    }

    if (activity == null) return;

    final Activity _activity = activity;

    _activity.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        if (mSplashDialog != null && mSplashDialog.isShowing()) {
          boolean isDestroyed = false;

          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            isDestroyed = _activity.isDestroyed();
          }

          if (!_activity.isFinishing() && !isDestroyed) {
            mSplashDialog.dismiss();
          }
          mSplashDialog = null;
        }
      }
    });
  }
}
