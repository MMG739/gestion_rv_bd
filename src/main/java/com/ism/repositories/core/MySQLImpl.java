package com.ism.repositories.core;

import java.sql.*;

public class MySQLImpl implements Database {
    private final String driver = "com.mysql.cj.jdbc.Driver";
    private final String url = "jdbc:mysql://localhost:3306/database_gestion_rv";
    private final String user = "root";
    private final String password = "root";
    private Connection conn = null;
    private PreparedStatement ps;


    @Override
    public void prepareStatement(String sql, Object... params) throws SQLException {
        ps = conn.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
    }

    @Override
    public void openConnexion() {
        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.printf("Erreur de chargement de driver %s",MySQLImpl.class);
        }
    }

    @Override
    public void closeConnexion() throws SQLException{
        conn.close();
        ps.close();
    }

    @Override
    public ResultSet executeSelect(String sql) {
        ResultSet rs=null;
        try {
            rs=ps.executeQuery();

        } catch (SQLException e) {
            System.out.println("Erreur de execution request");
        }
        return rs;
    }
    @Override
    public int executeUpdate() throws SQLException {
        return ps.executeUpdate();
    }

}
