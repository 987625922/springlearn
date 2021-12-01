package jdbc.dao;

import jdbc.bean.Book;
import jdbc.utils.DbUtil;
import jdbc.utils.SqlUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCBookDao {
    private SqlUtil sqlUtil;

    public JDBCBookDao() {
        sqlUtil = new SqlUtil();
    }


    public List<Book> queryAll() {
        String sql = sqlUtil.queryAll(Book.class);
        List<Book> books = new ArrayList<>();
        try (Connection conn = DbUtil.getConnection();PreparedStatement statement =
                     conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setBook_id(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setNumber(resultSet.getString("number"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Integer insert(Book book) {
        String sql = sqlUtil.insert(book);
        Integer id = null;
        try (Connection conn = DbUtil.getConnection();PreparedStatement statement =
                     conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.execute();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void delete(Book book) {
        String sql = sqlUtil.delete(book);
        try (Connection conn = DbUtil.getConnection();PreparedStatement statement =
                     conn.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Book book) {
        String sql = sqlUtil.update(book);
        try (Connection conn = DbUtil.getConnection();PreparedStatement statement =
                     conn.prepareStatement(sql)) {
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Book query(Book book) {
        String sql = sqlUtil.query(book);
        Book resultBook = null;
        try (Connection conn = DbUtil.getConnection();PreparedStatement statement =
                     conn.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                resultBook = new Book();
                resultBook.setBook_id(resultSet.getInt("id"));
                resultBook.setName(resultSet.getString("name"));
                resultBook.setNumber(resultSet.getString("number"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultBook;
    }
}
