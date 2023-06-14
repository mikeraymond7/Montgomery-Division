# Montgomery Modular Multiplication in Java  
## Problem Statement  
In modern cryptology, elliptical curves are utilized to create secure encryptions, hashes, and proofs. When these algorithms are implemented, they must use modulus after each multiplication in order to ensure the returned value is within the bounds of the selected curve.  
## Solution  
Montgomery Modular Multiplication eliminates the need for constant expensive divisions utilizing modulus. Rather, once an integer is in Montgomery Form, carefully selected values ensure that any division or modulus can be accomplished using bitshifting. In large looping scenarios where a number must be multiplied hundreds or thousands of times, this form is theorized to expedite the process.  
## Equations / Properties
