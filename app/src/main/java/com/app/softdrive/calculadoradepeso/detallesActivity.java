package com.app.softdrive.calculadoradepeso;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


public class detallesActivity extends AppCompatActivity {

    TextView indice, stado, standar, stadod;
    TextView mipeso, pesoIdeal, diferencia;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar);

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(detallesActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        indice=(TextView)findViewById(R.id.imc);
        stado=(TextView)findViewById(R.id.stado);
        standar=(TextView)findViewById(R.id.standar);
        stadod=(TextView)findViewById(R.id.stadod);

        mipeso=(TextView)findViewById(R.id.pn);
        pesoIdeal=(TextView)findViewById(R.id.pi);
        diferencia=(TextView)findViewById(R.id.dife);
        ProgressBar imc1 = (ProgressBar) findViewById(R.id.progresso);

        try{
        Bundle bundle=getIntent().getExtras();

        String estado= bundle.getString("estado");
        String imct= bundle.getString("imc");
        String pesoA= bundle.getString("peso");
        String pesoI= bundle.getString("pesoI");
        //String dife=bundle.getString("direfencia").toString();


        double pa=Double.parseDouble(pesoA);
        double pi=Double.parseDouble(pesoI);
        double pesoAN=pa*2.2;
        String peA=String.valueOf(pesoAN).substring(0,3);
        double pesoID=pi*2.2;
        String peI=String.valueOf(pesoID).substring(0,3);
        double dif;



        String pii = String.valueOf(pesoI).substring(0, 2);
        assert imct != null;
        String subimc = imct.substring(0, 5);
        int prog=Integer.parseInt(subimc.substring(0,2));

        imc1.setProgress(prog);

        indice.setText(subimc);



       if(pa>pi){
           dif=pa-pi;
           String dat=String.valueOf(dif).substring(0,2);



           if (estado != null) {
               switch (estado){

                   case "true":String libra=String.valueOf(Double.parseDouble(dat)*2.2).substring(0,3);
                       diferencia.setText("Tu sobrepeso es de: "+libra+" lb");
                       mipeso.setText(peA+" LB");
                       pesoIdeal.setText(peI+" LB");
                        break;
                   case "false":diferencia.setText("Tu sobrepeso es de: "+dat+" Kg");
                       mipeso.setText(pesoA+" KG");
                       pesoIdeal.setText(pii+" KG");
                       break;
               }
           }


       }else {
           dif=pi-pa;
           String dat=String.valueOf(dif).substring(0,2);





           if (estado != null) {
               switch (estado){
                   case "true": String libra=String.valueOf(Double.parseDouble(dat)*2.2).substring(0,2);
                       diferencia.setText("Te faltan: "+libra+" lb"+" para llegar a tu peso ideal");
                       mipeso.setText(peA+" LB");
                       pesoIdeal.setText(peI+" LB");
                       break;
                   case "false":diferencia.setText("Te faltan: "+dat+" kg"+" para llegar a tu peso ideal");
                       mipeso.setText(pesoA+" KG");
                       pesoIdeal.setText(pii+" KG");
               }
           }


       }

        double imc=Double.parseDouble(imct);

         if(imc<16){
                    stado.setText("Delgadez Severa");
                    standar.setText("- De 10 a 16");
                    stadod.setText("¡Cuidado! Tienes una cantidad baja de grasa en el cuerpo. Tus defensas inmunitarias podrian disminuir. Deberias visitar a un medico para asegurarte que estas bien, ya que podria ser un criterio de hospitalizacion. Te aconsejamos una dieta equilibrada y ejercicio para ganar peso y aumentar tu masa de muscular.");
                    stado.setTextColor(Color.parseColor("#d70808"));
            }
                else if(imc<17){
                    stado.setText("Delgadez Moderada");
                    standar.setText("- De 16.1 a 17");
                    stadod.setText("Tu bajo peso podria hacer disminuir tus defensas inmunitarias. Deberias visitar a un medico para asegurarte que estas bien. Te aconsejamos una dieta equilibrada y ejercicio para ganar peso y aumentar tu masa de muscular.");
                    stado.setTextColor(Color.parseColor("#33ffdd"));
                }
                else if(imc<18.5){
                    stado.setText("Delgadez Aceptable");
                    standar.setText("- De 17.1 a 18.5");
                    stadod.setText("Enhorabuena, usted es delgado, pero no se decuide ya que esta por debajo de los niveles recomendados.");
                    stado.setTextColor(Color.parseColor("#33ffdd"));
                }
                else if(imc<25){
                    stado.setText("Peso Normal");
                    standar.setText("- De 18.5 a 24.9");
                    stadod.setText("Tu IMC es ideal. Tienes una cantidad sana de grasa en el cuerpo, que es asociado con una vida mas larga, y bajas posibilidades de enfermedad grave. Coincidencia o no, la gente percibe las personas con este IMC mas atractivas esteticamente.");
                    stado.setTextColor(Color.parseColor("#FF09C606"));

                }
                else if(imc<30){
                     stado.setText("Sobrepeso");
                     standar.setText("- De 24.5 a 25");
                     stadod.setText("Estas aumentando el riesgo de enfermedades con tu peso actual. Deberias perder peso cambiando de dieta y haciendo mas ejercicio.");
                     stado.setTextColor(Color.parseColor("#FFB7E709"));
                }
                else if(imc<35){
                    stado.setText("Obeso: Tipo I");
                    standar.setText("- De 25.1 a 28.9");
                    stadod.setText("¡Cuidado! está por encima de los índices recomendados, su grado es de obesidad I. Intente rebajarlo ya que a la obesidad van asociadas enfermedades como diabetes, hipertensión arterial, cardiopatía hisquémica, alteraciones osteoarticulares o apnea del sueño.");
                    stado.setTextColor(Color.parseColor("#f3f702"));
                }
                else if(imc<40){
                    stado.setText("Obeso: Tipo II");
                    standar.setText("- De 28 a 39.9");
                    stadod.setText("¡Cuidado! está bastante por encima de los índices recomendados, su grado es de obesidad II. Intente rebajarlo ya que a la obesidad van asociadas enfermedades como diabetes, hipertensión arterial, cardiopatía hisquémica, alteraciones osteoarticulares o apnea del sueño.");
                    stado.setTextColor(Color.parseColor("#ffae00"));
                }
                else if(imc>=40){
                     stado.setText("Obeso: Tipo III");
                     standar.setText("- De 40 en adelante");
                     stadod.setText("¡Cuidado! está muy por encima de los índices recomendados, su grado es de obesidad III, mórbida. Intente rebajarlo ya que a la obesidad van asociadas enfermedades como diabetes, hipertensión arterial, cardiopatía hisquémica, alteraciones osteoarticulares o apnea del sueño");
                     stado.setTextColor(Color.parseColor("#df0b00"));
                }


                }catch (Exception e){
    Toast.makeText(detallesActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
}
    }
}
