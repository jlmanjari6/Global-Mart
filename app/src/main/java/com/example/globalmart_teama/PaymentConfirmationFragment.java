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
public class PaymentConfirmationFragment extends Fragment {


    public PaymentConfirmationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_payment_confirmation, container, false);;

        Bundle dataBundle = getArguments();
        String orderID = dataBundle.getString("orderID", "N/A");
        TextView txtOrderID = view.findViewById(R.id.txtOrderID);
        txtOrderID.setText(orderID);

        return view;
    }

}
