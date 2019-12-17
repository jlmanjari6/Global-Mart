package com.example.globalmart_teama.GridItem;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.globalmart_teama.R;
import com.example.globalmart_teama.models.ProductsModel;

import java.util.List;

public class ProductsGridItem extends BaseAdapter {
    private List<ProductsModel> mProductModelList;
    private Activity mActivity;

    public ProductsGridItem(Activity activity, List<ProductsModel> mProductModelList) {
        this.mActivity = activity;
        this.mProductModelList = mProductModelList;
    }

    @Override
    public int getCount() {
        return mProductModelList.size();
    }

    @Override
    public Object getItem(int position) {
        return mProductModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.grid_item_products, null);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.txtProduct);
        textView.setText(mProductModelList.get(position).getProductName());

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imgProduct);
        String imageID = mProductModelList.get(position).getProductImageID();

        int id = mActivity.getResources().getIdentifier(imageID, "drawable", mActivity.getPackageName());
        imageView.setImageResource(id);

        return convertView;

    }
}
