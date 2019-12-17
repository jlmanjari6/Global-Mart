package com.example.globalmart_teama.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "globalmart.models";
    public static final int DATABASE_VERSION = 1;

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String DOUBLE_TYPE = " DOUBLE";
    private static final String COMMA_SEP = ",";

    public static abstract class ProductsEntry implements BaseColumns {
        public static final String TABLE_NAME = "products";
        public static final String COLUMN_PRODUCT_ID = "product_id";
        public static final String COLUMN_PRODUCT_NAME = "product_name";
        public static final String COLUMN_PRODUCT_DESC = "product_desc";
        public static final String COLUMN_PRODUCT_PRICE = "product_price";
        public static final String COLUMN_PRODUCT_IMAGE_ID = "product_image_id";
        public static final String COLUMN_COUNTRY_NAME = "product_country_name";
        public static final String COLUMN_CATEGORY_NAME = "product_category_name";
        public static final String COLUMN_PRODUCT_CODE = "product_code";
    }

    public static abstract class OrdersEntry implements BaseColumns {
        public static final String TABLE_NAME = "orders";
        public static final String COLUMN_ORDER_ID = "order_id";
        public static final String COLUMN_PRODUCT_ID = "product_id";
        public static final String COLUMN_PRODUCT_NAME = "product_name";
        public static final String COLUMN_PRODUCT_IMAGE_ID = "product_image_id";
        public static final String COLUMN_QUANTITY_ = "product_quantity";
        public static final String COLUMN_CUSTOMER_ID = "customer_id";
        public static final String COLUMN_UNIT_PRICE = "unit_price";
        public static final String COLUMN_TOTAL_PRICE = "total_price";
    }

    private static final String SQL_CREATE_PRODUCT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + ProductsEntry.TABLE_NAME + " (" +
                    ProductsEntry.COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY" + COMMA_SEP +
                    ProductsEntry.COLUMN_PRODUCT_NAME + TEXT_TYPE + COMMA_SEP +
                    ProductsEntry.COLUMN_PRODUCT_DESC + TEXT_TYPE + COMMA_SEP +
                    ProductsEntry.COLUMN_PRODUCT_PRICE + INTEGER_TYPE + COMMA_SEP +
                    ProductsEntry.COLUMN_PRODUCT_IMAGE_ID + TEXT_TYPE + COMMA_SEP +
                    ProductsEntry.COLUMN_CATEGORY_NAME + TEXT_TYPE + COMMA_SEP +
                    ProductsEntry.COLUMN_COUNTRY_NAME + TEXT_TYPE + COMMA_SEP +
                    ProductsEntry.COLUMN_PRODUCT_CODE + TEXT_TYPE +" )";


    private static final String SQL_CREATE_ORDER_TABLE =
            "CREATE TABLE IF NOT EXISTS " + OrdersEntry.TABLE_NAME + " (" +
                    OrdersEntry.COLUMN_ORDER_ID + TEXT_TYPE + COMMA_SEP +
                    OrdersEntry.COLUMN_PRODUCT_ID + INTEGER_TYPE + COMMA_SEP +
                    OrdersEntry.COLUMN_PRODUCT_NAME + TEXT_TYPE + COMMA_SEP +
                    OrdersEntry.COLUMN_PRODUCT_IMAGE_ID + TEXT_TYPE + COMMA_SEP +
                    OrdersEntry.COLUMN_UNIT_PRICE + DOUBLE_TYPE + COMMA_SEP +
                    OrdersEntry.COLUMN_QUANTITY_+ INTEGER_TYPE + COMMA_SEP +
                    OrdersEntry.COLUMN_TOTAL_PRICE + DOUBLE_TYPE + COMMA_SEP +
                    OrdersEntry.COLUMN_CUSTOMER_ID + INTEGER_TYPE +" )";


    private static final String SQL_DELETE_SELECTED_ORDER =
            "DROP TABLE IF EXISTS " + ProductsEntry.TABLE_NAME;


    private static final String SQL_DELETE_PRODUCT =
            "DROP TABLE IF EXISTS " + ProductsEntry.TABLE_NAME;

    private static final String SQL_DELETE_ORDER =
            "DROP TABLE IF EXISTS " + OrdersEntry.TABLE_NAME;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_PRODUCT_TABLE);
        db.execSQL(SQL_CREATE_ORDER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_PRODUCT);
        db.execSQL(SQL_DELETE_ORDER);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion){
        onUpgrade(db, oldVersion, newVersion);
    }

    public void deleteOrder(String orderId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBHelper.OrdersEntry.TABLE_NAME, DBHelper.OrdersEntry.COLUMN_ORDER_ID + "=?", new String[]{ orderId});
    }
}
