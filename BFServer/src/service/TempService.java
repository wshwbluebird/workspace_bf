package service;

import java.rmi.Remote;
import java.rmi.RemoteException;
public interface TempService extends Remote{
	public boolean createTempFile(String code, int pointer)throws RemoteException;
	
	public String getPointFile(int pointer)throws RemoteException;
	
}
