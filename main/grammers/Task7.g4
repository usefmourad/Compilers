/**
 *
  * @name Youssef Atef Mourad
  * @id 49-5215
    @labNumber 18 */


grammar Task7;



test: (ONE | ZERO | ERROR) + EOF;


ONE: '000' | '011' | '111';
ZERO: '001' | '010' | '100' | '101' | '110';
ERROR: '0' | '1' | '00' | '01' | '10' | '11';
