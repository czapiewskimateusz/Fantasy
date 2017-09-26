package com.example.mateusz.fantasy.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mateusz.fantasy.R;


public class DialogUtils {

    public static AlertDialog getLeagueJoinDialog(final Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();

        final View view = inflater.inflate(R.layout.dialog_join_league, null);
        builder.setView(view)
                .setTitle(activity.getText(R.string.join_league_dialog_title))
                .setPositiveButton("join", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText eTjoinLeague = view.findViewById(R.id.et_join_league);
                        String code = eTjoinLeague.getText().toString();
                        Toast.makeText(activity, code, Toast.LENGTH_SHORT).show();
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
