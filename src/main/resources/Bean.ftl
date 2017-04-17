package ${conf.beanPackage};

import lombok.Data;

/**
 * <p>描述:</p>
 *
 * @author
 */
@Data
public class ${conf.bean} extends BaseDO {

    <#list columns as col><#-- 去掉BaseDO中有的字段 -->
    <#if "id gmtCreate gmtModified status"?split(" ")?seq_contains(col.fieldName)==false>
    private ${col.fieldType()?right_pad(8)} ${"${col.fieldName};"?right_pad(15)} // ${col.description}
    </#if></#list>

}