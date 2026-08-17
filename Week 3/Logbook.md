# Learning Java (3rd Edition) - Chapter 5 Summary
## Classes, Methods, and Object Creation

## Classes and Objects

A **class** is a blueprint for creating objects. It defines the structure (fields) and behavior (methods) of objects. You create an object using the `new` keyword, which allocates memory and calls a constructor. Example: `Dog dog = new Dog();`

## Methods

A method is a function that performs a specific action. It has a return type, name, optional parameters, and a body. Call methods on objects using dot notation: `objectName.methodName()`. Parameters are inputs, and the method can return a value or be `void` (no return).

```java
public int add(int a, int b) {
    return a + b;
}
```

## Constructors

A constructor is a special method that runs when you create an object. It initializes the object's fields. The constructor name matches the class name and has no return type. A class can have multiple constructors with different parameters.

```java
public class Dog {
    public Dog(String name) {  // Constructor
        this.name = name;
    }
}
```

## The `this` Keyword

`this` refers to the current object instance. It's used to distinguish instance variables from local variables, especially useful in constructors and methods where parameter names match field names: `this.name = name;`

## Access Modifiers

Control who can access class members:
- **public**: accessible from anywhere
- **private**: accessible only within the class
- **protected**: accessible within same package and subclasses
- No modifier: package-private (accessible within same package)

## Method Overloading

You can have multiple methods with the same name as long as they have different parameters (different number, type, or order). This lets you use the same method name for similar operations with different data types.

```java
public int add(int a, int b) { return a + b; }
public double add(double a, double b) { return a + b; }
```

## Static Members

Static variables and methods belong to the class itself, not individual objects. They're shared among all instances. Access them using `ClassName.memberName`. Static methods cannot directly access instance variables.

```java
static int count = 0;
static void increment() { count++; }
```

## Encapsulation

Make fields private and provide public getter/setter methods to control access. This protects your data and lets you add validation.

```java
private int age;
public int getAge() { return age; }
public void setAge(int age) { if (age > 0) this.age = age; }
```

## Object References

Variables hold references to objects, not the objects themselves. Multiple variables can reference the same object. When you assign one object variable to another, they both point to the same object in memory.

```java
Dog dog1 = new Dog("Buddy");
Dog dog2 = dog1;  // Both point to the same object
```
