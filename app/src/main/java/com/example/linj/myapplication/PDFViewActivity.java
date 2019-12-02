package com.example.linj.myapplication;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import es.voghdev.pdfviewpager.library.RemotePDFViewPager;
import es.voghdev.pdfviewpager.library.adapter.PDFPagerAdapter;
import es.voghdev.pdfviewpager.library.remote.DownloadFile;
import es.voghdev.pdfviewpager.library.util.FileUtil;

/**
 * @author JLin
 * @date 2019/12/2
 * @describe pdf显示 用列表式显示
 */
public class PDFViewActivity extends Activity implements DownloadFile.Listener {

    private RelativeLayout pdf_root;
    private ProgressBar pb_bar;
    private RemotePDFViewPager remotePDFViewPager;
    private String mUrl = "https://partners.adobe.com/public/developer/en/xml/AdobeXMLFormsSamples.pdf";
    private PDFPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfview);
        initView();
        setDownloadListener();
    }


    protected void initView() {
        pdf_root = findViewById(R.id.remote_pdf_root);
        pb_bar = findViewById(R.id.pb_bar);
    }

    /*设置监听*/
    protected void setDownloadListener() {
        final DownloadFile.Listener listener = this;
        remotePDFViewPager = new RemotePDFViewPager(this, mUrl, listener);
        remotePDFViewPager.setId(R.id.pdfViewPager);
    }

    /*加载成功调用*/
    @Override
    public void onSuccess(String url, String destinationPath) {
        pb_bar.setVisibility(View.GONE);
        adapter = new PDFPagerAdapter(this, FileUtil.extractFileNameFromURL(url));
        remotePDFViewPager.setAdapter(adapter);
        updateLayout();
    }

    /*更新视图*/
    private void updateLayout() {
        pdf_root.removeAllViewsInLayout();
        pdf_root.addView(remotePDFViewPager, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    /*加载失败调用*/
    @Override
    public void onFailure(Exception e) {
        pb_bar.setVisibility(View.GONE);
    }

    @Override
    public void onProgressUpdate(int progress, int total) {

    }
}
