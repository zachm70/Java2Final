package dmacc.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Member {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String address;
	private String city;
	private double fine;
}
