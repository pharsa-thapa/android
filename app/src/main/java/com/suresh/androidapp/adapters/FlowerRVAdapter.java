package com.suresh.androidapp.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.suresh.androidapp.FlowerDetail;
import com.suresh.androidapp.R;
import com.suresh.androidapp.entities.Flower;

import java.util.List;

/**
 * Created by suresh on 11/15/16.
 */

public class FlowerRVAdapter extends RecyclerView.Adapter<FlowerRVAdapter.FlowerViewHolder> {

    List<Flower> flowerList;

    public FlowerRVAdapter(List<Flower> flowers) {
        flowerList = flowers;

    }


    public class FlowerViewHolder extends RecyclerView.ViewHolder {

        TextView flowerInstruction;
        TextView flowerCategory;

        FlowerViewHolder(View itemView) {
            super(itemView);
            flowerCategory = (TextView) itemView.findViewById(R.id.item_name_category);
            flowerInstruction = (TextView) itemView.findViewById(R.id.flower_item_instruction);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Flower flower = flowerList.get(getAdapterPosition());
                    Bundle bundle = new Bundle();
                    bundle.putString("flowerName", flower.getName());
                    bundle.putString("flowerCategory", flower.getCategory());
                    bundle.putString("flowerPrice", flower.getPrice() + "");
                    bundle.putString("flowerInstruction", flower.getInstructions());
                    bundle.putString("flowerId", flower.getProductId() + "");

                    FlowerDetail fragFlowerDetail = new FlowerDetail();
                    fragFlowerDetail.setArguments(bundle);

                    AppCompatActivity activity = (AppCompatActivity) view.getContext();

                    FragmentManager fm = activity.getSupportFragmentManager();
                    fm.beginTransaction().replace(R.id.content_main, fragFlowerDetail, null).commit();

                }
            });


        }
    }

    @Override
    public FlowerRVAdapter.FlowerViewHolder onCreateViewHolder(ViewGroup viewGroup, int item) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View flowerView = inflater.inflate(R.layout.flower_item, viewGroup, false);
        FlowerViewHolder viewHolder = new FlowerViewHolder(flowerView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FlowerRVAdapter.FlowerViewHolder holder, int position) {

        Flower flower = flowerList.get(position);
        String name = "";
        holder.flowerCategory.setText((name = flower.getName() + " :: " + flower.getCategory()).length() > 32 ? name.substring(0, 32) : name);
        holder.flowerInstruction.setText(flower.getInstructions().length() > 100 ? (flower.getInstructions().substring(0, 100) + " ...") : flower.getInstructions());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return flowerList.size();
    }
}
