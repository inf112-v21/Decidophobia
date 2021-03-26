# Obligatorisk innlevering 3

### Deloppgave 1: Team og prosjekt

__Deltakere i teamet:__  
Alexander Gullestad: Kodetester  
Oscar Strømsli: Kundekontakt    
Isak Hølleland: Tech-lead   
Lars Andre Sande: UI-design     
Petter Paulsen: Sekretær
<br>
<br>
#### Roller i teamet
Rollene i teamet har fungert godt. Samtlige deltakere har tatt sine ansvarsområder  
på alvor og viser en god forståelse for sine oppgaver.  
Kodetester har ansvar for å lage gode tester til koden som blir skrevet.
Vi har beholdt de samme rollene.

Alexander har fått denne rollen og har løst den på en god måte.  
Vi ser på kodetesting som en viktig del av  
prosjektet, for å kunne verifisere at ting fungerer som det skal.

Rollen som kundekontakt ble gitt til Oscar. Han har altså hatt ansvaret  
for å kontakte kunden med ulike   problemer eller spørsmål vi har hatt til  
utviklingen av prosjektet. Rollen i seg selv er en spesielt viktig rolle  
for å kunne sikre at prosjektet står i tråd til kundens   forventninger,  
men vi har så langt i prosjektet følt at vi har hatt  
en god fremgang og enda ikke hatt store behov for å konsultere med kunde.  
Kundekontakt har også hatt rollen om å presentere prosjektet for kunden (Siv)  
og dette gikk veldig bra synes vi.

Isak har som Tech-lead hatt ansvar for selve kodingen i prosjektet,  
og for å ha et overordnet ansvar over fremgangen. Denne oppgaven er  
også spesielt viktig, ettersom det er kritisk at minst én person har  
en gjennomgående forståelse for de ulike oppgavene som til enhvertid  
arbeides med. Tech-lead kan også med et godt overordnet ansvar  
distribuere arbeidsoppgaver jevnt i teamet, og er best egnet til å  
kunne fordele disse på en god måte. Tech-lead-rollen har fungert godt  
og har vært en viktig rolle gjennom hele prosjektet for at gruppemedlemmer  
skal til enhver tid vite hva de skal jobbe med og hva de andre jobber med.  
Vi ser også at det er svært viktig at Tech-lead har god forståelse  
av hver del av prosjektet for at han skal kunne finne ut hva det neste  
vi skal ta for oss er.

Ansvaret for UI-design har vært hos Lars Andre. UI-designer skal være den  
som er ansvarlig for utformingen av GUI som kunden etter leveransen skal  
benytte seg av. Ettersom det er først og fremst UI-designeren sitt arbeid  
kunden først og fremst skal benytte seg av, så er det viktig at dette gjøres  
på en intuitiv og brukervennlig måte.

Som sekretær har Petter hatt ansvar for deliverables. Denne rollen har vært  
viktig for å være sikre på at en person alltid tar gode notater som er  
lettlesbare og tilgjengelige for gruppen. Disse må også ha en god formulering  
ettersom de skal leveres og leses av TA.

#### Gruppedynamikk og kommunikasjon
Generelt synes teamet det er synd å ikke kunne jobbe fysisk i felleskap til samme grad som ellers. Vi føler likevel vi har løst dette på en god måte ved at alle i teamet har kontinuerlig
vært tilgjengelige på discord. Vi har også ført alle møter på discord, og har gjennomført disse raskt og effektivt. Teamet har en god tone sammen og samtlige føler vi faktisk er ett godt samarbeidende team hvor ingen har blitt utelatt
i hverken samtaler eller avgjørelser. Dette har vært viktig for oss fra starten av.

Vi har delt gruppen i to, siden det var spesielt to ting ingen av oss hadde noe særlig erfaring med og det var Server/Client der vi satte 2 i arbeid og GUI der resten gikk. 
Det nye vi har prøvd av arbeidsmetodikk er at vi har prøvd å bruke mer parprogrammering og føler det har fungert svært godt hos Server/Client-paret.
Det ble lettere å oppdage feil når vi kodet ilag, i tillegg "tvang" det oss til å forklare koden mer. Vi brukte også mye tid på å diskutere hva som var beste
løsning og implementerte letter ting som passet ilag (når vi programmerte forskjellige ting).
Samtidig ble det vanskelig hos GUI-gruppen å få tid samtidig og individuelt arbeid ble brukt mer i praksis.


Vi har også hatt et projectBoard i Git der vi publiserte oppgaver, men dette har vært lite brukt og vi har i stede blitt enige om oppgavene vi skal gjøre muntlig på
gruppetimer. Vi ser at et projectboard kan være svært nyttig i arbeidslivet, at man kommer på jobb og kan "plukke" en oppgave å jobbe med i noen timer eller lignende,
men i vår tilfelle har vi ikke hatt særlig nytte av det siden en oppgave tar kanskje en uke og i og med at vi ikke er så erfarne med systemutvikling har vi også hatt en
tendens til å lage veldig åpne oppgaver med få detaljer, sånn at den som tar oppgaven kan "spesialisere" seg innenfor det feltet oppgaven krever.

#### Retroperspektiv
Slik koden er i dag er det særlig 3 store deler av prosjektet, det er Server/Client (multiplayer), Game (spill-logikk) og GUI.  
Planen er at GUI-en kjører kontinuerlig og fanger opp endringer i Game. GUI tar også input fra bruker og "sender" dette til Game.  
Game tar så å gir inputet i form av hvilke kort som brukeren har valgt til sin Client som sender dette til server.
Server sender til alle spillernes client hvilke kort som er valgt og alle bruker disse kortene til å oppdatere sin versjon av spillet.  
Client gir dette til Game, som da prossesserer runden etter å fått kortene til alle.  
Av dette så er Server/Client og game sin kommunikasjon fungerende, mens GUI er enda ikke knytta til Game-objektet i spillets prosess.  
Veien videre er å få med GUI-en i spill-prosessen og å få til at man kan navigere en meny for å sette opp spill.

#### Det som er gjort er:  
__Server/Client:__    
    utviklet Server/Client objekter som kommuniserer ilag og client kommuniserer med Game.  
    Her har vi også laget mange verktøy til Server/Client-forholdet som kan bli svært nyttig til neste sprint.  
    Vi synes at utviklingen av Server/Client har vært god, vi har truffet noen hinder som object-serializing der vi veide  
    våre muligheter i forhold til tid og videre utvikling og valgte da en annen metode å utvikle et request/response-språk  
    på. Vi synes at det valget vi tok var lurt siden det er enkelt å legge til mere i språket, det som kan være litt prolematisk  
    er derimot at for nye personer som ser koden kan det ta litt tid å bli kjent med Requestene og debugge om testene ikke  
    skulle passere senere.  
  
__Vi har også gjort en del utvikling med GUI:__  
    Utviklingen for GUI-en har vist seg å være svært vanskelig, under første sprint utviklet vi et rotete  
    GUI-objekt som skulle kun gjøre jobben for den sprinten og for at vi skulle bli kjent med hvordan LibGDX  
    fungerte, i denne sprinten har jobben fortsatt med å dele opp oppgavene som denne gamle GUI-en gjorde og  
    i tillegg, legge til rette for at applikasjonen kan ha andre scenes/screens som "main-menu", "settings" og  
    "lobby". Derfor har mye av tiden til GUI-gruppen gått til å utvikle en screenManager som bytter mellom screens  
    og som "manager" objketene for hver screen.  
  

- Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint
- Forklar kort hvordan dere har prioritert oppgavene fremover. Legg ved skjermdump av project board
ved innlevering

__Tre ting vi skal forbedre i løp av neste sprint:__  
    1. ScreenManager, lage et system som gjør det mulig å "bevege" seg mellom forskjellige screens.
    1. At en GameGUI er definert til å vise informasjon av et Game-Objekt.
    1. Implementere spill-logikk i Game-objektet.


Når det gjelder hver enkelts deltakelse i kodingen vil dette variere noe fra person til person. Det er naturlig at tech-lead vil bidra med mer kode enn UI-designer eller sekretær. Vi forsøker likevel ha en jevn fordeling av koding i teamet
slik at alle får bidra, men lar hver enkelt få styre sin egen deltakelse etter ønske og evne. 

### Deloppgave 2: Krav

#### Krav
1. Klare å kunne spille over nett med multiplayer
2. Spill logikk som omhandler spillets gang
3. GUI for å starte opp spillet, skal inneholde startside, settings og lobby for multiplayer
4. GUI for spillet som viser brett og lar bruker velge kort som kan bli spilt med

Vårt mål for Oblig3 var å få til mv og levere et spill som kan spilles over nettet. Vi prioriterte multiplayer og GUI fordi  
vi er usikre på hvordan det virker og hvordan vi kan implementere de for prosjektet. Vi har klart å få server og client oppe  
å gå sånn at det er mulig å sende informasjon over nettet. Forklaring av multiplayer finnes under Online - RoboServer & RoboClient.  
Vi har også fått sammenkoblet RoboClient sammen med Game der game logikken skal ligge. GUI for spill, lobby og start meny har ikke vært så stor framgang pågrunn av vanskeligheter med skin til kanpper og andre funksjoner som er nødvendig. Vi har fått begynt på et setup med screens for alle kravene men pågrunn av vanskeligheter med skins har ikke det kommet særlig lengre. 

Brukerhistorier finner du i BrukerHistorierOblig3.md

Bugs ligger i README.md

### Deloppgave 3: Produktleveranse og kodekvalitet

Manuelle tester ligger i ManuelTests.md

#### Spillet
    Spillet startes ved å kjøre launcher.class,
    Denne delen av spillet er foreløpig bare for
    å manuelt kunne teste hvordan en robot kan
    bevege seg, hvordan UI ser ut.

#### Online - RoboServer & RoboClient
    Testene til denne delen av prosjektet er parserTest og serverTest.
    serverTest tester at man kan bare joine et spill 1 gang, man kan ikke joine flere ganger.
    I tillegg tester den at det er den forventede informasjonen som blir sendt fra server etter
    at en client har sendt en request. Slik som at når man sender et move, så blir move-t tolket
    riktig.
     
    ParserTest-en ligner litt på deler av ServerTest, men tester bare om parseren til client tolker 
    objekter definert med en String i constructeren riktig og at toString-metoden konstruerer denne
    Stringen på riktig måte slik at en både kan sende og motta et objekt definert av en String.
     
    RoboServer/RoboClient klassene kan kjøres individuelt,
    Roboclient har en sentral rolle i spillet med å utveksle infomasjon med Game-objektet som kommer
    fra server.
    Server utveksler informasjonen som clients sender til server.
    Vi tapte mye tid med å prøve å sende Serialized objekter og hadde mange problemer med at bare standardverdier for objekter ble sendt
    gjennom serveren og konkluderte med at vi kunne endten;
        bruke masse tid på å lære oss om serialization eller 
        utvikle et String-basert språk som skriver objektene til strenger, sender og konstruerer like objekter basert på Strings.
    Vi valgte den siste og derfor har nå alle objekter som skal sendes en konstrukter som tar String som argument og en toString
    metode som Skriver objektet til String.
    Vi valgte også denne vinklingen på problemet siden det er lett å legge til ny infomrasjon til språket og at koden for å parse
    språket kan skrives ganske eneklt med switch-case-r.
