package br.com.indra.eduardo_bacchiega.controller;

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
public class CategoryController {

    private final CategoryService categoryService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<CategoryResponseDto> getAll(){
        return categoryService.getCategory();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CategoryResponseDto createCategory(@RequestBody CategoryRequestDto request){
        return categoryService.createCategory(request);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public CategoryResponseDto updateCategory(@RequestBody CategoryRequestDto request, @PathVariable Long id){
        return categoryService.updateCategory(id, request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
    }
}
