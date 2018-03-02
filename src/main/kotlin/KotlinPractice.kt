/**
 * main
 */
fun main(args: Array<String>) {
    println("Hello World!")
    User("Grady").user()
    var u = User("")
    u.age = 10
    println(u.age!!)
    var x = sum(3,5)
    print(x)
}

class User(val name:String){

    var age:Int? = null

    fun user(){
        println("A new user created , called $name")
    }
}

fun sum(a: Int,b:Int)=a+b