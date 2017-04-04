package com.ori.almog.simurban;

import java.util.ArrayList;

public class Communicator {

    protected ArrayList<Communicator> upRelays = new ArrayList<>();
    protected ArrayList<Communicator> downRelays = new ArrayList<>();

    public String name;

    public Communicator(){
        this.name = "Generic communicator";
    }

    protected void bindUpRelay(Communicator r){
        this.upRelays.add(r);
    }

    protected void bindDownRelay(Communicator r) {
        this.downRelays.add(r);
    }
}
