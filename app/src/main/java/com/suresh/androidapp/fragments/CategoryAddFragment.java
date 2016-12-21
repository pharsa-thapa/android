package com.suresh.androidapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.suresh.androidapp.R;
import com.suresh.androidapp.adapters.CategorySpinAdapter;
import com.suresh.androidapp.entities.Category;
import com.suresh.androidapp.presenters.CategoryAddPresenter;
import com.suresh.androidapp.views.CategoryAddView;

import java.util.List;


public class CategoryAddFragment extends MvpFragment<CategoryAddView, CategoryAddPresenter> implements CategoryAddView {

    public CategoryAddPresenter presenter = new CategoryAddPresenter(this);
    private CategorySpinAdapter adapter;

    @Override
    public CategoryAddPresenter createPresenter() {
        return new CategoryAddPresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        presenter = new CategoryAddPresenter(this);
        View view = inflater.inflate(R.layout.fragment_category_add, container, false);
        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner_category);

        List<Category> categories = presenter.getAllCategories(getContext());
        Category[] categoryArray = categories.toArray(new Category[categories.size()]);

        adapter = new CategorySpinAdapter(getContext(), android.R.layout.simple_spinner_item, categoryArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view,
//                                       int position, long id) {
//
//                Category category = (Category) parent.getItemAtPosition(position);
//                Log.v("item", category.getCategoryName());
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                // TODO Auto-generated method stub
//            }
//        });
Button saveBtn = (Button) view.findViewById(R.id.category_save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Category selectedCategory = (Category) spinner.getSelectedItem();
                Log.v("Selected Category", selectedCategory.getCategoryName());
            }
        });
        return view;
    }

    @Override
    public void showSuccessfulAlert(Context ctx, String message) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showFailureAlert(Context ctx, String message) {
        Toast.makeText(ctx, message, Toast.LENGTH_SHORT).show();
    }
}
