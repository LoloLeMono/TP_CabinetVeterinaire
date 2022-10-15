package server.classes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import server.common.IDossierSuivi;

public class DossierSuivi extends UnicastRemoteObject implements IDossierSuivi{

	private String dossier;
	public DossierSuivi() throws RemoteException {
		super();
		dossier = "";
	}
	@Override
	public String getInfo() throws RemoteException{
		return dossier;
	}
	@Override
	public IDossierSuivi getDossier() throws RemoteException{
		return this;
	}
	@Override
	public void modifier(String s) throws RemoteException{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		dossier += dtf.format(now) + " - " + s + " \n ";
	}

}
