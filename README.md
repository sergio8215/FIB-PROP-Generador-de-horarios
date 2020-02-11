# FIB-PROP-Schedules-Generator

Es tracta de construir un entorn per a la generació d’horaris de diverses unitats docents segons
certes condicions establertes prèviament. El marc d’aplicació ha de ser el mes flexible possible.
Podem considerar una unitat docent (per exemple la FIB) que ha d’organitzar els horaris
corresponents a diversos plans d’estudis i diverses titulacions.

Per començar s’establirà un escenari fix, on es definiran les condicions relatives al període de
classes, aules disponibles, pla d’estudis i propietats de cada assignatura. Aquest escenari
defineix implícitament unes restriccions concretes i a més a més es podrien definir d’altres de
forma explicita.

Com a mínim s’han de contemplar el següents tipus de restricció: l’organització per nivells del
pla d’estudis (no solapament d’assignatures del mateix nivell), correquisits entre assignatures,
el número, tipus i duració de les sessions per a cada assignatura, el període lectiu i el número
d’aules (i capacitat) necessàries per a cada assignatura (en funció del seus grups).

El sistema haurà de permetre la confecció d’un horari segons les restriccions indicades per
l’usuari, tenint en compte que:

  * Pot no haver solució per una petició de l’usuari. En aquest cas l’usuari haurà de eliminar
(o suavitzar) alguna(es) de les restriccions perquè hi hagi solució.
  * Es valorarà positivament la riquesa expressiva de les restriccions de l’aplicació.
  * Les dades s’han de poder definir via el programa o importar des d’un fitxer de text.
  * El sistema haurà de permetre la modificació posterior de la solució proposada
(mantenint la consistència).
  * A més dels altres factors de qualitat de qualsevol programa (disseny, codificació,
reusabilitat, modificabilitat, usabilitat, documentació,...), es valorarà en particular
l'eficiència i flexibilitat d’aquest.
  * Com a extensió es podria contemplar la possibilitat de que certes restriccions siguin
meres preferències.

## En la carpeta doc se encontraran los manuales y la documentación necesaria.
