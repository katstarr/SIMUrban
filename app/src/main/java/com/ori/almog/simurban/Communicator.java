package com.ori.almog.simurban;

import java.util.ArrayList;

public class Communicator {

    protected ArrayList<Communicator> upRelays = new ArrayList<>();
    protected ArrayList<Communicator> downRelays = new ArrayList<>();

    public Communicator(){}

    protected void bindUpRelay(Relay r){
        this.upRelays.add(r);
    }

    protected void bindDownRelay(Relay r) {
        this.downRelays.add(r);
    }
}
