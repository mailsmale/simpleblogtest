package cz.jiripinkas.jba.cz.jiripinkas.jba;

import cz.jiripinkas.jba.entity.Item;
import cz.jiripinkas.jba.exceptions.RssException;
import cz.jiripinkas.jba.service.RssService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class RssServiceTest {

    private RssService rssService;

    @Before
    public void setUp() throws Exception {
        rssService = new RssService();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetItemsFile() throws RssException {
        File file = new File("src/test/resources/JavaVids1.xml");
        List<Item> items = rssService.getItems(file);
        assertEquals(10, items.size());
        Item item = items.get(0);
        System.out.printf(item.getTitle());
        assertEquals("How to solve Source not found error during debug in Eclipse", item.getTitle());
        assertEquals("22 Jun 2014 23:35:49" , new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(item.getPublishedDate()));
    }

}
