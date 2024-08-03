package hello.item_service.domain.item;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class ItemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach(){
        itemRepository.clearStore();
    }

    @Test
    void save() {

        Item item = new Item("itemA", 1000,10);
        itemRepository.save(item);
        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem).isEqualTo(item);
    }

    @Test
    void findById() {
        Item item1 = new Item("itemA", 1000,10);



    }

    @Test
    void findAll() {
        Item item = new Item("itemA", 1000,10);
        Item item2 = new Item("itemB", 1200,3);
        itemRepository.save(item);
        itemRepository.save(item2);

        List<Item> all = itemRepository.findAll();

        assertThat(all).hasSize(2);
        assertThat(all).contains(item, item2);
    }

    @Test
    void update() {
        Item item = new Item("itemA", 1000,10);
        itemRepository.save(item);

        Item updateParam = new Item("itemB", 1200,3);
        itemRepository.update(item.getId(), updateParam);

        Item findItem = itemRepository.findById(item.getId());
        assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
        assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
    }

    @Test
    void clearStore() {
    }
}