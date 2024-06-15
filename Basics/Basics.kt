fun main() {
    // Q1
    var baskets = 10
    var eggs = 5
    println("You have $baskets baskets")
    println("You have $eggs eggs per basket")
    println("If you have $eggs eggs per basket and $baskets baskets, the total number of eggs is ${eggs*baskets}")

    // Q2
    var score = 50
    if (score <= 100 && score >= 0) {
        println("Valid Test Score")
    }
    else {
        println("Invalid Test Score")
    }

    // Q3
    when (score) {
        in 0..100 -> {
            println("Valid Test Score")
        }
        else -> {
            println("Invalid Test Score")
        }
    }

    // Q4
    var nums = listOf(1,4,6,7,0)
    var sum = 0
    for (num in nums) {
        sum += num
    }
    println(sum)

    // Q5
    fun printEcho(message: String) {
        println(message)
    }
    printEcho("Message")

    // Q6
    var stringMessages = listOf("Hello Students", "I Love Kotlin", "3 days to my last exam")
    fun readString(string: String) {
        println("Size of $string is ${string.length}")
        println("In lower case: ${string.lowercase()}")
        println("Contains the character 'e' ${string.contains('e')}")
    }
    for (string in stringMessages) {
        readString(string)
    }
}