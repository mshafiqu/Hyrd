package com.hyrd.hyrd;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AlyanShafique on 5/31/15.
 */
public class Employee implements Parcelable {
    private int emp_id;
    private String first_name;
    private String last_name;
    private String phone_number;
    private String email;
    private String password;
    private String department;
    private String salary;

    public Employee() {

    }

    public Employee(int emp_id, String first_name, String last_name, String phone_number, String email, String password, String department, String salary) {
        this.emp_id = emp_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
        this.department = department;
        this.salary = salary;
    }

    public Employee(String first_name, String last_name, String phone_number, String email, String password, String department, String salary) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email = email;
        this.password = password;
        this.department = department;
        this.salary = salary;
    }

    public void setID(int emp_id) {
        this.emp_id = emp_id;
    }

    public int getID() {
        return this.emp_id;
    }

    public void setFirstName(String first_name) {
        this.first_name = first_name;
    }

    public String getFirstName() {
        return this.first_name;
    }

    public void setLastName(String last_name) {
        this.last_name = last_name;
    }

    public String getLastName() {
        return this.last_name;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPhoneNumber() {
        return this.phone_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getDepartment() {
        return this.department;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSalary() {
        return this.salary;
    }

    protected Employee(Parcel in) {
        emp_id = in.readInt();
        first_name = in.readString();
        last_name = in.readString();
        phone_number = in.readString();
        email = in.readString();
        password = in.readString();
        department = in.readString();
        salary = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(emp_id);
        dest.writeString(first_name);
        dest.writeString(last_name);
        dest.writeString(phone_number);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(department);
        dest.writeString(salary);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Employee> CREATOR = new Parcelable.Creator<Employee>() {
        @Override
        public Employee createFromParcel(Parcel in) {
            return new Employee(in);
        }

        @Override
        public Employee[] newArray(int size) {
            return new Employee[size];
        }
    };
}
