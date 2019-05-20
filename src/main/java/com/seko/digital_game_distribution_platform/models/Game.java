package com.seko.digital_game_distribution_platform.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table ( name = "games" )
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Game
{
	@Id
	@GeneratedValue ( strategy = GenerationType.IDENTITY )
	private Long id;

	@NotBlank
	@Size ( max = 200 )
	private String name;

	@NotBlank
	@Size ( max = 10000 )
	@Column ( columnDefinition = "TEXT" )
	private String description;

	@Column ( columnDefinition = "TEXT" )
	private String pathToImage;

	@Column ( columnDefinition = "TEXT" )
	private String pathToSetupFile;

	@NotBlank
	@Size ( max = 200 )
	private String author;

	@NotBlank
	@Size ( max = 25 )
	private String version;

	@NotBlank
	private String platform;

	@Temporal ( TemporalType.DATE )
	@PastOrPresent
	private Date releaseDate;

	@NumberFormat ( style = NumberFormat.Style.CURRENCY )
	@PositiveOrZero
	private BigDecimal price;

	public enum Platform
	{
		WINDOWS, LINUX, MACOS
	}
}
