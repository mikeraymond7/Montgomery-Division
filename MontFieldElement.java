import java.math.BigInteger;

public class MontFieldElement extends BaseFieldElement {

	/** N and R are chosen such that R > N and gcd(R,N) = 1 */

	protected final BigInteger R = new BigInteger("64634012142622014601429753377339903920888205339430968064260690855049310277735781786394402823045826927377435921843796038988239118300981842190176304772896566241261754734601992183500395500779304213592115276768135136553584437285239512323676188676952340941163291704072610085775151783082131617215104798247860771043828666779336684841369949573129138989712352070652644116155611318662052385416920628300517185728354233451887207436923714715196702304603291808807395226466574462454251369421640419450314203453862646939357085161313395870091994536705997276431050332778874671087204270866459209290636957209904296387111707222119192461312");

	/** Rp and Np are constants calculated based on N and R*/

	protected final BigInteger Rp = new BigInteger("23409047373787533913086502390879934333670816436714705163436817516534143541888456135157166511695013486674993823494692289922415893084443395676215770223803993471049642411797620014010430009323922210872466053029032154382922948229213344013921966686051667079102529816651597838491023375204662065998945086845672830918477523333215228380502237598940675719180543893150306220522570959999432238509107530198691755099642291611029616030625129432316179183806856713060052999424589173027521954972653138766026981763972321349282625845753029468708714843642558852078293444133191635921634591080189670895127241956899607575380335956137770006900"); 
	protected final BigInteger Np = new BigInteger("46818094747575067826173004781759868667341632873429410326873635033068287083776912270314333023390026973349987646989384579844831786168886791352431540447607986942099284823595240028020860018647844421744932106058064308765845896458426688027843933372103334158205059633303195676982046750409324131997890173691345661836955046666430456761004475197881351438361087786300612441045141919998864477018215060397383510199284583222059232061250258864632358367566748889130344307653232992798907014383381313645375586391815022091000624319937965096087967730750185665292024596050293280518157949854392359504317477435377184967912161312732737596763");
	//protected final BigInteger Np = R.multiply(Rp).subtract(BigInteger.ONE).divide(N);
	protected final BigInteger Rm1 = R.subtract(BigInteger.ONE);

	protected boolean inMont = false;

	/** Constructor */
	public MontFieldElement(BigInteger e) { 
		super(e);
	}

	/** Constructor for internal functions */
	public MontFieldElement(BigInteger e, boolean in_mont){
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
	public BigInteger mul(BaseFieldElement b) {
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

		return reduction(this.element.multiply(b.element));
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
		BigInteger m = (T.and(Rm1).multiply(Np)).and(Rm1); // use bitshifting
		BigInteger t = (T.add(m.multiply(N))).shiftRight(R.bitLength() - 1);

		if (t.compareTo(N) >= 0) {
			return t.subtract(N);
		}
		return t;
	}

}
