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
    private static final String DATABASE_NAME = "Hyrd.db";
    public static final String TABLE_EMPLOYEE = "employee";
    public static final String TABLE_RECRUITER = "recruiters";
    public static final String TABLE_SKILLS = "skills";
    public static final String TABLE_EXPERIENCE = "experience";
    public static final String TABLE_HIGH_SCHOOL = "high_school";
    public static final String TABLE_COLLEGE = "college";
    public static final String TABLE_CERTIFICATIONS = "certifications";


    //Common Columns
    public static final String COLUMN_EMP_ID = "emp_id";
    public static final String COLUMN_FIRST_NAME = "first_name";
    public static final String COLUMN_LAST_NAME = "last_name";
    public static final String COLUMN_PHONE_NUMBER = "phone_number";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_CITY = "city";
    public static final String COLUMN_STATE = "state";
    public static final String COLUMN_COMPANY = "company";
    public static final String COLUMN_START_MONTH = "start_month";
    public static final String COLUMN_START_YEAR = "start_year";
    public static final String COLUMN_END_MONTH = "end_month";
    public static final String COLUMN_END_YEAR = "end_year";
    public static final String COLUMN_SCHOOL_NAME = "school_name";
    public static final String COLUMN_DEGREE_TYPE = "degree_type";
    public static final String COLUMN_DEGREE_RECEIVED_DATE = "degree_received_date";

    //Employee Table Columns
    public static final String COLUMN_DEPARTMENT = "department";
    public static final String COLUMN_SALARY = "salary";

    //Recruiter Table Columns
    public static final String COLUMN_REC_ID = "rec_id";

    //Skills Table Columns
    public static final String COLUMN_SKILL_NAME = "skill_name";
    public static final String COLUMN_SKILL_VALUE = "skill_value";

    //Experience Table Columns
    public static final String COLUMN_POSITION = "position";
    public static final String COLUMN_ADDRESS = "address";
    public static final String COLUMN_DESCRIPTION = "description";

    //College Table Columns
    public static final String COLUMN_MAJOR = "major";

    //Certifications Table Columns
    public static final String COLUMN_CERT_NAME = "cert_name";
    public static final String COLUMN_CERT_AUTHORITY = "cert_authority";
    public static final String COLUMN_LICENSE_NUMBER = "license_number";

    //Create Employee Table
    private static final String CREATE_EMPLOYEE_TABLE = "CREATE TABLE " +
            TABLE_EMPLOYEE + "(" +
            COLUMN_EMP_ID + " INTEGER PRIMARY KEY," +
            COLUMN_FIRST_NAME + " VARCHAR(255)," +
            COLUMN_LAST_NAME + " VARCHAR(255)," +
            COLUMN_PHONE_NUMBER + " VARCHAR(255)," +
            COLUMN_EMAIL + " VARCHAR(255)," +
            COLUMN_PASSWORD + " VARCHAR(255)," +
            COLUMN_DEPARTMENT + " VARCHAR(255)," +
            COLUMN_SALARY + " VARCHAR(255)" + ")";

    //Create Recruiter Table
    private static final String CREATE_RECRUITER_TABLE = "CREATE TABLE " +
            TABLE_RECRUITER + "(" +
            COLUMN_REC_ID + " INTEGER PRIMARY KEY," +
            COLUMN_FIRST_NAME + " VARCHAR(255)," +
            COLUMN_LAST_NAME + " VARCHAR(255)," +
            COLUMN_PHONE_NUMBER + " VARCHAR(255)," +
            COLUMN_EMAIL + " VARCHAR(255)," +
            COLUMN_PASSWORD + " VARCHAR(255)," +
            COLUMN_COMPANY + " VARCHAR(255)" + ")";

    //Create Skills Table
    private static final String CREATE_SKILLS_TABLE = "CREATE TABLE " +
            TABLE_SKILLS + "(" +
            COLUMN_EMP_ID + " INTEGER," +
            COLUMN_SKILL_NAME + " VARCHAR(255)," +
            COLUMN_SKILL_VALUE + " INTEGER" + ")";

    //Create Experience Table
    private static final String CREATE_EXPERIENCE_TABLE = "CREATE TABLE " +
            TABLE_EXPERIENCE + "(" +
            COLUMN_EMP_ID + " INTEGER," +
            COLUMN_COMPANY + " VARCHAR(255)," +
            COLUMN_POSITION + " VARCHAR(255)," +
            COLUMN_ADDRESS + " VARCHAR(255)," +
            COLUMN_CITY + " VARCHAR(255)," +
            COLUMN_STATE + " VARCHAR(255)," +
            COLUMN_START_MONTH + " VARCHAR(255)," +
            COLUMN_START_YEAR + " VARCHAR(255)," +
            COLUMN_END_MONTH + " VARCHAR(255)," +
            COLUMN_END_YEAR + " VARCHAR(255)," +
            COLUMN_DESCRIPTION + " VARCHAR(255)" + ")";

    //Create High School Table
    private static final String CREATE_HIGH_SCHOOL_TABLE = "CREATE TABLE " +
            TABLE_HIGH_SCHOOL + "(" +
            COLUMN_EMP_ID + " INTEGER," +
            COLUMN_SCHOOL_NAME + " VARCHAR(255)," +
            COLUMN_CITY + " VARCHAR(255)," +
            COLUMN_STATE + " VARCHAR(255)," +
            COLUMN_DEGREE_TYPE + " VARCHAR(255)," +
            COLUMN_DEGREE_RECEIVED_DATE + " VARCHAR(255)" + ")";

    //Create COLLEGE Table
    private static final String CREATE_COLLEGE_TABLE = "CREATE TABLE " +
            TABLE_COLLEGE + "(" +
            COLUMN_EMP_ID + " INTEGER," +
            COLUMN_SCHOOL_NAME + " VARCHAR(255)," +
            COLUMN_CITY + " VARCHAR(255)," +
            COLUMN_STATE + " VARCHAR(255)," +
            COLUMN_MAJOR + " VARCHAR(255)," +
            COLUMN_DEGREE_TYPE + " VARCHAR(255)," +
            COLUMN_DEGREE_RECEIVED_DATE + " VARCHAR(255)" + ")";

    //Create Certifications Table
    private static final String CREATE_CERTIFICATIONS_TABLE = "CREATE TABLE " +
            TABLE_CERTIFICATIONS + "(" +
            COLUMN_EMP_ID + " INTEGER," +
            COLUMN_CERT_NAME + " VARCHAR(255)," +
            COLUMN_CERT_AUTHORITY + " VARCHAR(255)," +
            COLUMN_LICENSE_NUMBER + " VARCHAR(255)," +
            COLUMN_START_MONTH + " VARCHAR(255)," +
            COLUMN_START_YEAR + " VARCHAR(255)," +
            COLUMN_END_MONTH + " VARCHAR(255)," +
            COLUMN_END_YEAR + " VARCHAR(255)" + ")";

    public DBHandler(Context context, String name,
                       CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_EMPLOYEE_TABLE);
        db.execSQL(CREATE_RECRUITER_TABLE);
        db.execSQL(CREATE_SKILLS_TABLE);
        db.execSQL(CREATE_EXPERIENCE_TABLE);
        db.execSQL(CREATE_HIGH_SCHOOL_TABLE);
        db.execSQL(CREATE_COLLEGE_TABLE);
        db.execSQL(CREATE_CERTIFICATIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECRUITER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SKILLS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPERIENCE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HIGH_SCHOOL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COLLEGE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CERTIFICATIONS);

        // create new tables
        onCreate(db);
    }

    public void addEmployee(Employee employee) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, employee.getFirstName());
        values.put(COLUMN_LAST_NAME, employee.getLastName());
        values.put(COLUMN_PHONE_NUMBER, employee.getPhoneNumber());
        values.put(COLUMN_EMAIL, employee.getEmail());
        values.put(COLUMN_PASSWORD, employee.getPassword());
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
                employee.setPassword(c.getString(c.getColumnIndex(COLUMN_PASSWORD)));
                employee.setDepartment((c.getString(c.getColumnIndex(COLUMN_DEPARTMENT))));
                employee.setSalary((c.getString(c.getColumnIndex(COLUMN_SALARY))));

                // adding to todo list
                employees.add(employee);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return employees;
    }

    public Employee findEmployee(String employeeName) {
        String query = "Select * FROM " + TABLE_EMPLOYEE + " WHERE " + COLUMN_FIRST_NAME + " =  \"" + employeeName + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(query, null);

        Employee employee = new Employee();

        if (c.moveToFirst()) {
            c.moveToFirst();
            employee.setID(c.getInt(c.getColumnIndex(COLUMN_EMP_ID)));
            employee.setFirstName(c.getString(c.getColumnIndex(COLUMN_FIRST_NAME)));
            employee.setLastName((c.getString(c.getColumnIndex(COLUMN_LAST_NAME))));
            employee.setPhoneNumber(c.getString(c.getColumnIndex(COLUMN_PHONE_NUMBER)));
            employee.setEmail(c.getString(c.getColumnIndex(COLUMN_EMAIL)));
            employee.setPassword(c.getString(c.getColumnIndex(COLUMN_PASSWORD)));
            employee.setDepartment((c.getString(c.getColumnIndex(COLUMN_DEPARTMENT))));
            employee.setSalary((c.getString(c.getColumnIndex(COLUMN_SALARY))));
        } else {
            employee = null;
        }
        c.close();
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

    public void addRecruiter(Recruiter recruiter) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRST_NAME, recruiter.getFirstName());
        values.put(COLUMN_LAST_NAME, recruiter.getLastName());
        values.put(COLUMN_PHONE_NUMBER, recruiter.getPhoneNumber());
        values.put(COLUMN_EMAIL, recruiter.getEmail());
        values.put(COLUMN_COMPANY, recruiter.getCompany());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_RECRUITER, null, values);
        db.close();
    }

    public List<Skills> getAllSkills() {
        List<Skills> skills = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_SKILLS;

        Log.e("DBQuery", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Skills skill = new Skills();
                skill.setID(c.getInt(c.getColumnIndex(COLUMN_EMP_ID)));
                skill.setSkillName(c.getString(c.getColumnIndex(COLUMN_SKILL_NAME)));
                skill.setSkillValue((c.getInt(c.getColumnIndex(COLUMN_SKILL_VALUE))));
                skills.add(skill);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return skills;
    }

    public List<Skills> getAllSkillsForEmployee(int id) {
        List<Skills> skills = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_SKILLS + " WHERE " + TABLE_SKILLS + "." + COLUMN_EMP_ID + " = "
                + id;

        Log.e("DBQuery", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Skills skill = new Skills();
                skill.setSkillName(c.getString(c.getColumnIndex(COLUMN_SKILL_NAME)));
                skill.setSkillValue((c.getInt(c.getColumnIndex(COLUMN_SKILL_VALUE))));
                skills.add(skill);
            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return skills;
    }

    public void addSkillForEmployee(Employee employee, Skills skill) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMP_ID, employee.getID());
        values.put(COLUMN_SKILL_NAME, skill.getSkillName());
        values.put(COLUMN_SKILL_VALUE, skill.getSkillValue());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_SKILLS, null, values);
        db.close();
    }

    public List<Experience> getAllExperience() {
        List<Experience> experiences = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_EXPERIENCE;

        Log.e("DBQuery", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Experience experience = new Experience();
                experience.setID(c.getInt(c.getColumnIndex(COLUMN_EMP_ID)));
                experience.setCompany(c.getString(c.getColumnIndex(COLUMN_COMPANY)));
                experience.setPosition((c.getString(c.getColumnIndex(COLUMN_POSITION))));
                experience.setAddress(c.getString(c.getColumnIndex(COLUMN_ADDRESS)));
                experience.setCity(c.getString(c.getColumnIndex(COLUMN_CITY)));
                experience.setState(c.getString(c.getColumnIndex(COLUMN_STATE)));
                experience.setStartMonth((c.getString(c.getColumnIndex(COLUMN_START_MONTH))));
                experience.setStartYear(c.getString(c.getColumnIndex(COLUMN_START_YEAR)));
                experience.setEndMonth((c.getString(c.getColumnIndex(COLUMN_END_MONTH))));
                experience.setEndYear(c.getString(c.getColumnIndex(COLUMN_END_YEAR)));
                experience.setDescription((c.getString(c.getColumnIndex(COLUMN_DESCRIPTION))));
                experiences.add(experience);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return experiences;
    }

    public List<Experience> getAllExperienceForEmployee(int id) {
        List<Experience> experiences = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_EXPERIENCE + " WHERE " + TABLE_HIGH_SCHOOL + "."
                + COLUMN_EMP_ID + " = " + id;

        Log.e("DBQuery", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Experience experience = new Experience();
                experience.setCompany(c.getString(c.getColumnIndex(COLUMN_COMPANY)));
                experience.setPosition((c.getString(c.getColumnIndex(COLUMN_POSITION))));
                experience.setAddress(c.getString(c.getColumnIndex(COLUMN_ADDRESS)));
                experience.setCity(c.getString(c.getColumnIndex(COLUMN_CITY)));
                experience.setState(c.getString(c.getColumnIndex(COLUMN_STATE)));
                experience.setStartMonth((c.getString(c.getColumnIndex(COLUMN_START_MONTH))));
                experience.setStartYear(c.getString(c.getColumnIndex(COLUMN_START_YEAR)));
                experience.setEndMonth((c.getString(c.getColumnIndex(COLUMN_END_MONTH))));
                experience.setEndYear(c.getString(c.getColumnIndex(COLUMN_END_YEAR)));
                experience.setDescription((c.getString(c.getColumnIndex(COLUMN_DESCRIPTION))));
                experiences.add(experience);
            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return experiences;
    }

    public void addExperienceForEmployee(Employee employee, Experience experience) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMP_ID, employee.getID());
        values.put(COLUMN_COMPANY, experience.getCompany());
        values.put(COLUMN_POSITION, experience.getPosition());
        values.put(COLUMN_ADDRESS, experience.getAddress());
        values.put(COLUMN_CITY, experience.getCity());
        values.put(COLUMN_STATE, experience.getState());
        values.put(COLUMN_START_MONTH, experience.getStartMonth());
        values.put(COLUMN_START_YEAR, experience.getStartYear());
        values.put(COLUMN_END_MONTH, experience.getEndMonth());
        values.put(COLUMN_END_YEAR, experience.getEndYear());
        values.put(COLUMN_DESCRIPTION, experience.getDescription());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_EXPERIENCE, null, values);
        db.close();
    }

    public List<HighSchool> getAllHighSchools() {
        List<HighSchool> highSchools = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_HIGH_SCHOOL;

        Log.e("DBQuery", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                HighSchool highSchool = new HighSchool();
                highSchool.setID(c.getInt(c.getColumnIndex(COLUMN_EMP_ID)));
                highSchool.setSchoolName(c.getString(c.getColumnIndex(COLUMN_SCHOOL_NAME)));
                highSchool.setCity(c.getString(c.getColumnIndex(COLUMN_CITY)));
                highSchool.setState(c.getString(c.getColumnIndex(COLUMN_STATE)));
                highSchool.setDegreeType((c.getString(c.getColumnIndex(COLUMN_DEGREE_TYPE))));
                highSchool.setDegreeReceivedDate(c.getString(c.getColumnIndex(COLUMN_DEGREE_RECEIVED_DATE)));
                highSchools.add(highSchool);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return highSchools;
    }

    public List<HighSchool> getAllHighSchoolsForEmployee(int id) {
        List<HighSchool> highSchools = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_HIGH_SCHOOL + " WHERE " + TABLE_HIGH_SCHOOL + "."
                + COLUMN_EMP_ID + " = " + id;

        Log.e("DBQuery", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                HighSchool highSchool = new HighSchool();
                highSchool.setSchoolName(c.getString(c.getColumnIndex(COLUMN_SCHOOL_NAME)));
                highSchool.setCity(c.getString(c.getColumnIndex(COLUMN_CITY)));
                highSchool.setState(c.getString(c.getColumnIndex(COLUMN_STATE)));
                highSchool.setDegreeType((c.getString(c.getColumnIndex(COLUMN_DEGREE_TYPE))));
                highSchool.setDegreeReceivedDate(c.getString(c.getColumnIndex(COLUMN_DEGREE_RECEIVED_DATE)));
                highSchools.add(highSchool);
            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return highSchools;
    }

    public void addHighSchoolForEmployee(Employee employee, HighSchool highSchool) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMP_ID, employee.getID());
        values.put(COLUMN_SCHOOL_NAME, highSchool.getSchoolName());
        values.put(COLUMN_CITY, highSchool.getCity());
        values.put(COLUMN_STATE, highSchool.getState());
        values.put(COLUMN_DEGREE_TYPE, highSchool.getDegreeType());
        values.put(COLUMN_DEGREE_RECEIVED_DATE, highSchool.getDegreeReceivedDate());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_HIGH_SCHOOL, null, values);
        db.close();
    }

    public List<College> getAllColleges() {
        List<College> colleges = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_HIGH_SCHOOL;

        Log.e("DBQuery", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                College college = new College();
                college.setID(c.getInt(c.getColumnIndex(COLUMN_EMP_ID)));
                college.setSchoolName(c.getString(c.getColumnIndex(COLUMN_SCHOOL_NAME)));
                college.setCity(c.getString(c.getColumnIndex(COLUMN_CITY)));
                college.setState(c.getString(c.getColumnIndex(COLUMN_STATE)));
                college.setMajor(c.getString(c.getColumnIndex(COLUMN_MAJOR)));
                college.setDegreeType((c.getString(c.getColumnIndex(COLUMN_DEGREE_TYPE))));
                college.setDegreeReceivedDate(c.getString(c.getColumnIndex(COLUMN_DEGREE_RECEIVED_DATE)));
                colleges.add(college);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return colleges;
    }

    public List<College> getAllCollegesForEmployee(int id) {
        List<College> colleges = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_COLLEGE + " WHERE " + TABLE_COLLEGE + "."
                + COLUMN_EMP_ID + " = " + id;

        Log.e("DBQuery", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                College college = new College();
                college.setSchoolName(c.getString(c.getColumnIndex(COLUMN_SCHOOL_NAME)));
                college.setCity(c.getString(c.getColumnIndex(COLUMN_CITY)));
                college.setState(c.getString(c.getColumnIndex(COLUMN_STATE)));
                college.setMajor(c.getString(c.getColumnIndex(COLUMN_MAJOR)));
                college.setDegreeType((c.getString(c.getColumnIndex(COLUMN_DEGREE_TYPE))));
                college.setDegreeReceivedDate(c.getString(c.getColumnIndex(COLUMN_DEGREE_RECEIVED_DATE)));
                colleges.add(college);
            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return colleges;
    }

    public void addCollegeForEmployee(Employee employee, College college) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMP_ID, employee.getID());
        values.put(COLUMN_SCHOOL_NAME, college.getSchoolName());
        values.put(COLUMN_CITY, college.getCity());
        values.put(COLUMN_STATE, college.getState());
        values.put(COLUMN_MAJOR, college.getMajor());
        values.put(COLUMN_DEGREE_TYPE, college.getDegreeType());
        values.put(COLUMN_DEGREE_RECEIVED_DATE, college.getDegreeReceivedDate());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_COLLEGE, null, values);
        db.close();
    }

    public List<Certifications> getAllCertifications() {
        List<Certifications> certifications = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_CERTIFICATIONS;

        Log.e("DBQuery", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Certifications certification = new Certifications();
                certification.setID(c.getInt(c.getColumnIndex(COLUMN_EMP_ID)));
                certification.setCertName(c.getString(c.getColumnIndex(COLUMN_CERT_NAME)));
                certification.setCertAuthority(c.getString(c.getColumnIndex(COLUMN_CERT_AUTHORITY)));
                certification.setLicenseNumber(c.getString(c.getColumnIndex(COLUMN_LICENSE_NUMBER)));
                certification.setStartMonth(c.getString(c.getColumnIndex(COLUMN_START_MONTH)));
                certification.setStartYear((c.getString(c.getColumnIndex(COLUMN_START_YEAR))));
                certification.setEndMonth(c.getString(c.getColumnIndex(COLUMN_END_MONTH)));
                certification.setEndYear(c.getString(c.getColumnIndex(COLUMN_END_YEAR)));
                certifications.add(certification);
            } while (c.moveToNext());
        }
        return certifications;
    }

    public List<Certifications> getAllCertificationsForEmployee(int id) {
        List<Certifications> certifications = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_CERTIFICATIONS + " WHERE " + TABLE_CERTIFICATIONS + "."
                + COLUMN_EMP_ID + " = " + id;

        Log.e("DBQuery", selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                Certifications certification = new Certifications();
                certification.setCertName(c.getString(c.getColumnIndex(COLUMN_CERT_NAME)));
                certification.setCertAuthority(c.getString(c.getColumnIndex(COLUMN_CERT_AUTHORITY)));
                certification.setLicenseNumber(c.getString(c.getColumnIndex(COLUMN_LICENSE_NUMBER)));
                certification.setStartMonth(c.getString(c.getColumnIndex(COLUMN_START_MONTH)));
                certification.setStartYear((c.getString(c.getColumnIndex(COLUMN_START_YEAR))));
                certification.setEndMonth(c.getString(c.getColumnIndex(COLUMN_END_MONTH)));
                certification.setEndYear(c.getString(c.getColumnIndex(COLUMN_END_YEAR)));
                certifications.add(certification);
            } while (c.moveToNext());
        }
        c.close();
        db.close();

        return certifications;
    }

    public void addCertificationsForEmployee(Employee employee, Certifications certification) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_EMP_ID, employee.getID());
        values.put(COLUMN_CERT_NAME, certification.getCertName());
        values.put(COLUMN_CERT_AUTHORITY, certification.getCertAuthority());
        values.put(COLUMN_LICENSE_NUMBER, certification.getLicenseNumber());
        values.put(COLUMN_START_MONTH, certification.getStartMonth());
        values.put(COLUMN_START_YEAR, certification.getStartYear());
        values.put(COLUMN_END_MONTH, certification.getEndMonth());
        values.put(COLUMN_END_YEAR, certification.getEndYear());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_COLLEGE, null, values);
        db.close();
    }
}
