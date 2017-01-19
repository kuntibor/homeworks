A tesztel�shez a mell�kelt devices.json �s users.json f�jlokban l�v� entit�sokat �rdemes haszn�lni.
Device cart-hoz ad�s�hoz el�sz�r futtatni kell egy getAllDevice() met�dust, a lentebb szerepl� linkkel.
Az id-khoz innen kell v�lasztani egyet, �s be�rni az input json f�jlokba.
 
A tesztel�st a k�vetkez� linkekkel hajtottam v�gre:
http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/users
http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/devices
GET k�r�sek. Visszaadj�k az �sszes user-t �s device-t.

http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/users/login
POST k�r�s. Visszaadja az �ppen bejelentkezett user-t. Admin-k�nt �rdemes, hogy mindent kipr�b�lhassunk.

http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/users/{username}
http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/devices/{id}
GET k�r�sek. Visszaadj�k az param�terben megadott user-t �s device-t.

http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/users/add
http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/devices/add
POST k�r�sek. A json-ben megadott entit�sokat felt�lti a DB-kbe.

http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/users/delete
http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/devices/delete
POST k�r�sek. A json-ben megadott entit�sokat t�rli a DB-kb�l.

http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/cart/add
POST k�r�s. A json-ben megadott divece-t hozz�adja a cart-hoz.

http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/cart/delete
POST k�r�s. A json-ben megadott divece-t hozz�adja a cart-hoz.

http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/cart/buy 
POST k�r�s. Megveszi a cart tartalm�t.

http://localhost:8080/10-JavaEE-EJB-tiborkun-web/rest/cart
GET k�r�s. Visszaadja a cart tartalm�t json-ben.