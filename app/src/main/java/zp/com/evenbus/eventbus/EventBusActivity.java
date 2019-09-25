package zp.com.evenbus.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import zp.com.evenbus.EventActionInfo;
import zp.com.evenbus.EventBusContent;
import zp.com.evenbus.R;

/**
 * @author zpan
 */
public class EventBusActivity extends Activity {

    private AppCompatTextView mEventBusDec;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus);
        // 注册EventBus
        EventBus.getDefault().register(this);

        initView();
    }

    private void initView() {
        mEventBusDec = findViewById(R.id.tv_event_bus_dec);
    }

    public void goPublishEventBus(View view) {
        startActivity(new Intent(EventBusActivity.this, EventBusPublishActivity.class));
    }

    /**
     * 在ui线程执行，优先级为100
     */
    @Subscribe(threadMode = ThreadMode.MAIN, priority = 100)
    public void onEvent(EventActionInfo info) {
        if (EventBusContent.EVENT_BUS_MAIN_THREAD.equals(info.getAction())) {
            Log.e("zpan","接收到了 publishMsg 传来的消息");
            EventBusBean bean = (EventBusBean) info.obj;
            StringBuffer sb = new StringBuffer();
            sb.append(info.intValue)
                .append("\n")
                .append(info.stringValue)
                .append("\n")
                .append(bean.getMsg())
                .append("\n")
                .append(info.bundle.getString(EventBusPublishActivity.BUNDLE_PARAMS));

            mEventBusDec.setText(getString(R.string.event_bus_dec, sb.toString()));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 反注册EventBus
        EventBus.getDefault().unregister(this);
    }
}
