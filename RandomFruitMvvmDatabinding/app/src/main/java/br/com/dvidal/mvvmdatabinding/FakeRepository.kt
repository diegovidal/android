package br.com.dvidal.mvvmdatabinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.util.*

/**
 * @author diegovidal on 28/09/2018.
 */
object FakeRepository {

    private val fruitNames: List<String> = listOf(
            "Apple", "Banana", "Orange", "Kiwi", "Grapes", "Fig",
            "Pear", "Strawberry", "Gooseberry", "Raspberry"
    )

    private val _currentRandomFruitName = MutableLiveData<String>()
    val currentRandomFruitName: LiveData<String>
        get() = _currentRandomFruitName

    init {
        _currentRandomFruitName.value = fruitNames.first()
    }

    fun getRandomFruitName(): String {
        val random = Random()
        return fruitNames[random.nextInt(fruitNames.size)]
    }

    fun changeCurrentRandomFruitName() {
        _currentRandomFruitName.value = getRandomFruitName()
    }
}