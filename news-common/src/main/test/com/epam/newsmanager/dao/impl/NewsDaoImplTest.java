package com.epam.newsmanager.dao.impl;

import com.epam.newsmanager.dao.AuthorDao;
import com.epam.newsmanager.dao.NewsDao;
import com.epam.newsmanager.dao.TagDao;
import com.epam.newsmanager.entity.Author;
import com.epam.newsmanager.entity.News;
import com.epam.newsmanager.entity.Tag;
import com.epam.newsmanager.entity.dto.SearchNewsDto;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class with BDUnit technology for NewsDaoImpl class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-test.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
public class NewsDaoImplTest {

    @Autowired
    private NewsDao newsDao;

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private TagDao tagDao;

    @Transactional
    @Rollback(true)
    @Test
    public void testInsert() throws Exception {
        News news = new News();
        news.setTitle("Title");
        news.setShortText("Short text");
        news.setFullText("Full text");
        news.setCreationDate(new Timestamp(new Date().getTime()));
        news.setModificationDate(new java.sql.Date(new Date().getTime()));
        newsDao.insert(news);
        Assert.assertNotEquals(news.getNewsId(), 0);
    }

    @DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
    @Test
    public void testUpdate() throws Exception {
        News news = new News();
        news.setNewsId(1);
        news.setTitle("Title");
        news.setShortText("Short text");
        news.setFullText("Full text");
        news.setCreationDate(new Timestamp(new Date().getTime()));
        news.setModificationDate(new java.sql.Date(new Date().getTime()));
        newsDao.update(news);
        Assert.assertEquals(newsDao.getById(1L), news);
    }

    @DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
    @Test
    public void testDelete() throws Exception {
        newsDao.delete(1L);
        Assert.assertEquals(newsDao.getById(1L), null);
    }

    @DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
    @Test
    public void testGetAll() throws Exception {
        News news = new News();
        news.setNewsId(1);
        news.setTitle("Test");
        news.setShortText("Short text");
        news.setFullText("Full text");
        news.setCreationDate(Timestamp.valueOf("1996-06-20 20:15:11"));
        news.setModificationDate(new java.sql.Date(Timestamp.valueOf("1996-06-20 20:15:11").getTime()));
        News news1 = new News();
        news1.setNewsId(2);
        news1.setTitle("Test2");
        news1.setShortText("Short text");
        news1.setFullText("Full text");
        news1.setCreationDate(Timestamp.valueOf("2016-06-20 20:15:11"));
        news1.setModificationDate(new java.sql.Date(Timestamp.valueOf("1999-06-20 20:15:11").getTime()));
        assertThat(newsDao.getAll()).contains(news, news1);
    }

    @DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
    @Test
    public void testGetById() throws Exception {
        News news = new News();
        news.setNewsId(1);
        news.setTitle("Test");
        news.setShortText("Short text");
        news.setFullText("Full text");
        news.setCreationDate(Timestamp.valueOf("1996-06-20 20:15:11"));
        news.setModificationDate(new java.sql.Date(Timestamp.valueOf("1996-06-20 20:15:11").getTime()));
        Assert.assertEquals(newsDao.getById(1L), news);
    }

    @DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
    @Test
    public void testSearchNews() throws Exception {
        SearchNewsDto dto= new SearchNewsDto();
        Set<Tag> tags = new HashSet<Tag>(1);
        Tag tag = new Tag();
        tag.setTagId(1);
        tag.setTagName("Religion");
        tags.add(tag);
        dto.setTags(tags);
        Author author = new Author();
        author.setAuthorId(1);
        author.setAuthorName("Ivan Ivanov");
        dto.setAuthor(author);
        News news = new News();
        news.setNewsId(1);
        news.setTitle("Test");
        news.setShortText("Short text");
        news.setFullText("Full text");
        news.setCreationDate(Timestamp.valueOf("1996-06-20 20:15:11"));
        news.setModificationDate(new java.sql.Date(Timestamp.valueOf("1996-06-20 20:15:11").getTime()));
        assertThat(newsDao.searchNews(dto)).contains(news);
    }

    @DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
    @Test
    public void testGetNumberOfNews() throws Exception {
        int number = newsDao.getNumberOfNews();
        Assert.assertEquals(number, newsDao.getAll().size());
    }

    @Transactional
    @Rollback(true)
    @DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
    @Test
    public void testInsertNewsAuthors() throws Exception {
        Set<Author> authors = new HashSet<Author>(1);
        Author author = new Author();
        author.setAuthorId(3);
        author.setAuthorName("Kto-to");
        authors.add(author);
        newsDao.insertNewsAuthors(1L,authors);
        assertThat(authorDao.getByNewsId(1L)).contains(author);
    }

    @Transactional
    @Rollback(true)
    @DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
    @Test
    public void testInsertNewsTags() throws Exception {
        Set<Tag> tags = new HashSet<Tag>(1);
        Tag tag = new Tag();
        tag.setTagId(3);
        tag.setTagName("Russia");
        tags.add(tag);
        newsDao.insertNewsTags(1L, tags);
        assertThat(tagDao.getByNewsId(1L)).contains(tag);
    }

    @DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
    @Test
    public void testGetMostCommented() throws Exception {
        int number = newsDao.getNumberOfNews();
        Assert.assertEquals(number, newsDao.getMostCommented().size());
    }
}