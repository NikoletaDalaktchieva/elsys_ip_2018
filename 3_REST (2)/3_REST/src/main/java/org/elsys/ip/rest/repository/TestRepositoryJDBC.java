package org.elsys.ip.rest.repository;

import org.elsys.ip.rest.config.HibernateUtil;
import org.elsys.ip.rest.model.Test;
import org.hibernate.Session;

import javax.ws.rs.POST;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestRepositoryJDBC extends TestRepository {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/ip";

    static final String USER = "root";
    static final String PASS = "root";

    @Override
    public List<Test> getTestList(){
        List<Test> tests = new ArrayList<>();
        Connection conn = null;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = null;
            ResultSet rs = null;//редове от базата
            try{
                stmt = conn.createStatement();
                String sql = "SELECT id, name FROM ip.test";
                rs = stmt.executeQuery(sql);
                while (rs.next()){
                    Test t = new Test();
                    t.setId(rs.getInt("id"));
                    t.setName(rs.getString("name"));
                    tests.add(t);
                }
            }finally {
                try{
                    if(!stmt.isClosed()){
                        stmt.close();
                    }
                    if(!rs.isClosed()){
                        rs.close();
                    }

                } catch (SQLException e){e.printStackTrace();}
            };


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            assert conn != null;
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return tests;
    }

    public void addTest(){
        System.out.println("Maven + Hibernate + MySQL");
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        Test test = new Test();

        test.setName("Name");

        session.save(test);
        session.getTransaction().commit();
    }

}
