package wizorle.mykotlin.Test0618;

import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.util.Util;

import java.lang.ref.WeakReference;

import wizorle.mykotlin.R;
import wizorle.mykotlin.base.BaseActivity;
import wizorle.mykotlin.util.ImageUtil;

/**
 * Created by 何人执笔？ on 2018/6/14.
 * liushengping
 */

public class TestImageLoadActivity extends BaseActivity{

    @Override
    public int getLayout() {
        return R.layout.activity_imgload;
    }

    public ImageView imageView;
    public Button btn_close;
    public WeakReference<TestImageLoadActivity> weakReference;
    @Override
    public void init() {
        weakReference=new WeakReference(TestImageLoadActivity.this);
         imageView=(ImageView)findViewById(R.id.img);
        btn_close=(Button)findViewById(R.id.btn_close);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    ImageUtil.INSTANCE.loadUrlImage(TestImageLoadActivity.this,
                            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2855417086,2712161706&fm=27&gp=0.jpg",
                            imageView,R.mipmap.ic_launcher);
            }
        },4000 );
    }

    @Override
    public void setListener() {
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
