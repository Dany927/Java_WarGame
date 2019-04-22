
public class Unite
{
	private int pv;
	private int p_att;
	private int p_def;
	private int depl;
	private int vision;
	private String nom;
	public int ligne;
	public int colonne;

	Unite(String nom, int pv, int p_att, int p_def, int depl,int vision)
	{
		this.nom = nom;
		this.pv = pv;
		this.p_att = p_att;
		this.p_def = p_def;
		this.depl = depl;
		this.vision = vision;
	}

	public String getNom() { return this.nom; }

	public int getPV() { return this.pv; }

	public int getPAtt() { return this.p_att; }

	public int getPDef() { return this.p_def; }

	public int getDepl() { return this.depl; }

	public int getVis() { return this.vision; }

	public int setLigne(int value)
	{
		ligne+=value;
		return ligne;
	}

  	/*
  	void Attaquer(Unite ennemie, double bonus_défense){
    int PD=ennemie.Défense;
    int Dbrut=0;
    int D=0;

    PD=(int)(PD*(1.0d+bonus_défense));
    Dbrut=this.Attaque-PD;
    D=Dbrut+(int)(-0.5*this.Attaque+Math.random()*(0.5*this.Attaque));
    if(D>=0)
      ennemie.PV-=D;
    else
      ennemie.PV+=D;
    if(ennemie.PV<0)
      ennemie.PV=0;
  }*/


}