package com.example.mateusz.fantasy.Utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.mateusz.fantasy.R;

public class LeagueDialog extends android.support.v4.app.DialogFragment {

    LeagueDialogListener mListener;

    private String code;

    public String getCode() {
        return code;
    }

    public interface LeagueDialogListener {
        public void onDialogPositiveClick(LeagueDialog dialog);
        public void onDialogNegativeClick(LeagueDialog dialog);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (LeagueDialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "Must implement LeagueDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.dialog_join_league, null);
        builder.setView(view)
                .setTitle(getActivity().getText(R.string.join_league_dialog_title))
                .setPositiveButton("join", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        EditText eTjoinLeague = view.findViewById(R.id.et_join_league);
                        code = eTjoinLeague.getText().toString();
                        mListener.onDialogPositiveClick(LeagueDialog.this);

                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        return builder.create();
    }
}
