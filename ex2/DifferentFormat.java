package No2;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

/*
 * 题目要求：
 * 0. 在个人仓库下，创建分支yourname_ex2
 * 1. 修改代码，使程序在输入『31/Dec/2015:00:02:09』的时候，转化为时间戳并输出，把结果填写到README.md当中
 * 2. 和ex1对比，对多出来的代码进行注释
 * 3. 提交代码到分支下，创建pull request，与主仓库的master分支对比
 */
public class DifferentFormat {
	@SuppressWarnings("resource")
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		Locale locale = Locale.US; 
		SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss", locale); //修改格式
		while (scanner.hasNext()){
			String line = scanner.nextLine();
			Date lineDate = null;
			long lineTimestamp;
			try {
				//分割字符串为了把月份的英文缩写提取出来
				String[] strs = line.split("/");
				String num = null;
				//把英文的月份缩写转化为数字月份
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
				//把原时间字符串中的英文月份用数字替换
				String line2 = line.replaceAll(strs[1], num);
				lineDate = inputFormat.parse(line2);
				lineTimestamp = lineDate.getTime();
				System.out.println(lineTimestamp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
