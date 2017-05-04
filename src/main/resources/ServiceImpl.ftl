package ${conf.basePackage}.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkState;

/**
 * <p>描述: </p>
 *
 * @author
 */
@Service
@Slf4j
public class ${conf.bean}ServiceImpl implements ${conf.bean}Service {
<#assign mapper=conf.bean?uncap_first+"Mapper"/>

	@Resource
	private ${conf.bean}Mapper ${mapper};

	@Override
	public Result<Long> save(${conf.bean} ${conf.bean?uncap_first}) {
		${mapper}.insert(${conf.bean?uncap_first});
    	return entity.getId();
	}

	@Override
	public Result<Boolean> delete(Long id) {
		${mapper}.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public Result<List<InventoryTaskDO>> queryBy() {
		return Result.onSuccess(${mapper}.queryBy(new HashMap()));
	}

}
