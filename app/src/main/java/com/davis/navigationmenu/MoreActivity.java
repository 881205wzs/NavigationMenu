package com.davis.navigationmenu;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.davis.navigationmenu.fragment.AFragment;
import com.davis.navigationmenu.fragment.BFragment;
import com.davis.navigationmenu.fragment.CFragment;
import com.davis.navigationmenu.fragment.DFragment;
import com.davis.navigationmenu.fragment.TipFragment;
import com.davis.ui.navigation.utils.NavigationUtil;
import com.davis.ui.navigation.view.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MoreActivity extends AppCompatActivity {

    private NavigationView navigationView;
    private TextView txt_title;
    private String[] tabText = {"首页", "发现", "", "消息", "我的"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.index, R.mipmap.find, R.mipmap.add_image, R.mipmap.message, R.mipmap.me};
    //选中时icon
    private int[] selectIcon = {R.mipmap.index_selected, R.mipmap.find_selected, R.mipmap.add_image, R.mipmap.message_selected, R.mipmap.me_selected};

    private List<Fragment> fragments = new ArrayList<>();

    //仿微博图片和文字集合
    private int[] menuIconItems = {R.mipmap.pic1, R.mipmap.pic2, R.mipmap.pic3, R.mipmap.pic4};
    private String[] menuTextItems = {"文字", "拍摄", "相册", "直播"};

    private LinearLayout menuLayout;
    private View cancelImageView;
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        init();
    }

    private void init(){
        navigationView = findViewById(R.id.navigation);
        txt_title = (TextView)findViewById(R.id.txt_title);
        txt_title.setText(tabText[0]);

        fragments.add(new TipFragment());
        fragments.add(new AFragment());
        fragments.add(new CFragment());
        fragments.add(new DFragment());

        navigationView.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .fragmentManager(getSupportFragmentManager())
                .addLayoutRule(NavigationView.RULE_BOTTOM)
                .addLayoutBottom(100)
                .onTabClickListener(new NavigationView.OnTabClickListener() {
                    @Override
                    public boolean onTabClickEvent(View view, int position) {
                        //Toast.makeText(MoreActivity.this, "" + position, Toast.LENGTH_LONG).show();
                        if (position == 4) {
                            //Toast.makeText(MainActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                            //return true则拦截事件、不进行页面切换
                            //return true;
                        } else if (position == 2) {
                            //跳转页面（全民K歌）   或者   弹出菜单（微博）
                            //showMunu();
                            showPopup();
                        }
                        return false;
                    }

                    @Override
                    public void onTabSelectedEvent(int position) {
                        int index = tabText.length/2;
                        if(position < index){
                            txt_title.setText(tabText[position]);
                        } else {
                            txt_title.setText(tabText[position + 1]);
                        }
                    }
                })
                .mode(NavigationView.MODE_ADD)
                .canScroll(true)
                .build();
    }

    private void showPopup(){
        View parent = getWindow().getDecorView();
        ViewGroup view = (ViewGroup) View.inflate(MoreActivity.this, R.layout.layout_add_view_1, null);

        final PopupWindow popWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popWindow.setAnimationStyle(R.style.bottom_anim);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);// 设置同意在外点击消失

        menuLayout = view.findViewById(R.id.icon_group);
        cancelImageView = view.findViewById(R.id.cancel_iv);
        cancelImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //closeAnimation();
                popWindow.dismiss();
            }
        });
        for (int i = 0; i < 4; i++) {
            View itemView = View.inflate(MoreActivity.this, R.layout.item_icon, null);
            ImageView menuImage = itemView.findViewById(R.id.menu_icon_iv);
            TextView menuText = itemView.findViewById(R.id.menu_text_tv);

            menuImage.setImageResource(menuIconItems[i]);
            menuText.setText(menuTextItems[i]);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            params.weight = 1;
            itemView.setLayoutParams(params);
            itemView.setVisibility(View.GONE);
            menuLayout.addView(itemView);
        }


        popWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);

        showMunu();
    }

    //
    private void showMunu() {
        //startAnimation();
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                //＋ 旋转动画
                cancelImageView.animate().rotation(90).setDuration(400);
            }
        });
        //菜单项弹出动画
        for (int i = 0; i < menuLayout.getChildCount(); i++) {
            final View child = menuLayout.getChildAt(i);
            child.setVisibility(View.INVISIBLE);
            mHandler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    child.setVisibility(View.VISIBLE);
                    ValueAnimator fadeAnim = ObjectAnimator.ofFloat(child, "translationY", 600, 0);
                    fadeAnim.setDuration(500);
                    KickBackAnimator kickAnimator = new KickBackAnimator();
                    kickAnimator.setDuration(500);
                    fadeAnim.setEvaluator(kickAnimator);
                    fadeAnim.start();
                }
            }, i * 50 + 200);
        }
    }


    private void startAnimation() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //圆形扩展的动画
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                        int x = NavigationUtil.getScreenWidth(MoreActivity.this) / 2;
                        int y = (int) (NavigationUtil.getScreenHeith(MoreActivity.this) - NavigationUtil.dip2px(MoreActivity.this, 25));
                        Animator animator = ViewAnimationUtils.createCircularReveal(navigationView.getAddViewLayout(), x,
                                y, 0, navigationView.getAddViewLayout().getHeight());
                        animator.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                navigationView.getAddViewLayout().setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onAnimationEnd(Animator animation) {
                                //							layout.setVisibility(View.VISIBLE);
                            }
                        });
                        animator.setDuration(300);
                        animator.start();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    /**
     * 关闭window动画
     */
    private void closeAnimation() {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                cancelImageView.animate().rotation(0).setDuration(400);
            }
        });

        try {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

                int x = NavigationUtil.getScreenWidth(this) / 2;
                int y = (NavigationUtil.getScreenHeith(this) - NavigationUtil.dip2px(this, 25));
                Animator animator = ViewAnimationUtils.createCircularReveal(navigationView.getAddViewLayout(), x,
                        y, navigationView.getAddViewLayout().getHeight(), 0);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        //							layout.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        navigationView.getAddViewLayout().setVisibility(View.GONE);
                        //dismiss();
                    }
                });
                animator.setDuration(300);
                animator.start();
            }
        } catch (Exception e) {
        }
    }

    public NavigationView getNavigationView() {
        return navigationView;
    }
}
