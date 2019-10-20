package com.google.ai.api.Adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.google.ai.api.Data;
import com.google.ai.api.R;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class adapter extends BaseAdapter {
    private   Activity     activity;
    private LayoutInflater inflater;

    List<Data>                        items;
    public adapter(Activity activity,List<Data> items){
        this.activity = activity;

        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      if (inflater == null)
          inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      if (convertView == null)
          convertView = inflater.inflate(R.layout.list_row,null);

        TextView Id = convertView.findViewById(R.id.tv_item_id);
        TextView tgl = convertView.findViewById(R.id.tvtgl);
        TextView sandi = convertView.findViewById(R.id.tvsandi);
        TextView debet = convertView.findViewById(R.id.tvdebet);
        TextView kredit = convertView.findViewById(R.id.tvkredit);
        TextView saldo = convertView.findViewById(R.id.tvsaldo);
        TextView Penesahan = convertView.findViewById(R.id.tvpengesahan);

        Data data = items.get(position);



        Locale                             localId      = new Locale("in","ID");
        NumberFormat                       formatrupiah = NumberFormat.getCurrencyInstance(localId);

    Id.setText(data.getId());
    tgl.setText(data.getTgl());
    sandi.setText(data.getSandi());
    debet.setText(data.getDebet());
    saldo.setText(data.getSaldo());
    Penesahan.setText(data.getPengesahan());

        return convertView;
    }
}
