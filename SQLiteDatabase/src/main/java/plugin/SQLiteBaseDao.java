package plugin;


import com.group4.shared.Model.CommandList;
import com.group4.shared.Model.Game.Game;
import com.group4.shared.command.Command;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public abstract class SQLiteBaseDao {

    protected SQLiteTransaction transaction;

    public SQLiteBaseDao(SQLiteTransaction transaction)
    {
        this.transaction = transaction;
    }



    protected boolean update(String sqlString, List<Object> entries)
    {
        boolean returnValue = true;
        PreparedStatement stmt = null;
        try
        {
            stmt = transaction.getConnection().prepareStatement(sqlString);

            for (Object entry : entries)
            {
                if (entry.getClass() == String.class)
                {
                    stmt.setString(entries.indexOf(entry) + 1, (String) entry);
                }
                else if (entry.getClass() == Game.class)
                {
                    stmt.setBytes(entries.indexOf(entry) + 1, convertSerializableToByteArray( (Game) entry ));
                }
                else if (entry.getClass() == ArrayList.class)
                {
                    stmt.setBytes(entries.indexOf(entry) + 1, convertSerializableToByteArray( (ArrayList<Command>) entry));
                }
            }

            if (stmt.executeUpdate() > 1)
            {
                transaction.setNoErrors(false);
                returnValue = false;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            returnValue = false;
            transaction.setNoErrors(false);

        }
        finally
        {
            try
            {
                if (stmt != null) stmt.close();
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
                returnValue = false;
                transaction.setNoErrors(false);
            }
        }

        return returnValue;
    }

    protected boolean updateBatch(String sqlString, List<List<Object>> entriesList)
    {
        boolean returnValue = true;
        PreparedStatement stmt = null;
        try
        {
            stmt = transaction.getConnection().prepareStatement(sqlString);

            for (List<Object> entries : entriesList)
            {
                for (Object entry : entries)
                {
                    if (entry.getClass() == String.class)
                    {
                        stmt.setString(entries.indexOf(entry) + 1, (String) entry);
                    }
                }
                stmt.addBatch();
            }

            if (stmt.executeBatch().length != entriesList.size())
            {
                transaction.setNoErrors(false);
                returnValue = false;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            returnValue = false;
            transaction.setNoErrors(false);
            transaction.closeConnection();
        }
        finally
        {
            try
            {
                if (stmt != null) stmt.close();
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
                returnValue = false;
                transaction.setNoErrors(false);
            }
        }

        return returnValue;
    }



    protected boolean createTable(String sqlString)
    {
        boolean returnValue = true;
        Statement stmt = null;

        try {
            stmt = transaction.getConnection().createStatement();
            stmt.executeUpdate(sqlString);

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            transaction.setNoErrors(false);
            returnValue = false;
        }
        finally
        {
            try
            {
                if (stmt != null)
                {
                    stmt.close();
                }
            }
            catch (SQLException e)
            {
                System.out.println(e.getMessage());
                transaction.setNoErrors(false);
                returnValue = false;
            }
        }
        return returnValue;
    }

    protected boolean dropTable(String sqlString)
    {
        boolean returnValue = true;
        Statement stmt = null;

        try {
            stmt = transaction.getConnection().createStatement();
            stmt.executeUpdate(sqlString);

        } catch (SQLException e) {

            System.out.println(e.getMessage());
            transaction.setNoErrors(false);
            returnValue = false;
        }
        finally
        {
            try
            {
                if (stmt != null)
                {
                    stmt.close();
                }
            }
            catch (SQLException e)
            {
                System.out.println(e.getMessage());
                transaction.setNoErrors(false);
                returnValue = false;
            }
        }
        return returnValue;
    }

    protected List<List<Object>> query(String sqlString, List<Object> queryFields,
                                               List<Class> resultTypes)
    {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<List<Object>> resultList = new ArrayList<>();
        try
        {
            stmt = transaction.getConnection().prepareStatement(sqlString);
            for(Object field : queryFields)
            {
                if (field.getClass() == String.class)
                {
                    stmt.setString(queryFields.indexOf(field) + 1, (String) field);
                }
            }

            rs = stmt.executeQuery();
            while (rs.next())
            {
                List<Object> rowResults = new ArrayList<>();
                for (Class type : resultTypes)
                {
                    if (type == InputStream.class)
                    {
                        Serializable obj = convertByteArrayToSerializable(rs.getBytes( resultTypes.indexOf(type) + 1 ));
                        rowResults.add( obj );
                    }
                    else if (type == String.class)
                    {
                        String str = rs.getString( resultTypes.indexOf(type)+1);
                        rowResults.add( str );
                    }
                    //this is a horrible workaround for the fact that I didn't think of if there were
                    //multiple of a single type (sorry)
                    else if (type == Integer.class)
                    {
                        String str = rs.getString(resultTypes.indexOf(type) + 1);
                        rowResults.add( str );
                    }
                }
                resultList.add(rowResults);
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            transaction.setNoErrors(false);
        }
        finally
        {
            try
            {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            }
            catch (SQLException e)
            {

                System.out.println(e.getMessage());
                transaction.setNoErrors(false);

            }
        }
        return resultList;
    }

    protected boolean deleteSingle(String sqlString, List<Object> queryFields)
    {
        boolean returnValue = true;
        PreparedStatement stmt = null;
        try
        {
            stmt = transaction.getConnection().prepareStatement(sqlString);
            for (Object entry : queryFields)
            {
                if (entry.getClass() == String.class)
                {
                    stmt.setString(queryFields.indexOf(entry) + 1, (String) entry);
                }
            }

            if (stmt.executeUpdate() > 1)
            {
                transaction.setNoErrors(false);
                returnValue = false;
            }
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
            returnValue = false;
            transaction.setNoErrors(false);

        }
        finally
        {
            try
            {
                if (stmt != null) stmt.close();
            } catch (SQLException e)
            {
                System.out.println(e.getMessage());
                returnValue = false;
                transaction.setNoErrors(false);
            }
        }
        return returnValue;
    }

    protected byte[] convertSerializableToByteArray(Serializable obj)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try
        {
            ObjectOutputStream oos = new ObjectOutputStream(baos);

            oos.writeObject(obj);

            oos.flush();
            oos.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }


        return baos.toByteArray();
    }

    protected Serializable convertByteArrayToSerializable(byte[] byteArray)
    {
        Serializable obj = null;
        try
        {
            ByteArrayInputStream fis = new ByteArrayInputStream(byteArray);
            ObjectInputStream ois = new ObjectInputStream(fis);
            obj = (Serializable) ois.readObject();
            ois.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return obj;
    }




}
