package zp.com.evenbus.eventbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.widget.Toast;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import zp.com.evenbus.EventActionInfo;
import zp.com.evenbus.EventBusContent;
import zp.com.evenbus.R;

/**
 * @author zpan
 * EventBus是一个Android端优化的publish/subscribe消息总线，简化了应用程序内各组件间、组件与后台线程间的通信。
 * 比如请求网络，等网络返回时通过Handler或Broadcast通知UI，两个Fragment之间需要通过Listener通信
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
        switch (info.getAction()) {
            case EventBusContent.EVENT_BUS_MAIN_THREAD:
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
                break;
            case EventBusContent.EVENT_BUS_THREAD:
                StringBuffer sb1 = new StringBuffer();
                sb1.append(info.intValue).append("\n").append(info.stringValue);

                mEventBusDec.setText(getString(R.string.event_bus_dec, sb1.toString()));
                break;
            default:
                Toast.makeText(this, "没有接收到消息", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 反注册EventBus
        EventBus.getDefault().unregister(this);
    }
}
