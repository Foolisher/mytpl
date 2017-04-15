package mytpl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import lombok.Cleanup;
import lombok.Data;
import org.yaml.snakeyaml.Yaml;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <p>Created by wanggen/付笑 on 2017/4/15.
 *
 * @author wanggen
 * @date 2017/04/15
 */
@Data
public class Conf {

	public static Conf conf = null;

	static {
		try {
			@Cleanup
			FileReader fr = new FileReader(new File("").getAbsolutePath() + "/conf.yaml");
			Yaml yaml = new Yaml();
			Conf.conf = yaml.loadAs(fr, Conf.class);
			checkNotNull(Conf.conf);
		} catch (IOException e2) {
			throw new RuntimeException(e2);
		}
	}

	public String dbUrl;
	public String dbUser;
	public String dbPassword;

	public  String beanPackage;     // path
	public  String mapperPackage;   // path
	public  String table;           // to generated table name
	private String bean;            // associated java bean simple file name

}
