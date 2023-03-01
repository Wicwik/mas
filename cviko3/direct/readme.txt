ObjectWriterAgent a ObjectReaderAgent su jednoduchou ukazkou 
priamej komunikacie medzi agentmi: Writer posiela spravu Readrovi.
Posielana sprava je textova.
pozrite si kod a pochopte ho

modifikujte text spravy podla lubovole (tak aby mal kazdy inu)
a skompilujte triedy

pozrite si runReader.bat - tentoraz je dolezite baty si precitat
a pochopit z nich ze pustaju containery a az tie pustaju agenty
a spustite pomocou neho hlavny container a v nom agenta Reader 
Argument -gui zariadi, ze sa spusti aj graficke prostredie zobrazujuce 
vsetky kontainery a agenty (treba si ten strom rozklikat aby ste videli
agenta, AMS, DF a rms - toto prostredie)

pomocou grafickeho rozhrania pravym tlacitkom pustite v containeri 
Jadu Writer a pozorujte vypis Readera potvrdzujuci ze dostal spravu
takto pustite Writer v tom istom containeri

pozrite si run runWriter.bat ktory pusti Writer vo vedlajsom 
containeri. Presvedcite sa ze tiez funguje, tj vsetko vypnite
a pustite hlavny kontainer s readrom a potom vedlajsi s Writerom

po dvojiciach skuste vhodne modifikovat runWriter.bat a poslite si spravu
z jedneho PC na druhe
- pritom musite na strane writera definovat ako -host ipcku kde 
bezi hlavny kontainer, t.j. reader (ipecku zistite napr ipconfig z cmd)
- na oboch stranach musite zadefinovat ako -local-host skutocnu IP
na ktorej pustate container (tj na kazdej strane bude ina)
- ked pustate writera, nesmie u vas bezat reader, lebo inak 
writer zisti, ze port 1099 je obsadeny a zvoli si nahodne iny port,
ktory ale blokuje firewall (1099 mame povoleny)

vyskusajte niektory iny content na prenesenie spravy, napr ako
StringLanguage, vid zakomentovany kod (tj. modifikujete kod writera). 
Podobne XML, ktore vyzaduje spravny codec co sa odzrkadluje aj v bat-och.

Reader a Writer maju cely svoj funkcny kod v setup()
Prerobte ich tak aby setup nastartoval nejaky Behavior a skoncil
(t.j. setup len zavesi Behavior implementovany anonymnou triedou)
pozri doc/index.html Behavior
Idealne je pouzit CyclicBehavior na strane readera a 
OneShotBehavior ma strane writera 
(pozor na doDelete)

Ball Agent je agent, ktory kontroluje virtualnu scenu s kockou,
s ktorou je mozne hybat mysou. Skompilujte ho. Spustite ho tak, ze
pustite najprv hlavny kontainer, potom prveho a druheho agenta
vo vedlajsich kontaineroch. Uvidite ze pohyby kociek su synchronizovane.
(pozor pri restarte musite vypnut a zapnut aj hlavny container aby
sa vycistil jeho DF)

Prerobte toto riesenie po dvojiciach na sietove, tj pohyb kocky z jedneho
pocitaca sa prenasa na pohyb kocky na druhom pocitaci. Berte v uvahu,
ze kod agenta sa nemusi pri zmenit, musi byt len sam o sebe funkcny.
Menit potrebujete jeho spustanie v bat suboroch.

Pritom si vsimnite, ze aj vedlajsi kontainer pocuva na urcitom porte
(a teda inom ako hlavny kontainer) - je to systém peer-to-peer. 
Pokial pre vedlajsi kontainer nepouzijete prepinac -local-port, ktory 
definuje port povoleny nasim firewallom, napr 7171, tak vsetci ho budu
v systeme vidiet, budu mu posielat spravy, ale on nebude nic dostavat.
Takze synchronizacia medzi dvomi kockami na inych pocitacoch ako je tam
kde bezi hlavny kontainer bude vzajomna, ale ak jedna z kociek pobezi
na rovnakom pocitaci ako hlavny kontainer a druha z iného stroja, tak
jednostranna. (vzajomna bude aj ked pustime dve gulucky na stroji s hlavnym
kontainerom, lebo ich komunikaciu firewall neovplyvnuje). Prepinac -local-port
nam umozni agenta pustit na povolenom porte, co nam tento problem vyriesi.

Hviezdickova uloha:
Sledujete atribut last a identifujte algoritmus, ktory sychronizuje
polohu kociek 

Domáca uloha: 
Upravte kod agent BallAgent aby sa dal agent vypnut a zapnut bez
restartu hlavneho kontainera
