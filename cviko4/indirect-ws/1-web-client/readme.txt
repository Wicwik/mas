Zistite si IP webovej stranky, ktora pouziva len http (nie https), napr.
ping dai.fmph.uniba.sk

pripravte si na kopirovanie tento request (vratane 2 prazdnych riadkov na konci)
GET / HTTP/1.1
User-Agent: me
Host: 192.168.1.15
Accept: */*



potom pustite z cmd
telnet-client ip port
pricom port je 80
a po vytvoreni spojenia
na nakopirujte tam onen jednoduchy HTTP request
(stlacenim praveho tlacidla mys nad oknom)
mal by reagovat html kodom

v hornom okraji okna pravym tlacitkom Edit / Select All a kliknite do zobrazeneho textu
(ak by to nefungovalo, nastavte Quick Edit Mode v Properties)
a vlozte odozvu do editora a ulozte ako pom.html

v textovom editore odstrante z pom.html HTTP hlavicku odpovede
otvorte pom.html v browseri napr ako 
file:///E:/users/cviko4/indirect-ws/1-web-client/pom.html

