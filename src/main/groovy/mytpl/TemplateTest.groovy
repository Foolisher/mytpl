package mytpl

import com.google.common.base.CaseFormat
import com.google.common.base.Joiner
import com.google.common.io.Files
import freemarker.template.Configuration
import freemarker.template.TemplateExceptionHandler

import java.nio.charset.Charset
import java.sql.ResultSet
import java.sql.SQLException

import static mytpl.JdbcClient.withTableMeta

/**
 *
 * <p>Created by wanggen/付笑 on 2017/4/14.
 * @author wanggen
 * @date 2017/04/14
 */
class TemplateTest {

  public static void main(String[] args) {

    withTableMeta({
        // 转换ResultSet
      ResultSet rst ->
        try {
          return new Column(
              columnName: rst.getString("COLUMN_NAME"),
              fieldName: CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, rst.getString("COLUMN_NAME")),
              dbType: rst.getString("TYPE_NAME").split(" ")[0],
              description: rst.getString("REMARKS")
          )
        } catch (SQLException e) {
          e.printStackTrace();
          throw new RuntimeException(e);
        }
    }, {
        // 消费 list:JavaBean
      List<Column> columns ->

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        cfg.setDirectoryForTemplateLoading(new File(new File("").getAbsolutePath() + "/src/main/resources"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);

        // Bean
        def beanFtl = cfg.getTemplate("Bean.ftl")
        StringWriter beanWriter = new StringWriter()
        beanFtl.process(["columns": columns, "conf": Conf.conf, "Joiner": Joiner], beanWriter)
        Files.write(beanWriter.toString(), new File("tmp/" + Conf.conf.bean + ".java"), Charset.defaultCharset())

        // Mapper.xml
        def mapperXmlFtl = cfg.getTemplate("Mapper.ftl")
        StringWriter mapperXmlWriter = new StringWriter()
        mapperXmlFtl.process(["columns": columns, "conf": Conf.conf, "Joiner": Joiner], mapperXmlWriter)
        Files.write(mapperXmlWriter.toString(), new File("tmp/" + Conf.conf.bean + "Mapper.xml"), Charset.defaultCharset())

        // Mapper
        def mapperFtl = cfg.getTemplate("MapperJava.ftl")
        StringWriter mapperWriter = new StringWriter()
        mapperFtl.process(["columns": columns, "conf": Conf.conf, "Joiner": Joiner], mapperWriter)
        Files.write(mapperWriter.toString(), new File("tmp/" + Conf.conf.bean + "Mapper.java"), Charset.defaultCharset())


    });


  }

}
