package com.ori.almog.simurban;

import java.util.ArrayList;

public class Relay extends Communicator {

    public Relay(){}

    protected void sendUp(Stimulus s){
        for (Communicator r : this.upRelays){
            if (r instanceof Relay){
                ((Relay)r).sendUp(s);
            }
            else if (r instanceof Subsystem){
                ((Subsystem)r).destination(s);
            }
        }
    }

    protected void sendDown(Stimulus s){
        for (Communicator r : this.downRelays){
            if (r instanceof Relay){
                ((Relay)r).sendDown(s);
            }
            else if (r instanceof Subsystem){
                ((Subsystem)r).destination(s);
            }
        }
    }

}
