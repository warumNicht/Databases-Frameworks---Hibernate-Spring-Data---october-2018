package gamestore.domain.dto;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class GameAddDto {
    @NotNull(message = "Title cannot be null!")
    @Size(min = 3, max = 100, message = "Title must have between 3 and 100 symbols!")
    @Pattern(regexp = "^[A-Z].*$", message = "Title should begin with uppercase letter!")
    private String title;

    @Min(value = 0,message = "The price must be positive!")
    @Digits(integer = 5, fraction = 2)
    private BigDecimal price;

    @Min(value = 0,message = "The size must be positive!")
    @Digits(integer = 5, fraction = 1)
    private Double size;

    @Size(min = 11, max = 11,message = "Trailers length must be exactly 11 symbols!")
    private String trailer;


    @Pattern(regexp = "^(http:\\/\\/|https:\\/\\/).*$", message = "Invalid URL!")
    private String thumbnailUrl;

    @Size(min = 20, message = "Description should be min 20 symbols!")
    private String description;

    private LocalDate releaseDate;

    public GameAddDto(String title, BigDecimal price, Double size, String trailer,
                      String thumbnailUrl, String description, LocalDate releaseDate) {
        this.title = title;
        this.price = price;
        this.size = size;
        this.trailer = trailer;
        this.thumbnailUrl = thumbnailUrl;
        this.description = description;
        this.releaseDate = releaseDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
