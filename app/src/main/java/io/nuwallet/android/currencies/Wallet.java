package io.nuwallet.android.currencies;

import java.io.Serializable;

/**
 * To make it easier to add new currencies to the wallet I'm trying to make everything as dynamic
 * as possible.
 * This will be the base class for ALL known currency wallets, extend this to add support for
 * another currency.
 */
public abstract class Wallet implements Serializable {
    /**
     * The user defined name for the wallet, this is NOT the name of the supported currency
     * @return
     */
    public abstract String getName();

    /**
     * The balance of this wallet, this is a string since we don't have to do any calculations on it
     * (If we want to access the raw balance then a {@link java.math.BigInteger} or {@link java.math.BigDecimal} is required)
     * @param currency - The currency you want to view the wallet balance as, {@link io.nuwallet.android.currencies.Currency}
     * @return
     */
    public abstract String getBalance(Currency currency);
}
