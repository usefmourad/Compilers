
/**
 *
 *
 * @name
 * @id
 * @labNumber
 */


grammar Task10;

@members {
   int count(String seq) {
           int count = 0;
           int prev = -1;
           boolean flag = false;
           String[] split = seq.split(",");

           for (int i = 0; i < split.length; i++) {
               int current = Integer.parseInt(split[i]);

               if (current != prev) {
                   flag = false;
               }

               if (current == prev && !flag) {
                   count++;
                   flag = true;
               }
               prev = current;
           }

           return count;
       }



}


s returns [int val]: seq { $val = count($seq.text); };

seq: (INT COMMA)* INT?;

INT: [0-9]+;
COMMA: ',';
WS: [ \t\r\n]+ -> skip;

