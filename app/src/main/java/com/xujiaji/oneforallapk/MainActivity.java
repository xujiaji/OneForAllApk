package com.xujiaji.oneforallapk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private TextView mTvVersion;
    private TextView mTvUseJava;
    private ImageView mImgGirl;
    private ImageView mImgAssetsGirl;
    private ImageView mImgNet;
    private TextView mTvQQId;
    private TextView mTvWxId;
    private TextView mTvLibraryText;

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

        mTvLibraryText = findViewById(R.id.tvLibraryText);

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
    }
}
