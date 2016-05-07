//请不要修改本文件名
package serviceImpl;

import java.rmi.RemoteException;

import service.ExecuteService;
import service.UserService;

public class ExecuteServiceImpl implements ExecuteService {

	/**
	 * 请实现该方法
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
					while(chararray[++index]!=']');
				}
			}else if(']'==(chararray[index]) ){				
					while(chararray[index--]!='[');				
			}
			 
			index++;
		}
		return ans;
		}
		return "*Wrong*";
	}

}
