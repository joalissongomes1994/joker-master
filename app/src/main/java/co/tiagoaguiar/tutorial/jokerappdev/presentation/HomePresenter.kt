package co.tiagoaguiar.tutorial.jokerappdev.presentation

import android.graphics.Color
import co.tiagoaguiar.tutorial.jokerappdev.data.CategoryRemoteDataSource
import co.tiagoaguiar.tutorial.jokerappdev.data.ListCategoryCallback
import co.tiagoaguiar.tutorial.jokerappdev.model.Category
import co.tiagoaguiar.tutorial.jokerappdev.view.HomeView

class HomePresenter(
    private val view: HomeView,
    private val dataSource: CategoryRemoteDataSource
) : ListCategoryCallback {

    fun findAllCategories() {
        view.showProgress()
        dataSource.findAllCategories(this)
    }

    override fun onComplete() {
        view.hideProgress()
    }

    override fun onSuccess(response: List<String>) {
        val start = 40 // H - initial hue
        val end = 65 // H - final hue
        val diff = (end - start) / response.size // (initial - end) / qtd

        val categories = response.mapIndexed { index, value ->
            val hsv = floatArrayOf(
                // (diff * (index + 1)).toFloat()
                start + (diff * index).toFloat(),
                100.0f,
                100.0f,
            )

            Category(value, Color.HSVToColor(0x55 , hsv).toLong())
        }

        view.showCategories(categories)
    }

    override fun onError(message: String) {
        view.showFailure(message)
    }
}