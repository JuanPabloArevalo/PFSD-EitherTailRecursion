import scala.annotation.tailrec

val list = List(4, 5, 6)
val seq = Seq(4, 5, 6)

list :+ 8
seq :+ 8

list :+ ()
list :+ "sample"
list :+ (2, 3)
seq :+ (2, 3)

1 :: list
//1 :: seq

1 +: list
1 +: seq

list :+ 1
1 +: list
2 +: (1 +: seq)

val list1 = List(0, 1, 2)
val list2 = List(3, 4, 5, 6)
val seq1 = Seq(6, 7, 8)

list1 ++ list2 ++ seq1

seq1 ++ list2

list2.filter(_ != 6)

val emptyList = Nil
val nonEmptyList = 1 :: 2 :: emptyList

@tailrec
def sumList(list: List[Int], sum: Int): Int = list match {
  case Nil => sum
  case x :: xs => sumList(xs, sum + x)
}

sumList(List(4, 5, 6, 7), 0)