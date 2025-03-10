package view;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//System.getProperties().forEach((k, v) -> System.out.println(k + " = " + v));
		RedesController redes = new RedesController();
		//redes.callProcess("regedit.exe");
		//System.out.println(redes.readProcess(redes.callProcess("ping -4 -n 10 www.google.com.br")));
		//System.out.println(redes.getIP());
		//redes.getIP();
		redes.showPing();
	}

}
