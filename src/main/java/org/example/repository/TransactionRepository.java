package org.example.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.Enums.TransactionType;
import org.example.container.ComponentContainer;
import org.example.dto.Profile;
import org.example.dto.Transaction;
import org.example.util.DataBaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
@Setter
@Getter
public class TransactionRepository {

    public List<StringBuilder> getProfTrListRP(String phone) {
        List<StringBuilder> list = new LinkedList<>();
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "select card_number,COALESCE(address,'Mavjud emas'),amount,transaction.created_date,tr_type from transaction " +
                    "left join terminal  on transaction.terminal_code=terminal.code" +
                    " where card_number in (select card_number from card where phone='" + phone + "') order by created_date desc";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                StringBuilder builder = new StringBuilder();
                builder.append("Transaction(Card number : ").append(resultSet.getInt("card_number"))
                        .append(", Address : ").append(resultSet.getString("coalesce"))
                        .append(", Amount : ").append(resultSet.getDouble("amount"))
                        .append(", Transaction date : ").append(resultSet.getTimestamp("created_date").toLocalDateTime())
                        .append(", Type : ").append(resultSet.getString("tr_type"));
                list.add(builder);
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

    public boolean addReFillRP(Transaction tr, Double balance) {
        boolean t = false;
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "BEGIN; update card set balance=balance+? where card_number=?;" +
                    "insert into transaction(card_number,amount,terminal_code,tr_type,created_date) values(?,?,?,?,?);" +
                    "COMMIT;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, balance);
            statement.setInt(2, tr.getCard_number());
            statement.setInt(3, tr.getCard_number());
            statement.setDouble(4, tr.getAmount());
            statement.setInt(5, tr.getTerm_code());
            statement.setString(6, String.valueOf(tr.getType()));
            statement.setTimestamp(7, Timestamp.valueOf(tr.getCreated1_date()));
//            if (statement.executeUpdate() != 0) t = true;
            System.out.println(statement.execute());
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

    public List<Transaction> getAllRP() {
        List<Transaction> list = new LinkedList<>();
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "select * from transaction order by created_date desc";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Transaction tr = new Transaction();
                tr.setCard_number(resultSet.getInt("card_number"));
                tr.setAmount(resultSet.getDouble("amount"));
                tr.setTerm_code(resultSet.getInt("terminal_code"));
                tr.setType(TransactionType.valueOf(resultSet.getString("tr_type")));
                tr.setCreated1_date(resultSet.getTimestamp("created_date").toLocalDateTime());
                list.add(tr);
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

    public List<Transaction> getAllByRP(Integer number) {
        List<Transaction> list = new LinkedList<>();
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "select * from transaction where card_number=%d or terminal_code=%d" +
                    "order by created_date desc";
            Statement statement = connection.createStatement();
            sql = String.format(sql, number, number);
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Transaction tr = new Transaction();
                tr.setCard_number(resultSet.getInt("card_number"));
                tr.setAmount(resultSet.getDouble("amount"));
                tr.setTerm_code(resultSet.getInt("terminal_code"));
                tr.setType(TransactionType.valueOf(resultSet.getString("tr_type")));
                tr.setCreated1_date(resultSet.getTimestamp("created_date").toLocalDateTime());
                list.add(tr);
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

    public List<Transaction> getDailyTrRP(String date) {
        List<Transaction> list = new LinkedList<>();
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "select * from transaction where CAST(created_date as DATE)= '%s'";
            sql = String.format(sql, date);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Transaction tr = new Transaction();
                tr.setCard_number(resultSet.getInt("card_number"));
                tr.setAmount(resultSet.getDouble("amount"));
                tr.setTerm_code(resultSet.getInt("terminal_code"));
                tr.setType(TransactionType.valueOf(resultSet.getString("tr_type")));
                tr.setCreated1_date(resultSet.getTimestamp("created_date").toLocalDateTime());
                list.add(tr);
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

    public List<Transaction> getInterimTrRP(String fromDate, String toDate) {
        List<Transaction> list = new LinkedList<>();
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "select * from transaction where CAST(created_date as DATE) between '%s' and '%s'";
            sql = String.format(sql, fromDate, toDate);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Transaction tr = new Transaction();
                tr.setCard_number(resultSet.getInt("card_number"));
                tr.setAmount(resultSet.getDouble("amount"));
                tr.setTerm_code(resultSet.getInt("terminal_code"));
                tr.setType(TransactionType.valueOf(resultSet.getString("tr_type")));
                tr.setCreated1_date(resultSet.getTimestamp("created_date").toLocalDateTime());
                list.add(tr);
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


    public boolean addPaymentRP(Transaction tr) {
        boolean t = false;
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "BEGIN; update card set balance=balance-? where card_number=?;" +
                    "update card set balance=balance+? where card_number=?;" +
                    "insert into transaction(card_number,amount,terminal_code,tr_type,created_date) values(?,?,?,?,?);" +
                    "COMMIT;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, ComponentContainer.fairy);
            statement.setInt(2, tr.getCard_number());
            statement.setDouble(3, ComponentContainer.fairy);
            statement.setInt(4, ComponentContainer.termCardNumber);
            statement.setInt(5, tr.getCard_number());
            statement.setDouble(6, tr.getAmount());
            statement.setInt(7, tr.getTerm_code());
            statement.setString(8, String.valueOf(tr.getType()));
            statement.setTimestamp(9, Timestamp.valueOf(tr.getCreated1_date()));
//            if (statement.executeUpdate() != 0) t = true;
            System.out.println(statement.execute());
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
}
