package main;

import java.sql.* ;
import java.util.ArrayList;
import java.util.HashMap;

import com.mysql.jdbc.Driver;

import java.util.Map;
import java.awt.EventQueue;
import java.io.*;

import javax.swing.JTextArea;

public class dbconnect {

	private String nick;
	private int points;
    public Map<Integer, String> map = new HashMap<Integer, String>();
    
	
	public dbconnect(String nickName, int score){
		nick = nickName;
		points = score;
		ex();
	}
	
	public Map getMap(){
		return this.map;
	}
	
	private void ex(){
		Connection c = null ;
		PreparedStatement pstmt = null;
		try {
	        DriverManager.registerDriver(new com.mysql.jdbc.Driver());

	        c = DriverManager.getConnection(
	             "jdbc:mysql://localhost/birdgame", "root", "274898");

	        Statement s = c.createStatement() ;
	        ResultSet rs = null;
	        String insert = "INSERT INTO TABLE1 (SCORE,NICKNAME) VALUES (?,?)";
	        pstmt = c.prepareStatement(insert);
	        pstmt.setInt(1,this.points);
	        pstmt.setString(2, nick);
	        pstmt.executeUpdate();
	        pstmt.close();
	        rs = s.executeQuery("SELECT * FROM table1 ORDER BY score DESC");

                          
	        String str=new String();
	        Integer score = new Integer(0);
	        int i= 0;

	        while( rs.next() && (i<5) )
	        {	
	           str=rs.getString("Nickname");
	           score = rs.getInt("score");
	           map.put(new Integer(score), str);
	           i++;
	        }
	        rs.close(); rs=null;

	        c.close();
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    Results frame2 = new Results(map);
	                    frame2.setTitle("Results");
	                    frame2.setVisible(true);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    } 
		
		
		
	    catch (SQLException ex)
	    {    System.out.println("SQLException caught");
	         System.out.println("---");
	         while ( ex != null ){
	             System.out.println("Message   : " + ex.getMessage());
	             System.out.println("SQLState  : " + ex.getSQLState());
	             System.out.println("ErrorCode : " + ex.getErrorCode());
	             System.out.println("---");
	             ex = ex.getNextException();
	         }
	    }
	    catch (Exception ex)
	    {    System.out.println("Other Error in Main.");    } 
	}

	
	
}
