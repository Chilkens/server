package com.chilkens.timeset.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by hoody on 2017-08-11.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PickList {
    List<String> pickIds;

    int[][] table = new int[5][10];
}
