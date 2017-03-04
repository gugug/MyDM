package com.java.dm;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Set;

public class Test {

	private HashMap<Integer, News> newsMap = new HashMap<Integer, News>();
	private HashMap<Integer, String> commentMap = new HashMap<Integer, String>();

	public void getNewsByMysql() {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DBHelper.getConn();
			System.out.println(" 实例化Statement对...");
			stmt = conn.createStatement();
			String newsSql;
			String commentSql;
			newsSql = "SELECT news_id, news_website_type, news_title, news_content FROM news WHERE news_id >= 200000";
			int start = 8678475;
			int pageSize = 1000;
			int numrows =  13224221;//SELECT COUNT( * )  FROM news_comment
			int pages = (int)(numrows / pageSize);
			if (numrows % pageSize > 0){
				pages++;
			}
			

//			while(pages>0){
//				System.out.println(start+", "+pages);
				commentSql = "SELECT news_id, news_comment_content FROM news_comment  WHERE news_id =108213";
				getComments(stmt, commentSql);
//				start+=pageSize;
//				pages--; 
//			}

//			getNews(stmt, newsSql);

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
				se2.printStackTrace();
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}
		System.out.println("Goodbye!");

	}

	private void getComments(Statement stmt, String sql1) throws SQLException {
		WriteByTXT wt = new WriteByTXT();
		ResultSet rs1 = stmt.executeQuery(sql1);
		while (rs1.next()) {
			int id = rs1.getInt("news_id");
			String comment = rs1.getString("news_comment_content");
			System.out.print("ID: " + id);
			System.out.println(", 评论: " + comment);
//			if (commentMap.containsKey(id)) {
//				comment = commentMap.get(id) + "\n" + comment;
//				commentMap.put(id, comment);
				
//				commentMap.put(id, "1“");
//				wt.writeTxt(comment, "./DMData/Comments1", id
//						+ ".txt");
//			} else {
//				commentMap.put(id, comment);
//				commentMap.put(id, "1“");
				wt.writeTxt(comment+"\n", "./DMData/Comments2", id
						+ ".txt");
			
//			}
		}
		rs1.close();
	}

	private void getNews(Statement stmt, String sql) throws SQLException {
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("news_id");
			String type = rs.getString("news_website_type");
			String title = rs.getString("news_title");
			String content = rs.getString("news_content");
			System.out.println("ID: " + id);
			System.out.println("类型: " + type);
			System.out.println("标题: " + title);
			System.out.println("内容:" + content);
			System.out.println();
			newsMap.put(id, new News(id, type, title, content));
		}
		rs.close();
	}

	private static void writeComments(Test  getData, WriteByTXT wt) {
		System.out.println("正在写入comments...");
		Set<Integer> set1 = getData.commentMap.keySet();
		for (Integer sb1 : set1) {
			wt.writeTxt(getData.commentMap.get(sb1), "./DMData/Comments", sb1
					+ ".txt");
		}
		System.out.println("comments ok");
	}

	private static void writeNews(Test getData, WriteByTXT wt) {
		System.out.println("正在写入news...");
		Set<Integer> set = getData.newsMap.keySet();
		for (Integer sb : set) {
			News n = getData.newsMap.get(sb);
			wt.writeTxt(n.getTitle() + "\n" + n.getContent(), "./DMData/News/"
					+ n.getType(), sb + ".txt");
		}
		System.out.println("news ok");
	}

	public static void main(String[] args) {
		final long start = System.currentTimeMillis();
		Test getData = new Test();
		getData.getNewsByMysql();
//		WriteByTXT wt = new WriteByTXT();
//		writeNews(getData, wt);
//		writeComments(getData, wt);
		final long end = System.currentTimeMillis();
		System.out.println("用时毫秒： " + (end - start));

	}
}

