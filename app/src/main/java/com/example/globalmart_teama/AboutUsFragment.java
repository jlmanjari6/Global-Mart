package com.example.globalmart_teama;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutUsFragment extends Fragment {


    public AboutUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        TextView txtAboutUs = (TextView) view.findViewById(R.id.txtAboutUs);
        txtAboutUs.setText("Global Mart allows you to walk away from the drudgery of grocery shopping in an unfamiliar place and welcomes an easy and relaxed way of" +
                " browsing and shopping for groceries. " +
                "\n" + "\n" +
                "Discover International products and shop for all your food and grocery needs from the" +
                " comfort of your home or office. " +
                "\n" + "\n" +
                "No more getting stuck in traffic jams, paying for parking, standing in long queues and" +
                " carrying heavy bags – get everything you need, when you need, right at your doorstep." +
                "\n" + "\n" +
                "Food shopping at a new place is now easy as every product on your monthly shopping list, is now available online at Global Mart");
        return view;
    }

}
