# Montgomery Modular Multiplication in Java  

## Problem Statement  
In modern cryptology, elliptical curves are utilized to create secure encryptions, hashes, and proofs. When these algorithms are implemented, they must use modulus after each multiplication in order to ensure the returned value is within the bounds of the selected curve.  

## Solution  
Montgomery Modular Multiplication eliminates the need for constant expensive divisions utilizing modulus. Rather, once an integer is in Montgomery Form, carefully selected values ensure that any division or modulus can be accomplished using bitshifting. In large looping scenarios where a number must be multiplied hundreds or thousands of times, this form is theorized to expedite the process. A number of constants can be pre-calculated and utilized throughout each multiplication.  

## Explanation of Variables
 - N --> In this situation, is the elliptical curve field order of your choice (BN128 was chosen)
 - R --> Chosen specifically such that R > N, gcd(R,N) == 1, and R is a power of 2
 - Rp --> `R.modInverse(N)`
 - Np --> Satisfies the equation: N\*Np - R\*Rp == 1  

## Conversion to Montgomery Form  
Given *a* is an integer, *a*<sub>mont</sub> = (*a*R) % N  

## Reduction Algorithm
```
def reduction(T):
  m -> ((T%R) * Np) mod R  
  t -> (T + mN) / R  
  if t >= N:  
    return t - N
  return t
```  

## Modular Multiplication Algorithm  
```
def mul(m1, m2):  
  return reduction(m1*m2)
```

## Conversion back to normal form
Given *a*<sub>mont</sub> is an integer, *a* = reduction(*a*<sub>mont</sub>)

## Conclusions  
In specific scenarios, this algorithm is over 2 times faster than the standard implementation. When large numbers are multiplied together and actually exceed the prime number N, then Montgomery Multiplication is more effective. However, in a scenario where N is near 2<sup>256</sup> and multipliers are less than 2<sup>4</sup> the effciency brought onby Montgomery Multiplication is lost due to the automatic use and multiplications of constants larger than N whereas standard BigInteger multiplication and modulus operations will not conduct the division operations when the result is less than the modulus. Therefore, the BigInteger version will remain at the bit-level of the actual result rather than the bit-level of N.  
If multiplications are conducted and modulus is needed, this Montgomery algorithm is far more effective than standard BigInteger Modular Multiplication.
