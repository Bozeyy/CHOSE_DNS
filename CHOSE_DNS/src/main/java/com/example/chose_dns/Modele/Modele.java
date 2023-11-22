package com.example.chose_dns.Modele;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;

public class Modele {


    private static final int MAX_RESPONSE_TIME = 1000;
    public ArrayList<String> listeDNS;

    public Modele() {
        this.listeDNS = new ArrayList<>();
        ajouterDNS("8.8.8.8");
        ajouterDNS("8.8.4.4");
        ajouterDNS("208.67.222.222");
        ajouterDNS("208.67.220.220");
        ajouterDNS("1.1.1.1");
        ajouterDNS("1.0.0.1");
        ajouterDNS("9.9.9.9");
        ajouterDNS("149.112.112.112");
        ajouterDNS("64.6.64.6");
        ajouterDNS("64.6.65.6");
        ajouterDNS("208.67.222.220");
        ajouterDNS("208.67.220.222");
        ajouterDNS("8.26.56.26");
        ajouterDNS("8.20.247.20");
        ajouterDNS("199.85.126.10");
        ajouterDNS("199.85.127.10");
        ajouterDNS("185.228.168.9");
        ajouterDNS("185.228.169.9");
        ajouterDNS("156.154.70.1");
        ajouterDNS("156.154.71.1");
        ajouterDNS("9.9.9.10");
        ajouterDNS("149.112.112.10");
        ajouterDNS("208.67.222.123");
        ajouterDNS("208.67.220.123");
        ajouterDNS("8.26.56.100");
        ajouterDNS("8.20.247.100");
        ajouterDNS("199.85.127.20");
        ajouterDNS("199.85.126.20");
        ajouterDNS("185.228.169.10");
        ajouterDNS("185.228.168.10");
        ajouterDNS("156.154.71.2");
        ajouterDNS("156.154.70.2");
        ajouterDNS("9.9.9.11");
        ajouterDNS("149.112.112.11");
    }

    private void ajouterDNS(String dns) {
        this.listeDNS.add(dns);
    }

    public List<String> getListeDNS() {
        return listeDNS;
    }

    /**
     * Méthode testDNS qui permet de ping le serveur DNS
     *
     * @param dns Adresse IP du serveur DNS
     * @return Une chaîne de caractères indiquant le résultat du ping
     * @throws IOException En cas d'erreur lors de la connexion au serveur DNS
     */
    public static int testDNS(String dns) throws IOException {
        try {
            long startTime = System.currentTimeMillis();
            InetAddress address = InetAddress.getByName(dns);
            Socket socket = new Socket(address, 53); // Port 53 est généralement utilisé pour les requêtes DNS
            long endTime = System.currentTimeMillis();

            // Calcul du temps de réponse en millisecondes
            long responseTime = endTime - startTime;

            // Si la connexion est réussie, le serveur DNS est accessible
            socket.close();

            // Vérifier si le temps de réponse dépasse 1 seconde
            if (responseTime > MAX_RESPONSE_TIME) {
                System.out.println("Le temps de réponse a dépassé la limite d'une seconde.");
                return MAX_RESPONSE_TIME; // Vous pouvez également lever une exception ici si nécessaire
            }

            return (int) responseTime;
        } catch (IOException e) {
            // En cas d'échec de la connexion, le serveur DNS est inaccessible
            return Integer.MAX_VALUE; // Utiliser MAX_VALUE pour indiquer un échec
        }
    }

    /**
     * Méthode qui permet de tester tous les serveurs DNS de la liste
     * et renvoyant le serveur DNS le plus rapide
     * @return Le serveur DNS le plus rapide
     */
    public String getBestDNS() throws IOException {
        String bestDNS = "";
        int bestTime = MAX_VALUE;
        for (String dns : listeDNS) {
            try {
                int time = testDNS(dns);
                System.out.println("DNS : " + dns + " - Temps de réponse : " + time);
                if (time < bestTime) {
                    bestTime = time;
                    bestDNS = dns;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bestDNS;
    }


    public static void main(String[] args) {
        Modele modele = new Modele();
        try {
            System.out.println(modele.getBestDNS());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
