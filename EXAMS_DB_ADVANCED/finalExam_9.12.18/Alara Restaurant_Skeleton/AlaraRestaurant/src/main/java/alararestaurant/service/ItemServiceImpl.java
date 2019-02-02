package alararestaurant.service;

import alararestaurant.domain.dtos.importDtos.ItemImportDto;
import alararestaurant.domain.entities.Category;
import alararestaurant.domain.entities.Item;
import alararestaurant.repository.CategoryRepository;
import alararestaurant.repository.ItemRepository;
import alararestaurant.util.Constants;
import alararestaurant.util.FileUtil;
import alararestaurant.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;

@Service
public class ItemServiceImpl implements ItemService {
    private final static  String ITEMS_FILE_PATH="src\\main\\resources\\files\\items.json";


    private final ItemRepository itemRepository;
    private final CategoryRepository categoryRepository;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper mapper;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, CategoryRepository categoryRepository,
                           FileUtil fileUtil, Gson gson, ValidationUtil validationUtil, ModelMapper mapper) {
        this.itemRepository = itemRepository;
        this.categoryRepository = categoryRepository;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public Boolean itemsAreImported() {
        // TODO : Implement me

        return this.itemRepository.count() > 0;
    }

    @Override
    public String readItemsJsonFile() throws IOException {
        // TODO : Implement me
        String content = this.fileUtil.readFile(ITEMS_FILE_PATH);
        return content;
    }

    @Override
    public String importItems(String items) {
        // TODO : Implement me
        ItemImportDto[] itemImportDtos = this.gson.fromJson(items, ItemImportDto[].class);
        StringBuilder res=new StringBuilder();

        for (ItemImportDto itemImportDto : itemImportDtos) {
            if(!this.validationUtil.isValid(itemImportDto)){
                res.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            if(this.itemRepository.existsByName(itemImportDto.getName())){
                res.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            if(!this.categoryRepository.existsByName(itemImportDto.getCategory())){
                Category category=new Category();
                category.setName(itemImportDto.getCategory());
                this.categoryRepository.saveAndFlush(category);
            }
            Category category = this.categoryRepository.findByName(itemImportDto.getCategory()).orElse(null);

            Item item = this.mapper.map(itemImportDto, Item.class);
            item.setOrderItems(new ArrayList<>());
            item.setCategory(category);

            this.itemRepository.saveAndFlush(item);

            res.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE,item.getName()))
                    .append(System.lineSeparator());

        }
        return res.toString().trim();
    }
}
