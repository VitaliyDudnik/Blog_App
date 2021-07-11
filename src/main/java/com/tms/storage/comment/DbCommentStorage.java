package com.tms.storage.comment;

import com.tms.entity.Comment;
import com.tms.entity.Post;
import com.tms.entity.User;
import com.tms.storage.connection.DbConnection;
import com.tms.storage.user.DbUserStorage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DbCommentStorage extends DbConnection {
    private final DbUserStorage u = new DbUserStorage();

    public void save(Comment comment, String text, int userId, int postId) {

        try {
            connection.setAutoCommit(false);
            if (text != null & comment != null) {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into comment values (default ,?,?,?)");
                preparedStatement.setString(1, text);
                preparedStatement.setInt(2, userId);
                preparedStatement.setInt(3, postId);
                preparedStatement.execute();

            }
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

    public void approved(int id) {

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update comment set approved=true where id=" + id + " ");
            preparedStatement.execute();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void notApproved(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update comment set approved=false where id=" + id + " ");
            preparedStatement.execute();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateComment(int id, String text) {
        assert text != null;
        assert !text.trim().isEmpty();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update comment set text = ? where id=" + id + "");
            preparedStatement.setString(1, text);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public List<Comment> getCommentList(Post post) {
        List<Comment> commentList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from comment");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String text = resultSet.getString(2);
                int userId = resultSet.getInt(3);
                int postId = resultSet.getInt(4);
                boolean approve = resultSet.getBoolean(5);
                User userById = u.getUserById(userId);
                commentList.add(new Comment(id, postId, text, userById, approve));
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>(commentList);
    }

    public List<Comment> getComments() {
        List<Comment> comments = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from comment");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String text = resultSet.getString(2);
                int userId = resultSet.getInt(3);
                int postId = resultSet.getInt(4);
                boolean approve = resultSet.getBoolean(5);
                User userById = u.getUserById(userId);
                comments.add(new Comment(id, postId, text, userById, approve));
            }
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ArrayList<>(comments);
    }

    public void deleteByPostId(int postId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from comment where post_id=" + postId + "");
            preparedStatement.execute();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from comment where id=" + id + "");
            preparedStatement.execute();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }



    public Comment getCommentById(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from comment where id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int num = resultSet.getInt(1);
            String text = resultSet.getString(2);
            int userId = resultSet.getInt(3);
            int postId = resultSet.getInt(4);
            boolean approve = resultSet.getBoolean(5);

            return new Comment(num, postId, text, userId, approve);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}


