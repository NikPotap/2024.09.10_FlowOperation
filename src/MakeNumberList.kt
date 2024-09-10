fun makeNumberList (size: Int, min: Int, max: Int): List<Int>{
    var list: MutableList<Int> = mutableListOf()
    repeat(size){
        list.add((min..max).random())
    }
    return list
}