// The MIT License (MIT)
//
// Copyright (c) 2013 G. Elian Gidoni
//
// Permission is hereby granted, free of charge, to any person obtaining a copy of
// this software and associated documentation files (the "Software"), to deal in
// the Software without restriction, including without limitation the rights to
// use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
// the Software, and to permit persons to whom the Software is furnished to do so,
// subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all
// copies or substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
// FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
// COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
// IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
// CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
//
// HSMgen code generator.
///

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class HSMgen extends HSMgenBaseListener
{
    private static enum SymType
    {
        NUMBER,
        STRING,
        ARRAY,
        IDENTIFIER,
    }

    private static class Symbol
    {
        Symbol()
        {
            this(null, null, null);
        }
        Symbol(SymType symType, String symName, Object symValue)
        {
            type = symType;
            name = symName;
            value = symValue;
        }
        public SymType type;
        public String name;
        public Object value;
    }

    // Arrays can also be used for record definitions.
    private static class ArraySym implements Iterable<Symbol>
    {
        public ArraySym()
        {
            elems = new Vector<Symbol>();
        }
        public ArraySym(ArraySym other)
        {
            elems = new Vector<Symbol>(other.elems);
        }
        public void addElem(Symbol value)
        {
            elems.add(value);
        }
        public Symbol getElemAt(int index)
        {
            return elems.get(index);
        }
        public Symbol getElem(String name)
        {
            for (Symbol s: elems){
                if (name.equals(s.name)){
                    return s;
                }
            }
            return null;
        }
        public Iterator<Symbol> iterator()
        {
            return elems.iterator();
        }
        public int size()
        {
            return elems.size();
        }

        private Vector<Symbol> elems;
    }

    private static class SymEnv
    {
        public SymEnv()
        {
            syms = new HashMap<String, Symbol>();
        }
        public void put(String name, Symbol value)
        {
            syms.put(name, value);
        }
        public Symbol get(String name)
        {
            return syms.get(name);
        }
        public boolean isSymDefined(String name)
        {
            return syms.containsKey(name);
        }

        private HashMap<String, Symbol> syms;
    }

    private static class FSMTranslator
    {
        FSMTranslator()
        {
            eventId = 0;
        }
        private String getEventName(Symbol pair)
        {
            Symbol e = ((ArraySym)pair.value).getElemAt(0);
            String estr = new String();
            if (e.type == SymType.NUMBER){
                estr = "timeout" + String.valueOf((Integer)e.value);
            }else{
                estr = (String) e.value;
            }
            return estr;
        }
        private Vector<String> getEvents(Symbol fsm)
        {
            Vector<String> events = new Vector<String>();
            for (Symbol s: (ArraySym)fsm.value){
                for (Symbol pair: (ArraySym)s.value){
                    String estr = getEventName(pair);
                    if (! events.contains(estr)){
                        events.add(estr);
                    }
                }
            }
            return events;
        }
        private String toUpper(String str)
        {
            String[] words = str.split("_");
            String res = new String(words[0].toUpperCase());
            for (int i=1; i < words.length; ++i){
                res = res + "_" + words[i].toUpperCase();
            }
            return res;
        }
        private String toCamel(String str)
        {
            String[] words = str.split("_");
            String res = new String();
            for (String s: words){
                res = res + s.substring(0,1).toUpperCase() + s.substring(1).toLowerCase();
            }
            return res;
        }
        private Vector<String> getStates(Symbol fsm)
        {
            Vector<String> states = new Vector<String>();
            for (Symbol s: (ArraySym)fsm.value){
                states.add(s.name);
            }
            return states;
        }
        private boolean isInherited(String state, Symbol inherited)
        {
            return ((ArraySym)inherited.value).getElem(state) != null;
        }
        private void dumpEventCodes(Symbol fsm)
        {
            System.out.println("    enum EventCode {");
            for (String e: getEvents(fsm)){
                System.out.println("        EVENT_" + toUpper(e) + " = " + (eventId++) + ",");
            }
            System.out.println("    };");
        }
        private void dumpStateInterface(String stateName, Symbol fsm, ArraySym events, Symbol inherited)
        {
            Vector<String> eNames = getEvents(fsm);
            String camelName = toCamel(stateName);
            System.out.println("class " + camelName + "State\n{\npublic:\n");
            System.out.println("    virtual ~" + camelName + "State(){ }");
            System.out.println("    virtual void enter" + camelName + "State(){ }");
            System.out.println("    virtual void exit" + camelName + "State(){ }\n");
            for(Symbol pair: events){
                String ename = getEventName(pair);
                String trans = (String)((ArraySym)pair.value).getElemAt(1).value;
                System.out.println("    virtual bool " + ename + "_then_" + trans + "_guard(Event *e)=0;");
                if (isInherited(trans, inherited)){
                    System.out.println("    virtual " + toCamel(trans) + "Fsm* "+ ename + "_then_"+trans + "(Event *e)=0;\n");
                }else{
                    System.out.println("    virtual " + toCamel(trans) + "State* "+ ename + "_then_"+trans + "(Event *e)=0;\n");
                }
            }
            System.out.println("};");
        }
        private void dumpFsmInit(Symbol fsm)
        {
            String hooktype = toCamel(fsm.name) + "FsmState";
            String state = getStates(fsm).get(0);
            System.out.println("    void init(" + toCamel(state) + "State* s, " + hooktype + "*fsmState)\n    {");
            System.out.println("        subState = NULL;");
            System.out.println("        curState = s;");
            System.out.println("        curStateId = STATE_" + toUpper(state) + ";");
            System.out.println("        fsmHook = fsmState;");
            System.out.println("    }");
        }
        private void dumpFsmEnterExit(Symbol fsm, Symbol inherited)
        {
            String hooktype = toCamel(fsm.name) + "FsmState";
            System.out.println("    virtual void enter()\n    {");
            System.out.println("        if (fsmHook != NULL){ fsmHook->enter" + hooktype + "(); }");
            System.out.println("        switch (curStateId){");
            for (String name: getStates(fsm)){
                System.out.println("            case STATE_" + toUpper(name) +":");
                System.out.println("                ((" + toCamel(name) + "State*)curState)->enter" + toCamel(name) + "State();");
                System.out.println("            break;\n");
            }
            System.out.println("            default:\n            break;");
            System.out.println("        }");
            System.out.println("        if (subState != NULL){ ((FsmState*)subState)->enter(); }");
            System.out.println("    }");

            System.out.println("    virtual void exit()\n    {");
            System.out.println("        exitSub();");
            System.out.println("        switch (curStateId){");
            for (String name: getStates(fsm)){
                System.out.println("            case STATE_" + toUpper(name) +":");
                System.out.println("                ((" + toCamel(name) + "State*)curState)->exit" + toCamel(name) + "State();");
                System.out.println("            break;\n");
            }
            System.out.println("            default:\n            break;");
            System.out.println("        }");
            System.out.println("        if (fsmHook != NULL){ fsmHook->exit" + hooktype + "(); }");
            System.out.println("    }");
        }
        private void dumpFsmUpdate(Symbol fsm)
        {
        }
        private void dumpFsmTrans(String state, ArraySym events, Symbol inherited)
        {
            String curState = toCamel(state) + "State";
            HashMap<String, Vector<String> > transByState = new HashMap<String, Vector<String> >();
            for(Symbol pair: events){
                String ename = getEventName(pair);
                String trans = (String)((ArraySym)pair.value).getElemAt(1).value;
                if (transByState.containsKey(ename)){
                    transByState.get(ename).add(trans);
                }else{
                    Vector<String> v = new Vector<String>();
                    v.add(trans);
                    transByState.put(ename, v);
                }
            }
            System.out.println("            switch (e->evCode){");
            for(Map.Entry<String, Vector<String> > elem: transByState.entrySet()){
                System.out.print("                case EVENT_" + toUpper(elem.getKey()) + ":\n                ");
                for(String trans: elem.getValue()){
                    System.out.println("if (((" + curState + "*)curState)->" + elem.getKey() +"_then_" + trans + "_guard(e)){");
                    if ((toCamel(trans)+"State").equals(curState)){
                        System.out.println("                    curState = ((" + curState + "*)curState)->" + elem.getKey() + "_then_"+ trans + "(e);");
                    }else{
                        if (isInherited(state, inherited)){
                            System.out.println("Error: redefinition of state '" + state + "'.");
                            System.exit(1);
                        }
                        System.out.println("                    exitSub();");
                        if (! isInherited(trans, inherited)){
                            System.out.println("                    ((" + curState + "*)curState)->exit" + curState + "();");
                            System.out.println("                    curState = ((" + curState + "*)curState)->" + elem.getKey() + "_then_"+ trans + "(e);");
                            System.out.println("                    curStateId = STATE_" + toUpper(trans) + ";");
                            System.out.println("                    ((" + toCamel(trans) + "State*)curState)->enter" + toCamel(trans) + "State();");
                        }else{
                            System.out.println("                    subState = ((" + curState + "*)curState)->" + elem.getKey() + "_then_"+ trans + "(e);");
                            System.out.println("                    ((FsmState*)subState)->enter();");
                        }
                    }
                    System.out.println("                    handled = true;");
                    System.out.print("                }else ");
                }
                System.out.println("{ }");
                System.out.println("                break;");
            }
            System.out.println("                default:\n                break;");
            System.out.println("            }");
        }
        private void dumpFsmSend(Symbol fsm, Symbol inherited)
        {
            System.out.println("    virtual bool sendEvent(Event *e)\n    {");
            System.out.println("        bool handled = false;");
            System.out.println("        if (subState != NULL && ((FsmState*)subState)->sendEvent(e)){ return true; }");
            System.out.println("        switch (curStateId){");
            for (Symbol state: (ArraySym)fsm.value){
                System.out.println("            case STATE_" + toUpper(state.name) +":");
                dumpFsmTrans(state.name, (ArraySym)state.value, inherited);
                System.out.println("            break;\n");
            }
            System.out.println("            default:\n            break;");
            System.out.println("        }");
            System.out.println("        return handled;");
            System.out.println("    }");
        }
        private void dumpFsmVars(Symbol fsm, Symbol inherited)
        {
            System.out.println("    void exitSub()\n    {");
            System.out.println("        if (subState != NULL){");
            System.out.println("            ((FsmState*)subState)->exit();");
            System.out.println("            subState = NULL;");
            System.out.println("        }");
            System.out.println("    }");

            System.out.println("    enum StateCode {");
            for (String name: getStates(fsm)){
                System.out.println("        STATE_" + toUpper(name) + ",");
            }
            System.out.println("    };");
            System.out.println("    " + toCamel(fsm.name) + "FsmState *fsmHook;");
            System.out.println("    void *curState, *subState;");
            System.out.println("    StateCode curStateId;");
        }
        public void dumpFsm(Symbol fsm, Symbol inherited)
        {
            for (Symbol state: (ArraySym)fsm.value){
                System.out.println("class " + toCamel(state.name) + "State;");
            }
            for (Symbol state: (ArraySym)fsm.value){
                dumpStateInterface(state.name, fsm, (ArraySym)state.value, inherited);
            }
            System.out.println("class " + toCamel(fsm.name) + "FsmState\n{\npublic:");
            System.out.println("    virtual ~" + toCamel(fsm.name) + "FsmState(){ }");
            System.out.println("    virtual void enter" + toCamel(fsm.name) + "FsmState()=0;");
            System.out.println("    virtual void exit" + toCamel(fsm.name) + "FsmState()=0;");
            System.out.println("};");

            System.out.println("class " + toCamel(fsm.name) + "Fsm : public FsmState\n{\npublic:");
            dumpEventCodes(fsm);
            dumpFsmInit(fsm);
            dumpFsmEnterExit(fsm, inherited);
            dumpFsmUpdate(fsm);
            dumpFsmSend(fsm, inherited);
            System.out.println("protected:");
            dumpFsmVars(fsm, inherited);
            System.out.println("};");
        }
        public void dumpGlobal()
        {
            System.out.println("class Event");
            System.out.println("{");
            System.out.println("public:");
            System.out.println("    int evCode;");
            System.out.println("};");
            System.out.println("class FsmState\n{\npublic:\n");
            System.out.println("    virtual ~FsmState(){ }");
            System.out.println("    virtual bool sendEvent(Event * e)=0;");
            System.out.println("    virtual void exit()=0;");
            System.out.println("    virtual void enter()=0;");
            System.out.println("};");
        }
        int eventId;
    }

    // Translation functions.
    ///

    private static FSMTranslator HSMgen = new FSMTranslator();
    private static Stack<SymEnv> envs = new Stack<SymEnv>();
    private static ParseTreeProperty<Symbol> annotations = new ParseTreeProperty<Symbol>();

    // Symbol table.

    private static void pushEnv(SymEnv env)
    {
        envs.push(env);
    }
    private static void popEnv()
    {
        if (envs.size() > 1){
            envs.pop();
        }
    }
    private static void defSym(Symbol sym)
    {
        envs.peek().put(sym.name, sym);
    }
    private static Symbol findSym(String name)
    {
        Stack tmp = (Stack) envs.clone();
        while (! tmp.empty()){
            SymEnv t = (SymEnv) tmp.pop();
            if (t.isSymDefined(name)){
                return t.get(name);
            }
        }
        return null;
    }

    // Annotations.

    private static void setAnnotation(ParseTree node, Symbol value)
    {
        annotations.put(node, value);
    }
    private static void delAnnotation(ParseTree node)
    {
        annotations.removeFrom(node);
    }
    private static Symbol getAnnotation(ParseTree node)
    {
        return annotations.get(node);
    }

    // Global section.

    public void exitConstant(@NotNull HSMgenParser.ConstantContext ctx)
    {
        defSym(new Symbol(SymType.NUMBER,
                          ctx.CONSTANT().getText(),
                          Integer.valueOf(ctx.NUMBER().getText())));
    }

    // FSM section.

    public void enterFsmSection(@NotNull HSMgenParser.FsmSectionContext ctx)
    {
        HSMgen.dumpGlobal();
    }

    public void exitFsmSectionStm(@NotNull HSMgenParser.FsmSectionStmContext ctx)
    {
        ArraySym states = new ArraySym();
        for (HSMgenParser.FsmStateStmContext sctx: ctx.fsmStateStm()){
            states.addElem(getAnnotation(sctx));
            delAnnotation(sctx);
        }
        ArraySym inherited = new ArraySym();
        if (ctx.fsmSubState() != null){
            for (TerminalNode i: ctx.fsmSubState().IDENTIFIER()){
                inherited.addElem(new Symbol(SymType.IDENTIFIER, i.getText(), null));
            }
        }
        HSMgen.dumpFsm(new Symbol(SymType.ARRAY, ctx.IDENTIFIER().getText(), states),
                              new Symbol(SymType.ARRAY, null, inherited));
    }

    public void exitFsmStateStm(@NotNull HSMgenParser.FsmStateStmContext ctx)
    {
        ArraySym events = new ArraySym();
        for (HSMgenParser.FsmEventStmContext ectx: ctx.fsmEventStm()){
            events.addElem(getAnnotation(ectx));
            delAnnotation(ectx);
        }
        setAnnotation(ctx, new Symbol(SymType.ARRAY, ctx.IDENTIFIER().getText(), events));
    }

    public void exitFsmEventStm(@NotNull HSMgenParser.FsmEventStmContext ctx)
    {
        Symbol ev;
        String trans = ctx.IDENTIFIER(0).getText();
        if (ctx.event.getType() == HSMgenParser.NUMBER){
            ev = new Symbol(SymType.NUMBER,
                            null,
                            Integer.valueOf(ctx.NUMBER().getText()));
        }else if (ctx.event.getType() == HSMgenParser.CONSTANT){
            ev = new Symbol(SymType.NUMBER,
                            null,
                            findSym(ctx.CONSTANT().getText()).value);
        }else{
            ev = new Symbol(SymType.IDENTIFIER,
                            null,
                            ctx.IDENTIFIER(0).getText());
            trans = ctx.IDENTIFIER(1).getText();
        }
        ArraySym pair = new ArraySym();
        pair.addElem(ev);
        pair.addElem(new Symbol(SymType.IDENTIFIER, null, trans));
        setAnnotation(ctx, new Symbol(SymType.ARRAY, null, pair));
    }

    public void exitEveryRule(@NotNull ParserRuleContext ctx)
    {
    }

    // Application main.
    ///

    public static void main(String[] args) throws Exception
    {
        String inputFile = null;

        // Push global environment.
        pushEnv(new SymEnv());

        if ( args.length>0 ) inputFile = args[0];
        InputStream is = System.in;
        if ( inputFile!=null ) is = new FileInputStream(inputFile);
        HSMgenLexer lexer = new HSMgenLexer(new ANTLRInputStream(is));
        HSMgenParser parser = new HSMgenParser(new CommonTokenStream(lexer));
        parser.setBuildParseTree(true); // tell ANTLR to build a parse tree
        ParseTree tree = parser.init();

        // Needed for 'NULL' definition.
        System.out.println("#include <cstddef>");

        new ParseTreeWalker().walk(new HSMgen(), tree);
    }

}
