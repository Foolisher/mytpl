package mytpl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import com.google.common.collect.Lists;
import lombok.Cleanup;

/**
 * <p>Created by wanggen/付笑 on 2017/4/14.
 *
 * @author wanggen
 * @date 2017/04/14
 */
public class JdbcClient {

	public static <T> void execute(String sql, Function<ResultSet, T> trans, Consumer<List<T>> consumer) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); //MYSQL驱动

			@Cleanup
			Connection conn = DriverManager.getConnection(Conf.conf.dbUrl, Conf.conf.dbUser,
					Conf.conf.dbPassword); //链接本地MYSQL
			@Cleanup
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			@Cleanup
			ResultSet resultSet = preparedStatement.executeQuery();

			List<T> rows = Lists.newArrayList();
			while (resultSet.next()) {
				rows.add(trans.apply(resultSet));
			}
			consumer.accept(rows);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	public static <T> void withTableMeta(Function<ResultSet, T> trans, Consumer<List<T>> consumer) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); //MYSQL驱动

			@Cleanup
			Connection conn = DriverManager.getConnection(Conf.conf.dbUrl, Conf.conf.dbUser, Conf.conf.dbPassword); //链接本地MYSQL
			DatabaseMetaData metaData  = conn.getMetaData();
			ResultSet        resultSet = metaData.getColumns(null, null, Conf.conf.table, null);
			List<T>          rows      = Lists.newArrayList();
			while (resultSet.next()) {
				rows.add(trans.apply(resultSet));
			}
			consumer.accept(rows);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

}
