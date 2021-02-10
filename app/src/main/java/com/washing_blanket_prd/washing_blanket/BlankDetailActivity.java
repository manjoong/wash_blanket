package com.washing_blanket_prd.washing_blanket;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.washing_blanket_prd.washing_blanket.database.DbOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;


public class BlankDetailActivity extends Activity {
    private Context context;

    ImageButton complate_info_btn;
    EditText blanket_name_text;

    ImageButton no_image_btn;
    ImageButton bed_btn;
    ImageButton small_sofa_btn;
    ImageButton big_sofa_btn;
    ImageButton curtain_btn;
    ImageButton chair_btn;
    ImageButton table_btn;
    ImageButton pillow_btn;

    SeekBar period_seekbar;
    TextView period_text;
    CheckBox washed_checkbox;

    String blanket_name;
    String blanket_icon;
    String blanket_image;
    Integer blanket_period;
    String washed_check;


    public DbOpenHelper mDbOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        WindowManager.LayoutParams layoutParams= new WindowManager.LayoutParams();
        layoutParams.flags= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        layoutParams.dimAmount= 0.7f;getWindow().setAttributes(layoutParams);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        getWindow().setAttributes(layoutParams);



        setContentView(R.layout.activity_detail_page);
        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        int width = (int) (dm.widthPixels * 0.9); // Display 사이즈의 90%
        int height = (int) (dm.heightPixels * 0.9); // Display 사이즈의 90%
        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;

        period_seekbar = (SeekBar) findViewById(R.id.period_seekbar);


        mDbOpenHelper = new DbOpenHelper(context);
        mDbOpenHelper.open();
        mDbOpenHelper.create();


        complate_info_btn = (ImageButton) findViewById(R.id.complate_info);
        blanket_name_text = (EditText) findViewById(R.id.blanket_name);
        no_image_btn = (ImageButton) findViewById(R.id.no_image);
        no_image_btn.setOnClickListener(myListener);
        bed_btn= (ImageButton) findViewById(R.id.bed);
        bed_btn.setOnClickListener(myListener);
        small_sofa_btn= (ImageButton) findViewById(R.id.small_sofa);
        small_sofa_btn.setOnClickListener(myListener);
        big_sofa_btn= (ImageButton) findViewById(R.id.big_sofa);
        big_sofa_btn.setOnClickListener(myListener);
        curtain_btn= (ImageButton) findViewById(R.id.curtain);
        curtain_btn.setOnClickListener(myListener);
        chair_btn= (ImageButton) findViewById(R.id.chair);
        chair_btn.setOnClickListener(myListener);
        table_btn= (ImageButton) findViewById(R.id.table);
        table_btn.setOnClickListener(myListener);
        pillow_btn= (ImageButton) findViewById(R.id.pillow);
        pillow_btn.setOnClickListener(myListener);

        period_seekbar = (SeekBar) findViewById(R.id.period_seekbar);
        period_text = (TextView) findViewById(R.id.period_text);
        washed_checkbox = (CheckBox) findViewById(R.id.washed_check);

        blanket_icon = "noicon";
        change_btn(no_image_btn);

        period_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onStopTrackingTouch(SeekBar seekBar) {
                period_text.setText(seekBar.getProgress()+" 일");
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
                period_text.setText(seekBar.getProgress()+" 일");
            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                period_text.setText(progress+" 일");
                blanket_period = Integer.valueOf(progress);
            }
        });

        complate_info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "insert", Toast.LENGTH_SHORT).show();
                blanket_name = blanket_name_text.getText().toString();
                if (washed_checkbox.isChecked()) {
                    SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
                    Date time = new Date();
                    String time1 = format1.format(time);
                    washed_check = time1+",";
                }else{
                    washed_check = "";
                }
                mDbOpenHelper.open();
                mDbOpenHelper.insertColumn(blanket_name, blanket_icon, "", blanket_period, washed_check);
                showDatabase("_id");
//                Integer pre_frag;
//                pre_frag = ((MainActivity)getActivity()).origin_fragment_frag;
//                ((MainActivity)getActivity()).setFrag(pre_frag);

            }
        });

    }
    public void showDatabase(String sort){
        Cursor iCursor = mDbOpenHelper.sortColumn(sort);
        Log.d("showDatabase", "DB Size: " + iCursor.getCount());
//        arrayData.clear();
//        arrayIndex.clear();
        while(iCursor.moveToNext()){
            String id = iCursor.getString(iCursor.getColumnIndex("_id"));
            String blanket_name = iCursor.getString(iCursor.getColumnIndex("blanket_name"));
            String icon = iCursor.getString(iCursor.getColumnIndex("icon"));
            String image = iCursor.getString(iCursor.getColumnIndex("image"));
            Integer alarm_period = iCursor.getInt(iCursor.getColumnIndex("alarm_period"));
            String washed_check = iCursor.getString(iCursor.getColumnIndex("washed_check"));
            String  create_date = iCursor.getString(iCursor.getColumnIndex("create_date"));

            String Result = "id: " + id + "이불명: " + blanket_name + "icon: " + icon + "이미지: " + image + "알람 주기: " + String.valueOf(alarm_period)+ "세탁 일자: " + washed_check + "내 이불 생성일: " + create_date;
//            Toast.makeText(context, "뭐가 문젠데", Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, blanket_name, Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, Result, Toast.LENGTH_SHORT).show();
            Log.d("디비 리스트", Result);
        }

    }


    View.OnClickListener myListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.no_image:
                    blanket_icon = "noicon";
                    change_btn(no_image_btn);
                    break;
                case R.id.bed:
                    blanket_icon = "bed";
                    change_btn(bed_btn);
                    break;
                case R.id.small_sofa:
                    blanket_icon = "small_sofa";
                    change_btn(small_sofa_btn);
                    break;
                case R.id.big_sofa:
                    blanket_icon = "big_sofa";
                    change_btn(big_sofa_btn);
                    break;
                case R.id.curtain:
                    blanket_icon = "curtain";
                    change_btn(curtain_btn);
                    break;
                case R.id.chair:
                    blanket_icon = "chair";
                    change_btn(chair_btn);
                    break;
                case R.id.table:
                    blanket_icon = "table";
                    change_btn(table_btn);
                    break;
                case R.id.pillow:
                    blanket_icon = "pillow";
                    change_btn(pillow_btn);
                    break;
            }
        }
    };




    public void change_btn(ImageButton btn_name){
        ImageButton[] btn_kinds = {no_image_btn, bed_btn, small_sofa_btn, big_sofa_btn, curtain_btn, chair_btn, table_btn, pillow_btn};

        for(int i=0; i<btn_kinds.length; i++){
            if(btn_name != btn_kinds[i]){
                btn_kinds[i].setAlpha(50);
            }else{
                btn_kinds[i].setAlpha(255);
            }
        }
    }
}

