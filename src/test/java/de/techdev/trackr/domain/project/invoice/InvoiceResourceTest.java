package de.techdev.trackr.domain.project.invoice;

import de.techdev.trackr.domain.AbstractDomainResourceTest;
import org.junit.Test;

import javax.json.stream.JsonGenerator;
import java.io.StringWriter;
import java.text.SimpleDateFormat;

import static de.techdev.trackr.domain.DomainResourceTestMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @author Moritz Schulze
 */
public class InvoiceResourceTest extends AbstractDomainResourceTest<Invoice> {

    @Override
    protected String getResourceName() {
        return "invoices";
    }

    @Override
    protected String getJsonRepresentation(Invoice invoice) {

        StringWriter writer = new StringWriter();
        JsonGenerator jg = jsonGeneratorFactory.createGenerator(writer);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        jg.writeStartObject()
                .write("identifier", invoice.getIdentifier())
                .write("invoiceState", invoice.getInvoiceState().toString())
                .write("invoiceTotal", invoice.getInvoiceTotal())
                .write("debitor", "/companies/" + invoice.getDebitor().getId())
                .write("creationDate", sdf.format(invoice.getCreationDate()));
        if (invoice.getDueDate() != null) {
            jg.write("dueDate", sdf.format(invoice.getDueDate()));
        }
        if(invoice.getId() != null) {
            jg.write("id", invoice.getId());
        }
        jg.writeEnd().close();
        return writer.toString();
    }

    @Test
    public void rootIsAccessible() throws Exception {
        assertThat(root(employeeSession()), isAccessible());
    }

    @Test
    public void oneIsAccessible() throws Exception {
        assertThat(one(employeeSession()), isAccessible());
    }

    @Test
    public void adminCanCreate() throws Exception {
        assertThat(create(adminSession()), isCreated());
    }

    @Test
    public void supervisorCannotCreate() throws Exception {
        assertThat(create(supervisorSession()), isForbidden());
    }
}
