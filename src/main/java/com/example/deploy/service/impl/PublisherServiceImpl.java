package com.example.deploy.service.impl;

import com.example.deploy.domain.Book;
import com.example.deploy.domain.Publisher;
import com.example.deploy.dto.BookDto;
import com.example.deploy.dto.PublisherDto;
import com.example.deploy.functiondto.SearchDto;
import com.example.deploy.repository.BookRepository;
import com.example.deploy.repository.PublisherRepository;
import com.example.deploy.service.PublisherService;
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
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    PublisherRepository publisherRepos;
    @Autowired
    BookRepository bookRepos;
    @Autowired
    EntityManager manager;

    @Override
    public PublisherDto saveOrUpdate(PublisherDto dto, UUID id) {
        if(dto != null) {
            Publisher entity = null;

            if(id != null) {
                entity = publisherRepos.getById(id);
            }
            if(entity==null) {
                entity = new Publisher();
            }

            entity.setCode(dto.getCode());
            entity.setName(dto.getName());
            List<Book> setItems = new ArrayList<>();
            if(dto.getBooks() !=null && dto.getBooks().size() > 0) {
                for(BookDto itemDto: dto.getBooks()) {
                    Book item = null;
                    if(itemDto.getId() != null) {
                        item = bookRepos.getById(itemDto.getId());
                    } else {
                        item = new Book();
                    }
                    item.setPublisher(entity);
                    setItems.add(item);
                }
            }
            if(entity.getBooks() == null) {
                entity.setBooks(setItems);
            } else {
                entity.getBooks().clear();
                entity.getBooks().addAll(setItems);
            }
            entity = publisherRepos.save(entity);

            if(entity != null) {
                return new PublisherDto(entity);
            }
        }
        return null;
    }

    @Override
    public Page<PublisherDto> searchByPage(SearchDto dto) {
        if(dto == null) return null;

        int pageIndex = dto.getPageIndex();
        int pageSize =dto.getPageSize();

        String whereClause = "";
        String orderBy = "";
        String sqlCount = "select count(e.id) from Publisher e where (1=1)";
        String sql = "select new.com.example.deploy.dto.PubliserDto(e, true) from Publisher e where (1=1)";

        if(StringUtils.hasText(dto.getText())) {
            whereClause += "(and e.code like :code" +
                    "or e.name like :name)";
        }
        sql += whereClause + orderBy;
        Query q = manager.createQuery(sql, PublisherDto.class);
        Query qCount = manager.createQuery(sqlCount);

        int startPosition =pageIndex*pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);
        List<PublisherDto> entities = new ArrayList<>();

        long count = (long)qCount.getSingleResult();
        Pageable pageable = PageRequest.of(pageIndex, pageSize);

        return new PageImpl<PublisherDto>(entities, pageable, count);
    }

    @Override
    public Boolean delete(UUID id) {
        if(id != null) {
            publisherRepos.deleteById(id);
            return true;
        }
        return null;
    }

    @Override
    public PublisherDto getById(UUID id) {
        Publisher entity = publisherRepos.getById(id);
        if(entity != null) {
            return new PublisherDto(entity);
        }
        return null;
    }

    @Override
    public PublisherDto getByCode(String code) {
        return null;
    }

    @Override
    public PublisherDto checkCode(String code, UUID id) {
        return null;
    }
}
