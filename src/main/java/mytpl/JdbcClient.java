package mytpl;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	public static <T> void executeQuery(String sql, Function<ResultSet, T> trans, Consumer<List<T>> consumer) {
		try {
			@Cleanup
			Connection conn = getConnection();
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
			throw new RuntimeException(e);
		}

	}

	public static <T> void withTableMeta(String table, Function<ResultSet, T> trans, Consumer<List<T>> consumer) {
		try {
			@Cleanup
			Connection conn = getConnection();
			DatabaseMetaData metaData = conn.getMetaData();
			@Cleanup
			ResultSet resultSet = metaData.getColumns(null, null, table, null);
			List<T> rows = Lists.newArrayList();
			while (resultSet.next()) {
				rows.add(trans.apply(resultSet));
			}
			consumer.accept(rows);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection(Conf.conf.dbUrl, Conf.conf.dbUser,
				Conf.conf.dbPassword);
	}

}
