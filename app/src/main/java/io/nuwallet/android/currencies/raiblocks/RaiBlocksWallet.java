package io.nuwallet.android.currencies.raiblocks;

import java.math.BigInteger;

import io.nuwallet.android.currencies.Currency;
import io.nuwallet.android.currencies.Wallet;

public class RaiBlocksWallet extends Wallet {
    private final static BigInteger _GRai = new BigInteger("1000000000000000000000000000000000");
    private final static BigInteger _MRai = new BigInteger("1000000000000000000000000000000");
    private final static BigInteger _kRai = new BigInteger("1000000000000000000000000000");
    private final static BigInteger _Rai  = new BigInteger("1000000000000000000000000");
    private final static BigInteger _mRai = new BigInteger("1000000000000000000000");
    private final static BigInteger _uRai = new BigInteger("1000000000000000000");

    private String mName;
    private BigInteger mBalance;

    public RaiBlocksWallet(String Name) {
        this.mName = Name;
        this.mBalance = new BigInteger("12000000000000000000000000");
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public String getBalance(Currency currency) {
        switch (currency) {
            case RaiBlocks:
                if (mBalance.compareTo(_GRai) >= 0) {
                    return String.valueOf(mBalance.divide(_GRai)) + " GRai";
                } else if (mBalance.compareTo(_MRai) >= 0) {
                    return String.valueOf(mBalance.divide(_MRai)) + " MRai";
                } else if (mBalance.compareTo(_kRai) >= 0) {
                    return String.valueOf(mBalance.divide(_kRai)) + " kRai";
                } else if (mBalance.compareTo(_Rai) >= 0) {
                    return String.valueOf(mBalance.divide(_Rai))  + " Rai";
                } else if (mBalance.compareTo(_mRai) >= 0) {
                    return String.valueOf(mBalance.divide(_mRai)) + " mRai";
                } else if (mBalance.compareTo(_uRai) >= 0) {
                    return String.valueOf(mBalance.divide(_uRai)) + " µRai";
                } else {
                    return String.valueOf(mBalance) + " raw";
                }
                // TODO: Support currency conversions
            case USD:
                return "{NOT SUPPORTED}";
        }

        return "{NOT SUPPORTED}";
    }
}
