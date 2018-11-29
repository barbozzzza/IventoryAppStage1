package com.example.android.iventoryapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.iventoryapp.database.InventoryDbHelper;

import com.example.android.iventoryapp.database.InventoryContract.newInventoryEntery;


public class CatalogActivity extends AppCompatActivity {
    private InventoryDbHelper mDBHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.catalogue_menu);

        // Pending update UI update to include button that add action button that open an editor activity

        mDBHelper = new InventoryDbHelper(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        dataBaseInfo();
    }

    private void addInvetoryItem() {

        SQLiteDatabase db = mDBHelper.getWritableDatabase();


        ContentValues inventoryValues = new ContentValues();
        inventoryValues.put(newInventoryEntery.COLUMN_PRODUCT_NAME, "Headphones");
        inventoryValues.put(newInventoryEntery.COLUMN_PRODUCT_PRICE, "$2000");
        inventoryValues.put(newInventoryEntery.COLUMN_PRODUCT_QUANTITY, "100");
        inventoryValues.put(newInventoryEntery.COLUMN_PRODUCT_SUPPLIER_NAME, "Sony");
        inventoryValues.put(newInventoryEntery.COLUMN_PRODUCT_SUPPLIER_PHONENUMBER, "1-800-Sony");

        long NewRowId = db.insert(newInventoryEntery.TABLE_NAME, null, inventoryValues);
    }


    private void dataBaseInfo() {

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


        try {

            display.setText("The Inventory Table has " + inventoryCursor.getCount() + " items.\n\n");
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

            while (inventoryCursor.moveToNext()) {

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
                        currentSupplierPhoneNumber));

            }


        } finally {


            inventoryCursor.close();

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_catalog.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.catalogue_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Insert dummy data" menu option
            case R.id.action_insert_dummy_data:
                addInvetoryItem();
                dataBaseInfo();
                return true;
            // Respond to a click on the "Delete all entries" menu option
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
