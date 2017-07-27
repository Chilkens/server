package com.chilkens.timeset.service;

import com.chilkens.timeset.dao.OpenRepository;
import com.chilkens.timeset.domain.Time_table;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by user on 2017-07-20.
 */
public class OpenTableService {
    @Autowired
    OpenRepository openRepository;

    public void save(Time_table time_table){
        openRepository.save(time_table);
    }
}
