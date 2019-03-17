/**
 * 
 */
package ca.csf.dfc.classe;

/**
 * @author administrateur
 *
 */
public class Carte {

	public static String[] CHOIX_ATOUT = { "pique", "trefle", "coeur", "carreau" };
	public static String[] CHOIX_FACE = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K" };

	private String m_Face;
	private String m_Atout;
	private int m_Valeur;
	private boolean m_Visible;

	public void setVisible() {
		this.m_Visible = true;
	}

	public Boolean isVisible() {
		return this.m_Visible;
	}

	public void setInVisible() {
		this.m_Visible = false;
	}

	public Carte(String p_Face, String p_Atout, int p_Valeur) {
		this.m_Face = p_Face;
		this.m_Atout = p_Atout;
		this.m_Valeur = p_Valeur;
		this.m_Visible=true;

	}

	/*
	 * public void setValeur(int p_Valeur) { this.m_Valeur=p_Valeur; }
	 */
	public int getCarteValue() {
		return this.m_Valeur;
	}

	/*
	 * public void setFace(String p_Face) { this.m_Face=p_Face; }
	 */
	public String getFace() {
		return this.m_Face;
	}

	/*
	 * public void setAtout(String p_Atout) { this.m_Atout=p_Atout; }
	 */
	public String getAtout() {
		return this.m_Atout;
	}

	public String toString() {
		if(this.isVisible())
		{
		return this.m_Face+ " de "+this.m_Atout;
		}
		else
		{
			return "blank";
		}
	}
}
