package cs430.a6;

import cs430.a6.entity.*;

import java.sql.*;
import java.text.DecimalFormat;
import java.util.List;


/**
 * Implement the methods as per the instructions given in the write up
 * and in the comments below.
 * Please do not change the signatures of these methods.
 * For testing, a tester class will be implemented which will create
 * and instance of this class (using the default constructor) and invoke
 * the methods.
 */
public class Stub {
    private Connection conn = null;
    private Statement statement = null;

    private CallableStatement callableStatement = null;

    private ResultSet resultSet = null;

    private String query;
    // Do not change or remove the default constructor.
    public Stub() {

    }
    
    // Write initializing logic if there is any. This method will be
    // called immediately following the constructor call.
    public void init(){
        String db = "jdbc:postgresql://localhost:5432/postgres";
        // Database name to access
        String dbUsername = "postgres";
        String dbPassword = "Mony2009?";
//        String db = "jdbc:postgresql://localhost:5432/benzo79";
//        // Database name to access
//        String dbUsername = "benzo79";
//        String dbPassword = "831047876";

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("DRIVER NOT FOUND");
            e.printStackTrace();
        }
        try {
            conn = DriverManager.getConnection(db, dbUsername, dbPassword);
            System.out.println("CONNECTION SUCCESFULL");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    // Clear out any resources such as open connections.
    // This method will be called at the end of the program.
    public void close(){
        try {
            if (statement != null) {
                statement.close();
            }

            if (resultSet != null) {
                resultSet.close();
            }

            if (conn != null) {
                conn.close();
            }

        } catch (SQLException ex) {}

}

    /**
     * Add a new artist to the database.
     * @param artist Artist object containing attributes of the artist
     * @throws SQLException
     */
    public void addArtist(Artist artist) throws SQLException {
        query = "INSERT INTO artist (a_name, birthplace, age, style) VALUES('"+ artist.getName()
                +"', '"+ artist.getBirthplace()+
                "', "+ Integer.toString(artist.getAge())  +
                ", '"+ artist.getStyle() +"');";

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    /**
     * 1. Add a new artwork.
     * 2. And create a group if it doesn't exist.
     * 3. Then add an entry to the classify table containing artwork title
     * and the group name.
     * All these 3 steps needs to be done using a stored procedure at the database.
     * From the client program, just invoke this stored procedure.
     * @param artwork   Artwork object with the attributes of the artwork.
     * @param group the name of the group
     * @throws SQLException
     */
    public void addArtwork(Artwork artwork, String group) throws SQLException {

        try{

       callableStatement = conn.prepareCall("call public.\"addArtwork\"(?, ?, ?, ?, ?, ?);");
        callableStatement.setString(1, artwork.getTitle());
        callableStatement.setInt(2, artwork.getYear());
        callableStatement.setString(3, artwork.getType());
        callableStatement.setFloat(4, artwork.getPrice());
        callableStatement.setString(5, artwork.getArtistName());
        callableStatement.setString(6,group);
        callableStatement.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


//        call public."addArtwork"('Good', 2023, 'Art', 3.50, 'Ali', 'Group1');

        
    }

    /**
     * Add a new customer
     * @param customer customer object with the attributes of the customer
     * @throws SQLException
     */
    public void addCustomer(Customer customer) throws SQLException {
//        customer(cust id:string, c name:string, address:string, amount:real)
        query = "INSERT INTO customer (cust_id, c_name, address, amount) VALUES('"+ customer.getCustomerId()
                +"', '"+ customer.getCustomerName()+
                "', '"+ customer.getAddress()+
                "', "+ customer.getAmount() +");";

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

    /**
     * Add a record to the like_group table with the customer id and the group name.
     * A part of this exercise is to write a trigger at the database for the insert operation of the like_group.
     * This trigger will invoke a function which does the following.
     * 1. It queries the classify table for the artworks with the same group name.
     * 2. Then it queries for the artist names of these artworks.
     * 3. Finally it should add records to the like_artist table for those artworks with the same customer id
     * as the one which is passed as an argument to this function. This entry is added only if there is no such
     * entry in the like_artist table which was added previously.
     * @param customerId Customer Id
     * @param likeGroup Group Name
     * @throws SQLException
     */
    public void addLikeGroup(String customerId, String likeGroup) throws SQLException {

        query = "INSERT INTO like_group (cust_id, g_name) VALUES('"+ customerId +
                "', '"+ likeGroup +"');";

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        
    }

    /**
     * Get the list of artists which are currently available in the database.
     * @return An array of Artist objects where each object corresponds to a record in the artist table.
     * @throws SQLException
     */
    public Artist[] getArtists() throws SQLException {

        Artist[] artists;
        query = "SELECT a_name, birthplace, age, style FROM artist;";

        try {
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                             ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        int size =0;
        if (resultSet != null)
        {
            resultSet.last();    // moves cursor to the last row
            size = resultSet.getRow(); // get row id
        }

        artists = new Artist[size];
        int index = 0;
        resultSet.beforeFirst();
        while (resultSet.next()) {

            artists[index] = new Artist(resultSet.getString("a_name"),
                    resultSet.getString("birthplace"),
                    resultSet.getInt("age"),
                    resultSet.getString("style")
                    );

            index++;

        }

        return artists;
    }

    /**
     * Get the list of artworks available in the database.
     * @return An array of Artwork objects where each object corresponds to a record in the artwork table.
     * @throws SQLException
     */
    public Artwork[] getArtworks() throws SQLException {
        Artwork[] artworks;
        query = "SELECT title, year, type, price,  a_name FROM artwork;";

        try {
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        int size =0;
        if (resultSet != null)
        {
            resultSet.last();    // moves cursor to the last row
            size = resultSet.getRow(); // get row id
        }

        artworks = new Artwork[size];
        int index = 0;
        resultSet.beforeFirst();
        while (resultSet.next()) {

            artworks[index] = new Artwork(resultSet.getString("title"),
                    resultSet.getInt ("year"),
                    resultSet.getString("type"),
                    resultSet.getFloat ("price"),
                    resultSet.getString("a_name")
            );

            index++;

        }

        return artworks;
    }

    /**
     * Get the list of groups.
     * @return An array of Group objects, each object corresponds to a record in the a_groups table.
     * @throws SQLException
     */
    public Group[] getGroups() throws SQLException {
        Group[] group;
        query = "SELECT g_name FROM a_group;";

        try {
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        int size =0;
        if (resultSet != null)
        {
            resultSet.last();    // moves cursor to the last row
            size = resultSet.getRow(); // get row id
        }

        group = new Group[size];
        int index = 0;
        resultSet.beforeFirst();
        while (resultSet.next()) {

            group[index] = new Group( resultSet.getString ("g_name"));

            index++;

        }

        return group;
    }

    /**
     * Returns List of entries in the classify table.
     * @return an array of classify objects with each objects correspond to a record in the classify table.
     * @throws SQLException
     */
    public Classify[] getClassifyEntries() throws SQLException {
        Classify[] classify;
        query = "SELECT title, g_name FROM classify;";

        try {
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        int size =0;
        if (resultSet != null)
        {
            resultSet.last();    // moves cursor to the last row
            size = resultSet.getRow(); // get row id
        }

        classify = new Classify[size];
        int index = 0;
        resultSet.beforeFirst();
        while (resultSet.next()) {

            classify[index] = new Classify(resultSet.getString("title"),
                    resultSet.getString ("g_name"));

            index++;

        }

        return classify;
    }

    /**
     * Returns the list of like_group table records.
     * @return Array of LikeGroup objects. An object in the array represents on record in the like_group table.
     * @throws SQLException
     */
    public LikeGroup[] getLikeGroupEntries() throws SQLException{
        LikeGroup[] likeGroup;
        query = "SELECT cust_id, g_name FROM like_group;";

        try {
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        int size =0;
        if (resultSet != null)
        {
            resultSet.last();    // moves cursor to the last row
            size = resultSet.getRow(); // get row id
        }

        likeGroup = new LikeGroup[size];
        int index = 0;
        resultSet.beforeFirst();
        while (resultSet.next()) {

            likeGroup[index] = new LikeGroup(resultSet.getString("cust_id"),
                    resultSet.getString ("g_name"));

            index++;

        }


        return likeGroup;
    }

    /**
     * Returns a list of like_artist records.
     * @return An array of LikeArtist objects. Each object represents one record in like_artist table.
     * @throws SQLException
     */
    public LikeArtist[] getLikeArtistEntries() throws SQLException{
        LikeArtist[] likeArtists;
        query = "SELECT cust_id, a_name FROM like_artist;";

        try {
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery(query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        int size =0;
        if (resultSet != null)
        {
            resultSet.last();    // moves cursor to the last row
            size = resultSet.getRow(); // get row id
        }

        likeArtists = new LikeArtist[size];
        int index = 0;
        resultSet.beforeFirst();
        while (resultSet.next()) {

            likeArtists[index] = new LikeArtist(resultSet.getString("cust_id"),
                    resultSet.getString ("a_name"));

            index++;

        }


        return likeArtists;
    }

    /**
     * Updates the 'style' field of an artist record with the given artist name.
     * @param artistName artist name
     * @param newStyle new value of the style field.
     * @throws SQLException
     */
    public void updateArtistStyle(String artistName, String newStyle) throws SQLException {

        query = "UPDATE artist SET style = '" + newStyle + "'"
                + " WHERE a_name = '" + artistName + "';";

        try {
            statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        
    }


    public static void main(String args[]) {
        Stub stub = new Stub();
        stub.init();

//        try {
//            stub.updateArtistStyle("Ali", "Dark");
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        // call stored procedure
//        Artwork artwork = new Artwork("Good3", 2022, "Art3", 2.75f, "Ben");
//        try {
//            stub.addArtwork(artwork, "Group3");
//        } catch (SQLException e) {
//           System.out.println(e.getMessage());
//        }



        // get data from database
//        try {
//             Artist[] result = stub.getArtists();
//             if (result.length == 0){
//                 System.out.println("Artist table is empty");
//             }else {
//                 for (int i = 0; i < result.length; i++)
//                     System.out.println(result[i].toString());
//             }
//        } catch (SQLException e) {
//           System.out.println(e.getMessage());
//        }
//
//
//        try {
//            Artwork[] result = stub.getArtworks();
//            if (result.length == 0){
//                System.out.println("Artwork table is empty");
//            }else {
//                for (int i = 0; i < result.length; i++)
//                    System.out.println(result[i].toString());
//            }
//        } catch (SQLException e) {
//           System.out.println(e.getMessage());
//        }



//        try {
//            Group[] result = stub.getGroups();
//            if (result.length == 0){
//                System.out.println("Group table is empty");
//            }else {
//                for (int i = 0; i < result.length; i++)
//                    System.out.println(result[i].toString());
//            }
//        } catch (SQLException e) {
//           System.out.println(e.getMessage());
//        }

//        try {
//            Classify[] result = stub.getClassifyEntries();
//            if (result.length == 0){
//                System.out.println("Classify table is empty");
//            }else {
//                for (int i = 0; i < result.length; i++)
//                    System.out.println(result[i].toString());
//            }
//        } catch (SQLException e) {
//           System.out.println(e.getMessage());
//        }

//        try {
//            LikeGroup[] result = stub.getLikeGroupEntries();
//            if (result.length == 0){
//                System.out.println("LikeGroup table is empty");
//            }else {
//                for (int i = 0; i < result.length; i++)
//                    System.out.println(result[i].toString());
//            }
//        } catch (SQLException e) {
//           System.out.println(e.getMessage());
//        }


//        try {
//            LikeArtist[] result = stub.getLikeArtistEntries();
//            if (result.length == 0){
//                System.out.println("LikeArtist table is empty");
//            }else {
//                for (int i = 0; i < result.length; i++)
//                    System.out.println(result[i].toString());
//            }
//        } catch (SQLException e) {
//           System.out.println(e.getMessage());
//        }



//        Artist artist1 = new Artist("Ben","USA",37,"SRR");
//        Artist artist2 = new Artist("Ali","LIB",30,"FUN");
//        try {
//            stub.addArtist(artist1);
//        } catch (SQLException e) {
//           System.out.println(e.getMessage());
//        }

//        Customer customer = new Customer("C123", "Dave", "1234 S Plum St", 25.70f);
//        try {
//            stub.addCustomer(customer);
//        } catch (SQLException e) {
//           System.out.println(e.getMessage());
//        }

//        try {
//            stub.addLikeGroup("C123", "Group3");
//        } catch (SQLException e) {
//           System.out.println(e.getMessage());
//        }



    }


    }
