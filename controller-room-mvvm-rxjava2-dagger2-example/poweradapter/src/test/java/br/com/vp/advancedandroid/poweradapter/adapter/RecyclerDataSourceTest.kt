package br.com.vp.advancedandroid.poweradapter.adapter

import android.view.View
import android.view.ViewGroup
import br.com.vp.advancedandroid.poweradapter.item.ItemRenderer
import br.com.vp.advancedandroid.poweradapter.item.RecyclerItem
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

/**
 * @author diegovidal on 11/05/2018.
 */
class RecyclerDataSourceTest {

    private val rendererOne: ItemRenderer<out RecyclerItem> = TestRender(1)
    private val rendererTwo: ItemRenderer<out RecyclerItem> = TestRender(2)

    private val itemOne = TestItem(1, "r1")
    private val itemTwo = TestItem(2, "r1")
    private val itemThree = TestItem(3, "r2")

    private lateinit var dataSource: RecyclerDataSource

    @Before
    fun setUp() {
        val items = arrayListOf(itemOne, itemTwo, itemThree)
        val renderers = mutableMapOf(
                Pair("r1",rendererOne),
                Pair("r2",rendererTwo)
        )
        dataSource = RecyclerDataSource(renderers)
        dataSource.seedData(items)
    }

    @Test
    fun rendererForType() {

        assertEquals(rendererOne, dataSource.rendererForType(rendererOne.layoutRes()))
        assertEquals(rendererTwo, dataSource.rendererForType(rendererTwo.layoutRes()))
    }

    @Test
    fun viewResourceForPosition() {

        assertEquals(rendererOne.layoutRes(), 1)
        assertEquals(rendererTwo.layoutRes(), 2)
    }

    @Test
    fun getCount() {

        assertEquals(3, dataSource.getCount())
    }

    @Test
    fun getItem() {

        assertEquals(itemOne, dataSource.getItem(0))
        assertEquals(itemThree, dataSource.getItem(2))
    }

    internal class TestItem
    constructor(val mId: Long,
                val mKey: String)
        : RecyclerItem {

        override fun getItemId(): Long {
            return mId
        }

        override fun renderKey(): String {
            return mKey
        }
    }

    internal class TestRender
    constructor(val layoutRes: Int)
        : ItemRenderer<RecyclerItem> {

        override fun layoutRes(): Int {
            return layoutRes
        }

        override fun createView(parent: ViewGroup): View {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }

        override fun render(itemView: View, item: RecyclerItem) {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}