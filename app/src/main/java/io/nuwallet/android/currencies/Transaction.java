package io.nuwallet.android.currencies;

import android.support.annotation.NonNull;

import java.util.Date;

public class Transaction {
    private TransactionType mTransactionType;
    private TransactionStatus mTransactionStatus;
    private long mTransactionAmount;
    private Date mTimestamp;

    public Transaction(TransactionType transactionType, TransactionStatus transactionStatus,
                       long transactionAmount, @NonNull Date timestamp) {
        mTransactionType = transactionType;
        mTransactionStatus = transactionStatus;
        mTransactionAmount = transactionAmount;
        mTimestamp = timestamp;
    }

    public TransactionType getTransactionType() {
        return mTransactionType;
    }

    public TransactionStatus getStatus() {
        return mTransactionStatus;
    }

    // TODO: Split this into the .iota package and format it correctly
    public String getFormattedTransactionAmount() {
        switch (mTransactionType) {
            case Receive:
                return "+ " + String.valueOf(getTransactionAmount()) + " i";
            case Send:
                return "- " + String.valueOf(getTransactionAmount()) + " i";
        }

        // THIS WILL NEVER BE REACHED
        return null;
    }

    public long getTransactionAmount() {
        return mTransactionAmount;
    }

    public Date getTimestamp() {
        return mTimestamp;
    }
}
