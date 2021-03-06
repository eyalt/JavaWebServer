
package il.technion.cs236369.webserver.simplefilter;


import java.util.List;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;




public class FilterChainImpl implements FilterChain
{

	public FilterChainImpl(List<SimpleFilterWrapper> filters)
	{
		this.filters = filters;
		index = 0;
	}


	@Override
	public void doFilter(HttpRequest request, HttpResponse response)
	{
		while (index < filters.size())
		{
			final SimpleFilterWrapper filterWrapper = filters.get(index);
			index++;
			if (filterWrapper.isMatching(url))
			{
				filterWrapper.doFilter(request, response, this);
			}

		}
	}


	public void reset(String url)
	{
		this.url = url;
		index = 0;
	}



	private String url;

	private final List<SimpleFilterWrapper> filters;

	private int index;
}
