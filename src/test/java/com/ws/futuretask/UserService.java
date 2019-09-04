package com.ws.futuretask;

/**
 * @author yunhua
 * @since 2019-08-26
 */
public class UserService {

    private Long id;

    private String name;

    private Integer sort;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
