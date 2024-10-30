package edu.hongik.yoondb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@SpringBootApplication
public class YoondbApplication implements ApplicationRunner {

    @Autowired
    DataSource dataSource;
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public static void main(String[] args) {
        SpringApplication.run(YoondbApplication.class, args);
    }


    @Override
    public void run(ApplicationArguments args) throws Exception {
        try (Connection connection = dataSource.getConnection()){
            System.out.println(dataSource.getClass());
            System.out.println(connection.getMetaData().getURL());
            System.out.println(connection.getMetaData().getUserName());

            Statement statement = connection.createStatement();
            String sql = "CREATE TABLE t_product(product_no INTEGER NOT NULL, product_name VARCHAR(255), PRIMARY KEY (product_no))";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jdbcTemplate.execute("INSERT INTO t_product VALUES (1, 'Big shirt')");
    }
}
