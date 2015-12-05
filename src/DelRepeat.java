import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;



/**
 * @author huangjh
 *	去除文件中的重复链接
 */
//TODO 
/*
我
我
他
他
你
工
工    里面的我排重后还有两个
*/
public class DelRepeat {

	public static void main(String[] args) {
		HashSet<String> hashSet = new HashSet<String>();
		
		//1.读取外部文件，存入hashset进行排重
		//2.打印hashset
		String fileName = "D:\\1.txt";
		FileReader fr;
		try {
			fr = new FileReader(fileName );
			BufferedReader bd = new BufferedReader(fr);
			try {
				
				while(bd.ready()){
					String str = bd.readLine();
					hashSet.add(str);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//2. 打印
		for (String string : hashSet) {
			System.out.println(string);
		}
		
	}//end Main()

}
