/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013 G. Elian Gidoni
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * HSMgen grammar.
 *
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
