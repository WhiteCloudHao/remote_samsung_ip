package com.example.remote_samsung_by_ip_method.utils;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
public class Aleart {
    public static void showAlert(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Đóng cửa sổ thông báo khi người dùng bấm nút "OK"
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
