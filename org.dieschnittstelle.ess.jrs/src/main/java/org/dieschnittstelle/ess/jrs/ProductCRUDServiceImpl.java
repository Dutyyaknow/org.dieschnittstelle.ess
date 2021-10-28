package org.dieschnittstelle.ess.jrs;

import org.apache.logging.log4j.Logger;
import org.dieschnittstelle.ess.entities.GenericCRUDExecutor;
import org.dieschnittstelle.ess.entities.crm.StationaryTouchpoint;
import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import java.util.List;

/*
 * TODO JRS2: implementieren Sie hier die im Interface deklarierten Methoden
 */

public class ProductCRUDServiceImpl implements IProductCRUDService {

	protected static Logger logger = org.apache.logging.log4j.LogManager.getLogger(TouchpointCRUDServiceImpl.class);

	private GenericCRUDExecutor<IndividualisedProductItem> touchpointCRUD;

	public ProductCRUDServiceImpl(@Context ServletContext servletContext, @Context HttpServletRequest request) {
		logger.info("<constructor>: " + servletContext + "/" + request);
		// read out the dataAccessor
		this.touchpointCRUD = (GenericCRUDExecutor<IndividualisedProductItem>) servletContext.getAttribute("touchpointCRUD");

		logger.debug("read out the touchpointCRUD from the servlet context: " + this.touchpointCRUD);
	}

	@Override
	public IndividualisedProductItem createProduct(
			IndividualisedProductItem prod) {
		return (IndividualisedProductItem) this.touchpointCRUD.createObject(prod);
	}

	@Override
	public List<IndividualisedProductItem> readAllProducts() {
		return (List) this.touchpointCRUD.readAllObjects();
	}

	@Override
	public IndividualisedProductItem updateProduct(long id,
			IndividualisedProductItem update) {
		update.setId(id);
		return this.touchpointCRUD.updateObject(update);
	}

	@Override
	public boolean deleteProduct(long id) {
		return this.touchpointCRUD.deleteObject(id);
	}

	@Override
	public IndividualisedProductItem readProduct(long id) {
		return this.touchpointCRUD.readObject(id);
	}
	
}
