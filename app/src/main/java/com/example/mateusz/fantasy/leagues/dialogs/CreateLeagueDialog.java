package com.example.mateusz.fantasy.leagues.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.mateusz.fantasy.R;


public class CreateLeagueDialog extends DialogFragment {
    CreateLeagueDialogListener mListener;
    private String name;

    public String getName() {
        return name;
    }

    public interface CreateLeagueDialogListener {
        public void onDialogPositiveClick(CreateLeagueDialog dialog);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            mListener = (CreateLeagueDialogListener) getTargetFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException("Calling Fragment must implement CreateLeagueDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final View view = inflater.inflate(R.layout.dialog_create_league, null);
        builder.setView(view)
                .setPositiveButton(getText(R.string.create), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText eTCreateLeague = view.findViewById(R.id.et_create_league);
                        name = eTCreateLeague.getText().toString();
                        if (!TextUtils.isEmpty(name)) mListener.onDialogPositiveClick(CreateLeagueDialog.this);
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
