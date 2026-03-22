Dokumentatsioon:
Käivitamine: Käivita fail NutikasRestoranApplication. Mine leheküljele http://localhost:8080/. Seal näed broneeritud laudu
punastena ning vabu rohelistena. Vali sobilikud filtrid ning uuenda vaadet. Parim valik on hele aqua värvi.

Programmi loomisprotsess:
Ma pole varem Spring Booti kasutanud, seega ennem sel nädalal tegelesin selle selgeks saamisega.
Kasutasin selleks videoid Youtube'ist, internetti ja ka Google Gemini abi, et segasemaid teemasid selgeks teha.
Kui jäin kuhugi peatuma, siis küsisin Gemini käest juhatavaid viise, kuidas edasi teha või mis loogikat kasutada.
Kuna keele piiranguid ei ole, otsustasin programmi kirjutada inglise keeles.
Kui leian, et pole varem selle annotatsiooniga kokku puutunud, siis lisan enda jaoks selgitusi.


Esiteks mõtlesin, et võiks alustada API otspunktidest, sest videotes on tavaliselt sellest alustatud.
Lõin TableController'i, kus on meetod getAllTables, mis peaks tagastama listi laudadest. 
Aga selleks pean veel looma objekti nimega RestaurantTable, mis sisaldab vajaminevaid välju (id, size, occupied ja features).
Ma genereerisin juhuslikult igale lauale väärtuse, kas ta on broneeritud või mitte ning edasi mõtisklesin omaduste kohta.
Küsisin vihjeid Gemini käest, kuidas oleks lihtne lahendus sellele ning ta soovitas kasutada if-lauset random'iga
ning selle sisse lisada see antav väärtus ("Akna all").

Edasi pean lisama filtreeringu, kuna kasutaja soovib näha laudu, mis on seotud tema sisestatud parameetriga.
Alustasin lihtsamast ehk laua suurusest. Selleks kasutasin RequestParam, mille abil võtan URL'i sisestatud parameetri
ning filtreerin .stream()-i funktsiooniga sobilikud lauad.
Jätsin alles siis nimekijra, kus kõige esimene laud on soovitatiuim ja järgnevad lauad on siis vähem soovitatud.

Mulle tundub kõige raskema osana kellaajaga arvestamine. Selleks küsisin abi Gemini'lt kust peaksin alustama, ta juhendas,
et esialgu võiks uue klassi luua nimega Reservation. Ma kaalutlesin, kas oleks parem lisada ReservationTable'i objekti uue välja
nimega Reservation või siis hoida globaalset nimekirja TableControlleris või eraldi teenuses. Küsisin nõu Google Gemini'lt
ning ta vastas, et paindlikum oleks globaalne nimekiri, mis ühtlasi soosib ka kodeerimises "loose coupling" reeglit. Seega 
otsustasin antud idee suunas liikuda. Seejärel pidin välja mõtlema, kuidas genereerida suvalised kellaajad broneeringutele.
Kui laud luuakse staatuse "occupied", siis lisan talle algus-ja lõpukellaaja. Selleks kasutasin LocalDateTime'i. Et kontrollida, kas
uue broneeringu tegemisel kattub ta vanaga, pidin paika panema loogika: "Kui uue broneeringu algus on varasem kui olemasoleva lõpp
ning uue lõpp on hilisem kui olemasoleva algus, siis uue broneeringu tegemine ei sobi". Tehes selles funktsiooni, sain selle kergemini
implementeerida getTable meetodi, kus lisasin filtri laua ajalisele olemasolule.

Saaliplaani jaoks pean lisama väljad, mille järgi arvuti teaks luua struktuur, kus mis laud asetseb (x, y). Mõtlesin algul koordinaadid
juhuslikult genereerida, kuid siis tekkis küsimus, kuidas kontrollida, et antud koordinaadid on juba olemas. Selle asemel otsustasin teha
kindla plaani, kus mis laud asetseb, sest see tundub programmi puhul loogilisem, lauad ei peaks iga kord vahetuma, aga broneeringud küll.
Lisasin x ja y tsüklisse ning mõtisklesin, kuidas tsoonidega arvestada. Küsisin suunavaid küsimusi Gemini'lt ja ta soovitas
ridade kaupa liigitada tsoonid. Selleks pidin aga RestaurantTable klassi looma uue välja nimega "zone". Mõtlesin, et võiksin proovida
nutikamat lahendust, ehk anda skoorid igale lauale, mis sobivad inimese kirjeldatud kriteeriumitele. See variant tundus põnevam. Ma ei osanud ise alustada, seega küsisin
uuesti nõu Gemini käest, kuidas tema sellele läheneks. Eesmärgiks oleks leida laud, millel on kõige vähem karistuspunkte. Reeglid oleksid järgmised:
- Vale tsoon: +10 punkti
- Puuduolev omadus: +5 punkti
- Suuruse vahe: +1 iga üleliigse koha kohta.

Selleks lõin abimeetodi calculateScore. Lisasin selle getTable meetodi Comparatorisse, et selle asemel, et sorteerida suuruse järjekorras, sorteerib nüüd skooride
järgi.

Nüüd on vaja kuvada saaliplaan ka veebilehel. Kõigepealt lõin index.html'i, kuhu saaliplaan peaks ennast kuvama. Siis lisasin natuke ajutiselt css'i koodi ning lõpuks scripti.
Ma pole väga kogenud JS kirjutamises, seega selles osas küsisin rohekm abi Gemini'lt. 

Nüüd on vaja lisada HTML'i sisendväljad, kuna kuigi vaade on olemas, sisi meil pole viisi broneerida või otsida sobilikke laudu. Selleks peaks olema 3 sisendparameetrit:
inimeste arv, eelistatud tsoon, soovitud omadused ning lõpuks kellaaeg. Selleks küsisin veelkord Gemini käest abi. Selle käigus mõtlesin, et mõtekam oleks
vahetada input asemel select tag'i, kui inimene peaks valima tsooni. Kuna otsustasin, et juhul kui inimene ei valinud mingit kindlat tsooni,
siis ei võeta karistuspunkte arvesse ning muutsin calculateScore'i meetodi kontrolli. Kontrollisin, kas HTML töötab, aga siis leidsin, et midagi läks katki.
Küsisin Gemini käest ning leidsime, et viga on tableController'is, sest ta ei arvestanud kasutaja sisenditega. Pärast seda parandasin JS-i, mis ei võtnud algul arvesse kellaaega ja tsooni.
Lisasin vajaminevad väljad ning nüüd lisan kalendri miinimumaja kohe lehe laadimisel.

Kuna lehel polnud näha, millist lauda kõige rohkem soovitan, siis otsustasin, et võiks siis paremad lauad ära värvida. Selleks lisasin välja klassi RestaurantTable score ning muutsin calculateScore
abimeetodi.

Avastasin, et peaksin esile tõstma soovitatud laudu. Pidin arvestama, et skoor tähendab siin karistuspunkte ning mida vähem, seda soovitatum on laud. Otsustasin selle teha aqua värviks. Otsin laua, mis on vaba
ning tema skoor on madal, siis värvin hele-aquaks. Lisasin ka selgituse karistuspunktide kohta ning parimale lauale "soovitatud!" sildi. Edasi otsustasin natuke ilusamaks lehte teha. Enne seda märkasin, et tekib 
NullPointerException probleem, kuna programm üritab null väärtust kasutada kui integer'ina, seega pidin TableController'isse lisama lisaks kontrolli
juhul kui klient pole sisestanud size'i numbrit. Avastasin, et olin valet muutujat kasutanud (size, mitte targetSize). Leidsin CSS'i jaoks huvitava viisi pealkirja kuvada https://blog.logrocket.com/css-header-styles-cross-browser-compatibility/#typewriter-effect-html-css.

See lahendus pole täiuslik, tal on omad vead näiteks skoori süsteem võiks parem olla, aga andsin endast parima.
