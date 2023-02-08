# notes

This CLI program will allow the user to add, edit, and/or delete notes from/to a local database.
It will attempt to follow MVC conventions.

#### Compilation:

From the root directory run `mvn clean compile assembly:single ` this will generate the jar inside `/target`.

#### Execution:

Run `java -jar notes-1.0-SNAPSHOT-jar-with-dependencies.jar`

#### Database:

The database will be stored in same directory as the jar file.

#### Technologies:
- Java
- Maven
- SQLite