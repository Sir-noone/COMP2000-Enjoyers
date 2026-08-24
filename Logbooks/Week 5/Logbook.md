# Week 5

## Generics
let classes and methods work with different types while providing compile-time type checking. In `Container<String>`, `T` is replaced with `String`, so the container should hold strings.

## Type erasure
means generic type information is mostly removed at runtime. A raw reference can bypass the compiler's checks:

```java
Container rawStrings = strings;
rawStrings.add(42);
```

This puts an `Integer` into the `Container<String>`. When `strings.get(2)` is assigned to a `String`, the compiler-generated runtime check fails and throws `ClassCastException`.

## Wildcards
use `?` for an unknown type:

```java
Container<?> container;
```

This can refer to a container of any type. Values cannot safely be added because the exact type is unknown.

## Bounded types
restrict possible types. `Container<? extends Number>` can produce numbers, while `Container<? super Integer>` can consume integers. A generic bound can also be declared as `<T extends Number>`. The rule PECS means Producer Extends, Consumer Super.
