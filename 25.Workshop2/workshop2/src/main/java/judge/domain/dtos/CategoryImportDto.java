package judge.domain.dtos;

import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CategoryImportDto {
    private Integer id;

    @NotNull
    @Size(min = 4)
    @Pattern(regexp = "^[A-Z].*$")
    private String name;

    @SerializedName(value = "category")
    private CategoryImportDto parentCategory;

    @SerializedName(value = "categories")
    private CategoryImportDto[] subCategories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryImportDto getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(CategoryImportDto parentCategory) {
        this.parentCategory = parentCategory;
    }

    public CategoryImportDto[] getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(CategoryImportDto[] subCategories) {
        this.subCategories = subCategories;
    }
}
