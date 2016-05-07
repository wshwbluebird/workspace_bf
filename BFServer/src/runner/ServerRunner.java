package runner;

import rmi.RemoteHelper;

public class ServerRunner {
	
	public ServerRunner() {
		new RemoteHelper();
	}
	
	public static void main(String[] args) {
		System.out.println("ZXc");
		new ServerRunner();
		System.out.println("c");
	}
}
