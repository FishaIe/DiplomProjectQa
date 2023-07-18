package ru.netology.web.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLHelper {

    private static QueryRunner runner = new QueryRunner();

    private SQLHelper(){
    }


    @SneakyThrows
    private static Connection getConn() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/app","app","pass");
    }

    @SneakyThrows
    public static String getVerificationPayStatus() {
        var codeSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        var status = runner.query(getConn(), codeSQL, new ScalarHandler<String>());
        return status;
    }

    @SneakyThrows
    public static String getVerificationCreditPayStatus() {
        var codeSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        var status = runner.query(getConn(), codeSQL, new ScalarHandler<String>());
        return status;
    }
}
