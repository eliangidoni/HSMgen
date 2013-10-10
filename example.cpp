#include "example.h"
#include <iostream>

class OutaStateIMP : public OutaState
{
public:
        virtual void
        enterOutaState(){
                std::cout<<"ENTER OUTA STATE\n";
        }
        virtual void
        exitOutaState(){
                std::cout<<"EXIT OUTA STATE\n";
        }
};

class SecondStateIMP : public SecondState
{
public:
        virtual void
        enterSecondState(){
                std::cout<<"ENTER SECOND STATE\n";
        }
        virtual void
        exitSecondState(){
                std::cout<<"EXIT SECOND STATE\n";
        }
        virtual bool
        eventtwo_then_outa_guard(Event *e){
                return true;
        }
        virtual OutaState*
        eventtwo_then_outa(Event *e){
                return new OutaStateIMP();
        }
};

class FirstStateIMP : public FirstState
{
public:

        virtual void
        enterFirstState(){
                std::cout<<"ENTER FIRST STATE\n";
        }
        virtual void
        exitFirstState(){
                std::cout<<"EXIT FIRST STATE\n";
        }
        virtual bool
        eventone_then_second_guard(Event *e){
                return true;
        }
        virtual SecondState*
        eventone_then_second(Event *e){
                return new SecondStateIMP();
        }
};

class InnerFsmStateIMP : public InnerFsmState
{
public:
        virtual void
        enterInnerFsmState(){
                std::cout<<"ENTER INNER FSM STATE\n";
        }
        virtual void
        exitInnerFsmState(){
                std::cout<<"EXIT INNER FSM STATE\n";
        }
};

Inner2Fsm * f2 = new Inner2Fsm();
class OtherstateStateIMP : public OtherstateState, NnnState
{
public:
        virtual void
        enterOtherstateState(){
                std::cout<<"ENTER OTHER\n";
        }
        virtual void
        exitOtherstateState(){
                std::cout<<"EXIT OTHER\n";
        }
        virtual bool
        eventfour_then_inner2_guard(Event *e){
                return true;
        }
        virtual Inner2Fsm*
        eventfour_then_inner2(Event *e){
                return f2;
        }
        virtual void
        enterNnnState(){
                std::cout<<"ENTER NNN STATE\n";
        }
        virtual void
        exitNnnState(){
                std::cout<<"EXIT NNN STATE\n";
        }
        virtual bool
        eventbla_then_nnn_guard(Event *e){
                return true;
        }
        virtual NnnState*
        eventbla_then_nnn(Event *e){
                return this;
        }
};

InnerFsm * f = new InnerFsm();
class StateStateIMP: public StateState
{
public:

        virtual void
        enterStateState(){
                std::cout<<"ENTER STATE STATE\n";
        }
        virtual void
        exitStateState(){
                std::cout<<"EXIT STATE STATE\n";
        }
        virtual bool
        eventthree_then_otherstate_guard(Event *e){
                return true;
        }
        virtual OtherstateState*
        eventthree_then_otherstate(Event *e){
                return new OtherstateStateIMP();
        }
        virtual bool
        eventfour_then_inner_guard(Event *e){
                return true;
        }
        virtual InnerFsm*
        eventfour_then_inner(Event *e){
                return f;
        }
};

class ChachaStateIMP:public ChachaState
{
public:
        virtual void
        enterChachaState(){
                std::cout<<"ENTER CHACHA STATE\n";
        }
        virtual void
        exitChachaState(){
                std::cout<<"EXIT CHACHA STATE\n";
        }

};
class Inner2FsmStateIMP:public Inner2FsmState
{
public:
        virtual void
        enterInner2FsmState(){
                std::cout<<"ENTER FSM INNER 2\n";
        }
        virtual void
        exitInner2FsmState(){
                std::cout<<"EXIT FSM INNER 2\n";
        }
};

class OuterFsmStateIMP: public OuterFsmState
{
public:

        virtual void
        enterOuterFsmState(){
                std::cout<<"ENTER FSM OUTER\n";
        }
        virtual void
        exitOuterFsmState(){
                std::cout<<"EXIT FSM OUTER\n";
        }
};

int main()
{
        OuterFsm out, out2;
        Event e;

        f->init(new FirstStateIMP(), new InnerFsmStateIMP());
        f2->init(new ChachaStateIMP(), new Inner2FsmStateIMP());

        out.init(new StateStateIMP(), new OuterFsmStateIMP());
        out.enter();

        e.evCode = OuterFsm::EVENT_EVENTTHREE;
        out.sendEvent(&e);

        e.evCode = OuterFsm::EVENT_EVENTBLA;
        out.sendEvent(&e);

        out.exit();

        std::cout<<"----------------\n";

        out2.init(new StateStateIMP(), new OuterFsmStateIMP());
        out2.enter();

        e.evCode = OuterFsm::EVENT_EVENTFOUR;
        out2.sendEvent(&e);

        e.evCode = InnerFsm::EVENT_EVENTONE;
        out2.sendEvent(&e);

        e.evCode = OuterFsm::EVENT_EVENTTHREE;
        out2.sendEvent(&e);

        e.evCode = OuterFsm::EVENT_EVENTFOUR;
        out2.sendEvent(&e);

        e.evCode = OuterFsm::EVENT_EVENTBLA;
        std::cout << out2.sendEvent(&e) << "\n";
        std::cout << out2.sendEvent(&e) << "\n";

        out2.exit();

        return 0;
}
