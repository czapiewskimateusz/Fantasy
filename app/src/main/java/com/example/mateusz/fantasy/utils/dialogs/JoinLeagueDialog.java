package com.example.mateusz.fantasy.utils.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.mateusz.fantasy.R;

public class JoinLeagueDialog extends android.support.v4.app.DialogFragment {

    LeagueDialogListener mListener;

    private String code;

    public String getCode() {
        return code;
    }

    public interface LeagueDialogListener {
        public void onDialogPositiveClick(JoinLeagueDialog dialog);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            mListener = (LeagueDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling Fragment must implement LeagueDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.dialog_join_league, null);
        builder.setView(view)
                .setPositiveButton(getText(R.string.join), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        EditText eTJoinLeague = view.findViewById(R.id.et_join_league);
                        code = eTJoinLeague.getText().toString();

                        if (!TextUtils.isEmpty(code)){
                            mListener.onDialogPositiveClick(JoinLeagueDialog.this);
                        }


                    }
                })
                .setNegativeButton(getText(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

        return builder.create();
    }
}
