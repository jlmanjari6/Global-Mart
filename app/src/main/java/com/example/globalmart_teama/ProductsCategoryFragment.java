package com.example.globalmart_teama;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.example.globalmart_teama.GridItem.ProductsGridItem;
import com.example.globalmart_teama.models.Database;
import com.example.globalmart_teama.models.ProductsModel;

import java.util.ArrayList;
import java.util.List;

public class ProductsCategoryFragment extends Fragment {

    List<ProductsModel> productsModelList = new ArrayList<>();

    public ProductsCategoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_categories_products, container, false);
        GridView productsGrid = (GridView) view.findViewById(R.id.grid);
        TextView txtCategoryType = (TextView) view.findViewById(R.id.txtCategoryType);

        final Database database = new Database(getActivity());

        Bundle dataBundle = getArguments();
        String categoryType = dataBundle.getString("CategoryType", "N/A");
        if (categoryType.equals("Beverages")) {
            txtCategoryType.setText("BEVERAGES");
            productsModelList = database.getProductsByCategory("Beverages");
        } else if (categoryType.equals("Groceries")) {
            txtCategoryType.setText("GROCERIES");
            productsModelList = database.getProductsByCategory("Groceries");
        } else if (categoryType.equals("Fruits&Vegetables")) {
            txtCategoryType.setText("FRUITS & VEGETABLES");
            productsModelList = database.getProductsByCategory("Fruits&Vegetables");
        }

        productsGrid.setAdapter(new ProductsGridItem(getActivity(), productsModelList));

        productsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adaptor, View v, int listIndex, long arg3) {

                Bundle detailsBundle = new Bundle();

                ProductsModel currProduct = productsModelList.get(listIndex);

                detailsBundle.putInt("productID", currProduct.getProductID());
                detailsBundle.putString("productName", currProduct.getProductName());
                detailsBundle.putString("productDesc", currProduct.getProductDesc());
                detailsBundle.putInt("productPrice", currProduct.getProductPrice());
                detailsBundle.putString("productImageId", currProduct.getProductImageID());

                ProductDetailsFragment fragment = new ProductDetailsFragment();
                fragment.setArguments(detailsBundle);

                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(((ViewGroup) (getView().getParent())).getId()
                        , fragment, "PRODUCT DESCRIPTION");
                ft.addToBackStack(null);
                ft.commit();
                ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Product Details");
            }
        });

        return view;
    }

}
