package com.sonaradar.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sonaradar.calculator.webservice.cmd;
import com.sonaradar.calculator.webservice.structure.stu_checkVersion;

public class elsePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //页面初始化代码
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_else_page);
        //安卓SDK大于9时请求网络严苛模式可能会导致socket发不出去，修改policy适用于数据请求量小的应用
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        //显示当前版本
        TextView txt_version = (TextView)findViewById(R.id.txt_version);
        txt_version.setText(String.valueOf(cmd.myVersion));
        //检测版本按钮
        Button btn_update = (Button) findViewById(R.id.btn_update);
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stu_checkVersion sc = cmd.checkVersion();
                cmd.da = sc.downloadAddress;
                //检测到新版本
                if(sc.latestVersionCode>cmd.myVersion){
                    AlertDialog.Builder builder = new AlertDialog.Builder(elsePage.this);
                    builder.setTitle("检测到新版本!");
                    builder.setMessage("当前版本:" + cmd.myVersion + "\n" +
                            "最新版本:" + sc.latestVersionCode + "\n" +
                            "最低可登录版本:" + sc.requiredOldestVersionCode + "\n" +
                            "为了保证您的正常使用,请更新软件!");
                    builder.setPositiveButton("更新", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //跳转到更新页面
                            Intent it = new Intent(getApplicationContext(), update_page.class);//启动MainActivity
                            startActivity(it);
                        }
                    });
                    builder.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    });
                    builder.show();
                }else{
                    //是最新版本
                    AlertDialog.Builder builder = new AlertDialog.Builder(elsePage.this);
                    builder.setTitle("您的软件已是最新版本!");
                    builder.setMessage("当前版本:" + cmd.myVersion + "\n" +
                            "最低可登录版本:" + sc.requiredOldestVersionCode + "\n" +
                            "最新版本:" + sc.latestVersionCode + "\n");
                    builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}});
                    builder.show();
                }
            }
        });

    }
}