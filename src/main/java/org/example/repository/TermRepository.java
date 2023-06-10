package org.example.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.Enums.ComponentStatus;
import org.example.dto.Terminal;
import org.example.util.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;


@Repository
public class TermRepository {

    public Terminal getTermRP(Integer termNumber) {
        Terminal terminal = null;
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "select * from terminal where code=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, termNumber);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                terminal = new Terminal();
                terminal.setCode(rs.getInt("code"));
                terminal.setAddress(rs.getString("address"));
                terminal.setStatus(ComponentStatus.valueOf(rs.getString("status")));
                terminal.setCrated_date(rs.getTimestamp("created_date").toLocalDateTime());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return terminal;
    }

    public boolean termUpdateRP(Terminal term) {
        boolean t = false;
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "update terminal set address=?, status=?  where code=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, term.getAddress());
            statement.setString(2, String.valueOf(term.getStatus()));
            statement.setInt(3, term.getCode());
            if (statement.executeUpdate() > 0) t = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return t;
    }


    public boolean add(Terminal term) {
        boolean t = false;
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "insert into terminal(code,address,status,created_date) values(?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, term.getCode());
            statement.setString(2, term.getAddress());
            statement.setString(3, String.valueOf(term.getStatus()));
            statement.setTimestamp(4, Timestamp.valueOf(term.getCrated_date()));
            if (statement.executeUpdate() > 0) t = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    public boolean deleteTermRP(Terminal term) {
        boolean t = false;
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "delete from terminal where code=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, term.getCode());
            if (statement.executeUpdate() > 0) t = true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    public List<Terminal> getAllTermRP() {
        List<Terminal> list = new LinkedList<>();
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "select * from terminal";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Terminal terminal = new Terminal();
                terminal.setCode(rs.getInt("code"));
                terminal.setAddress(rs.getString("address"));
                terminal.setStatus(ComponentStatus.valueOf(rs.getString("status")));
                terminal.setCrated_date(rs.getTimestamp("created_date").toLocalDateTime());
                list.add(terminal);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
