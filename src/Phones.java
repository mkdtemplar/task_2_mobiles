import java.sql.*;
import java.util.Scanner;

public class Phones
{

    public static void main ( String[] args ) throws Exception
    {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");


        String url="jdbc:ucanaccess://src\\MOBILES.mdb";

        Connection con = DriverManager.getConnection(url);
        Scanner in = new Scanner ( System.in );

        String averageGrade = "SELECT MODEL, AVG(GRADE) AS AVERAGE FROM PHONE, GRADE WHERE ID_PHONE=P_ID_PHONE GROUP BY MODEL";
        Statement statement1 = con.createStatement ();
        ResultSet resultSet = statement1.executeQuery (averageGrade);

        while ( resultSet.next () )
        {
            String Model = resultSet.getString("MODEL");
            double average = resultSet.getDouble ( "AVERAGE" );
            System.out.println (Model + " " + average);
        }

        int phoneId;
        int grade;

        System.out.print("Enter phone id: ");
        phoneId = in.nextInt();
        System.out.print ( "Grade of the phone: " );
        grade = in.nextInt();




        String insertSQL = "INSERT INTO GRADE (P_ID_PHONE, GRADE) VALUES ( ?, ? )";
        PreparedStatement preparedStatement1 = con.prepareStatement (insertSQL);
        preparedStatement1.setInt ( 1, phoneId );
        preparedStatement1.setInt ( 2, grade );


        int update = preparedStatement1.executeUpdate ();

        if (update > 0)
        {
            System.out.println("Insert successful");
        }
        else {
            System.out.println("Insert not successful");
        }

        String sql = "SELECT * FROM PHONE WHERE PRICE > 170";

        Statement statement = con.createStatement();

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next())
        {
            int id = rs.getInt ("ID_PHONE");
            String model = rs.getString ( "MODEL" );
            String type = rs.getString ( "TYPE" );
            int price = rs.getInt ("PRICE");

            System.out.println (id + "," + model + "," + type + "," + price);
        }

    }
}
