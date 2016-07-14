package com.example.administrator.android_tdy;

/**
 * Created by Administrator on 2016/7/13.
 */
import android.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;

/**
 * Created by Administrator on 2016/7/12.
 */
public class FragmentHello extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.hello_course,container,false);
        String[] category_str1 = {"1","2","3","4","5","6","7","8"};
//        whv.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(),category_str1));
        final WheelView whl = (WheelView)view.findViewById(R.id.lilailai);
        whl.setVisibleItems(4);
        whl.setViewAdapter(new ArrayWheelAdapter<String>(getActivity(),category_str1));

        Button b = (Button)view.findViewById(R.id.lilailai2);
        b.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Toast toast = Toast.makeText(getActivity().getApplicationContext(),
                                whl.getCurrentItem()+" ", Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.CENTER, 0, 0);
                        toast.show();
            }
        });


        return view;
    }
}
