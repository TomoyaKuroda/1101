package Models;

import java.sql.*;
import java.util.ArrayList;

public class DBConnect {
    private static String user = "student";
    private static String password = "student";

    public static ArrayList<String> getPhoneManufacturers() throws SQLException {
        ArrayList<String> manufacturers = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //1. Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phones?useSSL=false",
                    user, password);

            //2. create a Statement object
            statement = conn.createStatement();

            //3.  create the sql query
            resultSet = statement.executeQuery("SELECT * FROM manufacturers ORDER BY manufacturer");

            //4. loop over the results and add it to the ArrayList
            while (resultSet.next())
            {
                manufacturers.add(resultSet.getString("manufacturer"));
            }
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
        finally {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
        return manufacturers;
    }

    public static ArrayList<MobilePhone> getPhones() throws SQLException {
        ArrayList<MobilePhone> phones = new ArrayList<>();
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //1. Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phones?useSSL=false",
                    user, password);

            //2. create a Statement object
            statement = conn.createStatement();

            //3.  create the sql query
            resultSet = statement.executeQuery("SELECT * FROM phones");

            //4. loop over the results, create MobilePhone objects
            //   and add it to the ArrayList
            while (resultSet.next())
            {
                MobilePhone newPhone = new MobilePhone(
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getString("os"),
                        resultSet.getDouble("screenSize"),
                        resultSet.getDouble("memory"),
                        resultSet.getDouble("frontCamRes"),
                        resultSet.getDouble("rearCamRes"),
                        500);
                phones.add(newPhone);
            }
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
        finally {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
        return phones;
    }


    public static String getOSForManufacturer(String manufacturer) throws SQLException {
        String os=null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;

        try{
            //1.  Connect to the DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phones?useSSL=false",
                    user, password);

            //2.  Create a sql statement
            String sql = "SELECT os FROM manufacturers WHERE manufacturer = ?";

            //3.  Create the prepared statement
            ps = conn.prepareStatement(sql);

            //4.  Bind the parameter(s)
            ps.setString(1, manufacturer);

            //5.  get the results
            resultSet = ps.executeQuery();

            //6.  loop over the results
            while(resultSet.next())
            {
                os = resultSet.getString("os");
                System.out.printf("returned from DB: '%s'%n", os);
            }
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
        finally {
            if (conn != null)
                conn.close();

            if (ps != null)
                ps.close();

            if (resultSet != null)
                resultSet.close();
        }
        return os;
    }

    /**
     * This method receives a MobilePhone object and will store it in the DB
     * @param newPhone - must be a valid MobilePhone object
     * @throws SQLException
     */
    public static void insertPhoneIntoDB(MobilePhone newPhone) throws SQLException {
        Connection conn=null;
        PreparedStatement ps = null;

        try{
            //1.  connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/phones?useSSL=false",
                    user, password);

            //2. create a sql statement
            String sql = "INSERT INTO phones (make, model, os, screenSize, memory,frontCamRes," +
                    "rearCamRes) VALUES (?,?,?,?,?,?,?);";

            //3. create the PreparedStatement
            ps = conn.prepareStatement(sql);

            //4.  bind the parameters
            ps.setString(1, newPhone.getMake());
            ps.setString(2, newPhone.getModel());
            ps.setString(3, newPhone.getOs());
            ps.setDouble(4, newPhone.getScreenSize());
            ps.setDouble(5, newPhone.getMemory());
            ps.setDouble(6, newPhone.getFrontCameraRes());
            ps.setDouble(7, newPhone.getRearCameraRes());

            //5. execute the INSERT statement
            ps.executeUpdate();
        }
        catch (SQLException e)
        {
            System.err.println(e);
        }
        finally {
            if (conn != null)
                conn.close();
            if (ps != null)
                ps.close();
        }

    }

}
