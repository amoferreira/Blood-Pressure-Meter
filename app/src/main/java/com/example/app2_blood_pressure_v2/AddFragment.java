package com.example.app2_blood_pressure_v2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.jjoe64.graphview.series.DataPoint;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.util.List;


public class AddFragment extends Fragment {


    static final String ACTION_SCAN = "com.google.zxing.client.android.SCAN";
    TextView systolic_value, diastolic_value, rate_value, result;
    private DatabaseHelper databaseHelper;
    private Integer user_id;
    List<PressureRead> reads_user;

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user_id = getArguments().getInt("user_id");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        systolic_value = (TextView) view.findViewById(R.id.systolic_value);
        diastolic_value = (TextView) view.findViewById(R.id.diastolic_value);
        rate_value = (TextView) view.findViewById(R.id.rate_value);
        result = (TextView) view.findViewById(R.id.result);
        Button QRButton = view.findViewById(R.id.qr_button);

        QRButton.setOnClickListener((vw)->scan(true));

        return view;
    }

    public void scan(boolean qrcode) {
        PackageManager pm = getActivity().getPackageManager();
        boolean app_exists = true;
        try {
            ApplicationInfo appInfo = pm.getApplicationInfo("com.google.zxing.client.android", 0);
        } catch (PackageManager.NameNotFoundException e) {
            app_exists = false;
            // TODO Auto-generated catch block
            e.printStackTrace();
            new AlertDialog.Builder(getContext())
                    .setTitle("WARNING:")
                    .setMessage("You don't have Barcode Scanner installed. Please install it.")
                    .setCancelable(false)
                    .setNeutralButton("Install it now", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Uri uri = Uri.parse("market://search?q=pname:com.google.zxing.client.android");
                            startActivity(new Intent(Intent.ACTION_VIEW, uri));
                        }
                    })
                    .show();
        }

        if(app_exists) {
            Intent intent = new Intent(ACTION_SCAN);
            intent.putExtra("SCAN_MODE", qrcode ? "QR_CODE_MODE" : "PRODUCT_MODE");
            startActivityForResult(intent, 0);
        }
    }

    private AlertDialog showDialog(final Activity act, CharSequence title, CharSequence message, CharSequence buttonYes, CharSequence buttonNo) {
        AlertDialog.Builder downloadDialog = new AlertDialog.Builder(getActivity());
        downloadDialog.setTitle(title);
        downloadDialog.setMessage(message);
        downloadDialog.setPositiveButton(buttonYes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("market://search?q=pname:" + "com.google.zxing.client.android");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                act.startActivity(intent);
            }
        });
        downloadDialog.setNegativeButton(buttonNo, null);
        AlertDialog alert = downloadDialog.create();
        return alert;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==0) {
            String contents = data.getStringExtra("SCAN_RESULT");
            ByteBuffer bf = ByteBuffer.wrap(contents.getBytes(StandardCharsets.ISO_8859_1));
            Timestamp ts = new Timestamp(bf.getLong());
            int sp = bf.getInt();
            int dp = bf.getInt();
            int hr = bf.getInt();

            System.out.println(sp);
            systolic_value.setText(String.valueOf(sp));
            diastolic_value.setText(String.valueOf(dp));
            rate_value.setText(String.valueOf(hr));

            databaseHelper = new DatabaseHelper(getActivity());

            //Insert in Database
            PressureRead pressRead = new PressureRead();
            // Set
            pressRead.setSystolicPressure(sp);
            pressRead.setDiastolicPressure(dp);
            pressRead.setHeartRate(hr);
            pressRead.setTimeRead(ts.toString());
            pressRead.setPressureReadUserID(user_id);

            //see if there is a read with same time (ts)
            reads_user = databaseHelper.getAllReadsByUser(user_id);
            int numberOfReadsEqual = 0;
            int i=0;
            while(i< reads_user.size()){
                PressureRead pressure_read_i = reads_user.get(i);
                String reads_text_time = pressure_read_i.getTimeRead();
                System.out.println("Read "+ i +" :" + reads_text_time);
                i=i+1;
                if(reads_text_time.equals(ts.toString())){
                    numberOfReadsEqual=+1;
                    System.out.println("Number of reads equal to new_read "+ numberOfReadsEqual );
                }
            }
            if(numberOfReadsEqual == 0){
                databaseHelper.addPressureRead(pressRead);
            }

            if (sp<=90 && dp<=60){
                result.setText("Low");
                result.setTextColor(0xFF00BCD4);
            } else if ((sp>90 || dp>60) && (sp<=120 && dp<=80)){
                result.setText("Normal");
                result.setTextColor(0xFF4CAF50);
            } else if ((sp>120 || dp>80) && (sp<=140 && dp<=90)) {
                result.setText("Elevated");
                result.setTextColor(0xFFE6DB00);
            } else if ((sp>140 || dp>90) && (sp<=160 && dp<=100)) {
                result.setText("High 1 (stage 1)");
                result.setTextColor(0xFFFF9800);
            } else if (sp>160 || dp>100) {
                result.setText("High 2 (stage 2)");
                result.setTextColor(0xFFFF5722);
            }
        }

    }
}