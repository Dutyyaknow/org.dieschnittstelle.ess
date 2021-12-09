package org.dieschnittstelle.ess.mip.components.erp.crud.impl;

import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.PointOfSale;
import org.dieschnittstelle.ess.mip.components.erp.crud.api.ProductCRUD;
import org.dieschnittstelle.ess.utils.interceptors.Logged;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
@Logged
public class ProductCRUDImpl implements ProductCRUD {

    @Inject
    @EntityManagerProvider.ERPDataAccessor
    private EntityManager em;

    @Override
    public AbstractProduct createProduct(AbstractProduct prod) {
        em.persist(prod);
        return prod;
    }

    @Override
    public List<AbstractProduct> readAllProducts() {
        Query query = em.createQuery("SELECT ap FROM AbstractProduct ap");

        List<AbstractProduct> abstractProducts = (List<AbstractProduct>) query
                .getResultList();

        return abstractProducts;
    }

    @Override
    public AbstractProduct updateProduct(AbstractProduct update) {
        update = em.merge(update);
        return update;
    }

    @Override
    public AbstractProduct readProduct(long productID) {
        return em.find(AbstractProduct.class,productID);
    }

    @Override
    public boolean deleteProduct(long productID) {
        em.remove(em.find(AbstractProduct.class,productID));
        return true;
    }
}
