/*
 * HSMgen grammar.
 * Copyright (c) 2013 G. Elian Gidoni
 */

grammar HSMgen;

/* Section rules
 */

init
    :   constantStms? fsmSection
        EOF
    ;

constantStms
    :   (constant ';')+
    ;

constant
    :   CONSTANT '=' NUMBER
    ;

fsmSection
    :    (fsmSectionStm constantStms?)+
    ;

fsmSectionStm
    :   'fsm-info' IDENTIFIER fsmSubState? '{' fsmStateStm+ '}'
    ;

fsmSubState
    :   '(' IDENTIFIER (',' IDENTIFIER)* ')'
    ;

fsmStateStm
    :   IDENTIFIER '{' fsmEventStm* '}'
    ;

fsmEventStm
    :   event=(IDENTIFIER | NUMBER | CONSTANT) '-' '>' state=IDENTIFIER ';'
    ;

/* LEXER
 */

CONSTANT
    :   [A-Z] [A-Z0-9_]*
    ;

IDENTIFIER
    :   [a-z] [a-zA-Z0-9_]*
    ;

NUMBER
    :   [0-9]+
    ;

STRING
    :   '"' ('\\"' | .)*? '"'
    ;

WS
    :   [ \t\r\n]+ -> skip
    ;

COMMENT
    :   '#' ~[\r\n]* '\r'? '\n' -> skip
    ;

/* Keywords */
/* none */
