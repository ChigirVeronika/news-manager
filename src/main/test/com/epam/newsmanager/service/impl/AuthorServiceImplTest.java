package com.epam.newsmanager.service.impl;

import com.epam.newsmanager.dao.impl.AuthorDao;
import com.epam.newsmanager.entity.Author;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

/**
 * Test class with Mockito Framework for AuthorServiceImpl class.
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceImplTest {

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Mock
    private AuthorDao authorDao;

    @BeforeMethod
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInsertAuthor() throws Exception {
        Author author = new Author();
        author.setAuthorId(1);
        when(authorDao.insert(author)).thenReturn(Long.valueOf(1));
        authorService.insertAuthor(author);
        Assert.assertEquals(1, author.getAuthorId());
    }

    @Test
    public void testUpdateAuthor() throws Exception {

    }

    @Test
    public void testDeleteAuthor() throws Exception {

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
        when(authorDao.getById(1)).thenReturn(author);
        Author newAuthor = authorService.getAuthorById(1);
        Assert.assertEquals(author, newAuthor);
    }

    @Test
    public void testGetAuthorByNewsId() throws Exception {
        Set<Author> authors = new HashSet<Author>();
        authors.add(new Author());
        when(authorDao.getByNewsId(1)).thenReturn(authors);
        Set<Author> newAuthors = authorDao.getByNewsId(1);
        Assert.assertEquals(authors, newAuthors);
    }

    @Test
    public void testGetAuthorByName() throws Exception {
        Author author = new Author();
        author.setAuthorName("Alina");
        when(authorDao.getByName(author)).thenReturn(author);
        Author newAuthor = authorService.getAuthorByName(author);
        Assert.assertEquals(author, newAuthor);
    }
}