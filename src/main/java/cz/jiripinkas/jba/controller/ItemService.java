package cz.jiripinkas.jba.controller;


import cz.jiripinkas.jba.entity.Item;
import cz.jiripinkas.jba.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> getItems() {
        PageRequest publishedDate = new PageRequest(0, 20, Sort.Direction.DESC, "publishedDate");
        return itemRepository.findAll(publishedDate).getContent();
    }

}
