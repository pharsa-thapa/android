package com.suresh.androidapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby.mvp.MvpFragment;
import com.suresh.androidapp.R;
import com.suresh.androidapp.entities.Contact;
import com.suresh.androidapp.presenters.ContactAddPresenter;
import com.suresh.androidapp.views.ContactAddView;

public class ContactAddFragment extends MvpFragment<ContactAddView, ContactAddPresenter> implements ContactAddView {

    public TextView txtName;
    public TextView txtPhone;

    public ContactAddPresenter presenter;


    @Override
    public ContactAddPresenter createPresenter() {
        return new ContactAddPresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle("Add Contact");
        View view = inflater.inflate(R.layout.fragment_contact_add, container, false);

        txtName = (TextView) view.findViewById(R.id.name_field);
        txtPhone = (TextView) view.findViewById(R.id.phone_field);

        Button addButton = (Button) view.findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Contact contact = new Contact();
                contact.setContactNumber(txtPhone.getText().toString());
                contact.setContactName(txtName.getText().toString());
                presenter.proceedSave(contact);
            }
        });

        return view;
    }

    @Override
    public void saveSuccessfulAlert(String successfulMessage) {
        Toast.makeText(getActivity(), successfulMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void saveFailureAlert(String failureMessage) {
        Toast.makeText(getActivity(), failureMessage, Toast.LENGTH_SHORT).show();
    }
}
