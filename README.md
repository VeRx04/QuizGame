cat > README.md <<EOL
# Java Quizspiel

## Inhalt

- [Über das Projekt](#über-das-projekt)
- [Funktionen](#funktionen)
- [Installation](#installation)
  - [XAMPP & MySQL einrichten](#xampp--mysql-einrichten)
  - [Datenbank importieren](#datenbank-importieren)
  - [Repository klonen](#repository-klonen)
  - [Projekt starten](#projekt-starten)
- [Technologien](#technologien)

---

## Über das Projekt

Dieses Projekt ist ein interaktives **Quizspiel**, das mit **Java, Swing, JDBC & SQL** entwickelt wurde.  
Es verfügt über eine **grafische Benutzeroberfläche** und liest Fragen, Antwortmöglichkeiten sowie zugehörige Metadaten aus einer **MySQL-Datenbank**.  
Zusätzlich werden Highscores gespeichert, um den Spielfortschritt der Spieler zu verfolgen.

**Ziel:**  
Das Projekt wurde im Rahmen meiner **Ausbildung zum Softwareentwickler** entwickelt, um praktische Erfahrung mit **Datenbankanbindung, SQL-Abfragen und Benutzerinteraktionen** zu sammeln.  
Um meine Kenntnisse weiter zu vertiefen, habe ich **freiwillig eine GUI mit Swing** implementiert und die Anwendung **modular in OOP-Struktur** entwickelt.  
Dadurch lässt sich das Quiz flexibel erweitern und anpassen.

---

## Funktionen

✔ **Interaktive Benutzeroberfläche** mit Java Swing  
✔ **Fragen aus verschiedenen Kategorien**  
✔ **Dynamische Schwierigkeitsstufen**  
✔ **Highscore-Speicherung in MySQL**  
✔ **Automatische Auswahl & Zufallsreihenfolge der Fragen**  
✔ **Vier Antwortmöglichkeiten pro Frage**  
✔ **Audioeffekte für richtige und falsche Antworten**  
✔ **Soundtracks für Hauptmenü & Spielverlauf**  
✔ **Übersicht über die Top 10 Highscores**  
✔ **Dynamische Skalierung für verschiedene Bildschirmgrößen**  
✔ **Vollbild- & Fenstermodus umschaltbar (F11)**  
✔ **Zeitlimit & Fragenanzahl für jeden Spielverlauf wählbar**   
✔ **Spielername kann eingegeben & gespeichert werden**    
✔ **Optimiert für schnelle Ladezeiten & flüssiges Gameplay**  

---

## Installation

Hier finden Sie eine Schritt-für-Schritt-Anleitung, um das Projekt auf Ihrem Rechner einzurichten.

---

### XAMPP & MySQL einrichten

1. **XAMPP herunterladen & installieren**  
   - Lade **XAMPP** von der offiziellen Seite herunter:  
     🔗 [https://www.apachefriends.org/de/download.html](https://www.apachefriends.org/de/download.html)  
   - Starte den **XAMPP-Installer** und folge den Installationsanweisungen.  
   - **Hinweis:** Bei der Installation können Sie alles standardmäßig lassen.

2. **XAMPP starten**  
   - Öffne **XAMPP Control Panel**  
   - Klicke auf **Start** bei:  
     - ✅ **Apache** (wird für phpMyAdmin benötigt)  
     - ✅ **MySQL** (wird für die Datenbank benötigt)  

3. **phpMyAdmin öffnen**  
   - Öffne einen Browser und rufe **phpMyAdmin** auf:  
     🔗 [http://localhost/phpmyadmin/](http://localhost/phpmyadmin/) 

---

### Datenbank importieren

1. **Neue Datenbank erstellen**  
   - Klicke in **phpMyAdmin** auf den Reiter **"Datenbanken"**  
   - Gib als Namen **`quiz`** ein  
   - Wähle als Zeichensatz **`utf8mb4_general_ci`**  
   - Klicke auf **Erstellen**  

2. **Datenbank importieren**  
   - Wechsle zum Reiter **"Importieren"**  
   - Klicke auf **"Datei auswählen"** und wähle die Datei **`quiz.sql`** aus dem Projektverzeichnis  
   - Klicke auf **"OK"**, um den Import zu starten  

---

### Repository klonen

1. **Git installieren (falls nicht vorhanden)**  
   - Lade **Git** von [https://git-scm.com/downloads](https://git-scm.com/downloads) herunter und installiere es.  

2. **Projekt klonen**  
   - Öffne eine Konsole (PowerShell, Git Bash oder CMD)  
   - Wechsle in Ihr gewünschtes Verzeichnis:  
     ```bash
     cd C:\Pfad\zu\Ihrem\Ordner
     ```
   - Klone das Repository von GitHub:  
     ```bash
     git clone https://github.com/VeRx04/QuizGame.git
     cd QuizGame
     ```

---

### Projekt starten

#### Option 1: **Mit Maven**  

1. **Maven installieren (falls nicht vorhanden)**  
   - Lade **Maven** von [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi) herunter  
   - Entpacke die Datei und füge den `bin`-Ordner zu den **Systemvariablen** (`PATH`) hinzu  
   - Prüfe, ob Maven korrekt installiert ist:  
     ```bash
     mvn -version
     ```

2. **Projekt kompilieren & starten**  
   - Wechsle ins Projektverzeichnis:  
     ```bash
     cd QuizGame
     ```
   - **Build erstellen & starten**  
     ```bash
     mvn clean package
     java -jar target/quiz-app-maven-0.0.1-SNAPSHOT.jar
     ```

---

#### Option 2: **Manuell mit Java kompilieren**  

Falls Sie das Projekt manuell starten möchten, können Sie es mit **javac** kompilieren:

```bash
javac -d bin src/main/java/com/quiz/main/Main.java
java -cp bin com.quiz.main.Main
