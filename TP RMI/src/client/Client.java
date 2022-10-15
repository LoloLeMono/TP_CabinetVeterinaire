package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

import server.common.IAnimal;
import server.common.ICabinet;


public class Client extends UnicastRemoteObject implements Notifiable{
	
	public Client() throws RemoteException {
		super();
	}
	public void init(String[] args) {
		String host = (args.length < 1) ? null : args[0];
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			
			// Ex 5:
			Observer s = (Observer) registry.lookup("server");
			s.addClient(this);

			// Ex1:
			IAnimal stub = (IAnimal) registry.lookup("Zizoo");
			/*
			String response = stub.getNom();
			System.out.println("response: " + response);
			System.out.println(stub.getDossier().getInfo());
			stub.getDossier().modifier("Test");
			System.out.println(stub.getDossier().getInfo());
			System.out.println(stub.getEspece());
			System.out.println(stub.getRace());
			*/


			// Ex2: 
			ICabinet cabinet = (ICabinet) registry.lookup("Cabinet");
			/*
			for (IAnimal a : cabinet.getPatients()) {
				System.out.print(a.getNom() + " ");
			}
			System.out.println(" ");
			IAnimal zizoo = cabinet.get("Zizoo");
			System.out.println(zizoo.getNom() + " " + zizoo.getMaitre());
			cabinet.add("Ajout", "Test", "Je sais pas", "Peu importe");
			for (IAnimal a : cabinet.getPatients()) {
				System.out.print(a.getNom() + " ");
			}
			System.out.println(" ");
			 */
			boolean fini = false;
			while(!fini) {
				System.out.println("Entrez le numéro de l'action que vous souhaitez effectuer :");
				System.out.println("1. Ajouter un animal.");
				System.out.println("2. Afficher les informations d'un animal.");
				System.out.println("3. Modifier le dossier suivi.");
				System.out.println("4. Afficher tout les animaux.");
				System.out.println("5. Quitter.");
				Scanner saisieUtilisateur = new Scanner(System.in);
				System.out.print("Mon choix : ");
				int ent = saisieUtilisateur.nextInt();
				saisieUtilisateur.nextLine();
				System.out.println("");
				switch (ent) {

					case 1:
						System.out.print("Entrez le nom de l'animal : ");
						String nom = saisieUtilisateur.nextLine();
						System.out.print("Entrez le nom du maître (Gims) : ");
						String maitre = saisieUtilisateur.nextLine();
						System.out.print("Entrez l'espèce : ");
						String espece = saisieUtilisateur.nextLine();
						System.out.print("Entrez la race : ");
						String race = saisieUtilisateur.nextLine();
						cabinet.add(nom, maitre, espece, race);
						System.out.println("L'animal a bien été ajouté");
						System.out.println("");
						break;

					case 2:
						System.out.print("Entrez le nom de l'animal : ");
						String animal_rechercher = saisieUtilisateur.nextLine();
						IAnimal mon_animal = cabinet.get(animal_rechercher);
						while (mon_animal == null) {
							System.out.print("Animal inconnu : ");
							animal_rechercher = saisieUtilisateur.nextLine();
							mon_animal = cabinet.get(animal_rechercher);
						}
						System.out.println("Nom : " + mon_animal.getNom());
						System.out.println("Maître : " + mon_animal.getMaitre());
						System.out.println("Espèce : " + mon_animal.getEspece());
						System.out.println("Race : " + mon_animal.getRace());
						System.out.println("Dossier : " + mon_animal.getDossier().getInfo());
						System.out.println("");
						break;

					case 3:
						System.out.print("Entrez le nom de l'animal : ");
						String animal_rechercher2 = saisieUtilisateur.nextLine();
						IAnimal mon_animal2 = cabinet.get(animal_rechercher2);
						while (mon_animal2 == null) {
							System.out.print("Animal inconnu : ");
							animal_rechercher = saisieUtilisateur.nextLine();
							mon_animal2 = cabinet.get(animal_rechercher);
						}
						System.out.println("L'état actuel du dossier : " + mon_animal2.getDossier().getInfo());
						System.out.print("Entrez les nouvelles infos : ");
						String nouvelle_info = saisieUtilisateur.nextLine();
						cabinet.get(animal_rechercher2).getDossier().modifier(nouvelle_info);
						System.out.println("Le dossier mis à jour : " + mon_animal2.getDossier().getInfo());
						System.out.println("");
						break;

					case 4:
						for (IAnimal a : cabinet.getPatients()) {
							System.out.println(a.getNom() + " ");
						}
						System.out.println("");
						break;

					case 5:
						fini = true;
						break;
					default:
						System.out.println("Choix incorrect");
						break;
				}
			}
			
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());

			e.printStackTrace();
		}
	}
	@Override
	public void notify(String msg) throws RemoteException{
		System.out.println("Notif : " + msg);
	}

	public static void main(String[] args) throws RemoteException {		
		Client c = new Client();
		c.init(args);
	}
}
