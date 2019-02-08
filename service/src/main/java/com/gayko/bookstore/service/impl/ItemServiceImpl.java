
package com.gayko.bookstore.service.impl;

import com.gayko.bookstore.converters.Converter;
import com.gayko.bookstore.converters.DTOConverter;
import com.gayko.bookstore.converters.impl.dto.ItemDTOConverter;
import com.gayko.bookstore.converters.impl.entity.ItemConverter;
import com.gayko.bookstore.dao.ItemDao;
import com.gayko.bookstore.dao.model.Item;
import com.gayko.bookstore.dao.model.Order;
import com.gayko.bookstore.model.impl.ItemDTO;
import com.gayko.bookstore.service.ItemService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);

    private final ItemDao itemDao;
    private final Converter<ItemDTO, Item> itemConverter;
    private final DTOConverter<Item, ItemDTO> itemDTOConverter;

    @Autowired
    public ItemServiceImpl(
            ItemDao itemDao,
            ItemConverter itemConverter,
            ItemDTOConverter itemDTOConverter
    ) {
        this.itemDao = itemDao;
        this.itemConverter = itemConverter;
        this.itemDTOConverter = itemDTOConverter;
    }

    @Override
    public List<ItemDTO> save(List<ItemDTO> itemList) {
        List<Item> items = itemConverter.toEntityList(itemList);
        for (Item item : items) {
            itemDao.create(item);
        }
        return itemDTOConverter.toDTOList(items);
    }

    @Override
    public void create(ItemDTO item) {
        Item convertItem = itemConverter.toEntity(item);
        convertItem.setAlive(true);
        convertItem.setUniqueNumber(UUID.randomUUID().toString());
        itemDao.create(convertItem);
    }

    @Override
    public void update(ItemDTO itemDTO) {
        Item item = itemConverter.toEntity(itemDTO);
        itemDao.update(item);
    }

    @Override
    public List<ItemDTO> findItemsInPriceRange(int from, int to) {
        List<Item> items = itemDao.findItems(from, to);
        List<ItemDTO> listItems = itemDTOConverter.toDTOList(items);
        return listItems;
    }

    @Override
    public ItemDTO findById(Long id) {
        Item item = itemDao.findOne(id);
        return itemDTOConverter.toDTO(item);
    }

    @Override
    public List<ItemDTO> findAll() {
        List<Item> items = itemDao.findAll();
        return itemDTOConverter.toDTOList(items);
    }

    @Override
    public void upload(List<ItemDTO> items) {
        List<Item> convertItems = itemConverter.toEntityList(items);
        for (Item item : convertItems) {
            itemDao.create(item);
        }
    }

    @Override
    public void remove(Long id) {
        Item item = itemDao.findOne(id);
        List<Order> orders = item.getOrders();
        if (orders.isEmpty()) {
            item.setAlive(false);
            itemDao.update(item);
        }
    }

    /*@Override
    public void upload(*//*MultipartFile file*//*) {
        File file = new File("product.xml");

        try {
            if (file.() != 0) {
                JAXBContext context = JAXBContext.newInstance(XMLItem.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(new FileInputStream(file), "UTF-8"));
                XMLItem xmlItem = (XMLItem) unmarshaller.unmarshal(bufferedReader);
                List<ItemDTO> itemDTOList = xmlItem.getItems();

                for (ItemDTO itemDTO : itemDTOList) {
                    Item item = itemConverter.toEntity(itemDTO);
                    item.setUniqueNumber(UUID.randomUUID().toString());
                    itemDao.create(item);
                }
                file.delete();
            }
        } catch (Exception e) {
            logger.error("Failed to create items", e);
        }
    }*/
}





