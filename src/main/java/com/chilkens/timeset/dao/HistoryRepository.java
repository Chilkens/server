package com.chilkens.timeset.dao;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

/**
 * Created by user on 2017-07-20.
 */
@Repository
public class HistoryRepository {

    DataSource dataSource;

    JdbcTemplate template;

   public void openHistory (String user_id) {
        String query = "SELECT * FROM DATABASE WHERE ID=" + user_id;
        /*return template.queryForObject(query, );*/
   }


}
