package co.tiagoaguiar.tutorial.jokerappdev.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import co.tiagoaguiar.tutorial.jokerappdev.R
import co.tiagoaguiar.tutorial.jokerappdev.model.Joke
import co.tiagoaguiar.tutorial.jokerappdev.presentation.JokeDayPresenter
import com.squareup.picasso.Picasso

class JokeDayFragment : Fragment(), JokeDayView {

    private lateinit var presenter: JokeDayPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var txtJokeDay: TextView
    private lateinit var imgJokeDay: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = JokeDayPresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_joke_day, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = view.findViewById(R.id.progress_joke)
        txtJokeDay = view.findViewById(R.id.txt_joke)
        imgJokeDay = view.findViewById(R.id.image_joke)

        presenter.findJokeDay()
    }

    override fun showJokeDay(response: Joke) {
        txtJokeDay.text = response.text
        Picasso.get().load(response.iconUrl).into(imgJokeDay)
    }

    override fun showFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
        txtJokeDay.visibility = View.GONE

    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
        txtJokeDay.visibility = View.VISIBLE
    }
}