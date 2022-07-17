package com.sonaradar.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.sonaradar.calculator.webservice.cmd;
import com.sonaradar.calculator.webservice.structure.stu_checkVersion;
import com.sonaradar.calculator.webservice.structure.stu_isAllowUsing;
//这个页面是启动页，别问，就是用来水的
public class startPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        //检测是否允许使用
        stu_isAllowUsing si = cmd.isAllowUsing(cmd.myVersion);
        if(si.isAllowUsing==true){
            //不能使用执行下面步骤
            AlertDialog.Builder builder = new AlertDialog.Builder(startPage.this);
            builder.setTitle("服务器拒绝登录");
            builder.setMessage("原因:" + si.reason);
            builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
                }
            });
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(final DialogInterface arg0) {
                    System.exit(0);
                }
            });
            builder.show();
            return;
        }
        //检测可用的最低版本，判断能否使用
        stu_checkVersion sc = cmd.checkVersion();
        cmd.da = sc.downloadAddress;
        if(sc.requiredOldestVersionCode>cmd.myVersion){
            Thread myThread = new Thread() {//创建子线程
                @Override
                public void run() {
                    try {
                        sleep(3000);
                        Intent it = new Intent(getApplicationContext(), update_page.class);//启动MainActivity
                        startActivity(it);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            };
            myThread.start();
            return;
        }
        //载入到计算器页面
        Thread myThread = new Thread() {//创建子线程
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent it = new Intent(getApplicationContext(), MainActivity.class);//启动MainActivity
                    startActivity(it);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}