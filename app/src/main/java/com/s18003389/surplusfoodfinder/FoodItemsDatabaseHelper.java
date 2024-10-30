package com.s18003389.surplusfoodfinder;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class FoodItemsDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "fooditems.db";
    public static final String TABLE_NAME = "food_table";

    public static final String COL_1= "Food_Item_Title";
    public static final String COL_2 = "Food_Description";
    public static final String COL_3 = "Price";

    public FoodItemsDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_1 + " TEXT, " +
                COL_2 + " TEXT, " +
                COL_3 + " TEXT)");

        // Example data insertion
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_1 + "," + COL_2 + ", " + COL_3 + ") VALUES ('Burger','A burger is a patty of ground beef grilled and placed between two halves of a bun.','Spicy Crispy Chicken Burger. LKR 1,118.64 ; Beef Whopper. LKR 2,050.85')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_1 + "," + COL_2 + ", " + COL_3 + ") VALUES ('Submarine', 'a long, thin loaf of bread filled with meat or cheese, and often lettuce, tomatoes, etc.','Chicken Submarine. LKR 1,350.00 ; Beef Submarine. LKR 1,450.00')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_1 + "," + COL_2 + ", " + COL_3 + ") VALUES ('Chicken Wraps', 'A wrap is a culinary dish made with a soft flatbread rolled around a filling.','Chicken Wrap -12\". LKR 1,857.00.')");
        db.execSQL("INSERT INTO " + TABLE_NAME + " (" + COL_1 + "," + COL_2 + ", " + COL_3 + ") VALUES ('Chicken Bucket', 'The chicken buckets are uniquely designed bucket-shaped packages that are ideal for containing chicken.','6 pcs Bucket. Rs. 3,140')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor getAllFoodItems() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COL_3 + " LIKE '%Food%' OR " + COL_3 + " LIKE '%Restaurant%'", null);
    }

    public Cursor getAllData() {
        return null;
    }
}
