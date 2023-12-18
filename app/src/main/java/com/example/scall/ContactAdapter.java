package com.example.scall;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter {
    private List<ContactModel> contactList;

    public ContactAdapter(List<ContactModel> contactList) {
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_contact, parent, false);
        return new ContactViewHolder(itemView);
    }

//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        ContactModel contact = contactList.get(position);
//        holder.textViewName.setText(contact.getName());
//        holder.textViewPhoneNumber.setText(contact.getPhoneNumber());
//    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        ContactModel contact = contactList.get(position);
        holder.textViewName.setText(contact.getName());
        holder.textViewPhoneNumber.setText(contact.getPhoneNumber());

        // Menangani klik pada item kontak
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendSms(contact.getPhoneNumber());
            }
        });
    }

    private void sendSms(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));
        if (intent.resolveActivity(itemView.getContext().getPackageManager()) != null) {
            itemView.getContext().startActivity(intent);
        } else {
            Toast.makeText(itemView.getContext(), "Tidak ada aplikasi SMS yang terinstall", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    static class ContactViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewPhoneNumber;

        ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewPhoneNumber = itemView.findViewById(R.id.textViewPhoneNumber);
        }
    }
}
