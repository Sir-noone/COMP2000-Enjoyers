# Week 7 Logbook

# Head First Design Patterns
## Chapter 1: Strategy Pattern

The Strategy Pattern defines a family of interchangeable algorithms, places each algorithm in its own class, and allows them to be selected at runtime. A class uses composition to work with a strategy through a common interface instead of implementing every variation itself. This keeps behaviours separate, makes them easier to change or extend, and avoids large conditional statements or duplicated code. For example, a game character could use different behaviours for flying, walking, or not moving without changing the character class.

## Chapter 2: Observer Pattern

The Observer Pattern defines a one-to-many relationship between objects. The subject maintains a list of observers and notifies them automatically whenever its state changes. Observers implement a common interface so they can subscribe or unsubscribe without changing the subject. This creates loose coupling and allows multiple parts of a program to react to the same event, such as weather displays updating when new weather data is available.

# Presentation Notes
## Team 2
Predator Prey simulation, Parent class animals (abstract class), dinosaur and caveman, movement, bouncing off walls, taking damage in animal, which caveman extends off of. Simulator updates on a timer and draws the entities. No generics or exceptions. The entities have timers attatched to them to simulate hunger and health, and their behaviour changes when the hunger timer hits 0. There is a complicated relationship and hunting logic which determines caveman hunting and dinosaur hunting. Caves and volcanoes, caves are safezones for cavemen, volcanoes have a xy value and a radius, and lava based on the tip of the volcano, volcanos can erupt and kill entities within the lava radius. 

## Unnamed group
Predator Prey simulatiom, done with food, mice, rabbits, and fox. Predators have a detection radius which detects prey, there is a hunger and nutrition value, the simulation is live and runs on ticks, x and y values of every entity is tracked, so is total entity count of each type. 

## Reflection
For our simulation, we have made good groupwork progress, however we could have improved with better branching and version management. Some of the work that needs to be completed are also dependant on other sections being completed first, which have forced the use of placeholders in some of the classes to ensure the submission can run.