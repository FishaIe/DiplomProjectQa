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

    public static DataHelper.verificationPayStatus getVerificationPayStatus() {
        var codeSQL = "SELECT status FROM payment_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            var status = runner.query(conn, codeSQL, new ScalarHandler<String>());
            return new DataHelper.verificationPayStatus(status);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public static DataHelper.verificationCreditPayStatus getVerificationCreditPayStatus() {
        var codeSQL = "SELECT status FROM credit_request_entity ORDER BY created DESC LIMIT 1";
        try (var conn = getConn()) {
            var status = runner.query(conn, codeSQL, new ScalarHandler<String>());
            return new DataHelper.verificationCreditPayStatus(status);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}
