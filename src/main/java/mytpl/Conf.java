package mytpl;

import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;
import lombok.Data;
import org.yaml.snakeyaml.Yaml;

import static com.google.common.base.Preconditions.checkNotNull;
import static java.nio.charset.Charset.defaultCharset;

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
			Conf.conf = checkNotNull(new Yaml().loadAs(Files.toString(new File("conf.yaml"), defaultCharset()), Conf.class));
		} catch (IOException e2) {
			throw new RuntimeException(e2);
		}
	}

	public String dbUrl;
	public String dbUser;
	public String dbPassword;

	public String beanPackage;     // path
	public String mapperPackage;   // path
	public String table;           // to generated table name
	public String bean;            // associated java bean simple file name

}
