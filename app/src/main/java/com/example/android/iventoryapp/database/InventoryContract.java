package com.example.android.iventoryapp.database;

import android.provider.BaseColumns;

public class InventoryContract {

    private InventoryContract(){}



    public static final class newInventoryEntery implements BaseColumns {

        public final static String TABLE_NAME = "invetoryItems";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_PRODUCT_NAME = "name";

        public final static String COLUMN_PRODUCT_PRICE = "price";

        public final static String COLUMN_PRODUCT_QUANTITY = "quantity";

        public final static String COLUMN_PRODUCT_SUPPLIER_NAME = "supplier name";

        public final static String COLUMN_PRODUCT_SUPPLIER_PHONENUMBER = "supplier phonenumber";


        }

}
