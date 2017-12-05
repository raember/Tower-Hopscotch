package ch.zhaw.psit.towerhopscotch.models;

public class Gold {
    int amount = 0;

    public Gold(int amount) {
        this.amount = amount;
    }

    public Gold() {
        this(0);
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
