package eci.edu.co

import scala.annotation.tailrec

trait FinanceOperation {
  def income

  def expense(`type`: String): Unit = {
    if (`type` == Transaction.Expense)
      println("Expense operation")
  }
}

case class Transaction(value: Double, currency: String, `type`: String = "") extends FinanceOperation {

  import Transaction._

  override def income: Unit =
    if (`type` == Transaction.Income)
      println("Income operation")

  def this(currency: String, `type`: String) {
    this(0, currency, `type`)
  }

  def valueInCop = calculateValueInCop(currency, value)

  def valueInCopT = calculateValueInCop(this)

  def printTransaction: Unit = println(toString)

  override def toString: String = s"(Value: $value, Currency: $currency, Type: ${`type`})"

}

object Transaction {

  import scala.util.Random

  val COP = "COP"
  val CLP = "CLP"
  val USD = "USD"
  val EUR = "EUR"
  val GBP = "GBP"
  val JPY = "JPY"
  val Currencies = List(COP, USD, EUR, GBP, JPY)

  val USD_COP = 3872
  val EUR_COP = 4529
  val GBP_COP = 5275
  val JPY_COP = 35.26

  val Income = "Income"
  val Expense = "Expense"
  val Types = List(Income, Expense)

  val random = new Random()

  def calculateValueInCop(currency: String, value: Double): Either[String, Double] = {
    currency match {
      case USD => Right(value * USD_COP)
      case EUR => Right(value * EUR_COP)
      case GBP => Right(value * GBP_COP)
      case JPY => Right(value * JPY_COP)
      case COP => Right(value)
      case _ => Left(s"No value defined for $currency")
    }
  }

  def calculateValueInCop(t: Transaction): Either[String, Double] = {
    t match {
      case Transaction(_, USD, _) => Right(t.value * USD_COP)
      case Transaction(_, EUR, _) => Right(t.value * EUR_COP)
      case Transaction(_, GBP, _) => Right(t.value * GBP_COP)
      case Transaction(_, JPY, _) => Right(t.value * JPY_COP)
      case Transaction(_, COP, _) => Right(t.value)
      case _ => Left(s"No value defined for ${t.currency}")
    }
  }

  def copToUsdValue(t: Transaction): Double = {
    t match {
      case Transaction(_, USD, _) => t.value
      case _ =>
        calculateValueInCop(t) match {
          case Left(x) =>
            println(x)
            0
          case Right(valueInCop) => valueInCop / USD_COP
        }
    }
  }

  def valueCopToUSDTransaction(t: Transaction): Transaction = Transaction(copToUsdValue(t),USD,t.`type`)

  def toUsdTransaction(t: Transaction): Either[String, Transaction] =
  {
      t match {
        case Transaction(_, USD, _) => Right(t)
        case _ =>
          t.valueInCop match {
            case Left(x) => Left(x)
            case Right(x) => Right(valueCopToUSDTransaction(Transaction(x, COP, t.`type`)))
          }
      }
    }

  def copToEurValue(t: Transaction): Double = {
    t match {
      case Transaction(_, EUR, _) => t.value
      case _ =>
        calculateValueInCop(t) match {
          case Left(x) =>
            println(x)
            0
          case Right(valueInCop) => valueInCop / EUR_COP
        }
    }
  }

  def valueCopToEURTransaction(t: Transaction): Transaction = Transaction(copToEurValue(t),EUR,t.`type`)

  def toEurTransaction(t: Transaction): Either[String, Transaction] = {
    t match {
      case Transaction(_, EUR, _) => Right(t)
      case _ =>
        t.valueInCop match {
          case Left(x) => Left(x)
          case Right(x) => Right(valueCopToEURTransaction(Transaction(x, COP, t.`type`)))
        }
    }
  }

  @tailrec
  def sumExpenseCopTransactionsTailRec(copTransactions: List[Transaction], sum: Double): Double =
  copTransactions match {
    case Nil => sum
    case head :: tail  => head match {
      case Transaction(_,_,Transaction.Expense) => sumExpenseCopTransactionsTailRec(tail, sum + calculateValueInCop(head).getOrElse(0.0))
      case _ => sumExpenseCopTransactionsTailRec(tail, sum)
    }
  }

  def randomTransaction() = {
    val currency = randomCurrency
    new Transaction(currency = currency, value = randomValue(currency), `type` = randomType)
  }

  def randomCurrency: String = Currencies(random.nextInt(Currencies.length))

  def randomType: String = Types(random.nextInt(Types.length))

  def randomValue(currency: String): Double = currency match {
    case USD => random.nextInt(15)
    case EUR => random.nextInt(10)
    case COP => random.nextInt(50000)
    case _ => 0
  }
}
