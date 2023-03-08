Ukazka volania servletu cez XML Http Request

spustite tomcat\system\mysystem.bat
pockajte kym to nabehne
na browsujte http://localhost:8080/html/index.html 
a stlacajte button

Ako to funguje

tomcat\system\MySystem.java 
- startuje JVM masinu v ramci ktorej okrem nasej aplikacnej logiky
  ktoru reprezentuje metoda calculate() bezi aj webserver tomcat

tomcat poskytuje bezne sluzby webservera tj lovi html dokumenty 
z adresarovej struktury ktoru ma od tomcat\webapps\ROOT

v tomcat\webapps\ROOT\html\ je index.html kde je html kod a javascript
volany zo stlacenia buttona a zostavujuci XML Http Request
http://localhost:8080/a?arg=1
ten mozno aj normalne samostatne nabrowsovat browserom (vyskusajte)
na to vsak nie je urceny, nevracia HTML ale javascript (alternativne by 
mohol a vlastne aj mal vracat XML)

ten zachyti tomcat a podla konfiguracie v 
tomcat\webapps\ROOT\WEB-INF\web.xml
vie ze ide o volanie servletu, ktory obsluhuje class-a A

servlet je implementovany v 
\tomcat\webapps\ROOT\WEB-INF\classes\A.java

