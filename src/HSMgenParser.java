// Generated from HSMgen.g by ANTLR 4.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HSMgenParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__9=1, T__8=2, T__7=3, T__6=4, T__5=5, T__4=6, T__3=7, T__2=8, T__1=9, 
		T__0=10, CONSTANT=11, IDENTIFIER=12, NUMBER=13, STRING=14, WS=15, COMMENT=16;
	public static final String[] tokenNames = {
		"<INVALID>", "'fsm-info'", "'{'", "'>'", "')'", "','", "'-'", "'('", "'='", 
		"'}'", "';'", "CONSTANT", "IDENTIFIER", "NUMBER", "STRING", "WS", "COMMENT"
	};
	public static final int
		RULE_init = 0, RULE_constantStms = 1, RULE_constant = 2, RULE_fsmSection = 3, 
		RULE_fsmSectionStm = 4, RULE_fsmSubState = 5, RULE_fsmStateStm = 6, RULE_fsmEventStm = 7;
	public static final String[] ruleNames = {
		"init", "constantStms", "constant", "fsmSection", "fsmSectionStm", "fsmSubState", 
		"fsmStateStm", "fsmEventStm"
	};

	@Override
	public String getGrammarFileName() { return "HSMgen.g"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public HSMgenParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class InitContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(HSMgenParser.EOF, 0); }
		public FsmSectionContext fsmSection() {
			return getRuleContext(FsmSectionContext.class,0);
		}
		public ConstantStmsContext constantStms() {
			return getRuleContext(ConstantStmsContext.class,0);
		}
		public InitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_init; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HSMgenListener ) ((HSMgenListener)listener).enterInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HSMgenListener ) ((HSMgenListener)listener).exitInit(this);
		}
	}

	public final InitContext init() throws RecognitionException {
		InitContext _localctx = new InitContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_init);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(17);
			_la = _input.LA(1);
			if (_la==CONSTANT) {
				{
				setState(16); constantStms();
				}
			}

			setState(19); fsmSection();
			setState(20); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantStmsContext extends ParserRuleContext {
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantStmsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantStms; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HSMgenListener ) ((HSMgenListener)listener).enterConstantStms(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HSMgenListener ) ((HSMgenListener)listener).exitConstantStms(this);
		}
	}

	public final ConstantStmsContext constantStms() throws RecognitionException {
		ConstantStmsContext _localctx = new ConstantStmsContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_constantStms);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(22); constant();
				setState(23); match(10);
				}
				}
				setState(27); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CONSTANT );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(HSMgenParser.NUMBER, 0); }
		public TerminalNode CONSTANT() { return getToken(HSMgenParser.CONSTANT, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HSMgenListener ) ((HSMgenListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HSMgenListener ) ((HSMgenListener)listener).exitConstant(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_constant);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(29); match(CONSTANT);
			setState(30); match(8);
			setState(31); match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FsmSectionContext extends ParserRuleContext {
		public ConstantStmsContext constantStms(int i) {
			return getRuleContext(ConstantStmsContext.class,i);
		}
		public List<FsmSectionStmContext> fsmSectionStm() {
			return getRuleContexts(FsmSectionStmContext.class);
		}
		public FsmSectionStmContext fsmSectionStm(int i) {
			return getRuleContext(FsmSectionStmContext.class,i);
		}
		public List<ConstantStmsContext> constantStms() {
			return getRuleContexts(ConstantStmsContext.class);
		}
		public FsmSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fsmSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HSMgenListener ) ((HSMgenListener)listener).enterFsmSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HSMgenListener ) ((HSMgenListener)listener).exitFsmSection(this);
		}
	}

	public final FsmSectionContext fsmSection() throws RecognitionException {
		FsmSectionContext _localctx = new FsmSectionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_fsmSection);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(33); fsmSectionStm();
				setState(35);
				_la = _input.LA(1);
				if (_la==CONSTANT) {
					{
					setState(34); constantStms();
					}
				}

				}
				}
				setState(39); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==1 );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FsmSectionStmContext extends ParserRuleContext {
		public List<FsmStateStmContext> fsmStateStm() {
			return getRuleContexts(FsmStateStmContext.class);
		}
		public FsmSubStateContext fsmSubState() {
			return getRuleContext(FsmSubStateContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(HSMgenParser.IDENTIFIER, 0); }
		public FsmStateStmContext fsmStateStm(int i) {
			return getRuleContext(FsmStateStmContext.class,i);
		}
		public FsmSectionStmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fsmSectionStm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HSMgenListener ) ((HSMgenListener)listener).enterFsmSectionStm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HSMgenListener ) ((HSMgenListener)listener).exitFsmSectionStm(this);
		}
	}

	public final FsmSectionStmContext fsmSectionStm() throws RecognitionException {
		FsmSectionStmContext _localctx = new FsmSectionStmContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_fsmSectionStm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41); match(1);
			setState(42); match(IDENTIFIER);
			setState(44);
			_la = _input.LA(1);
			if (_la==7) {
				{
				setState(43); fsmSubState();
				}
			}

			setState(46); match(2);
			setState(48); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(47); fsmStateStm();
				}
				}
				setState(50); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(52); match(9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FsmSubStateContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER(int i) {
			return getToken(HSMgenParser.IDENTIFIER, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(HSMgenParser.IDENTIFIER); }
		public FsmSubStateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fsmSubState; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HSMgenListener ) ((HSMgenListener)listener).enterFsmSubState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HSMgenListener ) ((HSMgenListener)listener).exitFsmSubState(this);
		}
	}

	public final FsmSubStateContext fsmSubState() throws RecognitionException {
		FsmSubStateContext _localctx = new FsmSubStateContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_fsmSubState);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54); match(7);
			setState(55); match(IDENTIFIER);
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==5) {
				{
				{
				setState(56); match(5);
				setState(57); match(IDENTIFIER);
				}
				}
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(63); match(4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FsmStateStmContext extends ParserRuleContext {
		public FsmEventStmContext fsmEventStm(int i) {
			return getRuleContext(FsmEventStmContext.class,i);
		}
		public TerminalNode IDENTIFIER() { return getToken(HSMgenParser.IDENTIFIER, 0); }
		public List<FsmEventStmContext> fsmEventStm() {
			return getRuleContexts(FsmEventStmContext.class);
		}
		public FsmStateStmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fsmStateStm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HSMgenListener ) ((HSMgenListener)listener).enterFsmStateStm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HSMgenListener ) ((HSMgenListener)listener).exitFsmStateStm(this);
		}
	}

	public final FsmStateStmContext fsmStateStm() throws RecognitionException {
		FsmStateStmContext _localctx = new FsmStateStmContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_fsmStateStm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65); match(IDENTIFIER);
			setState(66); match(2);
			setState(70);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONSTANT) | (1L << IDENTIFIER) | (1L << NUMBER))) != 0)) {
				{
				{
				setState(67); fsmEventStm();
				}
				}
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(73); match(9);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FsmEventStmContext extends ParserRuleContext {
		public Token event;
		public Token state;
		public TerminalNode IDENTIFIER(int i) {
			return getToken(HSMgenParser.IDENTIFIER, i);
		}
		public List<TerminalNode> IDENTIFIER() { return getTokens(HSMgenParser.IDENTIFIER); }
		public TerminalNode NUMBER() { return getToken(HSMgenParser.NUMBER, 0); }
		public TerminalNode CONSTANT() { return getToken(HSMgenParser.CONSTANT, 0); }
		public FsmEventStmContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fsmEventStm; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof HSMgenListener ) ((HSMgenListener)listener).enterFsmEventStm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof HSMgenListener ) ((HSMgenListener)listener).exitFsmEventStm(this);
		}
	}

	public final FsmEventStmContext fsmEventStm() throws RecognitionException {
		FsmEventStmContext _localctx = new FsmEventStmContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_fsmEventStm);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			((FsmEventStmContext)_localctx).event = _input.LT(1);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONSTANT) | (1L << IDENTIFIER) | (1L << NUMBER))) != 0)) ) {
				((FsmEventStmContext)_localctx).event = (Token)_errHandler.recoverInline(this);
			}
			consume();
			setState(76); match(6);
			setState(77); match(3);
			setState(78); ((FsmEventStmContext)_localctx).state = match(IDENTIFIER);
			setState(79); match(10);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\3\22T\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\5\2\24\n\2\3\2"+
		"\3\2\3\2\3\3\3\3\3\3\6\3\34\n\3\r\3\16\3\35\3\4\3\4\3\4\3\4\3\5\3\5\5"+
		"\5&\n\5\6\5(\n\5\r\5\16\5)\3\6\3\6\3\6\5\6/\n\6\3\6\3\6\6\6\63\n\6\r\6"+
		"\16\6\64\3\6\3\6\3\7\3\7\3\7\3\7\7\7=\n\7\f\7\16\7@\13\7\3\7\3\7\3\b\3"+
		"\b\3\b\7\bG\n\b\f\b\16\bJ\13\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\2\n"+
		"\2\4\6\b\n\f\16\20\2\3\3\2\r\17S\2\23\3\2\2\2\4\33\3\2\2\2\6\37\3\2\2"+
		"\2\b\'\3\2\2\2\n+\3\2\2\2\f8\3\2\2\2\16C\3\2\2\2\20M\3\2\2\2\22\24\5\4"+
		"\3\2\23\22\3\2\2\2\23\24\3\2\2\2\24\25\3\2\2\2\25\26\5\b\5\2\26\27\7\2"+
		"\2\3\27\3\3\2\2\2\30\31\5\6\4\2\31\32\7\f\2\2\32\34\3\2\2\2\33\30\3\2"+
		"\2\2\34\35\3\2\2\2\35\33\3\2\2\2\35\36\3\2\2\2\36\5\3\2\2\2\37 \7\r\2"+
		"\2 !\7\n\2\2!\"\7\17\2\2\"\7\3\2\2\2#%\5\n\6\2$&\5\4\3\2%$\3\2\2\2%&\3"+
		"\2\2\2&(\3\2\2\2\'#\3\2\2\2()\3\2\2\2)\'\3\2\2\2)*\3\2\2\2*\t\3\2\2\2"+
		"+,\7\3\2\2,.\7\16\2\2-/\5\f\7\2.-\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\62"+
		"\7\4\2\2\61\63\5\16\b\2\62\61\3\2\2\2\63\64\3\2\2\2\64\62\3\2\2\2\64\65"+
		"\3\2\2\2\65\66\3\2\2\2\66\67\7\13\2\2\67\13\3\2\2\289\7\t\2\29>\7\16\2"+
		"\2:;\7\7\2\2;=\7\16\2\2<:\3\2\2\2=@\3\2\2\2><\3\2\2\2>?\3\2\2\2?A\3\2"+
		"\2\2@>\3\2\2\2AB\7\6\2\2B\r\3\2\2\2CD\7\16\2\2DH\7\4\2\2EG\5\20\t\2FE"+
		"\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2IK\3\2\2\2JH\3\2\2\2KL\7\13\2\2"+
		"L\17\3\2\2\2MN\t\2\2\2NO\7\b\2\2OP\7\5\2\2PQ\7\16\2\2QR\7\f\2\2R\21\3"+
		"\2\2\2\n\23\35%).\64>H";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}