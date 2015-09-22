import java.sql.*;
public class examdatabase
{
	public ResultSet select(String table,int value1) throws SQLException,ClassNotFoundException
	{
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		String url="jdbc:mysql://localhost:3306/onlineexam?user=root&password=root";
		Connection con=DriverManager.getConnection(url);
		Statement st=con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		ResultSet rs=st.executeQuery("select * from "+table+" where QuestionNo="+value1);
		return rs;
	}
}