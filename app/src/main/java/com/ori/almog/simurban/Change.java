package com.ori.almog.simurban;

import com.ori.almog.simurban.Direction;

class Change {

    public Direction direction;
    public double multiplier;

    public Change(Direction direction, double multiplier){
        this.direction = direction;
        this.multiplier = multiplier;
    }
}
