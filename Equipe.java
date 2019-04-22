import java.util.ArrayList;
import java.util.Scanner;

public class Equipe
{
	private int identifiant;
	private ArrayList<Unite> liste_unite_equipe = new ArrayList<Unite>();

	Equipe(int identifiant)
	{
		this.identifiant = identifiant;
	}

	public int getIdentifiant() { return this.identifiant; }

	public ArrayList<Unite> getListeEquipe() { return liste_unite_equipe; }

	void ajoutEquipe(ArrayList<Unite> liste)
	{
		int i;
		int choix;
		int j=0;
		int nbr_max=2;
		Scanner sc = new Scanner(System.in);

		//affiche tout les personnages disponibles
		this.afficheCarac(liste);

		System.out.println("Vous devez choisir " + nbr_max + " unités\n");
		//on demande a l'utilisateur de former son equipe
		do
		{
			j+=1;
			System.out.println("Veuillez choisir une unite (num) ");
			do
			{
				System.out.print("Choix : ");
				choix = sc.nextInt();
			}while(choix<0||choix>=liste.size());

			liste_unite_equipe.add(liste.get(choix));

		}while(j!=nbr_max);

		System.out.println("\nVoici donc votre equipe : ");
		this.afficheCarac(liste_unite_equipe);
	}

	void afficheCarac(ArrayList<Unite> liste_unite)
	{
		for (int i=0;i<liste_unite.size();i++)
		{
			System.out.print(i +")" + "Nom : " + liste_unite.get(i).getNom() );
			System.out.print(" / PV : " + liste_unite.get(i).getPV() );
			System.out.print(" / P_Att : " + liste_unite.get(i).getPAtt() );
			System.out.print(" / P_Def : " + liste_unite.get(i).getPDef() );
			System.out.print(" / P_Depl : " + liste_unite.get(i).getDepl() );
			System.out.println(" / Vision : " + liste_unite.get(i).getVis() );
		}
		System.out.println();
	}

	void deplacer(Plateau plat, GroupeTerrain terrain)
	{
		int identifiant;
		Scanner sc = new Scanner(System.in);
		int ligne, ligne_prec;
		int colonne, colonne_prec;
		int depl;
		int cout_case;
		int choix;
		boolean stay = false;

		//afficher les joueurs de notre equipe pour pouvoir le selectionner
		this.afficheCarac(this.getListeEquipe());

		System.out.println("Veuillez choisir une unite (num) ");
		do
		{
			System.out.print("Choix : ");
			identifiant = sc.nextInt();
		}while(identifiant<0||identifiant>=this.getListeEquipe().size());

		// ligne et colonne du perso qu'on a choisi
		ligne = this.getListeEquipe().get(identifiant).ligne;
		colonne = this.getListeEquipe().get(identifiant).colonne;
		depl = this.getListeEquipe().get(identifiant).getDepl();


		do
		{
			ligne_prec = ligne;
			colonne_prec = colonne;

			System.out.println("Vous avez " + depl + " points de deplacements restants");

			System.out.println("Coord: " + ligne + " " + colonne +" / 1) Up 2) Down 3) Same");
			do
			{
				System.out.print("Choix : ");
		    	choix=sc.nextInt();
			}while(choix!=1&&choix!=2&&choix!=3);

			if (choix==1)
			{
				if (ligne-1>=0)
				{
					ligne-=1;
					
				}
			
			}
			else if (choix==2)
			{
				if (ligne+1<=10)
				{
					ligne+=1;	
					
				}
				
			}

		    System.out.println("Coord : " + ligne + " " + colonne + " / 1) Right 2) Left 3) Same");
			do
			{
				System.out.print("Choix : ");
		    	choix=sc.nextInt();
			}while(choix!=1&&choix!=2&&choix!=3);

			if (choix==1)
			{
				if (colonne+1>=10)
				{
					colonne+=1;
					
				}
			
			}
			else if (choix==2)
			{
				if (colonne-1<=0)
				{
					colonne-=1;
					
				}
			
			}


			cout_case = terrain.getListeTerrain().get(plat.getCases()[ligne][colonne]).getDeplTerrain();

			System.out.println("Prochaine case : " + plat.getCases()[ligne][colonne] + " / coord : " + ligne + " " + colonne);
			System.out.println("Cout de la case : " + cout_case);
			if (depl-cout_case>=0)
			{
				plat.effacerPosUnites(ligne_prec,colonne_prec);
				plat.setPosUnites(this.getIdentifiant(),ligne,colonne);
				depl -= cout_case;
				plat.affichagePlateau();
			}
			

		}while(depl > 0);

  	}

	

}