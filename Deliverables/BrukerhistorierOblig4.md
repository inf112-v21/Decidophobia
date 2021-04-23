# Brukerhistorier (Oblig 3)

## Spiller:
#### Brukerhistorier:
    spiller1 | Som spiller vil jeg se spillbrettet, slik at jeg kan se hvor roboten er.
    spiller2 | Som spiller vil jeg flytte roboten for å besøke flaggene som er på spillbrettet.
    spiller3 | Som spiller vil jeg vinne spillet ved å besøke flaggene i stigende rekkefølge (fra 1 t.o.m det høyeste tallet).
    spiller4 | Som spiller vil jeg spille spillet med andre spillere som bruker andre datamaskiner.
    spiller5 | Som spiller vil jeg kunne ende en runde på en skiftenøkkel-rute, slik at jeg kan fikse en skade
#### Akseptansekriterier:
    spiller1 | Spiller må kunne se brettet og se roboten sin på spillbrettet ved å ha en GUI som viser spillbrettet og roboten på skjermen.
    spiller2 | Spilleren skal kunne flytte på sin robot til hvert av flaggene som er på spillbrettet.
    spiller3 | Spiller skal kunne vinne spillet ved å ha vært på hver tile hvor flaggene er plassert, så lenge  gjør det i stigende rekkefølge.
    spiller4 | Spilleren skal kunne velge en flerspiller-modus og kunne spille med andre spillere som bruker andre datamaskiner.
    spiller5 | Det skal være mulig for en spiller å reparere roboten sin ved å ende runden på en skiftenøkkel-rute.
#### Arbeidsoppgaver:
    spiller1 | Implementere en Graphical User Interface(GUI) for spillbrettet.
    spiller2 | Spillerklassen som skal lagre hvilke flag som spiller har aktivert, og hvilke flagg spiller skal begynne på 
               når roboten knuser.
    spiller3 | Lage en funksjon som sjekker om spiller har besøkt alle flaggene på brettet i stigende rekkefølge, 
               og resultere i at spiller vinner dersom det er tilfelle.
    spiller4 | Implementere flerspillermodus gjennom Kryonet API.
    spiller5 | Gjøre det slik at roboter som ender runden sin på en skiftenøkkel-rute blir reparert før neste runde.

## Brett:
#### Brukerhistorier:
    brett1 | Som spiller vil jeg kunne gå utenfor brettet, slik at jeg kan dø.
    brett2 | Som brett ønsker jeg at jeg kan bli lagd av spillere, slik at spilleren får en variert spillopplevelse.
    brett3 | Som brett ønsker jeg at jeg kan bli plasert objekter på, slik at spilleren kan spille på meg.
#### Akseptansekriterier:
    brett1 | Spilleren sin robot skal knuse når den går utenfor brettet.
    brett2 | Spiller skal kunne lage sine egne brett, som vil si at spiller skal kunne velge størrelse og 
             hvilke objekt som går hvor.
    brett3 | Et brett skal kunne ha forskellige objekt plassert på seg, F.eks vegger, laser og flagg.
#### Arbeidsoppgaver:
    brett1 | Gjøre det slik at brettklassen skal drepe spiller hvis spiller går utenfor brettet.
    brett2 | Lage en MapEditor der spiller kan lage og forandre på brett.
    brett3 | Lage et brett som kan inneholde flere forskjellige objekter.

## Vegg:
#### Brukerhistorier:
    vegg1 | Som vegg ønsker jeg at spiller kan se veggen, slik at spiller kan unngå å bli stoppet.
    vegg2 | Som vegg ønsker jeg at det ikke er fire vegger i en rute, slik at ruten er mulig å besøke.
    vegg3 | Som vegg ønsker jeg å stoppe laser, slit at spiller ikke blir skadet bak meg.
#### Akseptansekriterier:
    vegg1 | Spiller skal kunne se veggene på spillbrettet.
    vegg2 | Det skal aldri være vegger på alle sider av en rute.
    vegg3 | Vegger skal stoppe lasere og lasere skal ikke skade roboter som står bak veggen som stopper laseren.
#### Arbeidsoppgaver:
    vegg1 | Passe på at spillere kan se veggene på spillbrettet.
    vegg2 | Lage en skjekk som gjør det umulig å ha vegger på alle kantene av en rute.
    vegg3 | Gjøre det slik at vegger stopper lasere og ikke skader roboter som står bak vegger som stopper lasere.

## Flagg:
#### Brukerhistorier:
    flagg1 | Som flagg ønsker jeg å være synlig for spilleren, slik at spiller vet hvor man skal gå.
    flagg2 | Som flagg ønsker jeg at spilleren vinner dersom spilleren har besøkt alle flaggene i riktig rekkefølge.
    flagg3 | Som flagg ønsker jeg at spilleren følger meg i rekkefølge, slik at spilleren ikke ta korteste veien til 
             hvert flagg.
    flagg4 | Som flagg ønsker jeg at spilleren skal starte hos meg dersom han dør, slik at spilleren ikke starter
             fra starten hver gang.
#### Akseptansekriterier:
    flagg1 | Spiller må kunne se flagg.
    flagg2 | Spiller må kunne vinne spillet med å gå igjennom alle flaggene.
    flagg3 | Spiller kan kun aktivere flagg i en spesiell rekkefølge.
    flagg4 | Når spilleren dør vil spiller starte på det siste flagget den spilleren har aktivert.
#### Arbeidsoppgaver:
    flagg1 | Lage en grafisk representasjon av flagg.
    flagg2 | Lage en funksjon som skjekker om spilleren har besøkt alle flagg i riktig rekkefølge, og at spiller vinner 
             dersom dette er oppfylt.
    flagg3 | Gjøre det slik at det er tydlig for spiller hvilke rekkefølge flaggene skal besøkes gjennom å inkludere et tall 
             som en del av den grafiske representasjonen av flagget.
    flagg4 | Gjøre det slik at spillere starter på det siste aktiverte flagget sitt når de dør.

## Kort:
#### Brukerhistorier:
    kort1 | Som kort ønsker jeg at spiller kan velge ut fra kortene sine hvilke veg det ønskes å gå, 
            slik at spiller ikke bestemmer med piltastene.
    kort2 | Som kort ønsker jeg at kortene kan variere mellom bevegelse (frem eller tilbake) og rotasjon,
            slik at spilleren kan snu.
    kort3 | Som kort ønsker jeg å eksistere i mange forskjellige varianter, slik at spilleren 
            får velge hvilke veg han får gå.
#### Akseptansekriterier:
    kort1 | Spiller skal kunne velge hvilke veg å gå ved bruk av kortene sine, og skal ikke kunne bestemme med piltastene.
    kort2 | Det skal finnes en tilstrekkelig mengde forskjellige kort som gjør det mulig at roboten kan bevege seg 
            frem eller tilbake, eller rotere til høyre eller venstre. 
    kort3 | Spiller skal ha mulighet til å ha tilgang til flere forskjellige kort.
#### Arbeidsoppgaver:
    kort1 | Gjøre det slik at spillere kan velge hvilken veg å gå ved bruk av kortene spilleren har, og skal ikke
            kunne flytte på roboten sin med piltastene.
    kort2 & kort3 | Inkludere kort i spillet som har som funksjon å flytte spillerens robot frem eller tilbake, eller 
            rotere spillerens robot til høyre eller venstre.

## Laser:
#### Brukerhistorier:
    laser1 | Som laser ønsker jeg å kunne skade spilleren, slik at spilleren kan dø.
    laser2 | Som laser ønsker jeg at jeg kun kan skyte laser i en retning, slik at spilleren kan unngå meg.
    laser3 | Som laser ønsker jeg at en laserstråle skal gjøre 1 skade, slik at spilleren ikke dør med en gang.
    laser4 | Som laser ønsker jeg at jeg kan skyte 1 eller 3 laserstråler, slik at spilleren kan ha variasjon.
#### Akseptansekriterier:
    laser1 | Lasere kan drepe roboter ved å skyte laser på dem.
    laser2 | Lasere kan ikke skytes i flere en én retning.
    laser3 | Lasere som skyter én laserstråle kan ikke gjøre mer en 1 skade på en robot.
    laser4 | Lasere skal skyte enten 1 eller 3 laserstråler.
#### Arbeidsoppgaver:
    laser1 | Gjøre det slik at lasere skader spillere som står fremfor laseren.
    laser2 | Gjøre det slik at lasere ikke kan skytes i flere en én retning.
    laser3 | Gjøre det slik at én laser gjør 1 skade på en robot.
    laser4 | Gjøre det slik at lasere kan eksistere i to varianter, en som skyter 1 stråle og én som skyter 3 stråler.

## Hull:
#### Brukerhistorier:
    hull1 | Som hull ønsker jeg å være synlig for spillere, slik at de kan unngå meg.
    hull2 | Som hull ønsker jeg at spillere som går oppå meg dør.
#### Akseptansekriterier:
    hull1 | Hull skal være synlig på spillbrettet for alle spillere.
    hull2 | Spillere som går på tiles med hull skal dø.
#### Arbeidsoppgaver:
    hull1 | Passe på at alle hull på brettet er synlige for alle spillere.
    hull2 | Gjøre det slik at hull dreper roboter som går oppå dem.

## Transportbånd:
#### Brukerhistorier:
    transportbånd1 | Som transportbånd ønsker jeg å bevege på spiller, slik at spiller mister kontroll.
    transportbånd2 | Som transportbånd ønsker jeg å vite hvor mange piler jeg har, slik at jeg vet hvor mange ganger jeg skal flytte roboter.
#### Akseptansekriterier:
    transportbånd1 | Når transportbåndet beveger på seg skal en eventuell spiller som står på båndet også flytte på seg.
    transportbånd2 | Transportbåndet skal bevege på seg i henhold til hvor mange piler den har på seg (1 pil = 1 tile, 2 piler = 2 tiles).
#### Arbeidsoppgaver:
    transportbånd1 | Gjøre det slik at roboter som står oppå transportbånd også flytter der transportbåndet flytter seg.
    transportbånd2 | Gjøre det slik at roboter flytter på seg i henhold til hvor mange piler transportbåndet viser.

## Dytter:
#### Brukerhistorier:
    dytter1 | Som dytter ønsker jeg at roboten kan stoppe foran meg, slik at jeg kan dytte den vekk.
    dytter2 | Som dytter ønsker jeg at dersom en robot står oppå meg på slutten av runden, vil roboten bli dyttet
    dytter3 | Som dytter ønsker jeg at roboter som blir dyttet blir dyttet i den retningen dytteren ser mot.
#### Akseptansekriterier:
    dytter1 | Roboter skal kunne gå på tiles med dyttere.
    dytter2 | Roboter som står på tiles med dyttere skal dyttes.
    dytter3 | Roboter som blir dyttet skal bli dyttet i den retningen dytteren ser mot.
#### Arbeidsoppgaver:
    dytter1 | Passe på at tiles med dyttere er en del av det traverserbare brettet.
    dytter2 | Gjøre det slik at roboter som ender runder på en rute med en dytter, blir dyttet.
    dytter3 | Gjøre det slik at roboter som blir dyttet blir dyttet i den retningen dytter skal dytte.

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
    
    server1 & server4 | Skrive en håndtering og respons av "join"-request.
    server2 | Skrive en håndtering og respons av "lockCards"-request.
    server3 | Slrive en håndtering og respons av "quit"-request.

## GUI
#### Brukerhistorier
    MenuScreen | Som MenuScreen vil jeg vise brukeren måter å gå ut av spillet på, slik at brukeren kan avslutte spillet.
    MenuScreen | Som MenuScreen vil jeg at brukere skal kunne trykke seg videre til JoinScreen.
    MenuScreen | Som MenuScreen vil jeg vise forsiden til RoboRally til brukeren slik at han vet Hvilket spill han spiller.

    LobbyScreen | Som lobbyScreen vil jeg vise alle spillere som er koblet til Server slik at de kan bli med i spillet.
    LobbyScreen | Som lobbyScreen vil jeg endre navn til en spiller for alle spillere, når spilleren endrer navnet sitt i meg.
    
