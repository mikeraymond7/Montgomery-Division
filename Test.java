import java.math.BigInteger;
class Test {
		// use numbers from wiki page
	 public static void main(String[] args) {

		BigInteger x = new BigInteger("21848241876839275222246405745257275088548364400416034343698204186575808495617");
		BigInteger y = new BigInteger("16");
/*
		BigInteger x = new BigInteger("15");
		BigInteger y = new BigInteger("7");
*/
		MontFieldElement m1 = new MontFieldElement(x);
		MontFieldElement m2 = new MontFieldElement(y);


		FieldElement f1 = new FieldElement(x);
		FieldElement f2 = new FieldElement(y);
		BigInteger real = f1.mul(f2).getElement();

		System.out.print(String.format("Expecting (%s * %s) mod %s", x, y, m1.getN()));
		System.out.println(" = " + real);

		System.out.println("m1 in Mont Form: " + m1.toMont());
		System.out.println("m2 in Mont Form: " + m2.toMont());
		
		MontFieldElement product = m1.mul(m2);
		m1.fromMont();
		m2.fromMont();		
		System.out.println("m1 after fromMont = " + m1.getElement());

		System.out.println("Product in Mont Form: " + product.getElement());
		System.out.println("Actual Montgomery Product: " + product.fromMont());
		System.out.println("Real Product: " + real);
		if (product.getElement().compareTo(real) == 0){
			System.out.println("Montgomery and BigInteger values are equivalent");
		}
		else{
			System.out.println("Montgomery and BigInteger values are NOT equivalent");
		}
		


		System.out.println("\n\nTesting looping");

		long startM = System.nanoTime();
		m1.toMont();
		m2.toMont();
		long startMMul = System.nanoTime();
		for (int i = 0; i < 1000000; i++){
			m1 = m1.mul(m2);
		}	
		long endMMul = System.nanoTime();
		m1.fromMont();
		long endM = System.nanoTime();

		long startF = System.nanoTime();
		for (int i = 0; i < 1000000; i++){
			f1 = f1.mul(f2);
		}	
		long endF = System.nanoTime();

		System.out.println("m1 = " + m1.getElement());
		System.out.println("f1 = " + f1.getElement());
		
		System.out.println("\nRuntime Statistics: ");

		
		System.out.println("Total Montgomery Time = " + (endM-startM)/1000000 + "ms");
		System.out.println("Montgomery Multiplication Time = " + (endMMul-startMMul)/1000000 + "ms");
		System.out.println("Total Standard BigInteger Time = " + (endF-startF)/1000000 + "ms");
		

	}
}
