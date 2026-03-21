Dokumentatsioon :)
Käivitamine:...

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