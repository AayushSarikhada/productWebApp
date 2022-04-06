package app.java.dao;

import app.java.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;


@Repository
public class ProductDAO  {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    //create
    @Transactional
    public void createProduct(Product product){

        this.hibernateTemplate.saveOrUpdate(product);

    }

    //get all products
    public List<Product> getProducts(){
        return this.hibernateTemplate.loadAll(Product.class);
    }

    //delete a single product
    @Transactional
    public void deleteProduct(int pid){
        Product p = this.hibernateTemplate.load(Product.class,pid);
        this.hibernateTemplate.delete(p);
    }

    //get a single product
    public Product getProduct(int pid){
        return this.hibernateTemplate.get(Product.class,pid);
    }

}
