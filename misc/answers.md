## Vilka avvikelser från MVC-idealet kan ni identifiera i det ursprungliga användargränssnittet? Vad borde ha gjorts smartare, dummare eller tunnare?
- Controller är beroende av View
- View är beroende av Controller
- Timerlistener bör vara i Model
- Knappar som har med user input bör vara i Controller

### Vilka av dessa brister åtgärdade ni med er nya design från del 2A? Hur då? Vilka brister åtgärdade ni inte?
- Vi åtgärde att Controller var beroende av View genom ett observer pattern där View observerar model.
- Vi årgärde att View var beroende av Controller genom att lägga till actionlisteners i en App klass.

## Observer, Factory Method, State, Composite. För vart och ett av dessa fyra designmönster, svara pa följande frågor:

### Finns det något ställe i er design dar ni redan använder detta pattern, avsiktligt eller oavsiktligt? Vilka designproblem löste ni genom att använda det?
- Vi använder Observer Pattern för att lösa att Controller var beroende av View för att kalla "repaint" metoden.
- Vi använder Composite Pattern i VehicleController för att kalla metoder på alla vehicles genom en metod. 

### Finns det något ställe där ni kan förbättra er design genom att använda detta design pattern? 
- Factory method 

### Vilka designproblem skulle ni lösa genom att använda det? Om inte, varför skulle er design inte förbättras av att använda det?
- Factory Method - TODO! 
