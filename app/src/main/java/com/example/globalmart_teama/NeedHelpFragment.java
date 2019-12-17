package com.example.globalmart_teama;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class NeedHelpFragment extends Fragment {

    private TextView contact1;
    private TextView contact2;
    private TextView contact3;
    private TextView contact4;
    private TextView contact5;
    private TextView italian_contact1;
    private TextView italian_contact2;
    private TextView italian_contact3;
    private TextView chinese_contact1;
    private TextView chinese_contact2;
    private TextView chinese_contact3;
    private TextView japanese_contact1;
    private TextView japanese_contact2;
    private TextView japanese_contact3;
    private TextView contactJJMartStoreHalifax;

    private String phone;

    public NeedHelpFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_need_help, container, false);
        // Get the widget reference from xml layout
        contact1 = v.findViewById(R.id.contactview1);
        contact2 = v.findViewById(R.id.contactview2);
        contact3 = v.findViewById(R.id.contactview3);
        contact4 = v.findViewById(R.id.contactview4);
        contact5 = v.findViewById(R.id.contactview5);
        italian_contact1 = v.findViewById(R.id.italian_contactview1);
        italian_contact2 = v.findViewById(R.id.italian_contactview2);
        italian_contact3 = v.findViewById(R.id.italian_contactview3);
        chinese_contact1 = v.findViewById(R.id.chinese_contactview1);
        chinese_contact2 = v.findViewById(R.id.chinese_contactview2);
        chinese_contact3 = v.findViewById(R.id.chinese_contactview3);
        japanese_contact1 = v.findViewById(R.id.Japanese_contactview1);
        japanese_contact2 = v.findViewById(R.id.Japanese_contactview2);
        japanese_contact3 = v.findViewById(R.id.Japanese_contactview3);
        contactJJMartStoreHalifax = v.findViewById(R.id.Japanese_contactview4);

        // Inflate the layout for this fragment
        // Set a click listener for the button
        contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = (String) contact1.getText();
                openDialer();
            }
        });
        contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = (String) contact1.getText();
                openDialer();
            }
        });
        contact3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = (String) contact1.getText();
                openDialer();
            }
        });
        contact4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = (String) contact1.getText();
                openDialer();
            }
        });
        contact5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = (String) contact1.getText();
                openDialer();
            }
        });

        italian_contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = (String) contact1.getText();
                openDialer();
            }
        });
        italian_contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = (String) contact1.getText();
                openDialer();
            }
        });
        italian_contact3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = (String) contact1.getText();
                openDialer();
            }
        });

        chinese_contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = (String) contact1.getText();
                openDialer();
            }
        });
        chinese_contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = (String) contact1.getText();
                openDialer();
            }
        });
        chinese_contact3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = (String) contact1.getText();
                openDialer();
            }
        });

        japanese_contact1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = (String) contact1.getText();
                openDialer();
            }
        });
        japanese_contact2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = (String) contact1.getText();
                openDialer();
            }
        });
        japanese_contact3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = (String) contact1.getText();
                openDialer();
            }
        });
        contactJJMartStoreHalifax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phone = (String) contact1.getText();
                openDialer();
            }
        });
        return v;

    }

    // to send the contact number to the dial pad via intent
    protected void openDialer() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        // Send phone number to intent as data
        intent.setData(Uri.parse("tel:" + phone));
        // Start the dialer app activity with number
        startActivity(intent);
    }
}
