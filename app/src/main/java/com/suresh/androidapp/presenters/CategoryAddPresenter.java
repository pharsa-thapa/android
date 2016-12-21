package com.suresh.androidapp.presenters;

import android.content.Context;

import com.suresh.androidapp.entities.Category;
import com.suresh.androidapp.entities.Contact;
import com.suresh.androidapp.fragments.CategoryAddFragment;
import com.suresh.androidapp.interactors.CategoryInteractor;
import com.suresh.androidapp.presenters.interfaces.CategoryAddPresenterInterface;
import com.suresh.androidapp.views.CategoryAddView;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by suresh on 12/12/16.
 */

public class CategoryAddPresenter implements CategoryAddPresenterInterface {

    private CategoryAddFragment categoryAddFragment;

    public CategoryAddPresenter(CategoryAddFragment categoryAddFragment) {
        this.categoryAddFragment = categoryAddFragment;
    }

    @Override
    public void attachView(CategoryAddView view) {

    }

    @Override
    public void detachView(boolean retainInstance) {

    }

    @Override
    public void addCategory(String categoryName, Context context) {
        Category category = new Category();
        category.setCategoryName(categoryName);

        if (new CategoryInteractor(context).saveCategory(category) > 0) {
            categoryAddFragment.showSuccessfulAlert(context, categoryName + " saved successfully");
        } else {
            categoryAddFragment.showFailureAlert(context, "Failed to save category");
        }
    }

    @Override
    public List<Category> getAllCategories(Context context) {
        RealmResults<Category> categories = new CategoryInteractor(context).getAllCategories();

        List<Category> categoryList = new ArrayList<>();

        if (categoryList != null) {
            for (Category category : categories) {
                Category categoryInstance = new Category();
                categoryInstance.setCategoryName(category.getCategoryName());
                categoryInstance.setCategoryId(category.getCategoryId());
                categoryInstance.setParentCategory(category.getParentCategory());

                categoryList.add(categoryInstance);
            }
        }
        return categoryList;
    }
}
