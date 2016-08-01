package com.epam.newsmanager.service.impl;

import com.epam.newsmanager.dao.impl.TagDaoImpl;
import com.epam.newsmanager.entity.Tag;
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
 * Test class with Mockito Framework for <cod>TagServiceImpl<cod/> class.
 */
@RunWith(MockitoJUnitRunner.class)
public class TagServiceImplTest {

    @InjectMocks
    private TagServiceImpl tagService;

    @Mock
    private TagDaoImpl tagDao;

    @BeforeMethod
    public void initMocks(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testInsertTag() throws Exception {
        Tag tag = new Tag();
        tag.setTagId(1L);
        when(tagDao.insert(tag)).thenReturn(1L);
        tagService.insertTag(tag);
        Assert.assertEquals(1, tag.getTagId());
    }

    @Test
    public void testDeleteTag() throws Exception {
        Tag tag = new Tag();
        tag.setTagId(1L);
        when(tagDao.getById(1L)).thenReturn(tag);

        tagService.insertTag(tag);
        tagService.deleteTag(1L);
        verify(tagDao, times(1)).insert(any(Tag.class));
        verify(tagDao, times(1)).delete(any(Long.class));
    }

    @Test
    public void testGetAllTags() throws Exception {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag());
        when(tagDao.getAll()).thenReturn(tags);

        Set<Tag> newTags = tagDao.getAll();
        Assert.assertEquals(tags, newTags);
    }

    @Test
    public void testGetTagById() throws Exception {
        Tag tag = new Tag();
        tag.setTagId(1L);
        when(tagDao.getById(1L)).thenReturn(tag);

        Tag newTag = tagService.getTagById(1l);
        Assert.assertEquals(tag, newTag);
    }

    @Test
    public void testGetTagByName() throws Exception {
        Tag tag = new Tag();
        tag.setTagName("cool tag");
        when(tagDao.getByName(tag)).thenReturn(tag);
        Tag newTag = tagService.getTagByName(tag);
        Assert.assertEquals(tag, newTag);
    }

}