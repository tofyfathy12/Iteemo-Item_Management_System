import java.sql.*;


public class ItemDBHandler {
    private Connection connection = null;
    private String dbFileName;

    public ItemDBHandler(String dbFileName) {
        this.dbFileName = dbFileName;
    }

    /**
     * Establishes a connection to the SQLite database.
     */
    public Connection connect() {
        try {
            String url = "jdbc:sqlite:" + dbFileName;
            connection = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite database (" + dbFileName + ") has been established.");
        } catch (SQLException e) {
            System.err.println("Error connecting to SQLite database: " + e.getMessage());
        }
        return connection;
    }

    /**
     * Closes the database connection.
     */
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection to SQLite database has been closed.");
            }
        } catch (SQLException ex) {
            System.err.println("Error closing SQLite connection: " + ex.getMessage());
        }
    }

    /**
     * Creates the 'items' table in the database if it doesn't already exist.
     */
    public void createTable() {
        if (connection == null) {
            System.err.println("Cannot create table, no database connection.");
            return;
        }
        // SQL statement for creating a new table
        // Ensures that 'id' is the primary key.
        String sql = "CREATE TABLE IF NOT EXISTS items ("
                   + " id INTEGER PRIMARY KEY,"
                   + " name TEXT NOT NULL,"
                   + " description TEXT,"
                   + " category TEXT,"
                   + " priority INTEGER NOT NULL"
                   + ");";

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Error creating 'items' table: " + e.getMessage());
        }
    }

    /**
     * Inserts a new item into the 'items' table.
     * @param item The item to insert.
     * @return true if insertion was successful, false otherwise.
     */
    public boolean insertItem(Item item) {
        if (connection == null) {
            System.err.println("Cannot insert item, no database connection.");
            return false;
        }
        // SQL statement for inserting a new record
        String sql = "INSERT INTO items(id, name, description, category, priority) VALUES(?,?,?,?,?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, item.getID());
            pstmt.setString(2, item.getName());
            pstmt.setString(3, item.getDesc());
            pstmt.setString(4, item.getCategory());
            pstmt.setInt(5, item.getPriority());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            // Check for unique constraint violation (Primary Key constraint)
            // SQLite error code 19 usually indicates a constraint violation.
            if (e.getErrorCode() == 19 && e.getMessage().toLowerCase().contains("unique constraint failed: items.id")) {
                 System.err.println("Error inserting item: Item with ID " + item.getID() + " already exists in database (Primary Key constraint).");
            } else {
                System.err.println("Error inserting item with ID " + item.getID() + ": " + e.getMessage());
            }
            return false;
        }
    }

    /**
     * Updates an existing item in the 'items' table.
     * @param item The item with updated details.
     * @return true if update was successful, false otherwise.
     */
    public boolean updateItem(Item item) {
        if (connection == null) {
            System.err.println("Cannot update item, no database connection.");
            return false;
        }
        // SQL statement for updating an existing record
        String sql = "UPDATE items SET name = ?, description = ?, category = ?, priority = ? WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getDesc());
            pstmt.setString(3, item.getCategory());
            pstmt.setInt(4, item.getPriority());
            pstmt.setInt(5, item.getID()); // ID for the WHERE clause
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error updating item with ID " + item.getID() + ": " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes an item from the 'items' table by its ID.
     * @param id The ID of the item to delete.
     * @return true if deletion was successful, false otherwise.
     */
    public boolean deleteItem(int id) {
        if (connection == null) {
            System.err.println("Cannot delete item, no database connection.");
            return false;
        }
        // SQL statement for deleting a record
        String sql = "DELETE FROM items WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affectedRows = pstmt.executeUpdate();
             if (affectedRows > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting item with ID " + id + ": " + e.getMessage());
            return false;
        }
    }
   
}
