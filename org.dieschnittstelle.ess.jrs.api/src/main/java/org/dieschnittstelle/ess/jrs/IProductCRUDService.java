package org.dieschnittstelle.ess.jrs;

import org.dieschnittstelle.ess.entities.erp.IndividualisedProductItem;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/*
 * UE JRS2:
 * deklarieren Sie hier Methoden fuer:
 * - die Erstellung eines Produkts
 * - das Auslesen aller Produkte
 * - das Auslesen eines Produkts
 * - die Aktualisierung eines Produkts
 * - das Loeschen eines Produkts
 * und machen Sie diese Methoden mittels JAX-RS Annotationen als WebService verfuegbar
 */

@Path("/products")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
/*
 * TODO JRS3: aendern Sie Argument- und Rueckgabetypen der Methoden von IndividualisedProductItem auf AbstractProduct
 */
public interface IProductCRUDService {

	@Operation
	@POST
	public IndividualisedProductItem createProduct(IndividualisedProductItem prod);

	@Operation
	@GET
	public List<IndividualisedProductItem> readAllProducts();

	@Operation
	@PUT
	@Path("/{id}")
	public IndividualisedProductItem updateProduct(@PathParam("id") long id,
												   IndividualisedProductItem update);

	@Operation
	@DELETE
	@Path("/{id}")
	boolean deleteProduct(@PathParam("id") long id);

	@Operation
	@GET
	@Path("/{id}")
	public IndividualisedProductItem readProduct(@PathParam("id") long id);

}
