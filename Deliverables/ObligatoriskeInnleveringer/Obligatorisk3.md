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

#### Gruppedynamikk og kommunikasjon

__(gammel)__ Generelt synes teamet det er synd å ikke kunne jobbe fysisk i felleskap til samme grad som ellers. Vi føler likevel vi har løst dette på en god måte ved at alle i teamet har kontinuerlig
vært tilgjengelige på discord. Vi har også ført alle møter på discord, og har gjennomført disse raskt og effektivt. Teamet har en god tone sammen og samtlige føler vi faktisk er ett godt samarbeidende team hvor ingen har blitt utelatt
i hverken samtaler eller avgjørelser. Dette har vært viktig for oss fra starten av.

Det som har vært nytt for oss i Gruppedynamikken er at vi har prøvd å bruke mer parprogrammering og føler det har fungert svært godt hos Server/Client-paret.
Det ble lettere å oppdage feil når vi kodet ilag, i tillegg "tvang" det oss til å forklare koden mer. Vi brukte også mye tid på å diskutere hva som var beste
løsning og implementerte letter ting som passet ilag (når vi programmerte forskjellige ting).

Vi har også hatt et projectBoard i Git der vi publiserte oppgaver, men dette har vært lite brukt og vi har i stede blitt enige om oppgavene vi skal gjøre muntlig på
gruppetimer.

#### Retroperspektiv
- Er det noen erfaringer enten team-messig eller mtp prosjektmetodikk som er verdt å nevne? Synes
teamet at de valgene dere har tatt er gode? Hvis ikke, hva kan dere gjøre annerledes for å forbedre
måten teamet fungerer på?
- Bli enige om maks tre forbedringspunkter fra retrospektivet, som skal følges opp under neste sprint
- Forklar kort hvordan dere har prioritert oppgavene fremover. Legg ved skjermdump av project board
ved innlevering

__(gammel)__ Når det gjelder hver enkelts deltakelse i kodingen vil dette variere noe fra person til person. Det er naturlig at tech-lead vil bidra med mer kode enn UI-designer eller sekretær. Vi forsøker likevel ha en jevn fordeling av koding i teamet
slik at alle får bidra, men lar hver enkelt få styre sin egen deltakelse etter ønske og evne. 

### Deloppgave 2: Krav
- Oppdater hvilke krav dere har prioritert, hvor langt dere har kommet og hva dere har gjort siden forrige gang. Er dere kommet forbi MVP? Forklar hvordan dere prioriterer ny funksjonalitet.
- For hvert krav dere jobber med, må dere lage 1) ordentlige brukerhistorier, 2) akseptansekriterier og 3) arbeidsoppgaver. Husk at akseptansekriterier ofte skrives mer eller mindre som tester
- Dersom dere har oppgaver som dere skal til å starte med, hvor dere har oversikt over både brukerhistorie, akseptansekriterier og arbeidsoppgaver, kan dere ta med disse i innleveringen også.
- Husk å skrive hvilke bugs som finnes i de kravene dere har utført (dersom det finnes bugs).
- Kravlista er lang, men det er ikke nødvendig å levere på alle kravene hvis det ikke er realistisk. Det er viktigere at de oppgavene som er utført holder høy kvalitet. Utførte oppgaver skal være ferdige.

__(gammel)__ Vi prioriterte å få til multiplayer som gjorde at vi ikke klarte å få ferdig MVP. Vi hadde krav 1-5 klar i Oblig1. Vi fikk problemer med å forstå KryoNet som gjorde at vi ikke bli ferdig med de neste kravene. 

Vårt mål for Oblig3 var å få til mv og levere et spill som kan spilles over nettet. Vi prioriterte multiplayer og GUI fordi vi er usikre på hvordan det virker og hvordan vi kan implementere de for prosjektet. Vi har klart å få   
server og client oppe å gå sånn at det er mulig å sende informasjon over nettet. Forklaring av multiplayer finnes under Online - RoboServer & RoboClient. __Trenger informasjon om fremgang med GUI her__ 

Brukerhistorier finner du i BrukerHistorierOblig3.md

Bugs ligger i README.md

### Deloppgave 3: Produktleveranse og kodekvalitet
- Dere må dokumentere hvordan prosjektet bygger, testes og kjøres, slik at det er lett for gruppelderne å bygge, teste og kjøre koden deres. Under vurdering kommer koden også til å brukertestes.
- Kodekvalitet og testdekning vektlegges. Merk at testene dere skriver skal brukes i produktet. Det kan være smart å skrive manuelle tester for å teste det som er grafisk.
- Utførte oppgaver skal være ferdige. __Kjønner ikke helt hvilke oppgaver det er snakk om__
- Hvis dere tester manuelt: lever beskrivelser av hvordan testen foregår, slik at gruppeleder kan utføre testen selv.
- Under vurdering vil det vektlegges at alle bidrar til kodebasen. Hvis det er stor forskjell i hvem som committer, må dere legge ved en kort forklaring for hvorfor det er sånn. Husk å committe alt. (Også designfiler)

Manuelle tester ligger i ManuelTests.md
#### Spillet
    Spillet startes ved å kjøre launcher.class,
    Denne delen av spillet er foreløpig bare for
    å manuelt kunne teste hvordan en robot kan
    bevege seg, hvordan UI ser ut.

#### Online - RoboServer & RoboClient
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
