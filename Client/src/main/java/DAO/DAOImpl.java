package DAO;

import VO.Discipline;
import VO.Enrollment;
import VO.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOImpl extends DAO<Student> {
    String jdbcDriver = "com.mysql.jdbc.Driver";
    Connection conn = null;
    String username = "root";
    String password = "";
    String serverUrl = "jdbc:mysql://localhost:3306/task?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    Statement stmt = null;
    String dbName = "task";
    String checkDb = "SELECT SCHEMA_NAME FROM `information_schema`.`SCHEMATA` WHERE `SCHEMA_NAME` = '" + dbName + "'";
    ResultSet rs = null;
    boolean dbFound = false;



    public DAOImpl(){
        try {
            //check jdbc driver (mysql connector / j). Make sure the connector is configured correctly (added to libraries) before checking it.
            Class.forName(jdbcDriver);
            System.out.println("Driver Loaded");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Failed To Load");
            System.out.println(ex.getMessage());
        }

        try {
            //connecting to xampp server (Apache Server)
            conn = DriverManager.getConnection(serverUrl, username, password);
            System.out.println("Connected To Server Successfully");
        } catch (SQLException ex) {
            System.out.println("Failed To Connect To Server Successfully");
            System.out.println(ex.getMessage());

        }

        try {
            // Output sql query for checking if the database exists
            System.out.println("Check if database exists query - " + checkDb);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(checkDb);
            // Output the resultset value
            System.out.println("Result Set Value " + rs.next());
            //Move the cursor one row from its current position
            if (rs.next()) {
                //If the database is found in the information_schema, set the boolean value to true
                dbFound = true;
            }

            //If the database is no found create new database
            if (!dbFound) {
                String createNewDatabase = "CREATE DATABASE IF NOT EXISTS " + dbName + "";
                //Display database creation query in the console section.
                System.out.println("Database creation query - " + createNewDatabase);
                //Execute database creation query and store the result in integer variable. Expected reults are 0 and 1.
                int createdb = stmt.executeUpdate(createNewDatabase);

            }
        } catch (SQLException ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }


    public void insert(Student adr) {

    }

    public Student getById(int id) {
        return null;
    }

    public void update(Student adr) {

    }

    public void delete(Student adr) {

    }

    public List<Student> getAll() {
        System.out.println("dao");
        List<Student> students = new ArrayList<Student>();
        try {
            PreparedStatement prep = conn.prepareStatement("SELECT * FROM student");
            ResultSet res = prep.executeQuery();
            while (res.next()) {
                students.add(new Student(res.getInt(1), res.getString(2), res.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }


    public List<Enrollment> getDiscByStudent(Student student){
        List<Enrollment> enroll = new ArrayList<Enrollment>();

        try {
            PreparedStatement prep = conn.prepareStatement("SELECT enrollment.id, disciplines.id, disciplines.name, credits, student.id, student.fio, course, grade\n" +
                    "FROM (student INNER JOIN enrollment on student.id = id_student)\n" +
                    "  INNER JOIN disciplines ON id_discipline = disciplines.id\n" +
                    "WHERE id_student=? AND fio=?;");

            prep.setInt(1, student.getStudentId());
            prep.setString(2, student.getStudentName());

            ResultSet res = prep.executeQuery();
            enroll = parseResToEnroll(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enroll;
    }


    public List<Enrollment> getDiscByStudentId(int id){
        List<Enrollment> enroll = new ArrayList<Enrollment>();
        try {
            PreparedStatement prep = conn.prepareStatement("SELECT enrollment.id, disciplines.id, disciplines.name, credits, student.id, student.fio, course, grade\n" +
                    "FROM (student INNER JOIN enrollment on STUDENT.id = id_student)\n" +
                    "  INNER JOIN disciplines ON id_discipline = disciplines.id\n" +
                    "WHERE id_student=?;");

            prep.setInt(1, id);

            ResultSet res = prep.executeQuery();
            enroll = parseResToEnroll(res);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return enroll;
    }

    public List<Enrollment> parseResToEnroll(ResultSet res){
        List<Enrollment> enroll = new ArrayList<Enrollment>();

        while (true) {
            try {
                if (!res.next()) break;

                enroll.add(new Enrollment(res.getInt(1),
                        new Student(res.getInt(5), res.getString(6), res.getInt(7)),
                        new Discipline(res.getInt(2), res.getString(3), res.getFloat(4)),
                        res.getInt(8)));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return enroll;
    }

    public void close() {

        try {
            conn.commit();
            conn.close();
        } catch (SQLException se) {
            System.out.println("Some troubles with close connection");
        }
    }
}