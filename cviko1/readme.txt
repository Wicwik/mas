skompilujte cez compile.bat (alebo svojim vlastnym sposobom)
spustite cez run.bat (alebo svojim vlastnym sposobom)
(v batoch mozno treba upravit cestu k javac.exe respektive java.exe)
mali by ste vidiet dve gulicky ako preletia cez seba
(Mozete pouzivat aj svoje oblubene IDE, ktore si nastavite podla tychto bat-ov)

otvorte Scene.java v editore (napr Notepad++) aspon takom
aby zviditelnoval syntax Javy. Skuste pochopit dany kod.
- nacrtnite stromovu strukturu 3D sceny
- kde v kode vidime pouzitie anonymnej triedy?
- ako sa nazyva i v getroch a setri ? (napoveda: d........r)
- odkial sa nacitaju textury wood.jpg a stone.jpg ? (pripadne vyextrahujte)

ked si pokazite formatovanie suborov.java, format.bat ho obnovi, vyskusajte.

V podadresari java je zopar poznamok k jazyku Java, ktoré zrejme nepotrebujete, lebo ich viete,
avsak kto by niecomu z toho nerozumel, nech sa ihned ozve, polahky ho to doucime. 

Vidime ze main v Scene.java pouziva cyklus for

Prerobte main tak, aby sa gulicky od seba odrazili. Na to staci v 45 kroku zmenit smer pohybu
oboch guliciek na opacny

Urobte classu Gulka (kostru takej triedy mate v podadresari serialization) ktora 
bude "zit vlastnym zivotom".
Tak, aby gulky lietali (staci ked preletia cez seba) a pritom main vyzeral takto:

public static void main( String[] args ) {
  	initialize();
  	new Gulka(1,0.1f);
  	new Gulka(2,-0.1f);
  	delay(10000);
	System.exit(0);
}

Pomozte si kodmi v java/java.pdf a to bud
- Thread + delay (tj gulka ma vlastne vlakno v ktorom v cykle kona pohyb a vola delay) ()
- TimeredTask (tj pohyb gulky je spustany z vlakna casovaca) (str. 26)

hviezdickova uloha:
Ako teraz zariadit aby sa gulky od seba odrazili - ak to nema byt urobene dopredu naplanovanym spravnym
pohybom gulky na slepo? Zariadte, aby si mohli vzajomne otestovat, ze su pri sebe v zraze a odrazili sa.
Staci to riesit pre dve gulky, cize gulka id testuje zrazku s gulkou 3-id.

domaca uloha: vyskusajte si serializaciu v podadresari serialization (vid readme v podadresari)
Ked vymenite byteArray I/O za File I/O implementujete perzistenciu objektu Gulka.
Prerobte main tak, aby ulozit stav Guliek po 10 sekundach a po dalsom 
spusteni ho - pokial je k dispozicii - nacital, takze gulky budu pokracovat
tam, kde prestali.
