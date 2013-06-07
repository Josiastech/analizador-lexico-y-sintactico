package analizador;
import java_cup.runtime.*;
import javax.swing.*;
import java.util.*;
//import  analizador_lexico.*;
public class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;
String literal;
  String temp_include;

int columna=1;

  public String sourceFilename;
 StringBuffer string = new StringBuffer();
  int ultimoEstado = 0;
 public void init(){};
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NOT_ACCEPT,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR,
		/* 113 */ YY_NO_ANCHOR,
		/* 114 */ YY_NO_ANCHOR,
		/* 115 */ YY_NO_ANCHOR,
		/* 116 */ YY_NO_ANCHOR,
		/* 117 */ YY_NO_ANCHOR,
		/* 118 */ YY_NO_ANCHOR,
		/* 119 */ YY_NO_ANCHOR,
		/* 120 */ YY_NO_ANCHOR,
		/* 121 */ YY_NO_ANCHOR,
		/* 122 */ YY_NO_ANCHOR,
		/* 123 */ YY_NO_ANCHOR,
		/* 124 */ YY_NO_ANCHOR,
		/* 125 */ YY_NO_ANCHOR,
		/* 126 */ YY_NO_ANCHOR,
		/* 127 */ YY_NO_ANCHOR,
		/* 128 */ YY_NO_ANCHOR,
		/* 129 */ YY_NO_ANCHOR,
		/* 130 */ YY_NO_ANCHOR,
		/* 131 */ YY_NO_ANCHOR,
		/* 132 */ YY_NO_ANCHOR,
		/* 133 */ YY_NO_ANCHOR,
		/* 134 */ YY_NO_ANCHOR,
		/* 135 */ YY_NO_ANCHOR,
		/* 136 */ YY_NO_ANCHOR,
		/* 137 */ YY_NO_ANCHOR,
		/* 138 */ YY_NO_ANCHOR,
		/* 139 */ YY_NO_ANCHOR,
		/* 140 */ YY_NO_ANCHOR,
		/* 141 */ YY_NO_ANCHOR,
		/* 142 */ YY_NO_ANCHOR,
		/* 143 */ YY_NO_ANCHOR,
		/* 144 */ YY_NO_ANCHOR,
		/* 145 */ YY_NO_ANCHOR,
		/* 146 */ YY_NO_ANCHOR,
		/* 147 */ YY_NO_ANCHOR,
		/* 148 */ YY_NO_ANCHOR,
		/* 149 */ YY_NO_ANCHOR,
		/* 150 */ YY_NO_ANCHOR,
		/* 151 */ YY_NO_ANCHOR,
		/* 152 */ YY_NO_ANCHOR,
		/* 153 */ YY_NO_ANCHOR,
		/* 154 */ YY_NO_ANCHOR,
		/* 155 */ YY_NO_ANCHOR,
		/* 156 */ YY_NO_ANCHOR,
		/* 157 */ YY_NO_ANCHOR,
		/* 158 */ YY_NO_ANCHOR,
		/* 159 */ YY_NO_ANCHOR,
		/* 160 */ YY_NO_ANCHOR,
		/* 161 */ YY_NO_ANCHOR,
		/* 162 */ YY_NO_ANCHOR,
		/* 163 */ YY_NO_ANCHOR,
		/* 164 */ YY_NO_ANCHOR,
		/* 165 */ YY_NO_ANCHOR,
		/* 166 */ YY_NO_ANCHOR,
		/* 167 */ YY_NO_ANCHOR,
		/* 168 */ YY_NO_ANCHOR,
		/* 169 */ YY_NO_ANCHOR,
		/* 170 */ YY_NO_ANCHOR,
		/* 171 */ YY_NO_ANCHOR,
		/* 172 */ YY_NO_ANCHOR,
		/* 173 */ YY_NO_ANCHOR,
		/* 174 */ YY_NO_ANCHOR,
		/* 175 */ YY_NO_ANCHOR,
		/* 176 */ YY_NO_ANCHOR,
		/* 177 */ YY_NO_ANCHOR,
		/* 178 */ YY_NO_ANCHOR,
		/* 179 */ YY_NO_ANCHOR,
		/* 180 */ YY_NO_ANCHOR,
		/* 181 */ YY_NO_ANCHOR,
		/* 182 */ YY_NO_ANCHOR,
		/* 183 */ YY_NO_ANCHOR,
		/* 184 */ YY_NO_ANCHOR,
		/* 185 */ YY_NO_ANCHOR,
		/* 186 */ YY_NO_ANCHOR,
		/* 187 */ YY_NO_ANCHOR,
		/* 188 */ YY_NO_ANCHOR,
		/* 189 */ YY_NO_ANCHOR,
		/* 190 */ YY_NO_ANCHOR,
		/* 191 */ YY_NO_ANCHOR,
		/* 192 */ YY_NO_ANCHOR,
		/* 193 */ YY_NO_ANCHOR,
		/* 194 */ YY_NO_ANCHOR,
		/* 195 */ YY_NO_ANCHOR,
		/* 196 */ YY_NO_ANCHOR,
		/* 197 */ YY_NO_ANCHOR,
		/* 198 */ YY_NO_ANCHOR,
		/* 199 */ YY_NO_ANCHOR,
		/* 200 */ YY_NO_ANCHOR,
		/* 201 */ YY_NO_ANCHOR,
		/* 202 */ YY_NO_ANCHOR,
		/* 203 */ YY_NO_ANCHOR,
		/* 204 */ YY_NO_ANCHOR,
		/* 205 */ YY_NO_ANCHOR,
		/* 206 */ YY_NO_ANCHOR,
		/* 207 */ YY_NO_ANCHOR,
		/* 208 */ YY_NO_ANCHOR,
		/* 209 */ YY_NO_ANCHOR,
		/* 210 */ YY_NO_ANCHOR,
		/* 211 */ YY_NO_ANCHOR,
		/* 212 */ YY_NO_ANCHOR,
		/* 213 */ YY_NO_ANCHOR,
		/* 214 */ YY_NO_ANCHOR,
		/* 215 */ YY_NO_ANCHOR,
		/* 216 */ YY_NO_ANCHOR,
		/* 217 */ YY_NO_ANCHOR,
		/* 218 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"51:9,2,1,51,2:2,51:18,2,51,20,51:4,3,51,5,19,17,8,18,49,9,48,45:6,48:3,7,6," +
"16,10,15,51:2,21,26,34,22,24,39,27,40,31,47,33,32,41,30,28,38,35,23,25,36,2" +
"9,43,44,42,37,46,13,51,14,51,50,51,21,26,34,22,24,39,27,40,31,47,33,32,41,3" +
"0,28,38,35,23,25,36,29,43,44,42,37,46,11,4,12,51:65410,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,219,
"0,1:9,2,1:11,3,4,5,6,7,8,1,9:3,10,11,8,12,10,13,14,15,16,17,18,19,20,21,22," +
"23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47," +
"48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,9,64,9,65,66,67,9,68,69,70," +
"71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,91,92,93,94,95," +
"96,97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115" +
",116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,13" +
"4,135,136,137,138,139,140,141,142,143,144,145,146,147,148,149,150,151,152,1" +
"53,154,155,156,157,158,159,160,161,162,163,164,165,166,167,168,169,170,171," +
"172,173,174,175,176,177,178,179,180,181,182,183,184,185,186,187,188,189,190" +
",9")[0];

	private int yy_nxt[][] = unpackFromString(191,52,
"1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,33,37,24,38,218" +
",39,40,142,25,41,92,145,218,42,218,89,146,43,147,95,96,97,26,218:2,26,20,27" +
",20,-1:71,28,-1:53,218,198,218:9,44,218:12,27,218:2,27,-1,27,-1:22,218,88,2" +
"18:8,45,29,218:3,29,218:2,46,218:5,27,218:2,27,-1,27,-1:22,98,218,47,178,21" +
"8:4,48,218,99,218:4,180,218:4,148,218:3,27,218:2,27,-1,27,-1:22,218:4,150,2" +
"18:4,151,218:10,50,218:3,27,218:2,27,-1,27,-1:22,27:24,26,27:2,26,32,27,-1:" +
"22,27:28,-1,27,-1:22,218:24,27,218:2,27,-1,27,-1:46,36,-1:2,36,-1:24,218:7," +
"199,218:16,27,218:2,27,-1,27,-1:22,218:4,190,218:4,61,218:5,90,218:8,27,218" +
":2,27,-1,27,-1:22,218:20,141,218:3,27,218:2,27,-1,27,-1:22,100,218,88,218:3" +
",200,101,218:2,49,149,218:12,27,218:2,27,-1,27,-1:22,218:11,90,218:5,181,21" +
"8:6,27,218:2,27,-1,27,-1:22,218:11,29,218:12,27,218:2,27,-1,27,-1:22,218:7," +
"103,218:2,35,218:13,27,218:2,27,-1,27,-1:22,152,94,29,106,218:6,153,218:4,2" +
"9,218:3,94,218:4,27,218:2,27,-1,27,-1:22,218:2,91,109,218:11,110,218:8,34,2" +
"18:2,27,-1,27,-1:22,218:10,112,218:4,30,218:8,27,218:2,27,-1,27,-1:22,218:2" +
",88,218:21,27,218:2,27,-1,27,-1:22,218:9,88,218:14,27,218:2,27,-1,27,-1:22," +
"218:13,30,218:10,27,218:2,27,-1,27,-1:22,218:5,94,218:11,94,218:2,186,218:3" +
",27,218:2,27,-1,27,-1:22,218:6,88,218:17,27,218:2,27,-1,27,-1:22,218:6,90,2" +
"18:17,27,218:2,27,-1,27,-1:22,218,90,218:22,27,218:2,27,-1,27,-1:22,218:3,9" +
"4,218:20,27,218:2,27,-1,27,-1:22,218:17,29,218:6,27,218:2,27,-1,27,-1:22,21" +
"8:2,29,218:21,27,218:2,27,-1,27,-1:22,218:17,94,218:6,27,218:2,27,-1,27,-1:" +
"22,218:3,30,218:20,27,218:2,27,-1,27,-1:22,218:3,88,218:20,27,218:2,27,-1,2" +
"7,-1:22,218:16,88,218:7,27,218:2,27,-1,27,-1:22,218:2,90,218:21,27,218:2,27" +
",-1,27,-1:22,218:17,30,218:6,27,218:2,27,-1,27,-1:22,218:12,30,218:11,27,21" +
"8:2,27,-1,27,-1:22,218:15,29,218:8,27,218:2,27,-1,27,-1:22,218:18,30,218:5," +
"27,218:2,27,-1,27,-1:22,218,29,218:22,27,218:2,27,-1,27,-1:22,218:8,90,218:" +
"15,27,218:2,27,-1,27,-1:22,90,218:23,27,218:2,27,-1,27,-1:22,218:9,30,218:1" +
"4,27,218:2,27,-1,27,-1:22,218:11,94,218:12,27,218:2,27,-1,27,-1:22,218:12,8" +
"8,218:11,27,218:2,27,-1,27,-1:22,218:17,90,218:6,27,218:2,27,-1,27,-1:22,21" +
"8:15,90,218:8,27,218:2,27,-1,27,-1:22,218:2,30,218:21,27,218:2,27,-1,27,-1:" +
"22,218:19,30,218:4,27,218:2,27,-1,27,-1:22,218:15,94,218:8,27,218:2,27,-1,2" +
"7,-1:22,218:6,94,218:17,27,218:2,27,-1,27,-1:22,218:2,31,218:21,27,218:2,27" +
",-1,27,-1:22,218:9,94,218:14,27,218:2,27,-1,27,-1:22,218:15,31,218:8,27,218" +
":2,27,-1,27,-1:22,218:4,88,218:19,27,218:2,27,-1,27,-1:22,218:9,31,218:14,2" +
"7,218:2,27,-1,27,-1:22,218:16,31,218:7,27,218:2,27,-1,27,-1:22,218,88,218:2" +
"2,27,218:2,27,-1,27,-1:22,218:4,90,218:19,27,218:2,27,-1,27,-1:22,218:3,90," +
"218:20,27,218:2,27,-1,27,-1:22,94,218:23,27,218:2,27,-1,27,-1:22,218,30,218" +
":22,27,218:2,27,-1,27,-1:22,218:6,31,218:17,27,218:2,27,-1,27,-1:22,218:2,5" +
"2,218:8,202,218:12,27,218:2,27,-1,27,-1:22,218:3,63,218:20,27,218:2,27,-1,2" +
"7,-1:22,218:5,51,218:18,27,218:2,27,-1,27,-1:22,218:15,177,218:8,27,218:2,2" +
"7,-1,27,-1:22,218:20,53,218:3,27,218:2,27,-1,27,-1:22,54,218:10,154,218:12," +
"27,218:2,27,-1,27,-1:22,218:5,54,218:4,155,218:13,27,218:2,27,-1,27,-1:22,2" +
"18:20,55,218:3,27,218:2,27,-1,27,-1:22,218:24,27,56,218,27,-1,27,-1:22,218:" +
"4,57,218:8,204,218:10,27,218:2,27,-1,27,-1:22,218,58,158,218:21,27,218:2,27" +
",-1,27,-1:22,218:5,59,218:18,27,218:2,27,-1,27,-1:22,218:7,60,218:16,27,218" +
":2,27,-1,27,-1:22,218,57,218:9,118,218:12,27,218:2,27,-1,27,-1:22,218:15,57" +
",218:8,27,218:2,27,-1,27,-1:22,218:21,93,218:2,27,218:2,27,-1,27,-1:22,218:" +
"13,56,218:10,27,218:2,27,-1,27,-1:22,218:2,143,218:6,62,218:14,27,218:2,27," +
"-1,27,-1:22,64,218:9,162,218:13,27,218:2,27,-1,27,-1:22,218:20,179,218:3,27" +
",218:2,27,-1,27,-1:22,218:9,65,218:5,66,218:8,27,218:2,27,-1,27,-1:22,218:6" +
",67,218:2,61,218:14,27,218:2,27,-1,27,-1:22,218:3,64,218:20,27,218:2,27,-1," +
"27,-1:22,218:11,68,218:12,27,218:2,27,-1,27,-1:22,218:9,69,218:14,27,218:2," +
"27,-1,27,-1:22,70,218:23,27,218:2,27,-1,27,-1:22,218:8,71,218:15,27,218:2,2" +
"7,-1,27,-1:22,218:4,164,218:2,72,218:16,27,218:2,27,-1,27,-1:22,218:11,52,2" +
"18:12,27,218:2,27,-1,27,-1:22,218:9,61,218:14,27,218:2,27,-1,27,-1:22,218:1" +
"5,73,218:8,27,218:2,27,-1,27,-1:22,218:13,74,218:10,27,218:2,27,-1,27,-1:22" +
",218:9,75,218:14,27,218:2,27,-1,27,-1:22,218:3,76,218:20,27,218:2,27,-1,27," +
"-1:22,218:7,77,218:16,27,218:2,27,-1,27,-1:22,218:10,51,218:13,27,218:2,27," +
"-1,27,-1:22,218:3,45,218:20,27,218:2,27,-1,27,-1:22,218:19,78,218:4,27,218:" +
"2,27,-1,27,-1:22,218:4,79,218:19,27,218:2,27,-1,27,-1:22,80,218:23,27,218:2" +
",27,-1,27,-1:22,218:2,81,218:21,27,218:2,27,-1,27,-1:22,218:9,82,218:14,27," +
"218:2,27,-1,27,-1:22,218:3,83,218:20,27,218:2,27,-1,27,-1:22,218:9,50,218:1" +
"4,27,218:2,27,-1,27,-1:22,218:7,46,218:16,27,218:2,27,-1,27,-1:22,218:3,84," +
"218:20,27,218:2,27,-1,27,-1:22,218:3,85,218:20,27,218:2,27,-1,27,-1:22,218:" +
"21,74,218:2,27,218:2,27,-1,27,-1:22,218:9,86,218:14,27,218:2,27,-1,27,-1:22" +
",218:9,87,218:14,27,218:2,27,-1,27,-1:22,218:5,113,218:18,27,218:2,27,-1,27" +
",-1:22,218:3,182,218:3,102,218:16,27,218:2,27,-1,27,-1:22,218:20,29,218:3,2" +
"7,218:2,27,-1,27,-1:22,218:7,72,218:16,27,218:2,27,-1,27,-1:22,201,218:2,18" +
"3,218:3,104,218:2,105,218:13,27,218:2,27,-1,27,-1:22,107,218:6,108,218:16,2" +
"7,218:2,27,-1,27,-1:22,203,218:2,111,218:20,27,218:2,27,-1,27,-1:22,114,218" +
":23,27,218:2,27,-1,27,-1:22,218:7,205,218:2,115,218:13,27,218:2,27,-1,27,-1" +
":22,218:10,189,218:9,116,218:3,27,218:2,27,-1,27,-1:22,218:17,117,218:6,27," +
"218:2,27,-1,27,-1:22,218:5,119,218:18,27,218:2,27,-1,27,-1:22,218:15,119,21" +
"8:8,27,218:2,27,-1,27,-1:22,218:10,120,218:13,27,218:2,27,-1,27,-1:22,218,1" +
"21,218:22,27,218:2,27,-1,27,-1:22,218:3,122,218:20,27,218:2,27,-1,27,-1:22," +
"218:7,123,218:16,27,218:2,27,-1,27,-1:22,218,124,218:22,27,218:2,27,-1,27,-" +
"1:22,218:10,125,218:13,27,218:2,27,-1,27,-1:22,218:15,126,218:8,27,218:2,27" +
",-1,27,-1:22,218:15,127,218:8,27,218:2,27,-1,27,-1:22,218:6,128,218:17,27,2" +
"18:2,27,-1,27,-1:22,218:3,129,218:20,27,218:2,27,-1,27,-1:22,218:17,130,218" +
":6,27,218:2,27,-1,27,-1:22,131,218:23,27,218:2,27,-1,27,-1:22,218:8,132,218" +
":15,27,218:2,27,-1,27,-1:22,218:11,144,218:12,27,218:2,27,-1,27,-1:22,218,1" +
"33,218:22,27,218:2,27,-1,27,-1:22,218:10,134,218:13,27,218:2,27,-1,27,-1:22" +
",218:10,135,218:13,27,218:2,27,-1,27,-1:22,218:8,136,218:15,27,218:2,27,-1," +
"27,-1:22,218:2,137,218:21,27,218:2,27,-1,27,-1:22,218:3,138,218:20,27,218:2" +
",27,-1,27,-1:22,218:8,139,218:15,27,218:2,27,-1,27,-1:22,218:7,105,218:16,2" +
"7,218:2,27,-1,27,-1:22,218:10,140,218:13,27,218:2,27,-1,27,-1:22,172,218:23" +
",27,218:2,27,-1,27,-1:22,218:11,156,218:12,27,218:2,27,-1,27,-1:22,218:11,9" +
"0,218:12,27,218:2,27,-1,27,-1:22,218:2,157,218:21,27,218:2,27,-1,27,-1:22,2" +
"18:15,159,218:8,27,218:2,27,-1,27,-1:22,218:21,160,218:2,27,218:2,27,-1,27," +
"-1:22,218:9,161,218,206,218:12,27,218:2,27,-1,27,-1:22,218:2,163,218:21,27," +
"218:2,27,-1,27,-1:22,218:4,164,218:19,27,218:2,27,-1,27,-1:22,218:20,165,21" +
"8:3,27,218:2,27,-1,27,-1:22,218:7,166,218:16,27,218:2,27,-1,27,-1:22,218:7," +
"167,218:16,27,218:2,27,-1,27,-1:22,218:9,168,218:14,27,218:2,27,-1,27,-1:22" +
",218:15,169,218:8,27,218:2,27,-1,27,-1:22,218:15,170,218:8,27,218:2,27,-1,2" +
"7,-1:22,218:14,171,218:9,27,218:2,27,-1,27,-1:22,218:15,173,218:8,27,218:2," +
"27,-1,27,-1:22,218:7,174,218:16,27,218:2,27,-1,27,-1:22,218:8,175,218:15,27" +
",218:2,27,-1,27,-1:22,218:13,176,218:10,27,218:2,27,-1,27,-1:22,218,176,218" +
":22,27,218:2,27,-1,27,-1:22,218,184,218:22,27,218:2,27,-1,27,-1:22,218:23,1" +
"85,27,218:2,27,-1,27,-1:22,218:4,187,218:8,188,218:10,27,218:2,27,-1,27,-1:" +
"22,218:17,191,218:6,27,218:2,27,-1,27,-1:22,207,218:23,27,218:2,27,-1,27,-1" +
":22,218:2,192,218:21,27,218:2,27,-1,27,-1:22,218:12,208,218:11,27,218:2,27," +
"-1,27,-1:22,218:13,209,218:10,27,218:2,27,-1,27,-1:22,218:11,210,218:12,27," +
"218:2,27,-1,27,-1:22,218:10,211,218:13,27,218:2,27,-1,27,-1:22,218:6,212,21" +
"8:17,27,218:2,27,-1,27,-1:22,218:12,213,218:11,27,218:2,27,-1,27,-1:22,218:" +
"4,214,218:12,215,218:6,27,218:2,27,-1,27,-1:22,218:9,193,218:14,27,218:2,27" +
",-1,27,-1:22,218:2,194,218:21,27,218:2,27,-1,27,-1:22,218:14,195,218:9,27,2" +
"18:2,27,-1,27,-1:22,218:17,216,218:6,27,218:2,27,-1,27,-1:22,217,218:23,27," +
"218:2,27,-1,27,-1:22,196,218:23,27,218:2,27,-1,27,-1:22,218,197,218:22,27,2" +
"18:2,27,-1,27,-1");
	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

{
return new Symbol(sym.EOF, null); }
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{ yychar=0;}
					case -3:
						break;
					case 3:
						{ /*Ingnora los Espacios. */ }
					case -4:
						break;
					case 4:
						{ /* ignora apostrofos. */ }
					case -5:
						break;
					case 5:
						{/*no hace nada, aumenta una columna*/yychar++; }
					case -6:
						break;
					case 6:
						{  return new Symbol(sym.PARENTESIS_C, yyline, yychar,  yytext());}
					case -7:
						break;
					case 7:
						{  return new Symbol(sym.PUNTOCOMA, yyline, yychar,  yytext());}
					case -8:
						break;
					case 8:
						{  return new Symbol(sym.DOSPUNTOS, yyline, yychar,  yytext());}
					case -9:
						break;
					case 9:
						{  return new Symbol(sym.COMA, yyline, yychar,  yytext());}
					case -12:
						break;
					case 12:
						{  return new Symbol(sym.LLAVE_A, yyline, yychar,  yytext());}
					case -13:
						break;
					case 13:
						{  return new Symbol(sym.LLAVE_C, yyline, yychar,  yytext());}
					case -14:
						break;
					case 14:
						{  return new Symbol(sym.CORCHETE_A, yyline, yychar,  yytext());}
					case -15:
						break;
					case 15:
						{  return new Symbol(sym.CORCHETE_C, yyline, yychar,  yytext());}
					case -16:
						break;
					case 16:
						{  return new Symbol(sym.MAYORQ, yyline, yychar,  yytext());}
					case -17:
						break;
					case 17:
						{  return new Symbol(sym.MENORQ, yyline, yychar,  yytext());}
					case -20:
						break;
					case 20:
						{Interfaz.rotular("error lexico:   "+  yytext()+"   en la fila "+yyline +" y en la columna " + yychar);}
					case -21:
						break;
					case 21:
						{ return new Symbol(sym.COMILLA, yyline, yychar,  yytext());}
					case -22:
						break;
					case 22:
						{ return new Symbol(sym.ETIQUETA, yyline, yychar,  yytext());}
					case -23:
						break;
					case 23:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -24:
						break;
					case 24:
						{ return new Symbol(sym.ETIQUETA, yyline, yychar,  yytext());}
					case -25:
						break;
					case 25:
						{ return new Symbol(sym.ETIQUETA, yyline, yychar,  yytext());}
					case -26:
						break;
					case 26:
						{      return new Symbol(sym.NUMERICO, yyline, yychar,  yytext());}
					case -27:
						break;
					case 27:
						{  return new Symbol(sym.ALFANUMERICO, yyline, yychar,  yytext());}
					case -29:
						break;
					case 29:
						{ return new Symbol(sym.ETIQUETA, yyline, yychar,  yytext());}
					case -30:
						break;
					case 30:
						{ return new Symbol(sym.ATRIBUTO, yyline, yychar,  yytext());}
					case -31:
						break;
					case 31:
						{ return new Symbol(sym.ATRIBUTO, yyline, yychar,  yytext());}
					case -32:
						break;
					case 33:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -33:
						break;
					case 34:
						{ return new Symbol(sym.ETIQUETA, yyline, yychar,  yytext());}
					case -34:
						break;
					case 35:
						{ return new Symbol(sym.ETIQUETA, yyline, yychar,  yytext());}
					case -35:
						break;
					case 36:
						{      return new Symbol(sym.NUMERICO, yyline, yychar,  yytext());}
					case -36:
						break;
					case 37:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -37:
						break;
					case 38:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -38:
						break;
					case 39:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -39:
						break;
					case 40:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -40:
						break;
					case 41:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -41:
						break;
					case 42:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -42:
						break;
					case 43:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -43:
						break;
					case 44:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -44:
						break;
					case 45:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -45:
						break;
					case 46:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -46:
						break;
					case 47:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -47:
						break;
					case 48:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -48:
						break;
					case 49:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -49:
						break;
					case 50:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -50:
						break;
					case 51:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -51:
						break;
					case 52:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -52:
						break;
					case 53:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -53:
						break;
					case 54:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -54:
						break;
					case 55:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -55:
						break;
					case 56:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -56:
						break;
					case 57:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -57:
						break;
					case 58:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -58:
						break;
					case 59:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -59:
						break;
					case 60:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -60:
						break;
					case 61:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -61:
						break;
					case 62:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -62:
						break;
					case 63:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -63:
						break;
					case 64:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -64:
						break;
					case 65:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -65:
						break;
					case 66:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -66:
						break;
					case 67:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -67:
						break;
					case 68:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -68:
						break;
					case 69:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -69:
						break;
					case 70:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -70:
						break;
					case 71:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -71:
						break;
					case 72:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -72:
						break;
					case 73:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -73:
						break;
					case 74:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -74:
						break;
					case 75:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -75:
						break;
					case 76:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -76:
						break;
					case 77:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -77:
						break;
					case 78:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -78:
						break;
					case 79:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -79:
						break;
					case 80:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -80:
						break;
					case 81:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -81:
						break;
					case 82:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -82:
						break;
					case 83:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -83:
						break;
					case 84:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -84:
						break;
					case 85:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -85:
						break;
					case 86:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -86:
						break;
					case 87:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -87:
						break;
					case 88:
						{ return new Symbol(sym.ETIQUETA, yyline, yychar,  yytext());}
					case -88:
						break;
					case 89:
						{ return new Symbol(sym.ETIQUETA, yyline, yychar,  yytext());}
					case -89:
						break;
					case 90:
						{ return new Symbol(sym.ETIQUETA, yyline, yychar,  yytext());}
					case -90:
						break;
					case 91:
						{ return new Symbol(sym.ETIQUETA, yyline, yychar,  yytext());}
					case -91:
						break;
					case 92:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -92:
						break;
					case 93:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -93:
						break;
					case 94:
						{ return new Symbol(sym.ETIQUETA, yyline, yychar,  yytext());}
					case -94:
						break;
					case 95:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -95:
						break;
					case 96:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -96:
						break;
					case 97:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -97:
						break;
					case 98:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -98:
						break;
					case 99:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -99:
						break;
					case 100:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -100:
						break;
					case 101:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -101:
						break;
					case 102:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -102:
						break;
					case 103:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -103:
						break;
					case 104:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -104:
						break;
					case 105:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -105:
						break;
					case 106:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -106:
						break;
					case 107:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -107:
						break;
					case 108:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -108:
						break;
					case 109:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -109:
						break;
					case 110:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -110:
						break;
					case 111:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -111:
						break;
					case 112:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -112:
						break;
					case 113:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -113:
						break;
					case 114:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -114:
						break;
					case 115:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -115:
						break;
					case 116:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -116:
						break;
					case 117:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -117:
						break;
					case 118:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -118:
						break;
					case 119:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -119:
						break;
					case 120:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -120:
						break;
					case 121:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -121:
						break;
					case 122:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -122:
						break;
					case 123:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -123:
						break;
					case 124:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -124:
						break;
					case 125:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -125:
						break;
					case 126:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -126:
						break;
					case 127:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -127:
						break;
					case 128:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -128:
						break;
					case 129:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -129:
						break;
					case 130:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -130:
						break;
					case 131:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -131:
						break;
					case 132:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -132:
						break;
					case 133:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -133:
						break;
					case 134:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -134:
						break;
					case 135:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -135:
						break;
					case 136:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -136:
						break;
					case 137:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -137:
						break;
					case 138:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -138:
						break;
					case 139:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -139:
						break;
					case 140:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -140:
						break;
					case 141:
						{ return new Symbol(sym.ETIQUETA, yyline, yychar,  yytext());}
					case -141:
						break;
					case 142:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -142:
						break;
					case 143:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -143:
						break;
					case 144:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -144:
						break;
					case 145:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -145:
						break;
					case 146:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -146:
						break;
					case 147:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -147:
						break;
					case 148:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -148:
						break;
					case 149:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -149:
						break;
					case 150:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -150:
						break;
					case 151:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -151:
						break;
					case 152:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -152:
						break;
					case 153:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -153:
						break;
					case 154:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -154:
						break;
					case 155:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -155:
						break;
					case 156:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -156:
						break;
					case 157:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -157:
						break;
					case 158:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -158:
						break;
					case 159:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -159:
						break;
					case 160:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -160:
						break;
					case 161:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -161:
						break;
					case 162:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -162:
						break;
					case 163:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -163:
						break;
					case 164:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -164:
						break;
					case 165:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -165:
						break;
					case 166:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -166:
						break;
					case 167:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -167:
						break;
					case 168:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -168:
						break;
					case 169:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -169:
						break;
					case 170:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -170:
						break;
					case 171:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -171:
						break;
					case 172:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -172:
						break;
					case 173:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -173:
						break;
					case 174:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -174:
						break;
					case 175:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -175:
						break;
					case 176:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -176:
						break;
					case 177:
						{ return new Symbol(sym.ATRIBUTO, yyline, yychar,  yytext());}
					case -177:
						break;
					case 178:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -178:
						break;
					case 179:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -179:
						break;
					case 180:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -180:
						break;
					case 181:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -181:
						break;
					case 182:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -182:
						break;
					case 183:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -183:
						break;
					case 184:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -184:
						break;
					case 185:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -185:
						break;
					case 186:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -186:
						break;
					case 187:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -187:
						break;
					case 188:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -188:
						break;
					case 189:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -189:
						break;
					case 190:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -190:
						break;
					case 191:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -191:
						break;
					case 192:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -192:
						break;
					case 193:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -193:
						break;
					case 194:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -194:
						break;
					case 195:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -195:
						break;
					case 196:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -196:
						break;
					case 197:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -197:
						break;
					case 198:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -198:
						break;
					case 199:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -199:
						break;
					case 200:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -200:
						break;
					case 201:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -201:
						break;
					case 202:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -202:
						break;
					case 203:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -203:
						break;
					case 204:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -204:
						break;
					case 205:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -205:
						break;
					case 206:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -206:
						break;
					case 207:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -207:
						break;
					case 208:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -208:
						break;
					case 209:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -209:
						break;
					case 210:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -210:
						break;
					case 211:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -211:
						break;
					case 212:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -212:
						break;
					case 213:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -213:
						break;
					case 214:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -214:
						break;
					case 215:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -215:
						break;
					case 216:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -216:
						break;
					case 217:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -217:
						break;
					case 218:
						{  return new Symbol(sym.ALFABETICO, yyline, yychar,  yytext());}
					case -218:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
