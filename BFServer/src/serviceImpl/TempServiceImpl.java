package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.RemoteException;

import service.TempService;

public class TempServiceImpl implements TempService{

	@Override
	public boolean createTempFile(String code, int pointer) throws RemoteException {
		File file = new File("temp/"+"temp"+pointer);
		int p =pointer;
		if(file.exists()){
			while(true){
				File files = new File("temp/"+"temp"+p);
				if(!files.exists()){
					break;
				}else{
					files.delete();					
					p++;
				}
			}
		}
		if(!file.exists()){
			try {
				file.createNewFile();
				FileWriter fw0 = new FileWriter(file, false);
				fw0.write(code);
				fw0.flush();
				fw0.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public String getPointFile(int pointer) throws RemoteException {
		  File file = new File("temp/"+"temp"+pointer);
		  System.out.println("file:" +file.getName());
		  String content ="";
			String line;
			try {
				FileReader fr = new FileReader(file);
				BufferedReader bf = new BufferedReader(fr);
				int i = 0;
				while((line=bf.readLine())!=null){
					System.out.println("line"+(i++)+"   "+line);
				     content=content+line+'\n';
				     //System.out.println(line);
				}
				System.out.println(content);
				bf.close();
				fr.close();
				return content;
			} catch (IOException e) {
				e.printStackTrace();
				return "####nothing^^";
			}
	}

}
