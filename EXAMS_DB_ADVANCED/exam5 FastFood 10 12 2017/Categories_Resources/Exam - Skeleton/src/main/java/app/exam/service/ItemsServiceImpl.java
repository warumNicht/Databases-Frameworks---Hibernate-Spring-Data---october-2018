package app.exam.service;

import app.exam.domain.dto.json.ItemJSONImportDTO;
import app.exam.domain.entities.Category;
import app.exam.domain.entities.Item;
import app.exam.parser.interfaces.ModelParser;
import app.exam.parser.interfaces.ValidationUtil;
import app.exam.repository.CategoryRepository;
import app.exam.repository.ItemsRepository;
import app.exam.service.api.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemsServiceImpl implements ItemsService {
    private ItemsRepository itemsRepository;
    private CategoryRepository categoryRepository;
    private ValidationUtil validationUtil;
    private ModelParser mapper;
@Autowired
    public ItemsServiceImpl(ItemsRepository itemsRepository, CategoryRepository categoryRepository, ValidationUtil validationUtil, ModelParser mapper) {
        this.itemsRepository = itemsRepository;
    this.categoryRepository = categoryRepository;
    this.validationUtil = validationUtil;
        this.mapper = mapper;
    }

    @Override
    public void create(ItemJSONImportDTO itemJSONImportDTO) {
        Item item = this.mapper.convert(itemJSONImportDTO, Item.class);
        if(!this.categoryRepository.existsByName(itemJSONImportDTO.getCategory())){
            Category category=new Category();
            category.setName(itemJSONImportDTO.getCategory());
            this.categoryRepository.saveAndFlush(category);
        }
        Category category = this.categoryRepository.findByName(itemJSONImportDTO.getCategory());
        item.setCategory(category);
        this.itemsRepository.saveAndFlush(item);
    }
}
