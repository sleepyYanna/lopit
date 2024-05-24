package com.example.lopit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "SubjectList.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "my_subjects";

    private static final String COLUMN_SUBJECT = "subject_name";
    private static final String COLUMN_TEACHER = "subject_teacher";
    private static final String COLUMN_NOTE = "subject_note";
    private static final String COLUMN_COLOR = "subject_color";

    DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_SUBJECT + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TEACHER + " TEXT NOT NULL, " + COLUMN_COLOR + "LONG, "
                 + COLUMN_NOTE + " TEXT );";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME ;
        db.execSQL(query);
        onCreate(db);
    }
    void addSubject(String subject, String teacher, String color, String note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SUBJECT, subject);
        cv.put(COLUMN_TEACHER, teacher);
        cv.put(COLUMN_COLOR, color);
        cv.put(COLUMN_NOTE, note);

        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteTeacherRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "subject_name=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }

    }
}

