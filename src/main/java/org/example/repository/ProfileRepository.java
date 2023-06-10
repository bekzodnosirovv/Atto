package org.example.repository;

import lombok.Getter;
import lombok.Setter;
import org.example.Enums.ComponentStatus;
import org.example.Enums.ProfileRole;
import org.example.dto.Profile;
import org.example.util.DataBaseConnection;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@Repository
public class ProfileRepository {
    public Profile getProfileRP(String phone) {
        Profile profile = null;
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "select * from profile where phone=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, phone);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                profile = new Profile();
                profile.setName(rs.getString("name"));
                profile.setSurname(rs.getString("surname"));
                profile.setPhone(rs.getString("phone"));
                profile.setPassword(rs.getString("password"));
                profile.setCreated_date(rs.getTimestamp("created_date").toLocalDateTime());
                profile.setStatus(ComponentStatus.valueOf(rs.getString("status")));
                profile.setRole(ProfileRole.valueOf(rs.getString("profileRole")));
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
        return profile;
    }

    public boolean addProfileRP(Profile profile) {
        boolean t = false;
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "insert into profile(name,surname,phone,password,created_date,status,profileRole) values(?,?,?,?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, profile.getName());
            statement.setString(2, profile.getSurname());
            statement.setString(3, profile.getPhone());
            statement.setString(4, profile.getPassword());
            statement.setTimestamp(5, Timestamp.valueOf(profile.getCreated_date()));
            statement.setString(6, String.valueOf(profile.getStatus()));
            statement.setString(7, String.valueOf(profile.getRole()));
            if (statement.executeUpdate() != 0) t = true;
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

    public List<Profile> getAllProfileRP() {
        List<Profile> profileList = new LinkedList<>();
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "select * from profile";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Profile profile = new Profile();
                profile.setName(rs.getString("name"));
                profile.setSurname(rs.getString("surname"));
                profile.setPhone(rs.getString("phone"));
                profile.setPassword(rs.getString("password"));
                profile.setCreated_date(rs.getTimestamp("created_date").toLocalDateTime());
                profile.setStatus(ComponentStatus.valueOf(rs.getString("status")));
                profile.setRole(ProfileRole.valueOf(rs.getString("profileRole")));
                profileList.add(profile);
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
        return profileList;
    }

    public boolean updateProfileRP(Profile profile) {
        boolean t = false;
        Connection connection = DataBaseConnection.getCon();
        try {
            String sql = "update profile set status=?,profileRole=? where phone=? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(profile.getStatus()));
            statement.setString(2, String.valueOf(profile.getRole()));
            statement.setString(3, profile.getPhone());
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

}
