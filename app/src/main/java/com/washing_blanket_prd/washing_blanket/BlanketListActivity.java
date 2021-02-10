package com.washing_blanket_prd.washing_blanket;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.washing_blanket_prd.washing_blanket.database.DbOpenHelper;

import java.util.ArrayList;
import java.util.Objects;

public class BlanketListActivity extends Fragment implements View.OnClickListener {
    private Context context;


    private DbOpenHelper mDbOpenHelper;
    BlanketListAdapter adapter_blanket_list;
    ArrayList<BlanketListConstruct> BlanketItem = new ArrayList<>();
    RecyclerView recyclerView_blanket;
    ImageButton add_blanket_btn;


    ViewGroup viewGroup;
    @Nullable
    @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                       @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.activity_blanket_list,container,false);
        context = container.getContext();
        recyclerView_blanket = (RecyclerView)  viewGroup.findViewById(R.id.blacket_recycler);
        add_blanket_btn = (ImageButton) viewGroup.findViewById(R.id.add_blanket_btn);
        add_blanket_btn.setOnClickListener(this);

        mDbOpenHelper = new DbOpenHelper(context);
        mDbOpenHelper.open();
        mDbOpenHelper.create();
        showDatabase("_id");

        return viewGroup;
    }

    public void showDatabase(String sort){
        Cursor iCursor = mDbOpenHelper.sortColumn(sort);
        Log.d("showDatabase", "DB Size: " + iCursor.getCount());
//        arrayData.clear();
//        arrayIndex.clear();
        BlanketItem.clear();
        while(iCursor.moveToNext()){

            BlanketListConstruct blanket_info = new BlanketListConstruct();
            Integer id = iCursor.getInt(iCursor.getColumnIndex("_id"));
            blanket_info.setId(id);
            String blanket_name = iCursor.getString(iCursor.getColumnIndex("blanket_name"));
            blanket_info.setBlanketName(blanket_name);
            String icon = iCursor.getString(iCursor.getColumnIndex("icon"));
            blanket_info.setIcon(icon);
            String image = iCursor.getString(iCursor.getColumnIndex("image"));
            blanket_info.setImage(image);
            Integer alarm_period = iCursor.getInt(iCursor.getColumnIndex("alarm_period"));
            blanket_info.setAlarmPeriod(alarm_period);
            String washed_check = iCursor.getString(iCursor.getColumnIndex("washed_check"));
            blanket_info.setWashedCheck(washed_check);
            String  create_date = iCursor.getString(iCursor.getColumnIndex("create_date"));
            blanket_info.setCreateDate(create_date);

            BlanketItem.add(blanket_info);

            String Result = "id: " + id + "이불명: " + blanket_name + "icon: " + icon + "이미지: " + image + "알람 주기: " + String.valueOf(alarm_period)+ "세탁 일자: " + washed_check + "내 이불 생성일: " + create_date;
//            Toast.makeText(context, "뭐가 문젠데", Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, blanket_name, Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, Result, Toast.LENGTH_SHORT).show();
            Log.d("디비 리스트", Result);
        }
        adapter_blanket_list = new BlanketListAdapter(context, BlanketItem);
        recyclerView_blanket.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView_blanket.setAdapter(adapter_blanket_list);

    }

//    @Override
//    public void onBackPressed() {
//        goToMain();
//    }
//
//    //프래그먼트 종료
//    private void goToMain(){
//        FragmentManager fragmentManager = Objects.requireNonNull(getActivity()).getSupportFragmentManager();
//        fragmentManager.beginTransaction().remove(BlanketListActivity.this).commit();
//        fragmentManager.popBackStack();
//    }
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.add_blanket_btn:
                ((MainActivity) getActivity()).setFrag(3);
        }
    }

}
