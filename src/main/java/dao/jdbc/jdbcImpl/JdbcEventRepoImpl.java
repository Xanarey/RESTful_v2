package dao.jdbc.jdbcImpl;

import dao.jdbc.JdbcEventRepo;
import model.Event;
import utils.JdbcUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcEventRepoImpl implements JdbcEventRepo {
    @Override
    public Event getById(Long aLong) {
        Event event = Event.builder().build();
        String sqlQuery = "SELECT id, created, updated FROM events WHERE id = ?;";

        try(PreparedStatement ps = JdbcUtils.getPreparedStatement(sqlQuery)) {
            ps.setLong(1, aLong);
            ps.executeQuery();
            ResultSet resultSet = ps.getResultSet();
            while (resultSet.next()) {
                event.setId(resultSet.getLong("id"));
                event.setCreated(resultSet.getString("created"));
                event.setUpdated(resultSet.getString("updated"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return event;
    }

    @Override
    public List<Event> getAll() {
        List<Event> events = new ArrayList<>();
        String sqlQuery = "SELECT id, created, updated FROM events;";
        try(Statement statement = JdbcUtils.getStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                Event event = new Event();
                event.setId(resultSet.getLong("id"));
                event.setCreated(resultSet.getString("created"));
                event.setUpdated(resultSet.getString("updated"));
                events.add(event);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }
}
