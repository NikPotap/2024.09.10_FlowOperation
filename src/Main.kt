import kotlinx.coroutines.flow.*

suspend fun main() {
// 1.
    println("1. Вычисление суммы квадратов.\n")
    val list = makeNumberList(10, 1, 10)
    println("Задан список чисел:\n${list.joinToString()}")
    val sumSQR = list.asFlow().map { num -> num * num }.reduce { res, value -> res + value }
    println("Получен результат: $sumSQR.")
// 2.
    println("\n2. Поиск в списке персон.")
    val searchAge = inputAge()
    val searchFirstChar = inputFirstChar()
    val personsFlow = makePersonList().asFlow()
    personsFlow.getPerson(searchFirstChar, searchAge)
// 3.
    println("\n3. Список новых сотрудников.")
    val persons: MutableList<Staff> = mutableListOf()
    val names = makeNames().asFlow()
    val cards = makeCards().asFlow()
    val pins = makePINs().asFlow()
    makePersons(names, cards, pins).collect { pers -> persons.add(pers as Staff) }
    println(persons.joinToString(";\n","Создан список сотрудников:\n","."))
}
// класс и фунция для задания номер два
class Person(val name: String, val age: Int) {
    override fun toString(): String {
        return "$name, $age"
    }
}
suspend fun Flow<Person>.getPerson(first: String, age: Int) {
    if (first == "" && age == 0) {
        this.collect { pers -> println(pers) }
        return
    }
    if (first == "") {
        this.filter { person -> person.age == age }.collect { pers -> println(pers) }
        return
    }
    if (age == 0) {
        this.filter { person -> person.name.first().toString() == first }.collect { pers -> println(pers) }
        return
    }
    this.filter { person -> (person.name.first().toString() == first) && (person.age == age) }
        .collect { pers -> println(pers) }
    return
}
// класс и фунция для задания номер три
class Staff (private val name: String, private val card: String, private val pin: String){
    override fun toString(): String {
        return "$name, номер карты $card, PIN карты $pin"
    }
}
fun <T1, T2, T3> makePersons(first: Flow<T1>, second: Flow<T2>, third: Flow<T3>): Flow<*>{
    return first.zip(second){ a, b -> Pair(a, b)}.zip(third){ pair, c -> Staff(pair.first.toString(), pair.second.toString(), c.toString()) }
}