package com.suresh.androidapp;

import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FlowerDetail extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle args = getArguments();
        View view = inflater.inflate(R.layout.fragment_flower_detail, container, false);

        ((TextView)view.findViewById(R.id.item_name_text)).setText(args.get("flowerName").toString());
        ((TextView)view.findViewById(R.id.item_category_text)).setText("Category : " + args.get("flowerCategory").toString());
        ((TextView)view.findViewById(R.id.item_price_text)).setText("Price : " + args.get("flowerPrice").toString());
        ((TextView)view.findViewById(R.id.item_instruction_text)).setText(args.get("flowerInstruction").toString());

        return view;
    }
}
