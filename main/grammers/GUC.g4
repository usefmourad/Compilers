grammar GUC;
start: (E_MAIL | ID)+ EOF;   // EOF means end of file and its predifibed in ANTLR.

E_MAIL: USERNAME '@' (SUBDOMAIN '.')? DOMAIN; // ? --> means that the (SUBDOMAIN '.') is optional.
ID: BATCH '-' APPNUMBER;
WS: [ \r\t\n]+ -> skip; // white spaces and skips --> can be skipped.


fragment USERNAME: [A-Za-z-]+ '.' [A-Za-z-]+ ;  // [S]+ means ONE or more occurancess of what is S.
fragment SUBDOMAIN: STUDENT | BERLIN;
fragment STUDENT options { caseInsensitive=true; }:'student';
fragment BERLIN options { caseInsensitive=true; }: 'Berlin';
fragment DOMAIN : [Gg][Uu][Cc]'.'[Ee][Dd][Uu]'.'[Ee][Gg];
fragment BATCH: NONZERO? DIGIT;
fragment APPNUMBER: NONZERO? DIGIT DIGIT DIGIT DIGIT;
fragment NONZERO: [1-9];
fragment DIGIT: [0-9];