// Generated from HSMgen.g by ANTLR 4.1
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link HSMgenParser}.
 */
public interface HSMgenListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link HSMgenParser#fsmSectionStm}.
	 * @param ctx the parse tree
	 */
	void enterFsmSectionStm(@NotNull HSMgenParser.FsmSectionStmContext ctx);
	/**
	 * Exit a parse tree produced by {@link HSMgenParser#fsmSectionStm}.
	 * @param ctx the parse tree
	 */
	void exitFsmSectionStm(@NotNull HSMgenParser.FsmSectionStmContext ctx);

	/**
	 * Enter a parse tree produced by {@link HSMgenParser#constantStms}.
	 * @param ctx the parse tree
	 */
	void enterConstantStms(@NotNull HSMgenParser.ConstantStmsContext ctx);
	/**
	 * Exit a parse tree produced by {@link HSMgenParser#constantStms}.
	 * @param ctx the parse tree
	 */
	void exitConstantStms(@NotNull HSMgenParser.ConstantStmsContext ctx);

	/**
	 * Enter a parse tree produced by {@link HSMgenParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(@NotNull HSMgenParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link HSMgenParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(@NotNull HSMgenParser.ConstantContext ctx);

	/**
	 * Enter a parse tree produced by {@link HSMgenParser#fsmSubState}.
	 * @param ctx the parse tree
	 */
	void enterFsmSubState(@NotNull HSMgenParser.FsmSubStateContext ctx);
	/**
	 * Exit a parse tree produced by {@link HSMgenParser#fsmSubState}.
	 * @param ctx the parse tree
	 */
	void exitFsmSubState(@NotNull HSMgenParser.FsmSubStateContext ctx);

	/**
	 * Enter a parse tree produced by {@link HSMgenParser#init}.
	 * @param ctx the parse tree
	 */
	void enterInit(@NotNull HSMgenParser.InitContext ctx);
	/**
	 * Exit a parse tree produced by {@link HSMgenParser#init}.
	 * @param ctx the parse tree
	 */
	void exitInit(@NotNull HSMgenParser.InitContext ctx);

	/**
	 * Enter a parse tree produced by {@link HSMgenParser#fsmStateStm}.
	 * @param ctx the parse tree
	 */
	void enterFsmStateStm(@NotNull HSMgenParser.FsmStateStmContext ctx);
	/**
	 * Exit a parse tree produced by {@link HSMgenParser#fsmStateStm}.
	 * @param ctx the parse tree
	 */
	void exitFsmStateStm(@NotNull HSMgenParser.FsmStateStmContext ctx);

	/**
	 * Enter a parse tree produced by {@link HSMgenParser#fsmEventStm}.
	 * @param ctx the parse tree
	 */
	void enterFsmEventStm(@NotNull HSMgenParser.FsmEventStmContext ctx);
	/**
	 * Exit a parse tree produced by {@link HSMgenParser#fsmEventStm}.
	 * @param ctx the parse tree
	 */
	void exitFsmEventStm(@NotNull HSMgenParser.FsmEventStmContext ctx);

	/**
	 * Enter a parse tree produced by {@link HSMgenParser#fsmSection}.
	 * @param ctx the parse tree
	 */
	void enterFsmSection(@NotNull HSMgenParser.FsmSectionContext ctx);
	/**
	 * Exit a parse tree produced by {@link HSMgenParser#fsmSection}.
	 * @param ctx the parse tree
	 */
	void exitFsmSection(@NotNull HSMgenParser.FsmSectionContext ctx);
}