# Week 8 Logbook

## Decorator Pattern

The Decorator Pattern adds new behaviour to an object by wrapping it in another object. The decorator follows the same interface as the object it wraps, so decorators can be combined and used wherever the original object is expected. This provides a flexible alternative to creating many subclasses. For example, a basic coffee object could be wrapped with milk and then sugar decorators, adding each feature without changing the coffee class.

## Iterator Pattern

The Iterator Pattern provides a standard way to access the elements of a collection one at a time without exposing how the collection is stored. An iterator usually provides operations such as checking whether another element exists and returning the next element. This allows the same traversal code to work with different collections, such as arrays, lists, or trees.

## State Pattern

The State Pattern allows an object to change its behaviour when its internal state changes. Each state is represented by a separate class containing the behaviour appropriate for that state, and the main object delegates actions to its current state. For example, a media player could have playing, paused, and stopped states, each responding differently when the play button is pressed.

## Open/Closed Principle

The Open/Closed Principle states that software entities such as classes, modules, and functions should be open for extension but closed for modification. New behaviour should be added by creating new code or implementations rather than repeatedly changing existing, tested code. Interfaces, inheritance, and composition can help achieve this by allowing new implementations to be substituted without altering the code that uses them.

# Arguments 
## "Refactoring the game's 'enum state' into 'gamestate' classes was achange worth making.
For (My Team): gamestate classes allow for further expansion in the future, following open design principles. enum states is difficult to expand on.

Against: enum states is simple, and easy to work upon. 