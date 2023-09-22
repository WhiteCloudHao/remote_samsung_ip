package com.example.remote_samsung_by_ip_method;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.InetAddress;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button testButton = findViewById(R.id.testButton);

        // Đặt sự kiện click cho nút
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InetAddress address = InetAddress.getByName("192.168.77.140");
                    SamsungRemote remote = new SamsungRemote(address, MainActivity.this);
                    TVReply reply = remote.authenticate("Toaster"); // Argument is the device name (displayed on television).
                    if (reply == TVReply.ALLOWED) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setTitle("Success")
                                .setMessage("Authentication successful.")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                })
                                .show();
                        remote.keycode("KEY_INFO");
                        remote.keycode("KEY_VOLUP");
                    }
                    remote.close();
                } catch (IOException e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Lỗi")
                            .setMessage(e.getMessage())
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .show();
//                    System.err.println(e.getMessage());
                }
            }
        });
    }
}