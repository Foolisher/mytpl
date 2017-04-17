package mytpl

import com.google.common.base.CaseFormat
import com.google.common.io.Files
import freemarker.template.Configuration
import freemarker.template.TemplateExceptionHandler

import java.sql.ResultSet
import java.sql.SQLException

import static java.nio.charset.Charset.defaultCharset
import static mytpl.JdbcClient.getTableMeta

/**
 *
 * <p>Created by wanggen/付笑 on 2017/4/14.
 * @author wanggen
 * @date 2017/04/14
 */
class TemplateTest {

  static void main(String[] args) {

    def columns = getTableMeta(Conf.conf.table, {
        // ResultSet --> JavaBean
      ResultSet rst ->
        try {
          return new Column(
              columnName: rst.getString("COLUMN_NAME"),
              fieldName: CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, rst.getString("COLUMN_NAME")),
              dbType: rst.getString("TYPE_NAME").split(" ")[0],
              description: rst.getString("REMARKS")
          )
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }
    });

    Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
    cfg.setDirectoryForTemplateLoading(new File("src/main/resources"));
    cfg.setDefaultEncoding("UTF-8");
    cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    cfg.setLogTemplateExceptions(false);

    // Bean
    def beanFtl = cfg.getTemplate("Bean.ftl")
    StringWriter beanWriter = new StringWriter()
    beanFtl.process(["columns": columns, "conf": Conf.conf], beanWriter)
    Files.write(beanWriter.toString(), new File("tmp/" + Conf.conf.bean + ".java"), defaultCharset())

    // Mapper.xml
    def mapperXmlFtl = cfg.getTemplate("Mapper.ftl")
    StringWriter mapperXmlWriter = new StringWriter()
    mapperXmlFtl.process(["columns": columns, "conf": Conf.conf], mapperXmlWriter)
    Files.write(mapperXmlWriter.toString(), new File("tmp/" + Conf.conf.bean + "Mapper.xml"), defaultCharset())

    // Mapper
    def mapperFtl = cfg.getTemplate("MapperJava.ftl")
    StringWriter mapperWriter = new StringWriter()
    mapperFtl.process(["columns": columns, "conf": Conf.conf], mapperWriter)
    Files.write(mapperWriter.toString(), new File("tmp/" + Conf.conf.bean + "Mapper.java"), defaultCharset())


  }

}
