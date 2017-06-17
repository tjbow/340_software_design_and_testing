package plugin;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteTransaction {

    private Connection connection;
    private boolean noErrors = true;

    public SQLiteTransaction()
    {
        try
        {
            final String driver = "org.sqlite.JDBC";
            Class.forName(driver);
            openConnection();
        }
        catch (ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void openConnection()
    {
        if (connection == null)
        {

            String dbName = "ttr.sqlite";
            String connectionURL = "jdbc:sqlite:" + dbName;

            try
            {
                //Open a database connection
                this.connection = DriverManager.getConnection(connectionURL);

                //start a transaction
                this.connection.setAutoCommit(false);

            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }

        }
    }

    public Connection getConnection() {
        return connection;
    }

    /**
     * Close connection to database and commit or rollback transaction
     * @return whether or not the connection is now closed
     */
    public boolean closeConnection()
    {
        boolean returnValue = false;
        try
        {
            if (this.noErrors)
            {
                connection.commit();
                returnValue = true;
            }
            else
            {
                connection.rollback();
                returnValue = false;
            }

        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            returnValue = false;
        }
        finally
        {
            try
            {
                connection.close();
            }
            catch (SQLException e)
            {
                System.out.println(e.getMessage());
            }
        }
        //reset the noErrors flag
        this.noErrors = true;
        return returnValue;
    }

    public void setNoErrors(boolean noErrors) {
        this.noErrors = noErrors;
    }
}
