package com.app.softdrive.calculadoradepeso;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    String PesoT, state;
    Spinner  sex, alt, peso, pesoL;
    Button calc;
    TextView pesoO;
    private CheckBox lbl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //-----------------inicializacion------------------------
        sex=(Spinner)findViewById(R.id.spsex);

        alt=(Spinner)findViewById(R.id.spalt);
        peso=(Spinner)findViewById(R.id.sppeso);
        pesoL=(Spinner)findViewById(R.id.sppesoL);

        pesoO=(TextView)findViewById(R.id.pesot);

        lbl=(CheckBox)findViewById(R.id.lbl);

        calc=(Button)findViewById(R.id.btncalc);

        //-------------------llenado de spinners---------------------------

        ArrayAdapter<CharSequence> sexadapter=ArrayAdapter.createFromResource(this, R.array.sexo, R.layout.spinner_item);


        ArrayAdapter<CharSequence> altadapter=ArrayAdapter.createFromResource(this, R.array.altura, R.layout.spinner_item);

        ArrayAdapter<CharSequence> pesoadapter=ArrayAdapter.createFromResource(this, R.array.peso, R.layout.spinner_item);

        ArrayAdapter<CharSequence> pesoLadapter=ArrayAdapter.createFromResource(this, R.array.pesoL, R.layout.spinner_item);

        //----------setear adapters--------------------------

        sex.setAdapter(sexadapter);
        alt.setAdapter(altadapter);
        peso.setAdapter(pesoadapter);
        pesoL.setAdapter(pesoLadapter);



        lbl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lbl.isChecked()){
                    peso.setVisibility(View.INVISIBLE);
                    pesoL.setVisibility(View.VISIBLE);

                    pesoO.setText(R.string.pesoLibra);


                }else{
                    peso.setVisibility(View.VISIBLE);
                    pesoL.setVisibility(View.INVISIBLE);
                    pesoO.setText(R.string.pesoKilo);

                }
            }
        });






        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               try { //-------------Calculo del indice de masa corporal-------------------------

                   if (lbl.isChecked()) {

                       String libra = pesoL.getSelectedItem().toString();
                       double change = Double.valueOf(libra);
                       double pass = change / 2.2;
                       PesoT = String.valueOf(pass);
                       state = "true";
                   } else {
                       peso.setVisibility(View.VISIBLE);
                       pesoL.setVisibility(View.INVISIBLE);
                       PesoT = peso.getSelectedItem().toString();
                       state = "false";
                   }


                   String alturat = alt.getSelectedItem().toString();
                   int altura = Integer.parseInt(alturat);
                   double peso =Double.parseDouble(PesoT);
                   double alturaa = (double) altura / 100d;


                   double alturaCuadrada = alturaa * alturaa;

                   double imc =  peso / alturaCuadrada;


                   //------------------Calculo del peso ideal----------------------------------

                   String sexo = sex.getSelectedItem().toString();

                   double pi = 0;


                   switch (sexo) {
                       case "Hombre":
                           pi = (0.75 * altura) - 62.5;
                           break;
                       case "Mujer":
                           pi = (0.675 * altura) - 56.25;
                           break;


                   }




                   Intent intent = new Intent(MainActivity.this, detallesActivity.class);
                   intent.putExtra("pesoI", String.valueOf(pi));

                   intent.putExtra("estado", state);
                   intent.putExtra("peso", String.valueOf(peso));
                   intent.putExtra("imc", String.valueOf(imc));
                   startActivity(intent);
                   finish();
               }catch (Exception e){
                   Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
               }
            }
        });
    }
}
