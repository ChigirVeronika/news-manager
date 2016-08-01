package com.epam.newsmanager.dao.impl;

import com.epam.newsmanager.dao.CommentDao;
import com.epam.newsmanager.entity.Comment;
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
 * Test class with BDUnit technology for CommentDaoImpl class.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= "classpath:src/test/resources/test-db.xml")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
public class CommentDaoImplTest {

    @Autowired
    private CommentDao commentsDao;

    @Transactional
    @Rollback(true)
    @Test
    public void testInsert() throws Exception {
        Comment comment = new Comment();
        comment.setNewsId(1);
        comment.setCreationDate(new Timestamp(new Date().getTime()));
        comment.setCommentText("Something");
        commentsDao.insert(comment);
        Assert.assertNotEquals(comment.getCommentId(), 0);
    }

    @DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
    @Test
    public void testUpdate() throws Exception {
        Comment comment = new Comment();
        comment.setCommentId(1);
        comment.setNewsId(1);
        comment.setCreationDate(Timestamp.valueOf("2016-06-29 16:07:10"));
        comment.setCommentText("Something");
        commentsDao.update(comment);
        Assert.assertEquals(comment, commentsDao.getById(1L));
    }

    @DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
    @Test
    public void testDelete() throws Exception {
        commentsDao.delete(1L);
        Assert.assertEquals(commentsDao.getById(1L), null);
    }

    @DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
    @Test
    public void testGetAll() throws Exception {
        Comment comment = new Comment();
        comment.setCommentId(1);
        comment.setNewsId(1);
        comment.setCreationDate(Timestamp.valueOf("2016-06-29 16:07:10"));
        comment.setCommentText("Something");

        Comment comment1 = new Comment();
        comment1.setCommentId(3);
        comment1.setNewsId(1);
        comment1.setCreationDate(Timestamp.valueOf("2016-06-29 16:07:10"));
        comment1.setCommentText("Hello");

        assertThat(commentsDao.getAll()).contains(comment, comment1);
    }

    @DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
    @Test
    public void testGetById() throws Exception {
        Comment comment = new Comment();
        comment.setCommentId(1);
        comment.setNewsId(1);
        comment.setCreationDate(Timestamp.valueOf("2016-06-29 16:07:10"));
        comment.setCommentText("Something");
        Assert.assertEquals(comment, commentsDao.getById(1L));
    }

    @DatabaseSetup(value = "classpath:dataset.xml", type = DatabaseOperation.CLEAN_INSERT)
    @DatabaseTearDown(value = "classpath:dataset.xml", type = DatabaseOperation.DELETE_ALL)
    @Test
    public void testGetByNewsId() throws Exception {
        Set<Comment> comments = new HashSet<Comment>(2);
        Comment comment = new Comment();
        comment.setCommentId(1);
        comment.setNewsId(1);
        comment.setCreationDate(Timestamp.valueOf("2016-06-20 20:15:11"));
        comment.setCommentText("Something");
        Comment comment1 = new Comment();
        comment1.setCommentId(3);
        comment1.setNewsId(1);
        comment1.setCreationDate(Timestamp.valueOf("2016-06-20 20:15:11"));
        comment1.setCommentText("Hello");
        comments.add(comment);
        comments.add(comment1);
        Assert.assertEquals(comments, commentsDao.getByNewsId(1L));
    }
}