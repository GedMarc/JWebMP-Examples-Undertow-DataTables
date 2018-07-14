package com.jwebmp.examples.undertow.helloworld;

import com.jwebmp.plugins.datatable.DataTableData;
import com.jwebmp.plugins.datatable.events.DataTableDataFetchEvent;
import com.jwebmp.plugins.datatable.search.DataTableSearchRequest;

import java.util.ArrayList;
import java.util.List;

public class DataTablesDataServe extends DataTableDataFetchEvent
{
	@Override
	public MyData returnData(DataTableSearchRequest searchRequest)
	{
		MyData data = new MyData();
		data.loadData(searchRequest.getStart(),searchRequest.getLength());
		return data;
	}

	class MyData extends DataTableData
	{
		public List<List<String>> data;

		public MyData()
		{
			setRecordsTotal(100);
			setRecordsFiltered(50);
		}

		public void loadData(Integer start, Integer length)
		{
			data = new ArrayList<>();
			if(start == null)
				start = 0;
			if (length == null) {
				length = 10;
			}

			for (int i = 0,j = start; i < length; i++,j++)
			{
				List<String> cells = new ArrayList<>();
				cells.add("Data " + (j + 1));
				cells.add("Data " + (j + 1));
				cells.add("Data " + (j + 1));
				cells.add("Data " + (j + 1));
				cells.add("Data " + (j + 1));
				data.add(cells);
			}
		}
	}

}
