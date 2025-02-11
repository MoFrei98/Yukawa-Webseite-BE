package com.yukawawebseite.models.product;

public enum ProductCategory {

    TEXTILES("Textiles"),
    MUSIC("Music");

    private final String categoryName;

    ProductCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return categoryName;
    }
}
