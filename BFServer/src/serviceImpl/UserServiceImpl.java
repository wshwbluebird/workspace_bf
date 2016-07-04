package serviceImpl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;

import service.UserService;

public class UserServiceImpl implements UserService{

	@Override
	public boolean login(String username, String password) throws RemoteException {
		File userinfo = new File("data/userinfo.txt");
		// 读取 存储 所有用户 用户名 密码的存储文件
		try {
			FileReader fr = new FileReader(userinfo);
			BufferedReader reader = new BufferedReader(fr);
			String sr;
			while((sr = reader.readLine()) != null){
				if((username+";"+password).equals(sr))
				//每行都读 直到读到一行用户名密码完全匹配  返回true	
					return true;
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		//没有匹配 返回false
		
		return false;
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		return true;
	}

	@Override
	public boolean signup(String username, String password) throws RemoteException {
		File userinfo = new File("data/userinfo.txt");
		// 读取存储信息的文件
		try {
			FileReader fr = new FileReader(userinfo);
			BufferedReader reader = new BufferedReader(fr);
			String sr;
			while((sr = reader.readLine()) != null){
				 String user = sr.substring(0,sr.indexOf(';'));
				if(user.equals(username)){
					reader.close();
				    fr.close();
				    // 轮询检查 是否 这个用户名已经被注册
				    return false;
				}
			}
			//如果没有重复
			//那么就在 存储用户名和 密码的文件中 写入 这个 用户的用户名和密码
			FileWriter fw = new FileWriter(userinfo, true);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.newLine();
			writer.write(username+";"+password);
			writer.flush();
			writer.close();
			fw.close();
			
			//  为这个新用户 创建 文件存储结构
			//  文件夹的名字 就是这个用户的用户名
			File filetempdir = new File("data/"+username);
			filetempdir.mkdirs();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
