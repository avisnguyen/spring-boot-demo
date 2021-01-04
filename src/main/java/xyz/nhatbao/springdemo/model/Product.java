package xyz.nhatbao.springdemo.model;

import lombok.Data;

@Data //lombok constructor, getter, setter, hashCode, toString
public class Product {
    private String id;
    private String name;
}
