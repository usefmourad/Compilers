// Generated from /Users/usefmourad/Downloads/UNI/ACL/ACL ST7/grammers/Task7.g4 by ANTLR 4.13.1
package csen1002.main.task7;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class Task7Lexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ONE=1, ZERO=2, ERROR=3;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"ONE", "ZERO", "ERROR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ONE", "ZERO", "ERROR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public Task7Lexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Task7.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0003.\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0003\u0000\u0011\b\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"\"\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002-\b\u0002"+
		"\u0000\u0000\u0003\u0001\u0001\u0003\u0002\u0005\u0003\u0001\u0000\u0000"+
		"7\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000"+
		"\u0000\u0005\u0001\u0000\u0000\u0000\u0001\u0010\u0001\u0000\u0000\u0000"+
		"\u0003!\u0001\u0000\u0000\u0000\u0005,\u0001\u0000\u0000\u0000\u0007\b"+
		"\u00050\u0000\u0000\b\t\u00050\u0000\u0000\t\u0011\u00050\u0000\u0000"+
		"\n\u000b\u00050\u0000\u0000\u000b\f\u00051\u0000\u0000\f\u0011\u00051"+
		"\u0000\u0000\r\u000e\u00051\u0000\u0000\u000e\u000f\u00051\u0000\u0000"+
		"\u000f\u0011\u00051\u0000\u0000\u0010\u0007\u0001\u0000\u0000\u0000\u0010"+
		"\n\u0001\u0000\u0000\u0000\u0010\r\u0001\u0000\u0000\u0000\u0011\u0002"+
		"\u0001\u0000\u0000\u0000\u0012\u0013\u00050\u0000\u0000\u0013\u0014\u0005"+
		"0\u0000\u0000\u0014\"\u00051\u0000\u0000\u0015\u0016\u00050\u0000\u0000"+
		"\u0016\u0017\u00051\u0000\u0000\u0017\"\u00050\u0000\u0000\u0018\u0019"+
		"\u00051\u0000\u0000\u0019\u001a\u00050\u0000\u0000\u001a\"\u00050\u0000"+
		"\u0000\u001b\u001c\u00051\u0000\u0000\u001c\u001d\u00050\u0000\u0000\u001d"+
		"\"\u00051\u0000\u0000\u001e\u001f\u00051\u0000\u0000\u001f \u00051\u0000"+
		"\u0000 \"\u00050\u0000\u0000!\u0012\u0001\u0000\u0000\u0000!\u0015\u0001"+
		"\u0000\u0000\u0000!\u0018\u0001\u0000\u0000\u0000!\u001b\u0001\u0000\u0000"+
		"\u0000!\u001e\u0001\u0000\u0000\u0000\"\u0004\u0001\u0000\u0000\u0000"+
		"#-\u000201\u0000$%\u00050\u0000\u0000%-\u00050\u0000\u0000&\'\u00050\u0000"+
		"\u0000\'-\u00051\u0000\u0000()\u00051\u0000\u0000)-\u00050\u0000\u0000"+
		"*+\u00051\u0000\u0000+-\u00051\u0000\u0000,#\u0001\u0000\u0000\u0000,"+
		"$\u0001\u0000\u0000\u0000,&\u0001\u0000\u0000\u0000,(\u0001\u0000\u0000"+
		"\u0000,*\u0001\u0000\u0000\u0000-\u0006\u0001\u0000\u0000\u0000\u0004"+
		"\u0000\u0010!,\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}