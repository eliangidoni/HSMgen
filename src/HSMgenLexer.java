// Generated from HSMgen.g by ANTLR 4.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class HSMgenLexer extends Lexer {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__9=1, T__8=2, T__7=3, T__6=4, T__5=5, T__4=6, T__3=7, T__2=8, T__1=9, 
		T__0=10, CONSTANT=11, IDENTIFIER=12, NUMBER=13, STRING=14, WS=15, COMMENT=16;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"<INVALID>",
		"'fsm-info'", "'{'", "'>'", "')'", "','", "'-'", "'('", "'='", "'}'", 
		"';'", "CONSTANT", "IDENTIFIER", "NUMBER", "STRING", "WS", "COMMENT"
	};
	public static final String[] ruleNames = {
		"T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", 
		"T__0", "CONSTANT", "IDENTIFIER", "NUMBER", "STRING", "WS", "COMMENT"
	};


	public HSMgenLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "HSMgen.g"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 14: WS_action((RuleContext)_localctx, actionIndex); break;

		case 15: COMMENT_action((RuleContext)_localctx, actionIndex); break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0: skip();  break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1: skip();  break;
		}
	}

	public static final String _serializedATN =
		"\3\uacf5\uee8c\u4f5d\u8b0d\u4a45\u78bd\u1b2f\u3378\2\22q\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b"+
		"\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\7\fA\n\f\f\f\16\fD\13\f\3\r\3\r"+
		"\7\rH\n\r\f\r\16\rK\13\r\3\16\6\16N\n\16\r\16\16\16O\3\17\3\17\3\17\3"+
		"\17\7\17V\n\17\f\17\16\17Y\13\17\3\17\3\17\3\20\6\20^\n\20\r\20\16\20"+
		"_\3\20\3\20\3\21\3\21\7\21f\n\21\f\21\16\21i\13\21\3\21\5\21l\n\21\3\21"+
		"\3\21\3\21\3\21\3W\22\3\3\1\5\4\1\7\5\1\t\6\1\13\7\1\r\b\1\17\t\1\21\n"+
		"\1\23\13\1\25\f\1\27\r\1\31\16\1\33\17\1\35\20\1\37\21\2!\22\3\3\2\t\3"+
		"\2C\\\5\2\62;C\\aa\3\2c|\6\2\62;C\\aac|\3\2\62;\5\2\13\f\17\17\"\"\4\2"+
		"\f\f\17\17x\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\3#\3\2\2\2\5,\3\2\2\2\7.\3\2\2\2\t\60\3\2\2\2\13\62\3\2\2\2\r\64\3"+
		"\2\2\2\17\66\3\2\2\2\218\3\2\2\2\23:\3\2\2\2\25<\3\2\2\2\27>\3\2\2\2\31"+
		"E\3\2\2\2\33M\3\2\2\2\35Q\3\2\2\2\37]\3\2\2\2!c\3\2\2\2#$\7h\2\2$%\7u"+
		"\2\2%&\7o\2\2&\'\7/\2\2\'(\7k\2\2()\7p\2\2)*\7h\2\2*+\7q\2\2+\4\3\2\2"+
		"\2,-\7}\2\2-\6\3\2\2\2./\7@\2\2/\b\3\2\2\2\60\61\7+\2\2\61\n\3\2\2\2\62"+
		"\63\7.\2\2\63\f\3\2\2\2\64\65\7/\2\2\65\16\3\2\2\2\66\67\7*\2\2\67\20"+
		"\3\2\2\289\7?\2\29\22\3\2\2\2:;\7\177\2\2;\24\3\2\2\2<=\7=\2\2=\26\3\2"+
		"\2\2>B\t\2\2\2?A\t\3\2\2@?\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2\2C\30\3"+
		"\2\2\2DB\3\2\2\2EI\t\4\2\2FH\t\5\2\2GF\3\2\2\2HK\3\2\2\2IG\3\2\2\2IJ\3"+
		"\2\2\2J\32\3\2\2\2KI\3\2\2\2LN\t\6\2\2ML\3\2\2\2NO\3\2\2\2OM\3\2\2\2O"+
		"P\3\2\2\2P\34\3\2\2\2QW\7$\2\2RS\7^\2\2SV\7$\2\2TV\13\2\2\2UR\3\2\2\2"+
		"UT\3\2\2\2VY\3\2\2\2WX\3\2\2\2WU\3\2\2\2XZ\3\2\2\2YW\3\2\2\2Z[\7$\2\2"+
		"[\36\3\2\2\2\\^\t\7\2\2]\\\3\2\2\2^_\3\2\2\2_]\3\2\2\2_`\3\2\2\2`a\3\2"+
		"\2\2ab\b\20\2\2b \3\2\2\2cg\7%\2\2df\n\b\2\2ed\3\2\2\2fi\3\2\2\2ge\3\2"+
		"\2\2gh\3\2\2\2hk\3\2\2\2ig\3\2\2\2jl\7\17\2\2kj\3\2\2\2kl\3\2\2\2lm\3"+
		"\2\2\2mn\7\f\2\2no\3\2\2\2op\b\21\3\2p\"\3\2\2\2\13\2BIOUW_gk";
	public static final ATN _ATN =
		ATNSimulator.deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}