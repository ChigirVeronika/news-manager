package com.epam.newsmanager.service.impl;

import com.epam.newsmanager.dao.impl.CommentDaoImpl;
import com.epam.newsmanager.entity.Comment;
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
import static org.mockito.Mockito.*;

/**
 * Test class with Mockito Framework for <cod>CommentServiceImpl<cod/> class.
 */
@RunWith(MockitoJUnitRunner.class)
public class CommentServiceImplTest {

    @InjectMocks
    private CommentServiceImpl commentService;

    @Mock
    private CommentDaoImpl commentDao;

    @BeforeMethod
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInsertComment() throws Exception {
        Comment comment = new Comment();
        comment.setCommentId(1L);
        when(commentDao.insert(comment)).thenReturn(Long.valueOf(1));
        commentService.insertComment(comment);
        Assert.assertEquals(1, comment.getCommentId());
    }

    @Test
    public void testDeleteComment() throws Exception {
        Comment comment = new Comment();
        when(commentService.getCommentById(1L)).thenReturn(comment);

        commentService.insertComment(comment);
        commentService.deleteComment(1L);
        verify(commentDao, times(1)).insert(any(Comment.class));
        verify(commentDao, times(1)).delete(any(Long.class));
    }

    @Test
    public void testGetAllComments() throws Exception {
        Set<Comment> comments = new HashSet<>();
        comments.add(new Comment());
        when(commentDao.getAll()).thenReturn(comments);
        Assert.assertEquals(comments, commentDao.getAll());
    }

    @Test
    public void testGetCommentById() throws Exception {
        Comment comment = new Comment();
        comment.setCommentId(1L);
        when(commentDao.getById(1L)).thenReturn(comment);
        Comment newComment = commentService.getCommentById(1L);
        Assert.assertEquals(comment, newComment);
    }

    @Test
    public void testGetCommentByNewsId() throws Exception {
        Set<Comment> comments = new HashSet<>();
        comments.add(new Comment());
        when(commentDao.getByNewsId(1L)).thenReturn(comments);
        Set<Comment> newComments = commentDao.getByNewsId(1L);
        Assert.assertEquals(comments, newComments);
    }
}