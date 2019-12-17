package com.example.globalmart_teama.GridItem;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.globalmart_teama.MyCartFragment;
import com.example.globalmart_teama.R;
import com.example.globalmart_teama.models.ProductsModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MyCartGridItem extends BaseAdapter {

    private List<ProductsModel> cartList = new ArrayList<>();
    private Activity mActivity;
    SharedPreferences shref;
    Gson gson;
    SharedPreferences.Editor editor;

    public MyCartGridItem(Activity activity, List<ProductsModel> cartList) {
        this.mActivity = activity;
        this.cartList = cartList;
    }

    @Override
    public int getCount() {
        return cartList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_item_mycart, null);
        }

        final View view = layoutInflater.inflate(R.layout.fragment_my_cart, null);

        // product name
        final TextView textView = (TextView) convertView.findViewById(R.id.txtProduct);
        textView.setText(cartList.get(position).getProductName());

        // product image
        ImageView imageView = (ImageView) convertView.findViewById(R.id.imgProduct);
        String imageID = cartList.get(position).getProductImageID();
        int id = mActivity.getResources().getIdentifier(imageID, "drawable", mActivity.getPackageName());
        imageView.setImageResource(id);

        // product price
        TextView textViewPrice = (TextView) convertView.findViewById(R.id.txtCartPrice);
        textViewPrice.setText("$" + cartList.get(position).getProductPrice());

        // product quantity
        final TextView textViewQty = (TextView) convertView.findViewById(R.id.txtQuantity);
        textViewQty.setText("" + cartList.get(position).getProductCartQuantity());

        ImageView btnMinus = (ImageView) convertView.findViewById(R.id.btnminus);
        ImageView btnPlus = (ImageView) convertView.findViewById(R.id.btnplus);

        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(textViewQty.getText().toString());
                if (qty != 0) {
                    qty -= 1;
                    String x = qty + "";
                    textViewQty.setText(x);

                    //get shared preferences and update quantities
                    shref = mActivity.getSharedPreferences("CARTLIST", Context.MODE_PRIVATE);
                    gson = new Gson();
                    ArrayList<ProductsModel> lst = gson.fromJson(shref.getString("CARTLIST", ""),
                            new TypeToken<List<ProductsModel>>() {
                            }.getType());

                    for (ProductsModel p : lst) {
                        if (p.getProductName().equals(textView.getText().toString())) {
                            if (p.getProductCartQuantity() != 0) {
                                p.setProductCartQuantity(p.getProductCartQuantity() - 1);
                            }
                            break;
                        }
                    }

                    //put it back
                    editor = shref.edit();
                    editor.remove("CARTLIST").commit();
                    editor.putString("CARTLIST", gson.toJson(lst)).commit();

                    //update total price
                    int price = 0;
                    for (ProductsModel p : lst) {
                        price += p.getProductPrice() * p.getProductCartQuantity();
                    }
                    double totalPrice = price + (0.15 * price);

                    final TextView txtTotalPrice = (TextView) view.findViewById(R.id.txtTotalPrice);
                    txtTotalPrice.setText("$" + totalPrice);

                    // refresh fragment to update total price
                    FragmentManager fm = ((AppCompatActivity) parent.getContext()).getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    final MyCartFragment f = (MyCartFragment) fm.findFragmentByTag("MYCART");
                    ft.detach(f).attach(f).addToBackStack(null).commit();
                }
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int qty = Integer.parseInt(textViewQty.getText().toString());
                qty += 1;
                String x = qty + "";
                textViewQty.setText(x);

                //get shared preferences and update quantities
                shref = mActivity.getSharedPreferences("CARTLIST", Context.MODE_PRIVATE);
                gson = new Gson();
                ArrayList<ProductsModel> lst2 = gson.fromJson(shref.getString("CARTLIST", ""),
                        new TypeToken<List<ProductsModel>>() {
                        }.getType());

                for (ProductsModel p : lst2) {
                    if (p.getProductName().equals(textView.getText().toString())) {
                        p.setProductCartQuantity(p.getProductCartQuantity() + 1);
                        break;
                    }
                }

                //put it back
                editor = shref.edit();
                editor.remove("CARTLIST").commit();
                editor.putString("CARTLIST", gson.toJson(lst2)).commit();

                //update total price
                int price2 = 0;
                for (ProductsModel p : lst2) {
                    price2 += p.getProductPrice() * p.getProductCartQuantity();
                }
                double totalPrice2 = price2 + (0.15 * price2);
                final TextView txtTotalPrice = (TextView) view.findViewById(R.id.txtTotalPrice);
                txtTotalPrice.setText("$" + totalPrice2);

                // refresh fragment to update total price
                FragmentManager fm = ((AppCompatActivity) parent.getContext()).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                final MyCartFragment f = (MyCartFragment) fm.findFragmentByTag("MYCART");
                ft.detach(f).attach(f).addToBackStack(null).commit();
            }
        });

        return convertView;
    }
}