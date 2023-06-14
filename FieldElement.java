import java.math.BigInteger;

public class FieldElement extends BaseFieldElement {
	//private BigInteger F;
	

	public FieldElement(BigInteger e){
		super(e);
	}

	public void setElement(BigInteger e) {
		this.element=e;
	}
	
	public BigInteger mul(BaseFieldElement b) {
		return this.element.multiply(b.element).mod(N);
	}
}
