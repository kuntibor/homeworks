End pointok:

GET: feltölti az adatbázist a resource mappában lévő json fájlból
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/load

POST: json formátumban lehet entity-t feltölteni
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/movies
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/categories
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/trailers
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/actors

GET: entity-t ad vissza id alapján
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/movies/{id}
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/categories/{id}
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/trailers/{id}
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/actors/{id}

PUT: json formátumban lehet entity-t frissíteni
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/movies
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/categories
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/trailers
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/actors

DELETE: entity-t törlése id alapján
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/movies/{id}
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/categories/{id}
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/trailers/{id}
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/actors/{id}

GET: query paraméterek alapján lehet entitásokat egymáshoz rendelni
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/join/movie/actor/?movieId={id}&actorId={id}
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/join/movie/category/?categoryId={id}&movieId={id}
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/join/movie/trailer/?movieId={id}&trailerId={id}

GET: Film keresése kategória és/vagy név alapján
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/query/movies/?categoryId={id}&title={title}

GET: Szereplő keresése film és/vagy név alapján
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/query/actors/?movieId={id}&firstName={firstName}&lastName={lastName}

GET: Szereplő keresése nemzetiség alapján
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/query/actors/nationality/{nationality}

GET: Trailer keresése film alapján
http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/query/trailers/?movieId={id}