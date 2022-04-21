package com.bus.service;

import com.bus.entity.Product;
import com.bus.util.Page;

import java.util.List;

public interface ProductService {

    int addProduct(Product p);//增加

    List<Product> findAllProduct();//查找所有产品

    Page<Product> findProductByPage(Page<Product> page);

    Page<Product> findProductByCondition(String name, Page<Product> page);

    int  removeProductByID(Integer id);//删

    int updateProductByID(Product p);//改

    Product findProductByID(Integer id);//查

    int findAllProductName(String product_name);
}
