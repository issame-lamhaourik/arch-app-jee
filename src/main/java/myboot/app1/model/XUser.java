package myboot.app1.model;

import java.util.Set;

import jakarta.persistence.Basic;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class XUser {

	@Id
	String userName;

	@Basic
	String password;

	@ElementCollection(fetch = FetchType.EAGER)
	Set<String> roles;

}
