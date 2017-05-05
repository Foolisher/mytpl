package ${conf.basePackage}.bean;

import lombok.Data;

/**
 * <p>描述: </p>
 *
 * @author
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ${conf.bean} extends BaseDO {

    <#list columns as col><#-- 去掉BaseDO中有的字段 -->
    <#if "id gmtCreate gmtModified status features"?split(" ")?seq_contains(col.fieldName)==false>
    private ${col.fieldType()?right_pad(8)} ${"${col.fieldName};"?right_pad(15)} // ${col.description}
    </#if></#list>

}