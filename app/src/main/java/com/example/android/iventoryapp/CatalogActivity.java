package com.example.android.iventoryapp;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.iventoryapp.database.InventoryDbHelper;

import com.example.android.iventoryapp.database.InventoryContract.newInventoryEntery;


public class CatalogActivity extends AppCompatActivity {


    private InventoryDbHelper mDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalouge_activity);

        // Pending update UI update to include button that add action button that open an editor activity

        mDBHelper = new InventoryDbHelper(this);
    }


    @Override
    protected void onStart(){
        super.onStart();
        dataBaseInfo();
    }

    private void dataBaseInfo(){

        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        String[] dataProjection = {
                newInventoryEntery._ID,
                newInventoryEntery.COLUMN_PRODUCT_NAME,
                newInventoryEntery.COLUMN_PRODUCT_PRICE,
                newInventoryEntery.COLUMN_PRODUCT_QUANTITY,
                newInventoryEntery.COLUMN_PRODUCT_SUPPLIER_NAME,
                newInventoryEntery.COLUMN_PRODUCT_SUPPLIER_PHONENUMBER};

        Cursor inventoryCursor = db.query(

                newInventoryEntery.TABLE_NAME,
                dataProjection,
                null,
                null,
                null,
                null,
                null);

        TextView display = (TextView) findViewById(R.id.inventory_item_text);


        try{

            display.setText("The Inventory Table has " + inventoryCursor.getCount() + " items.\n\n" );
            display.append(newInventoryEntery._ID + " " +
            newInventoryEntery.COLUMN_PRODUCT_NAME + " " +
            newInventoryEntery.COLUMN_PRODUCT_PRICE + " " +
            newInventoryEntery.COLUMN_PRODUCT_QUANTITY + " " +
            newInventoryEntery.COLUMN_PRODUCT_SUPPLIER_NAME + " " +
            newInventoryEntery.COLUMN_PRODUCT_SUPPLIER_PHONENUMBER + "\n");

            int idColumIndex = inventoryCursor.getColumnIndex(newInventoryEntery._ID);
            int productNameColumIndex = inventoryCursor.getColumnIndex(newInventoryEntery.COLUMN_PRODUCT_NAME);
            int productPriceColumIndex = inventoryCursor.getColumnIndex(newInventoryEntery.COLUMN_PRODUCT_PRICE);
            int productQuantityColumIndex = inventoryCursor.getColumnIndex(newInventoryEntery.COLUMN_PRODUCT_QUANTITY);
            int supplierNameColumIndex = inventoryCursor.getColumnIndex(newInventoryEntery.COLUMN_PRODUCT_SUPPLIER_NAME);
            int supplierPhoneNumberColumIndex = inventoryCursor.getColumnIndex(newInventoryEntery.COLUMN_PRODUCT_SUPPLIER_PHONENUMBER);

            while (inventoryCursor.moveToNext()){

                int currentProductId = inventoryCursor.getInt(idColumIndex);
                String currentProductName = inventoryCursor.getString(productNameColumIndex);
                int currentProductPrice = inventoryCursor.getInt(productPriceColumIndex);
                int currentQuantity = inventoryCursor.getInt(productQuantityColumIndex);
                String currentSupplierName = inventoryCursor.getString(supplierNameColumIndex);
                String currentSupplierPhoneNumber = inventoryCursor.getString(supplierPhoneNumberColumIndex);

                display.append(("\n Table:" + currentProductId + " - " +
                currentProductName + " - " +
                currentProductPrice + " - " +
                currentQuantity + " - " +
                currentSupplierName + " - " +
                currentSupplierPhoneNumber ));

            }


        } finally {


            inventoryCursor.close();

        }














    }

}
