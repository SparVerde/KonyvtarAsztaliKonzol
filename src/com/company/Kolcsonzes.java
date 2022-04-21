package com.company;

import java.sql.*;
import java.util.ArrayList;

public class Kolcsonzes {
    private int id;
    private int book_id;
    private Date start_date;
    private Date end_date;

    public Kolcsonzes(int id, int book_id, Date start_date, Date end_date) {
        this.id = id;
        this.book_id = book_id;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    @Override
    public String toString() {
        return "Könyv adatbázis{" +
                "id=" + id +
                ", book_id:'" + book_id + '\'' +
                ", start date=" + start_date + '\'' +
                ", end date=" + end_date + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getBook_id() {
        return book_id;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

}