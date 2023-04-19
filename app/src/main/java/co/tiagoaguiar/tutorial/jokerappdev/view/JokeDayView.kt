package co.tiagoaguiar.tutorial.jokerappdev.view

import co.tiagoaguiar.tutorial.jokerappdev.model.Joke

interface JokeDayView {
    fun showJokeDay(response: Joke)
    fun showFailure(message: String)
    fun showProgress()
    fun hideProgress()
}