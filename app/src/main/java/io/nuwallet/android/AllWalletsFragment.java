package io.nuwallet.android;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.nuwallet.android.currencies.Wallet;
import io.nuwallet.android.currencies.iota.IOTAWallet;

public class AllWalletsFragment extends Fragment {
    private OnAllWalletsFragmentInteractionListener mListener;

    public AllWalletsFragment() {
        // Required empty public constructor
    }

    @SuppressWarnings("unused")
    public static AllWalletsFragment newInstance() {
        return new AllWalletsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_wallets, container, false);

        RecyclerView walletsList = view.findViewById(R.id.list_wallets);

        // Set the adapter
        Context context = walletsList.getContext();
        walletsList.setLayoutManager(new LinearLayoutManager(context));
        List<Wallet> tmp = new ArrayList<Wallet>();
        tmp.add(new IOTAWallet("Main Wallet"));
        walletsList.setAdapter(new AllWalletsAdapter(tmp, mListener));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnAllWalletsFragmentInteractionListener) {
            mListener = (OnAllWalletsFragmentInteractionListener) context;
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

    public interface OnAllWalletsFragmentInteractionListener {
        void onAllWalletsFragmentInteraction(Wallet wallet);
    }
}
