package org.example.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {

    private Long id;

    private String transactionName;

    private double amount;

    private String type;

    private String note;

    public Transaction(Long id, String transactionName, double amount, String type, String note) {
        this.id = id;
        this.transactionName = transactionName;
        this.amount = amount;
        this.type = type;
        this.note = note;
    }

    public Transaction(){}

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    @JsonProperty("amount")
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @JsonProperty("type")
    public String getTypeTransaction() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("note")
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", transactionName='" + transactionName + '\'' +
                ", amount=" + amount +
                ", type=" + type +
                ", note='" + note + '\'' +
                '}';
    }
}

