# Brukerhistorier (Oblig 1)
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
    
