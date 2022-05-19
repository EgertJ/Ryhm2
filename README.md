Autorite nimed:
	
	Egert Jaansoo, Hugo Martin Teemus

Eesmärk:
	
	Programm abistab bussipiletite müügiga ja selle info väljastamisega.
	Programm on loodud 1. rühmatöö programmi baasil, mille graafilist kasutajaliidest on edasi arendatud.

Selgitus:
	
	Programm küsib kasutajalt bussi marsruuti seni, kuni kasutaja sisestab eksisteeriva marsruuti.
	
	Seejärel avaneb uus aken, kust küsitakse ostetavate piletite arvu senikaua,
	kuni sisestatud piletite arv mahub ära vabade kohtade arvu.
	
	Kui piletid müüdud, salvestab programm tehingu info tekstifaili
	ning laseb uuesti osta marsruutidele pileteid.


Klasside eesmärgid ja olulisemad meetodid:
	
	Peaklass - toimub programmi põhitöö, peaklassis asuvad ka graafilise kasutajaliidese meetodid.

	start meetodiga käivitatakse kasutajaliides koos vajaliku informatsiooniga,
	nupuvajutustega vastavalt väljastatakse kas erindid või liigutakse edasi programmi tsükkliga.
	
	
	loeFailist meetod loeb failist piletide info, ning loob igale piletile bussi.

    	Buss - info bussi kohta ning vabade kohtade arv bussis.
    	müüPiletid meetodiga muudetakse vabade kohtade arv bussis vastavalt müüdud piletite kogusele.

    	Pilet - info sõidu kohta: lähtepunkt, sihtpunkt, hind, sõidu algus- ja lõppaeg.
    	getBuss meetodiga saab bussi kohtade arvu pileti kaudu.

Protsess:
	
	1. rühmatöö -
    	Kõigepealt mõtlesime välja skeemi, mis klasse vaja oleks ülesandes ning mis klass millist kasutab >
    	Seejärel klassidele vajalikud muutujad ja meetodid >
    	Failist lugemise meetod >
    	Peaklassis põhiprogramm >
    	Põhiprogrammi testimine ning puuduste eemaldamine.

	2. rühmatöö - ehitasime programmile detailsema ja keerukama(koodi mõttes) graafilise liidese.

Iga rühmaliikme panus:
    	
	Tegime mõlemad koos, samal ajal, kasutades IntelliJ CodeWithMe, ning panustasime
    	sama palju. Ajakulu 2. rühmatöös oli samuti ~6h.

Tegemise mured:
    	
	Alguses testisime erinevaid infopaigutus viise (GridPane, Vbox jne), valimisega läks veidi aega, 
	enne kui jõudsime soovitud tulemuseni.
	
	Kohati läks aega soovitud paigutuse/funktsioonide nimetuste otsimisega.

Hinnang oma töö lõpptulemusele:
    	
	Meie arvates toimib programm koos kasutajaliidesega hästi, 
	kus on arvestatud ka piirjuhtudega/vigaste sisenditega.

Kuidas programmi testisime:
    	
	Andsime sisendisse erinevaid väärtusi ning vaatasime, 
	mida tegi programm nende sisendite jooksutamisel, vastavalt sellele lõime erindeid ning käsitlesime
	piirjuhtumeid.
