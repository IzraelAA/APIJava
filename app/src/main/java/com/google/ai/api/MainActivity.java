package com.google.ai.api;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.ai.api.Adapter.adapter;
import com.google.ai.api.DbHelper.DBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    ListView                          listView;
    com.google.ai.api.Adapter.adapter adapter;
    List<Data>                        itemList = new ArrayList<Data>();


    FloatingActionButton tambah;
    public static final String Tag_id    = "Id";
    public static final String Tag_tgl   = "tgl";
    public static final String Tag_sandi = "sandi";
    public static final String Tag_d     = "debet";
    public static final String Tag_k     = "kredit";
    public static final String Tag_s     = "saldo";
    public static final String Tag_p     = "pengesahan";

    DBHelper SQLite = new DBHelper(this);
    TextView Id, sandi, debet, kredit, saldo, pengesah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLite = new DBHelper(getApplicationContext());

        listView = (ListView) findViewById(R.id.list_view);


        adapter = new adapter(MainActivity.this, itemList);
        getData();
        listView.setAdapter(adapter);

        tambah = findViewById(R.id.fab);
        tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });
    }

    private void sortArrayList() {
    }

    private void sortArray() {


        adapter.notifyDataSetChanged();

    }

    void getData() {
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=122f9d8704d0b3196854f9fc7f2f975d&language=en-US&page=10";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject record = jsonArray.getJSONObject(i);
                        String     Id     = record.getString("id");
                        Data       data   = new Data();
                        data.setId(Id);
                        itemList.add(data);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);

        adapter.notifyDataSetChanged();
    }

    private void getAllData() {

        ArrayList<HashMap<String, String>> row = SQLite.getData();

        int a             = 0;
        int b             = 0;
        int indexterbesar = 0;
        int dexterbesar   = 0;

        for (int j = 0; j < row.size(); j++) {
            int z = Integer.parseInt(row.get(j).get(Tag_k));
            if (z > a) {
                a = z;
                indexterbesar = j;
            }

        }

        for (int i = 0; i < row.size(); i++) {
            String id     = row.get(i).get(Tag_id);
            String poster = row.get(i).get(Tag_sandi);
            String title  = row.get(i).get(Tag_tgl);
            String d      = row.get(i).get(Tag_d);
            String k      = row.get(i).get(Tag_k);
            String s      = row.get(i).get(Tag_s);
            String p      = row.get(i).get(Tag_p);

            int z = Integer.parseInt(k);

            Data data = new Data();
            if (indexterbesar == i) {
                data.setTerbesar(true);
            } else if (indexterbesar != i) {
                data.setTerbesar(false);
            }
            if (a == z) {
                data.setTerbesar(true);
            } else if (a != z) {
                data.setTerbesar(false);
            }
            data.setSaldo("SALDO :" + s);
            data.setTgl("TANGGAL :" + title);
            data.setId("ID :" + id);
            data.setSandi("SANDI :" + poster);
            data.setDebet("DEBET :" + d);
            data.setKredit(k);
            data.setPengesahan("PENGESAHAN PETUGAS :" + p);

            itemList.add(data);


        }
        Arrays.sort(new ArrayList[]{row});
        Collections.sort(itemList, new Comparator<Data>() {
            @Override
            public int compare(Data o1, Data o2) {
                return o1.getId().compareTo(o2.getTgl());
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemList.clear();
        getData();
    }
}


