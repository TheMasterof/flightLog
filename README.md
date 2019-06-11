# flightLog
## Repository of our SYP-Project flightLog

http://vm81.htl-leonding.ac.at:8080/issues/flog

## Starten des Programms

### 1. Projekt in IntelliJ geöffnent  
In IntellJ: File -> Open -> [Verzeichnis von Project]/flightLogFXML

### 2. Datenbank Starten  
In Terminal zu /flightLog navigieren -> docker-compose up -d

### 3. Datenbank in IntelliJ hinzufügen  
Am rechten Rand von IntelliJ auf Database klicken, dann auf + -> Data Source -> MariaDB



Um das Programm starten zu können muss es in IntelliJ geöffnent werden.
Die Datenbank muss mit 'docker-compose up' in diesem Ordner gestartet werden.
In IntelliJ müssen im Datenbank Fenster als Username: 'user' und als Passwort 'passme' angegeben und eventuell die driver installiert werden.
Danach kann man das Programm starten.
Um Logs anlegen zu können, muss man sich über das Login Fenster mit Usernamen 'peda', 'hans' oder 'franz' und keinem Passwort anmelden

Fehldende Funktion:
Login mit ldap weil wir eine Connection-URL des Ldap Server benötigen
