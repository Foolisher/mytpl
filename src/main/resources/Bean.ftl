package ${conf.beanPackage};

import lombok.Data;

/**
 * @Desc:
 * <p/>
 * @author
 */
@Data
public class ${conf.bean} extends BaseDO {

    <#list columns as col>
    private ${col.fieldType()?right_pad(8)} ${"${col.fieldName};"?right_pad(15)} // ${col.description}
    </#list>

}