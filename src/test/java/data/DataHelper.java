package data;

import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataHelper {

    private DataHelper() {
    }

    private static QueryRunner runner = new QueryRunner();

    private static Connection getConn() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static VerificationCode getVerificationCode() {
        var codeSQL = "SELECT code FROM auth_codes ORDER BY created DESC LIMIT 1";
        var runner = new QueryRunner();
        try (var conn = getConn()) {
            var code = runner.query(conn, codeSQL, new ScalarHandler<String>());
            return new VerificationCode(code);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @SneakyThrows
    public static void clearAll() {
        var cardsClearSQL = "DELETE FROM cards";
        var auth_codesClearSQL = "DELETE FROM auth_codes";
        var usersClearSQL = "DELETE FROM users";

        runner.update(getConn(), cardsClearSQL);
        runner.update(getConn(), auth_codesClearSQL);
        runner.update(getConn(), usersClearSQL);
    }
}