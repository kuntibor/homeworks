A program induláskor feltölt néhány járatot a source mappában lévő json fájlból, és beállítja az indulási időket az aktuális időhöz viszonyítva 1-1 perccel eltolva.
Az értesítések percenként jönnek.
A járat törléséről és módosításáról a script mappában lévő scriptekkel lehet értesítést kapni a teszteléshez.

End pointok:

POST: json formátumban lehet járatot feltölteni
http://localhost:8080/13-jms-flight-tiborkun-web/rest/flights

GET: járatot ad vissza id alapján
http://localhost:8080/13-jms-flight-tiborkun-web/rest/flights/{id}

PUT: json formátumban lehet járatot frissíteni
http://localhost:8080/13-jms-flight-tiborkun-web/rest/flights

DELETE: járat törlése id alapján
http://localhost:8080/13-jms-flight-tiborkun-web/rest/flights/{id}

GET: az összes járatot visszaadja
http://localhost:8080/13-jms-flight-tiborkun-web/rest/flights/all


