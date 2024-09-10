fun inputAge(): Int {
    while (true) {
        print("\nВведите возраст для поиска: ")
        val input = readln()
        if (input == ""){
            return 0
        }
        if (input.toIntOrNull() == null) {
            println("Вводите число!")
            continue
        }
        if (input.toInt() < 1) {
            println("Минимальное значение - 1.")
            continue
        }
        if (input.toInt() > 100) {
            println("Максимальное значение - 100.")
            continue
        }
        return input.toInt()
    }
}

fun inputFirstChar(): String {
    while (true) {
        print("Введите первый символ имени: ")
        val input = readln()
        if (input == "") return ""
        if (input.length > 1) {
            println("Не больше одного символа!")
            continue
        }
            if (input.lowercase() !in ("а".."я")) {
                println("Используйте кириллические символы.")
                continue
            }
        return input.uppercase()
    }
}