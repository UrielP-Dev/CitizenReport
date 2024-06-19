package com.si.citizenreport.BLogic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.si.citizenreport.R;

public class mainPageSample extends AppCompatActivity {
    FirstFragment12 firstFragment = new FirstFragment12();
    ReportFragment secondFragment = new ReportFragment();
    ThirdFragment12 thirdFragment = new ThirdFragment12();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page_sample);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(navigationSelect);

    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navigationSelect = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.firstFragment1:
                    loadFragment(firstFragment);
                    return true;
                case R.id.secondFragment1:
                    loadFragment(secondFragment);
                    return true;
                case R.id.thirdFragment1:
                    loadFragment(thirdFragment);
                    return true;
                default:
                    return false;
            }
        }
    };

    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameContainer, fragment);
        transaction.commit();
    }
}
