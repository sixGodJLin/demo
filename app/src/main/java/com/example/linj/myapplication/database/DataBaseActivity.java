package com.example.linj.myapplication.database;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.linj.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DataBaseActivity extends AppCompatActivity {
    SqlDao sqlDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_base);
        ButterKnife.bind(this);

        sqlDao = new SqlDao(this);
    }

    @OnClick({R.id.add_user, R.id.add_record})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.add_user:
                sqlDao.addUser("1", "2", "3");
                break;
            case R.id.add_record:
                sqlDao.addRecord("1", "1");
                break;
        }
    }
}
