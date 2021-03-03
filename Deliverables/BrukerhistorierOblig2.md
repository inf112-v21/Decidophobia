# Brukerhistorier (Oblig 1)

## Spiller:
####Brukerhistorier:
    a1 | Som spiller vil jeg se spillbrettet, slik at jeg kan se hvor roboten er.
    a2 | Som spiller vil jeg bruke piltastene for å flytte roboten rundt på spillbrettet.
    a3 | Som spiller vil jeg flytte roboten for å besøke flaggene som er på spillbrettet.
    a4 | Som spiller vil jeg vinne spillet ved å besøke flaggene i stigende rekkefølge (fra 1 t.o.m det høyeste tallet).
    a5 | Som spiller vil jeg spille spillet med andre spillere som bruker andre datamaskiner.

#### Akseptansekriterier:
    a1 | Spiller må kunne se brettet og se roboten sin på spillbrettet ved å ha en GUI som viser spillbrettet og roboten på skjermen.
    a2 | Spiller skal kunne flytte på roboten sin rundt på spillbrettet ved bruk av tastaturets piltaster.
    a3 | Spilleren skal kunne flytte på sin robot til hvert av flaggene som er på spillbrettet.
    a4 | Spiller skal kunne vinne spillet ved å ha vært på hver tile hvor flaggene er plassert, så lenge  gjør det i stigende rekkefølge.
    a5 | Spilleren skal kunne velge en flerspiller-modus og kunne spille med andre spillere som bruker andre datamaskiner.
#### Arbeidsoppgaver:
    a1 | Implementere en Graphical User Interface(GUI) for spillbrettet.
    a2 |
    a3 | Spillerklassen som skal lagre hvilke flag som spiller har aktivert, og hvilke flagg spiller skal begynne på 
         når roboten knuser.
    a4 |
    a5 | Implementere flerspillermodus gjennom Kryonet API.

## Brett:
####Brukerhistorier:
    b1 | Som spiller vil jeg kunne gå utenfor brettet, slik at jeg kan dø.
    b2 | Som brett ønsker jeg at jeg kan bli lagd av spillere, slik at spilleren får en variert spillopplevelse.
    b3 | Som brett ønsker jeg at jeg kan bli plasert objekter på, slik at spilleren kan spille på meg.
#### Akseptansekriterier:
    b1 | Spilleren sin robot skal knuse når den går utenfor brettet.
    b2 | Spiller skal kunne lage sine egne brett, som vil si at spiller skal kunne velge størrelse og 
         hvilke objekt som går hvor.
    b3 | Et brett skal kunne ha forskellige objekt plassert på seg, F.eks vegger, laser og flagg.
#### Arbeidsoppgaver:
    b1 | Gjøre det slik at brettklassen skal drepe spiller hvis spiller går utenfor brettet.
    b2 | Lage en MapEditor der spiller kan lage og forandre på brett.
    b3 | Lage et brett som kan inneholde flere forskjellige objekter.

## Flagg:
####Brukerhistorier:
    c1 | Som flagg ønsker jeg å være synlig for spilleren.
    c2 | Som flagg ønsker jeg at spilleren vinner dersom spilleren har besøkt alle flaggene i riktig rekkefølge.
    c3 | Som flagg ønsker jeg at spilleren følger meg i rekkefølge, slik at spilleren ikke ta korteste veien til 
         hvert flagg.
    c4 | Som flagg ønsker jeg at spilleren skal starte hos meg dersom han dør, slik at spilleren ikke starter
         fra starten hver gang.
#### Akseptansekriterier:
    c1 | Spiller må kunne se flagg.
    c2 | Spiller må kunne vinne spillet med å gå igjennom alle flaggene.
    c3 | Spiller kan kun aktivere flagg i en spesiell rekkefølge.
    ?? | Når spiller har aktivert et flagg vil spiller begynne på dette flaget.
#### Arbeidsoppgaver:
    c1 | Lage en grafisk representasjon av flagg.
    c2 | Lage en funksjon som skjekker om spilleren har besøkt alle flagg i riktig rekkefølge, og at spiller vinner 
         dersom dette er oppfylt.
    c3 | 

## Kort:
####Brukerhistorier:
    d1 | Som spiller ønsker jeg at jeg kan velge ut fra kortene mine hvilken veg jeg skal gå, slik at spilleren ikke bestemmer med tastene.
    d2 | Som kort ønsker jeg at kortene kan variere mellom bevegelse (frem eller tilbake) og rotasjon, slik at spilleren kan snu.
    d3 | Som kort ønsker jeg å eksistere i mange forskjellige varianter, slik at spilleren får velge hvilke veg han får gå.
#### Akseptansekriterier:
#### Arbeidsoppgaver:

## Laser:
####Brukerhistorier:
    e1 | Som spiller ønsker jeg at jeg kan ta skade fra laser, slik at jeg kan dø.
    e2 | Som laser ønsker jeg at jeg kun kan skyte laser en retning, slik at spilleren kan unngå meg.
    e3 | Som laser ønsker jeg at en laserstråle skal gjøre 1 skade, slik at spilleren ikke dør med en gang.
    e4 | Som laser ønsker jeg at jeg kan skyte 1 eller 3 laserstråler, slik at spilleren kan ha variasjon.
#### Akseptansekriterier:
    e1 |
    e2 | Lasere kan ikke skytes i flere en én retning.
    e3 | Lasere som skyter én laserstråle kan ikke gjøre mer en 1 skade på en robot.
    e4 | Lasere skal skyte enten 1 eller 3 laserstråler.
#### Arbeidsoppgaver:
    e1 |
    e2 |
    e3 |
    e4 |

## Hull:
####Brukerhistorier:
    f1 | Som robot vil jeg at jeg forsvinner når jeg går på et hull, slik at jeg kan dø.
    f2 | Som hull vil jeg at spillere som går oppå meg dør.
#### Akseptansekriterier:
    f1 | 
    f2 | Spillere som går på tiles med hull skal dø.
#### Arbeidsoppgaver:
    f1 |
    f2 | Gjøre det slik at hull dreper roboter.

## Transportbånd:
####Brukerhistorier:
    g1 | Som transportbånd ønsker jeg å bevege på spiller, slik at spiller mister kontroll.
    g2 | Som transportbånd ønsker jeg å vite hvor mange piler jeg har, slik at jeg vet hvor mange ganger jeg skal flytte roboter.
#### Akseptansekriterier:
    g1 | Når transportbåndet beveger på seg skal en eventuell spiller som står på båndet også flytte på seg.
    g2 | Transportbåndet skal bevege på seg i henhold til hvor mange piler den har på seg (1 pil = 1 tile, 2 piler = 2 tiles).
#### Arbeidsoppgaver:
    g1 |
    g2 |
## Dytter:
####Brukerhistorier:
    h1 | Som dytter ønsker jeg at roboten kan stoppe foran meg, slik at jeg kan dytte den vekk.
    h2 | Som dytter ønsker jeg at dersom en robot står oppå meg på slutten av runden, vil roboten bli dyttet
    h3 | Som dytter ønsker jeg at roboter som blir dyttet blir dyttet i den retningen dytteren ser mot.
#### Akseptansekriterier:
    h1 | Roboter skal kunne gå på tiles med dyttere.
    h2 | Roboter som står på tiles med dyttere skal dyttes.
    h3 | Roboter som blir dyttet skal bli dyttet i den retningen dytteren ser mot.
#### Arbeidsoppgaver:
    h1 | Passe på at tiles med dyttere er en del av det traverserbare brettet.
    h2 | 
## Client/Sever:
#### Brukerhistorier:
    client1 | Som client vil jeg at jeg skal kunne bli med i spillet.
    client2 | Som client vil jeg levere kortene mine til server.
    client3 | Som client vil jeg kunne forlate spillet.
    client4 | Som client vil jeg ikke kunne bli med i påbegynte spill.'

    server1 | Som server vil jeg kunne legge til clients i spillet.
    server2 | Som server vil jeg få kort av clienter og sende de videre til alle andre clienter.
    server3 | Som server vil jeg kunne fjerne spillere fra spillet.
    server4 | Som server ignorerer jeg clienter som vil være med i et påbegynt spill.

#### Akseptansekriterier
    client1 | Dersom client vil bli med i spill, blir han lagt til.
    client2 | Dersom client leverer kortene sine blir disse brukt i alle clienters spill for denne clienten.
    client3 | Dersom client vil forlate spill, så forsvinner spillerens robot fra alles brett.
    client4 | Dersom spill er i gang, så får ikke ny client være med.

    server1 | Dersom server får melding fra client om å bli med, legger server clienten til i spill.
    server2 | Dersom server får kort fra client, må de samme kortene bli sendt til alle clienter.
    server3 | Dersom server får melding fra client om å forlate spill, så påvirker ikke client lenger spillet.
    server4 | Dersom spill er begynt, tar ikke server inn flere clienter i spillet.
    
#### Arbeidsoppgaver
    client1 | Skrive "join" request fra client til server
    client2 | Skrive "lockCards" request fra client til server, som leverer kort fra client.
    client3 | Skrive "quit" request fra client til server.
    client4 | Skrive en håndtering av "gameFull"- eller "gameStarted"-respons.
    
    server1 server 4 | Skrive en håndtering og respons av "join"-request.
    server2 | Skrive en håndtering og respons av "lockCards"-request.
    server3 | Slrive en håndtering og respons av "quit"-request.