package com.binhdi0111.bka.customview;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.SimpleTimeZone;

public class SecondActivity extends AppCompatActivity {
    EditText edt1,edtDate1,edtDate2,edtTime1,edtTime2;
    TextView tvGroup,tvMaSo;
    Calendar calendar;
    private SimpleDateFormat simpleDateFormat;
    SimpleTimeZone simpleTimeZone;
    ImageView imgDate1,imgDate2,imgTime1,imgTime2,imgBack;
    int gio,phut;
    int REQUEST_CODE_FILE =123;
    Button btnFile;
    private Spinner spinner,spinner2;
    ArrayAdapter adapter,adapter1;
    ArrayList<String> arrayList,arrayList1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        imgBack =(ImageView)findViewById(R.id.imageView2);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        edt1 = (EditText) findViewById(R.id.editText1);
        edt1.setOnKeyListener(onSoftKeyboardDonePress);
        imgDate1 = (ImageView) findViewById(R.id.imageViewDate);
        edtDate1 = (EditText) findViewById(R.id.editTextTime1);
        imgDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgay(edtDate1);
            }
        });
        imgDate2 = (ImageView) findViewById(R.id.imageViewDate1);
        edtDate2 = (EditText) findViewById(R.id.editTextTime2);
        imgDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgay(edtDate2);
            }
        });
        imgTime1 =(ImageView) findViewById(R.id.imageViewTime1);
        edtTime1 = (EditText) findViewById(R.id.editTextTime3);
        imgTime1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonGio(edtTime1);
            }
        });
        imgTime2 =(ImageView) findViewById(R.id.imageViewTime2);
        edtTime2 = (EditText) findViewById(R.id.editTextTime4);
        imgTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonGio(edtTime2);
            }
        });
        btnFile = (Button) findViewById(R.id.buttonFile);
        btnFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(SecondActivity.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQUEST_CODE_FILE);
            }
        });
        arrayList = new ArrayList<String>();
        arrayList.add("Chọn nhóm công việc");
        arrayList.add("hihi");
        arrayList.add("haha");
        arrayList.add("hehehehehe");
        arrayList1 = new ArrayList<String>();
        arrayList1.add("Mã/số hiệu văn bản");
        arrayList1.add("hihi");
        arrayList1.add("haha");
        arrayList1.add("hehehehehe");
        this.spinner = (Spinner) findViewById(R.id.spinner);
        adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinner.setAdapter(adapter);
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        this.spinner2= (Spinner)findViewById(R.id.Spinner2);
        adapter1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,arrayList1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinner2.setAdapter(adapter1);
        this.spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    private View.OnKeyListener onSoftKeyboardDonePress=new View.OnKeyListener()
    {
        public boolean onKey(View v, int keyCode, KeyEvent event)
        {
            if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)
            {
                // code to hide the soft keyboard
                edt1.clearFocus();
                edt1.requestFocus(EditText.FOCUS_DOWN);
            }
            return false;
        }
    };
    private void ChonNgay(EditText editText){
        calendar = Calendar.getInstance();
        int ngay = calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam = calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(i,i1,i2);
                simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                editText.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
    private void ChonGio(EditText editText){
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                gio =i;
                phut = i1;
                editText.setText(String.format(Locale.getDefault(),"%02d:%02d",gio,phut));
            }
        },gio,phut,true);
        timePickerDialog.show();
    }

    private ActivityResultLauncher activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        Uri uri = data.getData();
                    }
                }
            });

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_FILE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            Intent data = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            data.setType("*/*");
            data = Intent.createChooser(data,"choose a file");
            activityResultLauncher.launch(data);
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}