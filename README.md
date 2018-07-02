# swingy
42 school project

## About
Text-based RPG follows the Model-View-Controller architecture and allow switching between the console view and GUI view.
A player can have multiple heroes of different types. After choosing a hero the actual game begins. The initial position of the hero is in the center of the map. He wins the game if he reaches on of the borders of the map.

## Build
``` 
mvn clean package
```

## Modes
The game can be launched in 2 modes:
``` 
java -jar swingy.jar console
java -jar swingy.jar gui
```

## Validation
Validation implemented via Hibernate Annotation Validator.
