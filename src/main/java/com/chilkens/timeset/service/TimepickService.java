package com.chilkens.timeset.service;

import com.chilkens.timeset.dao.PickDetailRepository;
import com.chilkens.timeset.dao.PickRepository;
import com.chilkens.timeset.domain.Pick;
import com.chilkens.timeset.domain.PickDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ByeongChan on 2017. 7. 23..
 */
@Service
public class TimepickService {

    @Autowired
    PickRepository pickRepository;

    @Autowired
    PickDetailRepository pickDetailRepository;

    // public void savePick(Pick pickInfo){ pickRepository.save(pickInfo); }
    public Pick savePick(Pick pick){ return pickRepository.save(pick); }

    public void savePickDetail(PickDetail pickDetail){ pickDetailRepository.save(pickDetail); }
}
