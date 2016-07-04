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
		// ��ȡ �洢 �����û� �û��� ����Ĵ洢�ļ�
		try {
			FileReader fr = new FileReader(userinfo);
			BufferedReader reader = new BufferedReader(fr);
			String sr;
			while((sr = reader.readLine()) != null){
				if((username+";"+password).equals(sr))
				//ÿ�ж��� ֱ������һ���û���������ȫƥ��  ����true	
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
		//û��ƥ�� ����false
		
		return false;
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		return true;
	}

	@Override
	public boolean signup(String username, String password) throws RemoteException {
		File userinfo = new File("data/userinfo.txt");
		// ��ȡ�洢��Ϣ���ļ�
		try {
			FileReader fr = new FileReader(userinfo);
			BufferedReader reader = new BufferedReader(fr);
			String sr;
			while((sr = reader.readLine()) != null){
				 String user = sr.substring(0,sr.indexOf(';'));
				if(user.equals(username)){
					reader.close();
				    fr.close();
				    // ��ѯ��� �Ƿ� ����û����Ѿ���ע��
				    return false;
				}
			}
			//���û���ظ�
			//��ô���� �洢�û����� ������ļ��� д�� ��� �û����û���������
			FileWriter fw = new FileWriter(userinfo, true);
			BufferedWriter writer = new BufferedWriter(fw);
			writer.newLine();
			writer.write(username+";"+password);
			writer.flush();
			writer.close();
			fw.close();
			
			//  Ϊ������û� ���� �ļ��洢�ṹ
			//  �ļ��е����� ��������û����û���
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
