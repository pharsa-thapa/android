package com.suresh.androidapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.suresh.androidapp.entities.Contact;
import com.suresh.androidapp.helpers.ValidationHelper;
import com.suresh.androidapp.models.ContactModel;

import java.util.HashMap;

public class ContactAddFragment extends Fragment {

    TextView txtName;
    TextView txtPhone;

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

                String recordDetail[] = saveContactDetails(txtName.getText().toString().trim(), txtPhone.getText().toString().trim());

                if (recordDetail[0].equals("SUCCESS")) {
                    txtName.setText("");
                    txtPhone.setText("");
                    Toast.makeText(getActivity(), recordDetail[1] + "'s contact saved", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Error : " + recordDetail[3], Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private String[] saveContactDetails(String name, String phone) {
        HashMap<Object, Object> nameValHm = ValidationHelper.isValidName(name);
        HashMap<Object, Object> phoneValHm = ValidationHelper.isValidPhone(phone);
        String resultVal = "FAIL";
        String error = "";
        boolean isValidName = (Boolean) nameValHm.get("isValidName");
        boolean isValidPhone = (Boolean) phoneValHm.get("isValidPhone");

        if (!isValidName || !isValidPhone) {
            error = (String) nameValHm.get("error") + (String) phoneValHm.get("error");
        } else {
            ContactModel contactModel = new ContactModel(getContext());
            Contact contact = new Contact();
            contact.setContactName(name);
            contact.setContactNumber(phone);

            if (contactModel.saveContact(contact) != -1) {
                resultVal = "SUCCESS";
            } else {
                error = "Failed to save contact";
            }
        }

        String[] details = {resultVal, name, phone, error};

        return details;
    }
}
