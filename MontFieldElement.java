import java.math.BigInteger;

public class MontFieldElement extends BaseFieldElement {

	/** N and R are chosen such that R > N and gcd(R,N) = 1 */

	//protected final BigInteger N = new BigInteger("21888242871839275222246405745257275088548364400416034343698204186575808495617"); 
	protected final BigInteger R = new BigInteger("28948022309329048855892746252171976963317496166410141009864396001978282409984");

/*
	protected final BigInteger N = new BigInteger("17"); 
	protected final BigInteger R = new BigInteger("100");
*/
	/** Rp and Np are constants calculated based on N and R*/

	protected final BigInteger Rp = new BigInteger("17773755579518009376303681366703133516854333631346829854655645366227550102839"); 
	protected final BigInteger Np = new BigInteger("23506458515151433264464083090194480587220214185280767566518238411631651454975");
	//protected final BigInteger Np = R.multiply(Rp).subtract(BigInteger.ONE).divide(N);
/*
	protected final BigInteger Rp = new BigInteger("8"); 
	protected final BigInteger Np = new BigInteger("47");
*/
	protected boolean inMont = false;

	/** Constructor */
	public MontFieldElement(BigInteger e) { 
		super(e);
	}

	/** Constructor for internal functions */
	protected MontFieldElement(BigInteger e, boolean in_mont){
		super(e);
		inMont = in_mont;
	}

	/** Set new value to element */
	public void setElement(BigInteger e){
		//element = e.mod(N); // If in bounds, mod does nothing
		element = e;
	}

	/** Get field order (N) */
	public BigInteger getN(){
		return N;
	}

	/** Get the current element */
	public BigInteger getElement(){
		return element;
	}

	/** Multiply two MontFieldElement objects together and return a new one */
	public MontFieldElement mul(BaseFieldElement b) {
		System.out.println("Np = " + Np);
		if (!(b instanceof MontFieldElement)) {
			throw new IllegalArgumentException("Multiplication can only be performed with two MontFieldElement instances.");
		}
		if (!this.inMont) {
			throw new RuntimeException(String.format(
				"Cannot multiply with Montgomery form if primary element is not in Montgomery Form"));
		}
		if (!((MontFieldElement)b).inMont) {
			throw new RuntimeException(String.format(
				"Cannot multiply with Montgomery form if secondary element is not in Montgomery Form"));
		}

// Would it be faster to simply return the BigInteger and then set_element() to an existing MontFieldElement?

		//return new MontFieldElement(reduction(this.element.multiply(b.element)),true);
		return new MontFieldElement(reduction(this.element.multiply(b.element)),true);
	}

	/** Converts element to Montgomery Form */ 
	public BigInteger toMont() {
		if (inMont) {
			throw new RuntimeException(
				String.format("Element %s is already in Montgomery Form", element.toString()));
		}

		inMont = true; 
		this.element = this.element.multiply(R).mod(N);
		return this.element;
	}
	
	
	/** Convert from Montgomery form back to regular form */
	protected BigInteger fromMont() {
		if (!inMont) {
			throw new RuntimeException(String.format(
				"Cannot convert element from Montgomery form if value is not in Montgomery Form", 
				element.toString()));
		}
		inMont = false; 
		element = reduction(element);
		return element;
	}

	/** Reduction for chained multiplications or conversion back to standard form */
	protected BigInteger reduction(BigInteger T) { 
		BigInteger m = ((T.mod(R)).multiply(Np)).mod(R); // use bitshifting
		BigInteger t = (T.add(m.multiply(N))).divide(R);

		if (t.compareTo(N) >= 0) {
			return t.subtract(N);
		}
		return t;
	}

}
