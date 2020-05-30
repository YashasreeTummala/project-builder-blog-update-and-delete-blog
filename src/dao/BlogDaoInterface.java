package dao;

import java.sql.SQLException;
import java.util.List;

import model.Blog;

interface BlogDaoInterface
{
	//Stage-4 Part-1 (Create, Read)
	void insertBlog(Blog blog) throws SQLException, Exception;
	List selectAllBlogs() throws SQLException, Exception;
	
	//Stage-4 Part-2 (update, delete)
	Blog selectBlog(int blogid) throws SQLException, Exception;
	boolean deleteBlog(int id) throws SQLException, Exception;
	boolean updateBlog(Blog blog) throws SQLException, Exception;
}