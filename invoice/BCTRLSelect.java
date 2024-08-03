package systech.pp.invoice;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.sql.Connection;
import java.util.List;

import java.io.PrintWriter;

import systech.pp.ulib.UDatabase;
import systech.pp.ulib.URun;

import static systech.pp.ulib.UConvert.toMoney;

@WebServlet("/clistitem")
public class BCTRLSelect extends HttpServlet {
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws 
	ServletException, IOException {
		
		UDatabase udb=new UDatabase() ;
		
		udb.setProtocol("jdbc:mysql");
		udb.setServerIP("127.0.0.1");
		udb.setServerPort(3306);
		udb.setName("cmrdbs");
		udb.setUser("root");
		udb.setPassword("");
		
		Connection cn=udb.getConnection();
		
		URun ur=new URun();
		List<String>Lc=ur.uGetColumnsName(cn);
		
		if(Lc!=null) {
			String qrystr="";
			String htmlstr="<style>";
			htmlstr +="#id{text-align-last:end;";
			htmlstr +="padding: 10px;}";
			htmlstr +="body{background-color:;}";
			htmlstr +="</style>";
			htmlstr +="<body>";
			
			htmlstr +="<table width='100%' border='1'>";
			htmlstr +="<thead>";
			
			for(String colName:Lc) {
				htmlstr +="<th>"+ colName.toUpperCase();
			}
			
			htmlstr +="<th colspan='2'>";
			htmlstr +="<a href='bill03insert.html' title='Add'><b>&#10009;</b></a>";
			htmlstr +="</thead>";
			
			cn=udb.getConnection();
			ur=new URun();
			
			List<AModelBillTbl> Lr=ur.uDQLQuery(cn);
			
			if(Lr !=null) {
				for (AModelBillTbl mbt: Lr) {
					
					qrystr="?sno="+mbt.getSno();
					qrystr+="&tname="+mbt.getTname();
					qrystr+="&price="+mbt.getPrice();
					qrystr+="&qty="+mbt.getQty();
					
					htmlstr +="<tr>";
					htmlstr+="<td id='itd'>"+mbt.getSno();
					htmlstr+="<td style='blue padding:10px;'>"+mbt.getTname();
					htmlstr+="<td id='itd'>"+toMoney(mbt.getPrice());
					htmlstr+="<td id='itd'>"+mbt.getQty();
					htmlstr+="<td id='itd'>"+toMoney(mbt.getTprice());
					htmlstr+="<td id='itd'>"+toMoney(mbt.getDip());
					htmlstr+="<td id='itd'>"+toMoney(mbt.getDia());
					htmlstr+="<td id='itd'>"+toMoney(mbt.getCgst());
					htmlstr+="<td id='itd'>"+toMoney(mbt.getSgst());
					htmlstr+="<td id='itd'>"+toMoney(mbt.getDeducts());
					htmlstr+="<td id='itd'>"+toMoney(mbt.getNprice());
					htmlstr+="<td align='center'>";
					htmlstr+="<a href='bill04update.html"+qrystr+
							"'title='Edit'><b>&#9998;</b></a>";
					htmlstr+="<td align='center'>";
					
					htmlstr+="<a href='#' onclick=\"";
					htmlstr+="if(confirm('Sure,Delete a Record')){"+
					"location.href='cdelitem?sno="+mbt.getSno()+"';"+"}\">";
					htmlstr+="<span title='"+mbt.getSno()+"'><b>&#10008;</b></span>";
					htmlstr+="</a>";
				}
		}
			htmlstr+="</table>";
			htmlstr +="</body>";

			
			response.setContentType("text/html");
			
			PrintWriter pw=response.getWriter();
			pw.println(htmlstr);
			pw.close();	
			
		}
		
	}

}

