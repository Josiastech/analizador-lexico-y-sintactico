import java.lang.System;
class Yytoken{
	Yytoken(int numToken, String text, String compo, int line, int charBegin){
		//Contador para el número de tokens recononocidos
		m_numToken = numToken;
		//String del token reconocido
		m_text = new String(text);
		//Tipo de componente Lexico encomtrado
		m_compo = compo; 
		//Número de linea
		m_line = line;
		// Columna donde empieza el primer caracter del Token
		m_charBegin = charBegin;
	}
	//Metodos de los atributos de la Clase
	public int m_numToken;
	public String m_text;
	public String m_compo;
	public int m_line;
	public int m_charBegin;
	//Metodo que devuelve los datos necesarios que escribiremos en un archivo de Salida
	public String toString(){
		return "Token #"+m_numToken+":"+m_text+"C.Lexico:"+m_compo+"Line:"+m_line+"Column:"+m_charBegin;
	}
}
%%
//Permite cambiar el nombre de la funcion de yylex que reconoce los tokens (next token)
%function nextToken
//Permite cambiar el nombre de la clase del analizador lexico, desde Yylex.
%class Analizador
//Se define in integer para el contador de tokens
%{
	private int contador;
%}
//Con init inicializamos variables
%init{
	contador = 0;
%init}
//Fin del fichero
%eof{
%eof}
//Activa el contador de lineas, almacena en la variable entera yyline el indice de la primera linea del token
//reconocido
%line
//Activa el contador de caracteres, por defecto desactivado, almacena en la variable entera yychar 
//el indice del primer caracter del token reconocido
%char
/*
* Expresiones Regulares Acciones
*/
// Definimos los patrones para los tipos de datos a reconocer requeridos
EXP_ALPHA = [A-Za-z]
EXP_DIGITO = [0-9]
EXP_ALPHA_NUMERIC = {EXP_ALPHA}|{EXP_DIGITO}
NUMERO = ({EXP_DIGITO})*
NUMERO_ENTERO = "0"|{EXP_ALPHA}|{EXP_DIGITO}*
EXP_OCTAL = [0-8]
NUMERO_OCTAL = "0"({EXP_OCTAL})+
EXP_HEX = [0-9a-fA-F]
NUMERO_HEX = "0x"({EXP_HEX})+
IDENTIFICADOR = {EXP_ALPHA}({EXP_ALPHA_NUMERIC})*
%%
/*
Expresiones a realizar cuando encontramos un token que concuerda con el patron
en cada caso devolvemos una instancia de la clase 'yytoken' con los datos requeridos 
para escribirlos en un archivo de salida
Contador
yytext
componente lexico
yyline
yychar
*/
{NUMERO_HEX} { contador++
	return new Yytoken(contador, yytext(), "Hexadecimal", yyline, yychar);
	}
{NUMERO_ENTERO} { contador++
	return new Yytoken(contador, yytext(),"Entero",yyline, yychar);
}
{NUMERO_OCTAL} { contador++
	return new Yytoken(contador, yytext(),"Octal",yyline,yychar);
}