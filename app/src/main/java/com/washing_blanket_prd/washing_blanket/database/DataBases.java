package com.washing_blanket_prd.washing_blanket.database;

import android.provider.BaseColumns;

public final class DataBases {

    public static final class CreateDB implements BaseColumns {
        public static final String BlanketName = "blanket_name";
        public static final String Icon = "icon";
        public static final String Image = "image";
        public static final String AlarmPeriod = "alarm_period";
        public static final String CreateDate = "create_date";
        public static final String WashedCheck = "washed_check";
        public static final String _TABLENAME0 = "blanket";

        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, "
                +BlanketName+" text not null , "
                +Icon+" text, "
                +Image+" text, "
                +AlarmPeriod+" integer not null , "
                +CreateDate+" DATETIME DEFAULT (datetime('now','localtime')) , "
                +WashedCheck+" text );";
    }
}
