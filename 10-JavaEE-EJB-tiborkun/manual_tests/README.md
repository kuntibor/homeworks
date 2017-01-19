A teszteléshez a mellékelt devices.json és users.json fájlokban lévõ entitásokat érdemes használni.
Device cart-hoz adásához elõször futtatni kell egy getAllDevice() metódust, a lentebb szereplõ linkkel.
Az id-khoz innen kell választani egyet, és beírni az input json fájlokba.
 
A tesztelést a következõ linkekkel hajtottam végre:
http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/users
http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/devices
GET kérések. Visszaadják az összes user-t és device-t.

http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/users/login
POST kérés. Visszaadja az éppen bejelentkezett user-t. Admin-ként érdemes, hogy mindent kipróbálhassunk.

http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/users/{username}
http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/devices/{id}
GET kérések. Visszaadják az paraméterben megadott user-t és device-t.

http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/users/add
http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/devices/add
POST kérések. A json-ben megadott entitásokat feltölti a DB-kbe.

http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/users/delete
http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/devices/delete
POST kérések. A json-ben megadott entitásokat törli a DB-kbõl.

http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/cart/add
POST kérés. A json-ben megadott divece-t hozzáadja a cart-hoz.

http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/cart/delete
POST kérés. A json-ben megadott divece-t hozzáadja a cart-hoz.

http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/cart/buy 
POST kérés. Megveszi a cart tartalmát.

http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/cart
GET kérés. Visszaadja a cart tartalmát json-ben.