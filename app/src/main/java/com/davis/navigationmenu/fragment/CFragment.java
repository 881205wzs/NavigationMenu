package com.davis.navigationmenu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.davis.navigationmenu.R;


public class CFragment extends android.support.v4.app.Fragment {

    Button btn;
    TextView txt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c, null);
        init(view);
        return view;
    }

    private void init(View view){
        btn = (Button)view.findViewById(R.id.btn_c);
        txt = (TextView)view.findViewById(R.id.txt_c);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setText("Button_C");
            }
        });
    }

    //提示消息
    public void showToast(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }
}
