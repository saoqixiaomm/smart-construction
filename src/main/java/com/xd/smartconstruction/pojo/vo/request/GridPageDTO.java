package com.xd.smartconstruction.pojo.vo.request;

import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * @author mm
 * @Date 2021-02-01 11:18
 */
@Data
public class GridPageDTO<T> {

    private Integer total;

    private List<T> content;

    public static GridPageDTO emptyInstance() {
        return new GridPageDTO(0, Collections.emptyList());
    }

    public GridPageDTO(Integer total, List<T> content) {
        this.total = total;
        this.content = content;
    }
}
