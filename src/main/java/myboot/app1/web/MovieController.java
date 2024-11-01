package myboot.app1.web;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.validation.Valid;

import myboot.app3.View;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import myboot.app1.dao.MovieRepository;
import myboot.app1.model.Movie;

/**
 * Un contrôleur pour gérer les films.
 */
@Controller()
public class MovieController {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	MovieRepository dao;

	@PostConstruct
	public void init() {
		System.out.println("Start " + this);
		if (dao.count() == 0) {
			dao.save(new Movie("Star wars 4", 1977, //
					"Il y a bien longtemps, dans une galaxie " + //
							"lointaine, très lointaine..."));
			dao.save(new Movie("Star wars 5", 1980, //
					"Le temps du péril a commencé pour la rébellion..."));
			dao.save(new Movie("Star wars 6", 1983, //
					"Luke Skywalker est retourné parmi les siens sur la " + //
							"planète Tatooine..."));
		}
	}

	@PreDestroy
	public void destroy() {
	}

	/**
	 * Montrer les films (GET)
	 */

	@RequestMapping(value = "/movies")
	private ModelAndView getMovies() {
		var res = new ModelAndView("movies");
		res.addObject("movies", dao.findAll());
		return res;
	}

	@ModelAttribute
	public Movie newMovie(@PathVariable(value = "id", required = false) Integer id) {
		if (id != null) {
			logger.info("find movie " + id);
			var m = dao.findById(id);
			return m.get();
		}
		Movie m = new Movie();
		m.setId(0);
		m.setName("");
		m.setYear(1900);
		m.setDescription("");
		logger.info("new movie = " + m);
		return m;
	}

	/**
	 * Montrer un film (GET)
	 */
	@RequestMapping(value = "/movie/{id}")
	private ModelAndView getMovie(@ModelAttribute Movie m) {
		var res = new ModelAndView("show-movie");
		res.addObject("movie", m);
		return res;
	}
	/**
	 * Confirmation suppr
	 */

	@GetMapping("/confirmDelete/{id}")
	public ModelAndView confirmDelete(@PathVariable("id") Integer id) {
		ModelAndView res = new ModelAndView("confirm-delete");
		var movie = dao.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid movie Id:" + id));
		res.addObject("movie", movie);
		return res;
	}
	/**
	 * Supprimer un film
	 */
	@PostMapping("/deleteMovie/{id}")
	public String deleteMovie(@PathVariable("id") Integer id) {
		dao.deleteById(id);
		return "redirect:/movies"; // Redirection vers la liste après suppression
	}
	
	/**
	 * lister < 2000
	 */
	@RequestMapping(value = "/lister")
	private ModelAndView getMovieBydate() {
		var res = new ModelAndView("listed-movie");
		res.addObject("oldmovie", dao.listByDate());
		return res;
	}

	/**
	 * Editer un film (GET)
	 */
	@GetMapping(value = "/edit-movie/{id}")
	private ModelAndView getEditMovie(@ModelAttribute Movie m) {
		var res = new ModelAndView("edit-movie");
		res.addObject("movie", m);
		return res;
	}

	/**
	 * Editer un film (POST)
	 */
	@PostMapping(value = "/edit-movie/{id}")
	private String postEditMovie(@ModelAttribute @Valid Movie m, BindingResult result) {
		if (result.hasErrors()) {
			return "edit-movie";
		}
		dao.save(m);
		return "redirect:/app";
		//return "redirect:/movie/" + m.getId();
	}

}
