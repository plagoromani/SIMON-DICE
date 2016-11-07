package com.example.pablite5.simondice;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button start = (Button) findViewById(R.id.buttonStart);
        Button azul = (Button) findViewById(R.id.buttonBlue);
        azul.setEnabled(false);
        Button rojo = (Button) findViewById(R.id.buttonRed);
        rojo.setEnabled(false);
        Button amarillo = (Button) findViewById(R.id.buttonYellow);
        amarillo.setEnabled(false);
        Button verde = (Button) findViewById(R.id.buttonGreen);
        verde.setEnabled(false);

    }

    int[] botones = {R.id.buttonBlue, R.id.buttonRed, R.id.buttonYellow, R.id.buttonGreen};//para guardar los botones para luego emplearlos todos
    String[] colorBoton = {"#00B0FF", "#E57373", "#FFFF99", "#8BC34A"};// coger todos los colores
    int[] colorClaro = {Color.BLUE, Color.RED, Color.parseColor("#FFFF00"), Color.GREEN};//coger todos los colores claros
    int[] Audio = {R.raw.sonido0, R.raw.sonido2, R.raw.sonido3, R.raw.sonido4};//coger todos los audios

    TimerTask TimerProceso;
    Timer time;
    ArrayList<Integer> colores = new ArrayList();
    ArrayList<Integer> jugador = new ArrayList();

    protected static int CONTADOR = 0;
    protected static int CONTPARPADEAR=0;
    protected static int NUMBER=4;

    protected static int AUX=0;//variable auxiliar para el timer

    void iniciar(View e) {//Me empieza el puto programa
        findViewById(R.id.buttonBlue).setEnabled(true);
        findViewById(R.id.buttonRed).setEnabled(true);
        findViewById(R.id.buttonYellow).setEnabled(true);
        findViewById(R.id.buttonGreen).setEnabled(true);
        numeroAleatorio();
        CONTADOR = 0;
        AUX=0;
        empezarTimer();
e.setEnabled(false);
    }
void eventos(View v){
    CONTADOR++;
    if(v.getId()==R.id.buttonRed){
        jugador.add(1);
        parpadear(1);
    }else if(v.getId()==R.id.buttonBlue) {
    jugador.add(0);
        parpadear(0);

    }else if(v.getId()==R.id.buttonYellow) {
        jugador.add(2);
        parpadear(2);
    }else{
        jugador.add(3);
        parpadear(3);
    }
    restart();
    comprobar();
}



    void numeroAleatorio() {
        if(NUMBER==4){
            for (int i=0;i<4;i++){
                int valor1 = (int) Math.floor(Math.random()*4);
                colores.add(valor1);
            }}else{
            int valor1 = (int) Math.floor(Math.random()*4);
            colores.add(valor1);}



    }
    //PARPADEAR LOS BOTONES AL PULSARLOS
    void parpadear(final int posicionBoton){
        findViewById(botones[posicionBoton]).setBackgroundColor(colorClaro[posicionBoton]);
        final MediaPlayer audio = MediaPlayer.create(this, Audio[posicionBoton]);
        audio.start();
        findViewById(botones[posicionBoton]).postDelayed(new Runnable() {
            public void run() {
                audio.reset();
                findViewById(botones[posicionBoton]).setBackgroundColor(Color.parseColor(colorBoton[posicionBoton]));
            }
        }, 4000);

    }

    void restart(){
        Button start = (Button) findViewById(R.id.buttonStart);
        if (CONTADOR == NUMBER) {

            jugador.clear();
            CONTPARPADEAR=0;
            start.setEnabled(true);
        }
    }


    void inicializarTimer (){
        TimerProceso = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if(colores.get(AUX)==0){
                            parpadear(0);}
                        if(colores.get(AUX)==1){
                            parpadear(1);
                        }
                        if(colores.get(AUX)==2){
                            parpadear(2);
                        }
                        if(colores.get(AUX)==3){
                            parpadear(3);
                        }
                        AUX++;
                        CONTPARPADEAR++;
                        if(CONTPARPADEAR==NUMBER){
                            acabarTimer();
                        }

                    }
                });

            }
        };


    }

    public void empezarTimer(){
        time = new Timer();
        inicializarTimer();
        time.schedule(TimerProceso, 200, 2000);
    }

    public void acabarTimer(){
        if (time !=null){
            time.cancel();
            time= null;
        }
    }
public void comprobar() {

    if (colores.toString().equals(jugador.toString())) {
        Toast.makeText(this, "Has Ganado!!! ", Toast.LENGTH_SHORT).show();
        CONTPARPADEAR++;


    } else {
        Toast.makeText(this, "Has Perdido, eres un manco", Toast.LENGTH_SHORT).show();
        CONTPARPADEAR = 4;
        colores.clear();


    }
}
}
