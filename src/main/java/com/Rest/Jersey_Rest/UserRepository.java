package com.Rest.Jersey_Rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserRepository {
	
	Connection con ;
	
	public UserRepository() {
		// TODO Auto-generated constructor stub
		
		DatabaseConfig dbconfig = new DatabaseConfig();
		this.con = dbconfig.connectToDatabase();
		
	}
	
	
	public Connection getConnection() {
		return this.con;
	}
	
	
	public void closeConnection() {
	    try {
	        if (con != null) {
	            con.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	

	public boolean createUser(User user) {
		// TODO Auto-generated method stub
		
		Connection con = getConnection();
		
		PreparedStatement ps = null;
		try {
			 ps = con.prepareStatement("insert into Users(id,username,email,password,age) values(?,?,?,?,?)");
			 ps.setInt(1, user.getId());
			 ps.setString(2, user.getUsername());
			 ps.setString(3,user.getEmail());
			 ps.setString(4,user.getPassword());
			 ps.setInt(5, user.getAge());
			 
			 return ps.executeUpdate()>0;
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return false;
	}


	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		List<User> users = new ArrayList<>();
 		PreparedStatement ps = null;
		ResultSet  rs = null;
		try {
			ps = con.prepareStatement("select * from users");
			rs = ps.executeQuery();
			 while(rs.next()) {
				 User user = new User();
				 user.setAge(rs.getInt("age"));
				 user.setEmail(rs.getString("email"));
				 user.setPassword(rs.getString("password"));
				 user.setUsername(rs.getString("username"));
				 user.setId(rs.getInt("id"));
				 System.out.println(user);
				 users.add(user);
				 
			 }
			 return users;
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
		return null;
	}


	public User getUserById(int id) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
 		PreparedStatement ps = null;
		ResultSet  rs = null;
		try {
			ps = con.prepareStatement("select * from users where id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			 if(rs.next()) {
				 User user = new User();
				 user.setAge(rs.getInt("age"));
				 user.setEmail(rs.getString("email"));
				 user.setPassword(rs.getString("password"));
				 user.setUsername(rs.getString("username"));
				 user.setId(rs.getInt("id"));
				 return user;
				 
			 }
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
		return null;
	}


	public void updateUser(User user) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("update users set username=?, password=?, email=?, age=? where id=?;");
			ps.setString(1, user.getUsername());
			ps.setString(2,user.getPassword());
			ps.setString(3,user.getEmail());
			ps.setInt(4,user.getAge());
			ps.setInt(5, user.getId());
			ps.executeUpdate();
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
	}


	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		Connection con = getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement("delete from users where id=?;");
			ps.setInt(1, user.getId());
			ps.executeUpdate();
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}	
	}

}
