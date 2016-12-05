package com.suresh.androidapp.listeners.interfaces;

/**
 * Created by suresh on 11/29/16.
 */

public interface ContactListListener<A, B> {

    void onSuccess(A successMessage);

    void onFailure(B failureMessage);
}
