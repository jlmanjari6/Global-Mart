package com.example.globalmart_teama.GridItem;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.globalmart_teama.R;
import com.example.globalmart_teama.models.DBHelper;
import com.example.globalmart_teama.models.OrderModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrdersGridItem extends BaseAdapter {

    HashMap<String, List<OrderModel>> orderMap = new HashMap<>();
    private Activity mActivity;
    PopupWindow popupWindow;
    int counter = 0;

    public OrdersGridItem(Activity activity, HashMap<String, List<OrderModel>> orderMap) {
        this.mActivity = activity;
        this.orderMap = orderMap;
    }

    @Override
    public int getCount() {
        return orderMap.size();
    }

    @Override
    public Object getItem(int position) {
        return orderMap.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.grid_item_orders, null);
        }

        if (counter >= 1) {
            counter = 0;
            return convertView;
        }
        if (position == 0) {
            counter++;
        }


        final ImageView cancelBtn = convertView.findViewById(R.id.btnGridCancel);
        final ImageView trackOrderBtn = convertView.findViewById(R.id.btnGridTrack);
        final LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.linearLayout);
        final TextView txt_confirmation_cancelOrder = (TextView) convertView.findViewById(R.id.txtOrderCancelConfirm);

        String orderId = orderMap.keySet().toArray()[position].toString();
        double totalPrice = 0.0;
        List<OrderModel> productsList = (ArrayList<OrderModel>) orderMap.values().toArray()[position];

        // a vertical linear layout to hold the order id textview and the total price textview.
        final LinearLayout linearHeaderLayout = (LinearLayout) convertView.findViewById(R.id.linearHeaderLayout);
        linearHeaderLayout.setOrientation(LinearLayout.VERTICAL);

        // a vertical linear layout to hold all the products in the current order id.
        //  It contains a linear layout linearLayoutProductRow that is dynamically generated to hold the product details to display under the order id.
        LinearLayout lProductLayout = (LinearLayout) convertView.findViewById(R.id.linearProductLayout);

        // for each product in the current order, dynamically generate  product rows using linearLayoutProductRow to display the product image and its details.
        for (OrderModel product : productsList) {

            // a horizontal linear layout which adds one product under the current order id for each time it enters into the loop.
            //  This linear layout contains two internal linear layouts to hold the image and other details(product name, unit price, quantity ordered). 
            LinearLayout linearLayoutProductRow = new LinearLayout(parent.getContext());
            linearLayoutProductRow.setOrientation(LinearLayout.HORIZONTAL);

            //  an internal linear layout to hold the image of the product
            LinearLayout lImageLayout = new LinearLayout(parent.getContext());
            lImageLayout.setOrientation(LinearLayout.VERTICAL);

            // an internal vertical linear layout to hold the details(product name, unit price, quantity ordered) of the product
            LinearLayout lProductDetails = new LinearLayout(parent.getContext());
            lProductDetails.setOrientation(LinearLayout.VERTICAL);
            int pQty = 0;
            String imgId = product.getProductImageID();
            String pName = product.getProductName();
            if (product.getQuantity() > 0) {
                pQty = product.getQuantity();
            }
            double unitPrice = product.getUnitPrice();
            totalPrice = product.getTotalPrice();

            //set product image
            ImageView imgProduct = new ImageView(parent.getContext());
            LayoutParams imgProductParams = new LayoutParams(300, 300);
            imgProduct.setLayoutParams(imgProductParams);
            int id = mActivity.getResources().getIdentifier(imgId, "drawable", mActivity.getPackageName());
            imgProduct.setImageResource(id);
            if (pQty > 0) {
                lImageLayout.addView(imgProduct);

                //set product name
                TextView txtProductName = new TextView(parent.getContext());
                txtProductName.setText(pName);
                txtProductName.setTextSize(18);
                lProductDetails.addView(txtProductName);

                //set product quantity
                TextView txtProductQty = new TextView(parent.getContext());
                String txtQty = pQty + "";
                txtProductQty.setText("Quantity: " + txtQty);
                txtProductQty.setTextSize(18);
                lProductDetails.addView(txtProductQty);

                //set product unit price
                TextView txtProductUnitPrice = new TextView(parent.getContext());
                String txtUnitPrice = unitPrice + "";
                txtProductUnitPrice.setText("$" + txtUnitPrice);
                txtProductUnitPrice.setTextSize(18);
                lProductDetails.addView(txtProductUnitPrice);

                // add product image and details to the row
                linearLayoutProductRow.addView(lImageLayout);
                linearLayoutProductRow.addView(lProductDetails);
            }
            // add product row along with the list of other products of the current order id in the grid
            lProductLayout.addView(linearLayoutProductRow);
        }

        // set current order id header to the grid row
        final TextView txtGridOrderID = new TextView(parent.getContext());
        txtGridOrderID.setText("Order ID: " + orderId);
        txtGridOrderID.setTextSize(24);
        txtGridOrderID.setTypeface(null, Typeface.BOLD);
        linearHeaderLayout.addView(txtGridOrderID);

        // set total price of the current order id
        final TextView txtTotalPrice = new TextView(parent.getContext());
        String txtTotal = totalPrice + "";
        txtTotalPrice.setText("Total Price: $" + txtTotal);
        txtTotalPrice.setTextSize(24);
        linearHeaderLayout.addView(txtTotalPrice);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                ColorDrawable cd = new ColorDrawable(Color.parseColor("#FFFFFF"));
                linearLayout.setForeground(cd);
                linearLayout.getForeground().setAlpha(220);

                DBHelper dbQuery = new DBHelper(mActivity);
                dbQuery.deleteOrder(orderMap.keySet().toArray()[position].toString());
                LayoutInflater layoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popUpView = layoutInflater.inflate(R.layout.popup_window_cancel_order_success, null);
                ImageView closePopupBtn = (ImageView) popUpView.findViewById(R.id.closePopupBtn);
                popupWindow = new PopupWindow(popUpView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);
                closePopupBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        txt_confirmation_cancelOrder.setVisibility(View.VISIBLE);
                        cancelBtn.setVisibility(View.INVISIBLE);
                        trackOrderBtn.setVisibility(View.INVISIBLE);

                        linearLayout.getForeground().setAlpha(0);
                        popupWindow.dismiss();
                    }
                });
            }
        });

        trackOrderBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                ColorDrawable cd = new ColorDrawable(Color.parseColor("#FFFFFF"));
                linearLayout.setForeground(cd);
                linearLayout.getForeground().setAlpha(220);
                LayoutInflater layoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popUpView = layoutInflater.inflate(R.layout.popup_window_track_order, null);
                ImageView closePopupBtn = (ImageView) popUpView.findViewById(R.id.closePopupBtnTrackOrder);
                popupWindow = new PopupWindow(popUpView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(linearLayout, Gravity.CENTER, 0, 0);
                closePopupBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        linearLayout.getForeground().setAlpha(0);
                        popupWindow.dismiss();

                    }
                });
            }
        });

        return convertView;
    }
}