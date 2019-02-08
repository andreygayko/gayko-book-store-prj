package com.gayko.bookstore.model.impl;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "items")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLItem {

        @XmlElementWrapper(name = "items")
        @XmlElement(name = "item")
        private List<ItemDTO> items;

        public List<ItemDTO> getItems() {
            return items;
        }

        public void setItems(List<ItemDTO> items) {
            this.items = items;
        }
    }

