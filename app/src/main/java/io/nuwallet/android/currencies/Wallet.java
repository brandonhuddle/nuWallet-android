package io.nuwallet.android.currencies;

import java.io.Serializable;

public abstract class Wallet implements Serializable {
    public abstract String getName();
    public abstract String getBalance(Currency currency);
}
