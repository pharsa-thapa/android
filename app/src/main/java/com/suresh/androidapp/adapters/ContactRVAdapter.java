package com.suresh.androidapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.suresh.androidapp.R;
import com.suresh.androidapp.entities.Contact;
import com.suresh.androidapp.models.ContactModel;

import java.util.List;

/**
 * Created by suresh on 11/18/16.
 */

public class ContactRVAdapter extends RecyclerView.Adapter<ContactRVAdapter.ContactViewHolder> {

    List<Contact> contactList;
    Context context;

    public ContactRVAdapter(List<Contact> contacts, Context ctx) {
        contactList = contacts;
        context = ctx;
    }

    public Context getContext() {
        return context;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        TextView contactName;
        TextView contactNumber;

        ContactViewHolder(final View itemView) {
            super(itemView);
            contactName = (TextView) itemView.findViewById(R.id.contact_name);
            contactNumber = (TextView) itemView.findViewById(R.id.contact_number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Contact contact = contactList.get(getAdapterPosition());
                    if (deleteContact(contact)) {
                        contactList.remove(getAdapterPosition());
                        notifyItemRemoved(getAdapterPosition());
                    } else {
                        Toast.makeText(getContext(), "Oops! something went wrong. Unable to delete contact", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @Override
    public ContactRVAdapter.ContactViewHolder onCreateViewHolder(ViewGroup viewGroup, int item) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View contactView = inflater.inflate(R.layout.contact_item, viewGroup, false);
        ContactRVAdapter.ContactViewHolder viewHolder = new ContactRVAdapter.ContactViewHolder(contactView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ContactRVAdapter.ContactViewHolder holder, int position) {
        Contact contact = contactList.get(position);
        String name = "";
        holder.contactName.setText((name = contact.getContactName()).length() > 32 ? name.substring(0, 32) : name);
        holder.contactNumber.setText(contact.getContactNumber());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }


    boolean deleteContact(Contact contact) {
        boolean isDeleted = false;

        if (new ContactModel(getContext()).deleteContact(contact) ){
            isDeleted = true;
        }

        return isDeleted;
    }
}

