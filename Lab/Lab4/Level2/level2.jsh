/open Box.java

Box.ofNullable(4)
Box.ofNullable("string")
Box.ofNullable(null)
Box.empty() == Box.empty()
Box.ofNullable(null) == Box.empty()
Box.ofNullable(null).equals(Box.empty())
Box.ofNullable(null).equals(Box.of(null))
Box.ofNullable("string").isPresent()
Box.ofNullable(null).isPresent()

/exit