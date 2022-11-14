package dao.jdbc.jdbcImpl;

import dao.JdbcUserRepo;
import model.Event;
import model.User;
import utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserRepoImpl implements JdbcUserRepo {

    @Override
    public User getById(Long aLong) {
        User user = User.builder().build();
        List<Event> events = new ArrayList<>();
        user.setEvents(events);
        String sqlQueryUser = "SELECT users.id, name, events.id AS events_id, created, updated FROM users LEFT JOIN events ON users.id = events.user_id WHERE events.user_id = ?;";
        String sqlQueryNotEvents = "SELECT id, name FROM users WHERE id = ?;";

        try(PreparedStatement ps = JdbcUtils.getPreparedStatement(sqlQueryUser)) {
            ps.setLong(1, aLong);
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();
            if (!resultSet.next()) {
                PreparedStatement pa = JdbcUtils.getPreparedStatement(sqlQueryNotEvents);
                pa.setLong(1, aLong);
                pa.executeQuery();
                ResultSet rs = pa.getResultSet();
                while (rs.next()) {
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                }
            } else {
                resultSet.beforeFirst();
                while (resultSet.next()) {
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    Event event = Event.builder().build();
                    event.setId(resultSet.getLong("events_id"));
                    event.setCreated(resultSet.getString("created"));
                    event.setUpdated(resultSet.getString("updated"));
                    user.getEvents().add(event);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> developerList = new ArrayList<>();
        String sqlQuery = "SELECT id, name FROM users;";
        try(Statement statement = JdbcUtils.getStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                developerList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developerList;
    }
}
