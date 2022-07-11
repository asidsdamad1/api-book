package com.example.deploy.service.impl;

import com.example.deploy.domain.Book;
import com.example.deploy.domain.BookCategory;
import com.example.deploy.domain.Category;
import com.example.deploy.domain.Publisher;
import com.example.deploy.dto.BookCategoryDto;
import com.example.deploy.dto.BookDto;
import com.example.deploy.functiondto.BookSearchDto;
import com.example.deploy.repository.BookCategoryRepository;
import com.example.deploy.repository.BookRepository;
import com.example.deploy.repository.CategoryRepository;
import com.example.deploy.repository.PublisherRepository;
import com.example.deploy.service.BookService;
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
public class BookServiceImpl implements BookService {
    @Autowired
    public EntityManager manager;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    PublisherRepository publisherRepository;
    @Autowired
    BookCategoryRepository bookCategoryItemRepo;
    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public BookDto saveOrUpdate(BookDto dto, UUID id) {
        if(dto != null) {
            Book entity = null;

            if(id != null)  {
                bookRepository.findById(id).orElse(null);
            }
            if(entity == null) {
                entity = new Book();
            }
            entity.setCode(dto.getCode());
            entity.setName(dto.getName());
            entity.setAuthor(dto.getAuthor());
            entity.setQuantity(dto.getQuantity());

            Publisher publisher = null;
            if(dto.getPublisher().getId() != null && dto.getPublisher() != null) {
                publisher = publisherRepository.getById(dto.getPublisher().getId());
            }
            if(publisher==null) {
                return null;
            }

            entity.setPublisher(publisher);

            List<BookCategory> setItems = new ArrayList<>();
            if(dto.getBookCategoryItems() != null && dto.getBookCategoryItems().size()>0) {
                for(BookCategoryDto itemDto: dto.getBookCategoryItems()) {
                    BookCategory item = null;
                    if(itemDto.getId() != null) {
                        item = bookCategoryItemRepo.getById(itemDto.getId());
                    } else {
                        item = new BookCategory();
                    }

                    Category category = null;
                    if(itemDto.getCategory()  != null  && itemDto.getCategory().getId() != null) {
                        category = categoryRepository.getById(itemDto.getCategory().getId());
                    }

                    item.setCategory(category);
                    item.setBook(entity);
                    setItems.add(item);
                }
            }

            if(entity.getBookCategoryItems() == null) {
                entity.setBookCategoryItems(setItems);
            }  else {
                entity.getBookCategoryItems().clear();
                entity.getBookCategoryItems().addAll(setItems);
            }

            entity = bookRepository.save(entity);

            if(entity!=null) {
                return new BookDto(entity);
            }
        }
        return null;
    }

    @Override
    public List<BookDto> getAll() {
        return null;
    }

    @Override
    public Page<BookDto> searchByPage(BookSearchDto dto) {
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
        String sqlCount = "select count(e.id) from Book e where (1=1) ";
        String sql = "select new com.example.deploy.dto.BookDto(e, true) from Book as e where(1=1)";
        if(StringUtils.hasText(dto.getText())) {
            whereClause += "AND (e.code like :text or" +
                    "e.name like :text or" +
                    "e.author like :text or" +
                    "e.quantity like =:text or" +
                    " )";
        }

        sql += whereClause + orderBy;
        Query q = manager.createQuery(sql, BookDto.class);
        Query qCount = manager.createQuery(sqlCount);

        if (StringUtils.hasText(dto.getText())) {
            q.setParameter("text", '%' + dto.getText().trim() + '%');
            qCount.setParameter("text", '%' + dto.getText().trim() + '%');
        }

        int startPosition =pageIndex*pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);
        List<BookDto> entities = q.getResultList();

        long count = (long) qCount.getSingleResult();
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        return new PageImpl<>(entities, pageable, count);
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            Book entity = bookRepository.getById(id);
            if (entity != null) {
                bookRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }

    @Override
    public BookDto getByCode(String code) {
        if(StringUtils.hasText(code)) {
            List<Book> entities = bookRepository.getByCode(code);
            if(entities != null && !entities.isEmpty()) {
                return new BookDto(entities.get(0));
            }
        }
        return null;
    }

    @Override
    public BookDto getById(UUID id) {
        Book entity = bookRepository.getById(id);
        if(entity != null) {
            return new BookDto(entity);
        }

        return null;
    }

    @Override
    public Boolean checkDuplicateCode(String code, UUID id) {
        if(code != null && StringUtils.hasText(code)) {
            Long count = bookRepository.checkCode(code, id);
            return count!=0l;
        }
        return null;
    }
}
