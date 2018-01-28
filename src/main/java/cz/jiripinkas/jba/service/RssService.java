package cz.jiripinkas.jba.service;


import cz.jiripinkas.jba.entity.Item;
import cz.jiripinkas.jba.exceptions.RssException;
import cz.jiripinkas.jba.rss.ObjectFactory;
import cz.jiripinkas.jba.rss.TRss;
import cz.jiripinkas.jba.rss.TRssChannel;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class RssService {

    public List<Item> getItems(File file) throws RssException {
        return getItems(new StreamSource(file));
    }

    public List<Item> getItems(String url) throws RssException {
        return getItems(new StreamSource(url));
    }


    private List<Item> getItems(Source source) throws RssException {
        List<Item> listOfItems = new ArrayList<>();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ObjectFactory.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            JAXBElement<TRss> jaxbElement = unmarshaller.unmarshal(source, TRss.class);
            TRss tRss = jaxbElement.getValue();
            List<TRssChannel> channels = tRss.getChannel();
            channels.stream().map(channel -> channel.getItem()).forEach(items -> items.stream().forEach(rssItem -> {
                Item item = new Item();
                item.setTitle(rssItem.getTitle());
                String rssItemDescription = rssItem.getDescription();
                /*if (rssItemDescription.length() > 50) {
                    StringBuffer buffer = new StringBuffer();
                    for (String word : rssItemDescription.split("\\s")) {
                        if (buffer.length() + word.length() + 1 > 47) {
                            break;
                        }
                        buffer.append(word + " ");
                    }
                    buffer.append("...");
                    rssItemDescription = String.valueOf(buffer);
                }*/
                item.setDescription(rssItemDescription);
                item.setLink(rssItem.getLink());
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
                    Date date = simpleDateFormat.parse(rssItem.getPubDate());
                    item.setPublishedDate(date);
                } catch (ParseException e) {
                    try {
                        throw new RssException(e.getCause());
                    } catch (RssException e1) {
                        e1.printStackTrace();
                    }
                }
                listOfItems.add(item);
            }));

        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return listOfItems;
    }

}
