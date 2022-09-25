package com.server.operations;

import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@org.springframework.stereotype.Service
@Transactional
public class Service {

    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public String insertEC(@RequestBody Payload payload) {
        Item item = new Item(payload.getName(), payload.getDescription(), payload.getUrl(), payload.getTags());
        repository.save(item);
        return "Inserted " + item;
    }

    public String deleteEC(long id) {
        repository.deleteById(id);
        return "Success";
    }
    public List<Item> getWithTag(List<String> tags) {
        //todo this could be very much improved but I'd rather it work for now
        HashSet<String> tags_bucket = new HashSet<>(tags);
        List<Item> all = repository.fetchAll();
        List<Item> filtered = new ArrayList<>();
        for (Item item : all) {
            if (item.getTagsBucket().equals(tags_bucket)) {
                filtered.add(item);
            }
        }
        return filtered;
    }
    public List<Item> fetchAll(int amount) {
        var list  =repository.fetchAll();
        if (amount > 0) {
            return list.subList(0,Math.min(amount,list.size()));
        }
        return list;
    }
}
