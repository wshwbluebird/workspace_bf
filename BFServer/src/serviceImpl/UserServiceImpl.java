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
		try {
			FileReader fr = new FileReader(userinfo);
			BufferedReader reader = new BufferedReader(fr);
			String sr;
			while((sr = reader.readLine()) != null){
				if((username+";"+password).equals(sr))
					
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
		
		
		return false;
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		return true;
	}

	@Override
	public boolean signup(String username, String password) throws RemoteException {
		File userinfo = new File("data/userinfo.txt");
		try {
			FileReader fr = new FileReader(userinfo);
			BufferedReader reader = new BufferedReader(fr);
			String sr;
			while((sr = reader.readLine()) != null){
				 String user = sr.substring(0,sr.indexOf(';'));
				if(user.equals(username)){
					reader.close();
				    fr.close();
				    return false;
				}
			}
			FileWriter fw = new FileWriter(userinfo, true);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.newLine();
			writer.write(username+";"+password);
			writer.flush();
			writer.close();
			fw.close();
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
