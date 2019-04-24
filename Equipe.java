import java.util.ArrayList;
import java.util.Scanner;

public class Equipe
{

	private ArrayList<Unite> liste_unite_equipe = new ArrayList<Unite>();
	public ArrayList<Unite> getListeEquipe() { return liste_unite_equipe; }

	Unite ajoutUnite(String nom, int pv, int p_att, int p_def, int depl,int vision)
	{
		Unite perso = new Unite(nom, pv, p_att, p_def, depl, vision);
		return perso;
	}

	void ajoutEquipe(ArrayList<Unite> liste, int lig, int col)
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
			choix = selectionUniteEquipe(liste);
			if (choix==0) //archer
				this.liste_unite_equipe.add(ajoutUnite("Archer", 33, 6, 2, 5, 7));
			else if(choix==1)
				this.liste_unite_equipe.add(ajoutUnite("Infanterie", 28, 5, 3, 6, 4));
			else if(choix==2)
				this.liste_unite_equipe.add(ajoutUnite("Cavalerie", 38, 8, 3, 8, 6));


		}while(j!=nbr_max);

		System.out.println("\nVoici donc votre equipe : ");
		this.afficheCarac(this.liste_unite_equipe);

		//Initialisation des positions des joueurs 
		for (i=0;i<this.liste_unite_equipe.size();i++)
		{
			this.liste_unite_equipe.get(i).ligne = lig;
			this.liste_unite_equipe.get(i).colonne = col;
			col+=1;
		}
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

	int selectionUniteEquipe(ArrayList<Unite> liste_unite)
	{
		int choix;
		Scanner sc = new Scanner(System.in);

		do
		{
			System.out.print("Choix : ");
			choix = sc.nextInt();
		}while(choix<0||choix>=liste_unite.size());
		return choix;
	}

	int verifProchaineCol(int identifiant)
	{
		int choix;
		Scanner sc = new Scanner(System.in);

		System.out.println("Coord : [" + this.getListeEquipe().get(identifiant).ligne + "][" + this.getListeEquipe().get(identifiant).colonne + "] / 1) Right 2) Left 3) Same "); //colonne
		do
		{
			System.out.print("Choix : ");
	    	choix=sc.nextInt();
		}while(choix!=1&&choix!=2&&choix!=3);

		if (choix==1)
		{
			//si la colonne du personnage ne sort pas du plateau tout a droite (colonne<=10)
			//alors on effectue l'opération (+1) et on met un etat a faux
			if (this.getListeEquipe().get(identifiant).colonne+1<10)
			{
				this.getListeEquipe().get(identifiant).colonne+=1;
			}
		}
		else if (choix==2)
		{
			//si la colonne du personnage ne sort pas du plateau tout a gauche (colonne>=0)
			//alors on effectue l'opération (-1) et on met un etat a faux
			if (this.getListeEquipe().get(identifiant).colonne-1>=0)
			{
				this.getListeEquipe().get(identifiant).colonne-=1;
			}
		
		}
		return choix;
	}

	int verifProchaineLig(int identifiant)
	{
		int choix;
		Scanner sc = new Scanner(System.in);

		//Coord : [ligne][colonne] / 1) Up 2) Down 3) Same
		System.out.println("Coord: [" + this.getListeEquipe().get(identifiant).ligne + "][" + this.getListeEquipe().get(identifiant).colonne +"] / 1) Up 2) Down 3) Same"); //ligne
		do
		{
			System.out.print("Choix : ");
	    	choix=sc.nextInt();
		}while(choix!=1&&choix!=2&&choix!=3);

		//UP
		if (choix==1)
		{
			//si la ligne du personnage en haut ne sort pas du plateau tout en haut (ligne=0)
			//alors on effectue l'opération (-1) et on met un etat a faux
			if (this.getListeEquipe().get(identifiant).ligne-1>=0)
			{
				this.getListeEquipe().get(identifiant).ligne-=1;	
			}
		
		}
		//DOWN
		else if (choix==2)
		{
			//si la ligne du personnage en bas ne sort pas du plateau tout en bas (ligne=10 ici)
			//alors on effectue l'operation (+1) et on met stay a faux
			if (this.getListeEquipe().get(identifiant).ligne+1<10)
			{
				this.getListeEquipe().get(identifiant).ligne+=1;	
			}
		}
		
		return choix;
	}

	boolean verifProchaineCase(int identifiant, Plateau plat)
	{
		//si la case qu'on a choisi est occupe par un personnage alors on ne peut pas s'y deplacer
		System.out.println("Case occupe par un autre perso ? " + plat.getEtatOccupe()[this.getListeEquipe().get(identifiant).ligne][this.getListeEquipe().get(identifiant).colonne] );
		return (plat.getEtatOccupe()[this.getListeEquipe().get(identifiant).ligne][this.getListeEquipe().get(identifiant).colonne]);
	}

	void deplacer(Plateau plat, GroupeTerrain terrain, int tour)
	{
		int identifiant;
		Scanner sc = new Scanner(System.in);
		int ligne_prec, colonne_prec;
		int choix_lig, choix_col;
		boolean occupe=false;
		int cout_case;
		int depl;

		//afficher les joueurs de notre equipe pour pouvoir le selectionner
		this.afficheCarac(this.getListeEquipe());

		for (int i=0;i<this.getListeEquipe().size();i++)
		{
			System.out.println(i + ") Coord : " + this.getListeEquipe().get(i).ligne + " " + this.getListeEquipe().get(i).colonne);
		}

		identifiant = selectionUniteEquipe(this.getListeEquipe()); //on recupere l'identifiant du joueur qu'on joue
		depl = this.getListeEquipe().get(identifiant).getDepl();   //on obtient son nombre de points de deplacement

		do
		{
			System.out.println("Pour passer votre tour, rester sur votre place (SAME puis SAME)\n");
			//on sauvegarde la ligne / colonne precedente en cas de probleme et pour effacer dans la matrice
			ligne_prec = this.getListeEquipe().get(identifiant).ligne; 
			colonne_prec = this.getListeEquipe().get(identifiant).colonne; 

			System.out.println("Vous avez " + depl + " points de deplacements restants");
			choix_lig = verifProchaineLig(identifiant);	//verif si on sort du tableau ou non niveau ligne
			choix_col = verifProchaineCol(identifiant);  //verif si on sort du tableau ou non niveau colonne
			occupe = verifProchaineCase(identifiant, plat); //verif si la prochaine case est occupe

			// cout_case = terrain.ListeTerrain.get(plat[ligne][colonne]).getDeplTerrain()
			cout_case = terrain.getListeTerrain().get( plat.getCases()[this.getListeEquipe().get(identifiant).ligne][this.getListeEquipe().get(identifiant).colonne] ).getDeplTerrain();

			System.out.print("Prochaine case : " + plat.getCases()[this.getListeEquipe().get(identifiant).ligne][this.getListeEquipe().get(identifiant).colonne]);
			System.out.print(" / Cout de la case :  " + cout_case);
			System.out.println(" / Coord : " + this.getListeEquipe().get(identifiant).ligne + " " + this.getListeEquipe().get(identifiant).colonne);
			

			if (depl-cout_case<0||occupe==true) //si probleme sur la prochaine ligne ou prochaine colonne, rester sur place
			{
				if (choix_lig==3&&choix_col==3)
				{
					System.out.println("\nIl vous restait " + depl + " points de deplacements restants");
					System.out.println("Vous avez passe votre tour!\n");
					return;
				}
				System.out.println("\n** NE PEUT PAS SE DEPLACER! **");
				System.out.println("** VOIR COUT DE LA CASE ou SI CASE DEJA OCCUPE **\n");
				this.getListeEquipe().get(identifiant).ligne = ligne_prec;
				this.getListeEquipe().get(identifiant).colonne = colonne_prec;
			}
			else
			{
				plat.effacerPosUnites(ligne_prec,colonne_prec); //effacer case precdente
				plat.setPosUnites(tour,this.getListeEquipe().get(identifiant).ligne,this.getListeEquipe().get(identifiant).colonne); //afficher nouvelle pos
				depl -= cout_case; //calculer le nouveau cout de deplacement
				plat.affichagePlateau(); //afficher la nouvelle matrice
			}
			

		}while(depl > 0);

  	}

	

}