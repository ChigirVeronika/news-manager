package com.epam.newsmanager.dao.impl;

import com.epam.newsmanager.dao.AuthorDao;
import com.epam.newsmanager.entity.Author;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class with BDUnit technology for AuthorDaoImpl class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:test-db.xml" })
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class})
@DatabaseSetup("classpath:xmldata/authorData.xml")
@DatabaseTearDown(value = { "classpath:xmldata/authorData.xml" }, type = DatabaseOperation.DELETE)
public class AuthorDaoImplTest {

    @Autowired
    private AuthorDao authorDao;

    @Transactional
    @Rollback(true)
    @Test
    public void testInsert() throws Exception {
        Author author = new Author();
        author.setAuthorName("Test");
        authorDao.insert(author);
        Assert.assertNotEquals(0, author.getAuthorId());
    }

    @ExpectedDatabase(value = "classpath:xmldata/authorDataUpdate.xml", assertionMode = DatabaseAssertionMode.NON_STRICT_UNORDERED)
    @DatabaseTearDown(value = { "classpath:xmldata/authorDataUpdate.xml" }, type = DatabaseOperation.DELETE)
    @Test
    public void testUpdate() throws Exception {
        Author author = new Author();
        author.setAuthorId(1);
        author.setAuthorName("Test");
        authorDao.update(author);
        Assert.assertEquals(authorDao.getById(1L), author);
    }

    @DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
    @Test
    public void testDelete() throws Exception {
        authorDao.delete(1L);
        Assert.assertEquals(authorDao.getById(1L), null);
    }

    @DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
    @Test
    public void testGetAll() throws Exception {
        Author author = new Author();
        author.setAuthorId(1);
        author.setAuthorName("John Smith");

        Author author1 = new Author();
        author1.setAuthorId(2);
        author1.setAuthorName("Joe Bloggs");

        Set<Author> searchResults = authorDao.getAll();
        assertThat(searchResults).contains(author, author1);
    }

    @DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
    @Test
    public void testGetById() throws Exception {
        Author author = new Author();
        author.setAuthorId(1);
        author.setAuthorName("John Smith");//jane roe
        Assert.assertEquals(authorDao.getById(1L), author);
    }

    @DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
    @Test
    public void testGetByNewsId() throws Exception {
        Set<Author> authors = new HashSet<Author>();

        Author author = new Author();
        author.setAuthorId(1);
        author.setAuthorName("John Smith");

        Author author1 = new Author();
        author1.setAuthorId(2);
        author1.setAuthorName("Joe Bloggs");

        authors.add(author);
        authors.add(author1);

        Assert.assertEquals(authors, authorDao.getByNewsId(1L));
    }

    @DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
    @Test
    public void testGetByName() throws Exception {
        Author author = new Author();
        author.setAuthorId(1);
        author.setAuthorName("John Smith");
        Assert.assertEquals(author, authorDao.getByName(author));
    }
}