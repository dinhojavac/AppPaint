package com.example.gcatech.paint;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;


public class Dialogo extends DialogFragment {

    LayoutInflater inflater;
    View customView;

    public Dialog onCreateDialog(Bundle saveInstanceState){
        inflater=getActivity().getLayoutInflater();
        //customView=inflater.inflate(R.layout.dialogo,null);

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setView(customView).setPositiveButton("OK",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int which){

            }
        });
        return builder.create();
    }


    /*
    public AlertDialog dialogo(){
        AlertDialog.Builder b=new AlertDialog.Builder(getActivity());
        b.setTitle("Titulo");
        b.setMessage("el mensaje para el usuario");
        b.setPositiveButton("Ok",
                new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog,int which){
                        //listener.onPossitiveButtonClick();
                        Toast.makeText(getActivity(),R.string.ok,Toast.LENGTH_SHORT).show();
                    }
                });
        b.setNegativeButton(android.R.string.cancel,null);
        //Dialog d=b.create();
        //b.show();
        return b.create();
    }*/
    /*
    LayoutInflater inflater;
    View customView;

    public Dialog onCreateDialog(Bundle saveInstanceState){
        inflater=getActivity().getLayoutInflater();
        customView=inflater.inflate(R.layout.dialogo,null);

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setView(customView).setPositiveButton("OK",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog,int which){

            }
        });
        return builder.create();
    }
    */

}
