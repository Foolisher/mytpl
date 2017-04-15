package mytpl;

import lombok.Data;

/**
 * <p>Created by wanggen/付笑 on 2017/4/14.
 *
 * @author wanggen
 * @date 2017/04/14
 */
@Data
public class Column {

	private String columnName;    // sql column name
	private String fieldName;     // java field name
	private String dbType;        // db type eg. VARCHAR
	private String description;   // column comment defined

	public String fieldType() {		// dbType name --> javaType name
		switch (dbType) {
			case "VARCHAR":
			case "TEXT":
				return "String";
			case "BIGINT":
				return "Integer";
			case "INT":
			case "SMALLINT":
			case "TINYINT":
				return "Long";
			case "BYTE":
				return "Byte";
			case "DATE":
			case "DATETIME":
			case "TIMESTAMP":
				return "Date";
			default:
				throw new RuntimeException("找不到的类型: " + dbType);
		}
	}

}