Najprv na zahriatie to, co zo sietoveho programovania pozname
tj programovanie na trasportnej vrstve (vid cast tcp):

(nemente port 7171, lebo jedine on je nastaveny vo firewalle ako povoleny
keby nieco zahadne nefungovalo, alebo keby sa ozval firewall, treba hlasit)


1. skompilujte triedy (mate na to bat-y),
   pustite Server (taktiez mate na to bat)
   a otvorte scenu ovladanu Controller-om. Uvidite pohybujucu sa gulku. 
   Presvedcite sa ze bez spustenia Servera sa gulka nehybe
   
2. prestudujte si ako sa Server.class podiela na pohybe 
   gulicky a uvedomte ze se Server bezi pod inou JVM nez
   Controller. Vsimnite si ako funguje Server: je odvodeny
   od triedy Thread a v konstruktore vola start(). To je 
   metoda zdedena z Thread-u ktora vytvori nove vlakno
   a pusti v nom metodu run().

3. po dvojiciach: cez net.bat zistite adresu jedneho 
   z vasich strojov a prepracujte riesenie tak, ze server
   pobezi u jedneho a klient (Controller) u druheho

4. prepracujte riesenie tak, aby vedel server zvladnut viac 
   klientov - aby ste z jedneho servera obsluzili viac scen naraz.
   (hint: uprava nie je zlozita)
   
5. usposobte pracu servera tak aby vedel rozlisovat medzi 
   klientami a aby im odpovedal tak, ze ovlada dve sceny
   zobrazene na vedla seba postavenych monitoroch tak, aby 
   gulicka pri vodorovnom pohybe zdanlivo preskocila z jedneho
   monitora do druheho. (Staci to napevno pre konkretne id, 
   napr 2 posuvame az ked sa 1 posunie dostatocne daleko)
   
A teraz budeme programovat na prezentacnej vrstve, tj.
budeme pouzivat middleware, konkretne Remote Method Invocation (RMI)
Ulohy: (vid cast rmi-jrm):
(firewall by sme mali mat nastaveny tak ze povoli port 1099 - rmi registry 
co je TCP server pocuvajuci na porte 1099 realizujuci naming service)

skompilujte interface Space
skompilujte RemoteSpace co bude RMI object
pustime rmi.bat 
pustime cez runRemoteSpace.bat RemoteSpace 
skompilujeme Agenta
pustime Agenta cez runAgent.bat
(rmiregistry musi bezat len na tom uzle, kde bezi RemoteSpace)

pomocou RMI tu mate implementovane dve zakladne sluzby nepriamej komunikacie:
read a write

- skompilujte Controler (Scene.java je rovnaky ako pri tcp, ale Controler sa lisi)
  a spustite ho
  
- upravte Agent tak, aby sa kocka v scene hybala od momentu spustenia Agent
 
hviezdickova uloha:  
- modifikujte riesenie pre komunikaciu medzi dvomi uzlami (pustite rmiregistry.exe
  a ako adresu pocitaca pouzite IP adresu alebo logicke meno ktore ma pridelene vo windows - 
  to mozno zistit cez ipconfig /all napr H3-01.dai.fmph.uniba.sk) 
  (rmiregistry musi byt pustene z toho isteho adresara ako je skeleton!)
  (problem s dalsou virtualnou IP ktoru maju pcka v ucebniach Matfyzu rovnaku,
   sa riesi nastavenim java.rmi.server.hostname)
  (to ze mame firewall povolujuci len urcite porty si vyzaduje pri sietovom rieseni nastavit
  konkretny port 7171 pre RemoteSpace)
  rozhybte kocku na jednom uzle, odpalenim Agent na druhom uzle
  (pracujte po dvojiciach)

Domace ulohy:
- doplnte rozhranie Space o vlastnu metodu, napriklad blokujuci read, t.j. 
  pokial sa klient snazi citat objekt ktory este nie je zapisany,
  tak sa zablokuje kym iny klient tento objekt nezapise
  (pritom na zablokovanie mozete pouzit nieco drevorubacske, napr
  cinne cakanie, lebo synchronizacne prvky javy sme este nepreberali)
  pri cinnom cakani mozete nechat systemu vydychnut zaspatim na 
  urcity pocet milisekund takto:
  try {
  	Thread.sleep(ms);
  } catch (InterruptedException ee) {
  }


