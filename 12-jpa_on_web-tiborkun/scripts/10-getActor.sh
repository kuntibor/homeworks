curl --verbose --write-out "\n" --request GET --cookie-jar cart-cookies.txt --cookie cart-cookies.txt 'http://localhost:8080/12-jpa_on_web-tiborkun-web/rest/query/actors/?movieId=1&firstName=Harrison&lastName=Ford'