package zhuruyi.net.wechardemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import zhuruyi.net.wechardemo.fragment.ChartFragment;
import zhuruyi.net.wechardemo.fragment.FriendFragment;
import zhuruyi.net.wechardemo.fragment.MineFragment;
import zhuruyi.net.wechardemo.widget.CnToobar;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private MyFragmentPaperAdapter myFragmentPaperAdapter;
    private ChartFragment chartFrament;
    private FriendFragment friendFragment;
    private MineFragment mineFragment;
    private ArrayList<Fragment> mFragmentArrayList;
    private RadioGroup mRadioGroup;
    //private Toolbar mToolbar;
    private CnToobar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mViewPager = (ViewPager) findViewById(R.id.page);
        mToolbar = (CnToobar) this.findViewById(R.id.toolbar);
        //给toolbar设置点击事件监听器（最右边三个小点）
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Navigation clicked", Toast.LENGTH_LONG).show();
            }
        });
        mToolbar.inflateMenu(R.menu.menu_main);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_setting) {
                    Toast.makeText(MainActivity.this, "menu clicked", Toast.LENGTH_LONG).show();
                    return true;
                }
                return false;
            }
        });
        initPaperFragment();
        initRadioGroup();
    }
    //设置ViewPager中的Fragment
    public void initPaperFragment() {
        mFragmentArrayList = new ArrayList<Fragment>();
        chartFrament = new ChartFragment();
        friendFragment = new FriendFragment();
        mineFragment = new MineFragment();
        mFragmentArrayList.add(chartFrament);
        mFragmentArrayList.add(friendFragment);
        mFragmentArrayList.add(mineFragment);
        myFragmentPaperAdapter = new MyFragmentPaperAdapter(getSupportFragmentManager(), mFragmentArrayList);
        mViewPager.setAdapter(myFragmentPaperAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mRadioGroup.check(R.id.tab_chart);
                        break;
                    case 1:
                        mRadioGroup.check(R.id.tab_friend);
                        break;
                    case 2:
                        mRadioGroup.check(R.id.tab_more);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //设置FragmentPaperAdapter
    public class MyFragmentPaperAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragmentArrayList;

        public MyFragmentPaperAdapter(FragmentManager fm) {
            super(fm);
        }

        public MyFragmentPaperAdapter(FragmentManager fragmentManager, ArrayList<Fragment> mFragmentArrayList) {
            super(fragmentManager);
            this.fragmentArrayList = mFragmentArrayList;
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }

        @Override
        public int getItemPosition(Object object) {
            return super.getItemPosition(object);
        }
    }

    //设置RadioGroup
    public void initRadioGroup() {
        mRadioGroup = (RadioGroup) findViewById(R.id.tab_menu);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.tab_chart:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.tab_friend:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.tab_more:
                        mViewPager.setCurrentItem(2);
                        break;
                }
            }
        });
    }
}
