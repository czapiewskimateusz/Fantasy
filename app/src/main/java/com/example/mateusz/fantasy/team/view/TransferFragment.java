package com.example.mateusz.fantasy.team.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.mateusz.fantasy.R;
import com.example.mateusz.fantasy.team.model.Player;

import java.util.ArrayList;


public class TransferFragment extends Fragment {
    private ArrayList<Player> playersToTransfer;
    private Button makeTransfersButton;

    public TransferFragment() {
        // Required empty public constructor
    }

    public static TransferFragment newInstance() {
        TransferFragment fragment = new TransferFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transfer, container, false);
        makeTransfersButton = view.findViewById(R.id.btn_make_transfers);
        makeTransfersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TeamFragment newFragment = new TeamFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.transfer_fragment_container, newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

}
