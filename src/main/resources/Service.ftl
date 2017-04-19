package ${conf.basePackage}.service.${conf.bean}Service;

import ${conf.basePackage}.bean.${conf.bean};

import java.util.List;

/**
  * <p>描述: </p>
  *
  * @author
  */
public interface ${conf.bean}Service {


  Result<Long> save(${conf.bean} ${conf.bean?uncap_first});


  Result<Boolean> delete(Long id);
        
    
  Result<List<${conf.bean}>> queryBy();

}
