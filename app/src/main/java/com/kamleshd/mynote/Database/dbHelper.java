package com.kamleshd.mynote.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.kamleshd.mynote.model.Note;

import java.util.ArrayList;
import java.util.List;

public class dbHelper extends SQLiteOpenHelper {

    public dbHelper( Context context) {
        super(context,dbParameter.DB_NAME,null,dbParameter.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
String create="CREATE TABLE "+dbParameter.TABLE_NAME+"( "+dbParameter.KEY_ID+" INTEGER PRIMARY KEY, "
        +dbParameter.KEY_TEXT_NOTE+" TEXT )";
        Log.d("sql", "sql table "+create);
        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addNote(Note note)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(dbParameter.KEY_TEXT_NOTE,note.getText_Note());
        db.insert(dbParameter.TABLE_NAME,null,values);
        Log.d("sql", "sql inserted ");
        db.close();
    }
    public int  updateNote(Note note)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(dbParameter.KEY_TEXT_NOTE,note.getText_Note());
        return db.update(dbParameter.TABLE_NAME,values,"ID = ?", new String[]{String.valueOf(note.getId())});

    }

    public void   deleteNote(Note note)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(dbParameter.TABLE_NAME,"ID = ?", new String[]{String.valueOf(note.getId())});
        Log.d("sql", "sql delete "+note.getId());
        db.close();
    }


    public List<Note> getAllNote()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        List<Note> notes=new ArrayList<>();
        String show="SELECT * FROM "+dbParameter.TABLE_NAME;
        Cursor cursor=db.rawQuery(show,null);

        if (cursor.moveToFirst())
        {
            do {
                Note note=new Note();
                note.setId(Integer.parseInt(cursor.getString(0)));
                note.setText_Note(cursor.getString(1));
                notes.add(note);
            }while (cursor.moveToNext());

        }
        return notes;
    }
}
