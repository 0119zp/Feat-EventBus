package zp.com.evenbus.rxbus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import zp.com.evenbus.R;

/**
 * @author zpan
 * @date 2019/9/25 14:48
 *
 * description: RxBus 事件分发页面
 */
public class RxBusActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbus);
    }
}
