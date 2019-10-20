package com.google.ai.api.DbHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {
    private static final int DATABASI_VERSION = 14;

    static final        String DATABASE_NAME = "DB.One";
    public static final String Table         = "tb_tbku";
    public static final String Col_id        = "Id";
    public static final String Col_tgl       = "tgl";
    public static final String Col_sandi     = "sandi";
    public static final String Col_debet = "debet";
    public static final String Col_kredit = "kredit";
    public static final String Col_saldo = "saldo";
    public static final String Col_pengesahan = "pengesahan";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASI_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TABLE = "CREATE TABLE " + Table + "("
                + Col_id + " TEXT PRIMARY KEY NOT NULL,"
                + Col_tgl + " TEXT NOT NULL,"
                + Col_sandi + " TEXT NOT NULL,"
                + Col_debet + " TEXT NOT NULL,"
                + Col_kredit + " TEXT NOT NULL,"
                + Col_saldo +" TEXT NOT NULL,"
            + Col_pengesahan + " TEXT NOT NULL"+")";

        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Table);
        onCreate(db);
    }

    public ArrayList<HashMap<String, String>> getData() {
        ArrayList<HashMap<String, String>> ListTa;
        ListTa = new ArrayList<HashMap<String, String>>();
        String         selectQuery = "SELECT * FROM " + Table;
        SQLiteDatabase database    = this.getWritableDatabase();
        Cursor         cursor      = database.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put(Col_id, cursor.getString(0));
                map.put(Col_tgl, cursor.getString(1));
                map.put(Col_sandi, cursor.getString(2));
                map.put(Col_debet, cursor.getString(3));
                map.put(Col_kredit, cursor.getString(4));
                map.put(Col_saldo, cursor.getString(5));
                map.put(Col_pengesahan,cursor.getString(6));
                ListTa.add(map);

            } while (cursor.moveToNext());
        }
        Log.e("select tb_tbku",""+ListTa);


        database.close();
        return ListTa;
    }

    public void insert (String Id,String tgl,String sandi,String debet,String kredit,String saldo,String pengesahan){
        SQLiteDatabase database = this.getWritableDatabase();
        String queryValues = "INSERT INTO " + Table + " ( Id, tgl, sandi, debet, kredit, saldo, pengesahan) " +
                "VALUES ('" + Id + "','" + tgl + "', '" + sandi + "','" + debet + "','" + kredit + "','" + saldo + "','" + pengesahan + "')";

        Log.e("insert tb_tbku", "" + queryValues);
        database.execSQL(queryValues);
        database.close();
    }

}
