package com.example.android.iventoryapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.iventoryapp.database.InventoryContract.newInventoryEntery;



public class InventoryDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = InventoryDbHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "inventory.db";

    private static final int DATABASE_VERSION = 1;


    public InventoryDbHelper(Context context){super(context,DATABASE_NAME,null,DATABASE_VERSION);}


    @Override
    public void onCreate(SQLiteDatabase db){

        String SQL_CREATE_INVENTORY_TABLE =  "CREATE TABLE " + newInventoryEntery.TABLE_NAME + " ("
                + newInventoryEntery._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + newInventoryEntery.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + newInventoryEntery.COLUMN_PRODUCT_PRICE + " INTEGER NOT NULL, "
                + newInventoryEntery.COLUMN_PRODUCT_QUANTITY + " INTEGER NOT NULL DEFAULT 0, "
                + newInventoryEntery.COLUMN_PRODUCT_SUPPLIER_NAME + "TEXT ,"
                + newInventoryEntery.COLUMN_PRODUCT_SUPPLIER_PHONENUMBER + " INTEGER NOT NULL" +
                ");";



        db.execSQL(SQL_CREATE_INVENTORY_TABLE);


        }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }

    }




