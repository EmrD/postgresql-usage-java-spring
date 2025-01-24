package com.example.demo.Controllers;
import com.example.demo.DbProps;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@RestController
@RequestMapping("/api/v1")
class PostgresqlControllers {
    @GetMapping("/connectDatabase")
    public String connectDatabase() {
        try {
            Connection connection = DriverManager.getConnection(DbProps.connectionString , DbProps.username, DbProps.password);
            return "Connected to the PostgreSQL server successfully!";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @PostMapping("/addUser")
    public String addUser(String name, int age, String email) { //Example Schema: name, age, email
        try {
            Connection connection = DriverManager.getConnection(DbProps.connectionString , DbProps.username, DbProps.password);
            Statement statement = connection.createStatement();
            String query = "INSERT INTO testtable (name, age, email) VALUES ('" + name + "', '" + age + "', '" + email + "')";
            statement.executeUpdate(query);
            return "User added successfully.";
        } catch (Exception e) {
            return "Error while adding user: " + e.getMessage();
        }
    }
}