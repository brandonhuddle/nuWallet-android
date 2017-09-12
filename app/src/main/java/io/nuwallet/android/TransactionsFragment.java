package io.nuwallet.android;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.nuwallet.android.currencies.Transaction;
import io.nuwallet.android.currencies.TransactionStatus;
import io.nuwallet.android.currencies.TransactionType;

public class TransactionsFragment extends Fragment {
    private OnTransactionsFragmentInteractionListener mListener;

    public TransactionsFragment() {
        // Required empty public constructor
    }

    public static TransactionsFragment newInstance() {
        return new TransactionsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);

        Toolbar toolbar = view.findViewById(R.id.view_wallet_toolbar);
        toolbar.setTitle(R.string.title_transactions);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

        toolbar.inflateMenu(R.menu.fragment_transactions_toolbar);

        RecyclerView transactionsList = view.findViewById(R.id.list_transactions);

        Context context = transactionsList.getContext();
        transactionsList.setLayoutManager(new LinearLayoutManager(context));
        List<Transaction> tmp = new ArrayList<Transaction>();
        tmp.add(new Transaction(TransactionType.Receive, TransactionStatus.Completed, 12,
                new Date()));
        transactionsList.setAdapter(new TransactionsAdapter(getContext(), tmp, mListener));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnTransactionsFragmentInteractionListener) {
            mListener = (OnTransactionsFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnTransactionsFragmentInteractionListener {
        void onTransactionsFragmentInteraction(Transaction transaction);
    }
}
