package com.epam.newsmanager.service.impl;

import com.epam.newsmanager.dao.impl.AuthorDaoImpl;
import com.epam.newsmanager.entity.Author;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class with Mockito Framework for <cod>AuthorServiceImpl</cod> class.
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceImplTest {

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Mock
    private AuthorDaoImpl authorDao;

    @BeforeMethod
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInsertAuthor() throws Exception {
        Author author = new Author();
        author.setAuthorId(1L);
        when(authorDao.insert(author)).thenReturn(1L);
        authorService.insertAuthor(author);
        Assert.assertEquals(1, author.getAuthorId());
    }

    @Test
    public void testDeleteAuthor() throws Exception {
        Author author = new Author();
        author.setAuthorId(1L);
        when(authorService.getAuthorById(1L)).thenReturn(author);

        authorService.insertAuthor(author);
        authorService.deleteAuthor(1L);
        verify(authorDao, times(1)).insert(any(Author.class));
        verify(authorDao, times(1)).delete(any(Long.class));
    }

    @Test
    public void testGetAllAuthors() throws Exception {
        Set<Author> authors = new HashSet<Author>();
        authors.add(new Author());
        when(authorDao.getAll()).thenReturn(authors);

        Set<Author> newAuthors = authorDao.getAll();
        Assert.assertEquals(authors, newAuthors);
    }

    @Test
    public void testGetAuthorById() throws Exception {
        Author author = new Author();
        author.setAuthorId(1);
        when(authorDao.getById(1l)).thenReturn(author);
        Author newAuthor = authorService.getAuthorById(1l);
        Assert.assertEquals(author, newAuthor);
    }

    @Test
    public void testGetAuthorByNewsId() throws Exception {
        Set<Author> authors = new HashSet<Author>();
        authors.add(new Author());
        when(authorDao.getByNewsId(1L)).thenReturn(authors);
        Set<Author> newAuthors = authorDao.getByNewsId(1L);
        Assert.assertEquals(authors, newAuthors);
    }

    @Test
    public void testGetAuthorByName() throws Exception {
        Author author = new Author();
        author.setAuthorName("Veronika");
        when(authorDao.getByName(author)).thenReturn(author);
        Author newAuthor = authorService.getAuthorByName(author);
        Assert.assertEquals(author, newAuthor);
    }
}