package No3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 题目要求：
 * 0. 在个人仓库下，创建分支yourname_ex3
 * 1. 编写代码完成以下功能：
 * 		a. 从access.log中读入数据，获取IP和Time
 * 		b. 输出在时间区间[beginTime, endTime]内的IP和Time，以tab分割
 * 2. 提交代码到分支下，创建pull request，与主仓库的master分支对比
 */
public class FilterByTime {
	
	public static void main(String[] args) throws ParseException, IOException{
		
		SimpleDateFormat regularFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		Date beginDate = regularFormat.parse("2015-12-31 18:00:00");
		Date endDate = regularFormat.parse("2015-12-31 19:00:00");
		//正则表达式提取出ip和时间
		String pattern = "(\\d+.\\d+.\\d+.\\d+) [^ ]* [^ ]* \\[(\\d+\\/[JFMAONSD][a-z]{2}\\/\\d{4}:\\d{2}:\\d{2}:\\d{2}) .*";
		Pattern r = Pattern.compile(pattern);
        //从本地文件读入sccess.log，使用字符流读取
		FileReader fileRead = new FileReader("F:/access.log");
		BufferedReader br = new BufferedReader(fileRead);
		//读出一行寄存处
		String str=null;
		
		while((str=br.readLine())!=null){
			Matcher m = r.matcher(str);
			String ip = null;
			String tm = null;
			if(m.find()){
				ip = m.group(1);
				tm = m.group(2);
			}
			//调用getTimes方法“31/Dec/2015:18:59:26”格式的提起转换为“yyyy-MM-dd HH:mm:ss”格式并且得到时间戳
			long time=getTimes(tm);
			//用时间戳比较获取时间区间符合的ip和时间
			if(time>=beginDate.getTime() && time<=endDate.getTime()){
				System.out.println(ip+"\t"+tm);
			}
		}
		br.close();
		fileRead.close();
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
