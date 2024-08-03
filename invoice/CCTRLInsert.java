package systech.pp.invoice;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static systech.pp.ulib.UConvert.toInt;
import static systech.pp.ulib.UConvert.toDouble;

import systech.pp.ulib.UDatabase;
import systech.pp.ulib.URun;

import java.io.IOException;
import java.sql.Connection;

@WebServlet("/cnewitem")
public class CCTRLInsert extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{ doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String tname=request.getParameter("tbxtname");
		int qty=toInt(request.getParameter("tbxqty"));
		double price=toDouble(request.getParameter("tbxprice"));
		
		AModelBillTbl mbt=new AModelBillTbl();
		mbt.setTname(tname);
		mbt.setQty(qty);
		mbt.setPrice(price);
		
		UDatabase udb=new UDatabase();
		
		udb.setProtocol("jdbc:mysql");
		udb.setServerIP("127.0.0.1");
		udb.setServerPort(3306);
		udb.setName("cmrdbs");
		udb.setUser("root");
		udb.setPassword("");
		
		Connection cn=udb.getConnection();
		
		URun ur=new URun();
		int r=ur.uDMLQuery("insert", mbt,cn);
		
		if(r>0) 
		{
			System.out.println("1 row inserted");
		}
		else
		{
			System.out.println("0 row inserted");
		}
		
		request.getRequestDispatcher("clistitem").forward(request,response);	
	}
}
/*
 import java.io.PrintWriter;
 response.setContentType("text/html");
 
 
 PrintWriter pw=response.getWriter();
 pw.println("<br>Things name:"+tname);
 pw.println("<br>Things quantity:"+qty);
 pw.println("<br>Things price:"+price);
 pw.close();
*/