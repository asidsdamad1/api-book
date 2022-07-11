package com.example.deploy.service.impl;

import com.example.deploy.domain.Book;
import com.example.deploy.domain.BookCategory;
import com.example.deploy.domain.Category;
import com.example.deploy.dto.BookCategoryDto;
import com.example.deploy.dto.CategoryDto;
import com.example.deploy.functiondto.SearchDto;
import com.example.deploy.repository.BookCategoryRepository;
import com.example.deploy.repository.BookRepository;
import com.example.deploy.repository.CategoryRepository;
import com.example.deploy.repository.PublisherRepository;
import com.example.deploy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    CategoryRepository categoryRepo;
    @Autowired
    public EntityManager manager;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    PublisherRepository publisherRepository;
    @Autowired
    BookCategoryRepository bookCategoryItemRepo;

    @Override
    public CategoryDto saveOrUpdate(CategoryDto dto, UUID id) {
        if(dto != null) {
            Category entity = null;

            if(id!=null) {
                categoryRepo.findById(id).orElse(null);
            }
            if(entity==null) {
                entity = new Category();
            }

            entity.setCode(dto.getCode());
            entity.setName(dto.getName());
            entity.setDescription(dto.getDescription());
            List<BookCategory> setItems = new ArrayList<>();
            if(dto.getBookCategoryItem() != null && dto.getBookCategoryItem().size()>0) {
                for(BookCategoryDto itemDto: dto.getBookCategoryItem()) {
                    BookCategory item = null;
                    if(itemDto.getId() != null) {
                        item = bookCategoryItemRepo.getById(itemDto.getId());
                    } else {
                        item = new BookCategory();
                    }

                    Book book = null;
                    if(itemDto.getBook()  != null  && itemDto.getBook().getId() != null) {
                        book  = bookRepository.getById(itemDto.getBook().getId());
                    }
                    item.setBook(book);
                    setItems.add(item);
                }
            }

            if(entity.getBookCategoryItem() == null) {
                entity.setBookCategoryItem(setItems);
            }  else {
                entity.getBookCategoryItem().clear();
                entity.getBookCategoryItem().addAll(setItems);
            }

            entity = categoryRepo.save(entity);

            if(entity!=null) {
                return new CategoryDto(entity);
            }
        }
        return null;
    }

    @Override
    public List<CategoryDto> getAll() {
        return null;
    }

    @Override
    public Page<CategoryDto> searchByPage(SearchDto dto) {
        if(dto == null) {
            return null;
        }

        int pageIndex = dto.getPageIndex();
        int pageSize = dto.getPageSize();

        String whereClause = "";
        String orderBy = "";
        String sqlCount = "select count(e.id) from Category e where (1=1)";
        String sql = "select com.example.deploy.dto.CategoryDto(e) from Category e where (1=1)";

        if(StringUtils.hasText(dto.getText())) {
            whereClause += "and ( ) ";
        }

        sql += whereClause + orderBy;
        Query q = manager.createQuery(sql, CategoryDto.class);
        Query qCount = manager.createQuery(sqlCount);

        if(StringUtils.hasText(dto.getText())) {
            q.setParameter("text", "%" + dto.getText().trim() + "%");
            qCount.setParameter("text", "%" + dto.getText().trim() + "%");
        }

        int startPosition = pageIndex*pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);

        List<CategoryDto> entities = q.getResultList();

        long count = (long) qCount.getSingleResult();
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        return new PageImpl<>(entities, pageable, count);
    }

    @Override
    public Boolean delete(UUID id) {
        if(id != null) {
            categoryRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public CategoryDto getByCode(String code) {
        return null;
    }

    @Override
    public CategoryDto getById(UUID id) {
        Category entity = categoryRepo.getById(id);
        if(entity != null) {
            return new CategoryDto(entity);
        }
        return null;
    }

    @Override
    public Boolean checkDuplicateCode(String code, UUID id) {
        return null;
    }
}
