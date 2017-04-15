package mytpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.yaml.snakeyaml.Yaml;

/**
 * <p>Created by wanggen/付笑 on 2017/4/15.
 *
 * @author wanggen
 * @date 2017/04/15
 */
public class Conf {

	public static Conf conf = new Conf();

	static {
		FileReader fr = null;
		try {
			fr = new FileReader(new File("").getAbsolutePath() + "/conf.yaml");
			Yaml yaml = new Yaml();
			Conf.conf = yaml.loadAs(fr, Conf.class);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	public String dbUrl;
	public String dbUser;
	public String dbPassword;
	public String resourceRoot;

	public String beanPackage;
	public String mapperPackage;
	public String table;
	public String bean;

	public static Conf getConf() {
		return conf;
	}

	public String getDbUrl() {
		return dbUrl;
	}

	public String getDbUser() {
		return dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public String getResourceRoot() {
		return resourceRoot;
	}

	public String getBeanPackage() {
		return beanPackage;
	}

	public String getMapperPackage() {
		return mapperPackage;
	}

	public String getTable() {
		return table;
	}

	public String getBean() {
		return bean;
	}
}
