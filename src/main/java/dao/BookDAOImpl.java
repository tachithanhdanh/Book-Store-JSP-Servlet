package dao;

import database.JDBCUtil;
import model.Author;
import model.Book;
import model.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    @Override
    public List<Book> selectAll() {
        List<Book> bookList = new ArrayList<>();
        try {
            // Step 1: Open a connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create a statement
            String sql = "SELECT * FROM book";
            PreparedStatement st = con.prepareStatement(sql);

            // Step 3: Execute the query
            ResultSet rs = st.executeQuery();

            // Step 4: Process the result set
            while (rs.next()) {
                Book book = extractFromResultSet(rs);
                bookList.add(book);
            }

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Select all books failed");
            System.out.println(e.getMessage());
        }
        return bookList;
    }

    @Override
    public Book selectById(Book book) {
        Book resultBook = null;
        try {
            // Step 1: Open a connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create a statement
            String sql = "SELECT * FROM book WHERE book_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, book.getBookId());

            // Step 3: Execute the query
            ResultSet rs = st.executeQuery();

            // Step 4: Process the result set
            if (rs.next()) {
                resultBook = extractFromResultSet(rs);
            }

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Select book by id failed");
            System.out.println(e.getMessage());
        }

        return resultBook;
    }

    @Override
    public int insert(Book book) {
        int result = 0;
        try {
            // Step 1: Open a connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create a statement
            String sql = "INSERT INTO book"
                    + "(book_id, book_title, author_id, publication_year, import_price, "
                    + "list_price, selling_price, quantity, category_id, description)"
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, book.getBookId());
            st.setString(2, book.getBookTitle());
            st.setString(3, book.getAuthor().getAuthorId());
            st.setInt(4, book.getPublicationYear());
            st.setDouble(5, book.getImportPrice());
            st.setDouble(6, book.getListPrice());
            st.setDouble(7, book.getSellingPrice());
            st.setInt(8, book.getQuantity());
            st.setString(9, book.getCategory().getCategoryId());
            st.setString(10, book.getDescription());

            // Step 3: Execute the query
            result = st.executeUpdate();

            // Step 4: Process the result
            System.out.println("SQL command executed: " + sql);
            System.out.println("(" + result + " row(s) affected)\n");

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);

        } catch (SQLException e) {
            System.out.println("Insert book failed");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int insertAll(List<Book> bookList) {
        int result = 0;
        for (Book book : bookList) {
            result += insert(book);
        }
        return result;
    }

    @Override
    public int delete(Book book) {
        int result = 0;
        try {
            // Step 1: Open a connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create a statement
            String sql = "DELETE FROM book WHERE book_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, book.getBookId());

            // Step 3: Execute the query
            result = st.executeUpdate();

            // Step 4: Process the result
            System.out.println("SQL command executed: " + sql);
            System.out.println("(" + result + " row(s) affected)\n");

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Delete book failed");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public int deleteAll(List<Book> bookList) {
        int result = 0;
        for (Book book : bookList) {
            result += delete(book);
        }
        return result;
    }

    @Override
    public int update(Book book) {
        int result = 0;
        try {
            // Step 1: Open a connection
            Connection con = JDBCUtil.getConnection();

            // Step 2: Create a statement
            String sql = "UPDATE book SET book_title=?, author_id=?, publication_year=?, "
                    + "import_price=?, list_price=?, selling_price=?, quantity=?, "
                    + "category_id=?, description=? WHERE book_id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, book.getBookTitle());
            st.setString(2, book.getAuthor().getAuthorId());
            st.setInt(3, book.getPublicationYear());
            st.setDouble(4, book.getImportPrice());
            st.setDouble(5, book.getListPrice());
            st.setDouble(6, book.getSellingPrice());
            st.setInt(7, book.getQuantity());
            st.setString(8, book.getCategory().getCategoryId());
            st.setString(9, book.getDescription());
            st.setString(10, book.getBookId());

            // Step 3: Execute the query
            result = st.executeUpdate();

            // Step 4: Process the result
            System.out.println("SQL command executed: " + sql);
            System.out.println("(" + result + " row(s) affected)\n");

            // Step 5: Close the connection
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println("Update book failed");
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public Book extractFromResultSet(java.sql.ResultSet rs) throws SQLException {
        String bookId = rs.getString("book_id");
        String bookTitle = rs.getString("book_title");
        String authorId = rs.getString("author_id");
        int publicationYear = rs.getInt("publication_year");
        double importPrice = rs.getDouble("import_price");
        double listPrice = rs.getDouble("list_price");
        double sellingPrice = rs.getDouble("selling_price");
        int quantity = rs.getInt("quantity");
        String categoryId = rs.getString("category_id");
        String description = rs.getString("description");
        Author tempAuthor = new Author();
        tempAuthor.setAuthorId(authorId);
        AuthorDAO authorDAO = new AuthorDAOImpl();
        Author author = authorDAO.selectById(tempAuthor);
        CategoryDAO categoryDAO = new CategoryDAOImpl();
        Category category = categoryDAO.selectById(new Category(categoryId, ""));
        return new Book(bookId, bookTitle, author, publicationYear,
                        importPrice, listPrice, sellingPrice,
                        quantity, category, description);
    }
}
