package io.nuwallet.android;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import io.nuwallet.android.currencies.Transaction;
import io.nuwallet.android.currencies.Wallet;
import io.nuwallet.android.TransactionsFragment.OnTransactionsFragmentInteractionListener;
import io.nuwallet.android.SendFragment.OnSendFragmentInteractionListener;
import io.nuwallet.android.ReceiveFragment.OnReceiveFragmentInteractionListener;

public class ViewWalletActivity extends AppCompatActivity
        implements OnTransactionsFragmentInteractionListener, OnSendFragmentInteractionListener,
        OnReceiveFragmentInteractionListener{
    private Fragment mCurrentFragment;
    private Wallet mWallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        mWallet = (Wallet)intent.getSerializableExtra("wallet");

        setContentView(R.layout.activity_view_wallet);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.view_wallet_bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_transactions:
                        setFragment(new TransactionsFragment());
                        return true;
                    case R.id.navigation_send:
                        setFragment(new SendFragment());
                        return true;
                    case R.id.navigation_receive:
                        setFragment(new ReceiveFragment());
                        return true;
                    default:
                        return false;
                }
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.navigation_transactions);
    }

    private void setFragment(Fragment fragment) {
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            if (fragmentManager != null) {
                FragmentTransaction ft = fragmentManager.beginTransaction();

                if (ft != null) {
                    mCurrentFragment = fragment;
                    ft.replace(R.id.view_wallet_content, fragment);
                    ft.commit();
                }
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onTransactionsFragmentInteraction(Transaction transaction) {

    }

    @Override
    public void onSendFragmentInteraction(Uri uri) {

    }

    @Override
    public void onReceiveFragmentInteraction(Uri uri) {

    }
}
