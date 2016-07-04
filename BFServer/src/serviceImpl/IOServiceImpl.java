package serviceImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import service.IOService;

public class IOServiceImpl implements IOService{
	
	@Override
	public boolean writeFile(String file, String userId, String fileName) {
			File filexml= new File("data/"+userId+"/"+userId+".xml");
			if(!filexml.exists())
				try {
					filexml.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			try{
			FileWriter fw0 = new FileWriter(filexml, false);
			fw0.write(fileName);
			fw0.flush();
			fw0.close();
			}catch(IOException e) {
				e.printStackTrace();
				return false;
			}
			
		SimpleDateFormat simpledate = new SimpleDateFormat("yyyyMMddHHMMSS");
		Date now = new Date(System.currentTimeMillis());
		String dateNow = simpledate.format(now);
		File fileformal = new File("data/"+userId+"/"+fileName+".txt");
		File filetempdir = new File("data/"+userId+"/"+fileName);
		File filetemp =  new File("data/"+userId+"/"+fileName+"/"+dateNow+".txt");
		if(!fileformal.exists())
			try {
				fileformal.createNewFile();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if(!filetempdir.exists()){
			filetempdir.mkdirs();
			System.out.println(filetempdir.getAbsolutePath());
		}
		if(!filetemp.exists()){
		
				try {
					filetemp.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("try1");
					e.printStackTrace();
					System.out.println("try2");
				}
			
		}
		try {
			FileWriter fw = new FileWriter(fileformal, false);
			fw.write(file);
			fw.flush();
			fw.close();
			FileWriter fw2 = new FileWriter(filetemp, false);
			fw2.write(file);
			fw2.flush();
			fw2.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public String readFile(String userId, String fileName) {
		// TODO Auto-generated method stub
		File f = new File("data/"+userId +"/"+ fileName+".txt");
		String content ="";
		String line;
		try {
			FileReader fr = new FileReader(f);
			BufferedReader bf = new BufferedReader(fr);
			while((line=bf.readLine())!=null){
			     content=content+line+'\n';
			     //System.out.println(line);
			}
			bf.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "Nothing";
		}
		return content;
	}

	@Override
	public String readFileList(String userId) {
		String filelist="";
		List<File> files =
 			   Stream.of(new File("data/"+userId).listFiles())
 			   .filter(t->!t.isDirectory()&&!t.isHidden()&&!t.getName().endsWith(".xml"))
 			   .flatMap(file -> file.listFiles() == null ?
 			   Stream.of(file) : Stream.of(file.listFiles()))
 			   .collect(Collectors.toList());
 	            filelist= files.stream().map(t->t.getName())
 	            		.map(t->t.substring(0,t.length()-4))
               .collect(Collectors.joining(";"));
 	            System.out.println("filelist:  "+ filelist);
 	    
		return filelist;
	}

	
	
     // 如果传过来的是 用户名名.xml    读取上一次打开的 文件名
	@Override
	public String controlRead(String userId, String fileName) throws RemoteException {
		System.out.println("here");
	    //读取上一次打开的 文件名
		if("xml".equals(fileName)){
			File filexml = new File("data/"+userId+"/"+userId+".xml");
			if(!filexml.exists()){
				System.out.println("nothing");
				return"";
			}
			    String line="";
			try {
				FileReader fr = new FileReader(filexml);
				BufferedReader bf = new BufferedReader(fr);
				line = bf.readLine();  // 该文件  就一个一行  就一个文件名
				bf.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
				return "";
			}
			System.out.println("line:"+line);
			return line;
		}else{
			//否则读取  这个文件的 所有版本名 并且 用字符串传回去
			String filelist;
			List<File> files =
	 			   Stream.of(new File("data/"+userId+"/"+fileName).listFiles())   //到这个文件版本控制的 文件夹中
	 			   .filter(t->!t.isDirectory()&&!t.isHidden()&&!t.getName().endsWith(".xml"))//xml 文件跟版本控制文件 存在一起 所以要排除这个文件
	 			   .flatMap(file -> file.listFiles() == null ? //看映射完是否为空
	 			   Stream.of(file) : Stream.of(file.listFiles()))
	 			   .collect(Collectors.toList());
	 	            filelist= files.stream().map(t->t.getName())
	 	            		.map(t->t.substring(0,t.length()-4)) //吧文件末尾的.txt 删掉
	               .collect(Collectors.joining(";"));
	 	            System.out.println("filelist:  "+ filelist);
	 	            if(filelist.length()>95) {
                        System.out.println(filelist.substring(filelist.length()-95, filelist.length()));
			            return filelist.substring(filelist.length()-95, filelist.length());  //取前十个  用分号隔开并且传过来
	 	            }else return filelist;
		}
		
	}

	@Override
	public String versionRead(String userId, String fileName,String version) throws RemoteException {
		// 读取版本控制的文件
		File f = new File("data/"+userId +"/"+ fileName+"/"+version+".txt");
		String content ="";
		String line;
		try {
			FileReader fr = new FileReader(f);
			BufferedReader bf = new BufferedReader(fr);
			while((line=bf.readLine())!=null){
			     content=content+line+'\n';
			     //System.out.println(line);
			}
			bf.close();
			fr.close();
			File fileformal = new File("data/"+userId+"/"+fileName+".txt");
			FileWriter fw = new FileWriter(fileformal, false);
			fw.write(content);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "Nothing";
		}
		return content;
	}
	
}
