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
		int nbr_max=6;
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
				this.liste_unite_equipe.add(ajoutUnite("Infanterie", 28, 5, 3, 6, 4));
			else if(choix==1)
				this.liste_unite_equipe.add(ajoutUnite("Archer", 33, 6, 2, 5, 7));
			else if(choix==2)
				this.liste_unite_equipe.add(ajoutUnite("Mage", 21, 5, 1, 5, 5));
			else if(choix==3)
				this.liste_unite_equipe.add(ajoutUnite("Cavalerie", 38, 8, 3, 8, 6));
			else if(choix==4)
				this.liste_unite_equipe.add(ajoutUnite("Infanterie Lourde", 38, 10, 10, 4, 4));


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

	int verifProchaineCol(int vertical, int identifiant)
	{
		int choix=0;
		Scanner sc = new Scanner(System.in);

		if (vertical==1||vertical==2) //UP ou DOWN, donc on a le droit d'aller que a droite ou gauche
		{
			System.out.println("Coord : [" + this.getListeEquipe().get(identifiant).ligne + "][" + this.getListeEquipe().get(identifiant).colonne + "] / 1) Right 2) Left "); //colonne
			do
			{
				System.out.print("Choix : ");
		    	choix=sc.nextInt();
			}while(choix!=1&&choix!=2);
		}
		else if (vertical==3) //SAME, on a le droit de rester sur place ou droite / gauche
		{
			System.out.println("Coord : [" + this.getListeEquipe().get(identifiant).ligne + "][" + this.getListeEquipe().get(identifiant).colonne + "] / 1) Right 2) Left 3) Same "); //colonne
			do
			{
				System.out.print("Choix : ");
		    	choix=sc.nextInt();
			}while(choix!=1&&choix!=2&&choix!=3);
		}
		
		//RIGHT
		if (choix==1)
		{
			if (this.getListeEquipe().get(identifiant).ligne%2==0) //prochaine ligne pair
			{
				//si la colonne du personnage ne sort pas du plateau tout a droite (colonne<=10)
				//alors on effectue l'opération (+1)
				if (this.getListeEquipe().get(identifiant).colonne+1<10)
				{
					this.getListeEquipe().get(identifiant).colonne+=1;
				}
			}
			//si ligne impair, on ne change pas la colonne (du au decalage des hexagones)
			
		}
		//LEFT
		else if (choix==2)
		{
			if (this.getListeEquipe().get(identifiant).ligne%2==1||vertical==3) //si prohaine ligne impair ou si on reste a la meme ligne
			{
				//si la colonne du personnage ne sort pas du plateau tout a gauche (colonne>=0)
				//alors on effectue l'opération (-1) et on va a gauche
				if (this.getListeEquipe().get(identifiant).colonne-1>=0)
				{
					this.getListeEquipe().get(identifiant).colonne-=1;
				}
			}
			//sinon, on ne change pas la colonne (du au decalage des hexagones)
			if (this.getListeEquipe().get(identifiant).ligne==0||this.getListeEquipe().get(identifiant).ligne==9)
			{
				if (vertical!=3)
					System.out.println("\n**Vous ne pouvez pas monter ou descendre (extremite)**\n ");
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

	
	int typeAdverse(int identifiant, Equipe eq_adverse)
	{
		int i;
		for (i=0;i<eq_adverse.getListeEquipe().size();i++)
		{
			System.out.println("EQ ADVERSE LIG : " + eq_adverse.getListeEquipe().get(i).ligne + " COL : " + eq_adverse.getListeEquipe().get(i).colonne );
			System.out.println("MEC ACTUEL , LIG : " + this.getListeEquipe().get(identifiant).ligne + " COL : " + this.getListeEquipe().get(identifiant).colonne);
			if ( (eq_adverse.getListeEquipe().get(i).ligne == this.getListeEquipe().get(identifiant).ligne)&&(eq_adverse.getListeEquipe().get(i).colonne == this.getListeEquipe().get(identifiant).colonne) )
				return i;
		}
		System.out.println("ERREUR! pas trouve votre unite adverse");
		return -1; //si erreur 
	}


	void attaquer(int identifiant, Plateau plat, GroupeTerrain terrain, Equipe eq_adverse, int ligne_prec, int colonne_prec)
	{
		int attaque = 0;
		int defense_ennemie;
		double bonus_défense_lieu;
		int degat;
		int i_ennemie;

		attaque = this.getListeEquipe().get(identifiant).getPAtt();
		bonus_défense_lieu = terrain.getListeTerrain().get( plat.getCases()[this.getListeEquipe().get(identifiant).ligne][this.getListeEquipe().get(identifiant).colonne] ).getBonusDef();
		i_ennemie = this.typeAdverse(identifiant, eq_adverse); //identifiant ennemie
		defense_ennemie = eq_adverse.getListeEquipe().get(i_ennemie).getPDef(); //avoir les points de defense de l'ennemie

		System.out.println("Attaque : " + attaque + " Def : "+defense_ennemie);

		defense_ennemie = (int) (defense_ennemie * (1+bonus_défense_lieu)); //calculer ses nouveaux points suite au bonus du lieu
		degat = attaque - defense_ennemie; //calculer les degats infligees

		System.out.println("Attaquant : "+ this.getListeEquipe().get(identifiant).getNom() + " Ennemie : " + eq_adverse.getListeEquipe().get(i_ennemie).getNom());
		System.out.println("Defense lieu : " + bonus_défense_lieu);
		System.out.println("Apres bonus defense lieu, point def ennemie : " + defense_ennemie);
		System.out.println("Degat inflige : " + degat);

		if (degat>0)
		{
			eq_adverse.getListeEquipe().get(i_ennemie).setPV(degat); //enlever ses pv de - degat
			System.out.print("PV ennemie de " + eq_adverse.getListeEquipe().get(i_ennemie).getNom());
			System.out.println(" : " + eq_adverse.getListeEquipe().get(i_ennemie).getPV());
			if (eq_adverse.getListeEquipe().get(i_ennemie).getPV()<=0)
			{
				System.out.println(eq_adverse.getListeEquipe().get(i_ennemie).getNom() + " est mort!");
				plat.effacerPosUnites(eq_adverse.getListeEquipe().get(i_ennemie).ligne,eq_adverse.getListeEquipe().get(i_ennemie).colonne); //effacer case joueur tue
				eq_adverse.getListeEquipe().remove(i_ennemie); // on l'enleve de la liste
				System.out.println("\nIl ne vous reste plus que ces personnages suivants\n");
				this.afficheCarac(eq_adverse.getListeEquipe());
			}
			else
			{
				//si le joueur adverse n'est pas mort on revient a sa position initiale
				this.getListeEquipe().get(identifiant).ligne = ligne_prec;
				this.getListeEquipe().get(identifiant).colonne = colonne_prec;
			}
		}
		else
			System.out.println("DEF ABSOLU! AUCUN DEGAT");
	    
  	}

  	boolean verifDeplPoss(int identifiant, GroupeTerrain terrain, Plateau plat)
  	{
  		boolean case_dispo1 = true; //en haut a gauche
  		boolean case_dispo2 = true; //en haut a droite
  		boolean case_dispo3 = true; //a droite
  		boolean case_dispo4 = true; //a gauche
  		boolean case_dispo5 = true; //en bas a gauche
  		boolean case_dispo6 = true; //en bas a droite

  		int ligne = this.getListeEquipe().get(identifiant).ligne; //recuperer ligne du perso
  		int colonne = this.getListeEquipe().get(identifiant).colonne; //recuperer colonne du perso

  		int depl_perso = this.getListeEquipe().get(identifiant).getDepl();

  		System.out.println("LIG : " + ligne + " / COL : " + colonne + " / DEPL_PERSO : " + depl_perso);
  		// 1 1		 
  		//1 0 1 
  		// 1 1
  		//verifier toutes les positions autour du 0(perso)

  		if(ligne!=0&&ligne!=9&&colonne!=0&&colonne!=9)
  		{
  			//a droite
	  		if ( (depl_perso - terrain.getListeTerrain().get( plat.getCases()[ligne][colonne+1] ).getDeplTerrain()) >= 0)
	  		{
	  			System.out.println("Case dispo a droite en terme de deplacement, equipier?");
	  			for (int i=0;i<this.getListeEquipe().size();i++)
	  			{
	  				/*System.out.println("\nTEST : DROITE");
	  				System.out.println("LIG : " + this.getListeEquipe().get(i).ligne + " / COL : " + this.getListeEquipe().get(i).colonne);*/
	  				if ( (this.getListeEquipe().get(i).ligne==ligne)&&(this.getListeEquipe().get(i).colonne==colonne+1))
	  				{
	  					System.out.println("!!!!!!!!Case non dispo a droite!!!!!!!!");
	  					case_dispo3 = false;
	  				}
	  			}
	  		}
	  		else
  				case_dispo3 = false;

  			//a gauche
	  		if (  (depl_perso - terrain.getListeTerrain().get( plat.getCases()[ligne][colonne-1] ).getDeplTerrain()) >= 0 )
	  		{
	  			System.out.println("Case dispo a gauche en terme de deplacement, equipier?");
	  			for (int i=0;i<this.getListeEquipe().size();i++)
	  			{
	  				/*System.out.println("\nTEST : GAUCHE");
	  				System.out.println("LIG : " + this.getListeEquipe().get(i).ligne + " / COL : " + this.getListeEquipe().get(i).colonne);*/
	  				if ( (this.getListeEquipe().get(i).ligne==ligne)&&(this.getListeEquipe().get(i).colonne==colonne-1))
	  				{
	  					System.out.println("!!!!!!!!Case non dispo a gauche!!!!!!!!");
	  					case_dispo4 = false;
	  				}
	  			}
  			
	  		}
	  		else
	  			case_dispo4 = false;
  			
  			//si ligne pair
  			if (ligne%2==0)
  			{
  				//en haut a gauche
  				if ( (depl_perso - terrain.getListeTerrain().get( plat.getCases()[ligne-1][colonne-1] ).getDeplTerrain() >= 0) )
		  		{
		  			System.out.println("Case dispo en haut en gauche en terme de deplacement, equipier?");
		  			
		  			//verifier si il n'y a pas d'équipier en haut a gauche
		  			for (int i=0;i<this.getListeEquipe().size();i++)
		  			{
		  				/*System.out.println("\nTEST : HAUT");
		  				System.out.println("LIG : " + this.getListeEquipe().get(i).ligne + " / COL : " + this.getListeEquipe().get(i).colonne);*/
		  				if ( (this.getListeEquipe().get(i).ligne==ligne-1)&&(this.getListeEquipe().get(i).colonne==colonne-1))
		  				{
		  					System.out.println("!!!!!!!!Case non dispo en haut à gauche!!!!!!!!");
		  					case_dispo1 = false; 
		  				}
		  			}
	  			}
	  			else
	  				case_dispo1 = false;

	  			//en haut a droite
		  		if ( (depl_perso - terrain.getListeTerrain().get( plat.getCases()[ligne-1][colonne] ).getDeplTerrain()) >= 0 )
		  		{
		  			System.out.println("Case dispo en haut a droite en terme de deplacement, equipier?");
		  			for (int i=0;i<this.getListeEquipe().size();i++)
		  			{
		  				/*System.out.println("\nTEST : HAUT DROITE");
		  				System.out.println("LIG : " + this.getListeEquipe().get(i).ligne + " / COL : " + this.getListeEquipe().get(i).colonne);*/
		  				if ( (this.getListeEquipe().get(i).ligne==ligne-1)&&(this.getListeEquipe().get(i).colonne==colonne))
		  				{
		  					System.out.println("!!!!!!!!Case non dispo en haut a droite!!!!!!!!");
		  					case_dispo2 = false; //il existe donc au moins une case libre
		  				}
		  			}
		  			
		  		}
		  		else
		  			case_dispo2 = false;

		  		//en bas a gauche
		  		if (  (depl_perso - terrain.getListeTerrain().get( plat.getCases()[ligne+1][colonne-1] ).getDeplTerrain()) >= 0 )
		  		{
		  			System.out.println("Case dispo en bas a gauche en terme de deplacement, equipier?");
		  			for (int i=0;i<this.getListeEquipe().size();i++)
		  			{
		  				/*System.out.println("\nTEST : BAS GAUCHE");
		  				System.out.println("LIG : " + this.getListeEquipe().get(i).ligne + " / COL : " + this.getListeEquipe().get(i).colonne);*/
		  				if ( (this.getListeEquipe().get(i).ligne==ligne+1)&&(this.getListeEquipe().get(i).colonne==colonne-1))
		  				{
		  					System.out.println("!!!!!!!!Case non dispo en bas a gauche!!!!!!!!");
		  					case_dispo5 = false;
		  				}
		  			}
		  			
		  		}
		  		else
		  			case_dispo5 = false;

		  		//en bas a droite
		  		if (  (depl_perso - terrain.getListeTerrain().get( plat.getCases()[ligne+1][colonne] ).getDeplTerrain()) >= 0 )
		  		{
		  			System.out.println("Case dispo en bas a droite en terme de deplacement, equipier?");
		  			for (int i=0;i<this.getListeEquipe().size();i++)
		  			{
		  				/*System.out.println("\nTEST : BAS DROITE");
		  				System.out.println("LIG : " + this.getListeEquipe().get(i).ligne + " / COL : " + this.getListeEquipe().get(i).colonne);*/
		  				if ( (this.getListeEquipe().get(i).ligne==ligne+1)&&(this.getListeEquipe().get(i).colonne==colonne))
		  				{
		  					System.out.println("!!!!!!!!Case non dispo en bas a droite!!!!!!!!"); 
		  					case_dispo6 = false;
		  				}
		  			}
		  			
		  		}
		  		else
		  			case_dispo6 = false;

	  			
	  		}

	  		//SI ligne impair,
	  		else 
	  		{
	  			//en haut a gauche
	  			if ( (depl_perso - terrain.getListeTerrain().get( plat.getCases()[ligne-1][colonne] ).getDeplTerrain() >= 0) )
		  		{
		  			System.out.println("Case dispo en haut en gauche en terme de deplacement, equipier?");
		  			
		  			for (int i=0;i<this.getListeEquipe().size();i++)
		  			{
		  				/*System.out.println("\nTEST : HAUT");
		  				System.out.println("LIG : " + this.getListeEquipe().get(i).ligne + " / COL : " + this.getListeEquipe().get(i).colonne);*/
		  				if ( (this.getListeEquipe().get(i).ligne==ligne-1)&&(this.getListeEquipe().get(i).colonne==colonne))
		  				{
		  					System.out.println("!!!!!!!!Case non dispo en hauten gauche!!!!!!!!");
		  					case_dispo1 = false; 
		  				}
		  			}
	  			}
	  			else
	  				case_dispo1 = false;

	  			//en haut a droite
		  		if ( (depl_perso - terrain.getListeTerrain().get( plat.getCases()[ligne-1][colonne+1] ).getDeplTerrain()) >= 0 )
		  		{
		  			System.out.println("Case dispo en haut a droite en terme de deplacement, equipier?");
		  			for (int i=0;i<this.getListeEquipe().size();i++)
		  			{
		  				/*System.out.println("\nTEST : HAUT DROITE");
		  				System.out.println("LIG : " + this.getListeEquipe().get(i).ligne + " / COL : " + this.getListeEquipe().get(i).colonne);*/
		  				if ( (this.getListeEquipe().get(i).ligne==ligne-1)&&(this.getListeEquipe().get(i).colonne==colonne+1))
		  				{
		  					System.out.println("!!!!!!!!Case non dispo en haut a droite!!!!!!!!");
		  					case_dispo2 = false; //il existe donc au moins une case libre
		  				}
		  			}
		  			
		  		}
		  		else
		  			case_dispo2 = false;

		  		//en bas a gauche
		  		if (  (depl_perso - terrain.getListeTerrain().get( plat.getCases()[ligne+1][colonne] ).getDeplTerrain()) >= 0 )
		  		{
		  			System.out.println("Case dispo en bas a gauche en terme de deplacement, equipier?");
		  			for (int i=0;i<this.getListeEquipe().size();i++)
		  			{
		  				/*System.out.println("\nTEST : BAS GAUCHE");
		  				System.out.println("LIG : " + this.getListeEquipe().get(i).ligne + " / COL : " + this.getListeEquipe().get(i).colonne);*/
		  				if ( (this.getListeEquipe().get(i).ligne==ligne+1)&&(this.getListeEquipe().get(i).colonne==colonne))
		  				{
		  					System.out.println("!!!!!!!!Case non dispo en bas a gauche!!!!!!!!");
		  					case_dispo5 = false;
		  				}
		  			}
		  			
		  		}
		  		else
		  			case_dispo5 = false;

		  		//en bas a droite
		  		if (  (depl_perso - terrain.getListeTerrain().get( plat.getCases()[ligne+1][colonne+1] ).getDeplTerrain()) >= 0 )
		  		{
		  			System.out.println("Case dispo en bas a droite en terme de deplacement, equipier?");
		  			for (int i=0;i<this.getListeEquipe().size();i++)
		  			{
		  				/*System.out.println("\nTEST : BAS DROITE");
		  				System.out.println("LIG : " + this.getListeEquipe().get(i).ligne + " / COL : " + this.getListeEquipe().get(i).colonne);*/
		  				if ( (this.getListeEquipe().get(i).ligne==ligne+1)&&(this.getListeEquipe().get(i).colonne==colonne+1))
		  				{
		  					System.out.println("!!!!!!!!Case non dispo en bas a droite!!!!!!!!"); 
		  					case_dispo6 = false;
		  				}
		  			}
		  			
		  		}
		  		else
		  			case_dispo6 = false;


	  		}
 		
	  	 //	if (case_dispo1==true) //case en haut a gauche
  		 //	{
  		 //		//PARTIE GRAPHIQUE , ajouter une surbrillance de la case montrant qu'on peut s'y deplacer
  		 //	}
	  	 //	if (case_dispo2==true) //case en haut a droite
  		 //	{
  		 //		//PARTIE GRAPHIQUE , ajouter une surbrillance de la case montrant qu'on peut s'y deplacer
  		 //	}
	  	 //	if (case_dispo3==true) //case a droite
  		 //	{
  		 //		//PARTIE GRAPHIQUE , ajouter une surbrillance de la case montrant qu'on peut s'y deplacer
  		 //	}
	  	 //	if (case_dispo4==true) //case a gauche
  		 //	{
  		 //		//PARTIE GRAPHIQUE , ajouter une surbrillance de la case montrant qu'on peut s'y deplacer
  		 //	}
	  	 //	if (case_dispo5==true) //case en bas a gauche
  		 //	{
  		 //		//PARTIE GRAPHIQUE , ajouter une surbrillance de la case montrant qu'on peut s'y deplacer
  		 //	}
	  	 //	if (case_dispo6==true) //case en bas a droite
  		 //	{
  		 //		//PARTIE GRAPHIQUE , ajouter une surbrillance de la case montrant qu'on peut s'y deplacer
  		 //	}
	 

	  		if ( (case_dispo1==false)&&(case_dispo2==false)&&(case_dispo3==false)&&(case_dispo4==false)&&(case_dispo5==false)&&(case_dispo6==false))
	  		{
	  			System.out.println("\nAUCUNE CASE DISPO, on passe son tour!\n");
	  			return false; 
	  		}
	  		else
	  			return true;
  		}
  		else
  			return true; //on se situe dans les extremites, on considere qu'il y a des cases libres


  	}


	void deplacer(Graphique graph, Plateau plat, GroupeTerrain terrain, int tour, Equipe eq_adverse)
	{
		int identifiant;
		Scanner sc = new Scanner(System.in);
		int ligne_prec, colonne_prec;
		int choix_lig, choix_col;
		boolean occupe=false;
		int cout_case;
		boolean case_dispo=true;
		int depl_initial;

		//afficher les joueurs de notre equipe pour pouvoir le selectionner
		this.afficheCarac(this.getListeEquipe());

		for (int i=0;i<this.getListeEquipe().size();i++)
		{
			System.out.println(i + ") Coord : " + this.getListeEquipe().get(i).ligne + " " + this.getListeEquipe().get(i).colonne);
		}

		identifiant = selectionUniteEquipe(this.getListeEquipe()); //on recupere l'identifiant du joueur qu'on joue
		//recuperer le nombre de deplacement du joueur et lorsqu'on sort de la boucle while on remet le nombre de deplacement a ca
		depl_initial = this.getListeEquipe().get(identifiant).getDepl(); 


		do
		{

			System.out.println("Pour passer votre tour, rester sur votre place (SAME puis SAME)\n");
			//on sauvegarde la ligne / colonne precedente en cas de probleme et pour effacer dans la matrice
			ligne_prec = this.getListeEquipe().get(identifiant).ligne; 
			colonne_prec = this.getListeEquipe().get(identifiant).colonne; 

			
			System.out.println("Vous avez " + this.getListeEquipe().get(identifiant).getDepl() + " points de deplacements restants");
			
			case_dispo = verifDeplPoss(identifiant,terrain,plat);

			if (case_dispo==false)
			{
					//remettre le bon nombre de deplacement 
					this.getListeEquipe().get(identifiant).setDeplInitialiser(depl_initial);
					return; //on passe son tour car pas de case dispo
			}	

			choix_lig = verifProchaineLig(identifiant);	//verif si on sort du tableau ou non niveau ligne
			choix_col = verifProchaineCol(choix_lig, identifiant);  //verif si on sort du tableau ou non niveau colonne
			occupe = verifProchaineCase(identifiant, plat); //verif si la prochaine case est occupe

			// cout_case = terrain.ListeTerrain.get(plat[ligne][colonne]).getDeplTerrain()
			cout_case = terrain.getListeTerrain().get( plat.getCases()[this.getListeEquipe().get(identifiant).ligne][this.getListeEquipe().get(identifiant).colonne] ).getDeplTerrain();

			System.out.print("Prochaine case : " + plat.getCases()[this.getListeEquipe().get(identifiant).ligne][this.getListeEquipe().get(identifiant).colonne]);
			System.out.print(" / Cout de la case :  " + cout_case);
			System.out.println(" / Coord : " + this.getListeEquipe().get(identifiant).ligne + " " + this.getListeEquipe().get(identifiant).colonne);
			

			if (this.getListeEquipe().get(identifiant).getDepl()-cout_case<0||occupe==true) //si probleme sur la prochaine ligne ou prochaine colonne, rester sur place
			{
				//si on a decide de rester sur place, on passe son tour
				if (choix_lig==3&&choix_col==3)
				{
					System.out.println("\nIl vous restait " + this.getListeEquipe().get(identifiant).getDepl() + " points de deplacements restants");
					System.out.println("Vous avez passe votre tour!\n");
					//remettre le bon nombre de deplacement 
					this.getListeEquipe().get(identifiant).setDeplInitialiser(depl_initial);
					return;
				}
				//si la case etait occupe, on peut peut-etre attaquer 
				else if (occupe==true)
				{
					//on verifie si c'est un partenaire ou non puis on attaque si non
					if (tour==1)
					{
						if(plat.getPosUnites()[this.getListeEquipe().get(identifiant).ligne][this.getListeEquipe().get(identifiant).colonne]==2)
						{
							System.out.println("On attaque l'equipe 2!");
							this.attaquer(identifiant,plat,terrain,eq_adverse, ligne_prec, colonne_prec);
							//on a attaque, du coup c'est au tour de l'autre equipe de jouer
							this.getListeEquipe().get(identifiant).setDeplEnlever(cout_case); //calculer le nouveau cout de deplacement
						}
					}
					else if (tour==2)
					{
						if(plat.getPosUnites()[this.getListeEquipe().get(identifiant).ligne][this.getListeEquipe().get(identifiant).colonne]==1)
						{
							System.out.println("On attaque l'equipe 1!");
							this.attaquer(identifiant,plat,terrain,eq_adverse, ligne_prec, colonne_prec);
							//on a attaque, du coup c'est au tour de l'autre equipe de jouer
							this.getListeEquipe().get(identifiant).setDeplEnlever(cout_case); //calculer le nouveau cout de deplacement
						}
					}
					
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
				this.getListeEquipe().get(identifiant).setDeplEnlever(cout_case);//calculer le nouveau cout de deplacement
				plat.affichagePlateau(graph); //afficher la nouvelle matrice
			}
			

		}while(this.getListeEquipe().get(identifiant).getDepl() > 0);

		//remettre le bon nombre de deplacement 
		this.getListeEquipe().get(identifiant).setDeplInitialiser(depl_initial);
  	}

	

}