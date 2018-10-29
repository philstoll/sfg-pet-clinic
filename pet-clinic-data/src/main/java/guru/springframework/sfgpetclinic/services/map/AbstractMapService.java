package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.BaseEntity;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long> {
    protected Map<Long, T> map = new HashMap<>();

    Set<T> findAll() {
        return new HashSet<>(this.map.values());
    }

    T findById(ID id) {
        return this.map.get(id);
    }

    T save(T object) {

        if (null == object) {
            throw new RuntimeException("Object is null");
        }

        if (object.getId() == null) {
            object.setId(this.getNextId());
        }
        this.map.put(object.getId(), object);

        return object;
    }

    void deleteById(ID id) {
        this.map.remove(id);
    }

    void delete(T object) {
        this.map.entrySet().removeIf(entry -> entry.getValue().equals(object));
    }

    private Long getNextId() {
        Long nextId = null;
        try {
            nextId = Collections.max(this.map.keySet()) + 1;
        } catch (NoSuchElementException e) {
            nextId = 1L;
        }

        return nextId;
    }
}
