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
import com.davis.navigationmenu.fragment.EFragment;
import com.davis.ui.navigation.view.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private TextView txt_title;
    private String[] tabText = {"首页", "发现", "发布", "消息", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.index, R.mipmap.find, R.mipmap.add_image, R.mipmap.message, R.mipmap.me};
    //选中时icon
    private int[] selectIcon = {R.mipmap.index_selected, R.mipmap.find_selected, R.mipmap.add_image, R.mipmap.message_selected, R.mipmap.me_selected};

    private List<Fragment> fragments = new ArrayList<>();

    private Handler mHandler = new Handler();

    private boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        init();
    }

    private void init(){
        navigationView = findViewById(R.id.navigation);

        txt_title = (TextView)findViewById(R.id.txt_title);

        fragments.add(new AFragment());
        fragments.add(new BFragment());
        fragments.add(new CFragment());
        fragments.add(new DFragment());
        fragments.add(new EFragment());

        navigationView.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .canScroll(true)
                .addLayoutRule(NavigationView.RULE_BOTTOM)
                .addLayoutBottom(0)
                .addAlignBottom(true)
                .addAsFragment(true)
                .mode(NavigationView.MODE_ADD)
                .onTabClickListener(new NavigationView.OnTabClickListener() {
                    @Override
                    public boolean onTabClickEvent(View view, int position) {
                        Log.e("onTabClickEvent", position + "");
                        if (position == 2) {
                            mHandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    //＋ 旋转动画
                                    if (flag) {
                                        navigationView.getAddImage().animate().rotation(45).setDuration(400);
                                    } else {
                                        navigationView.getAddImage().animate().rotation(0).setDuration(400);
                                    }
                                    flag = !flag;
                                }
                            });
                        }
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
