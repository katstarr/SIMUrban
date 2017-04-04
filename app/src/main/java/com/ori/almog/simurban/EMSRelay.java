package com.ori.almog.simurban;

class EMSRelay extends Subsystem {

    public Relay ems;

    public EMSRelay(){
        this.ems = new Relay();
        this.ems.name = "EMS Relay component";
    }

    @Override
    protected void destination(Stimulus s) {
        ems.propagate(s);
    }

    @Override
    protected void compute() {

    }
}
