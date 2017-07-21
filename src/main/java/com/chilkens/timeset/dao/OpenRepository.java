package com.chilkens.timeset.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by user on 2017-07-20.
 */
@Repository
public class OpenRepository {

    DataSource dataSource;

    JdbcTemplate template;

    public void openTable (/*프런트에서 오는 값들*/) {
        template.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

               String query = "INSERT INTO DATABASE () VALUES ()";
               PreparedStatement pstmt = con.prepareStatement(query);
               /*pstmt.setString(1, bName);
               pstmt.setString(2, bTitle);
               pstmt.setString(3, bContent);*/

                return pstmt;
            }
        });
    }

}
