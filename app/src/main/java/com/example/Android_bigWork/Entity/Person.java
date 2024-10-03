package com.example.Android_bigWork.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "person_table")
public class Person implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int UID;

    public String username;   // 姓名
    public String password;  // 密码
    public long phoneNumber;  // 手机号码
    public int gender;   // 性别
    public int payPassword;   // 支付密码

    public static final int GENDER_MALE = 0;
    public static final int GENDER_FEMALE = 1;
    public static final long serialVersionUID = 1L; // 序列化的版本号

    public Person(String username, String password, long phoneNumber, int gender, int payPassword) {
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.payPassword = payPassword;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "UID=" + UID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", gender=" + gender +
                ", payPassword=" + payPassword +
                '}';
    }
    //序列化方式


}
