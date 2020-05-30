package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Blog;
import utility.ConnectionManager;

public class BlogDaoImpl implements BlogDaoInterface
{
	public void insertBlog(Blog blog) throws Exception
	{
		int id = blog.getBlogId();
		String title = blog.getBlogTitle();
		String desc = blog.getBlogDescription();
		LocalDate date = blog.getPostedOn();
		
		System.out.println("Enter details to insert into the blog:");
		//CREATE TABLE BLOG (ID NUMBER GENERATED BY DEFAULT ON NULL AS IDENTITY,TITLE VARCHAR2(20) NOT NULL, DESCRIPTION VARCHAR2(50), DATE1 DATE);
		//insert into blog(TITLE, DESCRIPTION, DATE1) values('python','Snake',TO_DATE ('30-05-20'));
		String sql = "INSERT INTO BLOG(TITLE, DESCRIPTION, DATE1) VALUES (?,?,?)";
		System.out.println("TABLE BLOG EXISTS");
		PreparedStatement st = ConnectionManager.getConnection().prepareStatement(sql);

		st.setString(1, title);
		st.setString(2, desc);
		st.setDate(3, java.sql.Date.valueOf(date));
		st.executeUpdate();
		ConnectionManager.getConnection().close();
	}

	public List<Blog> selectAllBlogs() throws SQLException, Exception 
	{	
		System.out.println("your Blogs are listed below:");
		Statement st = ConnectionManager.getConnection().createStatement();
		ResultSet rs = st.executeQuery("SELECT * from BLOG");
		List<Blog> list = new ArrayList<Blog>();
		
		while(rs.next())
		{
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String desc = rs.getString("DESCRIPTION");
			Date date = rs.getDate("DATE1"); 
			
			Blog blog = new Blog();
			blog.setBlogId(id);
			blog.setBlogTitle(title);
			blog.setBlogDescription(desc);
			
			LocalDate date1 = date.toLocalDate();
			blog.setPostedOn(date1);
			list.add(blog);
			ConnectionManager.getConnection().close();
		}
		return list;
	}


	public Blog selectBlog(int blogid) throws SQLException, Exception 
	{
		Statement st = ConnectionManager.getConnection().createStatement();
		ResultSet rs = st.executeQuery("SELECT TITLE, DESCRIPTION, DATE1 FROM BLOG WHERE ID =" + blogid +" ");
		
		while(rs.next())
		{
			int id = rs.getInt("ID");
			String title = rs.getString("TITLE");
			String desc = rs.getString("DESCRIPTION");
			Date date = rs.getDate("DATE1"); 
			
			Blog blog = new Blog();
			blog.setBlogId(id);
			blog.setBlogTitle(title);
			blog.setBlogDescription(desc);		
		
			return blog;
		}
		return null;
	}
	
	public boolean deleteBlog(int id) throws Exception 
	{
		boolean temp = false;
		Statement st = ConnectionManager.getConnection().createStatement();
		int executeUpdate = st.executeUpdate("DELETE FROM BLOG WHERE ID =" + id +" ");
		if(executeUpdate > 0)
			temp = true;
		return temp;
	}

	public boolean updateBlog(Blog blog) throws Exception 
	{
		int update_id = blog.getBlogId();
		String title = blog.getBlogTitle();
		System.out.println(update_id + " " + title );
		
		boolean temp = false;
		String sql = "UPDATE BLOG SET TITLE = "+"'"+blog.getBlogTitle()+"'"+"WHERE ID ="+blog.getBlogId();
		Statement st = ConnectionManager.getConnection().createStatement();
		int executeUpdate = st.executeUpdate(sql);
		if(executeUpdate > 0)
			temp = true;
		return temp;
	}
}