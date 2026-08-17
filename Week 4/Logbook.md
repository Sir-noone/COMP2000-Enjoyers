# Week 4 Planning




# Class Hierarchy, Interfaces, and Inheritance

## Class Hierarchy

A class hierarchy is a structure where classes are organized in a tree-like relationship. A parent (or superclass) is at the top, and child classes (subclasses) extend from it, inheriting its properties and methods. This allows you to organize related classes and reuse common code.

## Inheritance

Inheritance allows a class to acquire properties and methods from another class. Use the `extends` keyword to inherit from a parent class. A subclass can override parent methods to change their behavior while still inheriting everything else. Java supports single inheritance (extending one class only).

```java
class Animal {
    void eat() { System.out.println("Eating..."); }
}

class Dog extends Animal {
    void bark() { System.out.println("Woof!"); }
}
```

## Method Signature

A method signature consists of the method name and its parameters (number, type, and order). It uniquely identifies a method. Two methods with the same name but different signatures can exist in the same class (overloading). The return type is NOT part of the signature.

```java
void display(int x)  // signature: display(int)
void display(String s)  // signature: display(String)
```

## Interfaces

An interface is a contract that defines what methods a class must implement. It contains abstract methods (no body) and constants. Classes implement interfaces using the `implements` keyword. A class can implement multiple interfaces but can only extend one class. Use interfaces to enforce a consistent structure across different classes.

```java
interface Animal {
    void eat();
    void sleep();
}

class Dog implements Animal {
    public void eat() { }
    public void sleep() { }
}
```

## Default Methods

A default method is a method in an interface that has an actual implementation (body). It allows interfaces to provide default behavior that implementing classes can use without overriding. Use the `default` keyword. This lets you add new methods to interfaces without breaking existing code.

```java
interface Animal {
    default void sleep() {
        System.out.println("Sleeping...");
    }
}
```

## Methods

Methods are functions that perform actions. They can be instance methods (belong to objects), static methods (belong to the class), or abstract methods (no body, must be implemented by subclasses). Methods define the behavior of objects and interfaces.
