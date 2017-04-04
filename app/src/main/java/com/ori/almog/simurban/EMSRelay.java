package com.ori.almog.simurban;

class EMSRelay extends Subsystem {

    private Relay ems;

    public EMSRelay(){
        this.ems = new Relay();
    }

    @Override
    protected void destination(Stimulus s) {
        ems.propagate(s);
    }

    @Override
    protected void compute() {

    }
}
