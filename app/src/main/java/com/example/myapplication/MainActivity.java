package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView txt1, txt2, txt3, txt4;
    private Button btnRan, btnSort, btnSave, btnLoad, btnXoaTrung;
    private EditText edta, edtb;
    private int val1, val2, val3, val4 = 0;
    public void findviews(){
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        btnRan = findViewById(R.id.btnRan);
        btnSort = findViewById(R.id.btnSort);
        btnSave = findViewById(R.id.btnSave);
        btnLoad = findViewById(R.id.btnLoad);
        btnXoaTrung = findViewById(R.id.btnXoaTrung);
        edta = findViewById(R.id.edta);
        edtb = findViewById(R.id.edtb);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        findviews();

        btnRan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                val1 = Random();
                val2 = Random();
                val3 = Random();
                val4 = Random();

                txt1.setText(Integer.toString(val1));
                txt2.setText(Integer.toString(val2));
                txt3.setText(Integer.toString(val3));
                txt4.setText(Integer.toString(val4));

                int[] x = {val1, val2, val3, val4};
                Arrays.sort(x);

                int lon_nhat = x[3];
                int lon_nhi = x[2];
                int lon_ba = x[1];
                int be_nhat = x[0];

                setTextViewColor(txt1, val1, lon_nhat, lon_nhi, lon_ba, be_nhat);
                setTextViewColor(txt2, val2, lon_nhat, lon_nhi, lon_ba, be_nhat);
                setTextViewColor(txt3, val3, lon_nhat, lon_nhi, lon_ba, be_nhat);
                setTextViewColor(txt4, val4, lon_nhat, lon_nhi, lon_ba, be_nhat);
            }
        });

        btnSort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x1 = Integer.parseInt(txt1.getText().toString());
                int x2 = Integer.parseInt(txt2.getText().toString());
                int x3 = Integer.parseInt(txt3.getText().toString());
                int x4 = Integer.parseInt(txt4.getText().toString());

                int[] a_sorted = A(x1, x2, x3, x4);

                txt1.setText(Integer.toString(a_sorted[3]));
                txt2.setText(Integer.toString(a_sorted[2]));
                txt3.setText(Integer.toString(a_sorted[1]));
                txt4.setText(Integer.toString(a_sorted[0]));

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TAO DOI TUONG SharedPreferences
                SharedPreferences my_pref = getSharedPreferences("save", MODE_PRIVATE);
                //TAO DOI DUONG Editor
                SharedPreferences.Editor editor = my_pref.edit();
                //LUU DU LIEU
                editor.putString("txt1", txt1.getText().toString());
                editor.putString("txt2", txt2.getText().toString());
                editor.putString("txt3", txt3.getText().toString());
                editor.putString("txt4", txt4.getText().toString());
                //XAC NHAN VIEC LUU TRU
                editor.apply();
            }
        });

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
                String saved_txt1 = sharedPreferences.getString("txt1", "");
                String saved_txt2 = sharedPreferences.getString("txt2", "");
                String saved_txt3 = sharedPreferences.getString("txt3", "");
                String saved_txt4 = sharedPreferences.getString("txt4", "");

                txt1.setText(saved_txt1);
                txt2.setText(saved_txt2);
                txt3.setText(saved_txt3);
                txt4.setText(saved_txt4);
            }
        });

        btnXoaTrung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> listA = new ArrayList<>();
                ArrayList<String> listB = new ArrayList<>();

                if(!edta.getText().toString().isEmpty()){
                    listA.addAll(Arrays.asList(edta.getText().toString().split("\\s+")));
                }

                if(!edtb.getText().toString().isEmpty()){
                    listB.addAll(Arrays.asList(edtb.getText().toString().split("\\s+")));
                }

                int[] Mang_a = new int[listA.size()];
                for(int i=0; i<listA.size(); i++){
                    Mang_a[i] = Integer.parseInt(listA.get(i));
                }

                int[] Mang_b = new int[listB.size()];
                for(int i=0; i<listB.size(); i++){
                    Mang_b[i] = Integer.parseInt(listB.get(i));
                }
                Arrays.sort(Mang_a);
                Arrays.sort(Mang_b);
                int c = F(Mang_a, Mang_b);
                Toast.makeText(MainActivity.this, String.valueOf(c), Toast.LENGTH_LONG).show();
            }
        });
    }
    public int Random(){
        Random random = new Random();
        return random.nextInt(1000);
    }

    private void setTextViewColor(TextView textView, int val, int lon_nhat, int lon_nhi, int lon_ba, int be_nhat){
        if(val == lon_nhat){
            textView.setBackgroundColor(getResources().getColor(R.color.red));
        }
        else if(val == lon_nhi){
            textView.setBackgroundColor(getResources().getColor(R.color.green));
        }
        else if(val == lon_ba){
            textView.setBackgroundColor(getResources().getColor(R.color.blue));
        }
        else if(val == be_nhat){
            textView.setBackgroundColor(getResources().getColor(R.color.yellow));
        }
    }

    public int[] A(int x1, int x2, int x3, int x4){
        int[] a = {x1, x2, x3, x4};
        Arrays.sort(a);
        return a;
    }

    public int F(int[] a, int []b){
        int a_remove_dup = Remove_Dup(a).length;
        int b_remove_dup = Remove_Dup(b).length;
        int f = a_remove_dup+b_remove_dup;
        return f;
    }

    public int[] Remove_Dup(int []x){
        int len = x.length;
        int[] t = new int[x.length];
        int j =0;
        for(int i=0; i<len-1; i++){
            if(x[i] != x[i+1]){
                t[j++] = x[i];
            }
        }
        t[j++] = x[len - 1];
        int[] result = new int[j];
        System.arraycopy(t, 0, result, 0, j);
        return result;
    }
}