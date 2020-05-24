package com.davis.navigationmenu.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.davis.navigationmenu.MoreActivity;
import com.davis.navigationmenu.R;

public class TipFragment extends Fragment implements View.OnClickListener {

    private Button btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tip, null);
        init(view);
        return view;
    }

    private void init(View view){
        btn = (Button)view.findViewById(R.id.btn_1);
        btn.setOnClickListener(this);

        btn = (Button)view.findViewById(R.id.btn_2);
        btn.setOnClickListener(this);

        btn = (Button)view.findViewById(R.id.btn_3);
        btn.setOnClickListener(this);

        btn = (Button)view.findViewById(R.id.btn_4);
        btn.setOnClickListener(this);

        btn = (Button)view.findViewById(R.id.btn_5);
        btn.setOnClickListener(this);
    }

    private void btnClick(int type){
        if(type == 1){
            if(getActivity() instanceof MoreActivity) {
                ((MoreActivity) getActivity()).getNavigationView().setMsgPointCount(2, 109);
                ((MoreActivity) getActivity()).getNavigationView().setMsgPointCount(0, 8);
                ((MoreActivity) getActivity()).getNavigationView().setHintPoint(3, true);
            }
        } else if(type == 2){
            if(getActivity() instanceof MoreActivity) {
                ((MoreActivity) getActivity()).getNavigationView().clearAllHintPoint();
                ((MoreActivity) getActivity()).getNavigationView().clearAllMsgPoint();
            }
        } else if(type == 3){
            if(getActivity() instanceof MoreActivity) {
                ((MoreActivity) getActivity()).getNavigationView().clearMsgPoint(0);
            }
        } else if(type == 4){
            if(getActivity() instanceof MoreActivity) {
                ((MoreActivity) getActivity()).getNavigationView().clearHintPoint(3);
            }
        } else if(type == 5){
            if(getActivity() instanceof MoreActivity) {
                ((MoreActivity) getActivity()).getNavigationView().selectTab(1);
                //((MoreActivity) getActivity()).getNavigationView().getAdapter().getItem(1);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_1:
                btnClick(1);
                break;
            case R.id.btn_2:
                btnClick(2);
                break;
            case R.id.btn_3:
                btnClick(3);
                break;
            case R.id.btn_4:
                btnClick(4);
                break;
            case R.id.btn_5:
                btnClick(5);
                break;
        }
    }
}
