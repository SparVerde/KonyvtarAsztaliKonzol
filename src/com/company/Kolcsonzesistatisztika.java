package com.company;

import java.sql.*;
import java.util.ArrayList;


public class Kolcsonzesistatisztika {
    Connection conn;

    public Kolcsonzesistatisztika() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/books", "root", "");
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
    public int rekordHozzaadasa(Kolcsonzes pr) throws SQLException {
        String sql = "INSERT INTO rental(book_id, start_date, end_date) VALUES (?,?,?)";
        PreparedStatement stmt = conn.prepareStatement(sql);

        stmt.setInt(1, pr.getBook_id());
        stmt.setDate(2, pr.getStart_date());
        stmt.setDate(3, pr.getEnd_date());

        return stmt.executeUpdate();
    }

}


