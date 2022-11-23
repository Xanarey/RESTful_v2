package dao.jdbc.jdbcImpl;

import dao.jdbc.JdbcFileRepo;
import model.File;
import utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcFileRepoImpl implements JdbcFileRepo {
    @Override
    public File getById(Long aLong) {
        File file = new File();
        String sqlQuery = "SELECT id, name, url FROM files WHERE id = ?;";

        try(PreparedStatement ps = JdbcUtils.getPreparedStatement(sqlQuery)) {
            ps.setLong(1, aLong);
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();
            while (resultSet.next()) {
                file.setId(resultSet.getLong("id"));
                file.setName(resultSet.getString("name"));
                file.setUrl(resultSet.getString("url"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return file;
    }

    @Override
    public List<File> getAll() {
        List<File> files = new ArrayList<>();
        String sqlQuery = "SELECT id, name, url FROM files";

        try(Statement statement = JdbcUtils.getStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                File file = new File();
                file.setId(resultSet.getLong("id"));
                file.setName(resultSet.getString("name"));
                file.setUrl(resultSet.getString("url"));
                files.add(file);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return files;
    }
}
