package com.dds.unidown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TextView;

import com.dds.unidown.databinding.ActivityMainBinding;
import com.dds.unidown.databinding.ActivityWhatsappBinding;
import com.dds.unidown.fragment.imageFragment;
import com.dds.unidown.fragment.videoFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class WhatsappActivity extends AppCompatActivity {

    private ActivityWhatsappBinding binding;
    private WhatsappActivity activity;
    private ViewPager viewPager;
    private viewPagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_whatsapp);

        binding.back.setOnClickListener(v ->{
            startActivity(new Intent(this,MainActivity.class));

        });

        activity = this;
        initview();
    }

    private void initview() {
        adapter = new viewPagerAdapter(activity.getSupportFragmentManager(),
                activity.getLifecycle());
        adapter.addFragment(new imageFragment(), "images");
        adapter.addFragment(new videoFragment(), "videos");


        binding.viewpager.setAdapter(adapter);
        binding.viewpager.setOffscreenPageLimit(1);

       new TabLayoutMediator(binding.tablayout, binding.viewpager,
                (tab, position) -> {
                    tab.setText(adapter.fragmenttitleList.get(position));
                }).attach();
       for(int i = 0;i<binding.tablayout.getTabCount();i++){
           TextView tv = (TextView) LayoutInflater.from(activity)
                   .inflate(R.layout.custom_tab,null);
           binding.tablayout.getTabAt(i).setCustomView(tv);
       }

    }

    class viewPagerAdapter extends FragmentStateAdapter{
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmenttitleList = new ArrayList<>();
        public viewPagerAdapter(@NonNull @org.jetbrains.annotations.NotNull FragmentManager fragmentManager, @NonNull @org.jetbrains.annotations.NotNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        public void addFragment(Fragment fragment, String title){
            fragmentList.add(fragment);
            fragmenttitleList.add(title);

        }
        @NonNull
        @org.jetbrains.annotations.NotNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }
    }
}