/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.xembly;

import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class XemblyLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__8=8;
	public static final int T__9=9;
	public static final int T__10=10;
	public static final int T__11=11;
	public static final int T__12=12;
	public static final int T__13=13;
	public static final int T__14=14;
	public static final int T__15=15;
	public static final int T__16=16;
	public static final int T__17=17;
	public static final int COMMA=4;
	public static final int SEMICOLON=5;
	public static final int SPACE=6;
	public static final int TEXT=7;

	    @Override
	    public void emitErrorMessage(String msg) {
	        throw new ParsingException(msg);
	    }


	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public XemblyLexer() {} 
	public XemblyLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public XemblyLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "org/xembly/Xembly.g"; }

	// $ANTLR start "T__8"
	public final void mT__8() throws RecognitionException {
		try {
			int _type = T__8;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// org/xembly/Xembly.g:12:6: ( 'ADD' )
			// org/xembly/Xembly.g:12:8: 'ADD'
			{
			match("ADD"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__8"

	// $ANTLR start "T__9"
	public final void mT__9() throws RecognitionException {
		try {
			int _type = T__9;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// org/xembly/Xembly.g:13:6: ( 'ADDIF' )
			// org/xembly/Xembly.g:13:8: 'ADDIF'
			{
			match("ADDIF"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__9"

	// $ANTLR start "T__10"
	public final void mT__10() throws RecognitionException {
		try {
			int _type = T__10;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// org/xembly/Xembly.g:14:7: ( 'ATTR' )
			// org/xembly/Xembly.g:14:9: 'ATTR'
			{
			match("ATTR"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__10"

	// $ANTLR start "T__11"
	public final void mT__11() throws RecognitionException {
		try {
			int _type = T__11;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// org/xembly/Xembly.g:15:7: ( 'PI' )
			// org/xembly/Xembly.g:15:9: 'PI'
			{
			match("PI"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__11"

	// $ANTLR start "T__12"
	public final void mT__12() throws RecognitionException {
		try {
			int _type = T__12;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// org/xembly/Xembly.g:16:7: ( 'REMOVE' )
			// org/xembly/Xembly.g:16:9: 'REMOVE'
			{
			match("REMOVE"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__12"

	// $ANTLR start "T__13"
	public final void mT__13() throws RecognitionException {
		try {
			int _type = T__13;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// org/xembly/Xembly.g:17:7: ( 'SET' )
			// org/xembly/Xembly.g:17:9: 'SET'
			{
			match("SET"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__13"

	// $ANTLR start "T__14"
	public final void mT__14() throws RecognitionException {
		try {
			int _type = T__14;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// org/xembly/Xembly.g:18:7: ( 'STRICT' )
			// org/xembly/Xembly.g:18:9: 'STRICT'
			{
			match("STRICT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__14"

	// $ANTLR start "T__15"
	public final void mT__15() throws RecognitionException {
		try {
			int _type = T__15;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// org/xembly/Xembly.g:19:7: ( 'UP' )
			// org/xembly/Xembly.g:19:9: 'UP'
			{
			match("UP"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__15"

	// $ANTLR start "T__16"
	public final void mT__16() throws RecognitionException {
		try {
			int _type = T__16;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// org/xembly/Xembly.g:20:7: ( 'XPATH' )
			// org/xembly/Xembly.g:20:9: 'XPATH'
			{
			match("XPATH"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__16"

	// $ANTLR start "T__17"
	public final void mT__17() throws RecognitionException {
		try {
			int _type = T__17;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// org/xembly/Xembly.g:21:7: ( 'XSET' )
			// org/xembly/Xembly.g:21:9: 'XSET'
			{
			match("XSET"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__17"

	// $ANTLR start "COMMA"
	public final void mCOMMA() throws RecognitionException {
		try {
			int _type = COMMA;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// org/xembly/Xembly.g:148:6: ( ',' )
			// org/xembly/Xembly.g:149:5: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "COMMA"

	// $ANTLR start "SEMICOLON"
	public final void mSEMICOLON() throws RecognitionException {
		try {
			int _type = SEMICOLON;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// org/xembly/Xembly.g:152:10: ( ';' )
			// org/xembly/Xembly.g:153:5: ';'
			{
			match(';'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SEMICOLON"

	// $ANTLR start "TEXT"
	public final void mTEXT() throws RecognitionException {
		try {
			int _type = TEXT;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// org/xembly/Xembly.g:157:5: ( '\"' ( '\\\\\"' |~ '\"' )* '\"' | '\\'' ( '\\\\\\'' |~ '\\'' )* '\\'' )
			int alt3=2;
			int LA3_0 = input.LA(1);
			if ( (LA3_0=='\"') ) {
				alt3=1;
			}
			else if ( (LA3_0=='\'') ) {
				alt3=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}

			switch (alt3) {
				case 1 :
					// org/xembly/Xembly.g:158:5: '\"' ( '\\\\\"' |~ '\"' )* '\"'
					{
					match('\"'); 
					// org/xembly/Xembly.g:158:9: ( '\\\\\"' |~ '\"' )*
					loop1:
					while (true) {
						int alt1=3;
						int LA1_0 = input.LA(1);
						if ( (LA1_0=='\\') ) {
							int LA1_2 = input.LA(2);
							if ( (LA1_2=='\"') ) {
								int LA1_4 = input.LA(3);
								if ( ((LA1_4 >= '\u0000' && LA1_4 <= '\uFFFF')) ) {
									alt1=1;
								}
								else {
									alt1=2;
								}

							}
							else if ( ((LA1_2 >= '\u0000' && LA1_2 <= '!')||(LA1_2 >= '#' && LA1_2 <= '\uFFFF')) ) {
								alt1=2;
							}

						}
						else if ( ((LA1_0 >= '\u0000' && LA1_0 <= '!')||(LA1_0 >= '#' && LA1_0 <= '[')||(LA1_0 >= ']' && LA1_0 <= '\uFFFF')) ) {
							alt1=2;
						}

						switch (alt1) {
						case 1 :
							// org/xembly/Xembly.g:158:10: '\\\\\"'
							{
							match("\\\""); 

							}
							break;
						case 2 :
							// org/xembly/Xembly.g:158:18: ~ '\"'
							{
							if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '\uFFFF') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop1;
						}
					}

					match('\"'); 

					        try {
					            this.setText(Arg.unescape(this.getText()));
					        } catch (XmlContentException ex) {
					            throw new ParsingException(ex);
					        }
					    
					}
					break;
				case 2 :
					// org/xembly/Xembly.g:167:5: '\\'' ( '\\\\\\'' |~ '\\'' )* '\\''
					{
					match('\''); 
					// org/xembly/Xembly.g:167:10: ( '\\\\\\'' |~ '\\'' )*
					loop2:
					while (true) {
						int alt2=3;
						int LA2_0 = input.LA(1);
						if ( (LA2_0=='\\') ) {
							int LA2_2 = input.LA(2);
							if ( (LA2_2=='\'') ) {
								int LA2_4 = input.LA(3);
								if ( ((LA2_4 >= '\u0000' && LA2_4 <= '\uFFFF')) ) {
									alt2=1;
								}
								else {
									alt2=2;
								}

							}
							else if ( ((LA2_2 >= '\u0000' && LA2_2 <= '&')||(LA2_2 >= '(' && LA2_2 <= '\uFFFF')) ) {
								alt2=2;
							}

						}
						else if ( ((LA2_0 >= '\u0000' && LA2_0 <= '&')||(LA2_0 >= '(' && LA2_0 <= '[')||(LA2_0 >= ']' && LA2_0 <= '\uFFFF')) ) {
							alt2=2;
						}

						switch (alt2) {
						case 1 :
							// org/xembly/Xembly.g:167:11: '\\\\\\''
							{
							match("\\'"); 

							}
							break;
						case 2 :
							// org/xembly/Xembly.g:167:20: ~ '\\''
							{
							if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '\uFFFF') ) {
								input.consume();
							}
							else {
								MismatchedSetException mse = new MismatchedSetException(null,input);
								recover(mse);
								throw mse;
							}
							}
							break;

						default :
							break loop2;
						}
					}

					match('\''); 

					        try {
					            this.setText(Arg.unescape(this.getText()));
					        } catch (XmlContentException ex) {
					            throw new ParsingException(ex);
					        }
					    
					}
					break;

			}
			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "TEXT"

	// $ANTLR start "SPACE"
	public final void mSPACE() throws RecognitionException {
		try {
			int _type = SPACE;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// org/xembly/Xembly.g:178:5: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
			// org/xembly/Xembly.g:179:5: ( ' ' | '\\t' | '\\n' | '\\r' )+
			{
			// org/xembly/Xembly.g:179:5: ( ' ' | '\\t' | '\\n' | '\\r' )+
			int cnt4=0;
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( ((LA4_0 >= '\t' && LA4_0 <= '\n')||LA4_0=='\r'||LA4_0==' ') ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// org/xembly/Xembly.g:
					{
					if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt4 >= 1 ) break loop4;
					EarlyExitException eee = new EarlyExitException(4, input);
					throw eee;
				}
				cnt4++;
			}

			 skip(); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "SPACE"

	@Override
	public void mTokens() throws RecognitionException {
		// org/xembly/Xembly.g:1:8: ( T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | COMMA | SEMICOLON | TEXT | SPACE )
		int alt5=14;
		switch ( input.LA(1) ) {
		case 'A':
			{
			int LA5_1 = input.LA(2);
			if ( (LA5_1=='D') ) {
				int LA5_11 = input.LA(3);
				if ( (LA5_11=='D') ) {
					int LA5_17 = input.LA(4);
					if ( (LA5_17=='I') ) {
						alt5=2;
					}

					else {
						alt5=1;
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
							input.consume();
						}
						NoViableAltException nvae =
							new NoViableAltException("", 5, 11, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA5_1=='T') ) {
				alt5=3;
			}

			else {
				int nvaeMark = input.mark();
				try {
					input.consume();
					NoViableAltException nvae =
						new NoViableAltException("", 5, 1, input);
					throw nvae;
				} finally {
					input.rewind(nvaeMark);
				}
			}

			}
			break;
		case 'P':
			{
			alt5=4;
			}
			break;
		case 'R':
			{
			alt5=5;
			}
			break;
		case 'S':
			{
			int LA5_4 = input.LA(2);
			if ( (LA5_4=='E') ) {
				alt5=6;
			}
			else if ( (LA5_4=='T') ) {
				alt5=7;
			}

			else {
				int nvaeMark = input.mark();
				try {
					input.consume();
					NoViableAltException nvae =
						new NoViableAltException("", 5, 4, input);
					throw nvae;
				} finally {
					input.rewind(nvaeMark);
				}
			}

			}
			break;
		case 'U':
			{
			alt5=8;
			}
			break;
		case 'X':
			{
			int LA5_6 = input.LA(2);
			if ( (LA5_6=='P') ) {
				alt5=9;
			}
			else if ( (LA5_6=='S') ) {
				alt5=10;
			}

			else {
				int nvaeMark = input.mark();
				try {
					input.consume();
					NoViableAltException nvae =
						new NoViableAltException("", 5, 6, input);
					throw nvae;
				} finally {
					input.rewind(nvaeMark);
				}
			}

			}
			break;
		case ',':
			{
			alt5=11;
			}
			break;
		case ';':
			{
			alt5=12;
			}
			break;
		case '\"':
		case '\'':
			{
			alt5=13;
			}
			break;
		case '\t':
		case '\n':
		case '\r':
		case ' ':
			{
			alt5=14;
			}
			break;
		default:
			NoViableAltException nvae =
				new NoViableAltException("", 5, 0, input);
			throw nvae;
		}
		switch (alt5) {
			case 1 :
				// org/xembly/Xembly.g:1:10: T__8
				{
				mT__8(); 

				}
				break;
			case 2 :
				// org/xembly/Xembly.g:1:15: T__9
				{
				mT__9(); 

				}
				break;
			case 3 :
				// org/xembly/Xembly.g:1:20: T__10
				{
				mT__10(); 

				}
				break;
			case 4 :
				// org/xembly/Xembly.g:1:26: T__11
				{
				mT__11(); 

				}
				break;
			case 5 :
				// org/xembly/Xembly.g:1:32: T__12
				{
				mT__12(); 

				}
				break;
			case 6 :
				// org/xembly/Xembly.g:1:38: T__13
				{
				mT__13(); 

				}
				break;
			case 7 :
				// org/xembly/Xembly.g:1:44: T__14
				{
				mT__14(); 

				}
				break;
			case 8 :
				// org/xembly/Xembly.g:1:50: T__15
				{
				mT__15(); 

				}
				break;
			case 9 :
				// org/xembly/Xembly.g:1:56: T__16
				{
				mT__16(); 

				}
				break;
			case 10 :
				// org/xembly/Xembly.g:1:62: T__17
				{
				mT__17(); 

				}
				break;
			case 11 :
				// org/xembly/Xembly.g:1:68: COMMA
				{
				mCOMMA(); 

				}
				break;
			case 12 :
				// org/xembly/Xembly.g:1:74: SEMICOLON
				{
				mSEMICOLON(); 

				}
				break;
			case 13 :
				// org/xembly/Xembly.g:1:84: TEXT
				{
				mTEXT(); 

				}
				break;
			case 14 :
				// org/xembly/Xembly.g:1:89: SPACE
				{
				mSPACE(); 

				}
				break;

		}
	}



}
