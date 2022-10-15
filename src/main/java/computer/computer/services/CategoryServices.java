package computer.computer.services;

import computer.computer.modelo.Category;

import computer.computer.repositorio.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServices {
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAll() {
        return categoryRepository.getAll();


    }
    public Optional<Category> getCategory(int id){
        return categoryRepository.getCategory(id);
    }

    public Category save(Category category) {
        if (category.getId() == null) {
            return categoryRepository.save(category);


        } else {
            Optional<Category> opt = categoryRepository.getCategory(category.getId());
            if (opt.isEmpty()) {
                return categoryRepository.save(category);

            } else {

                return category;
            }
        }
    }
    public Category update (Category category){
        if(category.getId()!=null){
            Optional <Category> op = categoryRepository.getCategory(category.getId());
            if (op.isPresent()){
                if (category.getName()!=null){
                    op.get().setName(category.getName());

                }
                if (category.getDescription()!=null){
                    op.get().setDescription(category.getDescription());

                }

                categoryRepository.save(op.get());
                return op.get();

            }else {
                return category;
            }
        }else {
            return category;
        }
    }
    public boolean delete(int id){
        boolean flag=false;
        Optional<Category>category= categoryRepository.getCategory(id);
        if (category.isPresent()){
            categoryRepository.delete(category.get());
            flag=true;
        }
        return flag;
    }
}