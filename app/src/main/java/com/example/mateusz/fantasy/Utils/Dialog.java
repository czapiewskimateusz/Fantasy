package com.example.mateusz.fantasy.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Toast;

import com.example.mateusz.fantasy.R;

/**
 * Created by Mateusz on 25.09.2017.
 */

public class Dialog {

    public static AlertDialog getAlertDialog(final Activity activity) {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.dialog_join_league, null))
                .setTitle("Join league")
                .setPositiveButton("join", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(activity, "Clicked POSITIVE", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(activity, "Clicked NEGATIVE", Toast.LENGTH_SHORT).show();
                    }
                });

        AlertDialog dialog = builder.create();

        return dialog;
    }
}
