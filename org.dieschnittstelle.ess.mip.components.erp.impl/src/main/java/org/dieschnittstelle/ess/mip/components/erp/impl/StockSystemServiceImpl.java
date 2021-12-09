package org.dieschnittstelle.ess.mip.components.erp.impl;

import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.mip.components.erp.api.StockSystem;
import org.dieschnittstelle.ess.mip.components.erp.api.StockSystemService;
import org.dieschnittstelle.ess.mip.components.erp.crud.api.ProductCRUD;
import org.dieschnittstelle.ess.utils.interceptors.Logged;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Logged
@Transactional
public class StockSystemServiceImpl implements StockSystemService {

    @Inject
    private StockSystem stockSystem;

    @Inject
    private ProductCRUD productCRUD;

    @Override
    public void addToStock(long productId, long pointOfSaleId, int units) {

        AbstractProduct product = productCRUD.readProduct(productId);
        stockSystem.addToStock((IndividualisedProductItem) product, pointOfSaleId, units);
    }

    @Override
    public void removeFromStock(long productId, long pointOfSaleId, int units) {
        this.addToStock(productId, pointOfSaleId, -units);
    }

    @Override
    public List<IndividualisedProductItem> getProductsOnStock(long pointOfSaleId) {
        return pointOfSaleId > 0
                ? this.stockSystem.getProductsOnStock(pointOfSaleId)
                : this.stockSystem.getAllProductsOnStock();
    }

    @Override
    public int getUnitsOnStock(long productId, long pointOfSaleId) {
        AbstractProduct product = productCRUD.readProduct(productId);

        return pointOfSaleId > 0
                ? this.stockSystem.getUnitsOnStock((IndividualisedProductItem) product, pointOfSaleId)
                : this.stockSystem.getTotalUnitsOnStock((IndividualisedProductItem) product);
    }

    @Override
    public List<Long> getPointsOfSale(long productId) {
        AbstractProduct product = productCRUD.readProduct(productId);

        return this.stockSystem.getPointsOfSale((IndividualisedProductItem) product);
    }
}
