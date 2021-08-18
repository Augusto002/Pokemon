package com.Webflux.Pokedex.Model;

import java.util.Objects;

public class PokemonEvent {

    private Long eventid;
    private String eventType;

    public PokemonEvent(Long eventid, String eventType) {
        this.eventid = eventid;
        this.eventType = eventType;
    }

    public Long getEventid() {
        return eventid;
    }

    public void setEventid(Long eventid) {
        this.eventid = eventid;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PokemonEvent)) return false;
        PokemonEvent that = (PokemonEvent) o;
        return Objects.equals(eventid, that.eventid) && Objects.equals(eventType, that.eventType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventid, eventType);
    }

    @Override
    public String toString() {
        return "PokemonEvent{" +
                "eventid=" + eventid +
                ", eventType='" + eventType + '\'' +
                '}';
    }

    public PokemonEvent() {
        super();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
