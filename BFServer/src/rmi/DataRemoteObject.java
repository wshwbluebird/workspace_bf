package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.ExecuteService;
import service.IOService;
import service.TempService;
import service.UserService;
import serviceImpl.ExecuteServiceImpl;
import serviceImpl.IOServiceImpl;
import serviceImpl.TempServiceImpl;
import serviceImpl.UserServiceImpl;

public class DataRemoteObject extends UnicastRemoteObject implements IOService, UserService, ExecuteService,TempService{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4029039744279087114L;
	private IOService iOService;
	private UserService userService;
	private ExecuteService executeService;
	private TempService tempService;
	protected DataRemoteObject() throws RemoteException {
		iOService = new IOServiceImpl();
		userService = new UserServiceImpl();
		executeService = new ExecuteServiceImpl();
		tempService = new TempServiceImpl();
	}

	@Override
	public boolean writeFile(String file, String userId, String fileName) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.writeFile(file, userId, fileName);
	}

	@Override
	public String readFile(String userId, String fileName) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.readFile(userId, fileName);
	}

	@Override
	public String readFileList(String userId) throws RemoteException{
		// TODO Auto-generated method stub
		return iOService.readFileList(userId);
	}

	@Override
	public boolean login(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return userService.login(username, password);
	}

	@Override
	public boolean logout(String username) throws RemoteException {
		// TODO Auto-generated method stub
		return userService.logout(username);
	}

	@Override
	public String controlRead(String userId, String fileName) throws RemoteException {
		// TODO Auto-generated method stub
		return iOService.controlRead(userId, fileName);
	}

	
	@Override
	public String versionRead(String userId, String fileName, String version) throws RemoteException {
		// TODO Auto-generated method stub
		return iOService.versionRead(userId, fileName, version);
	}

	@Override
	public String execute(String code, String param) throws RemoteException {
		// TODO Auto-generated method stub
		return executeService.execute(code, param);
	}

	@Override
	public boolean signup(String username, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return userService.signup(username, password);
	}

	@Override
	public boolean createTempFile(String code, int pointer) throws RemoteException {
		// TODO Auto-generated method stub
		return tempService.createTempFile(code,pointer);
	}

	@Override
	public String getPointFile(int pointer) throws RemoteException {
		// TODO Auto-generated method stub
		return tempService.getPointFile(pointer);
	}

}
