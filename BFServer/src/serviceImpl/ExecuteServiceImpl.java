//璇蜂笉瑕佷慨鏀规湰鏂囦欢鍚�
package serviceImpl;

import java.rmi.RemoteException;

import service.ExecuteService;
import service.UserService;

public class ExecuteServiceImpl implements ExecuteService {

	/**
	 * 璇峰疄鐜拌鏂规硶
	 */
	@Override
	public String execute(String code, String param) throws RemoteException {
		// TODO Auto-generated method stub
		char[] chararray  = code.toCharArray();
		if(param.length()!=code.chars().mapToLong(t->t==','?1:0).sum())
			System.out.println("&wrong#");
		else{
		long much = code.chars().mapToLong(t->t=='>'?1:0).sum();
		char[] inputvalue = param.toCharArray();
		char[] charvalue = new char[(int) much];
		int[] left = new int[code.length()];
		int[] loc = new int[code.length()];
		int rear = 0;
		for (int i = 0; i < code.length(); i++) {
			if(code.charAt(i)=='[')  left[rear++]  = i;
			else if(code.charAt(i)==']') {
				loc[i] = left[--rear];
				loc[loc[i]] = i;
			}
			
			
		}
		int pointer = 0;
		int index = 0;
		int inputindex=0;
		String ans  = "";
		while(index!=code.length()){
			if('>'==(chararray[index]) ){
				pointer++;
			}else if('<'==(chararray[index]) ){
				pointer--;
			}else if('+'==(chararray[index]) ){
				charvalue[pointer]++;
			}else if('-'==(chararray[index]) ){
				charvalue[pointer]--;
			}else if(','==(chararray[index]) ){
				charvalue[pointer]=inputvalue[inputindex++];
			}else if('.'==(chararray[index]) ){
				//System.out.print(charvalue[pointer])
				ans+=charvalue[pointer];
			}else if('['==(chararray[index]) ){
				if(charvalue[pointer]==0){
					index = loc[index];
				}
			}else if(']'==(chararray[index]) ){				
				   index = loc[index]-1;				
			}
			 
			index++;
		}
		return ans;
		}
		return "*Wrong*";
	}

}
