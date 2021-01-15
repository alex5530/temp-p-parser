package net.alex.app.parser.entity;

import java.util.Objects;

public class DickWorld implements Comparable{
    private String world;
    private Integer amount;

    public DickWorld(String world, int amount) {
        this.world = world;
        this.amount = amount;
    }

    public DickWorld(String world) {
        this(world, 1);
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void addAmount(){
        amount = amount+1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DickWorld dickWorld = (DickWorld) o;
        return Objects.equals(world, dickWorld.world);
    }

    @Override
    public int hashCode() {
        return Objects.hash(world);
    }


    @Override
    public int compareTo(Object o) {
        DickWorld dw = (DickWorld) o;
        return Integer.compare(amount,dw.getAmount());
    }

//    @Override
//    public int compareTo(Object o) {
//        DickWorld dw = (DickWorld) o;
//        return Objects.compare(world,dw.getWorld(), Comparator.comparing(String::toString));
//    }



}
