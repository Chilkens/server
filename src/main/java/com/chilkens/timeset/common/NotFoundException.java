package com.chilkens.timeset.common;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ByeongChan on 2017. 8. 12..
 */

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "NOT FOUND TABLE") // 404
public class NotFoundException extends RuntimeException{

}
