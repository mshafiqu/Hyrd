package com.hyrd.hyrd;

/**
 * Created by AlyanShafique on 5/31/15.
 */
public class Skills {
    private int emp_id;
    private String skill_name;
    private int skill_value;

    public Skills() {

    }

    public Skills(int emp_id, String skill_name, int skill_value) {
        this.emp_id = emp_id;
        this.skill_name = skill_name;
        this.skill_value = skill_value;
    }

    public Skills(String skill_name, int skill_value) {
        this.skill_name = skill_name;
        this.skill_value = skill_value;
    }

    public void setID(int emp_id) {
        this.emp_id = emp_id;
    }

    public int getID() {
        return this.emp_id;
    }

    public void setSkillName(String skill_name) {
        this.skill_name = skill_name;
    }

    public String getSkillName() {
        return this.skill_name;
    }

    public void setSkillValue(int skill_value) {
        this.skill_value = skill_value;
    }

    public int getSkillValue() {
        return this.skill_value;
    }
}
