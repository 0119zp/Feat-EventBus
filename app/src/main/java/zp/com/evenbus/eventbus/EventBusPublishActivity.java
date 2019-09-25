package zp.com.evenbus.eventbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import org.greenrobot.eventbus.EventBus;
import zp.com.evenbus.EventActionInfo;
import zp.com.evenbus.EventBusContent;
import zp.com.evenbus.R;

/**
 * @author zpan
 * @date 2019/9/25 15:13
 *
 * description: 发布消息
 */
public class EventBusPublishActivity extends AppCompatActivity {

    public static final String BUNDLE_PARAMS = "bundle_params";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventbus_publish);
    }

    /**
     * 主线程发布消息
     */
    public void publishMsg(View view) {
        EventActionInfo info = new EventActionInfo(EventBusContent.EVENT_BUS_MAIN_THREAD);
        info.intValue = 0;
        info.stringValue = "stringValue：主线程发布消息";
        EventBusBean bean = new EventBusBean();
        bean.setMsg("EventBusBean：传递的参数");
        info.obj = bean;
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_PARAMS, "Bundle：传递的参数");
        info.bundle = bundle;
        EventBus.getDefault().post(info);
    }

}
