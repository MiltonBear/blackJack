/**
 * 
 */
package ca.csf.dfc.classe;

/**
 * @author administrateur
 *
 */
public class Dealer {
	private Hand m_dealersHand;
	
	public Hand getHand()
	{
		return this.m_dealersHand;
	}
	public Dealer()
	{
		this.m_dealersHand=new Hand();
	}


}
