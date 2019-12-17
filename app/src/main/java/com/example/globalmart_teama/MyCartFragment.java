package com.example.globalmart_teama;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.globalmart_teama.GridItem.MyCartGridItem;
import com.example.globalmart_teama.models.Database;
import com.example.globalmart_teama.models.ProductsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class MyCartFragment extends Fragment {

    SharedPreferences shref;
    Gson gson;
    SharedPreferences.Editor editor;
    double totalPrice;

    public MyCartFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_my_cart, container, false);

        ArrayList<ProductsModel> lstArrayList = null;
        GridView productsOrderedGrid = (GridView) view.findViewById(R.id.cartGrid);
        TextView txtTotalPrice = (TextView) view.findViewById(R.id.txtTotalPrice);
        TextView lblTotalPrice = (TextView) view.findViewById(R.id.lblTotal);
        ImageView btnPayment = (ImageView) view.findViewById(R.id.btnPayment);

        // get products from shared preferences
        shref = getActivity().getSharedPreferences("CARTLIST", Context.MODE_PRIVATE);
        gson = new Gson();
        lstArrayList = gson.fromJson(shref.getString("CARTLIST", ""),
                new TypeToken<List<ProductsModel>>() {
                }.getType());

        if (lstArrayList == null) {
            lstArrayList = new ArrayList<>();
        }

        if (lstArrayList.size() != 0) {
            // set the data to grid
            productsOrderedGrid.setAdapter(new MyCartGridItem(getActivity(), lstArrayList));

            //print total price
            int price = 0;
            for (ProductsModel p : lstArrayList) {
                price += p.getProductPrice() * p.getProductCartQuantity();
            }
            totalPrice = price + (0.15 * price);
            TextView txtPrice = (TextView) view.findViewById(R.id.txtTotalPrice);
            txtPrice.setText("$" + totalPrice);

            //set visibility of labels and button
            txtTotalPrice.setVisibility(View.VISIBLE);
            lblTotalPrice.setVisibility(View.VISIBLE);
            btnPayment.setVisibility(View.VISIBLE);
        } else {
            txtTotalPrice.setVisibility(View.INVISIBLE);
            lblTotalPrice.setVisibility(View.INVISIBLE);
            btnPayment.setVisibility(View.INVISIBLE);
        }

        // "proceed to payment" button click handler
        btnPayment.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String orderID = generateOrderID();

                updateOrdersTable(orderID);

                clearCart();

                Bundle detailsBundle = new Bundle();
                detailsBundle.putString("orderID", orderID);
                PaymentConfirmationFragment fragment = new PaymentConfirmationFragment();
                fragment.setArguments(detailsBundle);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(((ViewGroup) (getView().getParent())).getId()
                        , fragment, "PAYMENT");
                ft.addToBackStack(null);
                ft.commit();
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Payment Confirmation");
            }
        });

        return view;
    }

    // to clear the cart after ordering is completed
    private void clearCart() {
        SharedPreferences preferences = getActivity().getSharedPreferences("CARTLIST", Context.MODE_PRIVATE);
        preferences.edit().clear().commit();
    }

    // to store the order details in the database once payment is done
    private void updateOrdersTable(String orderID) {
        final Database database = new Database(getActivity());
        shref = getActivity().getSharedPreferences("CARTLIST", Context.MODE_PRIVATE);
        gson = new Gson();
        String response = shref.getString("CARTLIST", "");
        List<ProductsModel> lstArrayList = gson.fromJson(response,
                new TypeToken<List<ProductsModel>>() {
                }.getType());

        for (ProductsModel product : lstArrayList) {
            int pID = product.getProductID();
            String pName = product.getProductName();
            String pImgId = product.getProductImageID();
            int pUnitPrice = product.getProductPrice();
            int pQty = product.getProductCartQuantity();

            database.createOrder(orderID, pID, pName, pImgId, pUnitPrice, pQty, totalPrice, 123);
        }
    }

    // to generate random order id once the order is completed
    private String generateOrderID() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        Random rnd = new Random();
        while (sb.length() < 6) { // length of the random string.
            int index = (int) (rnd.nextFloat() * chars.length());
            sb.append(chars.charAt(index));
        }
        String orderID = sb.toString();
        return orderID;
    }

}
