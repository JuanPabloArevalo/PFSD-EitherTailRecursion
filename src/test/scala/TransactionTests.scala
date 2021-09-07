package eci.edu.co

import Transaction.{sumExpenseCopTransactionsTailRec, toEurTransaction}

import org.scalatest.FunSuite

class TransactionTests extends FunSuite {

  val t1 = Transaction(0, Transaction.CLP, Transaction.Income)
  val t2 = Transaction(3, Transaction.USD, Transaction.Income)

  test("Handle error with Either") {
    val expectedError = "No value defined for CLP"
    val eurTransactionLeft = toEurTransaction(t1).left.getOrElse("Undefined error")
    assert(eurTransactionLeft == expectedError)
  }

  test("Works fine for") {
    val expectedValue = 2.5648045926253036
    val eurTransactionValue = toEurTransaction(t2).getOrElse(null).value
    assert(eurTransactionValue == expectedValue)
  }

  val copT1 = Transaction(5000, Transaction.COP, Transaction.Expense)
  val copT2 = Transaction(2250, Transaction.COP, Transaction.Income)
  val copT3 = Transaction(8000, Transaction.COP, Transaction.Expense)
  val copT4 = Transaction(20000, Transaction.COP, Transaction.Expense)
  val copT5 = Transaction(3500, Transaction.COP, Transaction.Income)
  val copT6 = Transaction(12600, Transaction.COP, Transaction.Expense)

  val copTransactions = List(copT1, copT2, copT3, copT4, copT5, copT6)
  test("Works fine sumCopTransactionsTailRec") {
    val expectedValue = 45600
    val expenseCopTransactionsValue = sumExpenseCopTransactionsTailRec(copTransactions, 0)
    assert(expenseCopTransactionsValue == expectedValue)
  }

}
