package com.xd.smartconstruction.pojo.dto;

import com.xd.smartconstruction.common.constant.SortOrderEnum;
import lombok.Data;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author mm
 * @Date 2021-02-01 11:23
 */
@Data
public class BasePageDTO {

    public Integer pageNo;

    public Integer pageSize;

    public String sortKey;

    public String sortOrder;

    public boolean isPageable() {
        if (null == pageNo || null == pageSize) {
            return false;
        }
        if (pageNo < 1) {
            pageNo = 0;
        }
        return pageNo >= 0 && pageSize >= 1;
    }

    public boolean isSortable() {
        if (StringUtils.isBlank(sortKey) || StringUtils.isBlank(sortOrder)) {
            return false;
        }
        SortOrderEnum sortOrderEnum = EnumUtils.getEnum(SortOrderEnum.class, sortOrder.toUpperCase());
        return null != sortOrderEnum;
    }

    public Integer getPageStart() {
        if (!isPageable()) {
            return 0;
        }
        if (pageNo == 0) {
            return 0;
        }
        return (pageNo - 1) * pageSize;

    }
}
