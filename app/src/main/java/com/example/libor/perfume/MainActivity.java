package com.example.libor.perfume;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {


    Spinner spin;
    TextView txtPrice,txtTprice,txtOption;
    ImageButton img;

    RadioGroup rdoSize;
    RadioButton rdo10,rdo50,rdo100;
    EditText txtQty;

    ToggleButton btnOption;
    public int perfumeCntr,sizeCntr, totalimg;

    public String perfume []={"Lacoste Booster","Bvlgari Man","Bvlgari Extreme","Polo Black","Polo Blue", "Armani Code"
            , "Dolce and Gabbana", "Hugo Boss", "Ferrari", "Bench Perfume", "Penshoppe Perfume"};
    public String size[]={"10 ML","50 ML","100 ML"};
    public int pic[] = {R.drawable.lacostebooster,R.drawable.bvlgariman, R.drawable.bvlgarextreme
            , R.drawable.poloblack, R.drawable.poloblue,R.drawable.armanicode, R.drawable.dolceandgabbana, R.drawable.hugoboss
            , R.drawable.ferrari, R.drawable.bench, R.drawable.pen};
    public Double perfumePrice  [][]={{450.00,2000.00,3900.00},{500.00,2100.00,4000.00},
            {600.00,2500.00,4800.00},{650.00,2600.00,4900.00},{625.00,2700.00,5000.00}, {500.23, 2500.00, 4300.99}
            , {200.99,1500.99,6000.00}, {300.99, 3000.70, 5500.00}, {100.99,8000.80,6700.40},
            {400.30,4000.60,5000.00}, {600.30,2000.00,4000.00}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        perfumeCntr=0;
                sizeCntr=0;
        totalimg = 11;
        txtPrice =(TextView)findViewById(R.id.textPerfume1);
        txtTprice =(TextView) findViewById(R.id.textTPrice1);
        txtQty=(EditText) findViewById(R.id.textQty1);
        rdoSize =(RadioGroup) findViewById(R.id.RadioMembership1);
        rdo10=(RadioButton) findViewById(R.id.radio10);
        rdo50=(RadioButton) findViewById(R.id.radio50);
        rdo100=(RadioButton)findViewById(R.id.radio100);
        txtOption=(TextView) findViewById(R.id.textOption1);
        btnOption=(ToggleButton) findViewById(R.id.buttonOption1);
        img = (ImageButton)findViewById(R.id.imageButton);
        spin=(Spinner)findViewById(R.id.spinner);
        rdo10.performClick();


        displayTprice(0,perfumeCntr,sizeCntr);
        display(perfumeCntr,sizeCntr);

       img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perfumeCntr ++;
                display(perfumeCntr,sizeCntr);
                perfumeCntr = perfumeCntr % totalimg;
                img.setImageResource(pic[perfumeCntr]);
                try {
                    displayTprice( Integer.parseInt(txtQty.getText().toString()),perfumeCntr,sizeCntr);
                } catch (NumberFormatException e) {
                    txtTprice.setText("P 0.00");
                }
            }
        });

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                perfumeCntr=i;
                display(perfumeCntr,sizeCntr);
                img.setImageResource(pic[i]);
                try {
                    displayTprice( Integer.parseInt(txtQty.getText().toString()),perfumeCntr,sizeCntr);
                } catch (NumberFormatException e) {
                    txtTprice.setText("P 0.00");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        rdoSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch(i){

                    case R.id.radio10:
                        sizeCntr=0;
                        display(perfumeCntr,sizeCntr);
                        try {
                            displayTprice( Integer.parseInt(txtQty.getText().toString()),perfumeCntr,sizeCntr);
                        } catch (NumberFormatException e) {
                            txtTprice.setText("P 0.00");
                        }
                        break;
                    case R.id.radio50:
                        sizeCntr=1;
                        display(perfumeCntr,sizeCntr);
                        try {
                            displayTprice( Integer.parseInt(txtQty.getText().toString()),perfumeCntr,sizeCntr);
                        } catch (NumberFormatException e) {
                            txtTprice.setText("P 0.00");
                        }
                        break;
                    case R.id.radio100:
                        sizeCntr=2;
                        display(perfumeCntr,sizeCntr);
                        try {
                            displayTprice( Integer.parseInt(txtQty.getText().toString()),perfumeCntr,sizeCntr);
                        } catch (NumberFormatException e) {
                            txtTprice.setText("P 0.00");
                        }
                        break;


                }


            }
        });

        txtQty.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                try {
                    displayTprice( Integer.parseInt(txtQty.getText().toString()),perfumeCntr,sizeCntr);
                } catch (NumberFormatException e) {
                    txtTprice.setText("P 0.00");
                }
            }
        });

        btnOption.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (btnOption.isChecked()) {
                    txtOption.setText("Current Method: Pick UP");
                }


                else {

                    txtOption.setText("Current Method: Delivery");

                }



            }
        });

    }

    public void displayTprice(int i, int perfumeCntr, int sizeCntr) {
        try {
            txtTprice.setText("P "+String.format("%,.2f",perfumePrice[perfumeCntr][sizeCntr]*i));
        } catch (Exception e) {
            txtTprice.setText("P 0.00");
        }

    }


    public void display(int perfumeCntr, int sizeCntr) {

        txtPrice.setText(""+perfume[perfumeCntr]+"("+size[sizeCntr]+") " +
                "Priced at P "+ String.format("%,.2f",perfumePrice[perfumeCntr][sizeCntr]));

    }
}
