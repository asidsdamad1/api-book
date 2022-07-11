package com.example.deploy.service.impl;

import com.example.deploy.domain.Book;
import com.example.deploy.domain.BookCategory;
import com.example.deploy.domain.Category;
import com.example.deploy.dto.BookCategoryDto;
import com.example.deploy.dto.BookDto;
import com.example.deploy.functiondto.BookSearchDto;
import com.example.deploy.functiondto.SearchDto;
import com.example.deploy.repository.BookCategoryRepository;
import com.example.deploy.repository.BookRepository;
import com.example.deploy.repository.CategoryRepository;
import com.example.deploy.service.BookCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {
    @Autowired
    BookCategoryRepository bookCategoryRepos;
    @Autowired
    BookRepository bookRepos;
    @Autowired
    CategoryRepository categoryRepos;
    @Autowired
    EntityManager manager;

    @Override
    public BookCategoryDto saveOrUpdate(BookCategoryDto dto, UUID id) {
        if(dto != null) {
            BookCategory entity = null;

            if(id != null) {
                entity = bookCategoryRepos.getById(id);
            }
            if(entity == null) {
                entity = new BookCategory();
            }

            Book book = null;
            if(dto.getBook() != null && dto.getBook().getId()!= null) {
                book = bookRepos.getById(dto.getBook().getId());
            }
            if(book == null) {
                return null;
            }
            entity.setBook(book);

            Category category = null;
            if(dto.getCategory() != null && dto.getCategory().getId()!= null) {
                category = categoryRepos.getById(dto.getCategory().getId());
            }
            if(category == null) {
                return null;
            }
            entity.setCategory(category);

            entity = bookCategoryRepos.save(entity);
            if (entity != null) {
                return new BookCategoryDto(entity, false);
            }
        }
        return null;
    }

    @Override
    public Page<BookCategoryDto> searchByPage(SearchDto dto) {
        if(dto == null) {
            return null;
        }
        int pageIndex = dto.getPageIndex();
        int pageSize = dto.getPageSize();

        if(pageIndex>0){
            pageIndex--;
        } else {
            pageIndex = 0;
        }

        String whereClause = "";
        String orderBy = " ";
        String sqlCount = "select count(e.id) from BookCategory e where (1=1) ";
        String sql = "select new com.example.deploy.dto.BookCategoryDto(e, false) from BookCategory as e where(1=1)";
        if(StringUtils.hasText(dto.getText())) {
//            whereClause += "";
        }

        sql += whereClause + orderBy;
        Query q = manager.createQuery(sql, BookCategoryDto.class);
        Query qCount = manager.createQuery(sqlCount);

        if (StringUtils.hasText(dto.getText())) {
            q.setParameter("text", '%' + dto.getText().trim() + '%');
            qCount.setParameter("text", '%' + dto.getText().trim() + '%');
        }

        int startPosition =pageIndex*pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);
        List<BookCategoryDto> entities = q.getResultList();

        long count = (long) qCount.getSingleResult();
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        return new PageImpl<BookCategoryDto>(entities, pageable, count);
    }

    @Override
    public Boolean deletebyId(UUID id) {
        if(id != null) {
            bookCategoryRepos.deleteById(id);
            return true;
        }
        return null;
    }

    @Override
    public BookCategoryDto getById(UUID id) {
        BookCategory entity = bookCategoryRepos.getById(id);
        if(entity != null) {
            return new BookCategoryDto(entity, false);
        }
        return null;
    }
}
