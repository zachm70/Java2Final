package dmacc.beans;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Book {
	@Id
	@GeneratedValue
	private long id;
	private String title;
	private String author;
	private int checkOutStatus;
}
