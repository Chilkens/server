package com.chilkens.timeset.service;

import com.chilkens.timeset.dao.OpenRepository;
import com.chilkens.timeset.domain.TimeTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by user on 2017-07-20.
 */
@Service
public class OpenTableService {
    @Autowired
    OpenRepository openRepository;
    public void save(TimeTable time_table){
        openRepository.save(time_table);
    }
}
