curl --verbose --write-out "\n" --request POST --data @01-flight.json --header "Content-Type: application/json" --cookie-jar cart-cookies.txt --cookie cart-cookies.txt 'http://localhost:8080/13-jms-flight-tiborkun-web/rest/flights'
