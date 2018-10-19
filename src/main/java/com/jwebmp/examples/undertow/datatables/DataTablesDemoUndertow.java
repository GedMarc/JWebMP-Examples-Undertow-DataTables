/*
 * Copyright (C) 2017 Marc Magon
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.jwebmp.examples.undertow.datatables;

import com.jwebmp.core.Page;
import com.jwebmp.core.base.html.TableCell;
import com.jwebmp.core.base.html.TableHeaderCell;
import com.jwebmp.core.base.html.TableHeaderGroup;
import com.jwebmp.core.base.html.TableRow;
import com.jwebmp.core.services.IPage;
import com.jwebmp.plugins.datatable.DataTable;
import com.jwebmp.plugins.datatable.DataTablePageConfigurator;
import com.jwebmp.plugins.datatable.enumerations.DataTableButtons;
import com.jwebmp.plugins.datatable.enumerations.DataTableThemes;
import com.jwebmp.plugins.datatable.options.DataTablesDomOptions;
import com.jwebmp.plugins.datatable.options.buttons.DataTablesButtonButtonsOptions;
import com.jwebmp.undertow.JWebMPUndertow;

import javax.servlet.ServletException;

public class DataTablesDemoUndertow
		extends Page
		implements IPage
{
	public DataTablesDemoUndertow()
	{
		super("JWebSwing - Data Tables Demo");
		int rows = 100;
		int cols = 5;

		TableHeaderGroup thg = new TableHeaderGroup();

		DataTablePageConfigurator.getThemes()
		                         .add(DataTableThemes.JQueryUI);

		DataTable<?, ?> dt = new DataTable("dt", thg);
		dt.getOptions()
		  .setjQueryUI(true);


		dt.getOptions()
		  .setDom(DataTablesDomOptions.getDefault());

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
	public static void main(String[] args) throws Exception
	{
		JWebMPUndertow.boot("0.0.0.0", 6002);

	}
}
