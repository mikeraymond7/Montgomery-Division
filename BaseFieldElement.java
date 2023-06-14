import java.math.BigInteger;

abstract class BaseFieldElement {
	protected BigInteger N = new BigInteger("21888242871839275222246405745257275088548364400416034343698204186575808495617");
	//protected BigInteger N = new BigInteger("17");
	protected BigInteger element;

	public BaseFieldElement(BigInteger e){
		this.element = e;
	}

	/** Returns the current Field Element Value */
	public BigInteger getElement(){return this.element;}

	/** Returns the Field Order */
	public BigInteger getN(){return N;}
	
	/** Resets the Field Order */
	public void setN(BigInteger n) {N=n;}

	/** Updates and resets the current Field Element Value */
	abstract void setElement(BigInteger e);

	/** Multiplies BaseFieldElement elements together, ensures 
		they are within the field order, and returns the outcome */
	abstract BigInteger mul(BaseFieldElement b);

}
