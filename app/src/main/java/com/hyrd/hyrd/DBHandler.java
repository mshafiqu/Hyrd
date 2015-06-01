package com.hyrd.hyrd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "employeeDB.db";
    public static final String TABLE_EMPLOYEE = "employee";
    public static final String TABLE_SKILLS = "skills";

    public static final String COLUMN_EMP_ID = "emp_id";

    //Employee Table Columns
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_DEPARTMENT = "department";
    public static final String COLUMN_SALARY = "salary";

    //Skills Table Columns
    public static final String COLUMN_SKILL_NAME = "skill_name";
    public static final String COLUMN_SKILL_VALUE = "skill_value";

    private static final String CREATE_EMPLOYEE_TABLE = "CREATE TABLE " +
            TABLE_EMPLOYEE + "(" +
            COLUMN_EMP_ID + " INTEGER PRIMARY KEY," +
            COLUMN_FIRST_NAME + " VARCHAR(255)," +
            COLUMN_LAST_NAME + " VARCHAR(255)," +
            COLUMN_PHONE_NUMBER + " VARCHAR(255)," +
            COLUMN_EMAIL + " VARCHAR(255)," +
            COLUMN_DEPARTMENT + " VARCHAR(255)," +
            COLUMN_SALARY + " VARCHAR(255)" + ")";

    private static final String CREATE_SKILLS_TABLE = "CREATE TABLE " +
            TABLE_EMPLOYEE + "(" +
            COLUMN_EMP_ID + " INTEGER PRIMARY KEY," +
            COLUMN_SKILL_NAME + " VARCHAR(255)," +
            COLUMN_SKILL_VALUE + " INTEGER" + ")";


    public DBHandler(Context context, String name,
                       CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EMPLOYEE_TABLE);
        db.execSQL(CREATE_SKILLS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SKILLS);

        // create new tables
        onCreate(db);
    }

    public void addEmployee(Employee employee) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, employee.getFirstName());
        values.put(COLUMN_LAST_NAME, employee.getLastName());
        values.put(COLUMN_PHONE_NUMBER, employee.getPhoneNumber());
        values.put(COLUMN_EMAIL, employee.getEmail());
        values.put(COLUMN_DEPARTMENT, employee.getDepartment());
        values.put(COLUMN_SALARY, employee.getSalary());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_EMPLOYEE, null, values);
        db.close();
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_EMPLOYEE;

        Log.e("DBQuery", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setID(c.getInt(c.getColumnIndex(COLUMN_EMP_ID)));
                employee.setFirstName(c.getString(c.getColumnIndex(COLUMN_FIRST_NAME)));
                employee.setLastName((c.getString(c.getColumnIndex(COLUMN_LAST_NAME))));
                employee.setPhoneNumber(c.getString(c.getColumnIndex(COLUMN_PHONE_NUMBER)));
                employee.setEmail(c.getString(c.getColumnIndex(COLUMN_EMAIL)));
                employee.setDepartment((c.getString(c.getColumnIndex(COLUMN_DEPARTMENT))));
                employee.setSalary((c.getString(c.getColumnIndex(COLUMN_SALARY))));

                // adding to todo list
                employees.add(employee);
            } while (c.moveToNext());
        }

        return employees;
    }

    public Employee findEmployee(String employeename) {
        String query = "Select * FROM " + TABLE_EMPLOYEE + " WHERE " + COLUMN_FIRST_NAME + " =  \"" + employeename + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Employee employee = new Employee();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            employee.setID(Integer.parseInt(cursor.getString(0)));
            employee.setFirstName(cursor.getString(1));
            employee.setLastName(cursor.getString(2));
            employee.setPhoneNumber(cursor.getString(3));
            employee.setEmail(cursor.getString(4));
            employee.setDepartment(cursor.getString(5));
            employee.setSalary(cursor.getString(6));
            cursor.close();
        } else {
            employee = null;
        }
        db.close();
        return employee;
    }

    public boolean deleteEmployee(String employeename) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_EMPLOYEE + " WHERE " + COLUMN_FIRST_NAME + " =  \"" + employeename + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        Employee employee = new Employee();

        if (cursor.moveToFirst()) {
            employee.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_EMPLOYEE, COLUMN_EMP_ID + " = ?",
                    new String[] { String.valueOf(employee.getID()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public long addSkill(Skills skill, long[] emp_ids) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SKILL_NAME, skill.getSkillName());
        values.put(COLUMN_SKILL_VALUE, skill.getSkillValue());

        long skill_id = db.insert(TABLE_SKILLS, null, values);

        for (long emp_id : emp_ids) {
            assignSkillToEmp(skill_id, emp_id);
        }

        return skill_id;
    }

    public long assignSkillToEmp(long skill_id, long emp_id) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TODO_ID, todo_id);
        values.put(KEY_TAG_ID, tag_id);
        values.put(KEY_CREATED_AT, getDateTime());

        long id = db.insert(TABLE_TODO_TAG, null, values);

        return id;
    }
}
