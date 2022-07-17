package com.sonaradar.calculator;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sonaradar.calculator.webservice.cmd;
//这个页面是用来升级的，很简单的
public class update_page extends AppCompatActivity {
    public static String downaddress = cmd.da;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_page);
        Button qust_btn_update = (Button) findViewById(R.id.button5);
        Button qust_btn_exitupdate = (Button) findViewById(R.id.button6);
        qust_btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent= new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(downaddress);
                    intent.setData(content_url);
                    startActivity(intent);
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(),"无法打开网站:" + downaddress,Toast.LENGTH_LONG).show();
                }
            }
        });
        qust_btn_exitupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
    }
}