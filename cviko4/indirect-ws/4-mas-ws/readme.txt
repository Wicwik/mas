na serveri www.agentspace.org bezi na porte 8080 tomcat a 
v nom servlety read a write, ktore citaju a zapisuju z a do space

zapisovat sa da web sluzbou 
http://www.agentspace.org:8080/write?a=1&b=2.3&c=ahoj

citat sa da web sluzbou
http://www.agentspace.org:8080/read?a&b&c

vyskusajte rucne z browsera a pozrite si html kod vratenej stranky (cez vyvojove pluginy v browseroch)

pozrite si dodany kod java tried, pochopte ako funguje.
Uvedomte si, ze cislo suradnice kocky zdielate so vsetkych pouzivatelmi webovej sluzby,
takze bud si zmente cislo suradnice na svoje vlastne - alebo sa necudujte, ze vam nou
hybu ostatni.

skompilujte kod a spustite ho.

presvedcite sa ze viete ovladat polohy kocky zavolanim webovej sluzby write z browsera

menej je vidno, ze polohu gulicky viete menit pomocou mysi, pretoze system ju vracia do polohy na webserveri

prerobte system po dvojiciach tak, ze spriahnete dve gulicky, tj pohyb mysou jednej hybe obomi a pohyb druhej tiez.

hint Zakladny algoritmus riesenia konfliktu pohybu kociek sme uz videli na minulom cviceni
