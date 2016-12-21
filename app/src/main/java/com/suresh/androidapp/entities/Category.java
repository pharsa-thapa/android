package com.suresh.androidapp.entities;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by suresh on 12/12/16.
 */

public class Category extends RealmObject {

    @PrimaryKey
    private long categoryId;

    private long parentCategory;

    private String categoryName;

    private RealmList<Category> subCategories;

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(long parentCategory) {
        this.parentCategory = parentCategory;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public RealmList<Category> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(RealmList<Category> subCategories) {
        this.subCategories = subCategories;
    }


}
