

val firstMap = Map("key1" -> "value1", "key2" -> "value2")
firstMap("key1")
firstMap.keys
firstMap.values


val m = Map(
  "US" -> 1,
  "ES" -> 34,
  "CO" -> 57,
  "JP" -> 81
)

val colombiaCountryGet = m.get("CO")
val chileCountryGet = m get "CL"

val colombiaCountryCode = m("CO")
//val nonValidCountry = m("CL")


m + ("AR" -> 54)

m -- List("ES")
m -- Seq("US", "JP")

m.filter(tuple => tuple._1 != "ES")

val mappedValues =
  m
    .view
    .mapValues(countryCode => s"+$countryCode").toMap
mappedValues("CO")
mappedValues.get("CO")
mappedValues.get("CL")





