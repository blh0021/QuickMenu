# Quick Menu
---

![Quick Menu Logo](docs/quickmenu.png)

## Description
Quick Menu is a java based utility that provides a simple interface simular to DMenu or Alfred.  
One key note however is Quick Menu is cross platform and will work on Mac, Linux and Windows.

## Build
### Linux / Mac
    
To run the project:
    
    mvn clean javafx:run

To create a fat jar:

    mvn compile package
    java -jar shade/quickmenu.jar


### Windows

To run the project:
    
    mvn compile exec:java

To create a fat jar:

    mvn compile package
    java -jar shade\quickmenu.jar


![Image running xfce4](docs/xfce4.gif)

## Window Managers Configs
* [XFCE4](docs/xfce4.md)

