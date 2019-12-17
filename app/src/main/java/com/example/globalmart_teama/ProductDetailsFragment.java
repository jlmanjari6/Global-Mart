package com.example.globalmart_teama;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.globalmart_teama.models.ProductsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailsFragment extends Fragment {

    ImageView imgProduct;
    TextView imgDescription;
    TextView imgProductTitle;
    TextView imgProductPrice;
    TextView txtAddedtoCart;

    ArrayList<ProductsModel> cartList = new ArrayList<>();

    public ProductDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);

        imgDescription = view.findViewById(R.id.txtDescriptiontext);
        imgProductTitle = view.findViewById(R.id.imgProductName);
        imgProductPrice = view.findViewById(R.id.imgProductPrice);
        imgProduct = view.findViewById(R.id.imgProduct);
        txtAddedtoCart = view.findViewById(R.id.cart_added_txt);

        Bundle dataBundle = getArguments();
        final int productID = dataBundle.getInt("productID", -1);
        final String name = dataBundle.getString("productName", "N/A");
        final String desc = dataBundle.getString("productDesc", "N/A");
        final int price = dataBundle.getInt("productPrice", -1);
        final String imgId = dataBundle.getString("productImageId", "N/A");

        imgProductTitle.setText(name);
        imgDescription.setText(desc);
        imgProductPrice.setText("$" + price);

        int id = this.getActivity().getResources().getIdentifier(imgId, "drawable", this.getActivity().getPackageName());
        imgProduct.setImageResource(id);

        // Add to Cart functionality
        final ImageView btnAddToCart = (ImageView) view.findViewById(R.id.btnAddtoCart);
        btnAddToCart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                btnAddToCart.setVisibility(View.INVISIBLE);
                txtAddedtoCart.setVisibility(View.VISIBLE);

                ProductsModel product= new ProductsModel(productID, name, desc, price, imgId, null,null, null);

                try {
                    SharedPreferences shref = getActivity().getSharedPreferences("CARTLIST", Context.MODE_PRIVATE);
                    Gson json = new Gson();

                    String response=shref.getString("CARTLIST" , "");

                    if(!response.isEmpty()) { // if list already exist. if exist, get list, add new product to list and put it back
                        ArrayList<ProductsModel> existingList = json.fromJson(response,
                                new TypeToken<List<ProductsModel>>() {
                                }.getType());

                        boolean found = false;
                        for (ProductsModel p : existingList) {
                            if (p.getProductID() == product.getProductID()) {
                                p.setProductCartQuantity(p.getProductCartQuantity() + 1);
                                found = true;
                                break;
                            }
                        }

                        if (!found) {
                            product.setProductCartQuantity(1);
                            existingList.add(product);
                        }

                        // put it back
                        SharedPreferences.Editor editor;
                        editor = shref.edit();
                        editor.remove("CARTLIST").commit();
                        String newJson = json.toJson(existingList);
                        editor.putString("CARTLIST", newJson);
                        editor.commit();
                    }
                    else {  // when list not exist
                        ArrayList<ProductsModel> cartList = new ArrayList<ProductsModel>();
                        product.setProductCartQuantity(1);
                        cartList.add(product);

                        SharedPreferences.Editor editor;
                        editor = shref.edit();
                        String res = json.toJson(cartList);
                        editor.putString("CARTLIST", res);
                        editor.commit();
                    }
                } catch (Exception e){
                    Log.i("exc", "exception");
                }
            }
        });

        return view;
    }

}