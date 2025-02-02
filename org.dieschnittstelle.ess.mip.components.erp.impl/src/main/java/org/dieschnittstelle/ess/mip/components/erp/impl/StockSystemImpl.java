package org.dieschnittstelle.ess.mip.components.erp.impl;

import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.dieschnittstelle.ess.entities.erp.PointOfSale;
import org.dieschnittstelle.ess.entities.erp.StockItem;
import org.dieschnittstelle.ess.mip.components.erp.api.StockSystem;
import org.dieschnittstelle.ess.mip.components.erp.crud.api.PointOfSaleCRUD;
import org.dieschnittstelle.ess.mip.components.erp.crud.impl.StockItemCRUD;
import org.dieschnittstelle.ess.utils.interceptors.Logged;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
@Transactional
@Logged
public class StockSystemImpl implements StockSystem {

    @Inject
    private PointOfSaleCRUD posCRUD;

    @Inject
    private StockItemCRUD stockItemCRUD;

    @Override
    public void addToStock(IndividualisedProductItem product, long pointOfSaleId, int units) {

        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        StockItem stockItem = stockItemCRUD.readStockItem(product, pos);

        if (stockItem != null) {
            stockItem.setUnits(stockItem.getUnits()+units);
            stockItemCRUD.updateStockItem(stockItem);
        }
        else {
            stockItem = new StockItem(product, pos, units);
            stockItemCRUD.createStockItem(stockItem);
        }

    }

    @Override
    public void removeFromStock(IndividualisedProductItem product, long pointOfSaleId, int units) {
        this.addToStock(product, pointOfSaleId, -units);
    }

    @Override
    public List<IndividualisedProductItem> getProductsOnStock(long pointOfSaleId) {

        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        List<StockItem> stockItems = stockItemCRUD.readStockItemsForPointOfSale(pos);

        List<IndividualisedProductItem> products = new ArrayList<>();

        for (StockItem s : stockItems) {
            products.add(s.getProduct());
        }

        return products;
    }

    @Override
    public List<IndividualisedProductItem> getAllProductsOnStock() {
        List<PointOfSale> pos = posCRUD.readAllPointsOfSale();
        Set<IndividualisedProductItem> productItemSet = new HashSet<>();

        for (PointOfSale p : pos) {
            productItemSet.addAll(getProductsOnStock(p.getId()));
        }

        List<IndividualisedProductItem> products = new ArrayList<>(productItemSet);

        return products;
    }

    @Override
    public int getUnitsOnStock(IndividualisedProductItem product, long pointOfSaleId) {
        PointOfSale pos = posCRUD.readPointOfSale(pointOfSaleId);
        StockItem stockItem = stockItemCRUD.readStockItem(product, pos);

        return stockItem.getUnits();
    }

    @Override
    public int getTotalUnitsOnStock(IndividualisedProductItem product) {
        List<StockItem> stockItems = stockItemCRUD.readStockItemsForProduct(product);
        int totalUnits = 0;

        for (StockItem s : stockItems) {
            totalUnits += s.getUnits();
        }

        return totalUnits;
    }

    @Override
    public List<Long> getPointsOfSale(IndividualisedProductItem product) {
        List<StockItem> stockItems = stockItemCRUD.readStockItemsForProduct(product);
        List<Long> posIds = new ArrayList<>();

        for (StockItem s : stockItems) {
            posIds.add(s.getPos().getId());
        }

        return posIds;
    }
}
