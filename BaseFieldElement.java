import java.math.BigInteger;

abstract class BaseFieldElement {
	protected BigInteger N = new BigInteger("32317006071311007300714876688669951960444102669715484032130345427524655138867890893197201411522913463688717960921898019494119559150490921095088152386448283120630877367300996091750197750389652106796057638384067568276792218642619756161838094338476170470581645852036305042887575891541065808607552399123930385521914333389668342420684974786564569494856176035326322058077805659331026192708460314150258592864177116725943603718461857357598351152334063994785580370721665417662212881203104945914551140008147396357886767669820042828793708588252247031092071155540224751031064253209884099238184688246467489498721336450133889385773");
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
