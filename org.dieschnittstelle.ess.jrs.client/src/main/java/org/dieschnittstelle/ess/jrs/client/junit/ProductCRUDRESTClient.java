package org.dieschnittstelle.ess.jrs.client.junit;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.ess.entities.erp.AbstractProduct;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;

import org.dieschnittstelle.ess.jrs.IProductCRUDService;

public class ProductCRUDRESTClient {

	private IProductCRUDService serviceProxy;
	
	protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(ProductCRUDRESTClient.class);

	public ProductCRUDRESTClient() throws Exception {


		/*
		 * create a client for the web service using ResteasyClientBuilder and ResteasyWebTarget
		 */
		serviceProxy = new IProductCRUDService() {
			@Override
			public IndividualisedProductItem createProduct(IndividualisedProductItem prod) {
				return null;
			}

			@Override
			public List<IndividualisedProductItem> readAllProducts() {
				return null;
			}

			@Override
			public IndividualisedProductItem updateProduct(long id, IndividualisedProductItem update) {
				return null;
			}

			@Override
			public boolean deleteProduct(long id) {
				return false;
			}

			@Override
			public IndividualisedProductItem readProduct(long id) {
				return null;
			}
		};
	}

	public AbstractProduct createProduct(IndividualisedProductItem prod) {
		AbstractProduct created = serviceProxy.createProduct(prod);
		// as a side-effect we set the id of the created product on the argument before returning
		prod.setId(created.getId());
		return created;
	}

	public List<?> readAllProducts() {
		return serviceProxy.readAllProducts();
	}

	public AbstractProduct updateProduct(AbstractProduct update) {
		return serviceProxy.updateProduct(update.getId(),(IndividualisedProductItem)update);
	}

	public boolean deleteProduct(long id) {
		return serviceProxy.deleteProduct(id);
	}

	public AbstractProduct readProduct(long id) {
		return serviceProxy.readProduct(id);
	}

}
