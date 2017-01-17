# homeworks
A tesztelést a következő linkekkel hajtottam végre:
http://localhost:8080/async.tiborkun-web/async/users
GET kérés. Visszaadja az összes usert. Kezdettben üres json.

http://localhost:8080/async.tiborkun-web/async/devices
GET kérés. Visszaadja az összes device-t. Kezdettben üres json.

http://localhost:8080/async.tiborkun-web/async/users/load
POST kérés. Elkezdi betölteni a usereket egy json-ből.

http://localhost:8080/async.tiborkun-web/async/devices/load
POST kérés. Elkezdi betölteni a device-okat egy json-ből.
Ha az előző folyamat még nem ért véget, akkor látható a logban, hogy egyszer egy device, máskor egy user adódik hozzá. A két folyamat aszinkron fut.

http://localhost:8080/async.tiborkun-web/async/users
GET kérés. Visszaadja az összes usert. Már nem üres a json.

http://localhost:8080/async.tiborkun-web/async/devices
GET kérés. Visszaadja az összes device-t. Már nem üres a json.

http://localhost:8080/async.tiborkun-web/async/users/User
GET kérés. Visszaadja az összes felhasználót, amelyik tartalmazza a User szót. Egy json-el tér vissza.

http://localhost:8080/async.tiborkun-web/async/devices/SAMSUNG
GET kérés. Visszaadja az összes device-t, amelyik tartalmazza a SAMSUNG szót. Egy json-el tér vissza.