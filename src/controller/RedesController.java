package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {
		super();
		// TODO Auto-generated constructor stub
	}

	private String getOS() {
		return System.getProperty("os.name");
	}

	public void getIP() {
		String os = getOS();
		String ip = "";
		
		if (os.contains("Windows")) {
			ip = readProcess(callProcess("ipconfig"));
		} else if (os.contains("Linux")) {
			ip = readProcess(callProcess("ifconfig"));
			if (ip == null) {
				ip = readProcess(callProcess("ip addr"));
			}
		} else {
			System.err.println("SO não suportado: " + os);
		}

		if (ip == null) {
			System.err.println("Falha ao obter a configuração de IP.");
		}else {
			String ipLine[] = ip.split("\\R");
			StringBuffer adapters = new StringBuffer();
			String adaptador = "";
			String ipv4[];
			for(String linha : ipLine) {
				int cont = 0;
				if(linha.contains("adapter")) {
					cont++;
					adaptador = linha;
				}
				if(linha.contains("IPv4")) {
					ipv4 = linha.split(":");
					adapters.append("Adaptador: " + adaptador + " \n IPV4: " + ipv4[1] + "\n");
				}else {
					cont--;
				}
			}
			
			System.out.println(adapters);
				
		}
	}

	public Process callProcess(String proc) {
		String[] procArr = proc.split(" ");
		try {
			return Runtime.getRuntime().exec(procArr);
		} catch (Exception e) {
			if (e.getMessage().contains("740")) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("cmd /c");
				buffer.append(" ");
				buffer.append(proc);
				try {
					procArr = buffer.toString().split(" ");
					return Runtime.getRuntime().exec(procArr);
				} catch (Exception e1) {
					System.err.println(e1.getMessage());
				}
			} else {
				System.err.println(e.getMessage());

			}
			return null;
		}
	}

	public String readProcess(Process proc) {
		try {
			InputStream fluxo = proc.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			StringBuffer procMsg = new StringBuffer();
			String linha;
			while ((linha = buffer.readLine()) != null) {
				procMsg.append(linha).append("\n");
			}
			buffer.close();
			leitor.close();
			fluxo.close();
			return procMsg.toString();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return null;
		}

	}
	
	public void showPing() {
		String os = getOS();
		if (os.contains("Windows")) {
			String ping = readProcess(callProcess("ping -4 -n 10 www.google.com.br"));
			for(String linha : ping.split("\\R")) {
				if(linha.contains("Average")) {
					String[] tempos = linha.split("=");
					System.out.println("Tempo médio: " + tempos[3]);
				}
			}
		}else if (os.contains("Linux")){
			String ping = readProcess(callProcess("ping -4 -c 10 www.google.com.br"));
			for(String linha : ping.split("\\R")) {
				if(linha.contains("Average")) {
					String[] tempos = linha.split("=");
					System.out.println("Tempo médio: " + tempos[3]);
				}
			}
		}
		
		
	}

}
