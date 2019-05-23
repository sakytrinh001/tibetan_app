package com.android.dfr.tibetan_dfr.models;

/**
 * Created by MinhThanh on 11/27/17.
 */

public class TransactionObject {

    private String tName;
    private String amount;

    public TransactionObject(String tName, String amount) {
        this.tName = tName;
        this.amount = amount;
    }

    public String gettName() {
        return tName;
    }

    public String getAmount() {
        return amount;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
