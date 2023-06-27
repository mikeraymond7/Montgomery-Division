import java.math.BigInteger;
class Test_all {
		// use numbers from wiki page
	 public static void main(String[] args) {

		BigInteger x = new BigInteger("3237060713110073007148766886699519604441026697154840321303454275246551388678908931972014115229134636887179609218980194941195591504909210950881523864482831206308773673009960917501977503896521067960576383840675682767922186426197561618380943384761704705816458520363050428875758915410658086075523991239303855219143333896683424206849747865645694948561760353263220580778056593310261927084603141502585928641771167259436037184618573575983511523340639947855803707216654176622128812031049459145511400081473963578867676698200428287937085882522470310920711555402247510310642532098840992381846882467489498721336450133889385773");
		BigInteger y = new BigInteger("3237060713110073007148766886699519604441026697154840321303454275246551388678908931972014115229134636887179609218980194941195591504909210950881523864482831206308773673009960917501977503896521067960576383840675682767922186426197561618380943384761704705816458520363050428875758915410658086075523991239303855219143333896683424206849747865645694948561760353263220580778056593310261927084603141502585928641771167259436037184618573575983511523340639947855803707216654176622128812031049459145511400081473963578867676698200428287937085882522470310920711955802247560310542132098840992381846882467489498721336450133889385773");
/*
		BigInteger x = new BigInteger("15");
		BigInteger y = new BigInteger("7");
*/
		MontFieldElement m1 = new MontFieldElement(x);
		MontFieldElement m2 = new MontFieldElement(y);


		FieldElement f1 = new FieldElement(x);
		FieldElement f2 = new FieldElement(y);
		BigInteger real = f1.mul(f2);

		System.out.print(String.format("Expecting (%s * %s) mod %s", x, y, m1.getN()));
		System.out.println(" = " + real);

		System.out.println("m1 in Mont Form: " + m1.toMont());
		System.out.println("m2 in Mont Form: " + m2.toMont());
		
		MontFieldElement product = new MontFieldElement(m1.mul(m2), true);
		m1.fromMont();
		m2.fromMont();		
		System.out.println("m1 after fromMont = " + m1.getElement());

		System.out.println("Product in Mont Form: " + product.getElement());
		System.out.println("Actual Montgomery Product: " + product.fromMont());
		System.out.println("Real Product: " + real);
		if (product.getElement().compareTo(real) == 0){
			System.out.println("\nMontgomery and BigInteger values are equivalent");
		}
		else{
			System.out.println("\nMontgomery and BigInteger values are NOT equivalent");
		}
		
		System.out.println("\n\nTesting looping");

		int bound = 1000000;
		long startM = System.nanoTime();
		m1.toMont();
		m2.toMont();
		//long startMMul = System.nanoTime();
		for (int i = 0; i < bound; i++){
			m1.setElement(m1.mul(m2));
		}	
		//long endMMul = System.nanoTime();
		m1.fromMont();
		long endM = System.nanoTime();

		long startF = System.nanoTime();
		for (int i = 0; i < bound; i++){
			f1.setElement(f1.mul(f2));
		}	
		long endF = System.nanoTime();

		System.out.println("m1 = " + m1.getElement());
		System.out.println("f1 = " + f1.getElement());
		if (f1.getElement().compareTo(m1.getElement()) == 0) {
			 System.out.println("\nm1 and f1 are equal");
		} else { System.out.println("\nm1 and f1 not equal"); } 
		
		System.out.println("\nRuntime Statistics: ");
		
		System.out.println("Total Montgomery Time = " + (endM-startM)/1000000 + "ms");
		//System.out.println("Montgomery Multiplication Time = " + (endMMul-startMMul)/1000000 + "ms");
		System.out.println("Total Standard BigInteger Time = " + (endF-startF)/1000000 + "ms");
		System.out.println((float)(endM-startM)/(float)(endF-startF) + "x difference");

	}
}
