import com.jwebmp.core.services.IPage;
import com.jwebmp.examples.undertow.datatables.DataTablesDemoUndertow;

module com.jwebmp.examples.undertow.datatables {
	requires com.jwebmp.plugins.datatable;
	requires com.jwebmp.core;
	requires com.jwebmp.undertow;
	requires javax.servlet.api;

	provides IPage with DataTablesDemoUndertow;

	opens com.jwebmp.examples.undertow.datatables to com.google.guice, com.fasterxml.jackson.databind;
}
