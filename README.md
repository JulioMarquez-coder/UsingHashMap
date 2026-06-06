# Using HashMap - Map Traveller Game

This project was created for my Discrete Mathematics II class. The purpose of the program is to use a `HashMap` to represent a map with different towns and the possible directions between them.

The program was developed in Java and includes a graphical interface using Java Swing.

## How the program works

The user enters an initial town and a destination town. After starting the game, the user can move through the map using the following directions:

* `n` for north
* `s` for south
* `e` for east
* `w` for west

The program checks the connections stored in the `HashMap` and determines if the selected movement is valid. It also counts how many movements the user makes before arriving at the destination.

## Town connections

The towns included in the map are:

* Lam
* Mit
* Fer
* Lex
* Hon
* Maz
* Toy
* Mer
* Che

The connections between the towns are stored using a `Map` inside another `Map`.

```java
private static Map<String, Map<String, String>> mapa = new HashMap<>();
```

Example of some town connections:

```java
mapa.put("Lam", Map.of("e", "Mit"));
mapa.put("Mit", Map.of("e", "Fer", "s", "Mer"));
mapa.put("Lex", Map.of("e", "Hon", "s", "Maz", "n", "Lam"));
```

## Main features

* Uses Java `HashMap` and `Map`.
* Uses Java Swing for the graphical interface.
* Allows the user to select an initial town and destination.
* Validates each movement entered by the user.
* Counts the number of movements.
* Displays the map image inside the program.
* Allows the user to play again after reaching the destination.

## Files included

* `MarquezJulioTareaMapa.java` - Main Java program.
* `Mapa.png` - Image of the map used in the interface.
* `MarquezJulioVideo.mp4` - Video showing the program.
* `README.md` - Information about the project.

## How to run the program

1. Download or clone the repository.
2. Open the project folder in Visual Studio Code or another Java IDE.
3. Make sure Java is installed on the computer.
4. Keep the `Mapa.png` file in the same folder as the Java program.
5. Compile the program:

```bash
javac MarquezJulioTareaMapa.java
```

6. Run the program:

```bash
java MarquezJulioTareaMapa
```

## Author

Julio A. Marquez Torres
