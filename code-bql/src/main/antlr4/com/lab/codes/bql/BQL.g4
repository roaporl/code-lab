grammar BQL;

@parser::members {

    private static enum KeyType {
      STRING_LITERAL,
      IDENT,
      STRING_LITERAL_AND_IDENT
    }
}

// ***************** parser rules:
program: expr;

expr: ID
    | 'not' expr
    | expr 'and' expr
    | expr 'or' expr
    ;

ID: [a-zA-Z_][a-zA-Z_0-9]*;
WS: [ \t\n\r\f]+ -> skip;
ERROR: .;

