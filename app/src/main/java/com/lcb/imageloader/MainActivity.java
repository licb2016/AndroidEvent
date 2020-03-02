package com.lcb.imageloader;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.ItemTouchHelper;

import com.lcb.imageloader.bean.UserInfo;
import com.lcb.imageloader.db.BaseDao;
import com.lcb.imageloader.db.BaseDaoFactory;

import java.util.List;
import java.util.Random;

import okhttp3.OkHttpClient;


@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    public static final String TAG = "MainActivity";


    ItemTouchHelper itemTouchHelper;

    @InjectView(R.id.tv)
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println(tv.getId());

        tv.setText("1324");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click();
            }
        });
    }


    private void testRxJava2() {

    }

    private void okHttpInit() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

    }


    public void click() {
        BaseDao<UserInfo> baseDao = BaseDaoFactory.getInstance().getBaseDao(UserInfo.class);
        baseDao.insert(new UserInfo("1", "LiSi"));
        baseDao.insert(new UserInfo("2", "LiSi"));
        long result = baseDao.insert(new UserInfo(3 + "" + new Random().nextInt(50), "LiSi"));

        System.out.println("------insert----- " + result);

        UserInfo userInfo = new UserInfo();
        userInfo.setId("1");
        userInfo.setName("LiSi");
        List<UserInfo> resultList = baseDao.query(userInfo);
        if (resultList != null) {
            for (UserInfo info : resultList) {
                System.out.println("---query--- " + info.toString());
            }
        }
        System.out.println("------query---end-- ");


        long update = baseDao.update(new UserInfo("332","王五"), userInfo);
        userInfo.setName(null);
        resultList = baseDao.query(userInfo);
        if (resultList != null) {
            for (UserInfo info : resultList) {
                System.out.println(update + "---update--- " + info.toString());
            }
        }
        System.out.println("------update---end-- ");

    }


}
