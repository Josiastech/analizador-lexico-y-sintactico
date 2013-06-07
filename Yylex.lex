                                                                     
                                                                     
                                                                     
                                             
package jlexcup;
import java_cup.runtime.Symbol;
import javax.swing.*;
import java.util.*;
import  analizador_lexico.*;
%%
%cup
%ignorecase
%type java_cup.runtime.Symbol
%class scanner
%implements java_cup.runtime.Scanner
%class Yylex
%{String literal;
  String temp_include;
%}
%{
int columna=1;
%}
%public
%cup
%line
%char
%full
%unicode
%ignorecase
%{
public void PrintToken(String str){
System.out.println(str);
public String sourceFilename;
 StringBuffer string = new StringBuffer();
  int ultimoEstado = 0;
 public void init(){};
%}
%eofval{
{
return new Symbol(sym.EOF, null); }
%eofval}
ALFABETICO= [a-zA-Z]+
NUMERICO_E =[0-9]+
ALFANUMERICO = [a-zA-Z0-9_]+
NUMERICO_D = [0-9]+\.[0-9]+
H = H[1-6]
NEW_LINE=(\n|\r)
WHITE_SPACE=([\ |\t|\f])
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
%%
[\n] { yychar=0;}
[ \t\r\n\f] { /*Ingnora los Espacios. */ }
\' { /* ignora apostrofos. */ }
 <YYINITIAL> {WHITE_SPACE}  {/*no hace nada, aumenta una columna*/yychar++; }
<YYINITIAL> {NEW_LINE}     {yychar=0; yyline=0}
<YYINITIAL>")"		{  return new Symbol(sym.PARENTESIS_C, yyline, yychar,  yytext());}
<YYINITIAL>";"		{  return new Symbol(sym.PUNTOCOMA, yyline, yychar,  yytext());}
<YYINITIAL>":"		{  return new Symbol(sym.DOSPUNTOS, yyline, yychar,  yytext());}
<YYINITIAL>","		{  return new Symbol(sym.COMA, yyline, yychar,  yytext());}
<YYINITIAL>"{"		{  return new Symbol(sym.LLAVE_A, yyline, yychar,  yytext());}
<YYINITIAL>"}"		{  return new Symbol(sym.LLAVE_C, yyline, yychar,  yytext());}
<YYINITIAL>"["		{  return new Symbol(sym.CORCHETE_A, yyline, yychar,  yytext());}
<YYINITIAL>"]"		{  return new Symbol(sym.CORCHETE_C, yyline, yychar,  yytext());}
<YYINITIAL>">"		{  return new Symbol(sym.MAYORQ, yyline, yychar,  yytext());}
<YYINITIAL>"<"		{  return new Symbol(sym.MENORQ, yyline, yychar,  yytext());}
<YYINITIAL>"\""         { return new Symbol(sym.COMILLA, yyline, yychar,  yytext());}
<YYINITIAL>"ADRESS"         { return new Symbol(sym.ADRESS, yyline, yychar,  yytext());}
<YYINITIAL> "A"|"ADDRESS"|"BASE"|"BGSOUND"|"BIG"|"BLINK"|"BLOCKQUOTE"|"BODY"|"BR"|"CAPTION"|"CENTER"|"CITE"|"CODE"|"DD"|"DFN"|"DIR" { return new Symbol(sym.ETIQUETA, yyline, yychar,  yytext());}
<YYINITIAL> "HTML"|"I"|"IMG"|"INPUT"|"ISINDES"|"ISMAP"|"KBD"|"LI"|"LISTING"|"LIT"|"MARQUEE"|"MENU"|"META"|"NEXTID"|"NOBR"|"OL" { return new Symbol(sym.ETIQUETA, yyline, yychar,  yytext());}
<YYINITIAL> "DL"|"DT"|"EM"|"EMBED"|"FONT"|"FORM"|"HEAD"|"HR"|"TR"|"TT"|"UL"|"VAR"|"WBR"|"XMP" { return new Symbol(sym.ETIQUETA, yyline, yychar,  yytext());}
<YYINITIAL> "OPTION"|"P"|"PLAINTEXT"|"PRE"|"S"|"SAMP"|"SELECT"|"SMALL"|"STRONG"|"SUB"|"SUP"|"TABLE"|"TD"|"TEXTAREA"|"TH"|"TITLE"|{H} { return new Symbol(sym.ETIQUETA, yyline, yychar,  yytext());}
<YYINITIAL> "HREF"|"LOOP"|"SRC"|"LOOP"|"ALT"|"BGCOLOR"|"BACKGROUND"|"TEXT"|"LINK"|"VLINK"|"ALINK"|"ALIGN"|"SIZE"|"WIDTH"|"COLOR"|"FACE" { return new Symbol(sym.ATRIBUTO, yyline, yychar,  yytext());}
<YYINITIAL> "HEIGHT"|"WIDTH"|"BORDER"|"CELLPADDING"|"CELLSPACING"|"COLSPAN"|"ROWSPAN"|"summary" { return new Symbol(sym.ATRIBUTO, yyline, yychar,  yytext());}
{ALFABETICO}  {  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
<YYINITIAL>{NUMERICO_D}|{NUMERICO_E}   {      return new Symbol(sym.NUMERICO, yyline, yychar,  yytext());}
<YYINITIAL>{ALFANUMERICO}    {  return new Symbol(sym.ALFANUMERICO, yyline, yychar,  yytext());}
}
%}
%eofval{
System.out.println(“Fin del archivo”);
return null;
%eofval}
NUMTARJ=([0-9])+
MES = ([0-9])+
AÑO = ([0-9])+
COD_CLI=([0-9])+
VARIABLES= ({NOMBRE1})+
NOMBRE1=([a-z])+
NUMEROCASA= ([0-9])+
AVENIDA= ([0-9])* ([a-z])+
CALLE= ([0-9])* ([a-z])+
ZONA= ([0-9])* ([a-z])+
CIUDAD= ([a-z])+
PAIS = ([GUATEMALA])
NIT = ([0-9])+
TELEFONO = ([0-9])
%%
“(” {PrintToken(“Abre_parentesis”);return new Symbol(sym.PARENA);}
“)” {PrintToken(“Cierra_parentesis”);return new Symbol(sym.PARENC);}
[\t\r\f] {}
[\n] {}
” ” {}
{PrintToken(“Caracter_no_valido”);}