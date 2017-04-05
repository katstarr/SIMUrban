package com.ori.almog.simurban;

import java.util.ArrayList;

public class Communicator {

    protected ArrayList<Communicator> upRelays = new ArrayList<>();
    protected ArrayList<Communicator> downRelays = new ArrayList<>();

    private ArrayList<Communicator> comms;

    public String name;

    public Communicator(){
        this.name = "Generic communicator";
    }

    protected void bindUpRelay(Communicator r){ this.upRelays.add(r); }

    protected void bindDownRelay(Communicator r) {
        this.downRelays.add(r);
    }

    protected void propagate(Stimulus s){

        switch(s.direction){

            case Up:
                this.comms = this.upRelays;
                break;

            case Down:
                this.comms = this.downRelays;
                break;

        }

        for (Communicator r : comms){
            if (r instanceof Subsystem){
                ((Subsystem)r).destination(s);
            }
            else {
                r.propagate(s);
            }
        }
    }
}
