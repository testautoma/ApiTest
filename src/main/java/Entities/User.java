package Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true) //тогда не нужно описывать все поля, которые есть в json.
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User{
    @JsonProperty("firstName")
	private String firstName;
	private String lastName;
	private String password;
	private int userStatus;
	private String phone;
	private long id;
	private String email;
	private String username;

	public boolean equalWithoutIDTo(User user) {
		return Objects.equals(firstName, user.firstName)
				&& Objects.equals(lastName, user.lastName)
				&& Objects.equals(password, user.password)
				&& userStatus == user.userStatus
				&& Objects.equals(phone, user.phone)
				&& Objects.equals(email, user.email)
				&& Objects.equals(username, user.username);
	}
}
