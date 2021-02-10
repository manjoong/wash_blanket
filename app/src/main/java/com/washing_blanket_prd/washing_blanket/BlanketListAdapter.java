package com.washing_blanket_prd.washing_blanket;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import com.washing_blanket_prd.washing_blanket.BlanketListAdapter.MyViewHolder;
import com.washing_blanket_prd.washing_blanket.database.DbOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class BlanketListAdapter extends RecyclerView.Adapter<MyViewHolder>{
//    public class SelectedStationListAdapter extends RecyclerView.Adapter<MyViewHolder> implements View.OnClickListener {

    Context context;
    private DbOpenHelper mDbOpenHelper;
    Map<String, Integer> station_pointer = new HashMap<String, Integer>();

    ArrayList<BlanketListConstruct> BlanketList;

    public BlanketListAdapter(Context context, ArrayList<BlanketListConstruct> list) {
        super();
        this.context = context;
        this.BlanketList = list;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.blanket_list_detail, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.blanket_name_text.setText(BlanketList.get(position).getBlanketName());
        holder.wash_period_text.setText(BlanketList.get(position).getAlarmPeriod()+" 일 마다 세탁");

        String[] washed_date_arr={};

        washed_date_arr = BlanketList.get(position).getWashedCheck().split(",");

        Log.d("washed_date_arr의 크기 ", "갯수는 " + washed_date_arr.length);
        for(int i=0; i< washed_date_arr.length;i++){
            Log.d("세탁 내역", "세탁 내역은  " + washed_date_arr[i]);
        }
        SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format2 = new SimpleDateFormat( "yyyy/MM/dd");

        Calendar cal = Calendar.getInstance();

        if(washed_date_arr.length==0 || washed_date_arr[washed_date_arr.length-1].equals("")){
            Date date = null;
            try {
                date = format1.parse(BlanketList.get(position).getCreateDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cal.setTime(date);
            cal.add(Calendar.DATE, Integer.valueOf(BlanketList.get(position).getAlarmPeriod()));

            holder.next_wash_date_text.setText("다음 세탁일: "+format2.format(cal.getTime()));

            holder.check_wash_checkbox.setChecked(false);
            holder.check_wash_checkbox.setEnabled(true);
            holder.check_wash_text.setText("세탁 미완료");
        }else{
            Date date = null;
            try {
                Log.d("해당 날짜는?", "세탁 날짜" + washed_date_arr[washed_date_arr.length-1]);
                date = format1.parse(washed_date_arr[washed_date_arr.length-1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            cal.setTime(date);
            cal.add(Calendar.DATE, Integer.valueOf(BlanketList.get(position).getAlarmPeriod()));
            holder.next_wash_date_text.setText("다음 세탁일: "+format2.format(cal.getTime()));

            Date time = new Date();
            Date next_wash_day = new Date(cal.getTimeInMillis()); // Date(long date)

            if(time.getTime() <= next_wash_day.getTime()){
                holder.check_wash_checkbox.setChecked(true);
                holder.check_wash_checkbox.setEnabled(false);
                holder.check_wash_text.setText("세탁 완료");
            }else{
                holder.check_wash_checkbox.setChecked(false);
                holder.check_wash_checkbox.setEnabled(true);
                holder.check_wash_text.setText("세탁 미완료");
            }
        }

        switch(String.valueOf(BlanketList.get(position).getIcon())) {
            case "bed":
                Log.d("아이콘", "아이콘 " + BlanketList.get(position).getIcon());
                holder.blanket_icon_image.setImageResource(R.mipmap.bed);
                break;
            case "noicon":
                holder.blanket_icon_image.setImageResource(R.mipmap.x);
                break;
            case "small_sofa":
                holder.blanket_icon_image.setImageResource(R.mipmap.small_sofa);
                break;
            case "big_sofa":
                holder.blanket_icon_image.setImageResource(R.mipmap.big_sofa);
                break;
            case "curtain":
                holder.blanket_icon_image.setImageResource(R.mipmap.curtain);
                break;
            case "chair":
                holder.blanket_icon_image.setImageResource(R.mipmap.chair);
                break;
            case "table":
                holder.blanket_icon_image.setImageResource(R.mipmap.table);
                break;
            case "pillow":
                holder.blanket_icon_image.setImageResource(R.mipmap.pillow);
                break;
            default:
                holder.blanket_icon_image.setImageResource(R.mipmap.x);
                break;
        }





        holder.check_wash_checkbox.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mDbOpenHelper = new DbOpenHelper(context);
                mDbOpenHelper.open();
                mDbOpenHelper.create();
                String washed_check="";
                if (holder.check_wash_checkbox.isChecked()) {
                    SimpleDateFormat format1 = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
                    Date time = new Date();
                    String time1 = format1.format(time);
                    washed_check = BlanketList.get(position).getWashedCheck()+time1+",";
                    holder.check_wash_checkbox.setEnabled(false);
                    holder.check_wash_text.setText("세탁 완료");
                    Log.d("체크박스 ", "체크확인  " + washed_check);
                }
//                else{
//                    String[] washed_date_arr={};
//                    washed_date_arr = BlanketList.get(position).getWashedCheck().split(",");
////                    if(washed_date_arr.length == 1 && washed_date_arr[0].length()==0){
//                    if(washed_date_arr.length == 1){
//                        washed_check = "";
//                    }else{
//                        for(int i=0; i< washed_date_arr.length;i++){
//                            washed_check = washed_check + washed_date_arr[i] +",";
//                            Log.d("체크박스 ", "체크해제 확인  " + washed_date_arr[i]);
//                        }
//                    }
//                    Log.d("체크박스 ", "체크해제 확인  " + washed_check + washed_date_arr.length);
//                }
                mDbOpenHelper.open();
                mDbOpenHelper.updateColumn(BlanketList.get(position).getId(), BlanketList.get(position).getBlanketName(), BlanketList.get(position).getIcon(), BlanketList.get(position).getImage(), BlanketList.get(position).getAlarmPeriod(), washed_check);
            }
        });

    }



    @Override
    public int getItemCount() {
        return BlanketList.size();
    }

//    @Override
//    public void onClick(View v) {
//        int pos = getAdapterPosition() ;
//        switch (v.getId()) {
//            case R.id.opst_btn:
//                Toast.makeText(context, SelectedStation..getStnName();
//                break;
//            case R.id.apt_btn:
//                Toast.makeText(context, data.getTitle(), Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.or_btn:
//                Toast.makeText(context, data.getContent(), Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView blanket_name_text;
        ImageView blanket_icon_image;
        TextView wash_period_text;
        TextView next_wash_date_text;
        CheckBox check_wash_checkbox;
        TextView check_wash_text;

//        TextView textViewStnName;
//        FrameLayout frameLayoutOpst;
//        Button opst_btn;
//        FrameLayout frameLayoutApt;
//        FrameLayout frameLayoutOr;
//        TextView textViewStnType;
//        TextView textViewLine;

        public MyViewHolder(View itemView) {
            super(itemView);
            blanket_name_text = (TextView)itemView.findViewById(R.id.blanket_name);
            blanket_icon_image = (ImageView) itemView.findViewById(R.id.blanket_icon);
            wash_period_text = (TextView) itemView.findViewById(R.id.wash_period);
            next_wash_date_text = (TextView) itemView.findViewById(R.id.next_wash_date);
            check_wash_checkbox = (CheckBox) itemView.findViewById(R.id.check_wash_box);
            check_wash_text = (TextView) itemView.findViewById(R.id.check_wash_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, BlankDetailActivity.class);
                    intent.putExtra("data", "Test Popup");
                    ((MainActivity) context).startActivityForResult(intent, 1);

//                    startActivityForResult(intent, 1);


                }
            });



//            textViewStnName = (TextView)itemView.findViewById(R.id.station_name);
//            opst_btn = itemView.findViewById(R.id.opst_btn);
//            frameLayoutApt = itemView.findViewById(R.id.apt_btn);
//            frameLayoutOr = itemView.findViewById(R.id.or_btn);

//            if (SelectedStation.get(position).getType() == "OPST"){
//
//            }

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int pos = getAdapterPosition() ;
//                    if (pos != RecyclerView.NO_POSITION) {
//                        Toast.makeText(context,  SelectedStation.get(pos).getStnName() + "역을 삭제 하시겠습니까?", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
        }
    }



}

