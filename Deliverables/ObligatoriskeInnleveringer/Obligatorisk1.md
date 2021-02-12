# Obligatorisk innlevering 1

## Deloppgave 1

### Roller og forventninger til hver:

Alle skal bidra med kode i prosjektet uavhengig av rolle.

**Teamlead**: Hovedansvar for utvikling og distribusjon av arbeidsoppgaver i forbindelse med dette.

**Kundekontakt**: Hovedansvar for kontakt med ‘kunde’. Har ansvar for at leveranse samsvarer med kundens forventning.

**Kodetester**: Ansvar for å avdekke feil og mangler i koden.

**Sekretær**: Ansvar for møter

**UI-Designer**: Ansvaret for design av spillet

### Kompetanse og roller i teamet

Alexander Gullestad: </br>
**Kompetanse**: Har hatt INF100, INF101 og INF102</br>
**Rolle**: Kodetester

Isak Hølleland: </br>
**Kompetanse**: Har hatt INF100, INF101, INF102 og INF122 </br>
**Rolle**: Teamlead

Lars Andre Sande: </br>
**Kompetanse**: Har hatt INF100, INF101 og INF102 </br>
**Rolle**: UI-design

Oscar Strømsli: </br>
**Kompetanse**: Har hatt INF100, INF101, INF102, INF122 </br>
**Rolle**: Kundekontakt

Petter Paulsen: </br>
**Kompetanse**: Har hatt INF100, INF102, og INF122 </br>
**Rolle**: Sekretær 

## Deloppgave 2
Vi har valgt å følge XP som prosjektmetodikk. Den første tiden av
Vi har tildelt klare ansvarsoppgaver og bestemt faste tidspunkter for team-møter. 
I de fast oppsatte møtene vil alle deltakere stille. Dette er som følge av at 
dette er en skoleoppgave der alle deltakere ønsker ett innblikk i prosessen.
Ved behov vil deltakere sette opp møter seg i mellom. </br>
</br>
I den første tiden har målet vært å skape en god forståelse for oppgaven. 
Vi har brukt tid på å sette alle deltakere inn i målet for leveransen og har 
etablert rutiner for arbeidsfordeling. Tech lead vil ha hovedansvar for fordeling
av arbeidsoppgaver relatert til programmering, mens kundekontakt vil ha ansvar for
at hver leveranse samsvarer med kundens forventning. Oppfølging av utført arbeid
vil deretter kontrolleres av tester, som har ansvar for å følge opp at feil i koden blir 
rettet.

## Deloppgave 3
### RoboRally Applikasjonen  <br>
RoboRally er et brettspill og målet med denne applikasjonen  <br>
er å være en digital versjon av dette spillet som kan spilles  <br>
med andre digitalt.  <br>
Spillet handler om roboter som skal nå ett eller flere  <br>
punkter på et spillbrett, spillere kan flytte sin robot  <br>
med bevegelseskort der han må planlegge 5 kort om gangen.  <br>

# Brukerhistorier 
## Spiller:
- Som spiller vil jeg se spillbrettet, slik at jeg kan se hvor roboten er.
- Som spiller vil jeg bruke piltastene for å flytte roboten rundt på spillbrettet.
- Som spiller vil jeg flytte roboten for å besøke flaggene som er på spillbrettet.
- Som spiller vil jeg vinne spillet ved å besøke flaggene i stigende rekkefølge (fra 1 til det høyeste tallet).
- Som spiller vil jeg spille spillet med andre spillere som bruker andre maskiner.

#### Akseptansekriterier:
    - Spilleren skal kunne flytte på sin robot til hvert av flaggene som er på spillbrettet. 
    - Spiller skal kunne vinne spillet ved å ha vært på hver tile hvor flaggene er plassert, så lenge spilleren gjør det i stigende rekkefølge.  
    - Spiller skal kunne flytte på roboten sin rundt på spillbrettet ved bruk av tastaturets piltaster.
    - Spiller må kunne se brettet og se roboten sin på spillbrettet ved å ha en GUI som viser spillbrettet og roboten på skjermen.
#### Arbeidsoppgaver:
    - En Graphical User Interface(GUI) for spillbrettet.
    - Spillerklassen som skal lagre hvilke flag som spiller har aktivert, og hvilke flagg spiller skal begynne på når roboten knuser
## Brett:
- Som spiller vil jeg kunne gå utenfor brettet, slik at jeg kan dø.
- Som brett ønsker jeg at jeg kan bli lagd av spelere, slik at spilleren får variasjon.
- Som brett ønsker jeg at jeg kan bli plasert objekter på, slik at spilleren kan spille på meg.
#### Akseptansekriterier:
    - Spiller skal kunne lage sine egne brett, som vil si at spiller skal kunne velge størrelse og hvilke objekt som går hvor
    - Et brett skal kunne ha forskellige objekt plassert på seg, F.eks vegger, laser og flagg
    - Spillerenn sin robot skal knuse når den går utenfor brettet
#### Arbeidsoppgaver:
    - En Graphival User Interface (GUI) for brett
    - Lage et brett som kan inneholde flere forskjellige objekter
    - En MapEditor der spiller kan lage og forandre på brett
    - Brettklassen skal drepe spiller hvis spiller går utenfor brettet
## Flagg:
- Som spiller ønsker jeg å gå i flagg, slik at jeg kan vinne spillet.
- Som flagg ønsker jeg at spilleren følger meg i rekkefølge, slik at spilleren ikke ta korteste veien til hvert flagg.
- Som flagg ønsker jeg at spilleren skal starte hos meg dersom han dør, slik at spilleren ikke starter fra starten hver gang.
#### Akseptansekriterier:
    - Spiller må kunne se flagg.
    - Spiller må kunne vinne spillet med å gå igjennom alle flaggene
    - Spiller kan kun aktivere flagg i en spesiell rekkefølge
    - Når spiller har aktivert et flagg vil spiller begynne på dette flaget
#### Arbeidsoppgaver:
    - En Graphival User Interface (GUI) for flagg
    
