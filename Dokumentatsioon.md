Dokumentatsioon :)
Käivitamine:...

Programmi loomisprotsess:
Ma pole varem Spring Booti kasutanud, seega ennem sel nädalal tegelesin selle selgeks saamisega.
Kasutasin selleks videoid Youtube'ist, internetti ja ka Google Gemini abi, et segasemaid teemasid selgeks teha.
Kui jäin kuhugi peatuma, siis küsisin Gemini käest juhatavaid viise, kuidas edasi teha või mis loogikat kasutada.
Kuna keele piiranguid ei ole, otsustasin programmi kirjutada inglise keeles.


Esiteks mõtlesin, et võiks alustada API otspunktidest, sest videotes on tavaliselt sellest alustatud.
Lõin TableController'i, kus on meetod getAllTables, mis peaks tagastama listi laudadest. 
Aga selleks pean veel looma objekti nimega RestaurantTable, mis sisaldab vajaminevaid välju (id, size, occupied ja features).
Ma genereerisin juhuslikult igale lauale väärtuse, kas ta on broneeritud või mitte ning edasi mõtisklesin omaduste kohta.
Küsisin vihjeid Gemini käest, kuidas oleks lihtne lahendus sellele ning ta soovitas kasutada if-lauset random'iga
ning selle sisse lisada see antav väärtus ("aknaga").

