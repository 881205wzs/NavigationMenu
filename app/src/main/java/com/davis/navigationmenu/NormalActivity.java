package com.davis.navigationmenu;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.davis.navigationmenu.fragment.AFragment;
import com.davis.navigationmenu.fragment.BFragment;
import com.davis.navigationmenu.fragment.CFragment;
import com.davis.navigationmenu.fragment.DFragment;
import com.davis.ui.navigation.view.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class NormalActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private String[] tabText = {"首页", "发现", "消息", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.index, R.mipmap.find, R.mipmap.message, R.mipmap.me};
    //选中时icon
    private int[] selectIcon = {R.mipmap.index_selected, R.mipmap.find_selected, R.mipmap.message_selected, R.mipmap.me_selected};

    private List<Fragment> fragments = new ArrayList<>();

    private TextView txt_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        init();
    }

    private void init(){
        navigationView = findViewById(R.id.navigation);
        txt_title = (TextView)findViewById(R.id.txt_title);

        fragments.add(new AFragment());
        fragments.add(new BFragment());
        fragments.add(new CFragment());
        fragments.add(new DFragment());

        navigationView.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .canScroll(true)
                .onTabClickListener(new NavigationView.OnTabClickListener() {
                    @Override
                    public boolean onTabClickEvent(View view, int position) {
                        return false;
                    }

                    @Override
                    public void onTabSelectedEvent(int position) {
                        txt_title.setText(tabText[position]);
                    }
                })
                .build();
    }
}
