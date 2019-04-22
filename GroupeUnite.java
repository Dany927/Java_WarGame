
import java.util.ArrayList;

public class GroupeUnite
{
	private ArrayList<Unite> liste_unite = new ArrayList<Unite>();

	public ArrayList<Unite> getListeUnite() { return liste_unite; }

	void ajoutUnite(String nom, int pv, int p_att, int p_def, int depl,int vision)
	{
		Unite perso = new Unite(nom, pv, p_att, p_def, depl, vision);
		liste_unite.add(perso);
	}

}