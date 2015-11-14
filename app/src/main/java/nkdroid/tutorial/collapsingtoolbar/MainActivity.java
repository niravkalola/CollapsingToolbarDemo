package nkdroid.tutorial.collapsingtoolbar;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imageView,tabBg;
    private CollapsingToolbarLayout collapsingToolbar;
    private TabPagerAdapter tabPagerAdapter;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView= (ImageView) findViewById(R.id.backdrop);
        tabBg= (ImageView) findViewById(R.id.tabBg);
        collapsingToolbar=(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        setToolbar();
        setImage();

        mViewPager= (ViewPager) findViewById(R.id.viewpager);
        mTabLayout= (TabLayout) findViewById(R.id.detail_tabs);
        tabPagerAdapter=new TabPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(tabPagerAdapter);
        mTabLayout.setTabsFromPagerAdapter(tabPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0){
                    Glide.with(MainActivity.this).load(R.drawable.onet).into(imageView);
                    Glide.with(MainActivity.this).load(R.drawable.oneb).into(tabBg);
                } else {
                    Glide.with(MainActivity.this).load(R.drawable.twot).into(imageView);
                    Glide.with(MainActivity.this).load(R.drawable.twob).into(tabBg);
                }

                imageView.invalidate();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setToolbar() {
        toolbar= (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {

            setSupportActionBar(toolbar);
        }
    }

    private void setImage() {
        Glide.with(this).load(R.drawable.onet).into(imageView);
        Glide.with(MainActivity.this).load(R.drawable.oneb).into(tabBg);
    }

    class TabPagerAdapter extends FragmentStatePagerAdapter {

        public TabPagerAdapter(FragmentManager fm){
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {

            SampleFragment sampleFragment=SampleFragment.newInstance((++position)+"");
            return sampleFragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Dummy Tab "+(++position) ;
        }
    }
}
