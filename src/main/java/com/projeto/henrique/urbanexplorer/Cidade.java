package com.aplicativo.henrique.urbanexplorer;

import android.location.Location;


import java.math.BigDecimal;
import java.util.ArrayList;

import static com.aplicativo.henrique.urbanexplorer.MainActivity.lat;
import static com.aplicativo.henrique.urbanexplorer.MainActivity.longi;

public class Cidade extends Lugar {
    private ArrayList<Lugar> hotspots;
    private ArrayList<Restaurante> restaurantes;
    public Cidade(){
        hotspots = new ArrayList<>();
    }
    public Cidade(String nome){
        super(nome);

        if(nome.equals("Rio de Janeiro")){
            hotspots = returnRio();
            restaurantes = returnRioRestaurante();
            return;
        }
        if(nome.equals("São Paulo")){
            hotspots = returnSaoPaulo();
            restaurantes = returnSaoPauloRestaurante();
            return;
        }
        if(nome.equals("Petrópolis")){
            hotspots = returnPetro();
            restaurantes = returnPetroRestaurante();
            return;
        }
        if(nome.equals("Brasília")){
            hotspots = returnBrasilia();
            restaurantes = returnBrasiliaRestaurante();
            return;
        }
        if(nome.equals("Porto Alegre")){
            hotspots = returnPortoAlegre();
            restaurantes = returnPortoAlegreRestaurante();
            return;
        }
        if(nome.equals("Salvador")){
            hotspots = returnSalvador();
            restaurantes = returnSalvadorRestaurante();
            return;
        }
        if(nome.equals("Campos do Jordão")){
            hotspots = returnCamposDoJordao();
            restaurantes = returnCamposJordaoRestaurante();
            return;
        }
        if(nome.equals("Lisboa")){
            hotspots = returnLisboa();
            restaurantes = returnLisboaRestaurante();
            return;
        }
        if(nome.equals("Juiz de Fora")){
            hotspots = returnJuizFora();
            restaurantes = returnJuizForaRestaurante();
            return;
        }
        if(nome.equals("Buenos Aires")){
            hotspots = returnBuenosAires();
            restaurantes = returnBuenosAiresRestaurante();
            return;
        }
        if(nome.equals("Manaus")){
            hotspots = returnManaus();
            restaurantes = returnManausRestaurante();
            return;
        }
        if(nome.equals("Recife")){
            hotspots = returnRecife();
            restaurantes = returnRecifeRestaurante();
            return;
        }


    }


    public ArrayList<Lugar> getHotspots() {
        return hotspots;
    }

    public Cidade(ArrayList<Lugar> arrayList){
        hotspots = arrayList;
    }
    public void adicionarHotspot(Lugar h){
        hotspots.add(h);
    }
    public Lugar getHotspot(int i){
        return hotspots.get(i);
    }
    public void setHotspots(ArrayList<Lugar> arrayList){
        hotspots = arrayList;
    }

    public  ArrayList<Lugar> retornaArray(){
        if(this.getNome().equals("Rio de Janeiro")){
            return returnRio();
        }
        if(this.getNome().equals("São Paulo")){
            return returnSaoPaulo();
        }
        if(this.getNome().equals("Petrópolis")){
            return returnPetro();
        }
        return null;
    }
    public  ArrayList<Lugar> returnRio(){
        ArrayList<Lugar> rio = new ArrayList<>();
        rio.add(new Hotspot(R.drawable.maraca, "Maracanã", determinarDistancia(-22.912175, -43.230347)
                ,-22.912175, -43.230347, "https://www.tourmaracana.com.br/"));
        rio.add((new Hotspot(R.drawable.pao, "Pão de Açucar",
                determinarDistancia(-22.951305, -43.164915),-22.951305, -43.164915)));
        rio.add(new Hotspot(R.drawable.redentor, "Cristo Redentor",
                determinarDistancia(-22.952021, -43.210802),-22.952021, -43.210802));
        rio.add(new Hotspot(R.drawable.theatro, "Theatro municipal",
                determinarDistancia(-22.909370, -43.176388), -22.909370, -43.176388));
        rio.add(new Hotspot(R.drawable.maua, "Praça Mauá",
                determinarDistancia(-22.896291, -43.180900),-22.896291, -43.180900));
        rio.add(new Hotspot(R.drawable.mac, "MAC",
                determinarDistancia(-22.907898, -43.125908),-22.907898, -43.125908));
        rio.add(new Hotspot(R.drawable.fortecopacabana, "Forte de Copacabana",
                determinarDistancia(-22.986282, -43.187164),-22.986282, -43.187164));
        rio.add(new Hotspot(R.drawable.vistachinesa, "Vista Chinesa",
                determinarDistancia(-22.973251, -43.249515),-22.973251, -43.249515));
        rio.add(new Hotspot(R.drawable.realgabinete, "Real Gabinete",
                determinarDistancia(-22.905398, -43.182141),-22.905398, -43.182141));
        rio.add(new Hotspot(R.drawable.sanfran, "São Francisco Xavier",
                determinarDistancia(-22.920339, -43.223690),-22.920339, -43.223690));
        rio.add(new Hotspot(R.drawable.cidademusica, "Cidade da Música",
                determinarDistancia(-22.999203, -43.366115),-22.999203, -43.366115));
        rio.add(new Hotspot(R.drawable.pracinhas, "Monumento aos Pracinhas",
                determinarDistancia(-22.916613, -43.173515),-22.916613, -43.173515));
        rio.add(new Hotspot(R.drawable.lapa, "Lapa",
                determinarDistancia(-22.913069, -43.179820),-22.913069, -43.179820));
        rio.add(new Hotspot(R.drawable.jockey, "Jockey Club",
                determinarDistancia(-22.972893, -43.223471),-22.972893, -43.223471));
        rio.add(new Hotspot(R.drawable.parqueruina, "Parque das Ruínas",
                determinarDistancia(-22.917749, -43.182534),-22.917749, -43.182534));
        rio.add(new Hotspot(R.drawable.santosdummont, "Santos Dummont",
                determinarDistancia(-22.912714, -43.166690),-22.912714, -43.166690));
        rio.add(new Hotspot(R.drawable.planetario, "Planetário",
                determinarDistancia(-22.978622, -43.230122),-22.978622, -43.230122));
        rio.add(new Hotspot(R.drawable.bosquebarra, "Bosque da Barra",
                determinarDistancia(-22.996329, -43.367741),-22.996329, -43.367741));
        rio.add(new Hotspot(R.drawable.parqueniteroi, "Parque de Niterói",
                determinarDistancia(-22.929683, -43.087743),-22.929683, -43.087743));
        rio.add(new Hotspot(R.drawable.museurepublica, "Museu da República",
                determinarDistancia(-22.925906, -43.176286),-22.925906, -43.176286));
        rio.add(new Hotspot(R.drawable.alerj, "ALERJ",
                determinarDistancia(-22.904061, -43.174205),-22.904061, -43.174205));
        rio.add(new Hotspot(R.drawable.pacoimperial, "Paço Imperial",
                determinarDistancia(-22.903658, -43.174623),-22.903658, -43.174623));
        rio.add(new Hotspot(R.drawable.samba, "Sambódramo",
                determinarDistancia(-22.913352, -43.196268),-22.913352, -43.196268));
        rio.add(new Hotspot(R.drawable.igrejagloria, "Igreja da Glória",
                determinarDistancia(-22.921345, -43.175325),-22.921345, -43.175325));
        rio.add(new Hotspot(R.drawable.aquario, "AquaRio",
                determinarDistancia(-22.893156, -43.192198),-22.893156, -43.192198));
        rio.add(new Hotspot(R.drawable.utopia, "Armazén da Utopia",
                determinarDistancia(-22.892910, -43.190196),-22.892910, -43.190196));
        rio.add(new Hotspot(R.drawable.pasmado, "Mirante do Pasmado",
                determinarDistancia(-22.951407, -43.178242),22.951407, -43.178242));
        rio.add(new Hotspot(R.drawable.marinha, "Espaço da Marinha",
                determinarDistancia(-22.900847, -43.174418),-22.900847, -43.174418));
        rio.add(new Hotspot(R.drawable.fiscal, "Ilha Fiscal",
                determinarDistancia(-22.897072, -43.166321),-22.897072, -43.166321));
        rio.add(new Hotspot(R.drawable.bonde, "Museu do Bonde",
                determinarDistancia(-22.910147, -43.178698),-22.910147, -43.178698));
        rio.add(new Hotspot(R.drawable.riocentro, "RioCentro",
                determinarDistancia(-22.977990, -43.411326),-22.977990, -43.411326));
        rio.add(new Hotspot(R.drawable.donamarta, "Mirante Dona Marta",
                determinarDistancia(-22.944983, -43.196420),-22.944983, -43.196420));
        rio.add(new Hotspot(R.drawable.lage, "Parque Lage",
                determinarDistancia(-22.960276, -43.212073),-22.960276, -43.212073));
        rio.add(new Hotspot(R.drawable.muretaurca, "Mureta da Urca",
                determinarDistancia(-22.950608, -43.167801),-22.950608, -43.167801));
        rio.add(new Hotspot(R.drawable.feiracris, "Feira Nordestina",
                determinarDistancia(-22.897637, -43.220514),-22.897637, -43.220514));
        ArrayList<Evento> ccbb = new ArrayList<>();
        ccbb.add(new Evento("Exposição Egito Antigo", "De 12/10/2019 a 27/01/2019", "Apresentamos uma exposição inédita sobre o Egito Antigo. ",
                R.drawable.egitoccbb, "https://www.facebook.com/events/380353836173807/"));
        rio.add(new Hotspot(R.drawable.ccbb, "CCBB",
                determinarDistancia(-22.901076, -43.176340),-22.901076, -43.176340,
                "http://culturabancodobrasil.com.br/portal/rio-de-janeiro/", ccbb));
        rio.add(new Hotspot(R.drawable.mam, "MAM",
                determinarDistancia(-22.913524, -43.171737),-22.913524, -43.171737));
        rio.add(new Hotspot(R.drawable.central, "Central",
                determinarDistancia(-22.903861, -43.190831),-22.903861, -43.190831));
        rio.add(new Hotspot(R.drawable.irmaos, "Mirante Dois Irmãos",
                determinarDistancia(-22.989471, -43.228568),-22.989471, -43.228568));
        rio.add(new Hotspot(R.drawable.riozoo, "RioZoo",
                determinarDistancia(-22.904436, -43.229511),-22.904436, -43.229511));
        rio.add(new Hotspot(R.drawable.galeao, "Galeão",
                determinarDistancia(-22.811718, -43.251653),-22.811718, -43.251653));
        rio.add(new Hotspot(R.drawable.estacio, "Estácio de Sá",
                determinarDistancia(-22.939847, -43.169764),-22.939847, -43.169764));
        rio.add(new Hotspot(R.drawable.pierbarra, "Pier da Barra",
                determinarDistancia(-23.015779, -43.297611),-23.015779, -43.297611));
        rio.add(new Hotspot(R.drawable.museumilitar, "Museu Militar",
                determinarDistancia(-22.905049, -43.219290),-22.905049, -43.219290));
        rio.add(new Hotspot(R.drawable.largocarioca, "Largo da Carioca",
                determinarDistancia(-22.906824, -43.178476),-22.906824, -43.178476));
        rio.add(new Hotspot(R.drawable.cachoeirahorto, "Cachoeira do Horto",
                determinarDistancia(-22.965453, -43.245956),-22.965453, -43.245956));
        rio.add(new Hotspot(R.drawable.arpo, "Arpoador",
                determinarDistancia(-22.990139, -43.191024),-22.990139, -43.191024));
        rio.add(new Hotspot(R.drawable.madureira, "Parque de Madureira",
                determinarDistancia(-22.857640, -43.350640),-22.857640, -43.350640));
        rio.add(new Hotspot(R.drawable.miranteleblon, "Mirante do Leblon",
                determinarDistancia(-22.989954, -43.227453),-22.989954, -43.227453));
        rio.add(new Hotspot(R.drawable.botanico, "Jardim Botâncio",
                determinarDistancia(-22.967781, -43.223938),-22.967781, -43.223938));
        rio.add(new Hotspot(R.drawable.prefeiturario, "Prefeitura",
                determinarDistancia(-22.910982, -43.205753),-22.910982, -43.205753));
        rio.add(new Hotspot(R.drawable.gavea, "Pedra da Gávea",
                determinarDistancia(-22.998987, -43.283062),-22.998987, -43.283062));
        rio.add(new Hotspot(R.drawable.valongo, "Cais do Valongo",
                determinarDistancia(-22.896756, -43.187239),-22.896756, -43.187239));
        rio.add(new Hotspot(R.drawable.teles, "Arco dos Teles",
                determinarDistancia(-22.902782, -43.174602),-22.902782, -43.174602));
        rio.add(new Hotspot(R.drawable.pontal, "Pedra do Pontal",
                determinarDistancia(-23.033100, -43.470642),-23.033100, -43.470642));
        rio.add(new Hotspot(R.drawable.santacruzbarra, "Santa Cruz da Barra",
                determinarDistancia(-22.938415, -43.131649),-22.938415, -43.131649));

        return rio;
    }
    public ArrayList<Restaurante> returnRioRestaurante(){
        ArrayList<Restaurante> restaurante = new ArrayList<>();
        restaurante.add(new Restaurante(R.drawable.fogobotafogo, "Fogo de Chão",
                determinarDistancia(-22.948810, -43.180170),-22.948810, -43.180170));
        restaurante.add(new Restaurante(R.drawable.outbackbotafogo, "Outback",
                determinarDistancia(-22.954313, -43.177291),-22.954313, -43.177291));
        restaurante.add(new Restaurante(R.drawable.brasas, "Brasas",
                determinarDistancia(-22.916488, -43.2220821),-22.916488, -43.222082));
        restaurante.add(new Restaurante(R.drawable.ritacassia, "Rita de Cassia",
                determinarDistancia(-22.922688, -43.222404),-22.922688, -43.222404));


        return restaurante;
    }

    public  ArrayList<Lugar> returnSaoPaulo(){
        ArrayList<Lugar> sampa = new ArrayList<>();
        sampa.add(new Hotspot(R.drawable.catedralsampa, "Catedral de São Paulo",
                determinarDistancia(-23.550716, -46.634203),-23.550716, -46.634203));
        sampa.add(new Hotspot(R.drawable.pinacoteca, "Pinacoteca",
                determinarDistancia(-23.534355, -46.633595),-23.534355, -46.633595));
        sampa.add(new Hotspot(R.drawable.estacaoluz, "Estação da Luz",
                determinarDistancia(-23.535234, -46.635425),-23.535234, -46.635425));
        sampa.add(new Hotspot(R.drawable.galeriarock, "Galeria do Rock",
                determinarDistancia(-23.543387, -46.638514),-23.543387, -46.638514));
        sampa.add(new Hotspot(R.drawable.masp, "MASP",
                determinarDistancia(-23.561601, -46.656143),-23.561601, -46.656143));
        sampa.add(new Hotspot(R.drawable.memoriallatino, "Memorial América Latina",
                determinarDistancia(-23.526782, -46.664261),-23.526782, -46.664261));
        sampa.add(new Hotspot(R.drawable.theatrosp, "Theatro Muncipal",
                determinarDistancia(-23.545599, -46.638948 ),-23.545599, -46.638948 ));
        sampa.add(new Hotspot(R.drawable.edificioitalia, "Edifício Itália",
                determinarDistancia(-23.545398, -46.643237   ),-23.545398, -46.643237   ));
        sampa.add(new Hotspot(R.drawable.fiesp, "FIESP",
                determinarDistancia(-23.563331, -46.654342    ),-23.563331, -46.654342    ));

        return sampa;
    }
    public ArrayList<Restaurante> returnSaoPauloRestaurante(){
        ArrayList<Restaurante> restaurante = new ArrayList<>();
        return restaurante;
    }
    public ArrayList<Lugar> returnPetro(){
        ArrayList<Lugar> petro = new ArrayList<>();
        petro.add(new Hotspot(R.drawable.quintandinha, "Quintandinha",
                determinarDistancia(-22.527400, -43.212834),-22.527400, -43.212834));
        petro.add(new Hotspot(R.drawable.museuimperial, "Museu Imperial",
                determinarDistancia(-22.508206, -43.175532),-22.508206, -43.175532));
        petro.add(new Hotspot(R.drawable.palaciocristal, "Palácio de Cristal",
                determinarDistancia(-22.505392, -43.183162),-22.505392, -43.183162));
        petro.add(new Hotspot(R.drawable.relogioflores, "Relógio das Flores",
                determinarDistancia(-22.510697, -43.183947),-22.510283, -43.184431));
        petro.add(new Hotspot(R.drawable.catedralpetro, "Catedral de Petrópolis",
                determinarDistancia(-22.505897, -43.179327),-22.505897, -43.179327));
        petro.add(new Hotspot(R.drawable.casadummont, "Casa de Santos Dummont",
                determinarDistancia(-22.510313, -43.184428),-22.510313, -43.184428));

        return  petro;
    }
    public ArrayList<Restaurante> returnPetroRestaurante(){
        ArrayList<Restaurante> restaurante = new ArrayList<>();
        return restaurante;
    }

    public ArrayList<Lugar> returnBrasilia(){
        ArrayList<Lugar> brasi = new ArrayList<>();
        brasi.add(new Hotspot(R.drawable.congresso, "Congresso Nacional",
                determinarDistancia(-15.799525, -47.864551),-15.799525, -47.864551));
        brasi.add(new Hotspot(R.drawable.planalto, "Palácio do Planalto",
                determinarDistancia(-15.799304, -47.861011),-15.799304, -47.861011));
        brasi.add(new Hotspot(R.drawable.stf, "Supremo Tribunal Federal",
                determinarDistancia(-15.801860, -47.861721),-15.801860, -47.861721));
        brasi.add(new Hotspot(R.drawable.jk, "Memorial JK",
                determinarDistancia(-15.784283, -47.913224),-15.784283, -47.913224));
        brasi.add(new Hotspot(R.drawable.alvorada, "Palácio da Alvorada",
                determinarDistancia(-15.792478, -47.826616),-15.792478, -47.826616));
        brasi.add(new Hotspot(R.drawable.catedralbrasilia, "Catedral de Brasília",
                determinarDistancia(-15.798082, -47.875682),-15.798082, -47.875682));
        brasi.add(new Hotspot(R.drawable.torretv, "Torre de TV",
                determinarDistancia(-15.790681, -47.893021),-15.790681, -47.893021));
        brasi.add(new Hotspot(R.drawable.planetariobrasilia, "Planetário",
                determinarDistancia(-15.787561, -47.898763),-15.787561, -47.898763));
        brasi.add(new Hotspot(R.drawable.feb, "Federação Espiríta Brasileira",
                determinarDistancia(-15.777075, -47.873987),-15.777075, -47.873987));
        return  brasi;
     }
    public ArrayList<Restaurante> returnBrasiliaRestaurante(){
        ArrayList<Restaurante> restaurante = new ArrayList<>();
        return restaurante;
    }
    public ArrayList<Lugar> returnPortoAlegre() {
        ArrayList<Lugar> porto = new ArrayList<>();
        porto.add(new Hotspot(R.drawable.gasometro, "Gasômetro",
                determinarDistancia(-30.034196, -51.241325),-30.034196, -51.241325));
        return  porto;
    }
    public ArrayList<Restaurante> returnPortoAlegreRestaurante(){
        ArrayList<Restaurante> restaurante = new ArrayList<>();
        return restaurante;
    }
    public ArrayList<Lugar> returnSalvador() {
        ArrayList<Lugar> salvador = new ArrayList<>();
        salvador.add(new Hotspot(R.drawable.farolsalvador, "Farol da Barra",
                determinarDistancia(-13.010236, -38.532661),-13.010236, -38.532661));
        salvador.add(new Hotspot(R.drawable.pelourinho, "Pelourinho",
                determinarDistancia(-12.972271, -38.508355 ),-12.972271, -38.508355 ));
        salvador.add(new Hotspot(R.drawable.lacerda, "Elevador Lacerda",
                determinarDistancia(-12.974335, -38.513198 ),-12.974335, -38.513198 ));
        salvador.add(new Hotspot(R.drawable.mercadomodelo, "Mercado Modelo",
                determinarDistancia(-12.973166, -38.513828 ),-12.973166, -38.513828 ));
        return  salvador;
    }
    public ArrayList<Restaurante> returnSalvadorRestaurante(){
        ArrayList<Restaurante> restaurante = new ArrayList<>();
        return restaurante;
    }
    public ArrayList<Lugar> returnCamposDoJordao() {
        ArrayList<Lugar> campos = new ArrayList<>();
        campos.add(new Hotspot(R.drawable.boavistasp, "Palácio Boa Vista",
                determinarDistancia(-22.735877, -45.612456 ),-22.735877, -45.612456 ));
        campos.add(new Hotspot(R.drawable.claudiosantoro, "Museu e Auditório",
                determinarDistancia(-22.743065, -45.633669 ),-22.743065, -45.633669 ));
        campos.add(new Hotspot(R.drawable.emilioribas, "Estação Emílio Ribas",
                determinarDistancia(-22.717904, -45.567495  ),-22.717904, -45.567495 ));
        campos.add(new Hotspot(R.drawable.elefante, "Morro do Elefante",
                determinarDistancia(-22.713603, -45.566573  ),-22.713603, -45.566573));
        campos.add(new Hotspot(R.drawable.duchaprata, "Ducha da Prata",
                determinarDistancia(-22.738381, -45.569059 ),-22.738381, -45.569059));
        return campos;
    }
    public ArrayList<Restaurante> returnCamposJordaoRestaurante(){
        ArrayList<Restaurante> restaurante = new ArrayList<>();
        return restaurante;
    }
    public ArrayList<Lugar> returnLisboa() {
        ArrayList<Lugar> lisboa = new ArrayList<>();
        lisboa.add(new Hotspot(R.drawable.torrebelem, "Torre de Belém",
                determinarDistancia(38.691549, -9.215950),38.691549, -9.215950));
        lisboa.add(new Hotspot(R.drawable.cristorei, "Cristo Rei",
                determinarDistancia(38.678709, -9.171255),38.678709, -9.171255));
        lisboa.add(new Hotspot(R.drawable.pracacomercio, "Praça do Comércio",
                determinarDistancia(38.678709, -9.171255),38.678709, -9.171255));
        lisboa.add(new Hotspot(R.drawable.pombal, "Praça do Pombal",
                determinarDistancia(38.725156, -9.150213),38.725156, -9.150213));
        lisboa.add(new Hotspot(R.drawable.aqueduto, "Aqueduto",
                determinarDistancia(38.726683, -9.166557),38.726683, -9.166557));
        lisboa.add(new Hotspot(R.drawable.saojorge, "Castelo São Jorge",
                determinarDistancia(38.713855, -9.133492),38.713855, -9.133492));
        lisboa.add(new Hotspot(R.drawable.panteaolisboa, "Panteão Nacional",
                determinarDistancia(38.714925, -9.124751),38.714925, -9.124751));
        return lisboa;
    }
    public ArrayList<Restaurante> returnLisboaRestaurante(){
        ArrayList<Restaurante> restaurante = new ArrayList<>();
        return restaurante;
    }
    public ArrayList<Lugar> returnJuizFora(){
        ArrayList<Lugar> juizFora = new ArrayList<>();
        juizFora.add(new Hotspot(R.drawable.halfeld, "Parque Halfeld",
                determinarDistancia(-21.761156, -43.350129),-21.761156, -43.350129));
        juizFora.add(new Hotspot(R.drawable.morrocristo, "Morro do Cristo",
                determinarDistancia(-21.763810, -43.357286),-21.763810, -43.357286));
        juizFora.add(new Hotspot(R.drawable.marianoprocopio, "Museu Mariano Procópio",
                determinarDistancia(-21.746956, -43.360264),-21.746956, -43.360264));
        juizFora.add(new Hotspot(R.drawable.tremjf, "Vagão de Trem",
                determinarDistancia(-21.748033, -43.360069),-21.748033, -43.360069));
        juizFora.add(new Hotspot(R.drawable.igrejajf, "Nossa Senhora da Glória",
                determinarDistancia(-21.750132, -43.355721),-21.750132, -43.355721));
        juizFora.add(new Hotspot(R.drawable.museujf, "Museu Ferroviário",
                determinarDistancia(-21.759011, -43.343883),-21.759011, -43.343883));
        return juizFora;
    }
    public ArrayList<Restaurante> returnJuizForaRestaurante(){
        ArrayList<Restaurante> restaurante = new ArrayList<>();
        return restaurante;
    }
    public ArrayList<Lugar> returnBuenosAires(){
        ArrayList<Lugar> buenosAires = new ArrayList<>();
        buenosAires.add(new Hotspot(R.drawable.obeliscobuenos, "Obelisco",
                determinarDistancia(-34.603598, -58.381566),-34.603598, -58.381566));
        buenosAires.add(new Hotspot(R.drawable.congressoargentino, "Congresso de la Nación",
                determinarDistancia(-34.609907, -58.392551),-34.609907, -58.392551));
        buenosAires.add(new Hotspot(R.drawable.puertomadero, "Puerto Madero",
                determinarDistancia(-34.611931, -58.364352),-34.611931, -58.364352));
        buenosAires.add(new Hotspot(R.drawable.casarosada, "Casa Rosada",
                determinarDistancia(-34.608201, -58.370742),-34.608201, -58.370742));
        return buenosAires;
    }
    public ArrayList<Restaurante> returnBuenosAiresRestaurante(){
        ArrayList<Restaurante> restaurante = new ArrayList<>();
        return restaurante;
    }
    public ArrayList<Lugar> returnManaus(){
        ArrayList<Lugar> manaus = new ArrayList<>();
        manaus.add(new Hotspot(R.drawable.theatroamazonas, "Theatro Amazonas",
                determinarDistancia(-3.130403, -60.023545),-3.130403, -60.023545));
        manaus.add(new Hotspot(R.drawable.rionegro, "Palácio Rio Negro",
                determinarDistancia(-3.135080, -60.016774),-3.135080, -60.016774));
        manaus.add(new Hotspot(R.drawable.mindu, "Parque Municipal do Mindu",
                determinarDistancia(-3.135080, -60.016774),-3.135080, -60.016774));
        manaus.add(new Hotspot(R.drawable.largomanaus, "Largo São Sebastião",
                determinarDistancia(-3.130401, -60.022458),-3.130401, -60.022458));
        manaus.add(new Hotspot(R.drawable.povosamazonia, "Centro Cultural dos Povos da Amazônia",
                determinarDistancia(-3.133155, -59.987391),-3.133155, -59.987391));
        manaus.add(new Hotspot(R.drawable.museumanaus, "Museu da Cidade",
                determinarDistancia(-3.134376, -60.028588),-3.134376, -60.028588));
        manaus.add(new Hotspot(R.drawable.palacetemanaus, "Palacete Provincial",
                determinarDistancia(-3.135608, -60.021086),-3.135608, -60.021086));
        manaus.add(new Hotspot(R.drawable.relogiomanaus, "Relógio Municipal",
                determinarDistancia(-3.136181, -60.025028),-3.136181, -60.025028));
        manaus.add(new Hotspot(R.drawable.musa, "Torre de observação MUSA",
                determinarDistancia(-3.003324, -59.939675),-3.003324, -59.939675));
        return manaus;
    }
    public ArrayList<Restaurante> returnManausRestaurante(){
        ArrayList<Restaurante> restaurante = new ArrayList<>();
        return restaurante;
    }
    public ArrayList<Lugar> returnRecife(){
        ArrayList<Lugar> recife = new ArrayList<>();
        recife.add(new Hotspot(R.drawable.marcozero, "Marco Zero",
                determinarDistancia(-8.061754, -34.870683),-8.061754, -34.870683));
        recife.add(new Hotspot(R.drawable.sinagogarecife, "Sinagoga Kahal Zur Israel",
                determinarDistancia(-8.061842, -34.871402),-8.061842, -34.871402));
        recife.add(new Hotspot(R.drawable.caissertao, "Cais do Sertão",
                determinarDistancia(-8.060035, -34.869938),-8.060035, -34.869938));
        recife.add(new Hotspot(R.drawable.parqueescultura, "Parque das Esculturas",
                determinarDistancia(-8.063982, -34.869079),-8.063982, -34.869079));
        recife.add(new Hotspot(R.drawable.museurecife, "Museu da Cidade",
                determinarDistancia(-8.071841, -34.880892),-8.071841, -34.880892));
        return recife;
    }
    public ArrayList<Restaurante> returnRecifeRestaurante(){
        ArrayList<Restaurante> restaurante = new ArrayList<>();
        return restaurante;
    }

    public float getAvalicao(){
        return 0;
    }

    public  double determinarDistancia(double x, double y){
        Location a = new Location("a");
        Location b = new Location("b");
        a.setLatitude(x);
        a.setLongitude(y);
        b.setLatitude(lat);
        b.setLongitude(longi);
        return Double.parseDouble(""+new BigDecimal(a.distanceTo(b)/1000).setScale(2, BigDecimal.ROUND_UP));
    }

    public ArrayList<Restaurante> getRestaurantes() {
        return restaurantes;
    }

    public void setRestaurantes(ArrayList<Restaurante> restaurantes) {
        this.restaurantes = restaurantes;
    }
}
