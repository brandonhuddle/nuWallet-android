package io.nuwallet.android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import io.nuwallet.android.currencies.Transaction;
import io.nuwallet.android.TransactionsFragment.OnTransactionsFragmentInteractionListener;

public class TransactionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final List<Transaction> mValues;
    private final OnTransactionsFragmentInteractionListener mListener;

    private Context mContext;

    public TransactionsAdapter(Context context, List<Transaction> items, OnTransactionsFragmentInteractionListener listener) {
        mContext = context;
        mValues = items;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_transactions_item, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TransactionViewHolder) {
            TransactionViewHolder tmp = (TransactionViewHolder) holder;
            tmp.mItem = mValues.get(position);

            switch (mValues.get(position).getTransactionType()) {
                case Send:
                    tmp.mTransactionImage.setImageResource(R.drawable.ic_transaction_sent);
                    break;
                case Receive:
                    tmp.mTransactionImage.setImageResource(R.drawable.ic_transaction_received);
                    break;
            }

            tmp.mTransactionAmount.setText(mValues.get(position).getFormattedTransactionAmount());

            switch (mValues.get(position).getStatus()) {
                case Completed:
                    tmp.mApprovalTime.setText(DateUtils.getRelativeDateTimeString(mContext,
                            mValues.get(position).getTimestamp().getTime(),
                            DateUtils.MINUTE_IN_MILLIS, DateUtils.HOUR_IN_MILLIS, 0));
                    break;
                case InProgress:
                    tmp.mApprovalTime.setText(R.string.status_in_progress);
                    break;
                default:
                    tmp.mApprovalTime.setText(R.string.status_error);
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    private class TransactionViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final ImageView mTransactionImage;
        final TextView mTransactionAmount;
        final TextView mApprovalTime;
        Transaction mItem;

        TransactionViewHolder(View view) {
            super(view);
            mView = view;
            mTransactionImage = view.findViewById(R.id.transaction_image);
            mTransactionAmount = view.findViewById(R.id.text_transaction_amount);
            mApprovalTime = view.findViewById(R.id.text_approval_time);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTransactionAmount.getText() + "'";
        }
    }
}
