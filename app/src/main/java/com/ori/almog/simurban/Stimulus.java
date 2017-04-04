package com.ori.almog.simurban;

class Stimulus {
    public String name;
    public Change change;
    public PropagationDirection direction; //TODO: This direction is absolutely overused

    enum PropagationDirection{
        Up,
        Down
    };
}
