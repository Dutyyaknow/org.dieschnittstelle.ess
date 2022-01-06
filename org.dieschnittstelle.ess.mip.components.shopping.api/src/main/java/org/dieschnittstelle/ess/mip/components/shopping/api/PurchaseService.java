package org.dieschnittstelle.ess.mip.components.shopping.api;

// TODO: PAT1: this is the interface to be provided as a rest service if rest service access is used

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/purchase")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface PurchaseService {

	@POST
	@Path("/{shoppingCartId}/{touchpointId}/{customerId}")
	void purchaseCartAtTouchpointForCustomer(@PathParam("shoppingCartId") long shoppingCartId, @PathParam("touchpointId") long touchpointId, @PathParam("customerId") long customerId) throws ShoppingException;
	
}
