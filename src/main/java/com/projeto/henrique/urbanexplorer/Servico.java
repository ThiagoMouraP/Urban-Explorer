package com.aplicativo.henrique.urbanexplorer;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;

import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.aplicativo.henrique.urbanexplorer.MainActivity.determinarDistancia;

public class Servico extends Service {
    public static ArrayList<Lugar> cidadesServico = new ArrayList();
    private static Long ponto;
    private static String navegacao;
    public Servico() {
        criarCidadesServico();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public static ArrayList<Lugar> getCidadesServico() {
        return cidadesServico;
    }

    public static void setCidadesServico(ArrayList<Lugar> cidadesServico) {
        Servico.cidadesServico = cidadesServico;
    }

    public static Long getPonto() {
        return ponto;
    }

    public static void setPonto(Long ponto) {
        Servico.ponto = ponto;
    }

    public static String getNavegacao() {
        return navegacao;
    }

    public static void setNavegacao(String navegacao) {
        Servico.navegacao = navegacao;
    }

    public void criarCidadesServico(){
        //new JsonTask().execute("https://urbanweb.herokuapp.com/androidlercidade.php");
    }


    public static ArrayList<Lugar> retornarCidades(){
        return cidadesServico;
    }




}
