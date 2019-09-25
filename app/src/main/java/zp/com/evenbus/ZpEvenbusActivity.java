package zp.com.evenbus;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2017/10/22 0022.
 */

public class ZpEvenbusActivity extends Activity{

    private LinearLayout llayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evenbus);
        // 注册EvenBus
        EventBus.getDefault().register(this);

        initView();

    }

    private void initView() {
        llayout = findViewById(R.id.llayout);

        ZpEvenbusModle zpEvenbusModle = new ZpEvenbusModle(this,"evenbus", "2017.10.22", "记录学习日志");
        zpEvenbusModle.setView(llayout);
    }

    @Subscribe(threadMode = ThreadMode.MAIN,priority = 100) //在ui线程执行，优先级为100
    public void onEvent(ZpEventActionInfo info){
        if (ZpEventContent.ZP_MSG.equals(info.getAction())){
            String msg = info.msg;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(msg);
            builder.show();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 反注册EvenBus
        EventBus.getDefault().unregister(this);
    }
}
