package com.washing_blanket_prd.washing_blanket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.washing_blanket_prd.washing_blanket.databinding.CalendarListBinding;
import com.washing_blanket_prd.washing_blanket.viewmodel.CalendarListViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView; // 바텀 네비게이션 뷰
    private FragmentManager fm;
    private FragmentTransaction ft;
    private BlanketListActivity frag1;
    private CalenderActivity frag2;
    private ConfigureActivity frag3;
    private AddBlanketActivity frag4;
    public Integer origin_fragment_frag;
    private long lastTimeBackPressed;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.navigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem)
            {
                switch (menuItem.getItemId())
                {
                    case R.id.itemList:

                        setFrag(0);
                        break;
                    case R.id.calender:
                        setFrag(1);
                        break;
                    case R.id.configure:
                        setFrag(2);
                        break;
                }
                return true;
            }
        });
        frag1=new BlanketListActivity();
        frag2=new CalenderActivity();
        frag3=new ConfigureActivity();
        frag4=new AddBlanketActivity();
        bottomNavigationView.setSelectedItemId(R.id.calender);
        origin_fragment_frag = 1;
        setFrag(1); // 첫 프래그먼트 화면 지정
    }



    // 프레그먼트 교체
    public void setFrag(int n)
    {
        fm = getSupportFragmentManager();
        ft= fm.beginTransaction();
        switch (n)
        {
            case 0:
                ft.replace(R.id.main_layout,frag1);
                ft.commit();
                origin_fragment_frag = 0;
                break;

            case 1:
                ft.replace(R.id.main_layout,frag2);
                ft.commit();
                origin_fragment_frag = 1;
                break;

            case 2:
                ft.replace(R.id.main_layout,frag3);
                ft.commit();
                break;

            case 3:
                ft.replace(R.id.main_layout,frag4);
                ft.commit();
                break;
        }
    }

    @Override
    public void onBackPressed() {

        //프래그먼트 onBackPressedListener사용
        List<Fragment> fragmentList = fm.getFragments();
        for(Fragment fragment : fragmentList){
            Log.d("dd", String.valueOf(fragment));
            if(fragment instanceof onBackPressedListener){
                ((onBackPressedListener)fragment).onBackPressed();
                return;
            }
        }
        if(System.currentTimeMillis() - lastTimeBackPressed < 1500){
            finish();
            return;
        }
        lastTimeBackPressed = System.currentTimeMillis();
        Toast.makeText(this,"'뒤로' 버튼을 한 번 더 누르면 종료됩니다.",Toast.LENGTH_SHORT).show();

    }
}


