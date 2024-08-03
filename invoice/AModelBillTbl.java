
package systech.pp.invoice;

public class AModelBillTbl {
	private int sno=0;
	
	//inputs
	private String tname=null;
	private double price=0.0;
	private int qty=0;
	
	//outputs
	//dip:discount in percentage
	//dia:discount in amount
	private double tprice=0.0,dip=0.0,dia=0.0;
	private double cgst=0.0,sgst=0.0,deducts=0.0,nprice=0.0;
	
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
		compute();
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		
		this.qty = qty;
		compute();
	}
	public double getTprice() {
		return tprice;
	}
	
	public double getDip() {
		return dip;
	}
	
	public double getDia() {
		return dia;
	}
	
	public double getCgst() {
		return cgst;
	}
	
	public double getSgst() {
		return sgst;
	}
	public double getDeducts() {
		return deducts;
	}
	public double getNprice() {
		return nprice;
	}
	
	private void compute()
	{
		tprice=price*qty;
		
		if(tprice>10000) 
		{
			dip=10.0;
		}
		else if(tprice > 7500)
		{
			dip=7.5;
		}
		else if(tprice > 5000)
		{
			dip=5.0;
		}
		else if(tprice > 2500) 
		{
			dip=2.5;
		}
		else if(tprice > 1000) 
		{
			dip=1.0;
		}
		dia=tprice*(dip/100);
		
		cgst=tprice*(9.0/100);
		sgst=cgst;
		
		deducts=dia+cgst+sgst;
		nprice=tprice-deducts;
	}
}
