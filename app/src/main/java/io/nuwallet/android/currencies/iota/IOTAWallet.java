package io.nuwallet.android.currencies.iota;

import io.nuwallet.android.currencies.Currency;
import io.nuwallet.android.currencies.Wallet;

public class IOTAWallet extends Wallet {
    private String mName;
    private long mBalance;

    public IOTAWallet(String Name) {
        this.mName = Name;
        this.mBalance = 1000000;
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public String getBalance(Currency currency) {
        switch (currency) {
            // Current Max Value: 2779530283277761
            case IOTA:
                if (mBalance >= 1000000000000000L) {
                    return String.valueOf(mBalance / 1000000000000000L) + " Pi";
                } else if (mBalance >= 1000000000000L) {
                    return String.valueOf(mBalance / 1000000000000L) + " Ti";
                } else if (mBalance >= 1000000000L) {
                    return String.valueOf(mBalance / 1000000000L) + " Gi";
                } else if (mBalance >= 1000000L) {
                    return String.valueOf(mBalance / 1000000L) + " Mi";
                } else if (mBalance >= 1000L) {
                    return String.valueOf(mBalance / 1000L) + " Ki";
                } else {
                    return String.valueOf(mBalance) + " i";
                }
                // TODO: Support currency conversions
            case USD:
                return "{NOT SUPPORTED}";
        }

        return "{NOT SUPPORTED}";
    }
}
