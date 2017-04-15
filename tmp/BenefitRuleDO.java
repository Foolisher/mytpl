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
    private String   operator;       // 操作人
    private String   features;       // 扩展，KV对
    private Integer  groupId;        // 分组ID
    private String   description;    // 规则的外化文案
    private String   name;           // 权益名称

}