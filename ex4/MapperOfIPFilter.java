package No4;

package No1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MapperOfIPFilter {
	public static void main(String[] args) throws ParseException, IOException,FileNotFoundException{
		//判断args输入参数是否有值
		if(args.length<1){
			System.out.println("Please input beginTime, endTime");
			System.exit(-1);
		}
		
		String begin_endTime=args[0];
		//把输入的参数用，分割为两个时间
		String [] str = begin_endTime.split(",");
		SimpleDateFormat regularFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date beginDate = regularFormat.parse(str[0]);
		Date endDate = regularFormat.parse(str[1]);
		
		//正则表达式提取出ip和时间
		String pattern = "(\\d+.\\d+.\\d+.\\d+) [^ ]* [^ ]* \\[(\\d+\\/[JFMAONSD][a-z]{2}\\/\\d{4}:\\d{2}:\\d{2}:\\d{2}) .*";
		Pattern r = Pattern.compile(pattern);
		//管道输入
		Scanner scanner = new Scanner(System.in);
		String line=null;
		//存储ip的值
		Set<String> set = new HashSet<>();
		while(scanner.hasNext()){
			line=scanner.nextLine();
			Matcher m = r.matcher(line);
			String ip = null;
			String tm = null;
			if(m.find()){
				ip = m.group(1);
				tm = m.group(2);
			}
			//调用getTimes方法获得当前读入的时间戳
			long time=getTimes(tm);
			//判断是否在给定的时间区间
			if(time>=beginDate.getTime() && time<=endDate.getTime()){
				set.add(ip);
			}
		 }
		//输出ip的去重个数
		System.out.println(set.size());
	}
		
	private static long getTimes(String str) throws ParseException {
		String[] strs = str.split("/");
		String num = null;
		switch (strs[1]) {
		case "Jan":
			num = "01";
			break;
		case "Feb":
			num = "02";
			break;
		case "Mar":
			num = "03";
			break;
		case "Apr":
			num = "04";
			break;
		case "May":
			num = "05";
			break;
		case "Jun":
			num = "06";
			break;
		case "Jul":
			num = "07";
			break;
		case "Aug":
			num = "08";
			break;
		case "Sep":
			num = "09";
			break;
		case "Oct":
			num = "10";
			break;
		case "Nov":
			num = "11";
			break;
		case "Dec":
			num = "12";
			break;
		}
		String t2 = str.replaceAll(strs[1], num);
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
		Date date = simpleDateFormat.parse(t2);
		return date.getTime();
	}
}
