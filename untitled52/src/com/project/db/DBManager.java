package com.project.db;

import com.project.entities.Blog;
import com.project.entities.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;

    public void connect(){
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/test", "postgres", "123");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public User getUser(String login, String password){
        User user = null;
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE login=? AND password=?");
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("login"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name")
                );
                statement.close();
                resultSet.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
    public void addUser(User user){
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users(login, password, full_name) " +
                    " VALUES(?, ?, ?)");
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void addBlog(Blog blog){
        try{
            PreparedStatement statement =
                    connection.prepareStatement("INSERT INTO blogs(user_id, title, short_content, content, post_date) " +
                            "VALUES(?, ?, ?, ?, NOW())");
            statement.setLong(1, blog.getAuthor().getId());
            statement.setString(2, blog.getTitle());
            statement.setString(3, blog.getShortContent());
            statement.setString(4, blog.getContent());

            statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<Blog> getAllBlogs(){
        ArrayList<Blog> blogs = new ArrayList<>();
        try{
            PreparedStatement statement =
                    connection.prepareStatement("SELECT b.id, b.user_id, b.title, b.short_content, b.post_date, u.full_name" +
        " FROM blogs b INNER JOIN users u ON u.id = b.user_id ORDER BY b.post_date DESC");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                blogs.add(new Blog(
                        resultSet.getLong("id"),
                        new User(resultSet.getLong("user_id"),
                                null, null, resultSet.getString("full_name")),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        null,
                        resultSet.getDate("post_date")
                ));
            }
            statement.close();
            resultSet.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return blogs;
    }
    public Blog getBlog(Long id){
        Blog blog = null;
        try{
            PreparedStatement statement =
                    connection.prepareStatement("SELECT b.id, b.user_id, b.title, b.short_content, b.content, b.post_date, u.full_name" +
                    " FROM blogs b INNER JOIN users u ON u.id = b.user_id " +
                    "WHERE b.id=?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                blog = new Blog(
                        resultSet.getLong("id"),
                        new User(resultSet.getLong("user_id"),
                                null, null, resultSet.getString("full_name")),
                        resultSet.getString("title"),
                        resultSet.getString("short_content"),
                        resultSet.getString("content"),
                        resultSet.getDate("post_date")
                );
            }
            statement.close();
            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return blog;
    }

}
