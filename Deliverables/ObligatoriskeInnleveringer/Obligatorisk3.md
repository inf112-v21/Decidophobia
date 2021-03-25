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

__Trenger å oppdatere hvordan roller har fungert__  
Rollene i teamet har fungert godt. Samtlige deltakere har tatt sine ansvarsområder
på alvor og viser en god forståelse for sine oppgaver. <br> Kodetester har ansvar for å lage gode tester til koden som blir skrevet. Denne rollen har blitt besatt av Alexander som har løst den på en god måte. 
Vi ser på kodetesting som en viktig del av 
prosjektet, for å kunne verifisere at ting fungerer som det skal. <br> Rollen som kundekontakt ble gitt til Oscar. Han har altså
hatt ansvaret for å kontakte kunden med ulike problemer eller spørsmål vi har hatt til utviklingen av prosjektet. Rollen i seg selv
er en spesielt viktig rolle for å kunne sikre at prosjektet står i tråd til kundens forventninger, men vi har så langt i prosjektet følt at vi har hatt
en god fremgang og enda ikke hatt store behov for å konsultere med kunde. <br> 
Isak har som Tech-lead hatt ansvar for selve kodingen i prosjektet, og for å ha et overordnet ansvar over fremgangen. Denne oppgaven er også spesielt viktig, ettersom
det er kritisk at minst én person har en gjennomgående forståelse for de ulike oppgavene som til enhvertid arbeides med. Tech-lead kan også med et godt overordnet ansvar
distribuere arbeidsoppgaver jevnt i teamet, og er best egnet til å kunne fordele disse på en god måte. <br>
Ansvaret for UI-design har vært hos Lars Andre. UI-designer skal være den som er ansvarlig for utformingen av interfacet som kunden etter leveransen skal benytte seg av. Ettersom
det er først og fremst UI-designeren sitt arbeid kunden først og fremst skal benytte seg av, så er det viktig at dette gjøres på en intuitiv og brukervennlig måte. <br>
Som sekretær har Petter hatt ansvar for deliverables. Denne rollen har vært viktig for å være sikre på at en person alltid tar gode notater som er lettlesbare og tilgjengelige for gruppen. Disse må også ha en god formulering ettersom de skal leveres og leses av TA.
<br>
<br>
#### Gruppedynamikk og kommunikasjon
Generelt synes teamet det er synd å ikke kunne jobbe fysisk i felleskap til samme grad som ellers. Vi føler likevel vi har løst dette på en god måte ved at alle i teamet har kontinuerlig
vært tilgjengelige på discord. Vi har også ført alle møter på discord, og har gjennomført disse raskt og effektivt. Teamet har en god tone sammen og samtlige føler vi faktisk er ett godt samarbeidende team hvor ingen har blitt utelatt
i hverken samtaler eller avgjørelser. Dette har vært viktig for oss fra starten av.
<br>
<br>
#### Retroperspektiv

Når det gjelder hver enkelts deltakelse i kodingen vil dette variere noe fra person til person. Det er naturlig at tech-lead vil bidra med mer kode enn UI-designer eller sekretær. Vi forsøker likevel ha en jevn fordeling av koding i teamet
slik at alle får bidra, men lar hver enkelt få styre sin egen deltakelse etter ønske og evne. 

### Deloppgave 2: Krav
- Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang. Er dere kommet forbi MVP? Forklar hvordan dere prioriterer ny funksjonalitet.
- For hvert krav dere jobber med, må dere lage 1) ordentlige brukerhistorier, 2) akseptansekriterier og 3) arbeidsoppgaver. Husk at akseptansekriterier ofte skrives mer eller mindre som tester
- Dersom dere har oppgaver som dere skal til å starte med, hvor dere har oversikt over både brukerhistorie, akseptansekriterier og arbeidsoppgaver, kan dere ta med disse i innleveringen også.
- Husk å skrive hvilke bugs som finnes i de kravene dere har utført (dersom det finnes bugs).
- Kravlista er lang, men det er ikke nødvendig å levere på alle kravene hvis det ikke er realistisk. Det er viktigere at de oppgavene som er utført holder høy kvalitet. Utførte oppgaver skal være ferdige.

(gammel) Vi prioriterte å få til multiplayer som gjorde at vi ikke klarte å få ferdig MVP. Vi hadde krav 1-5 klar i Oblig1. Vi fikk problemer med å forstå KryoNet som gjorde at vi ikke bli ferdig med de neste kravene. 

Bugs ligger i README.md

### Deloppgave 3: 
#### Spillet
    Spillet startes ved å kjøre launcher.class,
    Denne delen av spillet er foreløpig bare for
    å manuelt kunne teste hvordan en robot kan
    bevege seg, hvordan UI ser ut.

#### Online - RoboServer & RoboClient
    RoboServer/RoboClient klassene kan kjøres individuelt,
    Man kan da kjøre først RoboServer for at server skal gå.
    Helt nederst i RoboClient klassen kan man skrive inn input
    som:
        "Join" -> For å bli med i spill
            Man får tilbake "Joined", "AlreadyJoined" eller 
            "Rejected" ettersom om man er med i spillet fra før
            eller om spillet allerede er startet.
        Kan også sende et MoveCardPacket-objekt som er et objekt 
        som sier hvilke kort som er lagt og hvilken spiller som
        har lagt det.

    Man kan teste LAN uten problem, men for å spille online må
    man åpne porter for RoboServer (TCP:54555, UDP:54777).
    Når ServerTest kjøres kan man ikke kjøre andre klass, som
    spesielt RoboServer.

#### Gang i spillet
##### Nå
    Gangen i spillet er slik:
    Launcher setter i gang Game som styrer spillet
    Game starter GUI som viser brett og tar input som piltaster og museklikk
    Player beveger seg etter piltaster og GUI blir oppdatert av det
    GUI tar seg av kollisjon med f.eks hull eller flagg.
    Server blir kjørt paralelt hos hosten.
    Hosten er også en client, slik at all informasjon som skal til alle blir 
        sendt til alle og info som skal bare til den enkelte blir sendt til den enkelte.

##### Planen videre for gang i spill
    Gangen i spillet blir slik:
    Launcher setter i gang Game som styrer spillet
    Game starter GUI som viser brett og tar input som piltaster og museklikk
    Bruker velger kort med piltaster (evt. museklikk senere) som GUI fanger 
        opp, konverterer til kort og sender det til server via Game.
    Game tar seg av kollisjon med f.eks hull eller flagg.
    Game venter også på kort fra alle spillere fra server før den starter en runde.
    Server blir kjørt paralelt hos hosten.
    Hosten er også en client, slik at all informasjon som skal til alle blir 
        sendt til alle og info som skal bare til den enkelte blir sendt til den enkelte.
