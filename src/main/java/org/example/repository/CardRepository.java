package org.example.repository;

import org.example.Enums.ComponentStatus;
import org.example.dto.Card;
import org.example.dto.Profile;
import org.example.util.DataBaseConnection;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class CardRepository {

    public Card getCardRP(Integer cardNumber) {
        Card card = null;
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "select * from card where card_number=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, cardNumber);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                card = new Card();
                card.setNumber(rs.getInt("card_number"));
                card.setExp_date(rs.getDate("exp_date").toLocalDate());
                card.setBalance(rs.getDouble("balance"));
                card.setStatus(ComponentStatus.valueOf(rs.getString("status")));
                card.setPhone(rs.getString("phone"));
                card.setCreated_date(rs.getTimestamp("created_date").toLocalDateTime());

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
        return card;
    }

    public boolean cardUpdateRP(Card card) {
        boolean t = false;
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "update card set exp_date=?,balance=?,status=?," +
                    "phone=?  where card_number=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDate(1, Date.valueOf(card.getExp_date()));
            statement.setDouble(2, card.getBalance());
            statement.setString(3, String.valueOf(card.getStatus()));
            statement.setString(4, card.getPhone());
            statement.setInt(5, card.getNumber());
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

    public List<Card> getProfileCardListRP(Profile profile) {
        List<Card> list = new LinkedList<>();
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "select * from card where phone=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, profile.getPhone());
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Card card = new Card();
                card.setNumber(rs.getInt("card_number"));
                card.setExp_date(rs.getDate("exp_date").toLocalDate());
                card.setBalance(rs.getDouble("balance"));
                card.setStatus(ComponentStatus.valueOf(rs.getString("status")));
                card.setPhone(rs.getString("phone"));
                card.setCreated_date(rs.getTimestamp("created_date").toLocalDateTime());
                list.add(card);
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

    public boolean add(Card card) {
        boolean t = false;
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "insert into card(card_number,exp_date,balance,status,phone,created_date) values(?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, card.getNumber());
            statement.setDate(2, Date.valueOf(card.getExp_date()));
            statement.setDouble(3, card.getBalance());
            statement.setString(4, String.valueOf(card.getStatus()));
            statement.setString(5, card.getPhone());
            statement.setTimestamp(6, Timestamp.valueOf(card.getCreated_date()));
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

    public boolean deleteCardRP(Card card) {
        boolean t = false;
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "delete from card where card_number=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, card.getNumber());
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

    public List<Card> getAllCardRP() {
        List<Card> list = new LinkedList<>();
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "select * from card";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Card card = new Card();
                card.setNumber(rs.getInt("card_number"));
                card.setExp_date(rs.getDate("exp_date").toLocalDate());
                card.setBalance(rs.getDouble("balance"));
                card.setStatus(ComponentStatus.valueOf(rs.getString("status")));
                card.setPhone(rs.getString("phone"));
                card.setCreated_date(rs.getTimestamp("created_date").toLocalDateTime());
                list.add(card);
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
