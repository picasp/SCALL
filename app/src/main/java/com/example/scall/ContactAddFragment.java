package com.example.scall;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactAddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactAddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContactAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactAddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactAddFragment newInstance(String param1, String param2) {
        ContactAddFragment fragment = new ContactAddFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private EditText editTextName;
    private EditText editTextPhoneNumber;
    private Button buttonAddContact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_add, container, false);

        editTextName = view.findViewById(R.id.editTextName);
        editTextPhoneNumber = view.findViewById(R.id.editTextPhoneNumber);
        buttonAddContact = view.findViewById(R.id.buttonAddContact);

        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });

        return view;
    }

    private void addContact() {
        String name = editTextName.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();

        if (!name.isEmpty() && !phoneNumber.isEmpty()) {
            // Mendapatkan daftar kontak dari Shared Preferences
            List<ContactModel> contacts = SharedPref.getContacts(requireContext());

            // Menambahkan kontak baru
            ContactModel newContact = new ContactModel(name, phoneNumber);
            contacts.add(newContact);

            // Menyimpan daftar kontak yang diperbarui ke Shared Preferences
            SharedPref.saveContacts(requireContext(), contacts);

            // Kembali ke fragment daftar kontak
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new ContactListFragment())
                    .commit();
        } else {
            Toast.makeText(requireContext(), "Nama dan nomor telepon tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }
    }
}