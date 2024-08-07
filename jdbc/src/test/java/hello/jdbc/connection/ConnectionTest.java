package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManager() throws SQLException {
        // given
        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection connection1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        // when

        log.info("connection = {}", connection);
        log.info("connection2 = {}", connection1);
        // then
    }

    @Test
    void dataSourceConnectionPool() {
        // given
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(URL);
        hikariDataSource.setUsername(USERNAME);
        hikariDataSource.setPassword(PASSWORD);
        hikariDataSource.setMaximumPoolSize(10);
        hikariDataSource.setPoolName("MyPool");
        // when

        // then

    }
}
