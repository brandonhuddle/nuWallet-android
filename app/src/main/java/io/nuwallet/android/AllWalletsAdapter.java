package io.nuwallet.android;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import io.nuwallet.android.AllWalletsFragment.OnAllWalletsFragmentInteractionListener;
import io.nuwallet.android.currencies.Currency;
import io.nuwallet.android.currencies.Wallet;

public class AllWalletsAdapter extends RecyclerView.Adapter<AllWalletsAdapter.ViewHolder> {
    private final List<Wallet> mValues;
    private final OnAllWalletsFragmentInteractionListener mListener;

    public AllWalletsAdapter(List<Wallet> items, OnAllWalletsFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_all_wallets_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mWalletName.setText(mValues.get(position).getName());
        holder.mWalletBalance.setText(mValues.get(position).getBalance(Currency.RaiBlocks));

        CardView cardView = holder.mView.findViewById(R.id.card_view);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onAllWalletsFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mWalletName;
        public final TextView mWalletBalance;
        public Wallet mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mWalletName = (TextView) view.findViewById(R.id.text_wallet_name);
            mWalletBalance = (TextView) view.findViewById(R.id.text_wallet_balance);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mWalletName.getText() + "'";
        }
    }
}
