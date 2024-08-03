const urlParams=new URLSearchParams(window.location.search);
var sno=urlParams.get('sno');
var tname=urlParams.get('tname');
var price=urlParams.get('price');
var qty=urlParams.get('qty');

sno=(sno==null)?0:parseInt(sno);
sno=isNaN(sno)?0:sno;
hdnsno.value=sno;

tbxtname.value=tname;
tbxprice.value=price;
tbxqty.value=qty;




 