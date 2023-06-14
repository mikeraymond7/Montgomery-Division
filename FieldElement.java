import java.math.BigInteger;

public class FieldElement extends BaseFieldElement {
	//private BigInteger F;
	

	public FieldElement(BigInteger e){
		super(e);
	}

	public void setElement(BigInteger e) {
		this.element=e;
	}
	
	public FieldElement mul(BaseFieldElement b) {
		return new FieldElement(this.element.multiply(b.element).mod(N));
	}
}
