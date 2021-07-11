package com.tms.storage.user;

import com.tms.DataPattern.UserPattern;
import com.tms.entity.Role;
import com.tms.entity.User;
import com.tms.storage.UserStorable;
import com.tms.storage.connection.DbConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbUserStorage extends DbConnection implements UserStorable {
    private final UserPattern uPattern = new UserPattern();

    @Override
    public void save(User user) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users values (default , ?, ?,?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, switch (user.getRole()) {
                case USER -> "USER";
                case ADMIN -> "ADMIN";
            });
            preparedStatement.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }



    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String username = resultSet.getString(3);
                String password = resultSet.getString(4);
                Role role = switch(resultSet.getString(5)) {
                    case "ADMIN" -> Role.ADMIN;
                    default -> Role.USER;
                };
                User user = new User(id, name, username, password, role);
                userList.add(user);
            }
            return new ArrayList<>(userList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void updateUsernameById(User user, String username) {

        try {
            if(username != null & user != null &
             !username.trim().isEmpty()) {
                PreparedStatement preparedStatement = connection.prepareStatement("update users set uname = ? where id = ?");
                preparedStatement.setString(1, username);
                preparedStatement.setInt(2, user.getId());
                preparedStatement.execute();
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updatePasswordById(User user, String password) {

        try {
            if(user != null & password != null &
             !password.trim().isEmpty()) {
                PreparedStatement preparedStatement = connection.prepareStatement("update users set password = ? where id = ?");
                preparedStatement.setString(1, password);
                preparedStatement.setInt(2, user.getId());
                preparedStatement.execute();
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public User getUserById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int num = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String username = resultSet.getString(3);
            String password = resultSet.getString(4);
            Role role = switch(resultSet.getString(5)) {
                case "ADMIN" -> Role.ADMIN;
                default -> Role.USER;
            };
            return new User(num, name, username, password, role);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void deleteById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteByUsername(String username) {
        assert username != null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from users where uname = ?");
            preparedStatement.setString(1, username);
            preparedStatement.execute();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean existsById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select id from users where id =?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean existsByUsername(String username) {
        try {
            if (username!=null){
            PreparedStatement preparedStatement = connection.prepareStatement("select * from users where uname = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public User getByUsername(String username) {

        try {
            if(username != null & !username.trim().isEmpty()) {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from users where uname=?");
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();
                int num = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String uname = resultSet.getString(3);
                String password = resultSet.getString(4);
                Role role = switch (resultSet.getString(5)) {
                    case "ADMIN" -> Role.ADMIN;
                    default -> Role.USER;
                };
                return new User(num, name, uname, password, role);
            }
         } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }



    public boolean validPassUname(String password, String username) {
        try {
            if (uPattern.passUname(password, username)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean validUname(String username) {
        try {
            if (uPattern.usernamePattern(username)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean validPass(String password) {
        try {
            if (uPattern.pass(password)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean validUserData(String name, String username, String password) {
        return uPattern.userDataPattern(name, username, password);
    }
}



