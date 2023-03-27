# XML

eXtensible Markup Language

## Storia

- Creato dal W3C nel 1998 come XML 1.0
- Deriva da SGML (Standard Generalized Markup Language)
- Nasce dall'idea di creare un linguaggio di markup che sia più semplice di SGML

## Generalità

- XML non nasce come linguaggio di markup, ma come meta-linguaggio di markup
- XML non ha tag predefiniti e non serve nè per programmare nè per definire pagine web
- Serve a modellare stutture dati
- I markup possono essere classificati come procedural o declarative: XML è dichiarativo in quanto definisce solo dati
- XML è uno standard aperto e non proprietario
- Può essere usato da qualsiasi programma, indipendentemente dalla piattaforma

## Sintassi

- XML è case sensitive
- I tag devono essere chiusi
- I tag sono definiti dall'utente
- Gli attributi dei tag sono indicati tra virgolette
- I dati sono organizzati in modo gerarchico
- Si possono aggiungere dei commenti con `<!-- commento -->`, saranno invisibili al processore XML

## Struttura

Ogni XML ha bisogno di un prologo, che contiene informazioni sull'encoding e sulla versione del documento, e del corpo, che contiene i dati

### XML declaration

```xml
<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
```

- Versione del protocollo
- Encoding (ASCII, UTF-8, UTF-16, ISO-8859-1) (opzionale)
- Standalone: se il documento è indipendente o meno da altri documenti (opzionale)

### Corpo

Un documento è ben formato (well formed) se rispetta le regole sintattiche:

- Deve essere sempre presente un tag root (radice)
- I tag devono essere chiusi
- I tag devono essere nidificati correttamente (ordine di apertura e chiusura dei tag)
- I nomi dei tag di apertura e chiusura devono essere uguali
- I nomi dei tag non devono cominciare con il carattere underscore o con un numero e non devono avere spazi al loro interno

## Gerarchia degli elementi

- Gli elementi di un documento XML sono da considerarsi come nodi appartenenti alla struttura di un albero
. All'interno di questa struttura possiamo individuare dei "rapporti di parentela" utili per individuare gli elementi
    - padre/figlio
    - fratelli
    - predecessore/discendenti

## Elementi e attributi

I dati possono essere rappresentati come attributi o elementi

### Elementi

Un elemento è tutto ciò che va dal tag iniziale al tag finale e può avere diversi tipi di contenuto:

- Può avere un contenuto semplice
    ```xml
    <elemento>Contenuto</elemento>
    ```
- Può essere formato da altri elementi
    ```xml
    <elemento>
        <elemento2 />
    </elemento>
    ```
- Può avere un contenuto misto
    ```xml
    <elemento>
        <elemento2 />
        Contenuto
    </elemento>
    ```
- Oppure può anche essere vuoto
    ```xml
    <elemento />
    ```

### Attributi

```xml
<elemento attributo="valore" />
```

- Gli attributi forniscono informazioni extra su un elemento
- Sono posti del tag di inizio
- Sono formati da un nome e un valore
- Sono utili per specificare informazioni aggiuntive su un elemento che non si vogliono inserire nel contenuto
- Al contrario degli elementi, per gli attributi l'ordine di presentazione non è importante

### Cosa usare?

In generale è preferibile evitare gli attributi in quanto:

- Gli attributi non possono contenere valori multipli (gli elementi sì)
- Gli attributi non possono contenere dati strutturati (gli elementi sì)
- Gli attributi sono più difficili da elaborare da parte di un codice di programma

## DTD

- La DTD (Document Type Definition) è un documento che definisce la struttura di un documento XML
- Un documento è valido (valid) se rispetta le regole sintattiche ed è conforme alla DTD
- Una alternativa alla DTD è lo schema XML, più moderno e avanzato
- Una DTD definisce elementi, attributi, elenchi, entità, proprietà e relazioni
- Le DTD possono far parte del documento XML o essere esterne
- Nella maggior parte dei casi si usano DTD pubbliche, che sono disponibili online
- Si può validare un XML [direttamente online](https://truugo.com/xml_validator)

### Sintassi

Se la DTD è interna al documento, va inserita tra parentesi angolari dopo la dichiarazione XML

```xml
<!DOCTYPE nome_documento [dichiarazioni]>
```

Se la DTD è esterna, va dichiarato dove si trova il file

```xml
<!DOCTYPE nome_documento SYSTEM "nome_file.dtd">
```

Se la DTD è pubblica, va dichiarato dove si trova il file

```xml
<!DOCTYPE nome_documento PUBLIC "nomeDTD" "URL">
```

### ELEMENT

```xml
<!ELEMENT nome_elemento tipo_contenuto>
```

- `<!ELEMENT>` definisce un elemento
- `nome_elemento` è il nome dell'elemento

Il tipo di contenuto può essere:

- `EMPTY`: Elemento vuoto
- `ANY`: Elemento con qualsiasi contenuto
- `(#PCDATA)`: Elemento con contenuto testuale
- `(elemento1)`: Elemento con contenuto formato da un elemento ripetuto 1 sola volta
- `(elemento1?)`: Elemento con contenuto formato da un elemento ripetuto 0 o 1 volta
- `(elemento1*)`: Elemento con contenuto formato da un elemento ripetuto 0 o più volte
- `(elemento1+)`: Elemento con contenuto formato da un elemento ripetuto 1 o più volte
- `(elemento1, elemento2, elemento3)`: Elemento con contenuto formato da una sequenza di elementi e ordinati come nella definizione
- `(elemento1 | elemento2 | elemento3)`: Elemento con contenuto formato da solo uno dei tre elementi

```xml
<!DOCTYPE catalogo [
    <!-- il contenuto di catalogo è formato da una sequenza di libri ripetuti 1 o più volte -->
    <!ELEMENT catalogo (libro+)>

    <!-- il contenuto di libro è formato da un titolo, un autore e un anno -->
    <!ELEMENT libro (titolo, autore, anno)>

    <!-- il contenuto di titolo, autore e anno è testuale -->
    <!ELEMENT titolo (#PCDATA)>
    <!ELEMENT autore (#PCDATA)>
    <!ELEMENT anno (#PCDATA)>
]>
```

### ATTLIST

```xml
<!ELEMENT nome_elemento EMPTY>
<!ATTLIST nome_elemento nome_attributo tipo_attributo valore_attributo>
```

- `<!ATTLIST>` definisce un attributo
- `nome_elemento` è il nome dell'elemento
- `nome_attributo` è il nome dell'attributo
- `tipo_attributo` è il tipo di valore dell'attributo
- `valore_attributo` è il valore di default dell'attributo, opzionale

Il tipo di attributo può essere:

- `CDATA`: Valore testuale
- `ID`: Valore univoco
- `(val1|val2)`: Valore che può essere solo `val1` o `val2`

Il valore dell'attributo può essere:

- `"valore"`: L'attributo ha valore di default pari a `valore` se non viene specificato
- `#REQUIRED`: L'attributo è obbligatorio
- `#IMPLIED`: L'attributo è opzionale
- `#FIXED "valore"`: L'attributo ha un valore fisso

```xml
<!DOCTYPE catalogo [
    <!ELEMENT catalogo (libro+)>
    <!ELEMENT libro (titolo, autore, anno)>
    <!ELEMENT titolo (#PCDATA)>
    <!ELEMENT autore (#PCDATA)>
    <!ELEMENT anno (#PCDATA)>

    <!-- l'elemento libro ha un attributo ISBN di tipo CDATA -->
    <!ATTLIST libro ISBN CDATA #REQUIRED>
]>
```
