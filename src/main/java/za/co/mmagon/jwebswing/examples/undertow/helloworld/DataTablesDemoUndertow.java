package za.co.mmagon.jwebswing.examples.undertow.helloworld;

import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import za.co.mmagon.guiceinjection.GuiceContext;
import za.co.mmagon.jwebswing.Page;
import za.co.mmagon.jwebswing.base.html.TableCell;
import za.co.mmagon.jwebswing.base.html.TableHeaderCell;
import za.co.mmagon.jwebswing.base.html.TableHeaderGroup;
import za.co.mmagon.jwebswing.base.html.TableRow;
import za.co.mmagon.jwebswing.plugins.jqdatatable.DataTable;
import za.co.mmagon.jwebswing.plugins.jqdatatable.DataTablePageConfigurator;
import za.co.mmagon.jwebswing.plugins.jqdatatable.enumerations.DataTableButtons;
import za.co.mmagon.jwebswing.plugins.jqdatatable.enumerations.DataTableThemes;
import za.co.mmagon.jwebswing.plugins.jqdatatable.options.DataTablesButtonButtonsOptions;
import za.co.mmagon.jwebswing.plugins.jqdatatable.options.DataTablesButtonsOptions;
import za.co.mmagon.jwebswing.plugins.jqdatatable.options.DataTablesDomOptions;
import za.co.mmagon.logger.LogFactory;
import za.co.mmagon.logger.handlers.ConsoleSTDOutputHandler;

import javax.servlet.ServletException;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataTablesDemoUndertow extends Page
{
	public DataTablesDemoUndertow()
	{
		super("JWebSwing - Data Tables Demo");
		int rows = 100;
		int cols = 5;

		TableHeaderGroup thg = new TableHeaderGroup();

		DataTablePageConfigurator.getThemes().add(DataTableThemes.JQueryUI);

		DataTable<?, ?> dt = new DataTable("dt", thg);
		dt.getOptions()
				.setjQueryUI(true);
		dt.getOptions()
				.setDom(DataTablesDomOptions.fromString("Bfrtip"));

		TableRow thr = new TableRow();
		for (int i = 0; i < cols; i++)
		{
			thr.add(new TableHeaderCell<>("Test Col " + (i + 1)));
		}
		thg.add(thr);
		add(dt);

		for (int i = 0; i < rows; i++)
		{
			dt.add(new TableRow<>().add(new TableCell<>("Data " + (i + 1)))
					       .add(new TableCell<>("Data Cell " + (i + 1)))
					       .add(new TableCell<>("Data Cell " + (i + 1)))
					       .add(new TableCell<>("Data Cell " + (i + 1)))
					       .add(new TableCell<>("Data Cell " + (i + 1))));
		}
/*
		dt.getOptions()
				.getButtons()
				.add("copy");
		dt.getOptions()
				.getButtons()
				.add("excel");
		dt.getOptions()
				.getButtons()
				.add("pdf");

		dt.getOptions()
				.getButtons()
				.add(new DataTablesButtonButtonsOptions<>().setName("copy"));
*/

		dt.getOptions()
				.getButtons()
				.add(new DataTablesButtonButtonsOptions<>().setExtend(DataTableButtons.Copy));
		dt.getOptions()
				.getButtons()
				.add(new DataTablesButtonButtonsOptions<>().setExtend(DataTableButtons.Excel));
		dt.getOptions()
				.getButtons()
				.add(new DataTablesButtonButtonsOptions<>().setExtend(DataTableButtons.Csv));
		dt.getOptions()
				.getButtons()
				.add(new DataTablesButtonButtonsOptions<>().setExtend(DataTableButtons.Print));
		dt.getOptions()
				.getButtons()
				.add(new DataTablesButtonButtonsOptions<>().setExtend(DataTableButtons.Pdf));

		//dt.getOptions().getAutoFill();
/*
		dt.getOptions()
				.getSearch()
				.setSmart(true);*/

		dt.asMe()
				.addServerDataSource(DataTablesDataServe.class);


		dt.getOptions()
				.getButtons();
	}

	/**
	 * This part runs the web site :)
	 *
	 * @param args
	 *
	 * @throws ServletException
	 */
	public static void main(String[] args) throws ServletException
	{
		Handler[] handles = Logger.getLogger("")
				                    .getHandlers();
		for (Handler handle : handles)
		{
			handle.setLevel(Level.FINE);
		}
		LogFactory.setDefaultLevel(Level.FINE);
		Logger.getLogger("")
				.addHandler(new ConsoleSTDOutputHandler(true));

		DeploymentInfo servletBuilder = Servlets.deployment()
				                                .setClassLoader(DataTablesDemoUndertow.class.getClassLoader())
				                                .setContextPath("/")
				                                .setDeploymentName("datatablesdemo.war");

		DeploymentManager manager = Servlets.defaultContainer()
				                            .addDeployment(servletBuilder);

		manager.deploy();
		GuiceContext.inject();

		HttpHandler jwebSwingHandler = manager.start();

		Undertow server = Undertow.builder()
				                  .addHttpListener(6002, "localhost")
				                  .setHandler(jwebSwingHandler)
				                  .build();

		server.start();
	}
}
