package com.washing_blanket_prd.washing_blanket;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.washing_blanket_prd.washing_blanket.databinding.CalendarListBinding;
import com.washing_blanket_prd.washing_blanket.viewmodel.CalendarListViewModel;

import java.security.acl.Owner;
import java.util.ArrayList;
import java.util.Objects;

public class CalenderActivity extends Fragment{

    MutableLiveData<ArrayList<Object>> mCalendarList= new MutableLiveData<>();
    private BottomNavigationView bottomNavigationView;
    private CalendarListBinding binding;
    private CalendarAdapter calendarAdapter;
    public ImageButton addBlanketBtn;
    private Context context;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context = container.getContext();
        binding = DataBindingUtil.inflate(
                inflater, R.layout.activity_calender, container, false);
        //
        binding.setActivity(this); //이걸 넣어줘야 버튼이 먹힘

//        binding.addBlanketBtn
        
        binding.setVariable(BR.model, new ViewModelProvider(this).get(CalendarListViewModel.class));
        binding.setLifecycleOwner(this);

        binding.getModel().initCalendarList();

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL);
        calendarAdapter = new CalendarAdapter();
        binding.pagerCalendar.setLayoutManager(manager);
        binding.pagerCalendar.setAdapter(calendarAdapter);
        observe();


        //





        View view = binding.getRoot();
        //here data must be an instance of the class MarsDataProvider
        return view;


    }




//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        binding.setVariable(BR.model, new ViewModelProvider(this).get(CalendarListViewModel.class));
//        binding.setLifecycleOwner(this);
//
//        binding.getModel().initCalendarList();
//
//        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL);
//        calendarAdapter = new CalendarAdapter();
//        binding.pagerCalendar.setLayoutManager(manager);
//        binding.pagerCalendar.setAdapter(calendarAdapter);
//        observe();
//
//
//
//    }
    public void btnClick(View view)
    {
        ((MainActivity)getActivity()).setFrag(3);

    }

    private void observe() {
        binding.getModel().mCalendarList.observe(getViewLifecycleOwner(), new Observer<ArrayList<Object>>() {
            @Override
            public void onChanged(ArrayList<Object> objects) {
                calendarAdapter.submitList(objects);
                if (binding.getModel().mCenterPosition > 0) {
                    binding.pagerCalendar.scrollToPosition(binding.getModel().mCenterPosition);
                }
            }
        });
    }


}
