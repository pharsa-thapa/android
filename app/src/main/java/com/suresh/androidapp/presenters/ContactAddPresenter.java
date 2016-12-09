package com.suresh.androidapp.presenters;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.suresh.androidapp.entities.Contact;
import com.suresh.androidapp.fragments.ContactAddFragment;
import com.suresh.androidapp.helpers.ValidationHelper;
import com.suresh.androidapp.models.ContactModel;
import com.suresh.androidapp.presenters.interfaces.ContactAddPresenterInterface;
import com.suresh.androidapp.views.ContactAddView;

import java.util.HashMap;

/**
 * Created by suresh on 11/30/16.
 */
public class ContactAddPresenter implements ContactAddPresenterInterface, MvpPresenter<ContactAddView> {

    ContactAddFragment contactAddView;

    public ContactAddPresenter(ContactAddFragment contactAddFragment) {
        this.contactAddView = contactAddFragment;
    }

    @Override
    public void proceedSave(Contact rawContact) {

        String contactName = rawContact.getContactName().toString().trim();
        String contactNumber = rawContact.getContactNumber().toString().trim();

        String recordDetail[] = saveContactDetails(contactName, contactNumber);

        if (recordDetail[0].equals("SUCCESS")) {
            contactAddView.txtName.setText("");
            contactAddView.txtPhone.setText("");
            contactAddView.saveSuccessfulAlert(recordDetail[1] + "'s contact saved");

        } else {
            contactAddView.saveFailureAlert("Error : " + recordDetail[3]);
        }
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
            ContactModel contactModel = new ContactModel(contactAddView.getContext());
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

    @Override
    public void attachView(ContactAddView view) {

    }

    @Override
    public void detachView(boolean retainInstance) {

    }


}
