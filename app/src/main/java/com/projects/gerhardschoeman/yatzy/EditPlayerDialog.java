package com.projects.gerhardschoeman.yatzy;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.projects.gerhardschoeman.yatzy.data.DataContract;
import com.projects.gerhardschoeman.yatzy.data.DataProjections;

import java.io.ByteArrayOutputStream;

/**
 * Created by Gerhard on 24/11/2015.
 */
public class EditPlayerDialog extends DialogFragment {

    private static final String LOGTAG = EditPlayerDialog.class.getSimpleName();

    private final int CAPTURE_PHOTO_RESULT = 4545;

    private byte[] photoBytes;
    private ImageView photo;
    private EditText name;
    private boolean dataUpdated = false;

    private int playerID = -1;
    private String playerName = null;

    public static final String PLAYERID_ARG = "PLAYERID";
    public static final String PLAYERNAME_ARG = "PLAYERNAME";

    private DialogClosedCallBack dlgCallback;

    public interface DialogClosedCallBack{
        void dialogClosed(boolean dataRefreshed);
    }

    public void setDlgCallback(DialogClosedCallBack cb){
        dlgCallback = cb;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if(dlgCallback!=null){
            dlgCallback.dialogClosed(dataUpdated);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.activity_edit_player,null);
        photo = (ImageView)v.findViewById(R.id.editPlayerPhoto);
        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, CAPTURE_PHOTO_RESULT);
            }
        });
        name = (EditText)v.findViewById(R.id.editPlayerName);

        Bundle b = getArguments();
        if(b!=null && (b.containsKey(PLAYERID_ARG) || b.containsKey(PLAYERNAME_ARG))){
            if(b.containsKey(PLAYERID_ARG))
                playerID  = b.getInt(PLAYERID_ARG);
            else
                playerName = b.getString(PLAYERNAME_ARG);
        }
        if(playerID>=0 || playerName!=null){
            Cursor c = null;

            if(playerID>=0)
                c = getActivity().getContentResolver().query(DataContract.PlayerEntry.getUriFromID(playerID),
                    DataProjections.Player_ALL.COLUMNS,
                    null,null,null);
            else
                c = getActivity().getContentResolver().query(DataContract.PlayerEntry.getUriFromName(playerName),
                    DataProjections.Player_ALL.COLUMNS,
                    null,null,null);

            if(c!=null && c.moveToFirst()){
                byte[] p = c.getBlob(DataProjections.Player_ALL.COL_PHOTO);
                if(p!=null){
                    photo.setImageBitmap(BitmapFactory.decodeByteArray(p,0,p.length));
                }
                name.setText(c.getString(DataProjections.Player_ALL.COL_NAME));
                if(playerID<0) playerID = c.getInt(DataProjections.Player_ALL.COL_ID);
            }
        }

        builder.setView(v);
        builder.setPositiveButton(playerID >= 0 ? "Update" : "Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String n = name.getText().toString();
                if (n != null && n.length() > 0) {
                    Log.d("DIALOG", "inserting record");
                    ContentValues cv = new ContentValues();
                    cv.put(DataContract.PlayerEntry.COL_NAME, n);
                    if (photoBytes != null) cv.put(DataContract.PlayerEntry.COL_PHOTO, photoBytes);
                    if (playerID >= 0) {
                        int r = getActivity().getContentResolver().update(DataContract.PlayerEntry.getUriFromID(playerID), cv, null, null);
                        dataUpdated = r>0;
                    } else {
                        Uri r = getActivity().getContentResolver().insert(DataContract.PlayerEntry.CONTENT_URI, cv);
                        dataUpdated = r!=null;
                    }
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dataUpdated = false;
            }
        });
        return builder.create();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==CAPTURE_PHOTO_RESULT && resultCode==Activity.RESULT_OK){
            Bitmap photoData = (Bitmap) data.getExtras().get("data");
            if(photoData!=null) {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photoData.compress(Bitmap.CompressFormat.PNG,100,stream);
                photoBytes = stream.toByteArray();
                photo.setImageBitmap(photoData);
            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
