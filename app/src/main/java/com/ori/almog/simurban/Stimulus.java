package com.ori.almog.simurban;

class Stimulus {
    public String name;
    public Change change;
    public PropagationDirection direction;

    enum PropagationDirection{
        Up,
        Down
    };
}
