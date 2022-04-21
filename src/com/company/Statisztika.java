package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;

public class Statisztika {
    Connection conn;

    public Statisztika() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "");
    }

    public ArrayList<Konyv> ListaFeltolt() throws SQLException, ClassNotFoundException {
        ArrayList<Konyv> lista = new ArrayList<>();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM books;";
        ResultSet result = stmt.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("id");
            String title = result.getString("title");
            String author = result.getString("author");
            int publish_year = result.getInt("publish_year");
            int page_count = result.getInt("page_count");

            Konyv elem = new Konyv(id, title, author, publish_year, page_count);
            lista.add(elem);
        }
        return lista;
    }

    public ArrayList<Kolcsonzes> ListaFeltolt2() throws SQLException {
        ArrayList<Kolcsonzes> lista = new ArrayList<>();
        Statement stmt = conn.createStatement();
        String sql = "SELECT * FROM rental;";
        ResultSet result = stmt.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt("id");
            int book_id = result.getInt("book_id");
            Date start_date = result.getDate("start_date");
            Date end_date = result.getDate("end_date");

            Kolcsonzes kelem = new Kolcsonzes(id, book_id, start_date, end_date);
            lista.add(kelem);
        }
        return lista;
    }

    public int rekordHozzaadasa(Konyv pr) throws SQLException {
        String sql = "INSERT INTO books(titla, author, publish_year, page_count) VALUES (?,?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, pr.getTitle());
        stmt.setString(2, pr.getAuthor());
        stmt.setInt(3, pr.getPublish_year());
        stmt.setInt(4, pr.getPage_count());

        return stmt.executeUpdate();
    }
}
