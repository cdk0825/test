
import static java.lang.System.out;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author work
 */
public class TestDay {
	
	public static void main(String[] args){
		String bgndate = "20060315"; 
		String enddate = "20060318"; 

		Calendar calendar = Calendar.getInstance(); 
		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyyMMdd");

		calendar.set(Calendar.YEAR,Integer.parseInt(bgndate.substring(0,4))); 
		calendar.set(Calendar.MONTH,Integer.parseInt(bgndate.substring(4,6))-1); 
		calendar.set(Calendar.DATE,1);

		calendar.add(Calendar.MONTH,1); 
		Date sDate = calendar.getTime();

		String sSpot_date = simpleDate.format(sDate);


		calendar.set(Calendar.YEAR,Integer.parseInt(enddate.substring(0,4))); 
		calendar.set(Calendar.MONTH,Integer.parseInt(enddate.substring(4,6))-1); 
		calendar.set(Calendar.DATE,1);

		calendar.add(Calendar.MONTH,2);
		calendar.add(Calendar.DATE,-1); 
		Date sDate2 = calendar.getTime();

		String sSpot_date2 = simpleDate.format(sDate2);

		out.println(sSpot_date); 
		out.println(sSpot_date2);
	}
}
