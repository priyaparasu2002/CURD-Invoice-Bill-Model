package systech.pp.ulib;

public class UConvert {
	public static int toInt(String s) 
	{
		try{
			return Integer.parseInt(s);
		}
		catch(Exception e)
		{
			return  0;
		}
	}
	public static double toDouble(String s) {
		try {
			return Double.parseDouble(s);
		}
		catch(Exception e) {
			return 0.0;
		}
	}
	
	public static String toMoney(double d) {
		try {
			return String.format("%.02f", d);
		}catch(Exception e) {
			return null;
		}
	}

}
