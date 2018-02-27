/**
 * main
 */
fun main(args: Array<String>) {
    println("Hello World!")
    User("Grady").user()
    var x = sum(3,5)
    print(x)
}

class User(val name:String){
    fun user(){
        println("A new user created , called $name")
    }
}

fun sum(a: Int,b:Int)=a+b