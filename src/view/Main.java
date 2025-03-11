package view;

import javax.swing.JOptionPane;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		//System.getProperties().forEach((k, v) -> System.out.println(k + " = " + v));
		RedesController redes = new RedesController();
		int op;
		
		do {
			op = Integer.parseInt(JOptionPane.showInputDialog("Digite uma opção: \n 1- Mostrar IP \n 2- Mostrar ping \n 9- Finalizar"));
			
			switch(op) {
			case 1:
				redes.getIP();
				break;
			case 2:
				redes.showPing();
				break;
			}
		} while(op != 9);
		
		
	}

}
