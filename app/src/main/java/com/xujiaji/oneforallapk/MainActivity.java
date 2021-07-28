package com.xujiaji.oneforallapk;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xujiaji.library.LibraryImplementationTest;

public class MainActivity extends AppCompatActivity {

    private TextView mTvVersion;
    private TextView mTvUseJava;
    private ImageView mImgGirl;
    private ImageView mImgAssetsGirl;
    private ImageView mImgNet;
    private TextView mTvQQId;
    private TextView mTvWxId;
    private TextView mTvLibraryText;
    private TextView mTvFlavorMapLibrary;
    private TextView mTvSharedUserIdTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvVersion = findViewById(R.id.tvVersion);
        mTvUseJava = findViewById(R.id.tvUseJava);
        mImgGirl = findViewById(R.id.imgGirl);
        mImgAssetsGirl = findViewById(R.id.imgAssetsGirl);
        mImgNet = findViewById(R.id.imgNet);
        mTvQQId = findViewById(R.id.tvQQId);
        mTvWxId = findViewById(R.id.tvWxId);

        mTvFlavorMapLibrary = findViewById(R.id.tvFlavorMapLibrary);
        mTvLibraryText = findViewById(R.id.tvLibraryText);
        mTvSharedUserIdTest = findViewById(R.id.tvSharedUserIdTest);

        mTvVersion.setText(getString(R.string.version_) + BuildConfig.VERSION_NAME);
        mTvUseJava.setText(getString(R.string.use_java) + Config.CONFIG);

        mImgGirl.setImageResource(R.mipmap.girl);
        Glide.with(this)
                .load("file:///android_asset/img.jpg")
                .into(mImgAssetsGirl);

        Glide.with(this)
                .load(BuildConfig.LOGOURL)
                .into(mImgNet);

        mTvQQId.setText(getString(R.string.qq_id) + BuildConfig.QQ_ID);
        mTvWxId.setText(getString(R.string.wx_id) + BuildConfig.WX_ID);

        mTvLibraryText.setText(getString(R.string.url_text_change) + com.xujiaji.library.BuildConfig.LIBRARY_URL);

        mTvFlavorMapLibrary.setText(getString(R.string.flavor_map_library) + LibraryImplementationTest.getMyName());


        // 测试sharedUserId变更
        PreferenceManager
                .getDefaultSharedPreferences(this)
                .edit()
                .putString("app_pkg", BuildConfig.APPLICATION_ID)
                .commit(); // 将当前的包名保存到SharedPreferences

        // 如果是TwoApk，那么不参与
        if ("com.xujiaji.oneforallapk_three".equals(BuildConfig.APPLICATION_ID)) {
            mTvSharedUserIdTest.setText(getString(R.string.shareduserid_info) + "ThreeApk是被测试对象，请打开其他进行观看该项。FourApk可与之匹配");
            return;
        }

        try {
            // 获取TwoApk中保存的包名
            Context otherApkContext = createPackageContext("com.xujiaji.oneforallapk_three",
                    Context.CONTEXT_INCLUDE_CODE | Context.CONTEXT_IGNORE_SECURITY);

            String str = PreferenceManager.getDefaultSharedPreferences(otherApkContext).getString("app_pkg", null);

            if (str == null) {
                mTvSharedUserIdTest.setText(getString(R.string.shareduserid_info) + "ThreeApk sharedUserId 不匹配");
            } else  {
                mTvSharedUserIdTest.setText(getString(R.string.shareduserid_info)
                        + "匹配成功-"
                        + str);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            mTvSharedUserIdTest.setText(getString(R.string.shareduserid_info) + "ThreeApk sharedUserId 不匹配");
        }
    }
}
