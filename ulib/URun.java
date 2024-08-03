package systech.pp.ulib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.ResultSetMetaData;


import systech.pp.invoice.AModelBillTbl;

public class URun {
	
	public int uDMLQuery(String statementType,
			AModelBillTbl mbt,Connection cn) {
		try {
			String qry=null;
			PreparedStatement ps=null;
			
			if(statementType.equalsIgnoreCase("insert")) {
				qry="insert into billtbl (tname,qty,price)values(?,?,?)";
				
				ps=cn.prepareStatement(qry);
				ps.setString(1, mbt.getTname());
				ps.setInt(2, mbt.getQty());
				ps.setDouble(3,mbt.getPrice());	
			}else if (statementType.equalsIgnoreCase("update")) {
				qry="update billtbl set tname=?,qty=?,price=?where sno=?";
				
				ps=cn.prepareStatement(qry);
				ps.setString(1,mbt.getTname());
				ps.setInt(2,mbt.getQty());
				ps.setDouble(3,mbt.getPrice());
				ps.setDouble(4,mbt.getSno());
			}else if(statementType.equalsIgnoreCase("delete")) {
				qry="delete from billtbl where sno=?";
				
				ps=cn.prepareStatement(qry);
				ps.setDouble(1, mbt.getSno());
			}else {
				return -1;
			}
			//'executeUpdate' method develop DML statements
			// like 'insert','update','delete'
			int ack=ps.executeUpdate();
			ps.close();
			cn.close();
			return ack;
		}catch(Exception e) {
			System.err.println("Err.:"+e.getMessage());
			return -1;
		}
	}
	public List<String>uGetColumnsName(Connection cn){
		try {
			Statement st=cn.createStatement();
			
			//'ResultSet' class handle rows or records
			//'executeQuery' method develop DQL statement like 'select'
			ResultSet rs=st.executeQuery("select * from billtbl");
			//'ResultSetMetaData' class handle columns or fields
			ResultSetMetaData rsmd=rs.getMetaData();
			
			List<String>Lst=new ArrayList<>();
			
			for(int i=1;i<=rsmd.getColumnCount(); i++)
			{
				Lst.add(rsmd.getColumnName(i));
			}
			
			rs.close();
			st.close();
			cn.close();
			
			return Lst;	
		}catch(Exception e) {
			System.err.println("Err.:"+e.getMessage());
		}
		return null;
	}
	public List<AModelBillTbl>uDQLQuery(Connection cn){
		try {
			Statement st=cn.createStatement();
			
			//'ResultSet' class handle rows or records
			// 'executeQuery' method develop DQL statement like 'select'
			ResultSet rs=st.executeQuery("select * from billtbl");
			
			List<AModelBillTbl>Lst=new ArrayList<>();
			
			while(rs.next()) {
				AModelBillTbl mbt=new AModelBillTbl();
				
				mbt.setSno(rs.getInt("sno"));
				mbt.setTname(rs.getString("tname"));
				mbt.setPrice(rs.getDouble("price"));
				mbt.setQty(rs.getInt("qty"));
				
				
				/*
				 * mbt.setTprice(rs.getDouble("tprice"));mbt.setDip(rs.getDouble("dip"));
				 * mbt.setDia(rs.getDouble("dia"));mbt.setCgst(rs.getDouble("cgst"));
				 * mbt.setSgst(rs.getDouble("sgst"));mbt.setDeducts(rs.h=getDouble("deducts"));
				 * mbt.setNprice(rs.getDouble("nprice"));
				 */
				Lst.add(mbt);
			}
			rs.close();
			st.close();
			cn.close();
			
			
			return Lst;
		}catch(Exception e) {
			System.err.println("Err.:"+e.getMessage());
		}
		return null;
		
	}
}
