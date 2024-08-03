function tbx_onkeyup(e){
	// document.title=e.keyCode+":"+e.charCode;
	
	if(e.keyCode==13){
		uNetPrice();
	}	
}
function uNetPrice(){
	var price=tbxprice.value;
	var qty=tbxqty.value;
	
	price=parseFloat(price);
	price=isNaN(price)?0:price;
	
	qty=parseInt(qty);
	qty=isNaN(qty)?0:qty;
	
	var tprice=price*qty;
	var dip=0;
	
	if(tprice>10000)
	{
		dip=10.0;
	}else if(tprice >7500)
	{
		dip=7.5;
	}else if(tprice >5000)
	{
	  dip=5.0;	
	}else if(tprice >2500)
	{
	  dip=2.5;	
	}else if(tprice>1000)
	{
		dip=1.0;
	}
	var dia=(dip/100)*tprice;
	var cgst=tprice*(9.0/100);
	var sgst=cgst;
	var deducts=dia+cgst+sgst;
	var nprice=tprice-deducts;
	
	tbxprice.value=price.toFixed(2);
	tbxqty.value=qty;
	
	tbxtprice.value=tprice.toFixed(2);
	tbxdip.value=dip;
	tbxdia.value=dia.toFixed(2);
	tbxcgst.value=cgst.toFixed(2);
	tbxsgst.value=sgst.toFixed(2);
	tbxdeducts.value=deducts.toFixed(2);
	tbxnprice.value=nprice.toFixed(2);	
}

 