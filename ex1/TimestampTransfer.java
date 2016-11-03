package No1;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/*
 * 题目要求：
 * 0. 在个人仓库下，创建分支yourname_ex1
 * 1. 在个人分支下，修改Answers.md文件，里面填入当输入为'2016-11-11 11:11:11'时，输出的值是多少
 * 2. 对代码进行注释，说明每行代码的作用，把代码提交到个人分支下
 * 3. 创建pull request，与主仓库的master分支对比
 */
public class TimestampTransfer {
	@SuppressWarnings("resource")
	public static void main(String[] args){
		//键盘输入
		Scanner scanner = new Scanner(System.in);
		//定义时间格式
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//定义时间格式
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		while (scanner.hasNext()){
			//键盘数据读入获取
			String line = scanner.nextLine();
			//声明Date时间对象
			Date lineDate = null;
			//时间戳
			long lineTimestamp;
			try {
				//把读入的字符串时间用"yyyy-MM-dd HH:mm:ss"格式转化为Date类型
				lineDate = inputFormat.parse(line);
				//获取Date时间的时间戳
				lineTimestamp = lineDate.getTime();
				//把Date类型时间格式化为"yyyy/MM/dd HH:mm:ss"和时间戳输出
				System.out.println(outputFormat.format(lineDate) + " to " + lineTimestamp);
			} catch (ParseException e) {
				//转换异常输出
				e.printStackTrace();
			}
		}
	}
}
