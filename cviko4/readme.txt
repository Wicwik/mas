mame tu dve riesenia budovanie multiagentoveho systemu s nepriamou komunikaciou
- pomocou webovych sluzieb
- pomocou TCP s nativnym mashalingom javy

pracujte v indirect-ws

webove sluzby maju 4 casti: 1, 2, 3, 4 
venujte sa im postupne.

cast 3 nie je taziskova a tyka sa toho ako sa spravi samotna webova sluzba
v casti 4 je vam nedostupna serverovska cast, ktoru len pouzivate, jej implemetacia 
je k nahliadnutiu v podadresari tomcat7

v kazdom podadresari je samostatne readme podla ktoreho postupujte

hviezdickova uloha: navrhnite (neimplementujte, nemate ako) aby sme do 4
classe WebService dorobili blockingRead(). Moznosti je viacero, ale v 
principe je to pre koncept webovych sluzieb problem. Netreba riesenie implementovat,
len vymysliet ako by sme na to mohli ist.

domaca uloha: indirect-rmi\readme (mozete ju urobit uz na hodine)