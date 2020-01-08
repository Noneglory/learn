package com.skills.mongodb;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "wahaha")
public class Producer implements Serializable {
    @Id
    private  int id;
    @Indexed
    private int age;
    private int shared;
    private int sss;

    public Producer(int id, int age, int shared, int sss) {
        this.id = id;
        this.age = age;
        this.shared = shared;
        this.sss = sss;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getShared() {
        return shared;
    }

    public void setShared(int shared) {
        this.shared = shared;
    }

    public int getSss() {
        return sss;
    }

    public void setSss(int sss) {
        this.sss = sss;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
