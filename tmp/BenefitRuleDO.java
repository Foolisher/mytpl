package com.taobao.gow.bean;

import lombok.Data;

/**
 * @Desc
 *
 * @author
 */
@Data
public class InventorySellerDataDO extends BaseDO {

    private Integer  id;             // 主键
    private Date     gmtCreate;      // 创建时间
    private Date     gmtModified;    // 修改时间
    private Long     status;         // -1删除，1正常，2停用

}