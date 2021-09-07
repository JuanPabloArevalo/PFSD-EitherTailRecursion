

val number1: Int = 6
val number2: Int = 3

val evenDivided2: Int => Option[Int] = n => if (n % 2 == 0) Some(n / 2) else None


val wrapNumberInOption1: Option[Int] = Option(number1)
val wrapNumberInOption2: Option[Int] = Option(number2)
val wrapNumberInOption3: Option[Int] = None

wrapNumberInOption1.flatMap(evenDivided2)
wrapNumberInOption2.flatMap(evenDivided2)
wrapNumberInOption3.flatMap(evenDivided2)

val option12 = Option(12)

option12
  .flatMap(evenDivided2) //6
  .flatMap(evenDivided2) //3

option12
  .flatMap(evenDivided2) //6
  .flatMap(evenDivided2) //3
  .flatMap(evenDivided2) //None

for {
  number12 <- option12
  number6 <- evenDivided2(number12)
  number3 <- evenDivided2(number6)
} yield number3

for {
  number12 <- option12
  number6 <- evenDivided2(number12)
  number3 <- evenDivided2(number6)
  nothing <- evenDivided2(number3)
} yield nothing