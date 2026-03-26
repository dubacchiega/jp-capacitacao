package br.com.indra.eduardo_bacchiega.category.service;

import br.com.indra.eduardo_bacchiega.category.dto.CategoryRequestDto;
import br.com.indra.eduardo_bacchiega.category.dto.CategoryResponseDto;
import br.com.indra.eduardo_bacchiega.exception.CategoryExistsException;
import br.com.indra.eduardo_bacchiega.exception.CategoryNotFoundException;
import br.com.indra.eduardo_bacchiega.category.mapper.CategoryMapper;
import br.com.indra.eduardo_bacchiega.category.model.Category;
import br.com.indra.eduardo_bacchiega.category.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public List<CategoryResponseDto> getCategory(){
        List<Category> all = categoryRepository.findAll();
        return all.stream().map(
                CategoryMapper::toDto
        ).toList();
    }

    @Transactional
    public CategoryResponseDto createCategory(CategoryRequestDto request){
        categoryRepository.findByName(request.name()).ifPresent(
                (e) -> {throw new CategoryExistsException("Category already exists");}
        );

        Category parent = null;
        if(request.parentId() != null){
            parent = categoryRepository.findById(request.parentId()).orElseThrow(
                    () -> new CategoryNotFoundException("Parent not found")
            );
        }

        Category newCategory = new Category(request.name(), parent);
        categoryRepository.save(newCategory);

        return CategoryMapper.toDto(newCategory);
    }

    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto request){
        categoryRepository.findByName(request.name()).ifPresent(
                (e) -> {throw new CategoryExistsException("Category already exists");}
        );

        Category oldCategory = categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Category not found")
        );
        Category parentCategory = categoryRepository.findById(request.parentId()).orElseThrow(() -> new CategoryNotFoundException("Parent not found"));
        oldCategory.setName(request.name());
        oldCategory.setParent(parentCategory);

        Category newCategory = categoryRepository.saveAndFlush(oldCategory);
        return CategoryMapper.toDto(newCategory);
    }

    @Transactional
    public void deleteCategory(Long id){
        categoryRepository.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Category not found")
        );

        categoryRepository.deleteById(id);
    }
}
