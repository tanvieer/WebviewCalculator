package calculator.webview.tanvir.com.webviewcalculator;

/**
 * Created by Tanvir on 25-Apr-17.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanvir on 11-Mar-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME ="history.db";
    private static final String TABLE_NAME = "history_table";
    private static final String KEY_ID = "ID";
    private static final String COL_2 = "RESULT";
    private String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY," + COL_2 + " TEXT" + ")";
    SQLiteDatabase db;

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null , 1);
        Log.d("calcDB","constructor called perfectly");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("calcDB","on create DatabaseHelper called");
        db.execSQL(CREATE_CONTACTS_TABLE);
        this.db = db;
        Log.d("calcDB","on create DatabaseHelper Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        Log.d("calcDB","on upgrade 1");
        onCreate(db);
    }


    public boolean insertData(String result){
        db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,result);

        Log.d("calcDB","on insert >  " + result);
        long check = db.insert(TABLE_NAME,null,contentValues);

        Log.d("calcDB","on insert check value=  "+ check );

        if(check == -1) return false;
        else return true;
    }

    public List<String> getHistory(){
        db = getWritableDatabase();
        List<String> historyList = new ArrayList<String>();


        String query = "select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);


        if (cursor.moveToFirst()) {
            do {
                String data;
                data = cursor.getString(0)+". ";
                data += cursor.getString(1)+"\n\n";
                historyList.add(data);
            }
            while (cursor.moveToNext());
        }

        return historyList;
    }

    public void deleteHistory(){
        db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        Log.d("calcDB","Deleted all");
        onCreate(db);
    }
}
