package zp.com.evenbus.eventbus;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import zp.com.evenbus.EventActionInfo;
import zp.com.evenbus.EventBusContent;
import zp.com.evenbus.R;

public class EventBusModel {

    private Context context;
    private String s1,s2,s3;


    public EventBusModel(Context context, String s1, String s2, String s3) {
        this.context = context;
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    public void setView(LinearLayout llayout){
        llayout.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_evenbus, null);
        TextView dec = view.findViewById(R.id.tv_dec);
        Button btn = view.findViewById(R.id.btn);

        dec.setText(s1 + "\n" + s2 + "\n" + s3);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventActionInfo info = new EventActionInfo(EventBusContent.ZP_MSG);
                info.msg = "evenbus发送过来的";
                EventBus.getDefault().post(info);
            }
        });

        llayout.addView(view);
    }


}
