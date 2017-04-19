package com.example.lab.atividade4.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lab.atividade4.model.User;

public class UserDAO {
    public static final String TABLE_NAME = "user";
    public static final String COLUMN_NAME_NAME = "name";
    public static final String COLUMN_NAME_EMAIL = "email";
    public static final String COLUMN_NAME_PASSWORD = "password";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";


    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    " _id INTEGER PRIMARY KEY," +
                    COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_EMAIL + TEXT_TYPE + COMMA_SEP +
                    COLUMN_NAME_PASSWORD + TEXT_TYPE +" )" ;


        private static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;



    DataBaseHelper dataBase;

        public UserDAO(Context context){
            dataBase = new DataBaseHelper(context);
        }

        public void insert(User user){
            SQLiteDatabase db = dataBase.getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_NAME, user.nome);
            values.put(COLUMN_NAME_EMAIL, user.email);
            values.put(COLUMN_NAME_PASSWORD,user.senha);


            // Insert the new row, returning the primary key value of the new row
            user.id = db.insert(TABLE_NAME, null, values);
        }

        public void update(User user){
            SQLiteDatabase db = dataBase.getWritableDatabase();

            // Create a new map of values, where column names are the keys
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_NAME, user.nome);
            values.put(COLUMN_NAME_EMAIL, user.email);
            values.put(COLUMN_NAME_PASSWORD,user.senha);

            db.update(TABLE_NAME,values,"_id = " + user.id, null);
        }

        public void delete(User user){
            SQLiteDatabase db = dataBase.getWritableDatabase();
            user.id = db.delete(TABLE_NAME, "_id = ?", new String[]{"" + user.id});
        }

        public User getUser(Long id){
            SQLiteDatabase db = dataBase.getReadableDatabase();
            Cursor c = db.rawQuery("select * from " + TABLE_NAME + " where _id = " + id, null);
            c.moveToFirst();
            return toModel(c);
        }

        public User toModel(Cursor c){
            User user = new User();
            user.id = c.getLong(0);
            user.nome = c.getString(1);
            return user;
        }



}
