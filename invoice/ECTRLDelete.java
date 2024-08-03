package systech.pp.invoice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import static systech.pp.ulib.UConvert.toInt;

import systech.pp.ulib.UDatabase;
import systech.pp.ulib.URun;

import java.io.IOException;
import java.sql.Connection;
@WebServlet("/cdelitem")
public class ECTRLDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		int sno=toInt(request.getParameter("sno"));
		
		AModelBillTbl mbt=new AModelBillTbl();
		mbt.setSno(sno);
		
		UDatabase udb=new UDatabase();
		
		udb.setProtocol("jdbc:mysql");
		udb.setServerIP("127.0.0.1");
		udb.setServerPort(3306);
		udb.setName("cmrdbs");
		udb.setUser("root");
		udb.setPassword("");
	
	Connection cn=udb.getConnection();
	
	URun ur=new URun();
	int r=ur.uDMLQuery("delete", mbt, cn);
	
	if(r>0) {
		System.out.println("1 row deleted");
	}else {
		System.out.println("0 row deleted");
	}
	
	//request.getRequestDispatchar("clistitem").forward(request,response);
	response.sendRedirect("clistitem");

}

}