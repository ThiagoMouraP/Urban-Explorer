package com.projeto.henrique.urbanexplorer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.google.firebase.database.FirebaseDatabase;

import java.math.BigDecimal;
import java.util.ArrayList;

import static com.projeto.henrique.urbanexplorer.MainActivity.determinarDistancia;

public class Servico extends Service {
    private static ArrayList<Lugar> cidadesServico = criarCidadesServico();
    private static Long ponto;
    private static String navegacao;
    public Servico() {

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

    public static ArrayList criarCidadesServico(){
        ArrayList<Cidade> cidades = new ArrayList<>();
        Cidade rio = new Cidade("Rio de Janeiro");
        rio.setDistancia(determinarDistancia(-22.907500, -43.181907));
        rio.setImagem(R.drawable.rio);
        cidades.add(rio);
        Cidade saoPaulo = new Cidade("São Paulo");
        saoPaulo.setDistancia(determinarDistancia(-23.554214, -46.633514));
        saoPaulo.setImagem(R.drawable.saopaulo);
        cidades.add(saoPaulo);
        Cidade petropolis = new Cidade("Petrópolis");
        petropolis.setImagem(R.drawable.petropolis);
        petropolis.setDistancia(determinarDistancia(-22.534258, -43.219550));
        cidades.add(petropolis);
        Cidade brasilia = new Cidade("Brasília");
        brasilia.setImagem(R.drawable.brasilia);
        brasilia.setDistancia(determinarDistancia(-15.794004, -47.882736));
        cidades.add(brasilia);
        Cidade portoalegre = new Cidade("Porto Alegre");
        portoalegre.setImagem(R.drawable.portoalegre);
        portoalegre.setDistancia(determinarDistancia(-30.030869, -51.227889));
        cidades.add(portoalegre);
        Cidade salvador = new Cidade("Salvador");
        salvador.setImagem(R.drawable.salvador);
        salvador.setDistancia(determinarDistancia(-12.997433, -38.516918));
        cidades.add(salvador);
        Cidade campoJordao = new Cidade("Campos do Jordão");
        campoJordao.setImagem(R.drawable.camposjordao);
        campoJordao.setDistancia(determinarDistancia(-22.735860, -45.588326));
        cidades.add(campoJordao);
        Cidade lisboa = new Cidade("Lisboa");
        lisboa.setImagem(R.drawable.lisboa);
        lisboa.setDistancia(determinarDistancia(38.706619, -9.135128));
        cidades.add(lisboa);
        Cidade juizFora = new Cidade("Juiz de Fora");
        juizFora.setDistancia(determinarDistancia(-21.761271, -43.349839));
        juizFora.setImagem(R.drawable.juiz);
        cidades.add(juizFora);
        Cidade buenoAires = new Cidade("Buenos Aires");
        buenoAires.setImagem(R.drawable.buenosaires);
        buenoAires.setDistancia(determinarDistancia(-34.590172, -58.428061));
        cidades.add(buenoAires);
        Cidade manaus = new Cidade("Manaus");
        manaus.setImagem(R.drawable.manaus);
        manaus.setDistancia(determinarDistancia(-3.123721, -60.018112));
        cidades.add(manaus);
        Cidade recife = new Cidade("Recife");
        recife.setImagem(R.drawable.recife);
        recife.setDistancia(determinarDistancia(-8.052608, -34.884042));
        cidades.add(recife);


        return cidades;
    }


    public static ArrayList<Lugar> retornarCidades(){
        return cidadesServico;
    }



}
