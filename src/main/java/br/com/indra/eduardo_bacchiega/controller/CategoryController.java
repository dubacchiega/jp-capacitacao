package br.com.indra.eduardo_bacchiega.controller;

import br.com.indra.eduardo_bacchiega.docs.CategoryControllerDoc;
import br.com.indra.eduardo_bacchiega.dto.CategoryRequestDto;
import br.com.indra.eduardo_bacchiega.dto.CategoryResponseDto;
import br.com.indra.eduardo_bacchiega.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController implements CategoryControllerDoc {

    private final CategoryService categoryService;

    @Override
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<CategoryResponseDto> getAll(){
        return categoryService.getCategory();
    }

    @Override
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponseDto createCategory(@RequestBody CategoryRequestDto request){
        return categoryService.createCategory(request);
    }

    @Override
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public CategoryResponseDto updateCategory(@RequestBody CategoryRequestDto request, @PathVariable Long id){
        return categoryService.updateCategory(id, request);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }
}
