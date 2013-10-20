/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.yellowme.sample.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 *
 * @author javier
 */
public class Messages {
 
    public static void displayAlert(Context context, String title, String message) {
        AlertDialog.Builder confirm = new AlertDialog.Builder(context);
        confirm.setTitle(title);
        confirm.setMessage(message);
        confirm.setNegativeButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        confirm.show().show();
    }
}
