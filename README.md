# Montgomery Modular Multiplication in Java  
## Problem Statement  
In modern cryptology, elliptical curves are utilized to create secure encryptions, hashes, and proofs. When these algorithms are implemented, they must use modulus after each multiplication in order to ensure the returned value is within the bounds of the selected curve.  
## Solution  
Montgomery Modular Multiplication eliminates the need for constant expensive divisions utilizing modulus. Rather, once an integer is in Montgomery Form, carefully selected values ensure that any division or modulus can be accomplished using bitshifting. In large looping scenarios where a number must be multiplied hundreds or thousands of times, this form is theorized to expedite the process.  
## Explanation
 - N --> In this situation, is the elliptical curve field order of your choice (BN128 was chosen)
 - R --> Chosen specifically such that R > N, gcd(R,N) == 1, and R is a power of 2
 - Rp --> R.modInverse(N)
 - Np --> Satisfies the equation: N\*Np - R\*Rp == 1  
## Reduction Algorithm
`
m -> ((T%R) * Np) mod R  
t -> (T + mN) / R  
if t >= N:  
  return t - N
return t
`
