package com.dvidal.bancodedados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diegovidal on 22/05/16.
 */
public class StudentDAO {

    private  StudentSQLHelper helper;

    public StudentDAO(Context context) {
        this.helper = new StudentSQLHelper(context);
    }

    private long insert(Student student){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(StudentSQLHelper.COLUMN_NAME, student.getName());
        values.put(StudentSQLHelper.COLUMN_EMAIL, student.getEmail());
        values.put(StudentSQLHelper.COLUMN_PHONE, student.getPhone());

        long id = db.insert(StudentSQLHelper.TABLE_STUDENTS, null , values);

        return id;
    }

    private int update(Student student){

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(StudentSQLHelper.COLUMN_NAME, student.getName());
        values.put(StudentSQLHelper.COLUMN_EMAIL, student.getEmail());
        values.put(StudentSQLHelper.COLUMN_PHONE, student.getPhone());

        String condition = StudentSQLHelper.COLUMN_ID + " = ?";
        String[] params = new String[] { String.valueOf(student.getId()) };

        int affectedLines = db.update(
            StudentSQLHelper.TABLE_STUDENTS,
                values,
                condition,
                params
        );

        db.close();

        return affectedLines;
    }

    public int delete(Student student){

        SQLiteDatabase db = helper.getWritableDatabase();

        String condition = StudentSQLHelper.COLUMN_ID + " = ?";
        String[] params = new String[] { String.valueOf(student.getId()) };

        int affectedLines = db.delete(
                StudentSQLHelper.TABLE_STUDENTS,
                condition,
                params
        );

        return affectedLines;
    }

    public void save(Student student){

        if (student.getId() == 0){
            insert(student);
        } else {
            update(student);
        }

    }

    public List<Student> select(String filter){

        SQLiteDatabase db = helper.getReadableDatabase();

        String sql = "SELECT * FROM "+ StudentSQLHelper.TABLE_STUDENTS;
        String[] params = null;

        if (filter != null) {

            sql += " WHERE " + StudentSQLHelper.COLUMN_NAME + " LIKE ?";
            params = new String[]{ filter };
        }
        Cursor cur = db.rawQuery(sql, params);

        List<Student> listStudents = new ArrayList<>();
        while (cur.moveToNext()){

            long id = cur.getLong(cur.getColumnIndex(StudentSQLHelper.COLUMN_ID));
            String name = cur.getString(cur.getColumnIndex(StudentSQLHelper.COLUMN_NAME));
            String email = cur.getString(cur.getColumnIndex(StudentSQLHelper.COLUMN_EMAIL));
            String phone = cur.getString(cur.getColumnIndex(StudentSQLHelper.COLUMN_PHONE));

            Student student = new Student(id, name, email, phone);
            listStudents.add(student);
        }

        cur.close();
        db.close();

        return listStudents;
    }
}
