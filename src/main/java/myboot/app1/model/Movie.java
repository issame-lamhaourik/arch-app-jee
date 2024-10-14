package myboot.app1.model;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import myboot.app3.View;

@NamedQuery(name = "findAllMovies", query = "SELECT m FROM Movie m")

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(View.Public.class)
	private int id = 0;

	@Basic
	@NotBlank(message = "{movie.name}")
	@JsonView(View.Public.class)
	private String name;

	@Basic
	@Min(value = 1900, message = "{movie.year}")
	@Max(value = 2100, message = "{movie.year}")
	@JsonView(View.Public.class)
	private int year;

	@Basic
	@Size(max = 200, message = "{movie.description}")
	@JsonView(View.Internal.class)
	private String description;

	public Movie(String name, int year, String description) {
		this(0, name, year, description);
	}

}
