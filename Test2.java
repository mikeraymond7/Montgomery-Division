import java.math.BigInteger;
class Test2 {
		// use numbers from wiki page
	 public static void main(String[] args) {
		BigInteger x = new BigInteger("15");
		BigInteger y = new BigInteger("7");

		MontFieldElement m1 = new MontFieldElement(x);
		MontFieldElement m2 = new MontFieldElement(y);


		FieldElement f1 = new FieldElement(x);
		FieldElement f2 = new FieldElement(y);
		
		System.out.println("m1 original: " + m1.getElement());
		System.out.println("m2 original: " + m2.getElement());

		System.out.println("m1 in Mont Form: " + m1.toMont());
		System.out.println("m2 in Mont Form: " + m2.toMont());
		
		MontFieldElement product = new MontFieldElement(m1.mul(m2),true);
		System.out.println("m1 in reduced from Mont Form: " + m1.fromMont());
		System.out.println("m2 in reduced from Mont Form: " + m2.fromMont());

		System.out.println("Product before reduction: " + product.getElement());
		System.out.println("Product after reduction: " + product.fromMont());
		System.out.println("Product from field elem: " + f1.mul(f2));
		if (f1.mul(f2).compareTo(product.getElement()) != 0) {
			System.out.println("Values not equal");
		}
		else {
			System.out.println("Values equal");
		}


	}
}
