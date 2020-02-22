/open BooleanCondition.java
/open LastDigitsOfHashCode.java
/open Transformer.java
/open DivisibleBy.java
/open LongerThan.java
/open BoxIt.java
/open Box.java

Box.of(4).map(new BoxIt<>())
Box.of(Box.of(5)).map(new BoxIt<>())
Box.ofNullable(null).map(new BoxIt<>())




/exit