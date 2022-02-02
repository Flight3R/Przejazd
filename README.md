# Symulacja Przejazdu Kolejowego

Program obrazujący działanie oraz wzajemną współpracę pojazdów oraz elementów infrastruktury kolejowej biorących udział w bezpiecznym funkcjonowaniu przejazdu kolejowego.

## Sposób działania

Program pozwala na zaobserwowanie oraz samodzielne sterowanie częścią urządzeń wchodzących w skład automatyki przejazdu kolejowego poprzez interfejs graficzny użytkownika.

### Mapa główna

Obszar symulacji zawiera 5 km odcinek dwutorowego szlaku kolejowego oraz drogę dla samochodów o długości 1 km przecinającą tory kolejowe pod kątem prostym w samym środku mapy

![środek](https://raw.githubusercontent.com/Flight3R/Przejazd/master/readme-res/środek.png)

​																													Rys. 1: Środek przejazdu.

### Pojazdy

W programie można obserwować zachowanie dwóch rodzajów pojazdów: samochodów oraz pociągów. Każdy z pojazdów poruszających się po mapie ma swoje unikalne cechy takie jak masa, przyspieszenie oraz długość.

![pociag](https://raw.githubusercontent.com/Flight3R/Przejazd/master/readme-res/pociag.png)

​																																Rys. 2: Pociąg.

![samochod](https://raw.githubusercontent.com/Flight3R/Przejazd/master/readme-res/samochod.png)

​																															Rys. 3: Samochód.

### Urządzenia kolejowe

Za bezpieczeństwo ruchu pociągów oraz samochodów przez przejazd odpowiada szereg specjalistycznych urządzeń kontroli ruchu, których działanie jest symulowane w stopniu koniecznym do zrozumienia ich zadań.

#### Czujniki

Są najmniejszym urządzeniem oraz odpowiadają za dostarczanie informacji o zajętości odcinków torowych innym urządzeniom. 

Zawsze występują w parach: 

- czujnik najazdowy - rejestruje wjazd pociągu na odcinek izolowany
- czujnik zjazdowy - informuje o opuszczeniu przez pociąg danego odcinka

#### Semafor SBL

W programie jest symulowane działanie dwustawnej samoczynnej blokady liniowej. Blokada liniowa jest elementem automatyki ruchu kolejowego zapewniającym, że w danym momencie odcinek izolowany jest zajmowany przez tylko jeden pociąg; zapobiega to najechaniu pociągów na siebie, a w ogólnym przypadku uniemożliwia wyprawienie pociągu na szlak jednotorowy  gdy jest on zajmowany przez inny pociąg.

Działanie samoczynnej blokady liniowej polega na podziale szlaku na odcinki izolowane, z których każdy jest chroniony semaforem SBL oraz parą czujników sterujących jego wskazaniami. Samnoczynność jest uzyskiwana poprzez automatyczną pracę w oparciu o stany tych czujników. Dwustawność to zdolność do wyświetlania na semaforach jednego z dwóch sygnałów: *"jedź"* (*S2*) lub *"stój"* (*S1*).

#### Tarcza SSP

Samoczynna sygnalizacja przejazdowa to system odpowiadający za sterowanie rogatkami oraz wczesne powiadamianie maszynisty w przypadku awarii i niepewności zabezpieczenia przejazdu kolejowego przed nadjechaniem pociągu. Zauważenie przez maszynistę tarczy SSP  wskazującej zagrożenie (*Osp1*) daje mu czas na zwolnienie pociągu do prędkości umożliwiającej szybkie zatrzymanie przed przejazdem w przypadku potwierdzenia jego nieprawidłowej pracy lub istniejącego zagrożenia.

![sbl-i-ssp](https://raw.githubusercontent.com/Flight3R/Przejazd/master/readme-res/sbl-i-ssp.png)

​																														Rys. 4: SBL, SSP i czujniki.

![sbl-s2](https://raw.githubusercontent.com/Flight3R/Przejazd/master/readme-res/sbl-s2.png)

​																														Rys. 5: Blokada uzbrojona.

![sbl-s1](https://raw.githubusercontent.com/Flight3R/Przejazd/master/readme-res/sbl-s1.png)

​																						Rys. 6: Blokada aktywowana (kolejny pociąg dostaje sygnał "stój").



#### Sygnalizacja przejazdowa

Za poinformowanie samochodów zbliżających się do przejazdu kolejowego o nadjeżdżającym pociągu odpowiada sygnalizacja przejazdowa. Może być ona kontrolowana automatycznie dzięki parze czujników, ale może być też przełączona w tryb sterowania ręcznego (użytkownik wciela się w dróżnika przejazdu). Samochody widząc zapaloną sygnalizację zatrzymują się przed przejazdem 

#### Rogatki przejazdu

Są stosowane do fizycznego oddzielenia samochodów od torów kolejowych. ich praca jest sprzęgnięta ze wskazaniem sygnalizacji przejazdowej.

### Panel sterowania

Umożliwia podgląd czasu, rozkładu przejazdu pociągów wraz z ich szacunkowym spóźnieniem oraz sterowaniem trybu działania sygnalizacji oraz rogatek. Wyświetlane są również ostrzeżenia mogące informować użytkownika o konieczności ręcznego zamknięcia rogatek w przypadku wyłączonego sterowania automatycznego.

​																	 ![sterowanie](https://raw.githubusercontent.com/Flight3R/Przejazd/master/readme-res/sterowanie.png)

​																									Rys. 7: Panel sterowania przejazdem.
