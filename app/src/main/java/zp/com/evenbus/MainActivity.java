package zp.com.evenbus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import zp.com.evenbus.eventbus.EventBusActivity;
import zp.com.evenbus.liveeventbus.LiveEventBusActivity;
import zp.com.evenbus.rxbus.RxBusActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openEventBus(View view) {
        Intent intent = new Intent(MainActivity.this, EventBusActivity.class);
        startActivity(intent);
    }

    public void openRxBus(View view) {
        Intent intent = new Intent(MainActivity.this, RxBusActivity.class);
        startActivity(intent);
    }

    public void openLiveEventBus(View view) {
        Intent intent = new Intent(MainActivity.this, LiveEventBusActivity.class);
        startActivity(intent);
    }
}
