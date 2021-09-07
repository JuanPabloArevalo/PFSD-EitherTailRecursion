

val tuple: (String, String, Int, String) = ("Bogota", "Colombia", 57, "Spanish")
tuple._1
tuple._2
tuple._3
tuple._4


val list1 = List(1, 2, 3, 4, 5, 7)

list1.size

list1.isEmpty
list1.nonEmpty


list1.max
list1.min
list1.sum


list1.slice(0, 2)
list1.slice(3, 4)


list1.sliding(2).toList

list1.sliding(3).toList


val list2 = List(6, 7, 8, 9, 10)

val tuplesList = list1 zip list2

tuplesList.unzip


val names = List(
  "Julian", "Charlie", "Charlie", "JuanPablo",
  "Julian", "Charlie", "Charlie", "JuanPablo",
  "Sebastian", "Robert", "Charlie", "Sebastian"
)

names.groupBy(n => n).view.mapValues(_.size).toMap
names.groupBy(identity).view.mapValues(_.size).toMap
names.groupBy(n => n.contains("an"))


