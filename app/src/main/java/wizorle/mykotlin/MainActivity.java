package wizorle.mykotlin;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import wizorle.mykotlin.Test0608.ListViewActivity;
import wizorle.mykotlin.Test0611.NetDataActivity;
import wizorle.mykotlin.Test0612.ViewPagerActivity;
import wizorle.mykotlin.Test0613.ListBaseActivity;
import wizorle.mykotlin.Test0614.RecylerViewActivity;
import wizorle.mykotlin.Test0615.ViewPagerFragActivity;
import wizorle.mykotlin.Test0616.ImageViewPagerActivity;
import wizorle.mykotlin.Test0617.view.MvpTestActivity;
import wizorle.mykotlin.Test0618.TestImageLoadActivity;


/**
 * Created by 何人执笔？ on 2018/6/8.
 * liushengping
 */

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ListViewActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_jhqq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, NetDataActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_viewpager).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ViewPagerActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_listView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ListBaseActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_recyler).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, RecylerViewActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_ViewPager_Fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ViewPagerFragActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_ViewPager_Image).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, ImageViewPagerActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_mvp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, TestImageLoadActivity.class);
                startActivity(intent);
            }
        });
    }
}
