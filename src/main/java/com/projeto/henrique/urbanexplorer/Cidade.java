package com.projeto.henrique.urbanexplorer;

import java.util.ArrayList;

public class Cidade extends Lugar {
    private ArrayList<Lugar> hotspots;
    public Cidade(){
        hotspots = new ArrayList<>();
    }
    public Cidade(String nome){
        super(nome);
        if(nome.equals("Rio de Janeiro")){
            hotspots = returnRio();
            return;
        }
        if(nome.equals("São Paulo")){
            hotspots = returnSaoPaulo();
            return;
        }
        if(nome.equals("Petrópolis")){
            hotspots = returnPetro();
            return;
        }
        if(nome.equals("Brasília")){
            hotspots = returnBrasilia();
            return;
        }
        if(nome.equals("Porto Alegre")){
            hotspots = returnPortoAlegre();
            return;
        }
        if(nome.equals("Salvador")){
            hotspots = returnSalvador();
            return;
        }
        if(nome.equals("Campos do Jordão")){
            hotspots = returnCamposDoJordao();
            return;
        }
        if(nome.equals("Lisboa")){
            hotspots = returnLisboa();
            return;
        }
        if(nome.equals("Juiz de Fora")){
            hotspots = returnJuizFora();
            return;
        }
        if(nome.equals("Buenos Aires")){
            hotspots = returnBuenosAires();
            return;
        }
        if(nome.equals("Manaus")){
            hotspots = returnManaus();
            return;
        }
        if(nome.equals("Recife")){
            hotspots = returnRecife();
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
        rio.add(new Hotspot(R.drawable.maraca, "Maracanã", MainActivity.determinarDistancia(-22.912175, -43.230347)
                ,-22.912175, -43.230347));
        rio.add((new Hotspot(R.drawable.pao, "Pão de Açucar",
                MainActivity.determinarDistancia(-22.951305, -43.164915),-22.951305, -43.164915)));
        rio.add(new Hotspot(R.drawable.redentor, "Cristo Redentor",
                MainActivity.determinarDistancia(-22.952021, -43.210802),-22.952021, -43.210802));
        rio.add(new Hotspot(R.drawable.theatro, "Theatro municipal",
                MainActivity.determinarDistancia(-22.909370, -43.176388), -22.909370, -43.176388));
        rio.add(new Hotspot(R.drawable.maua, "Praça Mauá",
                MainActivity.determinarDistancia(-22.896291, -43.180900),-22.896291, -43.180900));
        rio.add(new Hotspot(R.drawable.mac, "MAC",
                MainActivity.determinarDistancia(-22.907898, -43.125908),-22.907898, -43.125908));
        rio.add(new Hotspot(R.drawable.fortecopacabana, "Forte de Copacabana",
                MainActivity.determinarDistancia(-22.986282, -43.187164),-22.986282, -43.187164));
        rio.add(new Hotspot(R.drawable.vistachinesa, "Vista Chinesa",
                MainActivity.determinarDistancia(-22.973251, -43.249515),-22.973251, -43.249515));
        rio.add(new Hotspot(R.drawable.realgabinete, "Real Gabinete",
                MainActivity.determinarDistancia(-22.905398, -43.182141),-22.905398, -43.182141));
        rio.add(new Hotspot(R.drawable.sanfran, "São Francisco Xavier",
                MainActivity.determinarDistancia(-22.920339, -43.223690),-22.920339, -43.223690));
        rio.add(new Hotspot(R.drawable.cidademusica, "Cidade da Música",
                MainActivity.determinarDistancia(-22.999203, -43.366115),-22.999203, -43.366115));
        rio.add(new Hotspot(R.drawable.pracinhas, "Monumento aos Pracinhas",
                MainActivity.determinarDistancia(-22.916613, -43.173515),-22.916613, -43.173515));
        rio.add(new Hotspot(R.drawable.lapa, "Lapa",
                MainActivity.determinarDistancia(-22.913069, -43.179820),-22.913069, -43.179820));
        rio.add(new Hotspot(R.drawable.jockey, "Jockey Club",
                MainActivity.determinarDistancia(-22.972893, -43.223471),-22.972893, -43.223471));
        rio.add(new Hotspot(R.drawable.parqueruina, "Parque das Ruínas",
                MainActivity.determinarDistancia(-22.917749, -43.182534),-22.917749, -43.182534));
        rio.add(new Hotspot(R.drawable.santosdummont, "Santos Dummont",
                MainActivity.determinarDistancia(-22.912714, -43.166690),-22.912714, -43.166690));
        rio.add(new Hotspot(R.drawable.planetario, "Planetário",
                MainActivity.determinarDistancia(-22.978622, -43.230122),-22.978622, -43.230122));
        rio.add(new Hotspot(R.drawable.bosquebarra, "Bosque da Barra",
                MainActivity.determinarDistancia(-22.996329, -43.367741),-22.996329, -43.367741));
        rio.add(new Hotspot(R.drawable.parqueniteroi, "Parque de Niterói",
                MainActivity.determinarDistancia(-22.929683, -43.087743),-22.929683, -43.087743));
        rio.add(new Hotspot(R.drawable.museurepublica, "Museu da República",
                MainActivity.determinarDistancia(-22.925906, -43.176286),-22.925906, -43.176286));
        rio.add(new Hotspot(R.drawable.alerj, "ALERJ",
                MainActivity.determinarDistancia(-22.904061, -43.174205),-22.904061, -43.174205));
        rio.add(new Hotspot(R.drawable.pacoimperial, "Paço Imperial",
                MainActivity.determinarDistancia(-22.903658, -43.174623),-22.903658, -43.174623));
        rio.add(new Hotspot(R.drawable.samba, "Sambódramo",
                MainActivity.determinarDistancia(-22.913352, -43.196268),-22.913352, -43.196268));
        rio.add(new Hotspot(R.drawable.igrejagloria, "Igreja da Glória",
                MainActivity.determinarDistancia(-22.921345, -43.175325),-22.921345, -43.175325));
        rio.add(new Hotspot(R.drawable.aquario, "AquaRio",
                MainActivity.determinarDistancia(-22.893156, -43.192198),-22.893156, -43.192198));
        rio.add(new Hotspot(R.drawable.utopia, "Armazén da Utopia",
                MainActivity.determinarDistancia(-22.892910, -43.190196),-22.892910, -43.190196));
        rio.add(new Hotspot(R.drawable.pasmado, "Mirante do Pasmado",
                MainActivity.determinarDistancia(-22.951407, -43.178242),22.951407, -43.178242));
        rio.add(new Hotspot(R.drawable.marinha, "Espaço da Marinha",
                MainActivity.determinarDistancia(-22.900847, -43.174418),-22.900847, -43.174418));
        rio.add(new Hotspot(R.drawable.fiscal, "Ilha Fiscal",
                MainActivity.determinarDistancia(-22.897072, -43.166321),-22.897072, -43.166321));
        rio.add(new Hotspot(R.drawable.bonde, "Museu do Bonde",
                MainActivity.determinarDistancia(-22.910147, -43.178698),-22.910147, -43.178698));
        rio.add(new Hotspot(R.drawable.riocentro, "RioCentro",
                MainActivity.determinarDistancia(-22.977990, -43.411326),-22.977990, -43.411326));
        rio.add(new Hotspot(R.drawable.donamarta, "Mirante Dona Marta",
                MainActivity.determinarDistancia(-22.944983, -43.196420),-22.944983, -43.196420));
        rio.add(new Hotspot(R.drawable.lage, "Parque Lage",
                MainActivity.determinarDistancia(-22.960276, -43.212073),-22.960276, -43.212073));
        rio.add(new Hotspot(R.drawable.muretaurca, "Mureta da Urca",
                MainActivity.determinarDistancia(-22.950608, -43.167801),-22.950608, -43.167801));
        rio.add(new Hotspot(R.drawable.feiracris, "Feira Nordestina",
                MainActivity.determinarDistancia(-22.897637, -43.220514),-22.897637, -43.220514));
        rio.add(new Hotspot(R.drawable.ccbb, "CCBB",
                MainActivity.determinarDistancia(-22.901076, -43.176340),-22.901076, -43.176340));
        rio.add(new Hotspot(R.drawable.mam, "MAM",
                MainActivity.determinarDistancia(-22.913524, -43.171737),-22.913524, -43.171737));
        rio.add(new Hotspot(R.drawable.central, "Central",
                MainActivity.determinarDistancia(-22.903861, -43.190831),-22.903861, -43.190831));
        rio.add(new Hotspot(R.drawable.irmaos, "Mirante Dois Irmãos",
                MainActivity.determinarDistancia(-22.989471, -43.228568),-22.989471, -43.228568));
        rio.add(new Hotspot(R.drawable.riozoo, "RioZoo",
                MainActivity.determinarDistancia(-22.904436, -43.229511),-22.904436, -43.229511));
        rio.add(new Hotspot(R.drawable.galeao, "Galeão",
                MainActivity.determinarDistancia(-22.811718, -43.251653),-22.811718, -43.251653));
        rio.add(new Hotspot(R.drawable.estacio, "Estácio de Sá",
                MainActivity.determinarDistancia(-22.939847, -43.169764),-22.939847, -43.169764));
        rio.add(new Hotspot(R.drawable.pierbarra, "Pier da Barra",
                MainActivity.determinarDistancia(-23.015779, -43.297611),-23.015779, -43.297611));
        rio.add(new Hotspot(R.drawable.museumilitar, "Museu Militar",
                MainActivity.determinarDistancia(-22.905049, -43.219290),-22.905049, -43.219290));
        rio.add(new Hotspot(R.drawable.largocarioca, "Largo da Carioca",
                MainActivity.determinarDistancia(-22.906824, -43.178476),-22.906824, -43.178476));
        rio.add(new Hotspot(R.drawable.cachoeirahorto, "Cachoeira do Horto",
                MainActivity.determinarDistancia(-22.965453, -43.245956),-22.965453, -43.245956));
        rio.add(new Hotspot(R.drawable.arpo, "Arpoador",
                MainActivity.determinarDistancia(-22.990139, -43.191024),-22.990139, -43.191024));
        rio.add(new Hotspot(R.drawable.madureira, "Parque de Madureira",
                MainActivity.determinarDistancia(-22.857640, -43.350640),-22.857640, -43.350640));
        rio.add(new Hotspot(R.drawable.miranteleblon, "Mirante do Leblon",
                MainActivity.determinarDistancia(-22.989954, -43.227453),-22.989954, -43.227453));
        rio.add(new Hotspot(R.drawable.botanico, "Jardim Botâncio",
                MainActivity.determinarDistancia(-22.967781, -43.223938),-22.967781, -43.223938));
        rio.add(new Hotspot(R.drawable.prefeiturario, "Prefeitura",
                MainActivity.determinarDistancia(-22.910982, -43.205753),-22.910982, -43.205753));
        rio.add(new Hotspot(R.drawable.gavea, "Pedra da Gávea",
                MainActivity.determinarDistancia(-22.998987, -43.283062),-22.998987, -43.283062));
        rio.add(new Hotspot(R.drawable.valongo, "Cais do Valongo",
                MainActivity.determinarDistancia(-22.896756, -43.187239),-22.896756, -43.187239));
        rio.add(new Hotspot(R.drawable.teles, "Arco dos Teles",
                MainActivity.determinarDistancia(-22.902782, -43.174602),-22.902782, -43.174602));
        rio.add(new Hotspot(R.drawable.pontal, "Pedra do Pontal",
                MainActivity.determinarDistancia(-23.033100, -43.470642),-23.033100, -43.470642));
        rio.add(new Hotspot(R.drawable.santacruzbarra, "Santa Cruz da Barra",
                MainActivity.determinarDistancia(-22.938415, -43.131649),-22.938415, -43.131649));

        return rio;
    }
    public  ArrayList<Lugar> returnSaoPaulo(){
        ArrayList<Lugar> sampa = new ArrayList<>();
        sampa.add(new Hotspot(R.drawable.catedralsampa, "Catedral de São Paulo",
                MainActivity.determinarDistancia(-23.550716, -46.634203),-23.550716, -46.634203));
        sampa.add(new Hotspot(R.drawable.pinacoteca, "Pinacoteca",
                MainActivity.determinarDistancia(-23.534355, -46.633595),-23.534355, -46.633595));
        sampa.add(new Hotspot(R.drawable.estacaoluz, "Estação da Luz",
                MainActivity.determinarDistancia(-23.535234, -46.635425),-23.535234, -46.635425));
        sampa.add(new Hotspot(R.drawable.galeriarock, "Galeria do Rock",
                MainActivity.determinarDistancia(-23.543387, -46.638514),-23.543387, -46.638514));
        sampa.add(new Hotspot(R.drawable.masp, "MASP",
                MainActivity.determinarDistancia(-23.561601, -46.656143),-23.561601, -46.656143));
        sampa.add(new Hotspot(R.drawable.memoriallatino, "Memorial América Latina",
                MainActivity.determinarDistancia(-23.526782, -46.664261),-23.526782, -46.664261));
        sampa.add(new Hotspot(R.drawable.theatrosp, "Theatro Muncipal",
                MainActivity.determinarDistancia(-23.545599, -46.638948 ),-23.545599, -46.638948 ));
        sampa.add(new Hotspot(R.drawable.edificioitalia, "Edifício Itália",
                MainActivity.determinarDistancia(-23.545398, -46.643237   ),-23.545398, -46.643237   ));
        sampa.add(new Hotspot(R.drawable.fiesp, "FIESP",
                MainActivity.determinarDistancia(-23.563331, -46.654342    ),-23.563331, -46.654342    ));

        return sampa;
    }

    public ArrayList<Lugar> returnPetro(){
        ArrayList<Lugar> petro = new ArrayList<>();
        petro.add(new Hotspot(R.drawable.quintandinha, "Quintandinha",
                MainActivity.determinarDistancia(-22.527400, -43.212834),-22.527400, -43.212834));
        petro.add(new Hotspot(R.drawable.museuimperial, "Museu Imperial",
                MainActivity.determinarDistancia(-22.508206, -43.175532),-22.508206, -43.175532));
        petro.add(new Hotspot(R.drawable.palaciocristal, "Palácio de Cristal",
                MainActivity.determinarDistancia(-22.505392, -43.183162),-22.505392, -43.183162));
        petro.add(new Hotspot(R.drawable.relogioflores, "Relógio das Flores",
                MainActivity.determinarDistancia(-22.510697, -43.183947),-22.510283, -43.184431));
        petro.add(new Hotspot(R.drawable.catedralpetro, "Catedral de Petrópolis",
                MainActivity.determinarDistancia(-22.505897, -43.179327),-22.505897, -43.179327));
        petro.add(new Hotspot(R.drawable.casadummont, "Casa de Santos Dummont",
                MainActivity.determinarDistancia(-22.510313, -43.184428),-22.510313, -43.184428));

        return  petro;
    }

    public ArrayList<Lugar> returnBrasilia(){
        ArrayList<Lugar> brasi = new ArrayList<>();
        brasi.add(new Hotspot(R.drawable.congresso, "Congresso Nacional",
                MainActivity.determinarDistancia(-15.799525, -47.864551),-15.799525, -47.864551));
        brasi.add(new Hotspot(R.drawable.planalto, "Palácio do Planalto",
                MainActivity.determinarDistancia(-15.799304, -47.861011),-15.799304, -47.861011));
        brasi.add(new Hotspot(R.drawable.stf, "Supremo Tribunal Federal",
                MainActivity.determinarDistancia(-15.801860, -47.861721),-15.801860, -47.861721));
        brasi.add(new Hotspot(R.drawable.jk, "Memorial JK",
                MainActivity.determinarDistancia(-15.784283, -47.913224),-15.784283, -47.913224));
        brasi.add(new Hotspot(R.drawable.alvorada, "Palácio da Alvorada",
                MainActivity.determinarDistancia(-15.792478, -47.826616),-15.792478, -47.826616));
        brasi.add(new Hotspot(R.drawable.catedralbrasilia, "Catedral de Brasília",
                MainActivity.determinarDistancia(-15.798082, -47.875682),-15.798082, -47.875682));
        brasi.add(new Hotspot(R.drawable.torretv, "Torre de TV",
                MainActivity.determinarDistancia(-15.790681, -47.893021),-15.790681, -47.893021));
        brasi.add(new Hotspot(R.drawable.planetariobrasilia, "Planetário",
                MainActivity.determinarDistancia(-15.787561, -47.898763),-15.787561, -47.898763));
        brasi.add(new Hotspot(R.drawable.feb, "Federação Espiríta Brasileira",
                MainActivity.determinarDistancia(-15.777075, -47.873987),-15.777075, -47.873987));
        return  brasi;
     }
    public ArrayList<Lugar> returnPortoAlegre() {
        ArrayList<Lugar> porto = new ArrayList<>();
        porto.add(new Hotspot(R.drawable.gasometro, "Gasômetro",
                MainActivity.determinarDistancia(-30.034196, -51.241325),-30.034196, -51.241325));
        return  porto;
    }
    public ArrayList<Lugar> returnSalvador() {
        ArrayList<Lugar> salvador = new ArrayList<>();
        salvador.add(new Hotspot(R.drawable.farolsalvador, "Farol da Barra",
                MainActivity.determinarDistancia(-13.010236, -38.532661),-13.010236, -38.532661));
        salvador.add(new Hotspot(R.drawable.pelourinho, "Pelourinho",
                MainActivity.determinarDistancia(-12.972271, -38.508355 ),-12.972271, -38.508355 ));
        salvador.add(new Hotspot(R.drawable.lacerda, "Elevador Lacerda",
                MainActivity.determinarDistancia(-12.974335, -38.513198 ),-12.974335, -38.513198 ));
        salvador.add(new Hotspot(R.drawable.mercadomodelo, "Mercado Modelo",
                MainActivity.determinarDistancia(-12.973166, -38.513828 ),-12.973166, -38.513828 ));
        return  salvador;
    }
    public ArrayList<Lugar> returnCamposDoJordao() {
        ArrayList<Lugar> campos = new ArrayList<>();
        campos.add(new Hotspot(R.drawable.boavistasp, "Palácio Boa Vista",
                MainActivity.determinarDistancia(-22.735877, -45.612456 ),-22.735877, -45.612456 ));
        campos.add(new Hotspot(R.drawable.claudiosantoro, "Museu e Auditório",
                MainActivity.determinarDistancia(-22.743065, -45.633669 ),-22.743065, -45.633669 ));
        campos.add(new Hotspot(R.drawable.emilioribas, "Estação Emílio Ribas",
                MainActivity.determinarDistancia(-22.717904, -45.567495  ),-22.717904, -45.567495 ));
        campos.add(new Hotspot(R.drawable.elefante, "Morro do Elefante",
                MainActivity.determinarDistancia(-22.713603, -45.566573  ),-22.713603, -45.566573));
        campos.add(new Hotspot(R.drawable.duchaprata, "Ducha da Prata",
                MainActivity.determinarDistancia(-22.738381, -45.569059 ),-22.738381, -45.569059));
        return campos;
    }
    public ArrayList<Lugar> returnLisboa() {
        ArrayList<Lugar> lisboa = new ArrayList<>();
        lisboa.add(new Hotspot(R.drawable.torrebelem, "Torre de Belém",
                MainActivity.determinarDistancia(38.691549, -9.215950),38.691549, -9.215950));
        lisboa.add(new Hotspot(R.drawable.cristorei, "Cristo Rei",
                MainActivity.determinarDistancia(38.678709, -9.171255),38.678709, -9.171255));
        lisboa.add(new Hotspot(R.drawable.pracacomercio, "Praça do Comércio",
                MainActivity.determinarDistancia(38.678709, -9.171255),38.678709, -9.171255));
        lisboa.add(new Hotspot(R.drawable.pombal, "Praça do Pombal",
                MainActivity.determinarDistancia(38.725156, -9.150213),38.725156, -9.150213));
        lisboa.add(new Hotspot(R.drawable.aqueduto, "Aqueduto",
                MainActivity.determinarDistancia(38.726683, -9.166557),38.726683, -9.166557));
        lisboa.add(new Hotspot(R.drawable.saojorge, "Castelo São Jorge",
                MainActivity.determinarDistancia(38.713855, -9.133492),38.713855, -9.133492));
        lisboa.add(new Hotspot(R.drawable.panteaolisboa, "Panteão Nacional",
                MainActivity.determinarDistancia(38.714925, -9.124751),38.714925, -9.124751));
        return lisboa;
    }
    public ArrayList<Lugar> returnJuizFora(){
        ArrayList<Lugar> juizFora = new ArrayList<>();
        juizFora.add(new Hotspot(R.drawable.halfeld, "Parque Halfeld",
                MainActivity.determinarDistancia(-21.761156, -43.350129),-21.761156, -43.350129));
        juizFora.add(new Hotspot(R.drawable.morrocristo, "Morro do Cristo",
                MainActivity.determinarDistancia(-21.763810, -43.357286),-21.763810, -43.357286));
        juizFora.add(new Hotspot(R.drawable.marianoprocopio, "Museu Mariano Procópio",
                MainActivity.determinarDistancia(-21.746956, -43.360264),-21.746956, -43.360264));
        juizFora.add(new Hotspot(R.drawable.tremjf, "Vagão de Trem",
                MainActivity.determinarDistancia(-21.748033, -43.360069),-21.748033, -43.360069));
        juizFora.add(new Hotspot(R.drawable.igrejajf, "Nossa Senhora da Glória",
                MainActivity.determinarDistancia(-21.750132, -43.355721),-21.750132, -43.355721));
        juizFora.add(new Hotspot(R.drawable.museujf, "Museu Ferroviário",
                MainActivity.determinarDistancia(-21.759011, -43.343883),-21.759011, -43.343883));
        return juizFora;
    }
    public ArrayList<Lugar> returnBuenosAires(){
        ArrayList<Lugar> buenosAires = new ArrayList<>();
        buenosAires.add(new Hotspot(R.drawable.obeliscobuenos, "Obelisco",
                MainActivity.determinarDistancia(-34.603598, -58.381566),-34.603598, -58.381566));
        buenosAires.add(new Hotspot(R.drawable.congressoargentino, "Congresso de la Nación",
                MainActivity.determinarDistancia(-34.609907, -58.392551),-34.609907, -58.392551));
        buenosAires.add(new Hotspot(R.drawable.puertomadero, "Puerto Madero",
                MainActivity.determinarDistancia(-34.611931, -58.364352),-34.611931, -58.364352));
        buenosAires.add(new Hotspot(R.drawable.casarosada, "Casa Rosada",
                MainActivity.determinarDistancia(-34.608201, -58.370742),-34.608201, -58.370742));
        return buenosAires;
    }
    public ArrayList<Lugar> returnManaus(){
        ArrayList<Lugar> manaus = new ArrayList<>();
        manaus.add(new Hotspot(R.drawable.theatroamazonas, "Theatro Amazonas",
                MainActivity.determinarDistancia(-3.130403, -60.023545),-3.130403, -60.023545));
        manaus.add(new Hotspot(R.drawable.rionegro, "Palácio Rio Negro",
                MainActivity.determinarDistancia(-3.135080, -60.016774),-3.135080, -60.016774));
        manaus.add(new Hotspot(R.drawable.mindu, "Parque Municipal do Mindu",
                MainActivity.determinarDistancia(-3.135080, -60.016774),-3.135080, -60.016774));
        manaus.add(new Hotspot(R.drawable.largomanaus, "Largo São Sebastião",
                MainActivity.determinarDistancia(-3.130401, -60.022458),-3.130401, -60.022458));
        manaus.add(new Hotspot(R.drawable.povosamazonia, "Centro Cultural dos Povos da Amazônia",
                MainActivity.determinarDistancia(-3.133155, -59.987391),-3.133155, -59.987391));
        manaus.add(new Hotspot(R.drawable.museumanaus, "Museu da Cidade",
                MainActivity.determinarDistancia(-3.134376, -60.028588),-3.134376, -60.028588));
        manaus.add(new Hotspot(R.drawable.palacetemanaus, "Palacete Provincial",
                MainActivity.determinarDistancia(-3.135608, -60.021086),-3.135608, -60.021086));
        manaus.add(new Hotspot(R.drawable.relogiomanaus, "Relógio Municipal",
                MainActivity.determinarDistancia(-3.136181, -60.025028),-3.136181, -60.025028));
        manaus.add(new Hotspot(R.drawable.musa, "Torre de observação MUSA",
                MainActivity.determinarDistancia(-3.003324, -59.939675),-3.003324, -59.939675));
        return manaus;
    }
    public ArrayList<Lugar> returnRecife(){
        ArrayList<Lugar> recife = new ArrayList<>();
        recife.add(new Hotspot(R.drawable.marcozero, "Marco Zero",
                MainActivity.determinarDistancia(-8.061754, -34.870683),-8.061754, -34.870683));
        recife.add(new Hotspot(R.drawable.sinagogarecife, "Sinagoga Kahal Zur Israel",
                MainActivity.determinarDistancia(-8.061842, -34.871402),-8.061842, -34.871402));
        recife.add(new Hotspot(R.drawable.caissertao, "Cais do Sertão",
                MainActivity.determinarDistancia(-8.060035, -34.869938),-8.060035, -34.869938));
        recife.add(new Hotspot(R.drawable.parqueescultura, "Parque das Esculturas",
                MainActivity.determinarDistancia(-8.063982, -34.869079),-8.063982, -34.869079));
        recife.add(new Hotspot(R.drawable.museurecife, "Museu da Cidade",
                MainActivity.determinarDistancia(-8.071841, -34.880892),-8.071841, -34.880892));
        return recife;
    }

    public float getAvalicao(){
        return 0;
    }
}
