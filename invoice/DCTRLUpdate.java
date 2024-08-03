package systech.pp.invoice;

import java.io.IOException;
import java.sql.Connection;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static systech.pp.ulib.UConvert.toInt;
import static systech.pp.ulib.UConvert.toDouble;

import systech.pp.ulib.UDatabase;
import systech.pp.ulib.URun;

@WebServlet("/cedititem")
public class DCTRLUpdate extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException 
	{
		int sno=toInt(request.getParameter("hdnsno"));
		String tname=request.getParameter("tbxtname");
		int qty=toInt(request.getParameter("tbxqty"));
		double price=toDouble(request.getParameter("tbxprice"));
		
		AModelBillTbl mbt=new AModelBillTbl();
		mbt.setSno(sno);
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
		int r=ur.uDMLQuery("update", mbt, cn);
		
		if(r>0) {
			System.out.println("1 row updated");
		}else
		{
			System.out.println("0 row updated");
		}
		
		//requset.getRequestDispatcher("clistitem").forward(request,response);
		response.sendRedirect("clistitem");
		
	}

}
