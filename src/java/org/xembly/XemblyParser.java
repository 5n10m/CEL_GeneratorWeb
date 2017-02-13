// $ANTLR 3.5.1 org/xembly/Xembly.g 2013-10-13 14:39:20

    package org.xembly;
    import java.util.Collection;
    import java.util.LinkedList;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

/**
 * Copyright (c) 2013, xembly.org
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: 1) Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer. 2) Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided
 * with the distribution. 3) Neither the name of the xembly.org nor
 * the names of its contributors may be used to endorse or promote
 * products derived from this software without specific prior written
 * permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT
 * NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 * FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL
 * THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,
 * INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT,
 * STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGE.
 */
@SuppressWarnings("all")
public class XemblyParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"", "", "", "", "COMMA", "SEMICOLON", "SPACE", 
		"TEXT", "'ADD'", "'ADDIF'", "'ATTR'", "'PI'", "'REMOVE'", "'SET'", "'STRICT'", 
		"'UP'", "'XPATH'", "'XSET'"
	};
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

	// delegates
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public XemblyParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public XemblyParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return XemblyParser.tokenNames; }
	@Override public String getGrammarFileName() { return "org/xembly/Xembly.g"; }


	    @Override
	    public void emitErrorMessage(String msg) {
	        throw new ParsingException(msg);
	    }



	// $ANTLR start "directives"
	// org/xembly/Xembly.g:56:1: directives returns [Collection ret] : ( directive SEMICOLON )* EOF ;
	public final Collection directives() throws RecognitionException {
		Collection ret = null;


		Directive directive1 =null;

		 ret = new LinkedList(); 
		try {
			// org/xembly/Xembly.g:58:5: ( ( directive SEMICOLON )* EOF )
			// org/xembly/Xembly.g:59:5: ( directive SEMICOLON )* EOF
			{
			// org/xembly/Xembly.g:59:5: ( directive SEMICOLON )*
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= 8 && LA1_0 <= 17)) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// org/xembly/Xembly.g:60:9: directive SEMICOLON
					{
					pushFollow(FOLLOW_directive_in_directives76);
					directive1=directive();
					state._fsp--;

					match(input,SEMICOLON,FOLLOW_SEMICOLON_in_directives86); 
					 ret.add(directive1); 
					}
					break;

				default :
					break loop1;
				}
			}

			match(input,EOF,FOLLOW_EOF_in_directives109); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ret;
	}
	// $ANTLR end "directives"



	// $ANTLR start "directive"
	// org/xembly/Xembly.g:67:1: directive returns [Directive ret] : ( 'XPATH' argument | 'SET' argument | 'XSET' argument | 'ATTR' name= argument COMMA value= argument | 'ADD' argument | 'ADDIF' argument | 'REMOVE' | 'STRICT' argument | 'UP' | 'PI' target= argument data= argument );
	public final Directive directive() throws RecognitionException {
		Directive ret = null;


		Object name =null;
		Object value =null;
		Object target =null;
		Object data =null;
		Object argument2 =null;
		Object argument3 =null;
		Object argument4 =null;
		Object argument5 =null;
		Object argument6 =null;
		Object argument7 =null;

		try {
			// org/xembly/Xembly.g:68:5: ( 'XPATH' argument | 'SET' argument | 'XSET' argument | 'ATTR' name= argument COMMA value= argument | 'ADD' argument | 'ADDIF' argument | 'REMOVE' | 'STRICT' argument | 'UP' | 'PI' target= argument data= argument )
			int alt2=10;
			switch ( input.LA(1) ) {
			case 16:
				{
				alt2=1;
				}
				break;
			case 13:
				{
				alt2=2;
				}
				break;
			case 17:
				{
				alt2=3;
				}
				break;
			case 10:
				{
				alt2=4;
				}
				break;
			case 8:
				{
				alt2=5;
				}
				break;
			case 9:
				{
				alt2=6;
				}
				break;
			case 12:
				{
				alt2=7;
				}
				break;
			case 14:
				{
				alt2=8;
				}
				break;
			case 15:
				{
				alt2=9;
				}
				break;
			case 11:
				{
				alt2=10;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}
			switch (alt2) {
				case 1 :
					// org/xembly/Xembly.g:69:5: 'XPATH' argument
					{
					match(input,16,FOLLOW_16_in_directive134); 
					pushFollow(FOLLOW_argument_in_directive136);
					argument2=argument();
					state._fsp--;


					        try {
					            ret = new XpathDirective(argument2.toString());
					        } catch (XmlContentException ex) {
					            throw new ParsingException(ex);
					        }
					    
					}
					break;
				case 2 :
					// org/xembly/Xembly.g:78:5: 'SET' argument
					{
					match(input,13,FOLLOW_13_in_directive154); 
					pushFollow(FOLLOW_argument_in_directive156);
					argument3=argument();
					state._fsp--;


					        try {
					            ret = new SetDirective(argument3.toString());
					        } catch (XmlContentException ex) {
					            throw new ParsingException(ex);
					        }
					    
					}
					break;
				case 3 :
					// org/xembly/Xembly.g:87:5: 'XSET' argument
					{
					match(input,17,FOLLOW_17_in_directive174); 
					pushFollow(FOLLOW_argument_in_directive176);
					argument4=argument();
					state._fsp--;


					        try {
					            ret = new XsetDirective(argument4.toString());
					        } catch (XmlContentException ex) {
					            throw new ParsingException(ex);
					        }
					    
					}
					break;
				case 4 :
					// org/xembly/Xembly.g:96:5: 'ATTR' name= argument COMMA value= argument
					{
					match(input,10,FOLLOW_10_in_directive194); 
					pushFollow(FOLLOW_argument_in_directive198);
					name=argument();
					state._fsp--;

					match(input,COMMA,FOLLOW_COMMA_in_directive200); 
					pushFollow(FOLLOW_argument_in_directive204);
					value=argument();
					state._fsp--;


					        try {
					            ret = new AttrDirective(name.toString(), value.toString());
					        } catch (XmlContentException ex) {
					            throw new ParsingException(ex);
					        }
					    
					}
					break;
				case 5 :
					// org/xembly/Xembly.g:105:5: 'ADD' argument
					{
					match(input,8,FOLLOW_8_in_directive222); 
					pushFollow(FOLLOW_argument_in_directive224);
					argument5=argument();
					state._fsp--;


					        try {
					            ret = new AddDirective(argument5.toString());
					        } catch (XmlContentException ex) {
					            throw new ParsingException(ex);
					        }
					    
					}
					break;
				case 6 :
					// org/xembly/Xembly.g:114:5: 'ADDIF' argument
					{
					match(input,9,FOLLOW_9_in_directive242); 
					pushFollow(FOLLOW_argument_in_directive244);
					argument6=argument();
					state._fsp--;


					        try {
					            ret = new AddIfDirective(argument6.toString());
					        } catch (XmlContentException ex) {
					            throw new ParsingException(ex);
					        }
					    
					}
					break;
				case 7 :
					// org/xembly/Xembly.g:123:5: 'REMOVE'
					{
					match(input,12,FOLLOW_12_in_directive262); 
					 ret = new RemoveDirective(); 
					}
					break;
				case 8 :
					// org/xembly/Xembly.g:126:5: 'STRICT' argument
					{
					match(input,14,FOLLOW_14_in_directive280); 
					pushFollow(FOLLOW_argument_in_directive282);
					argument7=argument();
					state._fsp--;

					 ret = new StrictDirective(Integer.parseInt(argument7.toString())); 
					}
					break;
				case 9 :
					// org/xembly/Xembly.g:129:5: 'UP'
					{
					match(input,15,FOLLOW_15_in_directive300); 
					 ret = new UpDirective(); 
					}
					break;
				case 10 :
					// org/xembly/Xembly.g:132:5: 'PI' target= argument data= argument
					{
					match(input,11,FOLLOW_11_in_directive318); 
					pushFollow(FOLLOW_argument_in_directive322);
					target=argument();
					state._fsp--;

					pushFollow(FOLLOW_argument_in_directive326);
					data=argument();
					state._fsp--;


					        try {
					            ret = new PiDirective(target.toString(), data.toString());
					        } catch (XmlContentException ex) {
					            throw new ParsingException(ex);
					        }
					    
					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ret;
	}
	// $ANTLR end "directive"



	// $ANTLR start "argument"
	// org/xembly/Xembly.g:142:1: argument returns [Object ret] : TEXT ;
	public final Object argument() throws RecognitionException {
		Object ret = null;


		Token TEXT8=null;

		try {
			// org/xembly/Xembly.g:143:5: ( TEXT )
			// org/xembly/Xembly.g:144:5: TEXT
			{
			TEXT8=(Token)match(input,TEXT,FOLLOW_TEXT_in_argument357); 
			 ret = (TEXT8!=null?TEXT8.getText():null); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
		return ret;
	}
	// $ANTLR end "argument"

	// Delegated rules



	public static final BitSet FOLLOW_directive_in_directives76 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_SEMICOLON_in_directives86 = new BitSet(new long[]{0x000000000003FF00L});
	public static final BitSet FOLLOW_EOF_in_directives109 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_16_in_directive134 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_argument_in_directive136 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_13_in_directive154 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_argument_in_directive156 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_17_in_directive174 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_argument_in_directive176 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_10_in_directive194 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_argument_in_directive198 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_COMMA_in_directive200 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_argument_in_directive204 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_8_in_directive222 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_argument_in_directive224 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_directive242 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_argument_in_directive244 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_12_in_directive262 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_14_in_directive280 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_argument_in_directive282 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_15_in_directive300 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_11_in_directive318 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_argument_in_directive322 = new BitSet(new long[]{0x0000000000000080L});
	public static final BitSet FOLLOW_argument_in_directive326 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_TEXT_in_argument357 = new BitSet(new long[]{0x0000000000000002L});
}