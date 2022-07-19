package com.gabriel.myframe

//def v = 0
//v = new Date()
//def map = [:]
//map["value1"] = "1.06595068764607"
//
//println map
//
//Double value = Double.valueOf(map.get("value1").toString())
//
//println value

//def num = 0.23498734783523E-1
//println(Double.valueOf(num))
//println(num)
//print(num > 0.1)

//Double dataMap
//if (dataMap == null) {
//    print(1)
//}
//print(dataMap)

List<List<String>> list = [
        ["aKey", "NULL", "NULL", "0.921", "0.321"],
        ["aKey", "-inf", "876", "0.325", "0.321"],
        ["aKey", "231", "453", "0.654", "0.321"],
        ["aKey", "654", "765", "0.667", "0.321"],
        ["bKey", "NULL", "NULL", "0.921", "0.321"],
        ["bKey", "-inf", "876", "0.325", "0.321"],
        ["bKey", "231", "453", "0.654", "0.321"],
        ["bKey", "654", "765", "0.667", "0.321"]
]

def map = list.groupBy ({
    it.get(0)
})

println map