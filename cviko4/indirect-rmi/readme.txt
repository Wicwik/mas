prestudujte si implementaciu proprietarneho middleware na baze nativneho
marshallingu javy (org/agentspace) ktory implementuje zaklad nepriamej
komunikacie medzi agentami

mozno si pritom prezriet pouzivanie balikov (import, package)
mame tu jeden a to org.agentspace

Hlavnou triedou je RemoteSpace co je viacvlaknovy server operujuci
nad TCP, ktory implementuje rozhranie Space. Dalsou dolezitou triedou
je LocalSpace, ktory na stane klienta zastupuje RemoteSpace
(je to tzv. proxy) takze klientovi sa zda ako keby bol RemoteSpace
v jeho virtualnej masine, co nie je. Klientovi je to jedno, lebo on
k tomu pristupuje kazdopadne ako ku Space.

Ostatne triedy sluzia len na komunikaciu medzi klientom a serverom
a predstavuju serializovatelne data ktore tecu ako mobilne struktury
medzi klientom a serverom a spat. Zakladnou je SpaceMsg, ktora sluzi
len na zabezpecenie polymorfizmu a oznackovanie vsetkych odvodenych
struktur ako serializovatelne struktury

Kazda zo serializovatelnych struktur obsahuje serialVersionID
ale nekorektne v tom zmysle ze nie je zabezpecena jeho jedinecnost.
Mozete skusit v jednej z odvodenych tried, napr. SpaceMsgDelete
zakomentovat toto cislo a potom si nechat pomocou serialversion.bat
vygenerovat korektne cislo.

Spustite si Scenu (Controler), kde je pouzity rovinny senzor.
Overte si, ze so zobrazenou kockou mozete manipulovat pomocou mysi
Vypnite scenu.

pustite cez Starter Space, spustite znovu scenu a po nejakom case
odpalte Agent. Vidite, ze kocka je ovladana z agenta.

Po dvojiciach prerobte riesenie tak, ze skrz space pusteny na jednom 
pocitaci spriahnete dve sceny - ked hybete kockou v jednej,
druha bude pohyb sledovat a naopak. 

Ako sa riesi konflikt ked naraz hybeme s kockou v oboch scenach?

Doplnkova uloha: skuste slovne v odrazkach popisat co sa deje v systeme
ked sa kockou pohne. Nevynechavajte technicke detaily (ako napr marshalling).

