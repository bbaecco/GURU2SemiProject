package com.example.semiproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtil {
    public static void showDialog(Context context, String title, String msg, String okMsg, DialogInterface.OnClickListener okListener, String cancleMsg, DialogInterface.OnClickListener cancleListener){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg);

        if(okListener != null){
            builder.setPositiveButton(okMsg, okListener);
        }

        if (cancleListener != null){
            builder.setNegativeButton(cancleMsg, cancleListener);
        }


//        //긍정 대답
//        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                //TODO
//
//            }
//        });
//
//        //부정 대답
//        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                //TODO
//
//            }
//        });
        builder.show();  //표시
    }
}
