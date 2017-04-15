package mytpl;

/**
 * <p>Created by wanggen/付笑 on 2017/4/14.
 *
 * @author wanggen
 * @date 2017/04/14
 */
//@Data
//@ToString
public class Column {

	private String columnName;
	private String fieldName;
	private String dbType;
	private String description;

	public String fieldType() {
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
			case "DATETIME":
			case "TIMESTAMP":
				return "Date";
			default:
				throw new RuntimeException("找不到的类型: " + dbType);
		}
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Column{" +
				"columnName='" + columnName + '\'' +
				", fieldName='" + fieldName + '\'' +
				", dbType='" + dbType + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}