package _00.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateYearTime {
//	public static void main(String[] args) throws ParseException {
//		String year = "2019";
	public Date getBeginOfTheYear(String year) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
		Date begin=null;
		try {
			begin = sdf.parse(year+"-01-01");
//			System.out.println(begin);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return begin;
		
	}
	
	
	public Date getEndOfTheYear(String year) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD");
		int newYear1 = Integer.valueOf(year);
		String newYear2 = String.valueOf((newYear1+1));
		Date last = null;
		try {
			last = sdf.parse(newYear2+"-01-01");
//			System.out.println(last);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return last;
	}
}
